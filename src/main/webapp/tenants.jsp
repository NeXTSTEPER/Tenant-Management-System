<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,tenants.Tenants,apartments.Apartment, apartments.DateConverter"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Tenant List</title>
      <style>
          body {
    font-family: 'Helvetica', serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    color: #333;
}

h1 {
    font-size: 2.5em;
    text-align: center;
    padding: 20px 0;
    border-bottom: 1px solid #ddd;
    margin-bottom: 20px;
}

form {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

input[type="text"] {
    margin: 5px 0;
    padding: 10px;
    width: 50%;
    border-radius: 5px;
    border: 1px solid #ccc;
    color: #333;
    background-color: #fff;
    font-size: 1.2em;
}

input[type="submit"] {
    margin: 5px 0;
    padding: 10px;
    width: 20%;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: #4A7BB3;
    color: #333;
    cursor: pointer;
    transition: background-color 0.5s;
}

input[type="submit"]:hover {
    background-color: #fff;
}

.movie {
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
    margin-bottom: 20px;
    font-size: 1.5em;
    text-align: center;
}

p#error {
    color: #ff4b3e;
}

div {
    padding: 0 20px;
}

ol, li {
    margin: 0;
    padding: 0;
    list-style-position: inside;
}

a.back-to-index {
    display: inline-block;
    margin-bottom: 20px;
    padding: 10px;
    color: #337ab7;
    text-decoration: none;
    border: 1px solid #4A7BB3;
    border-radius: 5px;
    transition: background-color 0.3s, color 0.3s;
}

a.back-to-index:hover {
    background-color: #4A7BB3;
    color: #fff;
}
      </style>
   </head>
   <body>
      <div class="container">
         <br>
         <a href="index.jsp" class="back-to-index">Back</a>
         <h1>Tenant List</h1>
         <% if (request.getAttribute("error") != null) { %>
         <p style="color:red;"><%= request.getAttribute("error") %></p>
         <% } %>
         <form method="POST" action="TenantsServlet" onsubmit="return validateForm()">
            <p> Tenant Name: </p>
            <input type="text" id="tenantName" name="tenantName" />
            <p>Tenant Phone Number #</p>
            <input type="text" id="tenantPhoneNumber" name="tenantPhoneNumber" />
            <p> Rooms Desired:</p>
            <input type="text" id="roomsDesired" name="roomsDesired" />
            <select id="apartmentId" name="apartmentId" onchange="validateRooms()">
               <% 
                  @SuppressWarnings("unchecked")
                  List<Apartment> apartments = (List<Apartment>)request.getAttribute("apartments");
                  int counter = 0;
                  for (Apartment apartment : apartments) {
                  %>
               <option value="<%=apartment.getId()%>" <%= counter++ == 0 ? "selected" : "" %>><%=apartment.getAddress()%> - $<%= String.format("%.2f", apartment.getRent()) %></option>
               <% 
                  }
                  %>
            </select>
            <input type="submit" value="Add" />
            <p id="error" style="color:red; display:none">Please enter a tenant name, phone number, and number of rooms desired.</p>
         </form>
         <hr>
         <ol>
            <%
               @SuppressWarnings("unchecked")
               List<Tenants> tenants = (List<Tenants>)request.getAttribute("tenants");
               for (Tenants tenant : tenants) {
               %>
            <div class="tenant">
               <li> 
                  Tenant name: <%= tenant.getName() %>, 
                  Phone Number: <%= tenant.getPhoneNumber() %>,
                  Move-in Date: <%= DateConverter.getMoveInDate() %>
                  <% if (tenant.getApartment() != null) { %>
                  Apartment
                  Address: <%= tenant.getApartment().getAddress() %>,
                  Rent: $<%= String.format("%.2f", tenant.getApartment().getRent()) %>
                  <% } else { %>
                  (No apartment assigned)
                  <% } %>
               </li>
               <form method="POST" action="TenantsServlet">
                  <input type="hidden" name="id" value="<%=tenant.getId()%>" />
                  <p> Tenant Name </p>
                  <input type="text" name="tenantName" value="<%=tenant.getName()%>" />
                  <p>Phone Number # </p>
                  <input type="text" name="tenantPhoneNumber" value="<%=tenant.getPhoneNumber()%>" />
                  <p>Rooms Desired: </p>
                  <input type="text" name="roomsDesired" value="<%=tenant.getNumberOfRoomsDesired()%>" />
                  <select id="apartmentId" name="apartmentId">
                     <% 
                        counter = 0;
                        for (Apartment apartment : apartments) {
                            String selected = "";
                            if (tenant.getApartment() != null && tenant.getApartment().getId() == apartment.getId()) {
                                selected = "selected";
                            } else if (counter++ == 0 && tenant.getApartment() == null) {
                                selected = "selected";
                            }
                        %>
                     <option value="<%=apartment.getId()%>" <%=selected%>><%=apartment.getAddress()%> - $<%= String.format("%.2f", apartment.getRent()) %></option>
                     <% 
                        }
                        %>
                  </select>
                  <input type="hidden" name="operation" value="update" />
                  <input type="submit" value="Update" />
               </form>
               <form method="POST" action="TenantsServlet">
                  <input type="hidden" name="id" value="<%=tenant.getId()%>" />
                  <input type="hidden" name="operation" value="delete" />
                  <input type="submit" value="Delete" />
               </form>
            </div>
            <% } %>
         </ol>
         <hr>
      </div>
      <script>
         function validateForm() {
            var tenantName = document.getElementById('tenantName').value;
            var tenantPhoneNumber = document.getElementById('tenantPhoneNumber').value;
            var roomsDesired = document.getElementById('roomsDesired').value;
            if (tenantName == "" || tenantPhoneNumber == "" || roomsDesired == "") {
                document.getElementById('error').style.display = 'block';
                return false;
            } else if (tenantPhoneNumber.length != 10 || isNaN(tenantPhoneNumber)) {
                alert('Please enter a valid 10-digit phone number.');
                return false;
            } else {
                return true;
            }
         }
         
            
            function validateRooms() {
                var apartmentId = document.getElementById('apartmentId').value;
                var roomsDesired = document.getElementById('roomsDesired').value; // Assuming you have an input field with id 'roomsDesired'
            
                // Fetch the number of rooms for the selected apartment
                // This would require an API endpoint in your servlet that returns the number of rooms for a given apartment ID
                fetch('/TenantsServlet?operation=getNumberOfRooms&apartmentId=' + apartmentId)
                .then(response => response.text())
                .then(numberOfRooms => {
                    if (parseInt(roomsDesired) > parseInt(numberOfRooms)) {
                        alert('The number of rooms desired by the tenant is greater than the number of rooms in the selected apartment. Please choose a different apartment.');
                    }
                });
            }
            
            
      </script>
   </body>
</html>