<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<c:if test="${not empty sucesso}">
			<p>${sucesso}</p>
	</c:if>
	<c:if test="${not empty erro}">
			<p>${erro}</p>
	</c:if>
	<form  method="post" action='<c:url value="/usuario/edita" />'>
		Id: <input type="text" name="usuario.id" value="${usuario.id}"/>
		Nome: <input type="text" name="usuario.nome" value="${usuario.nome}"/>
		Login: <input type="text" name="usuario.login" value="${usuario.login}"/>
		Senha: <input type="password" name="usuario.password" value="${usuario.password}"/>
		Email: <input type="text" name="usuario.email" value="${usuario.email}"/>
		<input type="submit" value="Editar">
	</form>
</body>
</html>