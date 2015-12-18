<%-- 
    Document   : AdminTransaction
    Created on : 18-dic-2015, 10:33:20
    Author     : inftel10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
            <div class="container">
                <section id="perf" class="section appear clearfix">
                    <div class="container">  
                        <div class="align-center"><h1>Transacciones</h1><br> </div>  
                        
                        <div class="accordion">      
                            <c:set var="i" value="1"></c:set>
                            <c:forEach var="transaction" items="${transaction}">
                                <div class="accordion-section">
                                    <a class="accordion-section-title" href="#accordion-${i}">Pedido nº ${transaction.norder}</a>
                                    <div id="accordion-${i}" class="accordion-section-content">
                                        <p>Número de socio: <strong>${transaction.memberNumber}</strong></p>
                                        <p>Compra de agua para: <strong>${transaction.landId.nameland}</strong></p>
                                        <p>Cantidad de agua: <strong>${transaction.amount} m<sup>3</sup></strong></p>
                                        <p>Precio total: <strong>${transaction.price} €</strong></p>
                                        <p>Fecha del pedido: <strong><fmt:formatDate type="date" value="${transaction.dateOrder}" /></strong></p>
                                        <p>Estado del pedido: <strong>${transaction.stateOrder}</strong></p>
                                        <a href="ServletAcceptTransaction?norder=${transaction.norder}&landId=${transaction.landId}&amountWater=${transaction.amount}"><button  class="line-btn green">Aceptar</button></a>
                                        <a href="ServletDeniedTransaction"><button  class="line-btn green">Denegar</button></a>
                                    </div><!--end .accordion-section-content-->
                                </div><!--end .accordion-section--> 
                                <c:set var="i" value="${i+1}"></c:set>
                            </c:forEach>
                        </div><!--end .accordion-->
                    </div>
                </section>
            </div>