
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jsp file</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
    img{
    height:50px;
    width:50px;
    border-radius:10px;
    }
        ul {
            list-style-type: none;
            /width:100%;/
            margin: 0;
            padding: 10px;
            overflow: hidden;
            background-color: Gray;
        }

        li {
            float: right;
        }
        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
        .uname{
            margin-top: 14px;
            color: white;
            margin-right: 10px;
        }
    </style>
    </head>
    <body style="margin: 0;">
        <ul>
            <% if (session.getAttribute("uname") != null) {%>

            <li class="uname"><i class="fa fa-user-circle" aria-hidden="true" style="margin-right: 5px;"></i><%=session.getAttribute("uname")%></li>
            <li><a href="register?logout=yes">Logout</a></li>
            <li><a href="EditForm.jsp">Update</a></li>
            <li><a href="Delete.jsp">Delete</a></li>
            <li><a href="Search.jsp">Search</a></li>
                <% if (session.getAttribute("id")!=null) {%>
                <%}%>
                <%} else {%>
            <li><a href="Registration.jsp">Register</a></li>
            <li><a href="Login.jsp">Login</a></li>
                <%}%>
            <li><a class="active" href="Index.jsp">Home</a></li>
            <li style="float:left"><img src="Images/ini8logo.jpeg"></li>
        </ul>

</body>
</html>