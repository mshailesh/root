package com.shailesh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DemoCommandLineRunner implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(DemoCommandLineRunner.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(DemoCommandLineRunner.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws ClassNotFoundException {

        String sqlSelectAllCountry = "SELECT * FROM country";
        String connectionUrl = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";
      //  Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false","root","root");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllCountry);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long population = rs.getLong("Population");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                LOG.info(String.valueOf(population), name, continent);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            // handle the exception
        }



    }
}
