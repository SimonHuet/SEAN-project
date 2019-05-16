<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,fr.epsi.seanProject.beans.Blog,fr.epsi.seanProject.beans.Reponse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des blogs </title>
</head>
<body>

<% String name = (String)request.getAttribute("postName");
   Blog blog = (Blog)request.getAttribute("Blog");
   if((Blog)request.getAttribute("Blog") != null){
	   List<Reponse> reponses= blog.getListOfReponses();
   %>
   <p>Voici le blog <%= name%> du <%= blog.getDateCreation() %> modifier <%= blog.getDateModification() %>.</p>
   <p>Titre du blog : <%= blog.getTitre() %></p>
   <p>Pour contacter l'auteur : <%= blog.getCreateur().getEmail()  %></p>
   <p><%= blog.getDescription() %></p>
   
   <% 
	  for(Reponse var : blog.getListOfReponses() ) { %>
	  	<p>
	  		<%= var.getCommentaire() %>
	  	</p>
   <% } %> 
   
   <form  method="post">
	   <textarea name="commentaire" id="commentaire">Commentaire</textarea>
	   <button onclick="submit" >ADD COMMENTS</button>
   </form>
   <p>Statut actuel : <%= blog.getStatut().getDescription() %></p>
   <form method="post">
		<input value="1" name="statut" type="radio"> Publie &nbsp;&nbsp;
		<input value="2" name="statut" type="radio"> Privée &nbsp;&nbsp;
		<input value="3" name="statut" type="radio"> Archivée <br>
   	<button onclick="submit" name="updateStatut">Confirmer</button>
   </form>
   <form method="post">
   	<button onclick="submit" name="delete" value="delete">DELETE</button>
   </form>
   <form method="post">
   		<h1>Modifier le blog :</h1>
   		<h2>Title</h2>
		<input value="Entrez un titre" name="Titre">
		<h2>Contenu</h2>
		<textarea name="Description"> Description </textarea>
   	<button onclick="submit" name="update" value="update">MODIFIER</button>
   </form>
   <% } else { %>
	   <p>Le Blog n'existe pas ! </p>
   <% } %>
</body>
</html>