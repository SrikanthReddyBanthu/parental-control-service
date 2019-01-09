package com.bskyb.internettv.parental_control_service.services;

import com.bskyb.internettv.parental_control_service.services.exception.ParentalControlLevelNotFoundException;
import com.bskyb.internettv.parental_control_service.services.exception.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.exception.InvalidInputException;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;

public interface ParentalControlService {

    boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TechnicalFailureException, ParentalControlLevelNotFoundException, TitleNotFoundException, InvalidInputException;
}
