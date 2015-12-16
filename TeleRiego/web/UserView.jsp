<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <%@include file="/WEB-INF/header.jsp"%>
    <c:choose>
        <c:when test="${profile}">
            <!-- Vista Perfil-->
            
            <div class="container">
                <section id="perf" class="section appear clearfix">
                    <div class="container">  
                        <div class="align-center"><h2>Mi perfil</h2><br> </div>
                        <div class="row mar-bot40 col-md-6" role="group" style="margin-top: 0px"> 
                            <p>Número de usuario: <strong>${sessionScope.membership.memberNumber}</strong></p>
                            <p>DNI: <strong>${sessionScope.membership.dni}</strong></p>
                            <p>Nombre y apellidos: <strong>${sessionScope.membership.userName} ${sessionScope.membership.surname}</strong></p>
                            <p>Dirección: <strong>${sessionScope.membership.address}</strong></p>
                            <p>Teléfono: <strong>${sessionScope.membership.phone}</strong></p>
                            <p>E-Mail: <strong>${sessionScope.membership.email}</strong></p>
                            <a href="ServletChangePass"><button type="button" id="changepass" class="line-btn green">Cambiar Contraseña</button></a>                         
                        </div>
                        <div class="row mar-bot40 col-lg-offset-2 col-md-4" role="group" style="margin-top:0px">
                            <c:if test="${param.changepass}">
                                <div class="cform" id="contact-form">
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
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </div>    
                    </div>
                </section>
            </div>
            
        </c:when>
        <c:when test="${lands}">
            <!-- Vista Terrenos-->
            <div class="container">
                <section id="perf" class="section appear clearfix">
                    <div class="container">  
                        <div class="align-center"><h1>Mis Terrenos</h1><br> </div>
                        <div class="accordion">      
                            <c:set var="i" value="1"></c:set>
                            <c:forEach var="land" items="${sessionScope.membership.landCollection}">
                                <div class="accordion-section">
                                    <a class="accordion-section-title" href="#accordion-${i}">${land.nameland}</a>
                                    <div id="accordion-${i}" class="accordion-section-content">
                                        <p>Estado: <strong>${land.state}</strong></p>
                                        <p>Último riego: <strong><fmt:formatDate type="date" value="${land.lastDateIrrigation}" /></strong></p>
                                        <p>Humedad: <strong>${land.humidity} %</strong></p>
                                        <a href="ServletLand?landid=${land.landId}">Regar</a>
                                    </div><!--end .accordion-section-content-->
                                </div><!--end .accordion-section--> 
                                <c:set var="i" value="${i+1}"></c:set>
                            </c:forEach>
                        </div><!--end .accordion-->
                    </div>
                </section>
            </div>
        </c:when>
        <c:when test="${land}">
            <!-- Vista de un terreno-->
             <div class="container">
                <section id="perf" class="section appear clearfix">
                  <div class="container">  
                      <div class="align-center"><h1>Terreno: ${specificLand.nameland}</h1><br> </div>
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
        <c:when test="${transaction}">
            
            <!-- Vista Transacciones-->
            <div class="container">
                <section id="perf" class="section appear clearfix">
                    <div class="container">  
                        <div class="align-center"><h1>Transacciones</h1><br> </div>  
                        
                        <div class="accordion">      
                            <c:set var="i" value="1"></c:set>
                            <c:forEach var="transaction" items="${sessionScope.membership.transactionCollection}">
                                <div class="accordion-section">
                                    <a class="accordion-section-title" href="#accordion-${i}">Pedido nº ${transaction.norder}</a>
                                    <div id="accordion-${i}" class="accordion-section-content">
                                        <p>Compra de agua para: <strong>${transaction.landId.nameland}</strong></p>
                                        <p>Cantidad de agua: <strong>${transaction.amount} m<sup>3</sup></strong></p>
                                        <p>Precio total: <strong>${transaction.price} €</strong></p>
                                        <p>Fecha del pedido: <strong><fmt:formatDate type="date" value="${transaction.dateOrder}" /></strong></p>
                                        <p>Estado del pedido: <strong>${transaction.stateOrder}</strong></p>
                                    </div><!--end .accordion-section-content-->
                                </div><!--end .accordion-section--> 
                                <c:set var="i" value="${i+1}"></c:set>
                            </c:forEach>
                        </div><!--end .accordion-->
                    </div>
                </section>
            </div>
            
        </c:when>  
    </c:choose>
            
            <%@include file="/WEB-INF/footer.jsp"%>

                	

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
            
            <c:if test="${land}">
                           // When the window has finished loading create our google map below
            google.maps.event.addDomListener(window, 'load', init);
        
            function init() {
                // Basic options for a simple Google Map
                // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
                var mapOptions = {
                    // How zoomed in you want the map to start at (always required)
                    zoom: 11,

                    // The latitude and longitude to center the map (always required)
                    center: new google.maps.LatLng(${specificLand.latitude}, ${specificLand.longitude}), // New York //parametro de localizacion

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
