<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td>Nome</td>
			<td>Rm</td>
			<td>Qtd de Notas Necessárias</td>
		</tr>
		<c:forEach items="${notas}" var="n">
			<tr>
				<td>${n.usuario_nome}</td>
				<td>${n.usuario_rm}</td>
			    <td>${n.usuario_qtdnota}</td>
			</tr>
		</c:forEach>
		
	</table>
</div>

