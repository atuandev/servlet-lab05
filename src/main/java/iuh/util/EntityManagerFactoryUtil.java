package iuh.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
	private EntityManagerFactory entityManageFactory;
	private EntityManager entityManager;

	public EntityManagerFactoryUtil() {
		this.entityManageFactory = Persistence.createEntityManagerFactory("21129321_ORM_HIBERNATE_MARIADB");
		this.entityManager = entityManageFactory.createEntityManager();
	}

	public EntityManager getEnManager() {
		return this.entityManager;
	}

	public void close() {
		entityManager.close();
		entityManageFactory.close();
	}

}
