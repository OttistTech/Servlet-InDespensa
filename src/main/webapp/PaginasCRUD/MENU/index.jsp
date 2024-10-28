<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/MENU/style.css"> <!-- Caminho absoluto para o CSS -->
    <link rel="icon" href="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png"> <!-- Caminho absoluto para o ícone -->
    <title>Área restrita</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="logo"> <!-- Caminho absoluto para a logo -->
    <h1>InDespensa</h1>
</header>

<div class="content">
    <h1>CRUD InDespensa</h1>
    <h4>Selecione a tabela que voce deseja realizar o CRUD</h4>
    <div class="buttons">
        <a href="${pageContext.request.contextPath}/PaginasCRUD/ADM/index.jsp"><button class="admin"><p>ADM</p></button></a>
        <a href="${pageContext.request.contextPath}/categorias"><button class="admin"><p>Categorias</p></button></a>
        <a href="${pageContext.request.contextPath}/cep"><button class="admin"><p>CEP</p></button></a>
        <a href="${pageContext.request.contextPath}/produtos"><button class="admin"><p>Produtos</p></button></a>
        <a href="${pageContext.request.contextPath}/tag"><button class="admin"><p>Tag</p></button></a>
    </div>
</div>

<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo"> <!-- Caminho absoluto para a logo do footer -->
        <span>InDespensa</span>
    </div>

    <div class="button-container">
        <a href="${pageContext.request.contextPath}/"><button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site"> <!-- Caminho absoluto para o ícone do site -->
            <strong>Site</strong>
        </button></a>
        <a href="${pageContext.request.contextPath}/SupportPage/index.html"><button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/OnlineSupport.png" alt="Suporte"> <!-- Caminho absoluto para o ícone de suporte -->
            <strong>Suporte</strong>
        </button></a>
    </div>
</footer>
</body>
</html>
