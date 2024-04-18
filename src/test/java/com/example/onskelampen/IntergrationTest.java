package com.example.onskelampen;

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
class OnskeLampenIntergrationTest { //name prevent running on github

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
        try (
                Connection conn =
                        DriverManager.getConnection(db_url, uid, pwd)) {
            Statement stmt = conn.createStatement();

            String initSchema = "CREATE SCHEMA IF NOT EXISTS EMP_DEPT";
            String dropTableEmp = "drop table if exists emp";
            String dropTableDept = "drop table if exists dept";
            stmt.addBatch(initSchema);
            stmt.addBatch(dropTableEmp);
            stmt.addBatch(dropTableDept);
            String createTable = "CREATE TABLE DEPT " +
                    "(deptno INTEGER, " +
                    " dname VARCHAR(30), " +
                    " loc VARCHAR(30), " +
                    " PRIMARY KEY ( deptno ))";
            stmt.addBatch(createTable);
            stmt.executeBatch();
            System.out.println("Database created");

            String sqlInsertRow = "INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK')";
            stmt.addBatch(sqlInsertRow);
            sqlInsertRow = "INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS')";
            stmt.addBatch(sqlInsertRow);
            sqlInsertRow = "INSERT INTO DEPT VALUES (30,'SALES','CHICAGO')";
            stmt.addBatch(sqlInsertRow);
            sqlInsertRow = "INSERT INTO DEPT VALUES(40,'OPERATIONS','BOSTON')";
            stmt.addBatch(sqlInsertRow);
            int rows[] = stmt.executeBatch();
            System.out.println("Inserted " + rows.length + " records into the table");
        } catch (SQLException e) {
            System.out.println("Database call went wrong" + e.getMessage());
        }
    }
}