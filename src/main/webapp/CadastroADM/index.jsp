<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InDespensa - Login</title>
    <link rel="stylesheet" href="style-adm.css">
</head>
<body>
<div class="login-container">
    <div class="header">
        <img src="../LandingPage/imagens/Logo.png" alt="InDespensa Logo">
        <h1>InDespensa</h1>
    </div>

    <div class="login-box">
        <h2>É Bom te ver de volta Chefinho!</h2>

        <!-- Exibir mensagem de erro, se houver -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message">
            <p><%= errorMessage %></p>
        </div>
        <%
            }
        %>

        <form action="loginADM" method="post">
            <label for="email">Informe seu Email:</label>
            <input type="email" name="email" id="email" placeholder="Informe seu Email" required>

            <label for="senha">Informe sua Senha:</label>
            <input type="password" name="password" id="senha" placeholder="Informe sua Senha" required>
            <br>
            <button type="submit">Enviar</button>
        </form>
    </div>

    <footer>
        <div class="logo-container">
            <img src="../LandingPage/imagens/Logo.png" alt="InDespensa Logo" class="logo">
            <span>InDespensa</span>
        </div>
        <div class="button-container">
            <button class="footer-button">
                <img src="../LandingPage/imagens/OnlineSupport.png" alt="Suporte">
                <a href="../SupportPage/index.html">Suporte</a>
            </button>
            <button class="footer-button">
                <img src="../LandingPage/imagens/InternetPreto.png" alt="Site">
                <a href="../"><strong>Site</strong></a>
            </button>
        </div>
    </footer>
</div>
</body>
</html>
