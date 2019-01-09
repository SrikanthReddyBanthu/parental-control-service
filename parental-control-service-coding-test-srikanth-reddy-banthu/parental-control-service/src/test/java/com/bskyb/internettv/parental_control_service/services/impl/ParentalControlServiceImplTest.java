package com.bskyb.internettv.parental_control_service.services.impl;

import com.bskyb.internettv.parental_control_service.services.exception.ParentalControlLevelNotFoundException;
import com.bskyb.internettv.parental_control_service.services.exception.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.exception.TitleNotFoundException;
import com.bskyb.internettv.thirdparty.services.MovieService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceImplTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ParentalControlServiceImpl parentalControlService;

    @Test
    public void testShouldThrowExceptionWhenCustomerParentalControlLevelIsInvalid() throws Exception {

        thrown.expect(ParentalControlLevelNotFoundException.class);
        thrown.expectMessage(is("Parental control level: U1 is not valid"));

        parentalControlService.canWatchMovie("U1", "movie1");
    }

    @Test
    public void testShouldThrowExceptionWhenCustomerParentalControlLevelIsNull() throws Exception {

        thrown.expect(ParentalControlLevelNotFoundException.class);
        thrown.expectMessage(is("Parental control level: null is not valid"));

        parentalControlService.canWatchMovie(null, "movie1");
    }

    @Test
    public void testShouldThrowExceptionWhenMovieServiceThrowsTitleNotFoundException() throws Exception {

        when(movieService.getParentalControlLevel(anyString())).thenThrow(new TitleNotFoundException("Title not found"));

        thrown.expect(TitleNotFoundException.class);
        thrown.expectMessage(is("Title not found"));

        parentalControlService.canWatchMovie("U", null);
    }

    @Test
    public void testShouldThrowExceptionWhenMovieServiceThrowsInvalidInputException() throws Exception {

        when(movieService.getParentalControlLevel(anyString())).thenThrow(new TitleNotFoundException("Invalid input"));

        thrown.expect(TitleNotFoundException.class);
        thrown.expectMessage(is("Invalid input"));

        parentalControlService.canWatchMovie("U", null);
    }

    @Test
    public void testShouldThrowExceptionWhenMovieServiceThrowsInvalidInputException1() throws Exception {

        when(movieService.getParentalControlLevel(anyString())).thenThrow(new RuntimeException());

        thrown.expect(TechnicalFailureException.class);
        thrown.expectMessage(is("Technical fault in service"));

        parentalControlService.canWatchMovie("U", null);
    }

    @Test
    public void testShouldReturnFalseWhenCustomerParentalControlLevelIsNotAcceptableForGivenMovieTitle() throws Exception {

        String movieTitle = "movie5";
        when(movieService.getParentalControlLevel(movieTitle)).thenReturn("18");

        boolean canWatchMovie = parentalControlService.canWatchMovie("U", movieTitle);

        assertFalse(canWatchMovie);
    }

    @Test
    public void testShouldReturnTrueWhenCustomerParentalControlLevelHasSameRankAsGivenMovieTitle() throws Exception {

        String movieTitle = "movie5";
        when(movieService.getParentalControlLevel(movieTitle)).thenReturn("18");

        boolean canWatchMovie = parentalControlService.canWatchMovie("18", movieTitle);

        assertTrue(canWatchMovie);
    }

    @Test
    public void testShouldReturnTrueWhenCustomerParentalControlLevelHasGraterRankThanGivenMovieTitle() throws Exception {

        String movieTitle = "movie5";
        when(movieService.getParentalControlLevel(movieTitle)).thenReturn("15");

        boolean canWatchMovie = parentalControlService.canWatchMovie("18", movieTitle);

        assertTrue(canWatchMovie);
    }
}