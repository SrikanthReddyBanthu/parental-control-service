package com.bskyb.internettv.thirdparty.services.impl;

import com.bskyb.internettv.thirdparty.exception.InvalidInputException;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;
import com.bskyb.internettv.thirdparty.services.MovieService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MovieServiceImplTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private MovieService movieService = new MovieServiceImpl();

    @Test
    public void testShouldReturnParentalControlLevelWhenMovieDataHasMovieInformation() throws TitleNotFoundException, InvalidInputException {

        final String actualParentalControlLevel = movieService.getParentalControlLevel("movie1");

        assertEquals("U", actualParentalControlLevel);
    }

    @Test
    public void testShouldReturnParentalControlLevelWhenMovieDataHasMovieInformationAndTitleIsPassedInUpperCase() throws TitleNotFoundException, InvalidInputException {

        final String actualParentalControlLevel = movieService.getParentalControlLevel("MOVIE2");

        assertEquals("PG", actualParentalControlLevel);
    }

    @Test
    public void testShouldReturnThrowExceptionWhenMovieDataHasNoMovieInformation() throws TitleNotFoundException, InvalidInputException {

        thrown.expect(TitleNotFoundException.class);
        thrown.expectMessage(is("Can't find movie title : movie10"));

        movieService.getParentalControlLevel("movie10");
    }

    @Test
    public void testShouldReturnThrowExceptionWhenEmptyMovieTitleIsPassed() throws TitleNotFoundException, InvalidInputException {

        thrown.expect(InvalidInputException.class);
        thrown.expectMessage(is("Invalid movie title : "));

        movieService.getParentalControlLevel("");

    }

    @Test
    public void testShouldReturnThrowExceptionWhenNullMovieTitleIsPassed() throws TitleNotFoundException, InvalidInputException {

        thrown.expect(InvalidInputException.class);
        thrown.expectMessage(is("Invalid movie title : null"));

        movieService.getParentalControlLevel(null);
    }
}