/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Dinith
 */
public class Item {
    
    private String symbol;
    private String name;
    private float price;
    private int security;
    private float profit;
    private int hour;
    private int minute;
    private int seconds;
    private ArrayList<String> profitSubs = new ArrayList<String>();
    private ArrayList<String> bidSubs = new ArrayList<String>();
    Timer timer;

    public Item(String symbol, String price, String security, String profit, int hour, int minute, int seconds){
        this.symbol = symbol;
        this.price = Float.parseFloat(price);
        this.security = Integer.parseInt(security);
        this.profit = Float.parseFloat(profit);
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
        countdown_timer();
        timer.start();
    }

    public String get_symbol(){
        return this.symbol;
    }

    public String get_name(){
        return this.name;
    }

    public float get_price() {
    	return this.price;
    }
    
    public int get_security() {
    	return this.security;
    }
    
    public float get_profit() {
    	return this.profit;
    }
    
    public int get_hour(){
        return this.hour;
    }
    
    public int get_minute(){
        return this.minute;
    }
    
    public int get_seconds(){
        return this.seconds;
    }
    
    public void update_hour(int hour){
        this.hour = hour;
    }
    
    public void update_minute(int minute){
        this.minute = minute;
    }
    
    public void update_seconds(int seconds){
        this.seconds = seconds;
    }

    public void update_price(float price){
    	this.price = price;
    }
    
    public void update_profit(float profit){
    	this.profit = profit;
    }

    public int make_bid(float new_price){
    	// TODO: Implement this.
        if(hour>0||minute>0||seconds>0){
            if((hour==0)&&(minute==0)&&(seconds<40)){
                this.minute = 1;
            }
            if(new_price > this.price){
                this.price = new_price;
                this.profit = profit + (new_price-price);
                return 0;
            }else{
                return 1;
            }
            
        }else{
            return 2;
        }
        
    }
    
    public void updateProfitSubs(String profitSub){
        profitSubs.add(profitSub);
    }
    
    public void updateBidSubs(String bidSub){
        bidSubs.add(bidSub);
    }
    
    public ArrayList getProfitSubs(){
        return profitSubs;
    }
    
    public ArrayList getBidSubs(){
        return bidSubs;
    }
    
    private void countdown_timer(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                seconds--;
                if(seconds==-1){
                    seconds = 59;
                    minute--;
                    if(minute==-1){
                        minute = 59;
                        hour--;
                    }
                }
                if((hour==0)&&(minute==0)&&(seconds==00)){
                    timer.stop();
                }
            }
        });
    }
    
}
