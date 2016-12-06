import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.*;

public class To_overcom_HuU extends JFrame implements ActionListener {
	Client call = new Client();
	JCheckBox[] typeOfFood = new JCheckBox[5];
	JComboBox priority = new JComboBox();
	static JLabel blackRec;
	static JLabel blackR;
	private JPanel contentPane;
	BufferedReader in;
	PrintWriter out;
	int[] foodIndex = { -1, -1, -1, -1 };
	int priorityIndex = -1;
	static int type=-1;
	static int food=-1;
	static int des=-1;

	public To_overcom_HuU() {
		Font font;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 1000);
		contentPane = new JPanel() {
			Image bg = new ImageIcon("배경.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// left panel(음식종류,음식,가격제한선)
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBounds(170, 100, 700, 830);
		contentPane.add(leftPanel);

		// 음식종류
		JPanel foodtypePanel = new JPanel();
		foodtypePanel.setBackground(new Color(102, 153, 0, 150));
		font = new Font("함초롬돋움", Font.BOLD, 40);
		LineBorder border = new LineBorder(new Color(0, 0, 0, 0), 3, true);
		foodtypePanel
				.setBorder(new TitledBorder(border, "음식종류", TitledBorder.CENTER, TitledBorder.TOP, font, Color.black));// border자리에원래UIManager.getBorder("TitledBorder.border")
		foodtypePanel.setBounds(50, 50, 600, 150);
		foodtypePanel.setLayout(null);
		leftPanel.add(foodtypePanel);
		String[] typeoffood = { "한식", "중식", "일식", "분식", "전체" };
		int i = 0;
		for (i = 0; i < 5; i++) {
			typeOfFood[i] = new JCheckBox(typeoffood[i]);
			typeOfFood[i].setBounds(50 + (90 + 10) * i, 80, 90, 50);
			typeOfFood[i].setBorderPainted(true);
			typeOfFood[i].setFont(new Font("함초롬돋움", Font.CENTER_BASELINE, 30));
			typeOfFood[i].setBackground(new Color(250, 250, 250, 250));
			foodtypePanel.add(typeOfFood[i]);
		}

		typeOfFood[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (typeOfFood[4].isSelected()) {
					for (int i = 0; i < 4; i++) {
						typeOfFood[i].setSelected(true);
					}
				}
			}
		});

		// 우선순위
		JPanel priorityPanel = new JPanel();
		priorityPanel.setBackground(new Color(0x8FBC8F));
		priorityPanel
				.setBorder(new TitledBorder(border, "우선순위", TitledBorder.CENTER, TitledBorder.TOP, font, Color.black));
		priorityPanel.setBounds(50, 250, 600, 150);
		priorityPanel.setLayout(null);

		priority.setBackground(Color.white);
		priority.setBounds(200, 70, 200, 60);
		priority.setEditable(false);
		priority.setModel(new DefaultComboBoxModel(new String[] { "선택하세요", "추천순", "거리순", "가격순" }));
		priority.setFont(new Font("한초롬돋움", Font.PLAIN, 30));
		priority.setSelectedIndex(0);
		priority.setMaximumRowCount(4);
		priority.setForeground(new Color(0, 0, 0));
		priorityPanel.add(priority);

		leftPanel.add(priorityPanel);

		// 선택완료 & 혼밥 버튼패널
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new Color(0xEAEAEA));
		ButtonPanel.setLayout(null);
		ButtonPanel.setBounds(50, 400, 600, 400);
		leftPanel.add(ButtonPanel);

		JPanel completePanel = new JPanel();
		completePanel.setLayout(null);
		completePanel.setBounds(0, 0, 300, 400);
		completePanel.setBackground(new Color(3, 3, 3, 0));
		completePanel.setOpaque(false);

		JButton completeButton = new JButton(new ImageIcon(
				((new ImageIcon("선택완료샐리.png")).getImage()).getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH)));
		completeButton.setOpaque(false);
		completeButton.setBackground(new Color(0, 0, 0, 0));
		completeButton.setBorderPainted(false);
		completeButton.setBounds(0, 50, 300, 250);
		//completeButton.addActionListener((ActionListener) this);
		completeButton.addActionListener((ActionListener) this);
		// completeButton.addActionListener();
		// completeButton.setActionCommand(Integer.toString(1));
		// button[i].addActionListener(this);//new CreatePersonButtonListener()
		JLabel completeLabel = new JLabel(new ImageIcon(
				((new ImageIcon("선택완료라벨.png")).getImage()).getScaledInstance(250, 80, java.awt.Image.SCALE_SMOOTH)));
		completeLabel.setBounds(0, 300, 300, 80);
		completePanel.add(completeButton);
		completePanel.add(completeLabel);
		ButtonPanel.add(completePanel);

		// 혼밥버튼패널(버튼+라벨)
		JPanel eat_alone = new JPanel();
		eat_alone.setLayout(null);
		eat_alone.setBounds(300, 0, 300, 470);
		eat_alone.setBackground(new Color(3, 3, 3, 0));
		eat_alone.setOpaque(false);

		JButton eataloneButton = new JButton(new ImageIcon(
				((new ImageIcon("혼밥샐리.png")).getImage()).getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH)));
		eataloneButton.setOpaque(false);
		eataloneButton.setBackground(new Color(0, 0, 0, 0));
		eataloneButton.setBorderPainted(false);
		eataloneButton.setBounds(0, 50, 300, 250);
		eataloneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Client cli = new Client();
				int uncheckedCount = 0;
				// TODO Auto-generated method stub
				for (int i = 0; i < 4; i++) {
					foodIndex[i] = -1;
				}

				if (typeOfFood[4].isSelected()) {
					for (int i = 0; i < 4; i++) {
						foodIndex[i] = i;
						//out.println("food" + foodIndex[i]);
					}
				}

				else {
					for (int i = 0, j = 0; i < 4; i++) {
						if (typeOfFood[i].isSelected()) {
							foodIndex[j] = i;
							//out.println("food" + foodIndex[j]);
							j++;

						} else
							uncheckedCount++;
					}
				}

				// error message
				if (uncheckedCount == 4) {
					JOptionPane.showMessageDialog(null, "Please select at least one food type.", "Select food type!",
							JOptionPane.WARNING_MESSAGE);
					uncheckedCount = 0;
				}
				if (priority.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please select one priority.", "Select priority!",
							JOptionPane.WARNING_MESSAGE);
				}

				for (int i = 0; i < 4; i++) {
					System.out.println(foodIndex[i]);
				}
				priorityIndex = priority.getSelectedIndex();
				//out.println("priority" + priorityIndex);
				System.out.println("dd" + priorityIndex);
				cli.eataloneInformimg(foodIndex,priorityIndex);
			}
		});
		JLabel btLabel = new JLabel(new ImageIcon(
				((new ImageIcon("혼밥라벨.png")).getImage()).getScaledInstance(250, 80, java.awt.Image.SCALE_SMOOTH)));
		btLabel.setBounds(0, 300, 300, 80);
		eat_alone.add(eataloneButton);
		eat_alone.add(btLabel);
		ButtonPanel.add(eat_alone);

		// Right Panel(선택된 음식종류, 선택된 음식, 식당정보)
		JPanel panel3 = new JPanel();
		panel3.setSize(700, 830);
		panel3.setLocation(992, 100);
		contentPane.add(panel3);
		panel3.setLayout(null);

		// 선택된 음식종류
		JPanel selectedFoodtype = new JPanel();
		selectedFoodtype.setBounds(87, 50, 522, 150);
		panel3.add(selectedFoodtype);
		selectedFoodtype.setLayout(null);

		JLabel redTr = new JLabel(new ImageIcon(
				((new ImageIcon("그림2.png")).getImage()).getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH)));
		redTr.setBounds(196, 0, 154, 44);
		redTr.setBackground(null);
		selectedFoodtype.add(redTr);

//		blackRec = new JLabel(new ImageIcon(
//				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackRec = new JLabel();
		blackRec.setBounds(96, 40, 347, 106);
		blackRec.setText("");
		blackRec.setFont(new Font("함초롬바탕",Font.BOLD,30));
		blackRec.setHorizontalAlignment(SwingConstants.CENTER);
		blackRec.setOpaque(true);
		blackRec.setForeground(Color.WHITE);
		blackRec.setBackground(Color.black);
		selectedFoodtype.add(blackRec);

		// 선택된 음식
		JPanel selectedFood = new JPanel();
		selectedFood.setBounds(87, 230, 522, 150);
		panel3.add(selectedFood);
		selectedFood.setLayout(null);

		JLabel redT = new JLabel(new ImageIcon(
				((new ImageIcon("그림2.png")).getImage()).getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH)));
		redT.setBounds(201, 0, 154, 44);
		redT.setBackground(null);
		selectedFood.add(redT);

//		JLabel blackR = new JLabel(new ImageIcon(
//				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackR = new JLabel();
		blackR.setBounds(96, 40, 347, 106);
		blackR.setText("");
		blackR.setFont(new Font("함초롬바탕",Font.BOLD,30));
		blackR.setHorizontalAlignment(SwingConstants.CENTER);
		blackR.setOpaque(true);
		blackR.setForeground(Color.WHITE);
		blackR.setBackground(Color.black);
		selectedFood.add(blackR);

		// 식당정보
		JPanel restaurant = new JPanel() {
			Image bg = new ImageIcon("그림1.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		restaurant.setBounds(47, 400, 600, 400);
		panel3.add(restaurant);
	}

	

	public class sendToClient{
		sendToClient(){
			int uncheckedCount = 0;
			// TODO Auto-generated method stub
			for (int i = 0; i < 4; i++) {
				foodIndex[i] = -1;
			}

			if (typeOfFood[4].isSelected()) {
				for (int i = 0; i < 4; i++) {
					foodIndex[i] = i;
					//out.println("food" + foodIndex[i]);
				}
			}

			else {
				for (int i = 0, j = 0; i < 4; i++) {
					if (typeOfFood[i].isSelected()) {
						foodIndex[j] = i;
						j++;

					} else
						uncheckedCount++;
				}
			}

			// error message
			if (uncheckedCount == 4) {
				JOptionPane.showMessageDialog(null, "Please select at least one food type.", "Select food type!",
						JOptionPane.WARNING_MESSAGE);
				uncheckedCount = 0;
			}
			if (priority.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "Please select one priority.", "Select priority!",
						JOptionPane.WARNING_MESSAGE);
			}

			for (int i = 0; i < 4; i++) {
				System.out.println(foodIndex[i]);
			}
			priorityIndex = priority.getSelectedIndex();
			System.out.println("dd" + priorityIndex);

/////////////////////////////////////여기서 Client의 메소드나 컨스트럭터를 통해 값을 넘겨주고 그 값은 바로 서버에게 보내짐.
			call.completeInforming(foodIndex, priorityIndex);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//When user push complete button
		sendToClient a = new sendToClient();
	}
	
//	public static void ReadLine (int t, int f, int d)
//	{
//		type = t;
//		food = f;
//		des = d;
//		System.out.println("하하하하 "+type);
//		
//	}

	
	
	
//	public void actionPerformed(ActionEvent e) {
//		int uncheckedCount = 0;
//		// TODO Auto-generated method stub
//		for (int i = 0; i < 4; i++) {
//			foodIndex[i] = -1;
//		}
//
//		if (typeOfFood[4].isSelected()) {
//			for (int i = 0; i < 4; i++) {
//				foodIndex[i] = i;
//				out.println("food" + foodIndex[i]);
//			}
//			//return;
//		}
//
//		else {
//			for (int i = 0, j = 0; i < 4; i++) {
//				if (typeOfFood[i].isSelected()) {
//					foodIndex[j] = i;
//					out.println("food" + foodIndex[j]);
//					j++;
//
//				} else
//					uncheckedCount++;
//			}
//		}
//
//		// error message
//		if (uncheckedCount == 4) {
//			JOptionPane.showMessageDialog(null, "Please select at least one food type.", "Select food type!",
//					JOptionPane.WARNING_MESSAGE);
//			uncheckedCount = 0;
//		}
//		if (priority.getSelectedIndex() == 0) {
//			JOptionPane.showMessageDialog(null, "Please select one priority.", "Select priority!",
//					JOptionPane.WARNING_MESSAGE);
//		}
//
//		for (int i = 0; i < 4; i++) {
//			System.out.println(foodIndex[i]);
//		}
//		priorityIndex = priority.getSelectedIndex();
//		out.println("priority" + priorityIndex);
//		System.out.println("dd" + priorityIndex);		
//	}

//	private void run() throws IOException, InterruptedException {
//
//		Socket socket = new Socket("127.0.0.1", 1121);
//
//		System.out.println("tt1ttt "+type);
//		BufferedReader ii = new BufferedReader(new InputStreamReader( // ������ ���� �о���� input
//														// stream
//				socket.getInputStream()));
//		out = new PrintWriter(socket.getOutputStream(), true); // ������ �����͸� ������
//		//BufferedReader ii = new BufferedReader(new InputStreamReader(socket.getInputStream()));											// output stream
//		BufferedReader in;
//		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		//String line;
//	}

	
	
	
	
//	public static void main(String[] args) throws Exception {
//
//		To_overcom_HuU client = new To_overcom_HuU();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					client.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		client.run();
//	}
	

	
//아래 괄호 있다...!!!!! 잊지마!!	
}






