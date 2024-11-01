<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crud com sucesso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ErroSucesso/style-erro.css">
</head>
<body>
<div class="login-container">
    <div class="header">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="InDespensa Logo">
        <h1>InDespensa</h1>
    </div>
    <br><br><br><br>
    <div class="login-box">
        <svg width="271" height="312" viewBox="0 0 271 312" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M55.0801 27.0478C40.0858 25.7138 34.3494 51.2265 33.3555 64.1496L167.963 64.1496C167.963 60.5367 165.833 50.5595 157.314 39.554C148.794 28.5485 139.565 25.5193 136.015 25.3803C128.177 23.3794 120.822 34.5516 118.124 40.3878C108.923 28.0483 100.091 25.2414 96.8255 25.3803C87.9653 23.3793 80.3545 36.219 77.6567 42.8889C69.1372 28.215 59.0559 26.214 55.0801 27.0478Z" fill="#9D7A5E"></path>
            <path d="M195.653 0L176.91 65.0324H239.528L254.437 10.4219L195.653 0Z" fill="#FEAD98"></path>
            <path d="M0.130487 90.83V281.759C-1.9142 306.771 20.5772 312.19 32.0785 311.774H234.416C265.768 314.442 271.618 292.875 270.624 281.759C270.908 222.285 271.305 99.3343 270.624 83.3263C269.942 67.3183 252.733 63.5942 244.213 63.7332H25.6889C3.1975 63.7332 -0.72146 81.7978 0.130487 90.83Z" fill="#F59132"></path>
            <path d="M63.1762 197.967C49.2043 181.625 45.9953 156.418 46.1373 145.857H64.8801dC62.8354 155.195 69.4238 171.148 72.9736 177.957C99.8099 224.647 143.259 212.557 150.501 212.557C156.294 212.557 167.114 206.165 171.799 202.969C196.676 190.296 203.463 159.614 203.747 145.857C211.585 149.192 219.508 147.247 222.49 145.857C222.831 177.206 204.457 200.885 195.228 208.805C188.98 215.059 170.862 228.399 148.371 231.734C125.879 235.069 105.206 228.677 97.68 225.064C92.0004 222.84 77.1481 214.308 63.1762 197.967Z" fill="#FBFBFB"></path>
            <ellipse cx="213.543" cy="132.101" rx="17.4649" ry="17.0919" fill="#FBFBFB"></ellipse>
            <ellipse cx="55.9337" cy="132.101" rx="17.4649" ry="17.0919" fill="#FBFBFB"></ellipse>
        </svg>
        <br>
        <h2>CRUD Concluido!</h2>
        <p>Você gostaria de realizar mais alguma ação?</p>
        <br>
        <div class="button-wrapper">
            <a href="${pageContext.request.contextPath}/PaginasCRUD/MENU/index.jsp"><button>Voltar</button></a>
            <button>Verificar</button>
        </div>
    </div>
    <br><br><br>
    <footer>
        <br><br><br>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/Logo.png" alt="InDespensa Logo" class="logo">
            <span>InDespensa</span>
        </div>
        <div class="button-container">
            <button class="footer-button">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/OnlineSupport.png" alt="Suporte">
                <a href="#"><strong>Suporte</strong></a>
            </button>
            <button class="footer-button">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
                <a href="${pageContext.request.contextPath}/LandingPage/index.jsp"><strong>Site</strong></a>
            </button>
        </div>
        <br>
    </footer>
</div>
</body>
</html>