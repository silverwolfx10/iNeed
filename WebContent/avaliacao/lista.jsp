<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Descrição</td>
			<td>Peso</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${avaliacoes}" var="av">
			<tr>
				<td>${av.descricao}</td>
				<td>${av.peso}</td>
				<td><a href="avaliacao?action=cadastrar&id=${av.id}">Alterar</a></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
