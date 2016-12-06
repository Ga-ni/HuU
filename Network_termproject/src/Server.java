
import java.util.Iterator;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {

	private static final int PORT = 1121;// 서버가 사용하는 포트
	private static final int CPORT =1122;
	private static final int HPORT =1123;

	
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();

	// static Select selcet;
	private static int[] type = { -1, -1, -1, -1 };
	private static int prio;
	public static int T, F, D;
	private static String TypeName;
	private  static String FoodName;
	private static String Description;
	
	public static void main(String[] args) throws Exception {
		System.out.println("The Server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		ServerSocket clistener = new ServerSocket(CPORT);
		ServerSocket hlistener = new ServerSocket(HPORT);
		
		try {
			while (true) {
				new Handler(listener.accept()).start();
				new ChatThread(clistener.accept()).start();
				new ChatThread(hlistener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class Handler extends Thread {
		private Socket socket;
		private BufferedReader in; // 클라이언트로부터 데이터를 수신받기 위한
		private static PrintWriter out; // 클라이언트에게 데이터를 내보내기 위한

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("서버 run이다");
			try {
				// in = new BufferedReader(new
				// InputStreamReader(socket.getInputStream()));
				// out = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String input = "";
					int inx = 0;
					for (int i = 0; i < 4; i++)
						type[i] = -1; // initialization
					System.out.println("server : before while loop.");

					if ((input = in.readLine()).equals("SELECTCOMPLETE")) {
						System.out.println("server : complete button");
						while (!(input = in.readLine()).equals("null")) {
							if (input.startsWith("food")) {
								input = input.substring(4);
								System.out.println("foodtypeidx: " + input);
								type[inx] = Integer.parseInt(input);
								inx++;
							} else if (input.startsWith("priority")) {
								input = input.substring(8);
								System.out.println("priorityidx: " + input);
								prio = Integer.parseInt(input);
							}
							// else{
							// System.out.println("서버...오류");
							// }
						}
						System.out.println("COMPLETE: out of while loop!!!");
						callSelect("NOTALONE");
						////////////////////////////// 서버가 callSelect로 얻은 값을 디비로
						////////////////////////////// 잘 읽었다고 가정하고 뒤에 코딩함.


						try {
							Class.forName("org.gjt.mm.mysql.Driver");
							System.out.println("!!");
						} catch (ClassNotFoundException e) {
							System.out.println("!");
						}Connection con = null;
						String url = "jdbc:mysql://localhost:3306/termp";//?useUnicode=true&characterEncoding=utf8";
						String user = "root";
						String pass = "12345";
						try {
							con = DriverManager.getConnection(url, user, pass);
							System.out.println("My-SQL !!");
						} catch (SQLException e) {
							System.out.println("My-SQL!");
						}
						DBPrint(T, F, D, con);
						
						
						//e.printStackTrace();
					}
				else if (input.equals("EATALONE")) {
						System.out.println("server : eatalone button");
						while (!(input = in.readLine()).equals("null")) {
							if (input.startsWith("food")) {
								input = input.substring(4);
								System.out.println("foodtypeidx: " + input);
								type[inx] = Integer.parseInt(input);
								inx++;
							} else if (input.startsWith("priority")) {
								input = input.substring(8);
								System.out.println("priorityidx: " + input);
								prio = Integer.parseInt(input);
							}
							// else{
							// System.out.println("서버...오류");
							// }
						}
						System.out.println("EATALONE: out of while loop!!!");
						//ChatClient chat = new ChatClient();
						callSelect("ALONE");
						ChatMng manage = new ChatMng(T);
						///////////// chatclient 부르고 수정하기!! thread 생각해보기
						Thread.sleep(1000);
					}
				

			}
			
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end.");
		}

			private void DBPrint(int t, int f, int d, Connection con) throws UnsupportedEncodingException, SQLException {
				//System.out.println("���ɿ� "+t);
				PreparedStatement ps = null;
				ResultSet rs = null;
				//String name = new String(rs.getString("name").getBytes("8859_1"),"EUC_KR");
				try {
					String sql = null;
					//"select T_ID from type where T_id =1";
					sql = "select NAME from type where T_ID ="+t;
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while (rs.next()) {
						//String name = rs.getString(1);
						TypeName = rs.getString(1);
					}
					sql = null;
					sql = "select name from food where f_id ="+f;
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						FoodName = rs.getString(1);
					}
					sql = null;
					sql = "select RESTAURANT  from description where id ="+d;
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						Description = rs.getString(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("DBResult: "+TypeName+FoodName+Description);
				out.println(TypeName);
				out.println(FoodName);
				out.println(Description);
			
		}

		private static void callSelect(String check) {
			// TODO Auto-generated method stub
			System.out.println("callSelect함수 :" + type[0] + ", " + type[1]);
			Select selcet = new Select(type, prio);
			///////////////// 여기서 디비정보 읽어서 보내주기!!
			if (check.equals("NOTALONE")) {
				out.println("RESULT");
				T = selcet.Type;
				F = selcet.Food;
				D = selcet.Desc; 
//				out.println(T);
//				out.println(F);
//				out.println(D);
			} else if (check.equals("ALONE")) {
				////// 여기서 chatclient를 쓰레드로 해보기
				////// 일단은 결과를 화면에 출력
				T = selcet.Type;
				F = selcet.Food;
				D = selcet.Desc; 				
//				out.println(T);
//				out.println(F);
//				out.println(D);
				T=Select.Type;
			} else {
				System.out.println("음... callset error");
			}
		}
	}// 여기까지는 Handler class
	
	public static class ChatMng{ //채팅방관리  // extends Thread 
//		public int[][] chatroomNum = new int[4][10];
//		public Socket[][] chatroomSocket = new Socket[4][10];
//		private boolean isInitialized = false; 
		
		public ChatMng(int T){
//			if(!isInitialized){
//				for(int i=0;i<4;i++){
//					for(int j=0;j<10;j++){
//						chatroomNum[i][j]=0;
//					}
//				}
//				isInitialized=true;
//			}
//			
//			for(int j=0;j<10;j++){
//				if(chatroomNum[T][j]<5){
//					chatroomNum[T][j]++;
//					break;
//				}
//				if(j==11){
//					//chatting room 꽉참.
//				}
//			}
			try {
				ChatClient client = new ChatClient(T);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static class ChatThread extends Thread {
		private Socket socket;
		private BufferedReader in; // 클라이언트로부터 데이터를 수신받기 위한
		private PrintWriter out; // 클라이언트에게 데이터를 내보내기 위한
		private String exit_name = null;
		private String name = null;
		private static ArrayList<String> names = new ArrayList<String>();
		private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
		
		ChatThread(Socket socket){
			this.socket=socket;
		}
//		public Socket getsocket(){
//			return socket;
//		}
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("chatthread run이다");
			
			try {
				while (true) {
					out.println("SUBMITNAME");				
					name = in.readLine();
					if (name == null)
						return;
					synchronized (names){
						if (!names.contains(name)) {
							names.add(name); 
							break;
						}
						else{
							out.println("REPEATEDNAME");
						}
					}
				}
				out.println("NAMEACCEPTED");
				writers.add(out); 
				for (PrintWriter writer : writers){ 
					writer.println("ENTRANCE"+name);//////////
				}

				 while (true){ 
						String input = in.readLine();
						if (input == null)
							return;//리턴
					  String tok[] = input.split("@");
					  
					  if(tok.length>1){
						  for(int i=0; i<writers.size(); i++){
							  if(tok[0].equals(names.get(i))){
								writers.get(i).println("WHISPER" + name + ": " + tok[1]);
							  }
						  }
					  } else {
						  for (PrintWriter writer : writers){ 
						  writer.println("MESSAGE " + name + ": " + input);
						  }
					  }
				 }
				// Thread.sleep(500);

			} catch (Exception e) {

			}
			finally
			{
				if (name != null)
				{
					exit_name = name;
					names.remove(name);
				}
				if (out != null) {
					writers.remove(out);
				}
				try {
					socket.close();
					for (PrintWriter writer : writers)
					{
						writer.println("EXIT" + exit_name);
					}
					Thread.sleep(100);
				} catch (IOException e) {

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("thread end.");
			
		}//run
	}///ChatThread class끝

}////////여기 괄호!!