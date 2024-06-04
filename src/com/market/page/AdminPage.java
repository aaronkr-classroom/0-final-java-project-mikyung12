package com.market.page;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPage extends JPanel {
	
	public AdminPage(JPanel panel) {
		
		Font ft;
		ft = new Font("함초롬돋움", Font.BOLD, 15);
		
		setLayout(null);
		
		Rectangle rect = panel.getBounds();
		JPanel authorPanel = new JPanel();
		System.out.println(rect);
		setPreferredSize(rect.getSize());
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
		String strDate = formatter.format(date);
		
		JPanel idPanel = new JPanel();
		idPanel.setBounds(100, 0, 700, 50);
		JLabel idLabel = new JLabel("도서ID : ");
		idLabel.setFont(ft);
		JLabel idTextField = new JLabel();
		idTextField.setFont(ft);
		idTextField.setPreferredSize(new Dimension(290, 50));
		idTextField.setText("ISBN" + strDate);
		idPanel.add(idLabel);
		idPanel.add(idTextField);
		add(idPanel);
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(100, 50 ,700, 50);
		JLabel nameLabel = new JLabel("도서명 : ");
		nameLabel.setFont(ft);
		JTextField nameTextField = new JTextField(20);
		nameTextField.setFont(ft);
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		add(namePanel);
		
		JPanel pricePanel = new JPanel();
		pricePanel.setBounds(100, 100, 700, 50);
		JLabel priceLabel = new JLabel(" 가  격 :");
		priceLabel.setFont(ft);
		JTextField priceTextField = new JTextField(20);
		priceTextField.setFont(ft);
		pricePanel.add(priceLabel);
		pricePanel.add(priceTextField);
		add(pricePanel);
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
