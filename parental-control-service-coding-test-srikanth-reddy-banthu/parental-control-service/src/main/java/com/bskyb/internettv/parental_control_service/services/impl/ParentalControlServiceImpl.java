package com.bskyb.internettv.parental_control_service.services.impl;

import com.bskyb.internettv.parental_control_service.services.ParentalControlService;
import com.bskyb.internettv.parental_control_service.services.ParentalControlLevels;
import com.bskyb.internettv.parental_control_service.services.exception.ParentalControlLevelNotFoundException;
import com.bskyb.internettv.parental_control_service.services.exception.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.exception.InvalidInputException;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;
import com.bskyb.internettv.thirdparty.services.MovieService;

import java.util.Arrays;

/**
 * ParentalControlService is responsible to identify is someone can watch a movie or not
 */
public class ParentalControlServiceImpl implements ParentalControlService {

    private static final int INVALID_PARENTAL_CONTROL_LEVEL_RANK = -1;
    private static final String PARENTAL_CONTROL_LEVEL_NOT_FOUND_MESSAGE = "Parental control level: %s is not valid";
    private final MovieService movieService;

    public ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }


    /**
     * This method will identify is a movie can be watched based on given parental control level and movieId
     *
     * @param customerParentalControlLevel customer parental control level
     * @param movieId movie title ID
     *
     * @return boolean value
     *
     * @throws InvalidInputException this exception will be thrown when titleId is not valid
     * @throws InvalidInputException this exception will be thrown when titleId is not valid
     * @throws ParentalControlLevelNotFoundException this exception will be thrown when parental control level is invalid
     * @throws TechnicalFailureException this exception will be thrown when unexpected exception raises in system
     */
    @Override
    public boolean canWatchMovie(final String customerParentalControlLevel, final String movieId)
            throws TechnicalFailureException, ParentalControlLevelNotFoundException, TitleNotFoundException,
            InvalidInputException {

        try {
            final int customerParentalControlLevelRank = getParentalControlLevelRank(customerParentalControlLevel);

            if (customerParentalControlLevelRank == INVALID_PARENTAL_CONTROL_LEVEL_RANK) {
                throw new ParentalControlLevelNotFoundException(
                        String.format(PARENTAL_CONTROL_LEVEL_NOT_FOUND_MESSAGE, customerParentalControlLevel));
            }

            final String movieParentalControlLevel = movieService.getParentalControlLevel(movieId);
            final int movieParentalControlLevelRank = getParentalControlLevelRank(movieParentalControlLevel);

            return movieParentalControlLevelRank <= customerParentalControlLevelRank;
        } catch (ParentalControlLevelNotFoundException | TitleNotFoundException | InvalidInputException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechnicalFailureException("Technical fault in service", e);
        }
    }

    private int getParentalControlLevelRank(final String parentalControlLevel) {

        return Arrays.stream(ParentalControlLevels.values())
                .filter(x -> x.getParentalControlLevel().equals(parentalControlLevel)).findFirst()
                .map(ParentalControlLevels::getParentalControlLevelRank).orElse(INVALID_PARENTAL_CONTROL_LEVEL_RANK);
    }
}
