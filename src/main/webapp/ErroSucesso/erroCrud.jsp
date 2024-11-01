<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro no CRUD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ErroSucesso/style-erro.css">
</head>
<body>
<div class="login-container">
    <div class="header">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="InDespensa Logo">
        <h1>InDespensa</h1>
    </div>
    <br><br><br><br>
    <div class="login-box">
        <svg width="166" height="196" viewBox="0 0 166 196" fill="none" xmlns="http://www.w3.org/2000/svg">
            <!-- SVG Paths aqui -->
        </svg>
        <br>
        <h2>Erro no CRUD</h2>
        <p>Ops! Ocorreu algum erro no CRUD, tente novamente mais tarde.</p>
        <br>
        <button><a href="${pageContext.request.contextPath}/CadastroADM/indexCadastro.jsp">Tentar Novamente</a></button>
    </div>
    <br><br><br>
    <footer>
        <br><br><br>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="InDespensa Logo" class="logo">
            <span>InDespensa</span>
        </div>
        <div class="button-container">
            <button class="footer-button">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/OnlineSupport.png" alt="Suporte">
                <a href="#"><strong>Suporte</strong></a>
            </button>
            <button class="footer-button">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
                <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
            </button>
        </div>
        <br>
    </footer>
</div>
</body>
</html>