package com.example.onskelampen;

import com.example.onskelampen.model.IncorrectWishIDException;
import com.example.onskelampen.model.User;
import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.repository.OnskeLampenRepository;
import com.example.onskelampen.repository.OnskeLampenRepository_DB;
import com.example.onskelampen.repository.UserRepository;
import com.example.onskelampen.repository.UserRepository_DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"mysql"})
class IntergrationTest { //name prevent running on github

    @Autowired
    OnskeLampenRepository_DB repository;

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    @BeforeEach
    public void setup() {
        try (Connection conn = DriverManager.getConnection(db_url, uid, pwd)) {
            Statement stmt = conn.createStatement();

            stmt.addBatch("DROP TABLE IF EXISTS onsker");
            stmt.addBatch("DROP TABLE IF EXISTS bruger");

            stmt.addBatch("CREATE TABLE onsker (" +
                    "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "oname VARCHAR(100)," +
                    "odescription VARCHAR(100)," +
                    "oprice INTEGER," +
                    "oamount INTEGER," +
                    "olink VARCHAR(100)," +
                    "userid INTEGER," +
                    "FOREIGN KEY (userid) REFERENCES bruger(userid)" +
                    ")");
            stmt.addBatch("CREATE TABLE bruger (" +
                    "userid INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(100)," +
                    "userpassword VARCHAR(100)" +
                    ")");

            stmt.addBatch("INSERT INTO onsker (oname, odescription, oprice, oamount, olink, userid) " +
                    "VALUES ('computer', 'til at spille spil', 8000, 1, 'computersalg', 1)");
            stmt.addBatch("INSERT INTO onsker (oname, odescription, oprice, oamount, olink, userid) " +
                    "VALUES ('bil', 'til at køre', 48000, 1, 'børnevenlig', 2)");

            stmt.addBatch("INSERT INTO bruger (username, userpassword) VALUES ('Oskar', '1234')");
            stmt.addBatch("INSERT INTO bruger (username, userpassword) VALUES ('Mikkel', '1234')");
            stmt.addBatch("INSERT INTO bruger (username, userpassword) VALUES ('Jesper', '1234')");

            stmt.executeBatch();

            System.out.println("Database setup complete.");
        } catch (SQLException e) {
            System.out.println("Database call went wrong: " + e.getMessage());
        }
    }
    @Test
    void testFindOnskeListe() {
        int userid = 1;
        List<OnskeLampen> found = repository.showList(userid);
        assertFalse(found.isEmpty(), "List of wishes is empty");
    }

    @Test
    void testDeleteOnske() {
        repository.deleteWish(1);
        assertNull(repository.getWishById(1), "Wish was not deleted");
    }

    @Test
    void testFindOnskeById() {
        OnskeLampen found = repository.getWishById(1);
        assertNotNull(found, "Wish with id 1 not found");
    }

    @Test
    void testDeleteNonExistingOnske() {
        assertThrows(IncorrectWishIDException.class, () -> repository.deleteWish(999));
    }
}