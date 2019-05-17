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
<title>Connexion à My-Epsi</title>
</head>
<body>

	<h1>My epsi</h1>
	<form method="post">
		<fieldset>
			<legend>Connexion</legend>
			<p>Vous pouvez vous connecter via ce formulaire.</p>
			<div class="form-group">
				<label for="name">Nom d'utilisateur</label> <input
					class="form-control" type="text" id="name" name="name" value=""
					size="20" maxlength="20" />
			</div>

			<div class="form-group">
				<label for="password">Mot de passe <span class="requis">*</span></label>

				<input class="form-control" type="password" id="password"
					name="password" value="" size="20" maxlength="20" />
			</div>
			<input class="btn btn-success" type="submit" value="Connexion"
				class="sansLabel" />
		</fieldset>

	</form>
	<br>
	<form action="/SEAN/CreateUserServlet">
		<input class="btn btn-link" type="submit"
			value="Pas de compte ? Inscrivez vous ici" />
	</form>
</body>
</html>