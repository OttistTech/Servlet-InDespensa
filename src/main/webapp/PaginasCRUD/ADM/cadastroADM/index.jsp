<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Página de CRUD</title>
</head>
<body>
<header>
    <!-- colocar logo branca -->
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="content">
    <h1>Inserção de Parâmetros</h1>
    <form action="${pageContext.request.contextPath}/cadastroADM" method="post">
        <div class="params">
            <label for="name"> nome: </label>
            <br>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="params">
            <label for="password"> password: </label>
            <br>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="params">
            <label for="email"> email: </label>
            <br>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="send">
            <input type="submit" value="Enviar">
        </div>
    </form>
</div>
<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo">
        <span>InDespensa</span>
    </div>
    <div class="button-container">
        <button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
            <a href="${pageContext.request.contextPath}/LandingPage/index"><strong>Site</strong></a>
        </button>
    </div>
</footer>
</body>
</html>

