/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinith
 */
public class SocketClient extends Thread{
    
    @Override
    public void run(){
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.print("Please enter port number : ");
            int portNum = scanner.nextInt();
            if(portNum == 8080){
                try {
                    Socket s = new Socket("localhost", 8080);
                    
                    InputStream is = s.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    
                    OutputStream os = s.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    
//                    while(true){
//                        String req = br.readLine();
//                        if(req!=null){
//                            if(req == "Enter a Value : "){
//                                System.out.println(req);
//                                int val = scanner.nextInt();
//                                pw.print(val);
//                            }else{
//                                System.out.println(req);
//                            }
//                            req = null;
//                        }
//                    }
                    String serverMsg;
		    bw.write("8\r");
		    bw.write("10\r");
                    bw.flush();
                    while((serverMsg = br.readLine()) != null){
                        System.out.println("Client: " + serverMsg);
                    }


                } catch (IOException ex) {
                    Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("\n\nSorry..! Your port number is wrong");
            }
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new SocketClient().start();
        
    }
    
}
