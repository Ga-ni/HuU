import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client {
	public To_overcom_HuU HuU;
	public static BufferedReader in;
	public static PrintWriter out;

	public Client() {

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

	private void run() throws IOException {
		
		Socket socket = new Socket("127.0.0.1", 1121);
		in = new BufferedReader(new InputStreamReader( // 서버로 부터 읽어오는 input
														// stream
				socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true); // 서버로 데이터를 보내는
		
		HuU = new To_overcom_HuU();
		HuU.setVisible(true);
		HuU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);													// output stream

		while (true) {
			String result = "";
			String T;
			String F , D ;
			/////////////////일단은 alone이든 아니든 출력
			if ((result = in.readLine()).equals("RESULT")) {
				T=in.readLine();
				F=in.readLine();
				D=in.readLine();
				
				System.out.println("Client result test: "+T);
				System.out.println("Client result test: "+F);
				System.out.println("Client result test: "+D);
				
				
				HuU.blackRec.setText(T);
				HuU.blackR.setText(F);
				//HuU.Ex
				//HuU.setVisible(false);
				//HuU.blackRec.setText(D);
				
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.run();

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HuU.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		client.run();
//		HuU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// chat.frame.setVisible(true);

	}
}
