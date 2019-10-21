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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.net.aso.i;

/**
 *
 * @author wy105
 */
@WebServlet(name = "DPShowTree", urlPatterns = {"/Admin/DPShowTree"})
public class DPShowTree extends HttpServlet {

    List<Itmstr> itmstrs1 = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String itmId = request.getParameter("itmId");
        String supId = request.getParameter("supId");
        String json = "", json1 = "";
        String findTreeTime = "";
        long startTime = 0, endTime = 0;

        if (itmId != null && !itmId.equals("")) {
            startTime = System.currentTimeMillis();
            Itmstr itmstr = DaoFactory.GetItmstrDaoImp().GetAItmstr(itmId);
            itmstrs1 = DaoFactory.GetItmstrDaoImp().GetItmstrTree(itmId);

            request.setAttribute("itmId", itmId);
            request.setAttribute("itmstr", itmstr);

            if (supId != null && !supId.equals("")) {
                startTime = System.currentTimeMillis();

                // 过滤叶子 第一种方法 从低向上排查 记录前一个对象 比较叶/根进行梳理
                boolean hasChild = false;
                Itmstr itmstrTemp = itmstrs1.get(itmstrs1.size() - 1);
                List<Itmstr> isSupItmstrs = new ArrayList<>();
                for (int i = itmstrs1.size() - 1; i >= 0; i--) {
                    Itmstr itmstrTempEX = itmstrs1.get(i);
                    if (itmstrs1.get(i).getSupId().equals(supId)) {
                        hasChild = true;
                        isSupItmstrs.add(itmstrs1.get(i));
                    } else {
                        if (!itmstrs1.get(i).getItmId().equals(itmstrTemp.getItmPid())) {
                            if (!itmstrs1.get(i).getItmPid().equals(itmstrTemp.getItmPid())) {
                                itmstrs1.remove(i);
                                hasChild = false;
                            } else {
                                itmstrs1.remove(i);
                                if (!hasChild) {
                                    hasChild = false;
                                }
                            }
                        } else {
                            for (Itmstr supItmstr : isSupItmstrs) {
                                if (supItmstr.getItmPid().equals(itmstrs1.get(i).getItmId())) {
                                    hasChild = true;
                                    break;
                                }
                            }
                            if (!hasChild) {
                                itmstrs1.remove(i);
                                hasChild = false;
                            } else {
                                isSupItmstrs.add(itmstrs1.get(i));
                            }
                        }
                    }
                    itmstrTemp = itmstrTempEX;
                }

//                while (true) {
//                    for (int i = itmstrs1.size() - 1; i >= 0; i--) {
//                        if (!itmstrs1.get(i).getSupId().equals(supId)) {
//                            boolean canRemove = true;
//                            for (int j = itmstrs1.size() - 1; j >= 0; j--) {
//                                if (itmstrs1.get(i).getItmId().equals(itmstrs1.get(j).getItmPid())) {
//                                    canRemove = false;
//                                    break;
//                                }
//                            }
//                            if (canRemove) {
//                                itmstrs1.remove(i);
//                            }
//                        }
//                    }
//
//                    boolean canBreak = true;
//
//                    for (int k = itmstrs1.size() - 1; k >= 0; k--) {
//                        boolean isLeaf = true;
//                        for (int v = itmstrs1.size() - 1; v >= 0; v--) {
//                            if (itmstrs1.get(k).getItmId().equals(itmstrs1.get(v).getItmPid())) {
//                                isLeaf = false;
//                                break;
//                            }
//                        }
//                        if (isLeaf) {
//                            if (!itmstrs1.get(k).getSupId().equals(supId)) {
//                                canBreak = false;
//                            }
//                        }
//                    }
//
//                    if (canBreak) {
//                        break;
//                    }
//                }
                endTime = System.currentTimeMillis();
                findTreeTime = String.valueOf((endTime - startTime) / 1000.000);
                request.setAttribute("findTreeTime", findTreeTime);

                json1 = DaoFactory.GetItmstrDaoImp().showTree(itmstrs1);
                request.setAttribute("itmstrs1", json1);
                request.setAttribute("supId", supId);
            } else {
                endTime = System.currentTimeMillis();
                findTreeTime = String.valueOf((endTime - startTime) / 1000.000);
                request.setAttribute("findTreeTime", findTreeTime);

                json1 = DaoFactory.GetItmstrDaoImp().showTree(itmstrs1);
                request.setAttribute("itmstrs1", json1);
            }
        }

        List<Itmstr> itmstrs = DaoFactory.GetItmstrDaoImp().GetAllItmstr();
        json = DaoFactory.GetItmstrDaoImp().showTree(itmstrs);

        request.setAttribute("itmstrs", json);
        request.getRequestDispatcher("/admin/DPShowTree.jsp").forward(request, response);

    }

    public List<Itmstr> GetTreeWithSupId(Itmstr itmstr, String supId) {
        for (int i = 0; i < itmstrs1.size(); i++) {
            List<Itmstr> childItmstrs = DaoFactory.GetItmstrDaoImp().GetChildItmstr(itmstrs1.get(i).getItmId());
            if (childItmstrs != null) {
                GetTreeWithSupId(itmstrs1.get(i), supId);
                if (!itmstr.getSupId().equals(supId)) {
                    itmstrs1.remove(itmstr);
                }
            }
        }
        return itmstrs1;
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
