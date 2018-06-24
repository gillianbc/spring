package org.gillianbc.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gillianbc.messenger.database.DatabaseClass;
import org.gillianbc.messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size());
		profile.setCreated(new Date());
		showProfile("Adding", profile);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getId() < 0) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
	}

	private void showProfiles() {
		System.out.println("==== Profiles ====");
		List<Profile> list = new ArrayList<Profile>(profiles.values());
		for (Profile profile : list) {
			showProfile(profile);
		}

	}

	public void showProfile(Profile profile) {
		System.out.println("id: " + profile.getId() + " profilename " + profile.getProfileName() + " person "
				+ profile.getFirstName() + " " + profile.getLastName());
	}
	public void showProfile(String note, Profile profile) {
		System.out.println("==== " + note + "===");
		showProfile(profile);
	}
}
