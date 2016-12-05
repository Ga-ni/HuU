
import java.util.Iterator;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {

	private static final int PORT = 1121;// 서버가 사용하는 포트
	private static final int CPORT =1122;

	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();

	// static Select selcet;
	private static int[] type = { -1, -1, -1, -1 };
	private static int prio;

	public static void main(String[] args) throws Exception {
		System.out.println("The Server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		ServerSocket clistener = new ServerSocket(CPORT);
		try {
			while (true) {
				new Handler(listener.accept()).start();
				new ChatThread(clistener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class Handler extends Thread {
		private String exit_name;/////////////////////////////////
		private String name;//////////////////////////
		private Socket socket;
		private BufferedReader in; // 클라이언트로부터 데이터를 수신받기 위한
		private PrintWriter out; // 클라이언트에게 데이터를 내보내기 위한

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

					} else if (input.equals("EATALONE")) {
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
						ChatClient chat = new ChatClient();

						// callSelect("ALONE"); //////////////// 이거 수정하기!!!!
						///////////// chatclient 부르고 수정하기!! thread 생각해보기
						Thread.sleep(1000);
					}
				}

			} catch (IOException e) {
				System.out.println(e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end.");
		}

		private void callSelect(String check) {
			// TODO Auto-generated method stub
			System.out.println("callSelect함수 :" + type[0] + ", " + type[1]);
			Select selcet = new Select(type, prio);
			///////////////// 여기서 디비정보 읽어서 보내주기!!
			if (check.equals("NOTALONE")) {
				out.println("RESULT");
				out.println(selcet.Type);
				out.println(selcet.Food);
				out.println(selcet.Desc);
			} else if (check.equals("ALONE")) {
				////// 여기서 chatclient를 쓰레드로 해보기
				////// 일단은 결과를 화면에 출력
				out.println("RESULT");
				out.println(selcet.Type);
				out.println(selcet.Food);
				out.println(selcet.Desc);
			} else {
				System.out.println("음... callset error");
			}
		}

		// finally {
		//
		// if (name != null) // 네임이 null값이 아니면
		// {
		// exit_name = name; // name을 exit_name에 저장시킨다.
		// names.remove(name); // names Array list에서 종료된 클라이언트의 name을
		// // 삭제한다.
		// }
		// if (out != null) {
		//
		// writers.remove(out); // writers Array list에서 종료된 클라이언트의 out을
		// // 삭제한다.
		// }
		// try {
		// socket.close(); // 소켓을 종료시킨다.
		// for (PrintWriter writer : writers) // writer Array list에 있는
		// // 모든 writer들에 대해
		// {
		// writer.println("EXIT" + exit_name); // 모든 클라이언트들에게 "EXIT
		// // 종료한 클라이언트의 이름"
		// // format으로 데이터를
		// // 전송한다.
		// }
		// } catch (IOException e) {
		//
		// }
		// }
		// this.callSelect();
		// }

	}// 여기까지는 Handler class입니다.

	public static class ChatThread extends Thread {
		private Socket socket;
		private BufferedReader in; // 클라이언트로부터 데이터를 수신받기 위한
		private PrintWriter out; // 클라이언트에게 데이터를 내보내기 위한
		ChatThread(Socket socket){
			this.socket=socket;
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
			System.out.println("chatthread run이다");
			
			
			try{
				Thread.sleep(500);
			}catch(Exception e){
				
			}
			System.out.println("thread end.");
		}//run
	}///ChatThread class끝

}////////여기 괄호!!