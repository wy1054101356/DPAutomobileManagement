/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.servlet.admin;

import com.dongpu.model.Itmstr;
import com.dongpu.model.Sup;
import com.dongpu.util.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wy105
 */
@WebServlet(name = "DPItmstrInsert", urlPatterns = {"/Admin/DPItmstrInsert"})
public class DPItmstrInsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String canInsert = request.getParameter("canInsert");
        if (canInsert != null) {
            if (canInsert.equals("false")) {
                request.setAttribute("canInsert", canInsert);
            }
        }
        
        List<Itmstr> itmstrs = new ArrayList<>();
        itmstrs = DaoFactory.GetItmstrDaoImp().GetAllItmstr();

        String itmId = "";

//        //定义时间
//        Date date = new Date(System.currentTimeMillis());
//        String pattern = "yyyy-MM-dd-HH-mm-ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        String constructOrderDate = sdf.format(date);
//
//        //获取订单ID 随机的 根据 日期 + 随机数
//        itmId = "ITMSTR" + constructOrderDate.replace("-", "");
//
//        String randomStr = UUID.randomUUID().toString().replace("-", "");
//        for (int i = 0; i < 10; i++) {
//            itmId += randomStr.charAt((int) (Math.random() * randomStr.length()));
//        }
        List<Sup> sups = new ArrayList<>();
        sups = DaoFactory.GetSupDaoImp().GetAllSup();

        request.setAttribute("itmId", itmId);
        request.setAttribute("itmstrs", itmstrs);
        request.setAttribute("sups", sups);
        request.getRequestDispatcher("/admin/DPItmstrInsert.jsp").forward(request, response);

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
