/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.servlet.admin;

import com.dongpu.model.Sup;
import com.dongpu.util.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author wy105
 */
@WebServlet(name = "DPSupManage", urlPatterns = {"/Admin/DPSupManage"})
public class DPSupManage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String supId = request.getParameter("supId");
        List<Sup> sups = new ArrayList<>();

        if (supId != null && !"".equals(supId)) {
            Sup sup = DaoFactory.GetSupDaoImp().GetASup(supId);
            if (sup != null) {
                sup.setSupIsParents(JudgeSupIsUsed(sup));
                sups.add(sup);
            }
        } else {
            sups = DaoFactory.GetSupDaoImp().GetAllSup();
            for (Sup supItem : sups) {
                supItem.setSupIsParents(JudgeSupIsUsed(supItem));
            }
        }
        request.setAttribute("sups", sups);
        request.getRequestDispatcher("/admin/DPSupManage.jsp").forward(request, response);

    }

    private String JudgeSupIsUsed(Sup sup) {
        if (DaoFactory.GetItmstrDaoImp().GetItmstrsBySupId(sup.getSupId()) != null
                && !DaoFactory.GetItmstrDaoImp().GetItmstrsBySupId(sup.getSupId()).isEmpty()) {
            return "true";
        } else {
            return "false";
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
