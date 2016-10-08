package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class UserBuilder{
	
	private User[] friends = new User[]{};
	private Trip[] trips = new Trip[]{};
	
	public static  UserBuilder aUser(){
		return new UserBuilder();
	}
	
	public UserBuilder isFriendsWith(User...friends){
		this.friends = friends;
		return this;
	}
	
	public UserBuilder withTripsTo(Trip...trips){
		this.trips = trips;
		return this;
	}
	
	public User build(){
		
		User user = new User();
		addTripsTo(user);
		addFriendsTo(user);
		return user;
	}
	
	private void addTripsTo(User user){
		for(Trip trip : trips){
			user.addTrip(trip);
		}
	}
	
	private void addFriendsTo(User user){
		for(User friend : friends){
			user.addFriend(friend);
		}
	}
	
}