<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,fr.epsi.seanProject.beans.Utilisateur"%>
   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<meta charset="UTF-8">
<style>
    body{
        margin : 10%;
        margin-top: 5%;
        margin-bottom: 5%;
        color:#FFFAFA;
        font-weight: bold;  
        background-color: #2C2C2C;
  }
</style>
<title>Liste des utilisateurs</title>
</head>
<body>
<h1> Liste des utilisateurs</h1>
<% List<Utilisateur> list = (List<Utilisateur>) (request.getAttribute("list"));
		for (Utilisateur var : list) {
	%>
	<a href="/SEAN/UpdateUserServlet?email=<%=var.getEmail()%>"><%=var.getNom()%>
		<%=var.getEmail()%> </a>
	<br>
	<%
		}
	%>
	<form method="post">
		<input class="btn btn-info" type="submit" name="retour" value="retour"/>
	</form>
	
</body>
</html>