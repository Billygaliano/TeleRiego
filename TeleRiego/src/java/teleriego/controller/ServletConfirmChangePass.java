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
import teleriego.model.Membership;
import teleriego.model.viewbean.MembershipFacade;

/**
 *
 * @author inftel11
 */
@WebServlet(name = "ServletConfirmChangePass", urlPatterns = {"/ServletConfirmChangePass"})
public class ServletConfirmChangePass extends HttpServlet {
    @EJB
    private MembershipFacade membershipFacade;
    

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
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
     
        BigDecimal memberNumber = (BigDecimal) request.getSession().getAttribute("memberNumber");
        Boolean correctUpate = membershipFacade.changePassword(oldPass, newPass, memberNumber);
        Membership membership = membershipFacade.getMembership(memberNumber);
        request.setAttribute("membership", membership);
        
        if(membership.getRole().equalsIgnoreCase("administrador") && correctUpate == true){
                request.setAttribute("correctUpdate", correctUpate);
                request.getRequestDispatcher("WEB-INF/Pages/ProfileAdmin.jsp?changepass=true").forward(request, response);
                return;
        }else if(membership.getRole().equalsIgnoreCase("administrador") && correctUpate == false){
            request.setAttribute("incorrectUpdate", true);
            request.getRequestDispatcher("WEB-INF/Pages/ProfileAdmin.jsp?changepass=true").forward(request, response);
                return;
            }
        else if(correctUpate==true){
            request.setAttribute("correctUpdate", correctUpate);
            request.getRequestDispatcher("WEB-INF/Pages/Profile.jsp?changepass=true").forward(request, response);
        }
        else{
            request.setAttribute("incorrectUpdate", true);
            request.getRequestDispatcher("WEB-INF/Pages/Profile.jsp?changepass=true").forward(request, response);
        }
        
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
