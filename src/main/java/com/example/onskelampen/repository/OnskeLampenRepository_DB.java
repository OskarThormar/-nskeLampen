/*
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
}
*/
