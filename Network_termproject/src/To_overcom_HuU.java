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
	static JLabel[] restaurantInfo = new JLabel[6];
	private JPanel contentPane;
	BufferedReader in;
	PrintWriter out;
	int[] foodIndex = { -1, -1, -1, -1 };
	int priorityIndex = -1;
	static int type = -1;
	static int food = -1;
	static int des = -1;

	public To_overcom_HuU() {
		Font font;
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

		// L panel(음식종류,우선순위)
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBounds(170, 100, 700, 830);
		contentPane.add(leftPanel);

		// L panel-음식종류
		JPanel foodtypePanel = new JPanel();
		foodtypePanel.setBackground(new Color(118, 183, 80));
		font = new Font("배달의민족 도현", Font.BOLD, 40);
		LineBorder border = new LineBorder(new Color(118, 183, 80), 3, true);
		foodtypePanel.setBounds(50, 50, 600, 200);
		foodtypePanel.setLayout(null);
		JLabel larva = new JLabel(new ImageIcon(
				((new ImageIcon("윙크애벌레.png")).getImage()).getScaledInstance(150, 145, java.awt.Image.SCALE_SMOOTH)));
		larva.setBounds(20, 0, 150, 150);
		JLabel FT = new JLabel();
		FT.setBounds(270, 50, 400, 80);
		FT.setFont(font);
		FT.setText("음식종류");

		leftPanel.add(FT);
		leftPanel.add(larva);
		leftPanel.add(foodtypePanel);

		String[] typeoffood = { "한식", "중식", "일식", "분식", "전체" };
		int i = 0;
		for (i = 0; i < 5; i++) {
			typeOfFood[i] = new JCheckBox(typeoffood[i]);
			typeOfFood[i].setBounds(50 + (90 + 10) * i, 95, 90, 50);
			typeOfFood[i].setBorderPainted(true);
			typeOfFood[i].setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 30));
			typeOfFood[i].setBackground(new Color(250, 250, 250, 250));
			foodtypePanel.add(typeOfFood[i]);
		}
		// 전체를 누르면 모든 타입이 선택되도록 함
		typeOfFood[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeOfFood[4].isSelected()) {
					for (int i = 0; i < 4; i++)
						typeOfFood[i].setSelected(true);
				}
			}

		});

		// L panel->priority panel->우선순위
		JPanel priorityPanel = new JPanel();
		priorityPanel.setBackground(new Color(0, 153, 51));
		priorityPanel.setBounds(50, 320, 600, 200);
		priorityPanel.setLayout(null);
		JLabel frog = new JLabel(new ImageIcon(
				((new ImageIcon("뽀뽀개구리.png")).getImage()).getScaledInstance(155, 155, java.awt.Image.SCALE_SMOOTH)));
		frog.setBounds(20, 250, 155, 155);
		JLabel P = new JLabel();
		P.setBounds(270, 320, 360, 80);
		P.setFont(new Font("배달의민족 도현", Font.BOLD, 40));
		P.setText("우선순위");
		leftPanel.add(P);
		leftPanel.add(frog);

		priority.setBackground(Color.white);
		priority.setBounds(200, 90, 200, 60);
		priority.setEditable(false);
		priority.setModel(new DefaultComboBoxModel(new String[] { "선택하세요", "추천순", "거리순", "가격순" }));
		priority.setFont(new Font("배달의민족 도현", Font.PLAIN, 30));
		priority.setSelectedIndex(0);
		priority.setMaximumRowCount(4);
		priority.setForeground(new Color(0, 0, 0));
		priorityPanel.add(priority);

		leftPanel.add(priorityPanel);

		// L panel-> ButtonPanel-> 선택완료버튼 & 혼밥버튼
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new Color(0xEAEAEA));
		ButtonPanel.setLayout(null);
		ButtonPanel.setBounds(50, 450, 600, 300);
		leftPanel.add(ButtonPanel);

		JButton completeButton = new JButton(new ImageIcon(
				((new ImageIcon("선택완료샐리.png")).getImage()).getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH)));
		completeButton.setOpaque(false);
		completeButton.setBackground(new Color(0, 0, 0, 0));
		completeButton.setBorderPainted(false);
		completeButton.setBounds(0, 70, 300, 250);
		completeButton.addActionListener((ActionListener) this); // 선택완료버튼 액션 line:272
		ButtonPanel.add(completeButton);

		JButton eataloneButton = new JButton(new ImageIcon(
				((new ImageIcon("혼밥샐리.png")).getImage()).getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH)));
		eataloneButton.setOpaque(false);
		eataloneButton.setBackground(new Color(0, 0, 0, 0));
		eataloneButton.setBorderPainted(false);
		eataloneButton.setBounds(300, 70, 300, 250);
		eataloneButton.addActionListener(new ActionListener() {

			@Override
			// 혼밥버튼 액션
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Client cli = new Client();

				int uncheckedCount = 0;
				for (int i = 0; i < 4; i++) {
					foodIndex[i] = -1;
				}

				if (typeOfFood[4].isSelected()) {
					for (int i = 0; i < 4; i++) {
						foodIndex[i] = i;
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
				System.out.println("priority: " + priorityIndex);
				// foodindex와 priorityindex를 eataloneInforming 메소드로 client에게 넘겨줌
				cli.eataloneInforming(foodIndex, priorityIndex);
			}
		});

		ButtonPanel.add(eataloneButton);

		// R Panel(선택된 음식종류, 선택된 음식, 식당정보)
		JPanel panel3 = new JPanel();
		panel3.setSize(700, 830);
		panel3.setLocation(992, 100);
		panel3.setLayout(null);
		contentPane.add(panel3);

		// R Panel-선택된 음식종류
		JLabel Spin = new JLabel(new ImageIcon(
				((new ImageIcon("샐리꼬치.png")).getImage()).getScaledInstance(70, 110, java.awt.Image.SCALE_SMOOTH)));
		Spin.setBounds(325, 6, 70, 110);
		Spin.setBackground(null);
		panel3.add(Spin);

		blackRec = new JLabel();
		blackRec.setBounds(175, 70, 350, 105);
		blackRec.setText("");
		blackRec.setFont(font);
		blackRec.setHorizontalAlignment(SwingConstants.CENTER);
		blackRec.setOpaque(true);
		blackRec.setForeground(Color.black);
		blackRec.setBackground(new Color(247, 206, 60));
		panel3.add(blackRec);

		// R Panel-선택된 음식
		JLabel Bpin = new JLabel(new ImageIcon(
				((new ImageIcon("브라운꼬치.png")).getImage()).getScaledInstance(70, 110, java.awt.Image.SCALE_SMOOTH)));
		Bpin.setBounds(325, 181, 70, 110);
		Bpin.setBackground(null);
		panel3.add(Bpin);

		blackR = new JLabel();
		blackR.setBounds(175, 250, 350, 105);
		blackR.setText("");
		blackR.setFont(font);
		blackR.setHorizontalAlignment(SwingConstants.CENTER);
		blackR.setOpaque(true);
		blackR.setForeground(Color.WHITE);
		blackR.setBackground(new Color(108, 72, 58));
		panel3.add(blackR);

		// 식당정보
		JPanel restaurant = new JPanel() {
			Image bg = new ImageIcon("그림1.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		restaurant.setBounds(47, 400, 600, 400);
		restaurant.setLayout(null);

		for (int j = 0; j < 6; j++) {

			restaurantInfo[j] = new JLabel();
			if (j == 0) {
				restaurantInfo[j].setBounds(10, 100, 590, 50);
				restaurantInfo[j].setFont(new Font("배달의민족 도현", Font.BOLD, 40));
			} else {
				if (j == 5)
					restaurantInfo[j].setFont(new Font("배달의민족 도현", Font.BOLD, 25));
				else
					restaurantInfo[j].setFont(new Font("배달의민족 도현", Font.BOLD, 30));
				restaurantInfo[j].setBounds(10, 100 + (37 * j), 590, 100);
			}

			restaurantInfo[j].setHorizontalAlignment(SwingConstants.CENTER);
			restaurantInfo[j].setBackground(new Color(231, 191, 45));
			restaurantInfo[0].setBackground(Color.GREEN);
			restaurant.add(restaurantInfo[j]);
			panel3.add(restaurant);
		}
	}

	@Override
	// 선택완료버튼 액션
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		sendToClient a = new sendToClient();
	}

	public class sendToClient {
		sendToClient() {
			int uncheckedCount = 0;
			for (int i = 0; i < 4; i++) {
				foodIndex[i] = -1;
			}

			if (typeOfFood[4].isSelected()) {
				for (int i = 0; i < 4; i++) {
					foodIndex[i] = i;
					// out.println("food" + foodIndex[i]);
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
			// foodindex와 priorityindex를 completeInforming 메소드로 client에게 보내줌
			call.completeInforming(foodIndex, priorityIndex);
		}

	}
}
