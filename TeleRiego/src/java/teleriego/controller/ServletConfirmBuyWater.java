/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teleriego.model.Land;
import teleriego.model.Membership;
import teleriego.model.viewbean.LandFacade;
import teleriego.model.viewbean.MembershipFacade;
import teleriego.model.viewbean.TransactionFacade;

/**
 *
 * @author inftel11
 */
@WebServlet(name = "ServletConfirmBuyWater", urlPatterns = {"/ServletConfirmBuyWater"})
public class ServletConfirmBuyWater extends HttpServlet {
    @EJB
    private MembershipFacade membershipFacade;
    @EJB
    private LandFacade landFacade;
    @EJB
    private TransactionFacade transactionFacade;

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
        if (request.getSession().getAttribute("memberNumber") == null) {
            request.getRequestDispatcher("WEB-INF/Pages/Login.jsp").forward(request, response);
            return;
        }
        
        double quantity = Integer.parseInt(request.getParameter("quantity"));
        int landIdInteger = Integer.parseInt(request.getParameter("landId"));
        BigDecimal landId = new BigDecimal(landIdInteger);
        Land specificLand = landFacade.getLand(landId);
        request.setAttribute("specificLand", specificLand);
        BigDecimal memberNumber = (BigDecimal) request.getSession().getAttribute("memberNumber");
        Membership membership = membershipFacade.getMembership(memberNumber);
        request.setAttribute("membership", membership);
        transactionFacade.setTransaction(landId,memberNumber,quantity);
        double total = quantity * 0.22;
        request.setAttribute("total", total);
        
        request.getRequestDispatcher("WEB-INF/Pages/BuyWater.jsp?confirmedBuyWater=true").forward(request, response);
   
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
