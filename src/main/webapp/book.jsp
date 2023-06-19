<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,books.Books"%>

<!DOCTYPE html>
<html>
    <head>
        <title>JPA Reading List </title>
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
        
            <h1>JPA Reading List</h1>
            <form method="POST" action="BookServlet" onsubmit="return validateForm()">
                Book Title: <input type="text" id="bookTitle" name="bookTitle" />
                Book Author: <input type="text" id="bookAuthor" name="bookAuthor" />
                <input type="submit" value="Add" />
                <p id="error" style="color:red; display:none">Please enter a book title and author.</p>
            </form>
            <hr>
            <ol> 
                <%
                 @SuppressWarnings("unchecked")
                                 List<Books> books = (List<Books>)request.getAttribute("books");
                                 for (Books book : books) {
                 %>
                    <div class="book">
                        <li> <%= book %> </li>

                        <form method="POST" action="BookServlet">
                            <input type="hidden" name="id" value="<%=book.getId()%>" />
                            <input type="text" name="bookTitle" value="<%=book.getBookTitle()%>" />
                            <input type="text" name="bookAuthor" value="<%=book.getBookAuthor()%>" />
                            <input type="hidden" name="operation" value="update" />
                            <input type="submit" value="Update" />
                        </form>

                        <form method="POST" action="BookServlet">
                            <input type="hidden" name="id" value="<%=book.getId()%>" />
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
                var bookTitle = document.getElementById('bookTitle').value;
                var bookAuthor = document.getElementById('bookAuthor').value;
                if (bookTitle == "" || bookAuthor == "") {
                    document.getElementById('error').style.display = 'block';
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>