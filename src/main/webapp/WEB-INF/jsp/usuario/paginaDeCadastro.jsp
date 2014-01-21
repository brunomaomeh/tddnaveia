<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<form  method="post" action='<c:url value="/usuario/cadastra" />'>
		Id: <input type="text" name="usuario.id" />
		Nome: <input type="text" name="usuario.nome" />
		Login: <input type="text" name="usuario.login" />
		Senha: <input type="password" name="usuario.password" />
		Email: <input type="text" name="usuario.email" />
		<input type="submit" value="Cadastrar">
	</form>
</body>
</html>