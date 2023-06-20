package apartments;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.persistence.*;

public class ApartmentServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            String address = request.getParameter("address");
            String rent = request.getParameter("rent");
            String numberOfRooms = request.getParameter("numberOfRooms");
            
            if (address != null && !address.trim().isEmpty() && rent != null && !rent.trim().isEmpty() && numberOfRooms != null && !numberOfRooms.trim().isEmpty()) {
                em.getTransaction().begin();
                em.persist(new Apartment(address, Double.parseDouble(rent), Integer.parseInt(numberOfRooms)));
                em.getTransaction().commit();
            } else {
                request.setAttribute("error", "Please enter an address, rent, and number of rooms.");
            }

            List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            request.setAttribute("apartments", apartmentList);
            
            request.getRequestDispatcher("/apartment.jsp").forward(request, response);
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
        String address = request.getParameter("address");
        String rent = request.getParameter("rent");
        String numberOfRooms = request.getParameter("numberOfRooms");
        String operation = request.getParameter("operation");
        String id = request.getParameter("id");

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            if (operation != null && operation.equals("delete") && id != null) {
                em.getTransaction().begin();
                Apartment apartment = em.find(Apartment.class, Integer.parseInt(id));
                if (apartment != null) {
                    em.remove(apartment);
                }
                em.getTransaction().commit();
            } 
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
            else if (operation != null && operation.equals("update")) {
                request.setAttribute("error", "Please enter an address, rent, and number of rooms for update.");
            } 
            else if (address != null && !address.trim().isEmpty() && rent != null && !rent.trim().isEmpty() && numberOfRooms != null && !numberOfRooms.trim().isEmpty()) {
                em.getTransaction().begin();
                em.persist(new Apartment(address, Double.parseDouble(rent), Integer.parseInt(numberOfRooms)));
                em.getTransaction().commit();
            } else {
                request.setAttribute("error", "Please enter an address, rent, and number of rooms.");
            }

           List<Apartment> apartmentList = em.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
            request.setAttribute("apartments", apartmentList);
            
            request.getRequestDispatcher("/apartment.jsp").forward(request, response);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
