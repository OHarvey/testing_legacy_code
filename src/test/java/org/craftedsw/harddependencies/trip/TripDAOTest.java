package org.craftedsw.harddependencies.trip;

import static org.junit.Assert.*;

import org.craftedsw.harddependencies.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class TripDAOTest {

	@Test (expected = DependendClassCallDuringUnitTestException.class)
	public void should_throw_exception_when_getting_user_trips() {
		new TripDAO().tripsBy(new User());
	}

}
