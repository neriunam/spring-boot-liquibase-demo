package com.example.liquidbase.demo.util;

import java.util.Properties;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HsqlDBUtils {

	private static Server server;

	public static void startHsqldbServer() {
		log.info("Start HSQLDBServer");
		try {
			Properties properties = new Properties();
			properties.load(HsqlDBUtils.class.getResourceAsStream("/hsql-server.properties"));
			HsqlProperties hsqlProperties = new HsqlProperties(properties);

			log.info("Properties: {}", hsqlProperties.getProperties());
			if (server == null) {
				server = new Server();
				server.setProperties(hsqlProperties);
			}
			if (server.isNotRunning()) {
				log.info("HSQLDBServer NOT running -> START");
				server.start();

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void stopHsqldbServer() {
		log.info("Stop HSQLDBServer");
		if (server == null) {
			return;
		}
		if (!server.isNotRunning()) {
			log.info("HSQLDBServer running -> STOP");
			server.stop();
		}
	}

}
