<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,apartments.Apartment"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Apartment Manager</title>
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

            p {
                text-align: center;
                font-size: 1.5em;
                padding: 15px 0;
                margin-bottom: 20px;
            }

            a {
                color: #337ab7;
                text-decoration: none;
                transition: color 0.5s;
            }

            a:hover {
                color: #23527c;
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
                width: 80%;
                border-radius: 5px;
                border: 1px solid #ccc;
                color: #333;
                background-color: #fff;
            }

          
            input[type="submit"]:hover {
                background-color: #fff;
            }

            .apartment {
                border-bottom: 1px solid #ddd;
                padding-bottom: 10px;
                margin-bottom: 20px;
                font-size: 1.5em;
                text-align: center;
            }

            p#error {
                color: #ff4b3e;
            }

            .container {
                padding: 0 20px;
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
                color: white;
            }
            
             input[type="text"] {
                margin: 5px 0;
                padding: 10px;
                width: 50%; /* adjusted width */
                font-size: 1.2em; /* adjusted font size */
                border-radius: 5px;
                border: 1px solid #ccc;
                color: #333;
                background-color: #fff;
            }

            input[type="submit"] {
                margin: 5px 0;
                padding: 10px;
                width: 20%; /* adjusted width */
                font-size: 1.2em; /* adjusted font size */
                border-radius: 5px;
                border: 1px solid #ccc;
                background-color: #4A7BB3;
                color: #333;
                cursor: pointer;
                transition: background-color 0.5s;
            }
            

</style>


    </head>

  <body>
        <div class="container">
            <br>
            <a href="index.jsp" class="back-to-index"> Back</a>
        
            <h1>Apartment Manager</h1>
            <form method="POST" action="ApartmentServlet" onsubmit="return validateForm()">
                Address: <input type="text" id="address" name="address" />
                Rent: <input type="text" id="rent" name="rent" />
                Number of Rooms: <input type="text" id="numberOfRooms" name="numberOfRooms" />
                <input type="submit" value="Add" />
                <p id="error" style="color:red; display:none">Please enter an address, rent, and number of rooms.</p>
            </form>
            <hr>
           <ol> 
                <%
                 @SuppressWarnings("unchecked")
                 List<Apartment> apartments = (List<Apartment>)request.getAttribute("apartments");
                 for (Apartment apartment : apartments) {
                %>
                    <div class="apartment">
                        <form method="POST" action="ApartmentServlet">
                            <input type="hidden" name="id" value="<%=apartment.getId()%>" />
                            <p> Address</p><input type="text" name="address" value="<%=apartment.getAddress()%>" />
                            <input type="hidden" name="isSelected" value="<%=apartment.isSelected()%>" />
                            <p> Rent:</p> <input type="text" name="rent" value=<%=apartment.getFormattedRent()%>  />
                            <p> Number of Rooms:</p> <input type="text" name="numberOfRooms" value="<%=apartment.getNumberOfRooms()%>" />
                            <input type="hidden" name="operation" value="update" />
                            <input type="submit" value="Update" />
                        </form>
                        <form method="POST" action="ApartmentServlet">
                            <input type="hidden" name="id" value="<%=apartment.getId()%>" />
                            <input type="hidden" name="operation" value="delete" />
                            <% if (!apartment.isSelected()) { %>
                                <input type="submit" value="Delete" />
                            <% } else { %>
                                <input type="button" value="Cannot Delete: Apartment is Occupied" />
                            <% } %>
                        </form>
                    </div>
                <% } %>
            </ol>
            <hr>
        </div>
        <script>
        function validateForm() {
            var address = document.getElementById('address').value;
            var rent = document.getElementById('rent').value;
            var numberOfRooms = document.getElementById('numberOfRooms').value;
            
            if (address == "" || rent == "" || numberOfRooms == "") {
                document.getElementById('error').style.display = 'block';
                return false;
            } else if (isNaN(rent)) {
                alert('Please enter a numeric value for rent.');
                return false;
            } else if (!Number.isInteger(parseFloat(numberOfRooms)) || numberOfRooms < 1) {
                alert('Please enter a valid number of rooms (integer greater than 0).');
                return false;
            } else {
                return true;
            }
        }
        
        
        </script>
    </body>
</html>