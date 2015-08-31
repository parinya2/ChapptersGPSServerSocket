
package mygpstracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import sun.net.www.http.ChunkedOutputStream;

/**
 *
 * @author prinya
 */
public class GPSClient {
    
    public static void main(String[] args) throws Exception{
        
        GPSClient CLIENT = new GPSClient();
        CLIENT.run();
    }
    
    public void run() throws Exception {
        
        int portNumber = getPortNumber();
        System.out.println("Client is going to connect port " + portNumber);   
        Socket SOCK = new Socket("128.199.243.137", 8800);
        //Socket SOCK = new Socket("127.0.0.1", portNumber);
        PrintStream PS = new PrintStream(SOCK.getOutputStream());
        PS.println("Hello world from Prinya SNAIL");
        
        /*InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);
        
        String MESSAGE = BR.readLine();
        System.out.println(MESSAGE);*/
    }
    
    public int getPortNumber() {
        int port = 8800;
        /*try 
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
            //e.printStackTrace();
        }*/
        return port;
    }    
}
