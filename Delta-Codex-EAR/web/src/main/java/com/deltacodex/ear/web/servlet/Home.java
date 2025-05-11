package com.deltacodex.ear.web.servlet;

import com.deltacodex.ear.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet {

    @EJB
    private ProductService service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h1>My Product listing</h1>");
        service.getAllProducts().forEach(product -> {
            try {
                response.getWriter().write(product.getName() + "<br>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
