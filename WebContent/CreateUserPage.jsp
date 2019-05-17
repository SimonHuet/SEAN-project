<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Create user</title>
</head>
<body>
	<h1>
		Création d'un utilisateur <a href="/SEAN" style="float: right;"
			class="btn btn-info">Retour</a>
	</h1>
	<form method="post">
		<div class="form-group">
			<label for="nom">Nom </label> <input class="form-control" value=""
				name="nom" placeholder="nom">
		</div>
		<div class="form-group">
			<label for="email">Mail </label> <input class="form-control" value=""
				name="email" placeholder="mail">
		</div>

		<div class="form-group">
			<label for="password">Mot de passe <span class="requis">*</span></label>
			<input class="form-control" value="" name="password"
				placeholder="mot de passe" type="password">
		</div>
		<div class="form-group">
			<label for="confirm_password">Confirmation du mot de passe <span
				class="requis">*</span></label> <input class="form-control" type="password"
				id="confirm_password" name="confirm_password" />
		</div>
		<h1>Admin</h1>
		<input value="true" name="admin" type="radio"> Oui
		&nbsp;&nbsp; <input value="false" name="admin" type="radio"
			checked="checked"> Non<br> <br>
		<button class="btn btn-success" type="submit">Valider</button>
	</form>

</body>
</html>