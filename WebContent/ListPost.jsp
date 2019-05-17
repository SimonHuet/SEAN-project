<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,fr.epsi.seanProject.beans.Blog, fr.epsi.seanProject.beans.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel='stylesheet'
	href='https://use.fontawesome.com/releases/v5.7.0/css/all.css'
	integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ'
	crossorigin='anonymous'>
<meta charset="ISO-8859-1">
<style>
body {
	margin: 10%;
	margin-top: 5%;
	margin-bottom: 5%;
	color: #FFFAFA;
	font-weight: bold;
	background-color: #2C2C2C;
}
</style>
<title>Liste des blogs</title>
</head>
<body>
	<h1>Liste des blogs</h1>
	<%
		List<Blog> list = (List<Blog>) request.getAttribute("list");
		for (Blog var : list) {
	%>
	<%
		if (var.getCreateur().getEmail()
					.equals(((Utilisateur) request.getSession().getAttribute("utilisateur")).getEmail())) {
	%>
	<div>
	<i class='fas fa-bookmark' style='color: rgb(255, 221, 28)'></i>
	<%
		} else {
	%>
	<i class='far fa-bookmark' style='color: rgb(255, 221, 28)'></i>
	<%
		}
	%>
	
	<a href="/SEAN/BlogServlet?post=<%=var.getId()%>"><%=var.getTitre()%> </a>
		<%=var.getCreateur().getNom()%> <%=var.getDateCreation()%>
	</div>
	<br>
	<%
		}
	%>

	<form action="/SEAN/CreatePostServlet">
		<input  class="btn btn-info" type="submit" value="Create" />
	</form>
	<%
		Utilisateur uti = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (uti.getAdmin()) {
	%>
	<form action="/SEAN/ListUserServlet">
		<input class="btn btn-info" type="submit" value="Liste des utilisateurs" />
	</form>
	<%
		}
	%>
</body>
</html>