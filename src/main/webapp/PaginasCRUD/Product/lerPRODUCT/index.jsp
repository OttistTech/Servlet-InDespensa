<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.servletsindespensa.dao.ProductDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    ProductDAO productDAO = new ProductDAO();
    ResultSet products = productDAO.readProduct();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/Product/lerPRODUCT/style.css">
    <title>Página de CRUD - Produtos</title>
</head>
<body>
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela de Produtos</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/readProduct" method="post">
</form>
<div class="class-control">
    <div class="tabela-container">
        <div class="tabela">
            <div class="bar">
                <div class="header-item">ID do Produto</div>
                <div class="header-item">Descrição</div>
                <div class="header-item">Código de Barras</div>
                <div class="header-item">Marca</div>
                <div class="header-item">Nome</div>
                <div class="header-item">Tipo</div>
                <div class="header-item">Peso/Volume</div>
            </div>
            <div class="dados">
                <%
                    while (products != null && products.next()) {
                %>
                <div class="linha">
                    <div class="data-item"><%= products.getInt("product_id") %></div>
                    <div class="data-item"><%= products.getString("description") %></div>
                    <div class="data-item"><%= products.getString("barcode") %></div>
                    <div class="data-item"><%= products.getString("brand") %></div>
                    <div class="data-item"><%= products.getString("name") %></div>
                    <div class="data-item"><%= products.getString("type") %></div>
                    <div class="data-item"><%= products.getBigDecimal("weight_volume") %></div>
                </div>
                <%
                    }
                    if (products != null) {
                        products.close();
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
