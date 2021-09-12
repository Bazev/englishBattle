<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification du verbe ${verbe.baseVerbale}</title>
</head>
<body>
<h1>Modification du verbe ${verbe.baseVerbale}</h1>
<form action="verbe?ID=${verbe.id}" method="post">
    <input type="text" name="BASE_VERBALE" value="${verbe.baseVerbale}" placeHolder="Base verbale"  title="Base verbale contenant uniquement des lettres" required><br>
    <input type="text" name="PRETERIT" value="${verbe.preterit}" placeHolder="Prétérit"  title="Prétérit contenant uniquement des lettres" required><br>    
    <input type="text" name="PARTICIPE_PASSE" value="${verbe.participePasse}" placeHolder="Participe passé" title="Participe passé contenant uniquement des lettres" required><br>
    <input type="text" name="TRADUCTION" value="${verbe.traduction}" placeHolder="Traduction" title="Traduction contenant uniquement des lettres" required>
    <input type="submit" value="Enregistrer">
    </form>
</body>
</html>