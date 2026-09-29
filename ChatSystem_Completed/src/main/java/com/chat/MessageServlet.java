package com.chat;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/messages")
public class MessageServlet extends HttpServlet {
    private static String json(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r", "\\r").replace("\n", "\\n");
    }

    private String currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : (String) session.getAttribute("username");
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sender = currentUser(request);
        if (sender == null) { response.setStatus(401); return; }
        String receiver = request.getParameter("receiver");
        String message = request.getParameter("message");
        if (receiver == null || message == null || receiver.isBlank() || message.isBlank()) { response.setStatus(400); return; }
        String sql = "INSERT INTO messages(sender, receiver, message) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sender); ps.setString(2, receiver.trim()); ps.setString(3, message.trim()); ps.executeUpdate();
            response.setContentType("application/json;charset=UTF-8"); response.getWriter().print("{\"success\":true}");
        } catch (Exception e) { getServletContext().log("Send message error", e); response.setStatus(500); }
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String current = currentUser(request);
        if (current == null) { response.setStatus(401); return; }
        String other = request.getParameter("receiver");
        if (other == null || other.isBlank()) { response.setStatus(400); return; }
        String sql = "SELECT sender, receiver, message, DATE_FORMAT(message_time, '%Y-%m-%d %H:%i:%s') AS message_time FROM messages WHERE (sender=? AND receiver=?) OR (sender=? AND receiver=?) ORDER BY id ASC";
        response.setContentType("application/json;charset=UTF-8");
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, current); ps.setString(2, other.trim()); ps.setString(3, other.trim()); ps.setString(4, current);
            try (ResultSet rs = ps.executeQuery()) {
                StringBuilder out = new StringBuilder("["); boolean first = true;
                while (rs.next()) {
                    if (!first) out.append(','); first = false;
                    out.append("{\"sender\":\"").append(json(rs.getString("sender"))).append("\",")
                       .append("\"receiver\":\"").append(json(rs.getString("receiver"))).append("\",")
                       .append("\"message\":\"").append(json(rs.getString("message"))).append("\",")
                       .append("\"time\":\"").append(json(rs.getString("message_time"))).append("\"}");
                }
                out.append(']'); response.getWriter().print(out);
            }
        } catch (Exception e) { getServletContext().log("Retrieve messages error", e); response.setStatus(500); }
    }
}
