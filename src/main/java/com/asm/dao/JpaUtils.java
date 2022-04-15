package com.asm.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtils {

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("asm_java_4").createEntityManager();
	}

}
