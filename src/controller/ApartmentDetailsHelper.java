package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ApartmentDetails;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Mar 4, 2023
 */
public class ApartmentDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Tenant-Management-System");
	
	public void insertNewApartmentDetails(ApartmentDetails ad) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ad);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ApartmentDetails> getApartments(){
		EntityManager em = emfactory.createEntityManager(); 
		List<ApartmentDetails> allApartments = em.createQuery("SELECT ad from ApartmentDetails ad").getResultList();
		return allApartments;
	}
	
	public void deleteList(ApartmentDetails toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ApartmentDetails> typedQuery = em.createQuery("select ad from ApartmentDetails ad where ad.id = :selectedId", ApartmentDetails.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedId", toDelete.getId());
		// we only want one result
		typedQuery.setMaxResults(1);
		// get the result and save it into a new list item
		ApartmentDetails result = typedQuery.getSingleResult();
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public ApartmentDetails searchForApartmentDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ApartmentDetails found = em.find(ApartmentDetails.class, tempId);
		em.close();
		return found;		
	}
	
	public void updateDetails(ApartmentDetails toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
