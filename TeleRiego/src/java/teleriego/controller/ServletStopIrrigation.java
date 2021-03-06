/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teleriego.model.client.WeatherClient;
import teleriego.model.Land;
import teleriego.model.Membership;
import teleriego.model.rs.Recommendation;
import teleriego.model.viewbean.LandFacade;
import teleriego.model.viewbean.MembershipFacade;

/**
 *
 * @author inftel12
 */
@WebServlet(name = "ServletStopIrrigation", urlPatterns = {"/ServletStopIrrigation"})
public class ServletStopIrrigation extends HttpServlet {
    @EJB
    private MembershipFacade membershipFacade;
    @EJB
    private LandFacade landFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getSession().getAttribute("memberNumber")==null){
            request.getRequestDispatcher("WEB-INF/Pages/Login.jsp").forward(request, response);
            return;
        }
        
        int lnadIdInteger = Integer.parseInt(request.getParameter("landId"));
        BigDecimal landId = new BigDecimal(lnadIdInteger);
        Land specificLand = landFacade.getLand(landId);
        Recommendation recommendation = new Recommendation();
        
        landFacade.updateStateLand(landId, "parado");
        
        WeatherClient weatherClient = new WeatherClient();
        JsonArray wsResult = weatherClient.findAll_JSON(JsonArray.class);
        
        Boolean needIrrigate = recommendation.suggestIrrigation(specificLand.getHumidity(), specificLand.getLastDateIrrigation(), specificLand.getWMAvailable(), wsResult);
        
        BigDecimal memberNumber = (BigDecimal) request.getSession().getAttribute("memberNumber");
        Membership membership = membershipFacade.getMembership(memberNumber);        
        request.setAttribute("membership", membership);
        request.setAttribute("specificLand", specificLand);
        request.setAttribute("weatherPrediction", wsResult);
        request.setAttribute("needIrrigate", needIrrigate);
        request.setAttribute("land", true);
        request.setAttribute("stateIrrigation", landFacade.getStateIrrigateString(landId));
        
        request.getRequestDispatcher("WEB-INF/Pages/Land.jsp").forward(request, response);      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
