<%@ page import="org.example.servletsindespensa.dao.ProductDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    ProductDAO productDao = new ProductDAO();
    ResultSet products= productDao.readProduct();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/PaginasCRUD/Product/lerPRODUCT/style.css">
    <title>Pagina de CRUD</title>
</head>
<body>
<header>
    <!-- colocar logo branca -->
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela Produto</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/lerPRODUCT" method="post">
    <button type="submit" class="iconBusca"><img src="${pageContext.request.contextPath}/LandingPage/imagens/Search.svg" alt="Lupa"></button>
    <input type="text" placeholder="Pesquisar...">
</form>
<div class="tabela">
    <div class="bar">
        <div class="header-item">Id</div>
        <div class="header-item">Descrição</div>
        <div class="header-item">C. de barras</div>
        <div class="header-item">Marca</div>
        <div class="header-item">Nome</div>
        <div class="header-item">Tipo</div>
        <div class="header-item">Peso</div>
    </div>
    <div class="dados">
        <%
            while (products != null && products.next()) {
        %>
        <div class="linha">
            <div class="data-item"><%= products.getString("product_id") %></div>
            <div class="data-item"><%= products.getString("description") %></div>
            <div class="data-item"><%= products.getString("barcode") %></div>
            <div class="data-item"><%= products.getString("brand") %></div>
            <div class="data-item"><%= products.getString("name") %></div>
            <div class="data-item"><%= products.getString("type") %></div>
            <div class="data-item"><%= products.getString("weight_volume") %></div>
        </div>
        <%
            }
            if (products != null) {
                products.close();
            }
        %>
    </div>
</div>
<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo">
        <span>InDespensa</span>
    </div>

    <div class="button-container">
        <button class="footer-button">
            <img src="Imagens/image.png" alt="Site">
            <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
        </button>
    </div>
</footer>
</body>
</html>