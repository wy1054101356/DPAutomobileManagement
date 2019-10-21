/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.servlet;

import com.dongpu.model.Itmstr;
import com.dongpu.util.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wy105
 */
@WebServlet(name = "GetChildTreeServer", urlPatterns = {"/Admin/GetChildTreeServer"})
public class GetChildTreeServer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String choiseItmId = request.getParameter("choiseItmId");
        String choiseItmPid = "";
        String choiseItmName = "";
        String choiseSeq = "";
        String choiseItmCount = "";
        String choiseSupId = "";

        Itmstr itmstr = DaoFactory.GetItmstrDaoImp().GetAItmstr(choiseItmId);
        choiseItmPid = itmstr.getItmPid();
        choiseItmName = itmstr.getItmName();
        choiseSeq = String.valueOf(itmstr.getSeq());
        choiseItmCount = String.valueOf(itmstr.getItmCount());
        choiseSupId = itmstr.getSupId();
//
//        String choiseJson = "{"
//                + "choiseItmId:\"" + choiseItmId + "\","
//                + "\"choiseItmPid\":\"" + choiseItmPid + "\","
//                + "\"choiseItmName\":\"" + choiseItmName + "\","
//                + "\"choiseSeq\":\"" + choiseSeq + "\","
//                + "\"choiseItmCount\":\"" + choiseItmCount + "\","
//                + "\"choiseSupId\":\"" + choiseSupId + "\""
//                + "}";
//        response.setContentType("text/json");

        String choiseJson = choiseItmId + "@@@"
                + choiseItmPid + "@@@"
                + choiseItmName + "@@@"
                + choiseSeq + "@@@"
                + choiseItmCount + "@@@"
                + choiseSupId;

        try {
            out.write(choiseJson);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
