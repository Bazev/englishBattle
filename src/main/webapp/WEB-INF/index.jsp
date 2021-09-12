<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>English Battle G19</title>
<link href="style/theme1.css" rel="stylesheet">
</head>
<body>
	<h1>English Battle G19</h1>
	<c:if test="${erreur ne null}"><h2>${erreur}</h2></c:if>
	<form action="connexion" method="post">
		<input type="text" name="EMAIL" placeHolder="Email"><br>
		<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe"><br>
		<input type="submit" value="Connexion">
	</form>
	<br>
	<h1>Hall of Fame</h1>
	<table>
	<c:forEach var="joueur" items="${joueurs}">
	<tr>
	<td><h2>${joueur.prenom}<c:if test="${joueur.estEnLigne}"> (en ligne)</c:if></h2></td>
	<td><h2>${joueur.meilleurScore}</h2></td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<a href="inscription">Inscription</a>
	<br>
	<a href="inscriptionAvecTeleversement">Inscription avec téléversement</a>
	<br>
	<p>Nombre total de joueurs: ${joueurs.size()}</p>
	<p><a href="joueurs">Liste des joueurs</a></p>
	<p>Nombre total de verbes: ${nbVerbes}</p>
	<p><a href="verbes">Liste des verbes</a></p>
</body>
</html>