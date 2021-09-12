<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>English Battle G17</title>
</head>
<body>
	<h1>Liste des verbes</h1>
	<table>
		<tr>
			<td>Base Verbale</td>
			<td>Preterit</td>
			<td>Participe Passe</td>
			<td>Traduction</td>
			<td>Action</td>
		</tr>
		<c:forEach var="verbe" items="${verbes}">
			<tr>
				<td>${verbe.baseVerbale}</td>
				<td>${verbe.preterit}</td>
				<td>${verbe.participePasse}</td>
				<td>${verbe.traduction}</td>
				<td><a href="verbe?ID=${verbe.id}">Modifier</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="index">Retour</a>
</body>
</html>