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
	width:280px;
	height:20px;
	margin: 0 auto;
	margin-top:20px;
}
.label{
	float:left;
	width:50px;
	height:30px;
}
.campo{
	float:left;
	width:230px;
	margin-left:20px;
	height:30px;
}
.campo input{
	width:210px;
}

.submit{
	width:300px;
	float:right;
}
.submit input{
	float:right;
	margin-right: 20px;
}
h2{
	width:300px;
	height:35px;
	margin-top:20px;
	text-align: center;
}
.container{
	width:300px;
	margin:0 auto;
	border:1px solid #666;
	height: 170px;
	margin-top:100px;
}
</style>
</head>

<body>
	<div class="container">
		<form action="" method="POST" enctype="application/x-www-form-urlencoded">
		<c:choose>
		  <c:when test="${mensagem != null}">
		    <h2>${mensagem}</h2>
		  </c:when>
		  <c:otherwise>
		    <h2>Seja bem vindo ao iNeed</h2>
		  </c:otherwise>
		</c:choose>
			
			<div class="linha">
				<div class="label">RM:</div>
				<div class="input">
					<input type="text" name="login" />
				</div>
			</div>
			<div class="linha">
				<div class="label">Senha:</div>
				<div class="input">
					<input type="password" name="password" />
				</div>
			</div>
			<div class="submit">
				<input type="submit" value="Entrar"/>
			</div>
		</form>
	</div>
	
</body>
</html>