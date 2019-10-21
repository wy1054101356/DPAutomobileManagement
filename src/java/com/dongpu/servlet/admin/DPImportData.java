/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.servlet.admin;

import com.dongpu.model.Itmstr;
import com.dongpu.util.DaoFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author wy105
 */
@WebServlet(name = "DPImportData", urlPatterns = {"/Admin/DPImportData"})
public class DPImportData extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 300;  // 50MB
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 350; // 100MB
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 400; // 300MB
    private static final Integer ONE = 1;
    // 默认路径
    private String saveFilePath = "";

    public BufferedReader br = null;
    public String line = null;
    public Itmstr itmstr = null;
    public List<Itmstr> itmstrs = new LinkedList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        saveFilePath = filePath;
                        item.write(storeFile);// 保存文件到硬盘
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("写入文件失败");
        }

        String useTime = readFile(saveFilePath);
        request.setAttribute("useTime", useTime);
        request.getRequestDispatcher("/admin/DPImportData.jsp").forward(request, response);

    }

    public String readFile(String filePath) {
        long startTime, endTime;
        startTime = System.currentTimeMillis();

        try {
            //根据文件路径创建缓冲输入流
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(filePath)), "UTF-8"));
            String str = "";
            long itmstrCount = 0;
            //循环读取文件的每一行，对需要修改的行进行修改，放入缓冲对象中
            while ((line = br.readLine()) != null) {
                //设置正则将多余空格都转为一个空格
                str = line + "\r\n";
                String[] dictionary = str.split("   ");

                String itmId = dictionary[0].trim();
                String itmPid = dictionary[1].trim();
                String itmName = dictionary[2].trim();
                int seq = Integer.valueOf(dictionary[3].trim());
                int itmCount = Integer.valueOf(dictionary[4].trim());
                String supId = dictionary[5].trim();

                itmstr = new Itmstr(itmId, itmPid, itmName, seq, itmCount, supId);
                itmstrs.add(itmstr);
                itmstr = null;
                itmstrCount++;

                if ((itmstrCount) % 11000 == 0) {
                    DaoFactory.GetItmstrDaoImp().ProcessInsert(itmstrs);
                    itmstrs.clear();
                }
            }
            
            DaoFactory.GetItmstrDaoImp().ProcessInsert(itmstrs);
            itmstrs.clear();
            
            endTime = System.currentTimeMillis();
            return "共" + itmstrCount +"条数据 运行时间：" + String.valueOf((endTime - startTime) / 1000.0000) + "秒";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {// 关闭流
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        return "操作失败";
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

    class ReadAndInsert implements Runnable {

        @Override
        public void run() {

        }
    }

}
