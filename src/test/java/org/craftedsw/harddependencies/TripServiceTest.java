package org.craftedsw.harddependencies;

import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class TripServiceTest {
	
	private User loggedInUser;
	private User NOT_LOGGED_IN = null;
	private User DAVE = new User();
	private User FRED = new User();
	private Trip USA = new Trip();
	
	private TripService tripService;

	@Before
	public void setUp() {
		tripService = new TestableTripService();
		loggedInUser = DAVE;
	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_UserNotLoggedInException() throws UserNotLoggedInException {
		loggedInUser = NOT_LOGGED_IN;

		tripService.getTripsBy(NOT_LOGGED_IN, loggedInUser);
	}

	@Test
	public void should_not_return_trips_when_users_arent_friends() throws Exception {
		User friend = UserBuilder.aUser().isFriendsWith(FRED).withTripsTo(USA).build();

		List<Trip> friendsTrips = tripService.getTripsBy(friend, loggedInUser);
		
		assertEquals(0, friendsTrips.size());
	}
	
	@Test 
	public void should_return_trips_when_users_are_friend() throws Exception{
		
		User friend = UserBuilder.aUser().isFriendsWith(FRED, DAVE).withTripsTo(USA).build();
		
		List<Trip> friendsTrips = tripService.getTripsBy(friend, loggedInUser);

		assertEquals(1, friendsTrips.size());
	}
	
	private class TestableTripService extends TripService {

		@Override
		protected List<Trip> tripsBy(User user) {
			return user.trips();
		}

	}

}
