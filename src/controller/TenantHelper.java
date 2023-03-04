package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Tenant;


/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Spring 2023
 * Mar 4, 2023
 */

public class TenantHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Tenant-Management-System");
	public void inserItem(Tenant te) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(te);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Tenant> showAllTenants(){
		EntityManager em = emfactory.createEntityManager();
		List<Tenant> allTenants = em.createQuery("SELECT t FROM Tenant t").getResultList();
		return allTenants;
	}
	
	public void deleteItem(Tenant toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Tenant> typedQuery = em.createQuery
			("select te from Tenant te where te.familyName = :selectedFamilyName "
					+ "and te.numberOfOccupants = :selectedNumberOfOccupants "
					+ "and te.hasPets = :" , Tenant.class);
		
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedFamilyName", toDelete.getFamilyName());
		typedQuery.setParameter("selectedNumberOfOccupants", toDelete.getNumberOfOccupants());
		typedQuery.setParameter("hasPets", toDelete.isHasPets());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		Tenant result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	//findTenant method to look tenant up by last name
	public Tenant findTenant(String lastNameToLookup) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Tenant> typedQuery = em.createQuery("select te from Tenant te where te.familyName = :selectedName", Tenant.class);
		typedQuery.setParameter("selectedName", lastNameToLookup);
		typedQuery.setMaxResults(1);
		Tenant foundTenant;
		try {
			foundTenant = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundTenant = new Tenant(lastNameToLookup);
		}
		em.close();
		return foundTenant;
	}
	
	
}
