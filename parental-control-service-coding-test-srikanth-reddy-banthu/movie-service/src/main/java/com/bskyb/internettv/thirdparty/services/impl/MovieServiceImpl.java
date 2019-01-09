package com.bskyb.internettv.thirdparty.services.impl;

import com.bskyb.internettv.thirdparty.exception.InvalidInputException;
import com.bskyb.internettv.thirdparty.services.MovieService;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import static com.bskyb.internettv.thirdparty.ThirdPartyConstants.MOVIES_DATA;

/**
 * MovieService is responsible to evaluate parental control level
 */
public class MovieServiceImpl implements MovieService {

    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "Invalid movie title : %s";
    private static final String TITLE_NOT_FOUND_EXCEPTION_MESSAGE = "Can't find movie title : %s";

    /**
     * This method will return parental control level from MOVIES_DATA
     *
     * @param titleId movie title ID
     *
     * @return parental control level as String value
     *
     * @throws TitleNotFoundException this exception will be thrown when titleId not found
     * @throws InvalidInputException this exception will be thrown when titleId is not valid
     */
    @Override
    public String getParentalControlLevel(final String titleId) throws TitleNotFoundException, InvalidInputException {

        if (isEmpty(titleId)) {
            throw new InvalidInputException(String.format(INVALID_INPUT_EXCEPTION_MESSAGE, titleId));
        }

        final String parentalControlLevel = MOVIES_DATA.get(titleId.toLowerCase());

        if (isEmpty(parentalControlLevel)) {
            throw new TitleNotFoundException(String.format(TITLE_NOT_FOUND_EXCEPTION_MESSAGE, titleId));
        }

        return parentalControlLevel;
    }
}
