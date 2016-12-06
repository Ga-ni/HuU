import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client{//, InterruptedException

   public To_overcom_HuU HuU;
   public static BufferedReader in;
   public static PrintWriter out;
   static public String text;
   ChatClient chat;
   

   Client(){
	  
   }

   public void completeInforming(int[] foodIdx, int prioIdx) {
      // take food type and priority index from To_overcom_HuU as parameter,
      // and send data to server.
      out.println("SELECTCOMPLETE");////////////////////////
      for (int i = 0; i < 4; i++) {
         out.println("food" + foodIdx[i]);
      }
      out.println("priority" + prioIdx);
      out.println("null");
      /////////// 음..서버 코딩해주기
   }
   public void eataloneInformimg(int[] foodIdx, int prioIdx) {
      // take eat alone information from To_overcome_HuU, and send data to
      // server
      System.out.println("test~!!!");
      out.println("EATALONE");
      for (int i = 0; i < 4; i++) {
         out.println("food" + foodIdx[i]);
      }
      out.println("priority" + prioIdx);
      out.println("null");
      /////////// 서버 코딩해주기!!
   }

   private void run() throws IOException,InterruptedException {
	  chat= new ChatClient();
      Socket socket = new Socket("127.0.0.1", 1121);
      in = new BufferedReader(new InputStreamReader( // 서버로 부터 읽어오는 input
                                          // stream
            socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true); // 서버로 데이터를 보내는
      String name="";
      String result = "";
      String T, F , D, D1, D2, D3, D4, D5, D6;
      
      HuU = new To_overcom_HuU();
      HuU.setVisible(true);
      HuU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

      while (true) {
         result = in.readLine();
         if (result.equals("RESULT")) {
            System.out.println("ddddd");
            T=in.readLine();
            F=in.readLine();
            HuU.blackRec.setText(T);
            HuU.blackR.setText(F);
            
//            D1=in.readLine();
//            D2=in.readLine();
//            D3=in.readLine();
//            D4=in.readLine();
//            D5=in.readLine();
//            D6=in.readLine();
//            
//            HuU.yellow[0].setText(D1);
//            HuU.yellow[1].setText(D2);
//            HuU.yellow[2].setText(D3);
//            HuU.yellow[3].setText(D4);
//            HuU.yellow[4].setText(D5);
//            HuU.yellow[5].setText(D6);
            for(int i=0;i<6;i++){
            	D=in.readLine();
            	HuU.yellow[i].setText(D);
            }
            
         }
         //chatting
         else if(result.equals("SUBMITNAME")){
        	 System.out.println("8888888");
        	 chat.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	 chat.frame.setVisible(true);
		        name = chat.getName();
		        chat.textName.setText(name);
				out.println(name);
         } else if (result.startsWith("NAMEACCEPTED")) {
				System.out.println("네임어셉트????");
				//chat.setEdit1();
				chat.textField.setEditable(true);
			} else if (result.startsWith("ENTRANCE")) {
				System.out.println("입장했니?");
				text = result.substring(8);
				chat.messageArea.append("<" +text+ ">" + "님이 입장하셨습니다.\n");
				
			} else if (result.startsWith("WHISPER")) {
				
				text = result.substring(7);
				chat.messageArea.append(text);
			} else if (result.startsWith("MESSAGE")) {
				
				text = result.substring(8);
				chat.messageArea.append(text+"\n");
			} else if (result.startsWith("EXIT")) {
				
				text = result.substring(4);
				chat.messageArea.append("<" +text+ ">" + "님이 나가셨습니다.\n");
			}
         
      }
      
   }

   public static void main(String[] args) throws Exception {
      Client client = new Client();
      client.run();

//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               HuU.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//      client.run();
//      HuU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // chat.frame.setVisible(true);

   }
}