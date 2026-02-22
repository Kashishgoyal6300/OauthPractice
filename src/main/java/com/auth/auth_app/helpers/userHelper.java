package com.auth.auth_app.helpers;

import java.util.UUID;

public class userHelper {
	 public static UUID parseUUID(String uuidString) {
	        return UUID.fromString(uuidString);
	    }

}