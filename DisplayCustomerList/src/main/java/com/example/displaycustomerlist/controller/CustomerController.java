package com.example.displaycustomerlist.controller;

import com.example.displaycustomerlist.entity.Customer;
import com.example.displaycustomerlist.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,   // 1MB
        maxFileSize = 1024 * 1024 * 5,      // 5MB
        maxRequestSize = 1024 * 1024 * 10   // 10MB
)
@WebServlet (name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "add":
                req.getRequestDispatcher("/view/customer/add.jsp").forward(req, resp);
                break;
            case "search":
                break;
            default:
                showListRequest(req, resp);
        }


    }

    private void showListRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customerList", customerService.findAll());
        req.getRequestDispatcher("/view/customer/list.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            action = "";
        }

        switch (action){
            case "add":
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = null;
                try {
                    birthday = sdf.parse(req.getParameter("birthday"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Part filePart = req.getPart("image");
                String fileName = filePart.getSubmittedFileName();

                String uploadPath = getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);

                if(!uploadDir.exists()){
                    uploadDir.mkdir();
                }

                filePart.write(uploadPath + File.separator + fileName);
                Customer customer = new Customer(
                        name,
                        birthday,
                        address,
                        fileName
                );

                boolean isAddSuccess =  customerService.add(customer);
                String mess = isAddSuccess ? "Successed" : "Add failed";

                resp.sendRedirect("/customer?mess="+mess);
                break;
            case "delete":
                break;

        }
    }
}
