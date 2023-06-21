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
        String operation = request.getParameter("operation");
        String apartmentId = request.getParameter("apartmentId");
       

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            if (operation != null && operation.equals("getNumberOfRooms") && apartmentId != null) {
                Apartment apartment = em.find(Apartment.class, Integer.parseInt(apartmentId));
                if (apartment != null) {
                    response.getWriter().write(String.valueOf(apartment.getNumberOfRooms()));
                    return;
                }
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
                    Apartment apartment = tenant.getApartment();
                    if (apartment != null) {
                        apartment.setSelected(false);
                        em.merge(apartment);
                    }
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
                        if (apartment.getNumberOfRooms() >= tenant.getNumberOfRoomsDesired()) {
                            // Check if the apartment is already selected by another tenant
                            if (apartment.isSelected() && (tenant.getApartment() == null || tenant.getApartment().getId() != apartment.getId())) {
                                request.setAttribute("error", "This apartment is already selected by another tenant. Please choose a different apartment.");
                                return;
                            } else {
                                // If the tenant had a different apartment before, mark it as not selected
                                if (tenant.getApartment() != null && tenant.getApartment().getId() != apartment.getId()) {
                                    Apartment oldApartment = tenant.getApartment();
                                    oldApartment.setSelected(false);
                                    em.merge(oldApartment);
                                }
                                // Assign the new apartment to the tenant and mark it as selected
                                apartment.setSelected(true);
                                tenant.setApartment(apartment);
                            }
                        } else {
                            request.setAttribute("error", "The number of rooms desired by the tenant is greater than the number of rooms in the selected apartment. Please choose a different apartment.");
                            return;
                        }
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
                    if (apartment.getNumberOfRooms() >= tenant.getNumberOfRoomsDesired()) {
                        if (apartment.isSelected()) {
                            request.setAttribute("error", "This apartment is already selected by another user. Please choose a different apartment.");
                            request.getRequestDispatcher("/tenants.jsp").forward(request, response);
                            return;
                        } else {
                            apartment.setSelected(true);
                            
                            tenant.setApartment(apartment);
                        }
                    } else {
                        request.setAttribute("error", "The number of rooms desired by the tenant is greater than the number of rooms in the selected apartment. Please choose a different apartment.");

                        // Fetch the list of apartments and set it as a request attribute
                        List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
                        request.setAttribute("apartments", apartmentList);

                        // Fetch the list of tenants and set it as a request attribute
                        List<Tenants> tenantsList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
                        request.setAttribute("tenants", tenantsList);

                        // Forward the request and response to the JSP page
                        request.getRequestDispatcher("/tenants.jsp").forward(request, response);
                        return;
                    }
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

            // Forward the request and response to the JSP page
            request.getRequestDispatcher("/tenants.jsp").forward(request, response);

        }finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            // Fetch the list of apartments and set it as a request attribute
            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            request.setAttribute("apartments", apartmentList);

            // Fetch the list of tenants and set it as a request attribute
            List<Tenants> tenantsList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
            request.setAttribute("tenants", tenantsList);

            // Forward the request and response to the JSP page
            request.getRequestDispatcher("/tenants.jsp").forward(request, response);

            em.close();
        }
    }
}