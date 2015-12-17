<%-- 
    Document   : Land-content
    Created on : 16-dic-2015, 23:15:33
    Author     : aitorpagan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
             <div class="container">
                <section id="perf" class="section appear clearfix">
                  <div class="container">  
                      <div class="align-center"><h1>Terreno: ${specificLand.nameland}</h1><br> </div>
                      <div class="row mar-bot40 col-sm-6" role="group" style="margin-top: 0px">
                          <div id="area">Informacion sobre el área del terreno: <strong>${specificLand.squareMeters}</strong></div>
                          <div id="humedad">Información sobre la humedad del terreno: <strong>${specificLand.humidity} %</strong></div>
                          <div id="fechariego">Información sobre la fecha del último riego: <strong><fmt:formatDate type="date" value="${specificLand.lastDateIrrigation}" /></strong></div>                                                  
                      </div>
                      <div class="row mar-bot40 col-sm-6" role="group" style="margin-top: 0px">
                          <div id="map">Información sobre la localización MAPA</div>
                          <div id="agua">Información sobre el agua</div>
                          <div id="estado">Información sobre el estado</div>
                      </div>                      
                  </div>
                </section>
            </div>
