package com.bskyb.internettv.thirdparty.services;

import com.bskyb.internettv.thirdparty.exception.InvalidInputException;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;

public interface MovieService {

    String getParentalControlLevel(String titleId) throws TitleNotFoundException, InvalidInputException;
}
