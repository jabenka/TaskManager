package com.zxcjabka.notoficationservice.misc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Component
public class UserEmailFetcher {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/taskmanager";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1488";

    public String fetchUserEmail(Long id) {
        String email=null;
        String sql = "SELECT email FROM tasks.user WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }

        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при запросе к БД: " + e.getMessage());
        }
        return email;
    }
}
