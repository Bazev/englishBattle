<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>English Battle G18</title>
</head>
<body>
	<h1>Liste des joueurs</h1>
	<form action="joueurs" method="get">
	Ville <select name="ID_VILLE">
	<option value="">Toutes</option>
	<c:forEach items="${villes}" var="ville">
		<option value="${ville.id}"<c:if test="${ville.id eq idVilleRecherchee}"> SELECTED</c:if>>${ville.nom}</option>
	</c:forEach>
	</select> Prénom <input type="text" name="PRENOM" value="${prenomRecherche}"> <input type="submit" value="Rechercher"> 
	</form>
	<p></p>
	<table>
		<tr>
			<td>Image</td>
			<td>Prénom</td>
			<td>Nom</td>
			<td>Ville <c:if test="${idTri ne 0}"><a href="joueurs?ID_TRI=0&PRENOM=${prenomRecherche}&ID_VILLE=${idVilleRecherchee}">&#8595;</a></c:if></td>
			<td>Niveau</td>
			<td>Meilleur score <c:if test="${idTri ne 1}"><a href="joueurs?ID_TRI=1&PRENOM=${prenomRecherche}&ID_VILLE=${idVilleRecherchee}">&#8595;</a></c:if></td>
		</tr>
		<c:forEach var="joueur" items="${joueurs}">
			<tr>
				<td><img src="images/${joueur.id}.jpg" height="150px"></td>
				<td>${joueur.prenom}</td>
				<td>${joueur.nom}</td>
				<td>${joueur.ville.nom}</td>
				<td>${joueur.niveau.nom}</td>
				<td>${joueur.meilleurScore}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="index">Retour</a>
</body>
</html>