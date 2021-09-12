<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
</head>
<body>
<h1>Inscription</h1>
	<form action="inscriptionAvecTeleversement" method="post" enctype="multipart/form-data">
	<input type="text" name="NOM" placeHolder="Nom" title="Nom" required><br>
	<input type="text" name="PRENOM" placeHolder="Prénom" title="Prénom" required><br>	
	<input type="email" name="EMAIL" placeHolder="Email" required><br>
	<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe" required><br>
	<select name="ID_NIVEAU" required>
	<option value="">Merci de choisir un niveau</option>
	<c:forEach items="${niveaux}" var="niveau">
		<option value="${niveau.id}">${niveau.nom}</option>
	</c:forEach>
	</select><br>
	<select name="ID_VILLE" required>
	<option value="">Merci de choisir une ville</option>
	<c:forEach items="${villes}" var="ville">
		<option value="${ville.id}">${ville.nom}</option>
	</c:forEach>
	</select><br>
	<input type="file" name="FICHIER"><br>
	<input type="submit" value="Inscription" accept=".jpg">
	</form>
</body>
</html>