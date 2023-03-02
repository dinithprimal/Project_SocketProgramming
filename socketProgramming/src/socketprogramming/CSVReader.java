/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dinith
 */
public class CSVReader {
    private final String filename;
    public ItemMap item_map;
    String line = "";
    private int hour;
    private int minute;
    private int seconds;

    public CSVReader(String filename, ItemMap item_map, int hour, int minute, int seconds){
        this.filename = filename;
        this.item_map = item_map;   //reference to the datastructure containing all the items.
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    public void read() throws IOException{

    	// TODO : Implement method to read CSV file (this.filename) and populat the datastructure (this.item_map)
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            int count = 1;
            while((line=reader.readLine()) != null){
                String[] row = line.split(",");
//                if("ABTL".equals(row[0])){
//                    for(String index:row){
//                        System.out.printf("%-10s",index);
//                    }
//                    System.out.println();
//                }
                if(count!=1){
                    item_map.put(row[0], new Item(row[0],row[1],row[2],row[3],hour,minute,seconds));
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            reader.close();
        }

    }
    
}
