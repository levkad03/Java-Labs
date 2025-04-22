package org.example.webapp;

import org.example.models.Cat;
import org.example.manager.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cat> cats = DatabaseManager.getAllCats();

        request.setAttribute("cats", cats);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cats.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String catName = request.getParameter("catName");
        if (catName != null && !catName.trim().isEmpty()) {
            Cat newCat = new Cat(catName);
            DatabaseManager.addCat(newCat);
        }

        request.setAttribute("catName", catName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/catSaved.jsp");
        dispatcher.forward(request, response);
    }
}
