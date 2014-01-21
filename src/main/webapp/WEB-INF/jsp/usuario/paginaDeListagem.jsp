<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<c:if test="${not empty erro}">
			<p>${erro}</p>
	</c:if>
	<c:if test="${not empty sucesso}">
			<p>${sucesso}</p>
	</c:if>
	<form  method="post" action='<c:url value="/usuario/lista" />'>
		Login: <input type="text" name="usuario.login" />
		<input type="submit" value="Listar">
	</form>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Login</td>
				<td>Email</td>
				<td>Ação</td>
			</tr>
		</thead>
		<c:forEach var="usuario" items="${usuarios}">
			<tr>
				<td>${usuario.id} </td>
				<td>${usuario.nome} </td>
				<td>${usuario.login} </td>
				<td>${usuario.email} </td>
				<td><a href='<c:url value="/usuario/exclui/${usuario.id}" />'>Excluir</a> 
				<a href='<c:url value="/usuario/paginaDeEdicao/${usuario.id}" />'>Editar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>