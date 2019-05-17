<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,fr.epsi.seanProject.beans.Blog,fr.epsi.seanProject.beans.Reponse"%>
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
<meta charset="ISO-8859-1">
<title>Blog</title>
</head>
<body>
<h1> Blog  <form method="post">
				<input class="btn btn-info" style="float:right;" type="submit" name="retour" value="retour" />
			</form></h1>
	<%
		String name = (String) request.getAttribute("postName");
		Blog blog = (Blog) request.getAttribute("Blog");
		if ((Blog) request.getAttribute("Blog") != null) {
			List<Reponse> reponses = blog.getListOfReponses();
	%>
	<h2>
		Voici le blog
		<%=name%>
		du
		<%=blog.getDateCreation()%>
		modifier
		<%=blog.getDateModification()%>.
	</h2>
	<p>
		Titre du blog :
		<%=blog.getTitre()%></p>
	<p>
		Pour contacter l'auteur :
		<%=blog.getCreateur().getEmail()%></p>
	<p><%=blog.getDescription()%></p>

	<%
		for (Reponse var : blog.getListOfReponses()) {
	%>
	<p>
		<%=var.getCommentaire()%>
	</p>
	<%
		}
	%>

	<form method="post">
	<div class="form-group">
		<textarea class="form-control" name="commentaire" id="commentaire">Commentaire</textarea>
		<button class="btn btn-success" onclick="submit">Ajouter</button>
	</div>
	</form>
	<p>
		Statut actuel :
		<%=blog.getStatut().getDescription()%></p>
	<form method="post">
	<div class="form-group">
			
		<input value="1" name="statut" type="radio"> Publié
		&nbsp;&nbsp; <input value="2" name="statut" type="radio">
		Privé &nbsp;&nbsp; <input value="3" name="statut" type="radio">
		Archivé &nbsp;&nbsp;
		<input value="4" name="statut" type="radio">
		Annulé <br>
		<button class="btn btn-success" onclick="submit" name="updateStatut">Confirmer</button>
	</div>
	</form>
	<form method="post">
		<button class="btn btn-danger" onclick="submit" name="delete" value="delete">Supprimer</button>
	</form>
	<form method="post">
		<h1>Modifier le blog :</h1>
		<div class="form-group">
			<label for="titre">Titre</label>
		<input class="form-control" value="Entrez un titre" name="Titre">
		</div>
		<div class="form-group">
			<label for="Description">Description</label>
		<textarea class="form-control" name="Description"> Description </textarea>
		</div>
		<button class="btn btn-primary" onclick="submit" name="update" value="update">Modifier</button>
	</form>

	<%
		} else {
	%>
	<p>Le Blog n'existe pas !</p>
	<%
		}
	%>
</body>
</html>