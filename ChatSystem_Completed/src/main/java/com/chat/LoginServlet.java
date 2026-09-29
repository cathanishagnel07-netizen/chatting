package com.chat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            response.sendRedirect("login.jsp?error=Please enter username and password"); return;
        }
        String sql = "SELECT id FROM users WHERE username=? AND password=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username.trim()); ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", username.trim());
                    response.sendRedirect("chat.jsp");
                } else response.sendRedirect("login.jsp?error=Invalid username or password");
            }
        } catch (Exception e) {
            getServletContext().log("Login database error", e);
            response.sendRedirect("login.jsp?error=Unable to connect to database");
        }
    }
}
