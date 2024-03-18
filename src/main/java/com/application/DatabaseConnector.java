package com.application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private Connection connection;
    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password123");
            if(connection.isValid(10)) {
                System.out.println("Connected to database successfully");
            }
        } catch (Exception ex) {
            System.err.println("An error occurred while connecting to the database: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

//    public class DatabaseHandler {
//        private static final String URL = "jdbc:mysql://localhost:3306/library";
//        private static final String USERNAME = "root";
//        private static final String PASSWORD = "password123";
//
//        public void insertBook(String title, String author, String isbn) {
//            String insertPublicationSQL = "INSERT INTO Publication (title, publication_date) VALUES (?, NOW())";
//            String insertBookSQL = "INSERT INTO Book (library_id, isbn) VALUES (LAST_INSERT_ID(), ?)";
//            String insertAuthorSQL = "INSERT INTO Author (library_id, author_name) VALUES (LAST_INSERT_ID(), ?)";
//
//            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                 PreparedStatement publicationStatement = connection.prepareStatement(insertPublicationSQL);
//                 PreparedStatement bookStatement = connection.prepareStatement(insertBookSQL);
//                 PreparedStatement authorStatement = connection.prepareStatement(insertAuthorSQL)) {
//
//                // Insert into Publication table
//                publicationStatement.setString(1, title);
//                publicationStatement.executeUpdate();
//
//                // Insert into Book table
//                bookStatement.setString(1, isbn);
//                bookStatement.executeUpdate();
//
//                // Insert into Author table
//                authorStatement.setString(1, author);
//                authorStatement.executeUpdate();
//
//                System.out.println("Data inserted successfully.");
//            } catch (SQLException e) {
//                System.err.println("Error inserting data: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//    }
}
