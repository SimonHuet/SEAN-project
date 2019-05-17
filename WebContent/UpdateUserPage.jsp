<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import= "fr.epsi.seanProject.beans.Utilisateur"%>
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
<title>Update user </title>
</head>
<body>
<h1>Modification de l'utilisateur 
   <a href="/SEAN/ListUserServlet" style="float: right;"
			class="btn btn-info">Retour</a></h1>
	<form method="post">
	<% Utilisateur uti= (Utilisateur)request.getAttribute("User");
   %>
		
		<div class="form-group">
			<label for="nom">Nom</label> 
			<input class="form-control" 
				id="nom" name="nom" />
		</div>
		<div class="form-group">
			<label for="mail">mail</label>
		<input class="form-control"  name="mail" >
		<div class="form-group">
			<label for="password">Mot de Passe</label>
		<input class="form-control"  name="password" type="password">
		</div>
		
		<div class="form-group">
			<label for="confirm_password">Confirmation de mot de passe</label>
        <input class="form-control"  type="password" name="confirm_password"/>
        </div>
		<div class="form-group">
			<label for="admin">Admin</h1>
		<input  value="true" name="admin" type="radio"> Oui &nbsp;&nbsp;
		<input  value="false" name="admin" type="radio"> Non<br>
		</div>
		<br>
		<button class="btn btn-success" type="submit">Valider</button>
	</form>
	<form method="post">
	<br>
   	 <button class="btn btn-danger" onclick="submit" name="delete" value="delete">DELETE</button>
   </form>
</body>
</html>