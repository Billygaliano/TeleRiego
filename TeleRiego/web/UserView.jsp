<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : UserView
    Created on : 12-dic-2015, 12:37:39
    Author     : inftel10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
                <div class="navbar"  role="navigation" data-0="line-height:100px; height:100px; background-color:rgba(0,0,0,1);" data-300="line-height:60px; height:60px; background-color:rgba(0,0,0,1);">

                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="fa fa-bars color-white"></span>
                        </button>
                        <h1><a class="navbar-brand" href="index.html" data-0="line-height:90px;" data-300="line-height:50px;">Tele Riego
                            </a></h1>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;">
                            <li><a href="UserView.jsp?test=''">${sessionScope.membership.userName}</a></li>
                            <li><a href="UserView.jsp?perfil=true">Mi Perfil</a></li>
                            <li><a href="UserView.jsp?terrenos=true">Mis Terrenos</a></li>
                            <li><a href="UserView.jsp?transaccion=true">Transacciones</a></li>
                            <li><a href="#">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--/.navbar-collapse -->
                </div>
            </section>
            </div>
    <c:choose>
        <c:when test="${param.perfil}">
            <!-- Vista Perfil-->
            
            <div class="container">
                <section id="perf" class="section appear clearfix">
                  <div class="container">  
                      <div class="align-center"><h1>Perfil de usuario</h1><br> </div>
                  <div class="row mar-bot40 col-md-6" role="group" style="margin-top: 0px">                           
                                 
                      <h2>Número de usuario: ${sessionScope.membership.memberNumber}</h2><br>
                      <h2>DNI: ${sessionScope.membership.dni}</h2><br>
                      <h2>Nombre y apellidos: ${sessionScope.membership.userName} ${sessionScope.membership.surname}</h2><br>
                      <h2>Dirección: ${sessionScope.membership.address}<br></h2><h3></h3><br>
                      <h2>Teléfono: ${sessionScope.membership.phone}</h2><br>
                      <h2>E-Mail: ${sessionScope.membership.email}</h2><br>
                      <a href="UserView.jsp?perfil=true&changepass=true"><button type="button" id="changepass" class="line-btn green">Cambiar Contraseña</button></a>                         
                  </div>
                      <div class="row mar-bot40 col-lg-offset-2 col-md-4" role="group" style="margin-top:0px">
                        <c:if test="${param.changepass}">
                        <div class="cform" id="contact-form">
                                <div id="sendmessage">
                                    Your message has been sent. Thank you!
                                </div>
                                <form action="ServletChangePass" method="post" role="form">
                                    <div class="wow bounceIn">
                                        <div class="form-group">
                                            <label for="name">Contraseña Actual</label>
                                            <input type="password" name="oldPass" class="form-control" id="user" placeholder="" data-rule="required" data-msg="Por favor, introduca contraseña" />
                                            <div class="validation"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Nueva Contraseña</label>
                                            <input type="password" class="form-control" name="newPass" id="password" placeholder="" data-rule="required" data-msg="Por favor, introduzca contraseña" />
                                            <div class="validation"></div>
                                        </div>
                                    <button type="submit" class="line-btn green">Cambiar</button>
                                </form>

                            </div>
                        </div>
                        </c:if>
                      </div>    
                  </div>
                </section>
            </div>
            
        </c:when>
        <c:when test="${param.terrenos}">
            <!-- Vista Terrenos-->
            <div class="container">
                <section id="perf" class="section appear clearfix">
                  <div class="container">  
                      <div class="align-center"><h1>Mis Terrenos</h1><br> </div>
                      <div class="row mar-bot40 col-sm-offset-3 col-sm-6 col-sm-offset-3 table-responsive" role="group" style="margin-top: 0px">
                          
                          <table id="field_table" border="0" cellpadding="5" style="border-collapse: separate;border-spacing: 20px 20px">
                              <thead>
                                  <tr>
                                      <th><h4>Nombre</h4></th>
                                      <th><h4>Estado</h4></th>
                                      <th><h4>Ult. Riego</h4></th>
                                      <th><h4>Humedad</h4></th>
                                      <th>  </th>
                                  </tr>
                              </thead>
                              <tbody>
                                <!--foreach -->  
                                  <tr>
                                      <td>Nombre</td>
                                      <td>Estado</td>
                                      <!-- choose regando o fecha-->
                                      <td>Regando</td><!--end choose-->
                                      <td>Humedad %</td>
                                      <td><a href="UserView.jsp?field=true"><button type="button">Go</button></a></td>
                                  </tr><!--endforeach-->
                              </tbody>
                          </table>
                        
                      </div>
                  </div>
                </section>
            </div>
        </c:when>
        <c:when test="${param.field}">
            <!-- Vista de un terreno-->
             <div class="container">
                <section id="perf" class="section appear clearfix">
                  <div class="container">  
                      <div class="align-center"><h1>Terreno: Nombre</h1><br> </div>
                      <div class="row mar-bot40 col-sm-6" role="group" style="margin-top: 0px">
                          <div id="area">Informacion sobre el área del terreno</div>
                          <div id="humedad">Información sobre la humedad del terreno</div>
                          <div id="fechariego">Información sobre la fecha del último riego</div>                                                  
                      </div>
                      <div class="row mar-bot40 col-sm-6" role="group" style="margin-top: 0px">
                          <div id="map">Información sobre la localización MAPA</div>
                          <div id="agua">Información sobre el agua</div>
                          <div id="estado">Información sobre el estado</div>
                      </div>                      
                  </div>
                </section>
            </div>
        </c:when>
        <c:when test="${param.transaccion}">
            
            <!-- Vista Transacciones-->
            <div class="container">
                <section id="perf" class="section appear clearfix">
                    <div class="container">  
                        <div class="align-center"><h1>Pedidos</h1><br> </div>                       
                            <div class="accordion">
                                    <div class="accordion-section">
                                            <a class="accordion-section-title" href="#accordion-1">Pedido nº "numPedido"</a>
                                            <div id="accordion-1" class="accordion-section-content">
                                                <p>Compra de agua para "nombreTerreno"</p>
                                                <p>Cantidad de agua: X m<sup>3</sup></p>
                                                <p>Precio total: X €</p>
                                                <p>Fecha del pedido: XX/XX/XXXX</p>
                                                <p>Estado del pedido: pendiente OR pagado</p>
                                            </div><!--end .accordion-section-content-->
                                    </div><!--end .accordion-section-->

                                    <div class="accordion-section">
                                            <a class="accordion-section-title" href="#accordion-2">Pedido nº "numPedido"</a>
                                            <div id="accordion-2" class="accordion-section-content">
                                                <p>Compra de agua para "nombreTerreno"</p>
                                                <p>Cantidad de agua: X m<sup>3</sup></p>
                                                <p>Precio total: X €</p>
                                                <p>Fecha del pedido: XX/XX/XXXX</p>
                                                <p>Estado del pedido: pendiente OR pagado</p>
                                            </div><!--end .accordion-section-content-->
                                    </div><!--end .accordion-section-->

                                    <div class="accordion-section">
                                            <a class="accordion-section-title" href="#accordion-3">Pedido nº "numPedido"</a>
                                            <div id="accordion-3" class="accordion-section-content">
                                                <p>Compra de agua para "nombreTerreno"</p>
                                                <p>Cantidad de agua: X m<sup>3</sup></p>
                                                <p>Precio total: X €</p>
                                                <p>Fecha del pedido: XX/XX/XXXX</p>
                                                <p>Estado del pedido: pendiente OR pagado</p>
                                            </div><!--end .accordion-section-content-->
                                    </div><!--end .accordion-section-->
                            </div><!--end .accordion-->
                    </div>
                </section>
            </div>
            
        </c:when>    
        <c:otherwise>
            <!-- Vista Principal Usuario-->
            <div class="container">
            <section id="user-principal" class="section appear clearfix">
                  
                    <div class="row mar-bot40 col-md-12" role="group" style="margin-top: 40px">
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <a href="UserView.jsp?perfil=true?userName=${membership.userName}"><button type="button" class="btn btn-theme"><h1>Mi Perfil</h1></button></a>
                        </div>
                        </div>
                    </div>
                     <div class="row mar-bot40 col-md-12" role="group" style="margin-top: 40px">
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <a href="UserView.jsp?terreno=true"><button type="button" class="btn btn-theme"><h1>Mis Terrenos</h1></button></a>
                        </div>
                        </div>
                    </div>
                     <div class="row mar-bot40 col-md-12" role="group" style="margin-top: 40px">
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <a href="UserView.jsp?perfil=true"><button type="button" class="btn btn-theme"><h1>Transacciones</h1></button></a>
                        </div>
                        </div>
                    </div>                    
                 
                 </section>
            </div>
            
 
            
        </c:otherwise>
    </c:choose>
            
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
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB6JFvBfAvf0e91N6WgmpJ4vk-e4uJKO6U&sensor=false"></script>        
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
        <script type="text/javascript" src="js/accordion.js"></script>
        <script type="text/javascript">
            
            <c:if test="${param.field}">
                           // When the window has finished loading create our google map below
            google.maps.event.addDomListener(window, 'load', init);
        
            function init() {
                // Basic options for a simple Google Map
                // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
                var mapOptions = {
                    // How zoomed in you want the map to start at (always required)
                    zoom: 11,

                    // The latitude and longitude to center the map (always required)
                    center: new google.maps.LatLng(40.6700, -73.9400), // New York //parametro de localizacion

                    // How you would like to style the map. 
                    // This is where you would paste any style found on Snazzy Maps.
                    //styles: [	{		featureType:"all",		elementType:"all",		stylers:[		{			invert_lightness:true		},		{			saturation:10		},		{			lightness:30		},		{			gamma:0.5		},		{			hue:"#1C705B"		}		]	}	]
      
        };       
                // Get the HTML DOM element that will contain your map 
                // We are using a div with id="map" seen below in the <body>
                var mapElement = document.getElementById('map');

                // Create the Google Map using out element and options defined above
                var map = new google.maps.Map(mapElement, mapOptions);
            }
        </script>
		 <script src="js/wow.min.js"></script>
	 <script>
	 wow = new WOW(
	 {
	
		}	) 
		.init();
	</script>
            </c:if>
    </body>
</html>
