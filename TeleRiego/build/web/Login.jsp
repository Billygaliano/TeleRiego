<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Login
    Created on : 15-dic-2015, 10:22:18
    Author     : inftel11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
		<!-- BASICS -->
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Tele Riego</title>
        <meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="css/isotope.css" media="screen" />	
		<link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/bootstrap-theme.css">
		<link href="css/responsive-slider.css" rel="stylesheet">
		<link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/style.css">

		<link rel="stylesheet" href="css/font-awesome.min.css">
		<!-- skin -->
		<link rel="stylesheet" href="skin/default.css">
    </head>
	 
    <body>
        <div class="header">
            <section id="header" class="appear">
                <div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:100px; height:100px; background-color:rgba(0,0,0,0.3);" data-300="line-height:60px; height:60px; background-color:rgba(0,0,0,1);">

                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="fa fa-bars color-white"></span>
                        </button>
                        <h1><a class="navbar-brand" href="index.html" data-0="line-height:90px;" data-300="line-height:50px;">Tele Riego
                            </a></h1>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
                            <li><a href="index.html#index">Inicio</a></li>
                            <li><a href="index.html#section-about">Organización</a></li>
                            <li><a href="index.html#section-works">Entidades colaboradoras</a></li>
                            <li><a href="index.html#section-contact">Contacto</a></li>
                            <li class="active"><a href="#">Acceso a regantes</a></li>
                        </ul>
                    </div><!--/.navbar-collapse -->
                </div>
            </section>
            </div>
        <div class="container">
            <section id="login" class="section appear clearfix">
             

                    <div class="row mar-bot40" style="margin-top: 40px">
                        <div class="col-md-offset-3 col-md-6">
                            <div class="section-header">
                                <h2 class="section-heading animated" data-animation="bounceInUp">Acceso a regantes</h2>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="cform" id="contact-form">
                                <div id="sendmessage">
                                    Your message has been sent. Thank you!
                                </div>
                                <form action="ServletLogin" method="post" role="form" class="ServletLogin">
                                    <div class="wow bounceIn">
                                        <div class="form-group">
                                            <label for="name">Usuario</label>
                                            <input type="text" name="user" class="form-control" id="user" placeholder="Nombre" data-rule="maxlen:4" data-msg="Por favor, introduca usuario" />
                                            <div class="validation"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Contraseña</label>
                                            <input type="text" class="form-control" name="password" id="password" placeholder="Contraseña" data-rule="required" data-msg="Por favor, introduzca contraseña" />
                                            <div class="validation"></div>
                                        </div>
                                    <button type="submit" class="line-btn green">Acceder</button>
                                </form>
                                    <c:if test="${param.errorPassword}">
                                        <p class="error">Usuario o contraseña incorrectos</p>
                                    </c:if>
                            </div>
                        </div>
                        <!-- ./span12 -->
                    </div>

                </div>
            </section>
            </div>
            <section id="footer" class="section footer">
                <div class="container">
                    <div class="row animated opacity mar-bot0" data-andown="fadeIn" data-animation="animation">
                        <div class="col-sm-12 align-center">
                            <ul class="social-network social-circle">
                                <li><a href="http://www.fenacore.org/escaparate/gmms/fenacore/contenidosGMMFenacore.cgi?tipo=noticiasenportada" class="icoRss" title="Rss"><i class="fa fa-rss"></i></a></li>
                                <li><a href="https://twitter.com/FenacoreOficial" class="icoTwitter" title="Twitter"><i class="fa fa-twitter"></i></a></li>
                            </ul>				
                        </div>
                    </div>

                    <div class="row align-center copyright">
                        <div class="col-sm-12"><p>Copyright &copy; 2015 - Máster INFTEL</p></div>
                        <!-- 
                            All links in the footer should remain intact. 
                            Licenseing information is available at: http://bootstraptaste.com/license/
                            You can buy this theme without footer links online at: http://bootstraptaste.com/buy/?theme=Green
                        -->
                    </div>
                </div>

            </section>
                	

	<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
        <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/jquery.nicescroll.min.js"></script>
	<script src="js/fancybox/jquery.fancybox.pack.js"></script>
	<script src="js/jquery.parallax-1.1.3.js" type="text/javascript" ></script>
	<script src="js/skrollr.min.js"></script>		
	<script src="js/jquery.scrollTo-1.4.3.1-min.js"></script>
	<script src="js/jquery.localscroll-1.2.7-min.js"></script>
	<script src="js/stellar.js"></script>
	<script src="js/responsive-slider.js"></script>
	<script src="js/jquery.appear.js"></script>
	<script src="js/validate.js"></script>
	<script src="js/grid.js"></script>
        <script src="js/main.js"></script>
        <script type="text/javascript">
    </body>
</html>