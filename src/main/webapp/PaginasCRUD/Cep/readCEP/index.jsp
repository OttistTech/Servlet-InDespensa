<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.servletsindespensa.dao.AdmDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    AdmDAO admDAO = new AdmDAO();
    ResultSet adms = admDAO.readAdm();
%>
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
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela Produto</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/readCEP" method="post">
    <button type="submit" class="iconBusca"><img src="Imagens/Search.svg" alt="Lupa"></button>
    <input type="text" placeholder="Pesquisar...">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">Email</div>
                <div class="header-item">Nome</div>
                <div class="header-item">Senha</div>
            </div>
            <div class="dados">
                <%
                    while (adms != null && adms.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= adms.getString("email") %></div>
                    <div class="data-item"><%= adms.getString("name") %></div>
                    <div class="data-item"><%= adms.getString("password") %></div>
                </div>
                <%
                    }
                    if (adms != null) {
                        adms.close();
                    }
                %>
            </div>
        </div>
    </div>
</div>
<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo">
        <span>InDespensa</span>
    </div>
    <button class="footer-button">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
        <a href="${pageContext.request.contextPath}/LandingPage/index"><strong>Site</strong></a>
    </button>
</footer>
</body>
</html>