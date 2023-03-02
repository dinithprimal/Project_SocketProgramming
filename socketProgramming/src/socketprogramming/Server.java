package socketprogramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    // TODO : Define all required varible
    private ItemMap item_map;
    private final HashMap<String, Socket> socketUserName = new HashMap<String, Socket>();
    int nbClients,nbPubs;
    serverGUI gui;
    private Runnable accepterA;
    private Runnable accepterB;

    public Server(ItemMap item_map){
        this.item_map = item_map;
        gui = new serverGUI(item_map);
        gui.setVisible(true);
    }
    
    /**
     *
     * @throws IOException
     */
    @Override
    public void run(){
        try {
            // Create a socket and accept connections
            ServerSocket ss = new ServerSocket(2021);
            ServerSocket sp = new ServerSocket(2022);
            while(true){
                //if(ss.getLocalPort()==2021){
                    //Socket s = ss.accept();
                    new Conversation(ss.accept(),++nbClients).start();
                //}
                //if(ss.getLocalPort()==2022){
                   // Socket spub = sp.accept();
                    new ConversationPubs(sp.accept(),++nbPubs).start();
                //}
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class Conversation extends Thread{
        private Socket socket;
        private int numOfClients;
        private String name;
        
        public Conversation(Socket s,int num) {
            super();
            this.socket = s;
            this.numOfClients = num;
            
        }
        
        @Override
        public void run() {
            InputStream is = null;
            try {
                //code for conversation
                
                Scanner scanner = new Scanner(System.in);
                
                is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                
                //DataInputStream din=new DataInputStream(socket.getInputStream());
                
                String IP = socket.getRemoteSocketAddress().toString();
                
                System.out.println("Connection for client number"+numOfClients+" IP="+IP);
                bw.write("Hello, Your client number is "+numOfClients);
                
                bw.write("\n\n*** Welcome to the Bidding Server (Clients Only) ***\r\n");            
                
                String client;
                
                while(true){
                    bw.write("\n\n*** Please Enter your user code here and press Enter : ");
                    bw.flush();
                    String password = br.readLine().trim();
                    if("123".equals(password)){
                        bw.write("\r\n\n*** You are successfully logged In \r\n");
                        bw.flush();
                        bw.write("\n\n*** Welcome to the bidding service... \r\n");
                        bw.flush();
                        bw.write("\n\n*** Please enter your name here... \r\n");
                        bw.flush();
                        this.name = br.readLine().trim();
                        socketUserName.put(this.name, socket);
                        gui.setClientUser(name);
                        client = "User";
                        break;
                    }else{
                        bw.write("\r\n\n*** User code you entered was incorrect \r\n");
                        bw.flush();
                        continue;
                    }
                }
                
                if("User".equals(client)){
                    client();
                    socket.close();
                }else{
                    
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
//        Socket getSoket(String name) throws IOException{
//            if(name.equals(this.name)){
//                return this.socket;
//            }
//            return null;
//        }
        
        void client() throws IOException{
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String Symbol[] = null;
            
            bw.write("\n\n*** Available biddings are... \r\n\n");
            bw.flush();
            bw.write("\tItem Symbol \t\t=>\tItem Price\r\n");
            bw.write("==========================================================\r\n");
            //int count = 0;
            item_map.forEach((symbol, item) -> {
                try {
                    bw.write("\t" + symbol + "\t\t\t=>\t" + item.get_price() + "\r\n");
                    bw.flush();
                    //Symbol[count] = symbol;
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                //count++;
            });
            
            while(true){
                bw.write("\n\n*** Enter a query... To exit enter 'Exit' \r\n");
                bw.flush();
                String inputVal = br.readLine().trim();
                if("Exit".equals(inputVal)){
                    bw.write("\n\n*** Thank You! Come Again... \r\n");
                    bw.flush();
                    gui.outUser(name);
                    return;
                }else{
                    String queryArray = inputVal; 
                    String[] QueryWords = queryArray.split(" ");
                    
                    if(QueryWords.length == 1){
                    
                        if(check(QueryWords[0])){
                            //bw.write("\n\n*** OK... \r\n");
                            bw.write("\n\n" + item_map.get(inputVal).get_symbol()+ " => "+item_map.get(inputVal).get_price()+"\r\n");
                            bw.flush();
    
                        }else{
                            bw.write("\n\n*** You entered item is not valied... Please try again... \r\n");
                            bw.flush();
                        }
                    }else if(QueryWords.length >= 2){
                        
                        if((QueryWords[0].equals("PRFT"))||(QueryWords[0].equals("BID"))){
                            
                            if(QueryWords[0].equals("PRFT")){
                                int noSyms = QueryWords.length;
                                for(int i = 1; i<noSyms; i++){
                                    if(check(QueryWords[i])){
                                        item_map.get(QueryWords[i]).updateProfitSubs(this.name);
                                        bw.write("\n\n" + item_map.get(QueryWords[i]).get_symbol()+ " => Successfully subscribed for profit...\r\n");
                                        bw.flush();
                                        String log = this.name+" successfully subscribe for profit of "+item_map.get(QueryWords[i]).get_symbol();
                                        gui.makeLog(log);
                                    }else{
                                        bw.write("\n\n*** You entered item is not valied... \r\n");
                                        bw.flush();
                                    }
                                }
//                                for(int i = 1; i<noSyms; i++){
//                                    System.out.println(item_map.get(QueryWords[i]).getProfitSubs());
//                                }
                            }else if(QueryWords[0].equals("BID")){
                                int noSyms = QueryWords.length;
                                for(int i = 1; i<noSyms; i++){
                                    if(check(QueryWords[i])){
                                        item_map.get(QueryWords[i]).updateBidSubs(this.name);
                                        bw.write("\n\n" + item_map.get(QueryWords[i]).get_symbol()+ " => Successfully subscribed for bids...\r\n");
                                        bw.flush();
                                        String log = this.name+" successfully subscribe for bid of "+item_map.get(QueryWords[i]).get_symbol();
                                        gui.makeLog(log);
                                    }else{
                                        bw.write("\n\n*** You entered item is not valied... \r\n");
                                        bw.flush();
                                    }
                                }
//                                for(int i = 1; i<noSyms; i++){
//                                    System.out.println(item_map.get(QueryWords[i]).getBidSubs());
//                                }
                            }
                            
                        }else{
                        
                            if(check(QueryWords[0])){

                                    String inputSym = QueryWords[0];
                                    String inputBidVal = QueryWords[1];
                                    try {
                                        float bidValue = Float.parseFloat(inputBidVal);
                                        int rtVal = item_map.get(inputSym).make_bid(bidValue);
                                        if(rtVal == 0){
                                            bw.write("\n\n*** Bid successfull... New value : "+ item_map.get(inputSym).get_symbol()+ " => "+item_map.get(inputSym).get_price()+" \r\n");
                                            bw.flush();
                                            gui.setVal();
                                            notifyBidUpdates(item_map.get(inputSym).get_symbol(),bidValue,this.name);
                                            String log = this.name+" successfully bided for "+item_map.get(inputSym).get_symbol()+": value = "+item_map.get(inputSym).get_price();
                                            gui.makeLog(log);
                                            continue;
                                        }else if(rtVal == 1){
                                            bw.write("\n\n*** You entered value is less than the present value... Please try again.. Present Value : "+ item_map.get(inputSym).get_symbol()+ " => "+item_map.get(inputSym).get_price()+" \r\n");
                                            bw.flush();
                                            continue;
                                        }else if(rtVal == 2){
                                            bw.write("\n\n*** Sorry..! Biding time is over for this item... \r\n");
                                            bw.flush();
                                            continue;
                                        }
                                    } catch (NumberFormatException ex) {
                                        bw.write("\n\n*** Invalied input... Please try again \r\n");
                                        bw.flush();
                                        continue;
                                    }   

                            }else{
                                bw.write("\n\n*** You entered item is not valied... Please try again... \r\n");
                                bw.flush();
                            }
                        }
                        
                    }else{
                        bw.write("\n\n*** You entered query is not valied... Please try again... \r\n");
                        bw.flush();
                    }
                }
            }
            
            
        }
        
        private void notifyBidUpdates(String sym, float bid,String n){
            ArrayList bidSubs = item_map.get(sym).getBidSubs();
            
            Iterator<Map.Entry<String, Socket> >
            iterator = socketUserName.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Socket>entry = iterator.next();
                if(bidSubs.contains(entry.getKey())){
                    if(!entry.getKey().equals(n)){
                        Socket sBids = entry.getValue();
                        try{
                            OutputStream os = sBids.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);

                            bw.write("\n\n*** This is a Notificatioin : "+ n +" bids for "+ sym +" => "+bid+"\r\n\n");
                            bw.flush();
                        }catch(Exception e){

                        }
                    }
                }
            }
        }
        
        private boolean check(String toCheckValue){
            
            boolean isKeyPresent = false;
            
            Iterator<Map.Entry<String, Item> >
            iterator = item_map.entrySet().iterator();

            while (iterator.hasNext()) {

                Map.Entry<String, Item>entry = iterator.next();

                if (toCheckValue.equals(entry.getKey())) {

                    isKeyPresent = true;

                }
            }
            return isKeyPresent;
        }
        
    }
    
    
    //Conversation for publisher*****************************************************************
    
    class ConversationPubs extends Thread{
        private Socket socket;
        private int numOfPubs;
        private String name;
        
        public ConversationPubs(Socket s,int num) {
            super();
            this.socket = s;
            this.numOfPubs = num;
        }
        
        @Override
        public void run() {
            InputStream is = null;
            try {
                //code for conversation
                
                Scanner scanner = new Scanner(System.in);
                
                is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                
                //DataInputStream din=new DataInputStream(socket.getInputStream());
                
                String IP = socket.getRemoteSocketAddress().toString();
                
                System.out.println("Connection for publisher number"+numOfPubs+" IP="+IP);
                bw.write("Hello, Your publisher number is "+numOfPubs);
                
                bw.write("\n\n*** Welcome to the Bidding Server (Publisher) ***\r\n");            
                
                String client;
                
                while(true){
                    bw.write("\n\n*** Please Enter your user code here and press Enter : ");
                    bw.flush();
                    String password = br.readLine().trim();
                    if("123".equals(password)){
                        bw.write("\r\n\n*** You are successfully logged In \r\n");
                        bw.flush();
                        bw.write("\n\n*** Welcome to the bidding service... \r\n");
                        bw.flush();
                        bw.write("\n\n*** Please enter your name here... \r\n");
                        bw.flush();
                        this.name = br.readLine().trim();
                        gui.setPubsUser(name);
                        client = "User";
                        break;
                    }else{
                        bw.write("\r\n\n*** User code you entered was incorrect \r\n");
                        bw.flush();
                        continue;
                    }
                }
                
                if("User".equals(client)){
                    publisher();
                    socket.close();
                }else{
                    
                }
                
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        void publisher() throws IOException{
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String Symbol[] = null;
            
            bw.write("\n\n*** Available biddings are... \r\n");
            bw.flush();
            bw.write("\tItem Symbol \t\t=>\tItem Price\r\n");
            bw.write("==========================================================\r\n");
            //int count = 0;
            item_map.forEach((symbol, item) -> {
                try {
                    bw.write("\t" + symbol + "\t\t\t=>\t" + item.get_price() + "\r\n");
                    bw.flush();
                    //Symbol[count] = symbol;
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                //count++;
            });
            
            while(true){
                bw.write("\n\n*** Enter Enter a query... To exit enter 'Exit' \r\n");
                bw.flush();
                String inputVal = br.readLine().trim();
                if("Exit".equals(inputVal)){
                    bw.write("\n\n*** Thank You! Come Again... \r\n");
                    bw.flush();
                    gui.outPubUser(name);
                    return;
                }else{
                    String queryArray = inputVal; 
                    String[] QueryWords = queryArray.split(" ");
                    
                    if(QueryWords.length == 3){
                        
                        if(check(QueryWords[0],QueryWords[1])){
                                
                                String inputSym = QueryWords[0];
                                String inputBidVal = QueryWords[2];
                                try {
                                    float bidValue = Float.parseFloat(inputBidVal);
                                    item_map.get(inputSym).update_profit(bidValue);
                                    bw.write("\n\n*** Profit updated successfull... New value : "+ item_map.get(inputSym).get_symbol()+ " PRFT "+item_map.get(inputSym).get_profit()+" \r\n");
                                    bw.flush();
                                    notifyProfitUpdates(item_map.get(inputSym).get_symbol(),item_map.get(inputSym).get_profit(),this.name);
                                    String log = this.name+" successfully changed profit for "+item_map.get(inputSym).get_symbol()+": value = "+item_map.get(inputSym).get_profit();
                                    gui.makeLog(log);
                                    continue;
                                    
                                } catch (NumberFormatException ex) {
                                    bw.write("\n\n*** Invalied input... Please try again \r\n");
                                    bw.flush();
                                    continue;
                                }   
                                
                        }else{
                            bw.write("\n\n*** You entered item or security was not valied... Please try again... \r\n");
                            bw.flush();
                        }
                        
                    }else{
                        bw.write("\n\n*** You entered query is not valied... Please try again... \r\n");
                        bw.flush();
                    }
                }
            }
            
            
        }
        
        private void notifyProfitUpdates(String sym, float profit,String n){
            ArrayList profitSubs = item_map.get(sym).getProfitSubs();
            
            Iterator<Map.Entry<String, Socket> >
            iterator = socketUserName.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Socket>entry = iterator.next();
                if(profitSubs.contains(entry.getKey())){
                    if(!entry.getKey().equals(n)){
                        Socket sProfit = entry.getValue();
                        try{
                            OutputStream os = sProfit.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);

                            bw.write("\n\n*** This is a Notificatioin : "+ n +" change profit for "+ sym +" => "+profit+"\r\n\n");
                            bw.flush();
                        }catch(Exception e){

                        }
                    }
                }
            }
        }
        
        private boolean check(String toCheckValue,String toCheckSec){
            
            Iterator<Map.Entry<String, Item> >
            iterator = item_map.entrySet().iterator();
  
            // flag to store result
            boolean isKeyPresent = false;
            
            int sec = Integer.parseInt(toCheckSec);

            // Iterate over the HashMap
            while (iterator.hasNext()) {

                // Get the entry at this iteration
                Map.Entry<String, Item>entry = iterator.next();

                // Check if this key is the required key
                if ((toCheckValue.equals(entry.getKey()))&&(sec == item_map.get(toCheckValue).get_security())) {

                    isKeyPresent = true;
                }
            }
            return isKeyPresent;
        }
        
    }
}
