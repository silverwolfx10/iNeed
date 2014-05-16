<%@ page language="java" contentType="text/html charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=ISO-8859-1">
<title>Lista Materias</title>
</head>
<body>
	<table width="800px" align="center">
		<tr style="font-weight: bold">	
			<td>Materia</td>
			<td>Turma</td>
			<td>Ação</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${listaMaterias}" var="m">
			<tr>
				<td>${m.descricao}</td>
				<td>${m.turmaId.getDescricao()}</td>
				<td><a href="alterarContato?id=${c.id}">Alterar</a></td>
				<td><a href="excluirContato?id=${c.id}">Excluir</a></td>
			</tr>
		</c:forEach>
		<tr><td colspan="5">&nbsp</td></tr>
		<tr><td colspan="5"><a href="menu.html">Voltar</a></td></tr>
	</table>
</body>
</html>