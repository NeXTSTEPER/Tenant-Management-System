package tenants;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.persistence.*;
import apartments.Apartment;

public class TenantsServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            // Fetch the list of apartments and set it as a request attribute
            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            request.setAttribute("apartments", apartmentList);

            // Fetch the list of tenants and set it as a request attribute
            List<Tenants> tenantsList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
            request.setAttribute("tenants", tenantsList);

            request.getRequestDispatcher("/tenants.jsp").forward(request, response);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenantName = request.getParameter("tenantName");
        String tenantPhoneNumber = request.getParameter("tenantPhoneNumber");
        String roomsDesired = request.getParameter("roomsDesired");
        String apartmentId = request.getParameter("apartmentId");
        String operation = request.getParameter("operation");
        String id = request.getParameter("id");

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            if (operation != null && operation.equals("delete") && id != null) {
                em.getTransaction().begin();
                Tenants tenant = em.find(Tenants.class, Integer.parseInt(id));
                if (tenant != null) {
                    em.remove(tenant);
                }
                em.getTransaction().commit();
            } 
            else if (operation != null && operation.equals("update") && tenantName != null && !tenantName.trim().isEmpty() && tenantPhoneNumber != null && !tenantPhoneNumber.trim().isEmpty() && roomsDesired != null && !roomsDesired.trim().isEmpty() && id != null) {
                em.getTransaction().begin();
                Tenants tenant = em.find(Tenants.class, Integer.parseInt(id));
                if (tenant != null) {
                    tenant.setName(tenantName);
                    tenant.setPhoneNumber(tenantPhoneNumber);
                    tenant.setNumberOfRoomsDesired(Integer.parseInt(roomsDesired));
                    if (apartmentId != null && !apartmentId.equals("-1")) {
                        Apartment apartment = em.find(Apartment.class, Integer.parseInt(apartmentId));
                        tenant.setApartment(apartment);
                    }
                }
                em.getTransaction().commit();
            } 
            else if (operation != null && operation.equals("update")) {
                request.setAttribute("error", "Please enter a tenant name, phone number, and number of rooms desired for update.");
            } 
            else if (tenantName != null && !tenantName.trim().isEmpty() && tenantPhoneNumber != null && !tenantPhoneNumber.trim().isEmpty() && roomsDesired != null && !roomsDesired.trim().isEmpty()) {
                em.getTransaction().begin();
                Tenants tenant = new Tenants(tenantName, tenantPhoneNumber, Integer.parseInt(roomsDesired));
                if (apartmentId != null && !apartmentId.equals("-1")) {
                    Apartment apartment = em.find(Apartment.class, Integer.parseInt(apartmentId));
                    tenant.setApartment(apartment);
                }
                em.persist(tenant);
                em.getTransaction().commit();
            } else {
                request.setAttribute("error", "Please enter a tenant name, phone number, and number of rooms desired.");
            }

            // Fetch the list of apartments and set it as a request attribute
            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            request.setAttribute("apartments", apartmentList);

            // Fetch the list of tenants and set it as a request attribute
            List<Tenants> tenantsList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
            request.setAttribute("tenants", tenantsList);

            request.getRequestDispatcher("/tenants.jsp").forward(request, response);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}