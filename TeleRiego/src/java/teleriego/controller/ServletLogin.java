/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teleriego.model.Membership;
import teleriego.viewbean.LoginViewBean;


/**
 *
 * @author inftel12
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    @PersistenceContext(unitName = "TeleRiegoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private LoginViewBean loginViewBean;

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
       
        if(request.getSession().getAttribute("membership")!=null){
            request.setAttribute("profile", true);
            request.getRequestDispatcher("WEB-INF/Pages/Profile.jsp").forward(request, response);
            return;
        }
        
        if(request.getParameter("user")==null || request.getParameter("password")==null){
            request.getRequestDispatcher("WEB-INF/Pages/Login.jsp").forward(request, response);
            return;
        }
        
        int memberUserInteger = Integer.parseInt(request.getParameter("user"));
        BigDecimal memberNumber = new BigDecimal(memberUserInteger);                           
        Membership membership = em.find(Membership.class, memberNumber);
        if(membership==null){
            request.getRequestDispatcher("WEB-INF/Pages/Login.jsp?errorPassword=true").forward(request, response);
            return;
        }
        
        String password = request.getParameter("password");
        String passwordDB = membership.getPassword(); 
        if(!loginViewBean.autentication(passwordDB, password)){
            request.getRequestDispatcher("WEB-INF/Pages/Login.jsp?errorPassword=true").forward(request, response);
            return;
        }
        
        request.getSession().setAttribute("membership", membership);
        request.setAttribute("profile", true);
        request.getRequestDispatcher("WEB-INF/Pages/Profile.jsp").forward(request, response);

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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        loginViewBean = new LoginViewBean();
    }

}
