<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
   import="java.util.List,fr.epsi.seanProject.beans.Blog"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des blogs </title>
</head>
<body>
  <% 
  List<Blog> list = (List<Blog>)request.getAttribute("list"); 
  for(Blog var : list ) { %>
  		<a  href="/SEAN/BlogServlet?post=<%= var.getId() %>"><%= var.getTitre() %> <%= var.getDescription() %> </a><br>
  <% } %>

	<form action="/SEAN/CreatePostServlet">
		<input type="submit" value="Create" />
	</form>
</body>
</html>