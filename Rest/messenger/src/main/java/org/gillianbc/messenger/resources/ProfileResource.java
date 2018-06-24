package org.gillianbc.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gillianbc.messenger.model.Message;
import org.gillianbc.messenger.model.Profile;
import org.gillianbc.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profName) {
		
		return profileService.getProfile(profName);
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		System.out.println("In addProfile resource method");
		profileService.showProfile(profile);
		profileService.addProfile(profile);
		return profile;
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateMessage(@PathParam("profileName") String someProfileName, Profile profile) {
		if (!profile.getProfileName().equals(someProfileName)) {
			System.out.println("You cannot change the profile name - it is the key");
			return null;  //I want to return a status forbidden but don't know how yet
		}
		profile.setProfileName(someProfileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String someProfileName) {
		profileService.deleteProfile(someProfileName);
	}
}
