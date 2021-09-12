<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
<link href="style/theme1.css" rel="stylesheet">
</head>
<body>
<h1>Inscription</h1>
	<form action="inscription" method="post">
	<input type="text" name="NOM" placeHolder="Nom"<c:if test="${nom ne null}"> value="${nom}"</c:if> required><br>
	<input type="text" name="PRENOM" placeHolder="Prénom"<c:if test="${prenom ne null}"> value="${prenom}"</c:if> required><br>	
	<input type="email" name="EMAIL" placeHolder="Email"<c:if test="${email ne null}"> value="${email}"</c:if> required><br>
	<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe"<c:if test="${motDePasse ne null}"> value="${motDePasse}"</c:if> required><br>
	<select name="ID_NIVEAU">
	<option value="">Merci de choisir un niveau</option>
	<c:forEach items="${niveaux}" var="niveau">
		<option value="${niveau.id}"<c:if test="${niveau.id eq idNiveau}"> selected</c:if>>${niveau.nom}</option>
	</c:forEach>
	</select><br>
	<select name="ID_VILLE">
	<option value="">Merci de choisir une ville</option>
	<c:forEach items="${villes}" var="ville">
		<option value="${ville.id}"<c:if test="${ville.id eq idVille}"> selected</c:if>>${ville.nom}</option>
	</c:forEach>
	</select><br>
	<input type="submit" value="Inscription">
	</form>
</body>
</html>