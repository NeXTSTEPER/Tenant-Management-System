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
                background-color: #000;
                color: #fff;
            }
            h1 {
                font-size: 4.5em;
                text-align: center;
                padding: 20px 0;
                border-bottom: 1px solid #fff;
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
                color: #000;
                background-color: #fff;
            }
            input[type="submit"] {
                margin: 5px 0;
                padding: 10px;
                width: 30%;
                border-radius: 5px;
                border: 1px solid #ccc;
                background-color: #ff4b3e;
                color: #000;
                cursor: pointer;
                transition: background-color 0.5s;
            }
            input[type="submit"]:hover {
                background-color: #fff;
            }
            .book {
                border-bottom: 1px solid #fff;
                padding-bottom: 10px;
                margin-bottom: 20px;
                font-size: 2.8em;
                text-align: center;
            }
            p#error {
                color: #ff4b3e;
            }
            div {
                padding: 0 20px;
            }
            
             input[type="text"] {
        width: 19%;  /* adjust this to your needs */
        padding: 10px;
        margin-bottom: 10px;
    }

    input[type="submit"] {
        width: 20%;  /* adjust this to your needs */
        padding: 10px;
        background-color: #ff4b3e;
        border: none;
        color: white;
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
    color: white;
    text-decoration: none;
    border: 1px solid #EC5A49;
    border-radius: 5px;
    transition: background-color 0.3s, color 0.3s;
}

a.back-to-index:hover {
    background-color: #EC5A49;
    color: #000000;
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
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>