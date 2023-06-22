/**
 * @author - Alex Cox
 * CIS175 2023
 * 
 */

package apartments;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.persistence.*;

public class ApartmentServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    // This method is called when a GET request is sent to the servlet. 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // An EntityManagerFactory is created to manage persistence.
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            // Retrieves parameters from the GET request.
            String address = request.getParameter("address");
            String rent = request.getParameter("rent");
            String numberOfRooms = request.getParameter("numberOfRooms");
            
            // Checks if all parameters are valid before creating a new apartment.
            if (address != null && !address.trim().isEmpty() && rent != null && !rent.trim().isEmpty() && numberOfRooms != null && !numberOfRooms.trim().isEmpty()) {
                // Begins a transaction to the database to persist the new Apartment entity.
                em.getTransaction().begin();
                em.persist(new Apartment(address, Double.parseDouble(rent), Integer.parseInt(numberOfRooms)));
                em.getTransaction().commit();
            } else {
                // If any parameters are missing, an error message is set.
                request.setAttribute("error", "Please enter an address, rent, and number of rooms.");
            }

            // Retrieves a list of all Apartment entities from the database.
            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            // Sets the list of apartments to the request attribute so it can be used in the view (JSP).
            request.setAttribute("apartments", apartmentList);
            
            // Forwards the request and response to the JSP page to display the apartments.
            request.getRequestDispatcher("/apartment.jsp").forward(request, response);
        } finally {
            // If there's still an active transaction, it's rolled back to maintain data integrity.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // The EntityManager is closed after processing the request.
            em.close();
        }
    }

    // This method is called when a POST request is sent to the servlet.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieves parameters from the POST request.
        String address = request.getParameter("address");
        String rent = request.getParameter("rent");
        String numberOfRooms = request.getParameter("numberOfRooms");
        String operation = request.getParameter("operation");
        String id = request.getParameter("id");

        // An EntityManagerFactory is created to manage persistence.
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
        	// If operation is 'delete', it will try to delete an apartment from the database.
        	if (operation != null && operation.equals("delete") && id != null) {
        	    em.getTransaction().begin();
        	    Apartment apartment = em.find(Apartment.class, Integer.parseInt(id));
        	    if (apartment != null) {
        	        // Checks if the apartment is occupied before removing it.
        	        if (apartment.isSelected()) {
        	            request.setAttribute("error", "This apartment is occupied. You cannot delete it.");
        	            request.getRequestDispatcher("/apartment.jsp").forward(request, response);
        	            return;
        	        }
        	        em.remove(apartment);
        	    }
        	    em.getTransaction().commit();
        	}
            // If operation is 'update', it will try to update an existing apartment.
            else if (operation != null && operation.equals("update") && address != null && !address.trim().isEmpty() && rent != null && !rent.trim().isEmpty() && numberOfRooms != null && !numberOfRooms.trim().isEmpty() && id != null) {
                em.getTransaction().begin();
                Apartment apartment = em.find(Apartment.class, Integer.parseInt(id));
                if (apartment != null) {
                    apartment.setAddress(address);
                    apartment.setRent(Double.parseDouble(rent));
                    apartment.setNumberOfRooms(Integer.parseInt(numberOfRooms));
                }
                em.getTransaction().commit();
            } 
            // If the operation is 'update' but missing required parameters, it sets an error message.
            else if (operation != null && operation.equals("update")) {
                request.setAttribute("error", "Please enter an address, rent, and number of rooms for update.");
            } 
            // If there's no operation, it tries to create a new apartment with the provided parameters.
            else if (address != null && !address.trim().isEmpty() && rent != null && !rent.trim().isEmpty() && numberOfRooms != null && !numberOfRooms.trim().isEmpty()) {
                em.getTransaction().begin();
                em.persist(new Apartment(address, Double.parseDouble(rent), Integer.parseInt(numberOfRooms)));
                em.getTransaction().commit();
            } else {
                // If any parameters are missing, an error message is set.
                request.setAttribute("error", "Please enter an address, rent, and number of rooms.");
            }

            // Retrieves a list of all Apartment entities from the database.
            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            // Sets the list of apartments to the request attribute so it can be used in the view (JSP).
            request.setAttribute("apartments", apartmentList);
            
            // Forwards the request and response to the JSP page to display the apartments.
            request.getRequestDispatcher("/apartment.jsp").forward(request, response);
        } finally {
            // If there's still an active transaction, it's rolled back to maintain data integrity.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // The EntityManager is closed after processing the request.
            em.close();
        }
    }
}