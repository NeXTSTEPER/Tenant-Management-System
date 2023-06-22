<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
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
        </style>
    </head>
    <body>
        <h1>Tenant Manager</h1> 
        <p><a href="ApartmentServlet">Apartments</a></p>
        <p><a href="TenantsServlet">Tenants</a></p>        
            
      
    </body>
</html>
