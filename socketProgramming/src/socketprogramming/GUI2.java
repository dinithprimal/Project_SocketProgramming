/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinith
 */
public class GUI2 extends JFrame{
    
    static JFrame f;
 
    // JButton
    static JButton b, b1, b2, b3;
 
    // label to display text
    static JLabel l, l2;
    
    JLabel[] labels;// = new JLabel[4];
    private ItemMap item_map;
    int ItemMapCount;
 
    // main class
    public GUI2(ItemMap item_map)
    {
        // create a new frame to store text field and button
        this.item_map = item_map;
        ItemMapCount = this.item_map.size();
        this.labels = new JLabel[ItemMapCount];
        f = new JFrame("Bidding Server");
 
        // create a label to display text
//        l = new JLabel("panel label");
//        l2 = new JLabel("panel label2");
 
        // create a new buttons
//        b = new JButton("button1");
//        b1 = new JButton("button2");
//        b2 = new JButton("button3");
//        b3 = new JButton("button4");
 
        // create a panel to add buttons and  a specific layout
        JPanel p = new JPanel();
        p.setLayout (null);
        

 
        // add buttons and textfield to panel
//        p.add(b, BorderLayout.NORTH);
//        p.add(b1, BorderLayout.SOUTH);
//        p.add(b2, BorderLayout.EAST);
//        p.add(b3, BorderLayout.WEST);
        //p.add(l, BorderLayout.CENTER);
        //p.add(l2, BorderLayout.SOUTH);
        int v = 10;
//        for (int i = 0 ;i < 3; i++){
//
//                        //String name = file.getName();
//                        
//
//                        labels[i] = new JLabel();
//
//                        labels[i].setBounds(100,v,80,20);
//
//                        labels[i].setText(""+i);                       
//
//                        p.add(labels[i]);
//                        
//                        v = v+20;
//
//                         
//
//                 
//                }
//                
//            this.item_map.forEach((symbol, item) -> {
//                try {
//                    labels[i].setText("" + symbol + " => " + item.get_price() + "\r\n");
//                    //Symbol[count] = symbol;
//                    
//                } catch (Exception ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                //count++;
//            });
            int i = 0;
            for (Map.Entry<String, Item> pair: this.item_map.entrySet()) {
                labels[i] = new JLabel();
                labels[i].setBounds(100,v,500,100);
                p.add(labels[i]);
                labels[i].setText(""+pair.getKey()+" =>\t "+pair.getValue().get_price());
                v = v+30;
                i++;
            }

        // setbackground of panel
        //p.setBackground(Color.red);
 
        // add panel to frame
        f.add(p);
 
        // set the size of frame
        f.setSize(600, 600);
 
        f.show();
    }
    
    public void setVal(){
        int i = 0;
        for (Map.Entry<String, Item> pair: this.item_map.entrySet()) {
            labels[i].setText(""+pair.getKey()+" =>\t "+pair.getValue().get_price());
            i++;
        }
        
    }
    
}
