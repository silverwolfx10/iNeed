<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Nome</td>
			<td>Rm</td>
			<td>Pontuação</td>
		</tr>
		<c:forEach items="${notas}" var="n">
			<c:choose>
				<c:when test="${usuarioLogado.id == n.usuarioId.id}">
					<tr>
						<td><b>${n.usuarioId.nome}</b></td>
						<td><b>${n.usuarioId.rm}</b></td>
					    <td><b>${n.nota * 100}</b></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>${n.usuarioId.nome}</td>
						<td>${n.usuarioId.rm}</td>
					    <td>${n.nota * 100}</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
	</table>
</div>

