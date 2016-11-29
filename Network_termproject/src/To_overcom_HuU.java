import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class To_overcom_HuU extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					To_overcom_HuU frame = new To_overcom_HuU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public To_overcom_HuU() {
		Font font;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1900, 1400);
		contentPane = new JPanel() {
			Image bg = new ImageIcon("배경.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		// contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// left panel(음식종류,음식,가격제한선)
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(200, 175, 700, 1025);
		contentPane.add(panel1);

		// 음식종류
		JPanel foodtypePanel = new JPanel();
		foodtypePanel.setBackground(new Color(102, 153, 0, 150));
		font = new Font("함초롬돋움", Font.BOLD, 40);
		LineBorder border = new LineBorder(new Color(0, 0, 0, 0), 3, true);
		foodtypePanel
				.setBorder(new TitledBorder(border, "음식종류", TitledBorder.CENTER, TitledBorder.TOP, font, Color.black));// border자리에
																														// 원래
																														// UIManager.getBorder("TitledBorder.border")
		foodtypePanel.setBounds(50, 50, 600, 200);
		foodtypePanel.setLayout(null);
		panel1.add(foodtypePanel);

		/*
		 * JPanel checkboxPanel = new JPanel(); checkboxPanel.setBorder(null);
		 * checkboxPanel.setLayout(null); checkboxPanel.setBackground(new
		 * Color(173, 255, 47)); checkboxPanel.setBounds(50, 50, 500, 90);
		 */

		JCheckBox[] typeOfFood = new JCheckBox[5];
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
		// foodtypePanel.add(checkboxPanel);

		// 우선순위
		JPanel priorityPanel = new JPanel();
		priorityPanel.setBackground(new Color(0x8FBC8F));
		priorityPanel
				.setBorder(new TitledBorder(border, "우선순위", TitledBorder.CENTER, TitledBorder.TOP, font, Color.black));
		priorityPanel.setBounds(50, 300, 600, 200);
		priorityPanel.setLayout(null);

		JComboBox priority = new JComboBox();
		priority.setBackground(Color.white);
		priority.setBounds(200, 70, 200, 60);
		priority.setEditable(false);
		priority.setModel(new DefaultComboBoxModel(new String[] { "선택하세요", "추천순", "거리순", "가격순" }));
		priority.setFont(new Font("함초롬돋움", Font.PLAIN, 30));
		priority.setSelectedIndex(0);
		priority.setMaximumRowCount(4);
		priority.setForeground(new Color(0, 0, 0));
		priorityPanel.add(priority);

		panel1.add(priorityPanel);

		// 선택완료 & 혼밥 버튼패널
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new Color(0xEAEAEA));
		ButtonPanel.setLayout(null);
		ButtonPanel.setBounds(50, 550, 600, 400);
		panel1.add(ButtonPanel);
		
		JPanel completePanel = new JPanel();
		completePanel.setLayout(null);
		completePanel.setBounds(0, 0, 300, 400);
		completePanel.setBackground(new Color(3, 3, 3, 0));
		completePanel.setOpaque(false);
		
		JButton completeButton = new JButton(new ImageIcon(
				((new ImageIcon("선택완료샐리.png")).getImage()).getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH)));
		completeButton.setOpaque(false);
		completeButton.setBackground(new Color(0, 0, 0, 0));
		completeButton.setBorderPainted(false);
		completeButton.setBounds(0, 50, 300, 250);
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

		JButton Button = new JButton(new ImageIcon(
				((new ImageIcon("혼밥샐리.png")).getImage()).getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH)));
		Button.setOpaque(false);
		Button.setBackground(new Color(0, 0, 0, 0));
		Button.setBorderPainted(false);
		Button.setBounds(0, 50, 300, 250);
		JLabel btLabel = new JLabel(new ImageIcon(
				((new ImageIcon("혼밥라벨.png")).getImage()).getScaledInstance(250, 80, java.awt.Image.SCALE_SMOOTH)));
		btLabel.setBounds(0, 300, 300, 80);
		eat_alone.add(Button);
		eat_alone.add(btLabel);
		ButtonPanel.add(eat_alone);
		

		// Right Panel(선택된 음식종류, 선택된 음식, 식당정보)
		JPanel panel3 = new JPanel();
		panel3.setSize(700, 1025);
		panel3.setLocation(992, 175);
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

		JLabel blackRec = new JLabel(new ImageIcon(
				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackRec.setBounds(96, 40, 347, 106);
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

		JLabel blackR = new JLabel(new ImageIcon(
				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackR.setBounds(96, 40, 347, 106);
		selectedFood.add(blackR);

		// 식당정보
		JPanel restaurant = new JPanel() {
			Image bg = new ImageIcon("그림1.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		restaurant.setBounds(47, 400, 600, 600);
		panel3.add(restaurant);
	}
}
