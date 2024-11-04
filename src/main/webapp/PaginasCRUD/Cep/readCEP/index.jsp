<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.servletsindespensa.dao.CepDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    CepDAO cepDAO = new CepDAO();
    ResultSet ceps = cepDAO.readCep();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/Cep/readCEP/style.css">
    <title>Página de CRUD - CEP</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela de CEPs</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/readCEP" method="post">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">ID</div>
                <div class="header-item">CEP</div>
            </div>
            <div class="dados">
                <%
                    while (ceps != null && ceps.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= ceps.getInt("cep_id") %></div>
                    <div class="data-item"><%= ceps.getString("cep") %></div>
                </div>
                <%
                    }
                    if (ceps != null) {
                        ceps.close();
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
        <a href="${pageContext.request.contextPath}/PaginasCRUD/MENU/index.jsp"><strong>Menu</strong></a>
    </button>
</footer>
</body>
</html>
