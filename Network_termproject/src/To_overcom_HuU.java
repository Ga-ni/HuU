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
		setBounds(100, 100, 2200, 1400);
		contentPane = new JPanel() {
			Image bg = new ImageIcon("배경.png").getImage();

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		//contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//left panel(음식종류,음식,가격제한선)
		JPanel panel1 = new JPanel();
		panel1.setBackground(UIManager.getColor("Button.background"));
		panel1.setLayout(null);
		panel1.setBounds(200, 175, 700, 1025);
		contentPane.add(panel1);

			//음식종류
		JPanel foodtypePanel = new JPanel();
		foodtypePanel.setBackground(new Color(102,153,0,150));
		font=new Font("돋움",Font.BOLD,40);
		foodtypePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "음식종류", TitledBorder.LEFT, TitledBorder.TOP, font, Color.black));
		foodtypePanel.setBounds(50, 50, 600, 200);
		foodtypePanel.setLayout(null);
		panel1.add(foodtypePanel);

		/*JPanel checkboxPanel = new JPanel();
		checkboxPanel.setBorder(null);
		checkboxPanel.setLayout(null);
		checkboxPanel.setBackground(new Color(173, 255, 47));
		checkboxPanel.setBounds(50, 50, 500, 90);*/

		JCheckBox[] typeOfFood = new JCheckBox[5];
		String[] typeoffood = { "한식", "중식", "일식", "분식", "전체" };

		int i = 0;
		for (i = 0; i < 5; i++) {
			typeOfFood[i] = new JCheckBox(typeoffood[i]);
			typeOfFood[i].setBounds(50 + (90 + 10) * i, 80, 90, 50);
			typeOfFood[i].setBorderPainted(true);
			typeOfFood[i].setFont(new Font("돋움", Font.CENTER_BASELINE, 30));
			typeOfFood[i].setBackground(new Color(250, 250, 250, 250));
			foodtypePanel.add(typeOfFood[i]);
		}
		//foodtypePanel.add(checkboxPanel);

			//우선순위
		JPanel priorityPanel = new JPanel();
		priorityPanel.setBackground(new Color(0x8FBC8F));
		priorityPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "우선순위", TitledBorder.LEFT, TitledBorder.TOP, font, Color.black));
		priorityPanel.setBounds(50, 275, 600, 200);
		priorityPanel.setLayout(null);

		JComboBox priority = new JComboBox();
		priority.setBackground(new Color(255, 228, 225));
		priority.setBounds(200, 70, 200, 60);
		priority.setEditable(false);
		priority.setModel(new DefaultComboBoxModel(new String[] { "선택하세요","추천순", "거리순", "가격순" }));
		priority.setFont(new Font("돋움", Font.PLAIN, 30));
		priority.setSelectedIndex(0);
		priority.setMaximumRowCount(4);
		priority.setForeground(new Color(0, 0, 0));
		priorityPanel.add(priority);

		panel1.add(priorityPanel);

			//가격제한선
		JPanel pricerangePanel = new JPanel();
		pricerangePanel.setBackground(new Color(0xEAEAEA));
		pricerangePanel.setBounds(50, 500, 600, 470);
		panel1.add(pricerangePanel);

		
		//Center Panel(혼밥버튼)
		JPanel panel2 = new JPanel();
		panel2.setSize(250, 200);
		panel2.setLocation(896, 1002);
		panel2.setBackground(new Color(3, 3, 3, 0));
		contentPane.add(panel2);

		JButton Button = new JButton(new ImageIcon(
				((new ImageIcon("혼자버튼.png")).getImage()).getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH)));
		Button.setBackground(new Color(0, 0, 0, 0));
		Button.setBorderPainted(false);
		panel2.add(Button, BorderLayout.NORTH);

		JLabel btLabel = new JLabel("혼자오셨어요..?");
		btLabel.setFont(new Font("돋움", Font.BOLD, 30));
		btLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		panel2.add(btLabel, BorderLayout.CENTER);

		
		//Right Panel(선택된 음식종류, 선택된 음식, 식당정보)
		JPanel panel3 = new JPanel();
		panel3.setBackground(UIManager.getColor("Button.background"));
		panel3.setSize(700, 1025);
		panel3.setLocation(1247, 175);
		contentPane.add(panel3);
		panel3.setLayout(null);

			//선택된 음식종류
		JPanel selectedFoodtype = new JPanel();
		selectedFoodtype.setBounds(87, 50, 522, 150);
		panel3.add(selectedFoodtype);
		selectedFoodtype.setLayout(null);

		JLabel redTr = new JLabel(new ImageIcon(
				((new ImageIcon("그림2.png")).getImage()).getScaledInstance(130, 60, java.awt.Image.SCALE_SMOOTH)));
		redTr.setBounds(196, 0, 154, 44);
		redTr.setBackground(null);
		selectedFoodtype.add(redTr);

		JLabel blackRec = new JLabel(new ImageIcon(
				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackRec.setBounds(96, 40, 347, 106);
		blackRec.setBackground(null);
		selectedFoodtype.add(blackRec);

			//선택된 음식
		JPanel selectedFood = new JPanel();
		selectedFood.setBounds(87, 230, 522, 150);
		panel3.add(selectedFood);
		selectedFood.setLayout(null);

		JLabel redT = new JLabel(new ImageIcon(
				((new ImageIcon("그림2.png")).getImage()).getScaledInstance(130, 60, java.awt.Image.SCALE_SMOOTH)));
		redT.setBounds(201, 0, 154, 44);
		redT.setBackground(null);
		selectedFood.add(redT);

		JLabel blackR = new JLabel(new ImageIcon(
				((new ImageIcon("그림3.png")).getImage()).getScaledInstance(347, 106, java.awt.Image.SCALE_SMOOTH)));
		blackR.setBounds(96, 40, 347, 106);
		blackR.setBackground(null);
		selectedFood.add(blackR);

			//식당정보
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
