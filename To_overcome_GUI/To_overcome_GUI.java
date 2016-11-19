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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2200, 1400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		panel1.setLayout(null);
		panel1.setBounds(200, 100, 700, 1100);
		contentPane.add(panel1);

		JPanel foodtypePanel = new JPanel();
		foodtypePanel.setBackground(new Color(250, 240, 230, 0));
		Font f = new Font("돋움", Font.PLAIN, 30);
		foodtypePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "음식종류", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(138, 43, 226)));
		foodtypePanel.setBounds(50, 50, 600, 200);
		foodtypePanel.setLayout(null);
		panel1.add(foodtypePanel);

		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setBorder(null);
		checkboxPanel.setLayout(null);
		checkboxPanel.setBackground(new Color(255, 228, 225));
		checkboxPanel.setBounds(50, 50, 500, 90);

		JCheckBox[] typeOfFood = new JCheckBox[5];
		String[] typeoffood = { "한식", "중식", "일식", "분식", "전체" };

		int i = 0;
		for (i = 0; i < 5; i++) {
			typeOfFood[i] = new JCheckBox(typeoffood[i]);
			typeOfFood[i].setBounds(50 + (70 * i + 20), 30, 70, 30);
			typeOfFood[i].setBorderPainted(true);
			typeOfFood[i].setFont(new Font("돋움", Font.CENTER_BASELINE, 20));
			typeOfFood[i].setBackground(new Color(250, 250, 250, 250));
			checkboxPanel.add(typeOfFood[i]);
		}
		foodtypePanel.add(checkboxPanel);

		JPanel priorityPanel = new JPanel();
		priorityPanel.setBackground(new Color(250, 240, 230, 0));
		priorityPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "우선순위", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(75, 0, 130)));
		priorityPanel.setBounds(50, 350, 600, 200);
		priorityPanel.setLayout(null);

		JComboBox priority = new JComboBox();
		priority.setBackground(new Color(255, 228, 225));
		priority.setBounds(200, 70, 200, 60);
		priority.setEditable(false);
		priority.setModel(new DefaultComboBoxModel(new String[] { "추천순", "거리순", "가격순" }));
		priority.setSelectedIndex(-1);
		priority.setMaximumRowCount(3);
		priority.setForeground(new Color(0, 0, 0));
		priorityPanel.add(priority);

		panel1.add(priorityPanel);

		JPanel pricerangePanel = new JPanel();
		pricerangePanel.setBackground(new Color(250, 240, 230));
		pricerangePanel.setBounds(50, 650, 600, 400);
		panel1.add(pricerangePanel);

		JPanel panel2 = new JPanel();
		panel2.setSize(250, 200);
		panel2.setLocation(950, 1000);
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

		JPanel panel3 = new JPanel();
		panel3.setSize(700, 1100);
		panel3.setLocation(1250, 100);
		contentPane.add(panel3);
		panel3.setLayout(null);

		JPanel selectedFoodtype = new JPanel();
		selectedFoodtype.setBounds(250, 50, 250, 150);
		panel3.add(selectedFoodtype);

		JPanel selectedFood = new JPanel();
		selectedFood.setBounds(250, 250, 250, 150);
		panel3.add(selectedFood);

		JPanel restaurant = new JPanel();
		restaurant.setBounds(50, 450, 600, 600);
		panel3.add(restaurant);
	}
}
