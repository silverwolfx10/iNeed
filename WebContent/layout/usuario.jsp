<%@ page language="java" contentType="text/html charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=ISO-8859-1">
<title>${title}</title>
<style type="text/css">
.linha{
	width:800px;
	height:30px;
	margin: 0 auto;
	margin-top:20px;
}
.label{
	float:left;
	width:100px;
	height:30px;
}
.campo{
	float:left;
	width:680px;
	margin-left:20px;
	height:30px;
}
.campo input{
	width:548px;
}

.campo input[type="radio"]{
	width:10px;
}

.submit{
	width:800px;
	maring:0 auto;
}
.submit input{
	float:right;
	margin-right: 120px;
}
h1{
	width:800px;
	height:100px;
	margin: 0 auto;
	margin-top:20px;
}
.menu{
	width:800px;
	margin:0 auto;
}
.menu ul{
	float:left;
	width:800px;
	padding-left:0px;
}
.menu ul li{
	list-style:none;
	float:left;
	margin-left:10px;
}
.menu ul li a{
	display:block;
}

.menulogado{
	float:left;
	width:150px;
	height:30px;
	margin-right:10px;
	margin-top: 15px;
}

.container{
	width:800px;
	margin:0 auto;
}

.table{
	width:800px;
	float:left;
}

.voltar{
	
	width:800px;
	margin:0 auto;
	padding-top:50px;
}
</style>
</head>

<body>
	<div class="menu">
		<ul>
			<li><a href="nota">NOTAS</a></li>
			<li><a href="boletim">BOLETIM</a></li>
			<li><a href="ranking">RANKING</a></li>
			<c:if test="${usuarioLogado.isAdmin == 1}">			
			<li><a href="turmas">Painel Admin</a></li>
			</c:if>
		</ul>
	</div>
	
	<div class="menulogado">Logado Como: ${usuarioLogado.nome}
	  <a href="login?action=logout">Logout</a>		
	</div>
	<jsp:include page="${pagina}" flush="true" />  
</body>
</html>