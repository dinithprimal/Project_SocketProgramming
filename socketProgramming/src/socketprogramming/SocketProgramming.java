/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.io.IOException;

/**
 *
 * @author Dinith
 */
public class SocketProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        // Milestone 1 : Create the CSV reader and populate datastructure
        
        int hour = 0;
        int minute = 3;
        int seconds = 0;

        ItemMap item_map = new ItemMap();   // Datastructure
        CSVReader csvreader = new CSVReader("src\\stocks1.csv", item_map,hour,minute,seconds);    // CSVreader
        csvreader.read();   //item_map gets populated with data


        // Milestone 2 : Create a server and accept 1 connection
        

        Server server = new Server(item_map);   // Server
        server.start();       // Server starts running here.
        
        
        //GUI.setVisible(true);

        // Milestone 3 : Modify server to accept multiple connections (multi-threading)
    }
    
}
