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
   List<Reponse> reponses= blog.getListOfReponses();
   String str = request.getParameter("description");%>
   <p>Voici le blog <%= name%> du <%= blog.getDateCreation() %> modifier <%= blog.getDateModification() %>.</p>
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
</body>
</html>