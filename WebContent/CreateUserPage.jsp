<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create user </title>
</head>
<body>
	<form method="post">
		<h1>Nom</h1>
		<input value="" name="nom" placeholder="nom">
		<h1>mail</h1>
		<input value="" name="email" placeholder="mél">
		<h1>Mot de Passe</h1>
		<input value="" name="password" placeholder="mot de passe" type="password">
		<h1>Admin</h1>
		<input value="true" name="admin" type="radio"> Oui &nbsp;&nbsp;
		<input value="false" name="admin" type="radio" checked="checked"> Non<br>
		<br>
		<button type="submit">Valider</button>
	</form>
</body>
</html>