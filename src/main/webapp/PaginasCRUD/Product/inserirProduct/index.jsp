<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Página de Inserção de Produto</title>
</head>
<body>
<header>
    <!-- colocar logo branca -->
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="content">
    <h1>Inserção de Produto</h1>
    <form action="${pageContext.request.contextPath}/cadastrarPRODUCT" method="post">
        <div class="params">
            <label for="description">DESCRIPTION:</label>
            <br>
            <input type="text" id="description" name="DESCRIPTION" required>
        </div>
        <div class="params">
            <label for="barcode">BARCODE:</label>
            <br>
            <input type="text" id="barcode" name="BARCODE" required>
        </div>
        <div class="params">
            <label for="brand">BRAND:</label>
            <br>
            <input type="text" id="brand" name="BRAND" required>
        </div>
        <div class="params">
            <label for="name">NAME:</label>
            <br>
            <input type="text" id="name" name="NAME" required>
        </div>
        <div class="params">
            <label for="type">TYPE:</label>
            <br>
            <input type="text" id="type" name="TYPE" required>
        </div>
        <div class="params">
            <label for="weightVolume">WEIGHT/VOLUME:</label>
            <br>
            <input type="text" id="weightVolume" name="WEIGHT_VOLUME" required>
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
