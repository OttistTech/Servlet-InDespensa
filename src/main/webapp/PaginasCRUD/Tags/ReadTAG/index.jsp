<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.example.servletsindespensa.dao.TagDAO" %>
<%
    TagDAO tagDAO = new TagDAO();
    ResultSet tags = tagDAO.readTag();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/Tags/ReadTAG/style.css">
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
<form action="${pageContext.request.contextPath}/readTAG" method="post">
    <button type="submit" class="iconBusca"><img src="${pageContext.request.contextPath}/LandingPage/imagens/Search.svg" alt="Lupa"></button>
    <input type="text" placeholder="Pesquisar...">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">Id</div>
                <div class="header-item">Description</div>
            </div>
            <div class="dados">
                <%
                    while (tags != null && tags.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= tags.getInt("id") %></div>
                    <div class="data-item"><%= tags.getString("description") %></div>
                </div>
                <%
                    }
                    if (tags != null) {
                        tags.close();
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
        <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
    </button>
</footer>
</body>
</html>