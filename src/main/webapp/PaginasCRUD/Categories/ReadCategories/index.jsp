<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.servletsindespensa.dao.CategoriesDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    CategoriesDAO categoriesDAO = new CategoriesDAO();
    ResultSet categories = categoriesDAO.readCategories();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/Categories/ReadCategories/style.css">
    <title>Página de CRUD - Categorias</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela Categorias</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/readCategories" method="post">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">ID da Categoria</div>
                <div class="header-item">Nome da Categoria</div>
            </div>
            <div class="dados">
                <%
                    while (categories != null && categories.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= categories.getInt("category_id") %></div>
                    <div class="data-item"><%= categories.getString("category_name") %></div>
                </div>
                <%
                    }
                    if (categories != null) {
                        categories.close();
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
