/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project_tools.servlets;

import com.mycompany.project_tools.helpers.DatabaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ildi
 */
@WebServlet(name = "NewBookServlet", urlPatterns = {"/NewBookServlet"})
public class NewBookServlet extends HttpServlet {

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
        System.out.println("Megpróbálunk könyvet hozzáadni..");
      
        response.setContentType("text/html;charset=UTF-8");
        
        String title = request.getParameter("title_input");
        String author = request.getParameter("author_input");
        String publisher = request.getParameter("publisher_input");
        String desc = request.getParameter("description_input");
        System.out.println("itt");
        String category = request.getParameter("category_input");
        String year = request.getParameter("year_input");
        System.out.println("szervlet");
        try {
            DatabaseHelper.insertBook(title, author, publisher, desc, category, year);
            System.out.println("meghívva");
        } catch (SQLException ex) {
            Logger.getLogger(RegistServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("main.jsp");
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
