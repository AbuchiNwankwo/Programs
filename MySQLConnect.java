package com.revature.banking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class MySQLConnect {
    static boolean status;
    private static Connection con;

    /**
     * Creates a connection and returns for further use
     */
    static Connection ConnectDB(){

        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://maduabuchi-db.cw51wm3v5bmw.us-east-1.rds.amazonaws.com:5432/"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false";
            String username = "postgres"; //username
            String password = "iLOVEMYLIFE2K22"; //password
            con=DriverManager.getConnection(url,username,password);

           //Successfully Connected to the Database
            status=true;

            return con;
        } catch(Exception e){
            //Connection error
            status=false;
            return null;
        }
    }

    /**
     * Returns the list of customers from the database
     * @return list of type ArrayList
     */
    @SuppressWarnings("rawtypes")
	static ArrayList getCustomers(){

        ArrayList<String> list = new ArrayList<>();

        // Get the accountIDs
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT accountID FROM account";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                list.add(rs.getString("accountID"));
            }

            rs.close();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null,"Load Unsuccessful!");
        }

        return list;
    }

}
