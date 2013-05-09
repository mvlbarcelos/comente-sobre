package com.mvlbarcelos.dao;


import org.jstryker.database.JPAHelper;

public class JStrykerHelper {
	
	private static boolean load;
	
	public static void init() {
		if (!load) {
			load = true;
			JPAHelper.entityManagerFactory("test");
		}
	}
}
