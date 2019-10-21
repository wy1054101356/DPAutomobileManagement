/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.servlet.admin;

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
@WebServlet(name = "DPItmstrInsertDao", urlPatterns = {"/Admin/DPItmstrInsertDao"})
public class DPItmstrInsertDao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Itmstr itmstr = null;
        Itmstr itmstrItem = null;
        String itmId = request.getParameter("itmId");
        String itmPid = request.getParameter("itmPid");
        String itmName = request.getParameter("itmName");
        int seq = Integer.valueOf(request.getParameter("seq"));
        int itmCount = Integer.valueOf(request.getParameter("itmCount"));
        String supId = request.getParameter("supId");

        itmstr = new Itmstr(itmId, itmPid, itmName, seq, itmCount, supId);
        itmstrItem  = new Itmstr(itmId, itmPid, itmName, seq, itmCount, supId);
        String canInsert = "true";

        while (!itmstrItem.getItmPid().equals("NULL")) {
            itmstrItem = DaoFactory.GetItmstrDaoImp().GetAItmstr(itmstrItem.getItmPid());
            if (itmstr.getItmId().equals(itmstrItem.getItmId())) {
                canInsert = "false";
            }
        }

        if (canInsert.equals("true")) {
            boolean isInsert = DaoFactory.GetItmstrDaoImp().InsertItmstr(itmstr);
            if (isInsert) {
                response.sendRedirect(request.getContextPath() + "/Admin/DPItmstrManage");
            } else {
                response.sendRedirect(request.getContextPath() + "/Admin/DPItmstrManage");
            }
        } else {
            request.setAttribute("canInsert", canInsert);
            request.getRequestDispatcher("/Admin/DPItmstrInsert").forward(request, response);
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
