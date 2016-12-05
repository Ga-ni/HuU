import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class ChatClient{
   String name="";
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("혼밥러 채팅창");
    JTextField textField = new JTextField();
    JTextField textName = new JTextField();
    JTextArea messageArea;
    JPanel panel;
    Image nameImage = new ImageIcon("img/카와이샐리.png").getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
    Image img = new ImageIcon("img/치킨샐리.jpg").getImage().getScaledInstance(470, 500, java.awt.Image.SCALE_SMOOTH);
    String other="";
  
    
    public ChatClient() throws IOException
    {
       messageArea = new JTextArea(){
          {setOpaque(false);}
          public void paint(Graphics g){
             g.drawImage(img, 0, 0, this);
             super.paint(g);
          }
       };
       
       panel = new JPanel(){
          {setOpaque(false);}
          public void paint(Graphics g){
             g.drawImage(nameImage, 0, 0, this);
             super.paint(g);
          }
       };
       
       frame.getContentPane().setBackground(new Color(79,54,29));
       JButton b1 = new JButton(new ImageIcon(
                ((new ImageIcon("img/전송.png")).getImage()).getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH)));
       JButton whisper = new JButton(new ImageIcon(
                ((new ImageIcon("img/귓속말.png")).getImage()).getScaledInstance(80, 35, java.awt.Image.SCALE_SMOOTH)));
        //b1.setBackground(new Color(254,230,251));
       JButton b2 = new JButton(new ImageIcon(
                ((new ImageIcon("img/exit.png")).getImage()).getScaledInstance(80, 55, java.awt.Image.SCALE_SMOOTH)));
       // b2.setBackground(Color.lightGray);
        whisper.setBackground(new Color(225,219,249));
        // Layout GUI
        
        textField.setEditable(false);//textField를 직은 사용하지 못하게 false값으로 초기화한다.
        messageArea.setEditable(false);//messageArea를 아직은 사용하지 못하게 false값으로 초기화한다.
        
        frame.setLayout(null);
        frame.setBounds(10, 10, 500, 600);
        textName.setBounds(70,10,300,35);
        textName.setBackground(new Color(79,54,29));
        textName.setForeground(Color.white);
        textName.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        textField.setBounds(10, 510, 335, 35);
        panel.setBounds(15, 10, 35, 35);
        textField.setBackground(new Color(255,255,255));
        textField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        frame.getContentPane().add(textField); // textField를 생성한다.
        frame.getContentPane().add(textName);
        frame.getContentPane().add(panel);
        textField.setFont(new Font("배달의민족 주아",Font.PLAIN,15));
        textName.setFont(new Font("배달의민족 주아",Font.PLAIN,25));
        b1.setBounds(350, 510, 35, 35);
        b1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        b2.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        whisper.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        messageArea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        b1.setOpaque(false);
        //b1.setBackground(new Color(0,0,0,0));
   
        frame.getContentPane().add(b1); 
        //b1.setFont(new Font("배달의민족 한나",Font.PLAIN,15));
        b2.setBounds(390, 10, 80, 25);
      
        b2.setBackground(new Color(255,255,255));
        frame.getContentPane().add(b2); 
        //b2.setFont(new Font("배달의민족 한나",Font.PLAIN,15));
        //whisper.setBounds(390, 10, 80, 25);
        whisper.setBounds(390, 510, 80, 35);
        frame.getContentPane().add(whisper);//whisper button을 추가한다.
        //whisper.setFont(new Font("배달의민족 한나",Font.PLAIN,15));
        messageArea.setBounds(5,30,470,500);

        messageArea.setFont(new Font("배달의민족 주아",Font.PLAIN,15));
        JScrollPane scroll = new JScrollPane(messageArea);
        scroll.setBounds(15, 50, 450, 450);
        frame.getContentPane().add(scroll);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.run();

        // Add Listeners
        textField.addActionListener(new ActionListener() { 
           
            public void actionPerformed(ActionEvent e) 
            {
           
//                out.println(textField.getText());//textField에 입력을 받아서 서버로 보낸다.
//                textField.setText("");
            	///////////////////////////////////////////////////////////////////////
            }
            
        });
        
        b2.addActionListener(new ActionListener(){
           
           public void actionPerformed(ActionEvent e)
           {
              System.exit(0);
           }
   
        });
        
        b1.addActionListener(new ActionListener(){
           
           public void actionPerformed(ActionEvent e)
           {
//              out.println(textField.getText());//textField에 입력을 받아서 서버로 보낸다.
//                textField.setText("");
        	   //////////////////////////////////////////////////////////////
           }
        });
       
        whisper.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e) 
             {
               other = sendWhisper(); //sendWhisper함수를 호출해서 귓속말을 보낼 사람의 이름을 입력받아서 other에 저장한다.
//                 out.println(other+"@"+textField.getText()); // "귓속말 받을 사람의 이름@message" format으로 서버로 보낸다. 
//                 textField.setText("");
               System.out.println("");///////////////////////////////////////////////
             }
        });
    }
    
       
    private String getName() 
    {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE); //클라이언트의 이름을 입력받음
    }

   private String sendWhisper()
   {
      return JOptionPane.showInputDialog(
               frame,
               "Who will you spend the WHISPER message?",
               "",
               JOptionPane.PLAIN_MESSAGE); //귓속말을 보낼 상대의 이름을 입력받음
   }
    public void run() throws IOException 
    {
    	Socket socket = new Socket("127.0.0.1", 1122);
        in = new BufferedReader(new InputStreamReader( //서버로 부터 읽어오는 input stream
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true); //서버로 데이터를 보내는 output stream
        
     
    	System.out.println("chat client run이다!");
//    	while(true){
//    		name = getName(); // getName()함수를 호출해서 클라이언트의 이름을 입력받음
//    		textName.setText(name);
//    		
//    	}
        
    	
//        while (true) 
//        {
//            String line = in.readLine(); //서버로부터 데이터를 읽어와서 line string에 저장함
//            if (line.startsWith("SUBMITNAME")) //서버로부터 읽어온 데이터가 SUBMITNAME으로 시작하면
//            {
//               name = getName(); // getName()함수를 호출해서 클라이언트의 이름을 입력받음
//               textName.setText(name);
//                out.println(name); // 입력 받은 이름을 서버에 보내줌
//             
//            } 
//            else if (line.startsWith("NAMEACCEPTED")) //서버로부터 읽어온 데이터가 NAMEACCEPTED로 시작하면
//            {
//                textField.setEditable(true); //이제 textField가 수정 가능해짐
//            } 
//            else if(line.startsWith("ENTRANCE"))//서버로부터 읽어온 데이터가 ENTRANCE로 시작하면
//            {
//               messageArea.append("<"+line.substring(8)+">"+"님이 입장하셨습니다.\n"); //읽어온 데이터의 8글자 뒤부터가 이름이기 때문에 messageArea에 <name> ENTRANCE란 메세지를 첨부한다. 
//               
//            }
//            else if(line.startsWith("WHISPER")) //서버로부터 읽어온 데이터가 WHISPER로 시작하면
//            {
//               
//               messageArea.append(line.substring(7)+"\n");//읽어온 데이터의 7글자 뒤부터가 "보내는 사람의 이름: message" format이기 때문에 messageArea에 첨부한다.
//            }
//            else if (line.startsWith("MESSAGE")) 
//            {
//                messageArea.append(line.substring(8) + "\n"); //읽어온 데이터의 8글자 뒤부터가 message이기 때문에 messageArea에 첨부한다.
//            }
//            else if(line.startsWith("EXIT")) //서버로부터 읽어온 데이터가 EXIT로 시작하면
//            {
//               messageArea.append("<"+line.substring(4)+">"+"님이 나가셨습니다."+"\n"); //읽어온 데이터의 4글자 뒤부터가 종료하는 클라이언트의 name이기 때문에 messageArea에 첨부한다.
//            }
//        } 
    	
           
    }//////여기도 괄호!!

   
    public static void main(String[] args) throws Exception 
    {
    	ChatClient client = new ChatClient();
//    	client.run();
//        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        client.frame.setVisible(true);
    }
    
    
} ////////////여기 괄호!!!
