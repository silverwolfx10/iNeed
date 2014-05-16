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
			<li><a href="turmas">TURMAS</a></li>
			<li><a href="materias">MATERIAS</a></li>
			<li><a href="avaliacao">TIPO DE AVALIAÇÃO</a></li>
			<li><a href="usuarios">USUÁRIOS</a></li>
		</ul>
	</div>
	<jsp:include page="${pagina}" flush="true" />  
</body>
</html>