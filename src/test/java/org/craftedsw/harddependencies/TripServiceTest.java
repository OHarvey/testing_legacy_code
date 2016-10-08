package org.craftedsw.harddependencies;

import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripServiceTest {

	private User NOT_LOGGED_IN = null;
	private User DAVE = new User();
	private User FRED = new User();
	private User MARTIN = new User();
	private User loggedInUser;
	private Trip USA = new Trip();

	TripService tripService;

	@Before
	public void setUp() {
		tripService = new TestableTripService();
		loggedInUser = DAVE;

	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_UserNotLoggedInException() throws UserNotLoggedInException {
		loggedInUser = NOT_LOGGED_IN;

		tripService.getTripsByUser(NOT_LOGGED_IN);
	}

	@Test
	public void should_not_return_trips_when_users_arent_friends() throws Exception {
		FRED.addFriend(MARTIN);
		FRED.addTrip(USA);
		
		List<Trip> friendsTrips = tripService.getTripsByUser(FRED);
		
		assertEquals(0, friendsTrips.size());
	}
	
	@Test 
	public void should_return_trips_when_users_are_friend() throws Exception{
		FRED.addFriend(DAVE);
		FRED.addTrip(USA);
		
		List<Trip> friendsTrips = tripService.getTripsByUser(FRED);

		assertEquals(1, friendsTrips.size());
	}

	private class TestableTripService extends TripService {

		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}

		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}

	}

}
