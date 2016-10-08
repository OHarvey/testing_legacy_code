package org.craftedsw.harddependencies;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripDAO;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedInUser = validateLoggedInUser();
		return user.isFriendsWith(loggedInUser) ? tripsBy(user) : new ArrayList<Trip>();
	}

	private User validateLoggedInUser() throws UserNotLoggedInException {
		User loggedUser = getLoggedInUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();

		}
		return loggedUser;
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
