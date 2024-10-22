import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;

import javax.swing.AbstractAction;
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
import javax.swing.KeyStroke;
import javax.swing.JLabel;


public class ChatClient{
   String text="";
   String name="";
    JFrame frame = new JFrame("혼밥러 채팅창");
    JTextField textField = new JTextField();
    JTextField textName = new JTextField();
    JTextArea messageArea;
    JPanel panel;
    Image nameImage = new ImageIcon("img/카와이샐리.png").getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
    Image img = new ImageIcon("img/치킨샐리.jpg").getImage().getScaledInstance(470, 500, java.awt.Image.SCALE_SMOOTH);
    String other="";
    
    public ChatClient() throws IOException,InterruptedException 
    {

       
       messageArea = new JTextArea(){ //message를 보여주는 창에 대한 GUI
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
                ((new ImageIcon("img/전송.png")).getImage()).getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH))); //전송버튼
       JButton whisper = new JButton(new ImageIcon(
                ((new ImageIcon("img/귓속말.png")).getImage()).getScaledInstance(80, 35, java.awt.Image.SCALE_SMOOTH))); //귓속말버튼
       JButton b2 = new JButton(new ImageIcon(
                ((new ImageIcon("img/exit.png")).getImage()).getScaledInstance(80, 55, java.awt.Image.SCALE_SMOOTH))); //나가기버튼
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
        b1.setOpaque(true);
        frame.getContentPane().add(b1); 
        b2.setBounds(390, 10, 80, 25);
      
        b2.setBackground(new Color(255,255,255));
        frame.getContentPane().add(b2); 
        whisper.setBounds(390, 510, 80, 35);
        frame.getContentPane().add(whisper);
        messageArea.setBounds(5,30,470,500);
        messageArea.setFont(new Font("배달의민족 주아",Font.PLAIN,15));
        JScrollPane scroll = new JScrollPane(messageArea);
        scroll.setBounds(15, 50, 450, 450);
        frame.getContentPane().add(scroll);

        textField.addActionListener(new ActionListener() { //textField를 사용했을 때 취하는 액션
           
            public void actionPerformed(ActionEvent e) 
            {
               Client.out.println(textField.getText());//textField에 입력을 받아서 서버로 보낸다.
                textField.setText("");
            }
        });
        
        b2.addActionListener(new ActionListener(){ //나가기버튼을 눌렀을 때 취하는 액션
           
           public void actionPerformed(ActionEvent e)
           {
              System.exit(0);
           }
   
        });
        
        b1.addActionListener(new ActionListener(){ //전송버튼을 눌렀을 때 취하는 액션
           
           public void actionPerformed(ActionEvent e)
           {
              Client.out.println(textField.getText()); //서버로 보냄
                textField.setText("");
           }
        });
       
        whisper.addActionListener(new ActionListener(){ //귓속말 버튼을 눌렀을 때 취하는 액션
           
            public void actionPerformed(ActionEvent e) 
             {
               other = sendWhisper(); //sendWhisper함수를 호출해서 귓속말을 보낼 사람의 이름을 입력받아서 other에 저장한다.
                 Client.out.println(other+"@"+textField.getText()); // "귓속말 받을 사람의 이름@message" format으로 서버로 보낸다. 
                 textField.setText("");
               System.out.println("");
             }
        });
    }
    
       
    String getName() {
      return JOptionPane.showInputDialog(frame, "Choose a screen name:", "Screen name selection",
            JOptionPane.PLAIN_MESSAGE); // 클라이언트의 이름을 입력받음
   }

   String sendWhisper() {
      return JOptionPane.showInputDialog(frame, "Who will you spend the WHISPER message?", "",
            JOptionPane.PLAIN_MESSAGE); // 귓속말을 보낼 상대의 이름을 입력받음
   }
   String getText()
   {
      return text;
   }

    
    
} 