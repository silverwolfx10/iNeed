<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="menu">
	<ul>
		<li><a href="nota?action=cadastrar&materia_id=${materia_id}">Cadastrar Nota</a></li>
	</ul>
</div>
<div class="container">
			<form action="" method="get" onChange="this.submit();" >
					<select name="materia_id">
					 		<option <c:if test="${materia_id == 0}"> selected="selected"</c:if> value="0">
						 	 	Selecione uma matéria
					 	 	</option>
						<c:forEach items="${materias}" var="item">
						 	 <option <c:if test="${materia_id == item.id}"> selected="selected"</c:if> value="<c:out value="${item.id}"/>">
						 	 	<c:out value="${item.descricao}"/>
					 	 	</option>
						</c:forEach>
					</select>
			</form>
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Nota</td>
			<td>Tipo</td>
			<td>Ação</td>
			<td>Ação</td>
		</tr>
		<c:forEach items="${notas}" var="nt">
			<tr>
				<td><fmt:formatNumber value="${nt.nota}" pattern="####.##"/></td>
				<td>${nt.avaliacaoId.descricao}</td>
				<td><a href="nota?action=cadastrar&materia_id=${materia_id}&id=${nt.id}">Alterar</a></td>
				<td><a href="nota?action=excluir&materia_id=${materia_id}&id=${nt.id}">Excluir</a></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
