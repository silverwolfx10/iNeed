<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <div class="menu">
	<ul>
		<li><a href="avaliacao?action=cadastrar">Cadastrar novo Tipo Avaliação</a></li>
	</ul>
</div> -->

<div class="container">
	<table class="table" align="center">
		<tr style="font-weight: bold">	
			<td rowspan="2">Disciplinas</td>
			<td colspan="4">1º Semestre</td>
			<td colspan="2">2º Semestre</td>
			<td>Preciso:</td>
		</tr>
		<tr style="font-weight: bold">	
			<td>NAC</td>
			<td>AM</td>
			<td>PS</td>
			<td>MD</td>
			<td>NAC</td>
			<td>AM</td>
			<td>PS</td>
		</tr>
		<tr>
		<c:forEach items="${boletim}" var="nt" varStatus="contador">
		
		     <c:if test="${contador.count == 1 || (contador.count - 1) % 7 == 0}">
		  		   <td>${nt.materiaId.descricao}</td>
             </c:if>
		      	<td><fmt:formatNumber value="${nt.nota}" pattern="####.##"/></td>
     	  	 <c:if test="${(contador.count) % 7 == 0}">
              	</tr>
              	<tr>
              </c:if>
	        
		</c:forEach>
		</tr>
		
	</table>
</div>
