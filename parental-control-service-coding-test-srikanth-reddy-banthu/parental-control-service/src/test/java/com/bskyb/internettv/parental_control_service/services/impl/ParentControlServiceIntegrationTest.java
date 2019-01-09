package com.bskyb.internettv.parental_control_service.services.impl;

import com.bskyb.internettv.parental_control_service.services.ParentalControlService;
import com.bskyb.internettv.thirdparty.services.MovieService;
import com.bskyb.internettv.thirdparty.services.impl.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ParentControlServiceIntegrationTest {


    private ParentalControlService parentalControlService;

    @Before
    public void setUp() {
        final MovieService movieService = new MovieServiceImpl();
        parentalControlService = new ParentalControlServiceImpl(movieService);
    }

    @Test
    public void verifyWhichMoviesCanBeWatchedForCustomerParentalControlLevelU() throws Exception {

        assertTrue(parentalControlService.canWatchMovie("U", "movie1"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie2"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie3"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie4"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie5"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie6"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie7"));
        assertFalse(parentalControlService.canWatchMovie("U", "movie8"));
    }

    @Test
    public void verifyWhichMoviesCanBeWatchedForCustomerParentalControlLevelPG() throws Exception {

        assertTrue(parentalControlService.canWatchMovie("PG", "movie1"));
        assertTrue(parentalControlService.canWatchMovie("PG", "movie2"));
        assertFalse(parentalControlService.canWatchMovie("PG", "movie3"));
        assertFalse(parentalControlService.canWatchMovie("PG", "movie4"));
        assertFalse(parentalControlService.canWatchMovie("PG", "movie5"));
        assertTrue(parentalControlService.canWatchMovie("PG", "movie6"));
        assertFalse(parentalControlService.canWatchMovie("PG", "movie7"));
        assertFalse(parentalControlService.canWatchMovie("PG", "movie8"));
    }

    @Test
    public void verifyWhichMoviesCanBeWatchedForCustomerParentalControlLevel12() throws Exception {

        assertTrue(parentalControlService.canWatchMovie("12", "movie1"));
        assertTrue(parentalControlService.canWatchMovie("12", "movie2"));
        assertTrue(parentalControlService.canWatchMovie("12", "movie3"));
        assertFalse(parentalControlService.canWatchMovie("12", "movie4"));
        assertFalse(parentalControlService.canWatchMovie("12", "movie5"));
        assertTrue(parentalControlService.canWatchMovie("12", "movie6"));
        assertTrue(parentalControlService.canWatchMovie("12", "movie7"));
        assertFalse(parentalControlService.canWatchMovie("12", "movie8"));
    }

    @Test
    public void verifyWhichMoviesCanBeWatchedForCustomerParentalControlLevel15() throws Exception {

        assertTrue(parentalControlService.canWatchMovie("15", "movie1"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie2"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie3"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie4"));
        assertFalse(parentalControlService.canWatchMovie("15", "movie5"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie6"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie7"));
        assertTrue(parentalControlService.canWatchMovie("15", "movie8"));
    }

    @Test
    public void verifyWhichMoviesCanBeWatchedForCustomerParentalControlLevel18() throws Exception {

        assertTrue(parentalControlService.canWatchMovie("18", "movie1"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie2"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie3"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie4"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie5"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie6"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie7"));
        assertTrue(parentalControlService.canWatchMovie("18", "movie8"));
    }

}
