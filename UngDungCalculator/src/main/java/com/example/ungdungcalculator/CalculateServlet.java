package com.example.ungdungcalculator;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CalculateServlet", value = "/calculate")
public class CalculateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double n1 = Double.parseDouble(req.getParameter("n1"));
        double n2 = Double.parseDouble(req.getParameter("n2"));
        double result = 0;
        String message = "";
        String operator = req.getParameter("operator");

        if(operator == null){
            operator = "";
        }

        switch (operator){
            case "addition" :
                result = n1+n2;
                break;
            case "subtraction":
                result = n1 - n2;
                break;
            case "multiplication":
                result = n1 * n2;
                break;
            case "division":
                if(n2 == 0){
                    message = "Không thể chia cho 0";
                }else {
                    result = n1/n2;
                }
                break;
            default:
                message= " Vui lòng chọn loại máy tính";
        }

        req.setAttribute("result", result);
        req.setAttribute("message", message);

        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}