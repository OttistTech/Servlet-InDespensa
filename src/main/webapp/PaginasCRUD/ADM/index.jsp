<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <!-- colocar logo branca -->
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="logo">
    <h1>InDespensa</h1>
</header>
<div class="titulo">
    <h1>Tabela ADM</h1>
    <h2>Selecione a operação desejada</h2>
</div>

<div class="crud">
    <a href="${pageContext.request.contextPath}/PaginasCRUD/ADM/cadastroADM/index.jsp" id="criar">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Create.svg" alt="icon create">
        <h5>Criar</h5>
        <h6>Adicione novos itens na tabela como preferir</h6>
    </a>
    <a href="${pageContext.request.contextPath}/PaginasCRUD/ADM/ReadADM/index.jsp" id="ler">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Reading.svg" alt="icon read">
        <h5>Ler</h5>
        <h6>Verifique os itens já existentes na tabela</h6>
    </a>
    <a href="${pageContext.request.contextPath}/PaginasCRUD/ADM/UpdateADM/index.jsp" id="atualizar">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Refresh.svg" alt="icon update">
        <h5>Atualizar</h5>
        <h6>Modifique os itens já existentes na tabela</h6>
    </a>
    <a href="${pageContext.request.contextPath}/PaginasCRUD/ADM/RemoveADM/index.jsp" id="remover">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Remove.svg" alt="icon delete">
        <h5>Remover</h5>
        <h6>Remova os itens já existentes na tabela</h6>
    </a>
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