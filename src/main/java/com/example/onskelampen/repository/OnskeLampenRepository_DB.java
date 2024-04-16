package com.example.onskelampen.repository;

import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class OnskeLampenRepository_DB {
    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String uid;
    @Value("${spring.datasource.password}")
    String pwd;

    public List<OnskeLampen> showList() {
        List<OnskeLampen> onsker = new ArrayList<OnskeLampen>();
        String SQL = "SELECT * FROM onsker;";
// singleton
        Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
        try (Statement stmt = con.createStatement();
// ikke nødvendig i try, da stmt.close også lukker Resultset
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                int ID = rs.getInt("id");
                String name = rs.getString("oname");
                String description = rs.getString("odescription");
                int price = rs.getInt("oprice");
                int amount = rs.getInt("oamount");
                String link = rs.getString("olink");
                onsker.add(new OnskeLampen(ID, name, description, price, amount, link));
            }
            return onsker;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OnskeLampen addWish(OnskeLampen onske) {
        String SQL = "INSERT INTO onsker(oname,odescription,oprice,oamount,olink) VALUES (?, ?, ?, ?, ?);";
// singleton
        Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setString(1, onske.getName());
            pstmt.setString(2, onske.getDescription());
            pstmt.setDouble(3, onske.getPrice());
            pstmt.setInt(4, onske.getAmount());
            pstmt.setString(5, onske.getLink());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return onske;
    }

    public boolean deleteWish(int id) {
        int rows = 0; // Antal rækker der er ændret
        String SQL = "DELETE FROM onsker WHERE ID = ?;";
// singleton
        Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
        try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
// Returnerer true hvis en række er blevet slettet, ellers false
        return rows == 1;
    }

    public OnskeLampen changeWish(OnskeLampen onske) {
        int rows = 0; // antal rækker der er ændret
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "UPDATE onsker SET oname = ?, odescription = ?, oprice = ?, oamount = ?, olink = ? WHERE ID = ? ;";
            PreparedStatement pstmt = con.prepareStatement(SQL);

            pstmt.setString(1, onske.getName());
            pstmt.setString(2, onske.getDescription());
            pstmt.setDouble(3, onske.getPrice());
            pstmt.setInt(4, onske.getAmount());
            pstmt.setString(5, onske.getLink());
            pstmt.setInt(6, onske.getId());

            rows = pstmt.executeUpdate();
            System.out.println("Rows: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rows == 1) // onske fundet og opdateret
            return onske;
        else
            return null;
    }

    public OnskeLampen getWishById(int id) {
        String sql = "SELECT * FROM onsker WHERE id = ?";
        OnskeLampen oenskeFind = null;
        try (Connection connection = DriverManager.getConnection(db_url, uid, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Opret et nyt OnskeLampen objekt med data fra databasen
                oenskeFind = new OnskeLampen();
                oenskeFind.setId(resultSet.getInt("id"));
                oenskeFind.setName(resultSet.getString("oname"));
                oenskeFind.setDescription(resultSet.getString("odescription"));
                oenskeFind.setPrice(resultSet.getDouble("oprice"));
                oenskeFind.setAmount(resultSet.getInt("oamount"));
                oenskeFind.setLink(resultSet.getString("olink"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oenskeFind;
    }
}

