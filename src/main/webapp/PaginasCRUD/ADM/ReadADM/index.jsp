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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/ADM/ReadADM/style1.css">
    <title>Página de CRUD</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela ADM</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/readADM" method="post">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">Id</div>
                <div class="header-item">Email</div>
                <div class="header-item">Nome</div>
                <div class="header-item">Senha</div>
            </div>
            <div class="dados">
                <%
                    while (adms != null && adms.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= adms.getInt("customer_id") %></div>
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
    <a href="${pageContext.request.contextPath}/PaginasCRUD/MENU/index.jsp"><button class="footer-button">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Suporte">
        <strong>Suporte</strong>
    </button></a>
</footer>
</body>
</html>