<%@ page import="org.example.servletsindespensa.dao.ProductDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%
    ProductDAO productDao = new ProductDAO();
    ResultSet products= productDao.read();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
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
        <li>ID</li>
        <li>Descrição</li>
        <li>C. de Barras</li>
        <li>Marca</li>
        <li>Nome</li>
        <li>Tipo</li>
        <li>Peso</li>
    </div>
    <div class="dados">
        <%
            while (products != null && products.next()) {
        %>
        <div class="linha">
            <li><%= products.getString("product_id") %></li>
            <li><%= products.getString("description") %></li>
            <li><%= products.getString("barcode") %></li>
            <li><%= products.getString("brand") %></li>
            <li><%= products.getString("name") %></li>
            <li><%= products.getString("type") %></li>
            <li><%= products.getString("weight_volume") %></li>
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
            <a href="${pageContext.request.contextPath}/LandingPage/index"><strong>Site</strong></a>
        </button>
    </div>
</footer>
</body>
</html>