<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- <title>LOGIN</title!> -->

<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
<title>LOGIN</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">

</head>
<body class="text-center">
	<form class="form-signin">
		<div class="row justify-content-md-center align-items-center">
			<div class="col-md-4">
				<img class="mb-4">
				<h1 class="h3 mb-3 font-weight-normal">Login</h1>
				<label for="inputMatricula" class="sr-only col-md-4">Número de Matricula</label> <input type="text" id="input_matricula"
					class="form-control" placeholder="Matricula" required autofocus>
				<label for="inputPassword" class="sr-only col-md-4">Senha</label> <input
					type="password" id="input_senha" class="form-control"
					placeholder="Senha" required>

				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					In</button>
				<p class="mt-5 mb-3 text-muted">© Detroid</p>
			</div>
		</div>
	</form>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>