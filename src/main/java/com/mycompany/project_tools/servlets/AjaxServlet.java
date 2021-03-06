/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project_tools.servlets;

import com.mycompany.project_tools.helpers.DatabaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ildi
 */
@WebServlet(name = "AjaxServlet", urlPatterns = {"/AjaxServlet"})
public class AjaxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        JSONObject res = new JSONObject();

        String cmd = request.getParameter("cmd");
        String cmd2 = request.getParameter("cmd2");
        String id = request.getParameter("id");
        String username = request.getParameter("username");

        if (cmd != null) {
            if (cmd.equals("getCategories")) {
                JSONArray categories;
                categories = DatabaseHelper.getCategories();
                res.put("categories", categories);
                res.put("error", "0");
            }
            if (cmd2 != null) {
                if (cmd2.equals("getBooks")) {
                    JSONArray books;
                    books = DatabaseHelper.getBooks();
                    res.put("books", books);
                }
            }
            if (cmd.equals("getBookDetails")) {
                JSONArray book;
                book = DatabaseHelper.getBookDetails(id);
                res.put("books", book);
            }
            if(cmd.equals("insertToRead")){
                DatabaseHelper.insertToRead(id, username);
            }
            if(cmd.equals("insertToBorrows")){
                DatabaseHelper.insertToBorrow(id, username);
            }
            if(cmd.equals("insertToRate")){
                int pontszam = Integer.parseInt(request.getParameter("point"));
                DatabaseHelper.insertToRate(id, username, pontszam);
            }
            if(cmd.equals("renewuser")){
                int kolcs_id = Integer.parseInt(request.getParameter("kolcs_id"));
                DatabaseHelper.setStatus(kolcs_id, "hosszabbit");
            }
            if(cmd.equals("renewadmin")){
                int kolcs_id = Integer.parseInt(request.getParameter("kolcs_id"));
                System.out.println("admin hosszabbit");
                DatabaseHelper.renew(kolcs_id);                
            }
            if(cmd.equals("returnbook")){
                int kolcs_id = Integer.parseInt(request.getParameter("kolcs_id"));
                DatabaseHelper.setStatus(kolcs_id, "visszahozott");
            }
            if(cmd.equals("search")){
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                int category_id=Integer.parseInt(request.getParameter("category_id"));
                JSONArray sbooks;
                sbooks = DatabaseHelper.search(title, author, category_id);
                res.put("sbooks", sbooks);
            }
            if(cmd.equals("getBorrows")){
                JSONArray borrows;
                borrows = DatabaseHelper.getBorrows(username);
                res.put("borrows", borrows);
            }
            if(cmd.equals("getCategoryDetails")){
                String category_id=request.getParameter("category_id");
                JSONArray sbooks;
                sbooks = DatabaseHelper.search(category_id);
                res.put("sbooks", sbooks);
            }
            if(cmd.equals("deleteBorrow")){
                DatabaseHelper.deleteBorrow(id);
            }
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(res.toString());
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
