/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygpstracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.SimpleDateFormat;

public class GPSServer {
    
    static String SERVER_VERSION = "MyGpsTracker ver2";
    
    //static String FilePath = "/var/zpanel/hostdata/admin/public_html/gps_tracking/server_socket_log.txt";
    static String FilePath = "./server_socket_log.txt";
   
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    
    static final String DB_URL = "jdbc:mysql://128.199.243.137/admin_gps";
    //static final String DB_URL = "jdbc:mysql://127.0.0.1/MyDatabase";
    
   
    // Database credentials
    static final String USER = "gps";
    static final String PASS = "65gps65";
    //static final String USER = "admin";
    //static final String PASS = "password";
   
    public static void writeMsgToDatabase(String message, String clientIpAddress, int clientPort) {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            preparedStmt = conn.prepareStatement("INSERT INTO tracking_data (raw_data, client_ip, client_port, date_time, server_version) value (?,?,?,now(),?)");
            preparedStmt.setString(1, message);
            preparedStmt.setString(2, clientIpAddress);
            preparedStmt.setInt(3, clientPort);
            preparedStmt.setString(4, SERVER_VERSION);
            preparedStmt.executeUpdate();
            
            //STEP 6: Clean-up environment
            preparedStmt.close();
            conn.close();
            
            System.out.println("Insert message: " + message + " completed");
        } catch (Exception e) {
            System.out.println("Database Error: " + e.toString());
            writeMsgTologFile("Failed to Insert Message:" + message + 
                                "  Database Error: " + e.toString(), 
                                clientIpAddress, 
                                clientPort);
        } finally {
            try{
                if(preparedStmt!=null)
                   preparedStmt.close();
            } catch(SQLException se2) {}
            
            try{
                if(conn!=null)
                   conn.close();
            } catch(SQLException se) {
               System.out.println("Database Finally Error: " + se.toString());
            }
        }
    }
    
    public static void writeMsgTologFile(String message, String clientIpAddress, int clientPort) {
        try {
            File logFile = createLogFile();
            String newLine = System.getProperty("line.separator");
            FileWriter FW = new FileWriter(logFile.getAbsoluteFile(), true);
            BufferedWriter BW = new BufferedWriter(FW);
            StringBuilder sb = new StringBuilder("(" + SERVER_VERSION + ") ");
            sb.append("Client connected from IP:");
            sb.append(clientIpAddress);
            sb.append(" Port:");
            sb.append(clientPort); 
            sb.append("  Date : ");
            Date date = new Date();
            sb.append(date.toString());
            sb.append(newLine);
            sb.append("Data : ");
            sb.append(message);
            sb.append(newLine);
            sb.append(newLine); 

            BW.write(sb.toString());
            BW.close();    
        } catch(Exception e) {
            System.out.println("Error at writeMessageToLogFile: " + e.toString());
        }

    }
    
    public static void main(String[] args) throws Exception{
        
            GPSServer SERVER = new GPSServer();
            SERVER.run();
    }
    
    public void run() throws Exception {
        
        File logFile = createLogFile();
        int portNumber = getPortNumber();
        ServerSocket SRVSOCK = new ServerSocket(portNumber);        
        
        while (true) {
            System.out.println("(GPSServer) Waiting for Client...");
            Socket SOCK = SRVSOCK.accept();
                        
            InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
            BufferedReader BR = new BufferedReader(IR);

            String MESSAGE = BR.readLine();
            String clientHostAddress = SOCK.getLocalAddress().getHostAddress();
            Date date = new Date();

            // Write to File
            /*
            
            String newLine = System.getProperty("line.separator");
            FileWriter FW = new FileWriter(logFile.getAbsoluteFile(), true);
            BufferedWriter BW = new BufferedWriter(FW);
            StringBuilder sb = new StringBuilder("");
            sb.append("(Server ver 3) Client connected from ");
            sb.append(clientHostAddress);
            sb.append("  Date : ");
            sb.append(date.toString());
            sb.append(newLine);
            sb.append("Data : ");
            sb.append(MESSAGE);
            sb.append(newLine);
            sb.append(newLine); 
            
            BW.write(sb.toString());
            BW.close();*/
            
            System.out.println("Client connected from " + SOCK.getLocalAddress().getHostAddress() + 
                    " Date: " + date.toString());
            System.out.println("Data : " + MESSAGE);
            
            
            /*if (MESSAGE != null) {
                PrintStream PS = new PrintStream(SOCK.getOutputStream());
                PS.println("Response from SERVER (ver 3)");
            }*/
            
            System.out.println("Done");
            System.out.println(); 
        }
    }
    
    public static File createLogFile() {
        File file = new File(FilePath); 
        
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error at Create Log File");
            }
        }
        
        return file;
    }
    
    public int getPortNumber() {
        int port = 8800;
       /* try 
        {
            File file = new File("./socket_example_port_number.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
            }
            fileReader.close();
            
            port = Integer.parseInt(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return port;
    }
}
