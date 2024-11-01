<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CadastroADM/style-adm.css">
    <title>Cadastro ADM</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="logo">
    <h1>InDespensa</h1>
</header>

<div class="container">
    <h1 id="texto1">É Bom te ver de volta!</h1>
    <form action="${pageContext.request.contextPath}/verificarLogin" method="post">
        <h2 id="texto2">Informe seu Email:</h2>
        <input type="email" id="email" name="email" required>
        <h2 id="texto3">Informe sua Senha:</h2>
        <input type="password" id="password" name="password" required>
        <br>
        <!-- Exibir mensagem de erro, se houver -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="login-error-message">
            <p style="color: red; display: flex; justify-content: center"><%= errorMessage %></p>
        </div>
        <%
            }
        %>
        <button type="submit"><h2>Enviar</h2></button>
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
            <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
        </button>
        <button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/OnlineSupport.png" alt="Suporte">
            <a href="${pageContext.request.contextPath}/SupportPage/index.html"><strong>Suporte</strong></a>
        </button>
    </div>
</footer>
</body>
</html>
