import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client{//, InterruptedException

   public To_overcom_HuU HuU;
   BufferedReader in;
   static PrintWriter out;
   static public String text;
   ChatClient chat;
   

   Client(){
     
   }

   public void completeInforming(int[] foodIdx, int prioIdx) { //선택완료 버튼 눌렀을 때 서버로 보내는 메세지
      out.println("SELECTCOMPLETE");
      for (int i = 0; i < 4; i++) {
         out.println("food" + foodIdx[i]);
      }
      out.println("priority" + prioIdx);
      out.println("null");
   }
   public void eataloneInforming(int[] foodIdx, int prioIdx) { //같이드실래요 버튼을 눌렀을 때 서버로 보내는 메세지
      out.println("EATALONE");
      for (int i = 0; i < 4; i++) {
         out.println("food" + foodIdx[i]);
      }
      out.println("priority" + prioIdx);
      out.println("null");
   }

   private void run() throws IOException,InterruptedException {
     chat= new ChatClient();
      Socket socket = new Socket("127.0.0.1", 1121);
      in = new BufferedReader(new InputStreamReader( // 서버로 부터 읽어오는 input stream
            socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true); // 서버로 데이터를 보내는 output stream
      String name="";
      String result = "";
      String T, F , D;
      
      HuU = new To_overcom_HuU();
      HuU.setVisible(true);
      HuU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

      while (true) {
         result = in.readLine(); //서버로 부터 데이터 읽어옴
         if (result.equals("RESULT")) { //읽어온 값이 RESULT이면
            System.out.println("result : ");
            T=in.readLine();
            F=in.readLine();
            HuU.blackRec.setText(T);
            HuU.blackR.setText(F);
            for(int i=0;i<6;i++){
               D=in.readLine();
               HuU.restaurantInfo[i].setText(D);
            }
            
         }
         //chatting
         else if(result.equals("SUBMITNAME")){ //서버로 부터 읽어온 값이 SUBMITNAME이면
            chat.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            chat.frame.setVisible(true); //Chatclient에서 만든 GUI를 보여줌
            name = chat.getName(); //이름을 입력받음
            if(name.equals("")){
            	JOptionPane.showMessageDialog(null, "Please input your name!", "No name",
						JOptionPane.WARNING_MESSAGE);
            	name = chat.getName();
            }
            chat.textName.setText(name); //이름을 창에 보여줌
            out.println(name); //서버로 이름을 보내줌
         } else if (result.startsWith("NAMEACCEPTED")) { //서버로 부터 읽어온 값이 NAMEACCEPT로 시작하면
            chat.textField.setEditable(true); //textField가 수정가능하게 바꿈
         } else if (result.startsWith("ENTRANCE")) {  //서버로 부터 읽어온 값이 ENTRANCE로 시작하면
            chat.textName.setEditable(false); //이제 textField 수정못하게 바꿈
            text = result.substring(8); // ENTRANCE 뒤부터 메세지이기 때문에 
            chat.messageArea.append("<" +text+ ">" + "님이 입장하셨습니다.\n"); //messageArea에 메세지를 보여줌
            
         } else if (result.startsWith("WHISPER")) {   //서버로 부터 읽어온 값이 WHISPER로 시작하면
            text = result.substring(7); // WHISPER 뒤부터 메세지이기 때문에 
            chat.messageArea.append(text);//messageArea에 메세지를 보여줌
         } else if (result.startsWith("MESSAGE")) {//서버로 부터 읽어온 값이 MESSAGE로 시작하면
            text = result.substring(8); // MESSAGE 뒤부터 메세지이기 때문에 
            chat.messageArea.append(text+"\n"); //messageArea에 메세지를 보여줌
         } else if (result.startsWith("EXIT")) {
            
            text = result.substring(4); // EXIT 뒤부터 이름이기 때문에 
            chat.messageArea.append("<" +text+ ">" + "님이 나가셨습니다.\n"); //messageArea에 메세지를 보여줌
         }
         
      }
      
   }

   public static void main(String[] args) throws Exception {
      Client client = new Client(); 
      client.run();
   }
}