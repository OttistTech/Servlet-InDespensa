<%@ page import="org.example.servletsindespensa.dao.CategoriesDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.example.servletsindespensa.dao.CategoriesDAO" %>
<%
    CategoriesDAO categoryDAO = new CategoriesDAO();
    ResultSet categories = categoryDAO.readCategories();
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
    <!-- colocar logo branca -->
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela de Categorias</h1>
    <h2>Verifique os dados da tabela</h2>
</div>
<form action="${pageContext.request.contextPath}/lerCategories" method="post">
    <button type="submit" class="iconBusca"><img src="Imagens/Search.svg" alt="Lupa"></button>
    <input type="text" placeholder="Pesquisar...">
</form>
<div class="class-control">
    <div class="tabela">
        <div class="bar">
            <div class="header-item">CATEGORY ID</div>
            <div class="header-item">CATEGORY NAME</div>
        </div>
        <div class="dados">
            <%
                while (categories != null && categories.next()) {
            %>
            <div class="linha">
                <div class="data-item"><%= categories.getString("category_id") %></div>
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

<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo">
        <span>InDespensa</span>
    </div>

    <div class="button-container">
        <button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
            <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
        </button>
    </div>
</footer>
</body>
</html>
