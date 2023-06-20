package tenants;

// Importing necessary libraries
import java.io.IOException; // Handling I/O exceptions
import java.util.List; // Using List for handling collections of objects
import javax.servlet.ServletException; // Handling servlet exceptions
import javax.servlet.http.*; // Using HttpServlet, HttpServletRequest, HttpServletResponse

import javax.persistence.*; // Importing classes related to database operations

public class TenantsServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            String tenantName = request.getParameter("tenantName");
            String tenantPhoneNumber = request.getParameter("tenantPhoneNumber");
            String roomsDesired = request.getParameter("roomsDesired");

            if (tenantName != null && !tenantName.trim().isEmpty() && tenantPhoneNumber != null && !tenantPhoneNumber.trim().isEmpty() && roomsDesired != null && !roomsDesired.trim().isEmpty()) {
                em.getTransaction().begin();
                em.persist(new Tenants(tenantName, tenantPhoneNumber, Integer.parseInt(roomsDesired)));
                em.getTransaction().commit();
            } else {
                request.setAttribute("error", "Please enter a tenant name, phone number, and number of rooms desired.");
            }

            List<Tenants> tenantList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
            request.setAttribute("tenants", tenantList);

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
                }
                em.getTransaction().commit();
            }
            else if (operation != null && operation.equals("update")) {
                request.setAttribute("error", "Please enter a tenant name, phone number, and number of rooms desired for update.");
            }
            else if (tenantName != null && !tenantName.trim().isEmpty() && tenantPhoneNumber != null && !tenantPhoneNumber.trim().isEmpty() && roomsDesired != null && !roomsDesired.trim().isEmpty()) {
                em.getTransaction().begin();
                em.persist(new Tenants(tenantName, tenantPhoneNumber, Integer.parseInt(roomsDesired)));
                em.getTransaction().commit();
            } else {
                request.setAttribute("error", "Please enter a tenant name, phone number, and number of rooms desired.");
            }

            List<Tenants> tenantList = em.createQuery("SELECT t FROM Tenants t", Tenants.class).getResultList();
            request.setAttribute("tenants", tenantList);

            request.getRequestDispatcher("/tenants.jsp").forward(request, response);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
