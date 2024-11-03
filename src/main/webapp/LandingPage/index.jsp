<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InDespensa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/LandingPage/styleLanding.css">
    <link rel="icon" href="${pageContext.request.contextPath}/LandingPage/imagens/LogoSombrinha.png">
</head>
<body>
<!-- Nosso header -->
<header>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="Logo" id="logo">
    <div class="topicos1">
        <a href="#sobre">Sobre</a>
        <a href="#funcionalidades" id="funcionalidadesA">Funcionalidades</a>
        <a href="#historia" id="sobreA">Nossa história</a>
    </div>
    <div class="textao">
        <h1>InDespensa</h1>
        <h2>O indispensável da sua cozinha</h2>
    </div>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/CelularIndespensa.png" id="cel" alt="cel">
</header>
<!-- Quem somos -->
<section class="quemSomosText" id="sobre">
    <h1 id="typeBlack">Quem</h1>
    <h1 id="special">Somos?</h1>
</section>
<!-- Div quadrados com texto -->
<div class="quadrados">
    <section class="quadrado1" id="quadrado1">
        <p>O InDespensa surgiu para resolver o desafio da organização de alimentos e reduzir o desperdício, algo comum no cotidiano. Pensado para melhorar a eficiência na cozinha e incentivar práticas mais sustentáveis, o InDespensa é uma ferramenta indispensável para quem busca praticidade e cuidado com o meio ambiente.</p>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/pessoa.png" id="herique" alt="pessoa">
    </section>
    <section class="quadrado2" id="quadrado2">
        <p>O InDespensa nasceu para solucionar o desafio da organização de alimentos e reduzir o desperdício. Pensado para tornar a cozinha mais eficiente e sustentável, ele se apresenta como uma ferramenta essencial para o dia a dia.</p>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/image-Photoroom%20(12)%201.png" class="ferreira" alt="pessoa">
    </section>
</div>
<section class="type" id="funcionalidades">
    <h1 class="typeBlack">Funcionalidades:</h1>
</section>
<section class="quadradoGrande">
    <div id="carrosel1" class="carrosel">
        <h1 class="typeBlack">Despensa</h1>
        <p>Administre sua Despensa como quiser! Com nosso app, você terá total controle da sua cozinha, promovendo maior organização e produtividade</p>
        <div class="linha"></div>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Lista_compras.png" alt="ListaDeCompra">
        <a href="#carrosel2">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/ArrowRight.png" class="setas setaDireita" alt="SetaDireita">
        </a>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Bolinhas1.png" class="bolinhas" alt="Bolinhas1">
    </div>
    <div id="carrosel2" class="carrosel">
        <h1 class="typeBlack">Receitas</h1>
        <p>Utilize todos os ingredientes na sua Despensa! Usando a aba Receitas, você poderá escolher diversos pratos para fazer no seu dia a dia</p>
        <div class="linha"></div>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Detalhe_Receita.png" alt="Detalhe - Receita">
        <a href="#carrosel1">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/ArrowLeft.png" class="setas setaEsquerda" alt="SetaEsquerda">
        </a>
        <a href="#carrosel3">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/ArrowRight.png" class="setas setaDireita" alt="SetaDireita">
        </a>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Bolinhas2.png" class="bolinhas" alt="Bolinhas2">
    </div>
    <div id="carrosel3" class="carrosel">
        <h1 class="typeBlack">Microempreendedor</h1>
        <p>O plano microempreendor conta com diversas utilidades para te ajudar a organizar seu negócio! Além disso, você pode anunciar seu negocio em nosso app.</p>
        <div class="linha"></div>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Adicionar_item.png" alt="Add_Item">
        <a href="#carrosel2">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/ArrowLeft.png" class="setas setaEsquerda" alt="SetaEsquerda">
        </a>
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/Bolinhas3.png" class="bolinhas" alt="Bolinhas3">
    </div>
</section>
<div>
    <h1 class="typeBlack" id="historia">Nossa História</h1>
</div>
<section class="container">
    <p>O <strong>Ottis Tech</strong> nasceu da ideia de cinco alunos da brisa louca no meio da floresta atrás do Colégio Germinare. Obrigado! baixem o Indespensa, ainda não sabemos o significado do nome da empresa, mas é isso mesmo!</p>
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/FotosIndividuais.png" alt="fotosindividuais">
</section>
<section class="container2">
    <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoSombrinha.png" alt="logoSombrinha" id="logoSombrinha">
    <div class="nossasRedes" id="redes">
        <div class="nossasRedesText">
            <h1 class="typeBlack">NOSSAS</h1>
            <h1 class="special">REDES</h1>
        </div>
        <div class="redes">
            <div class="container3">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/Instagram.png" alt="instagram">
                <a href="https://www.instagram.com/indespensa_/" target="_blank" style="color: inherit; text-decoration: none;">
                    <h2>@indespensa_</h2>
                </a>
            </div>
            <div class="container3">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/Email.png" alt="email">
                <h2>indespensa@gmail.com</h2>
            </div>
            <div class="container3">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/Phone.png" alt="phone">
                <h2>(11) 9111-6411</h2>
            </div>
            <div class="container3">
                <img src="${pageContext.request.contextPath}/LandingPage/imagens/Internet.png" alt="internet">
                <h2>indespensa.com.br</h2>
            </div>
        </div>
    </div>
</section>
<footer>
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/LandingPage/imagens/LogoEscurinha.png" alt="InDespensa Logo" class="logo">
        <span>InDespensa</span>
    </div>
    <ul class="footer-links">
        <li><a href="#sobre">Sobre nós</a></li>
        <li><a href="#funcionalidades">Funcionalidades</a></li>
        <li><a href="#historia">Nossa História</a></li>
        <li><a href="#redes">Nossas Redes</a></li>
    </ul>
    <div class="button-container">
        <a href="${pageContext.request.contextPath}/SupportPage/index.html"><button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/OnlineSupport.png" alt="Suporte">
            <strong>Suporte</strong>
        </button></a>
        <a href="#"><button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/InternetPreto.png" alt="Site">
            <strong>Site</strong>
        </button></a>
        <a href="${pageContext.request.contextPath}/CadastroADM/index.jsp"><button class="footer-button">
            <img src="${pageContext.request.contextPath}/LandingPage/imagens/restrita.png" alt="Cadastro ADM">
            <strong>Área restrita</strong>
        </button></a>
    </div>
</footer>
</body>
</html>