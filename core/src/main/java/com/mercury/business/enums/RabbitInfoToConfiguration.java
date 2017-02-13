package com.mercury.business.enums;

public abstract class RabbitInfoToConfiguration {

	private final static String SERVER = "172.17.0.2";
	private final static String USERNAME = "guest";
	private final static String PASSWORD = "guest";
	private final static Integer PORT = 5672;

	public static String getServer() {
		return SERVER;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static Integer getPort() {
		return PORT;
	}
	
}