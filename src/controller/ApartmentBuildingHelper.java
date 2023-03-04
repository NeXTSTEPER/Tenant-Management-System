package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ApartmentBuilding;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Mar 4, 2023
 */
public class ApartmentBuildingHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Tenant-Management-System");
	
	public void insertBuilding(ApartmentBuilding s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ApartmentBuilding> showAllShoppers() {
		EntityManager em = emfactory.createEntityManager();
		List<ApartmentBuilding> allBuildings = em.createQuery("SELECT b FROM ApartmentBuilding b").getResultList();
		return allBuildings;
	}
	
	public ApartmentBuilding findBuilding(String buildingToLookUp) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ApartmentBuilding> typedQuery = em.createQuery("select b from ApartmentBuilding b where b.name = :building", ApartmentBuilding.class);
		typedQuery.setParameter("building", buildingToLookUp);
		typedQuery.setMaxResults(1);
		ApartmentBuilding foundBuilding;
		try {
			foundBuilding = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundBuilding = new ApartmentBuilding(buildingToLookUp);
		}
		em.close();
		return foundBuilding;
	}
}
