package com.lessons.security;

import java.util.ArrayList;

public class UserDirectory {

	private ArrayList<UserIdentity> identities;
	
	public UserDirectory() {
		this.identities = new ArrayList<>();
		this.seed();
	}
	
	private void seed() {
		this.identities.add(new UserIdentity("test_user","password123"));
	}
	
	public boolean userInDirectory(UserIdentity identity) {
		boolean user_count = this.identities.stream().filter(i -> i.getUsername().equals(identity.getUsername()) && i.getPassword().equals(identity.getPassword())).count() > 0;
		System.out.println(user_count + " - " + identity.getUsername() + " " + identity.getPassword());
		return user_count;
	}
	
}
