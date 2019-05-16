<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion </title>
</head>
<body>

<form method="post">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="name">Nom d'utilisateur</label>
                <input type="text" id="name" name="name" value="" size="20" maxlength="20" />
                <br />
                
                <label for="password">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
            </fieldset>
            
        </form>
        
       <form action="/SEAN/CreateUserServlet">
       <p> Pas de compte ?  Inscrivez vous ici:</p>
		<input type="submit" value="Create" />
	</form>
</body>
</html>