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
		
		JPanel authorPanel = new JPanel();
		authorPanel.setBounds(100, 150, 700, 50);
		JLabel authorLabel = new JLabel("저   자 : ");
		authorLabel.setFont(ft);
		JTextField authorTextField = new JTextField(20);
		authorTextField.setFont(ft);
		authorPanel.add(authorLabel);
		authorPanel.add(authorTextField);
		add(authorPanel);
		
		JPanel descPanel = new JPanel();
		descPanel.setBounds(100, 200, 700, 50);
		JLabel descLabel = new JLabel("설   명 : ");
		descLabel.setFont(ft);
		JTextField descTextField = new JTextField(20);
		descTextField.setFont(ft);
		descPanel.add(descLabel);
		descPanel.add(descTextField);
		add(descPanel);
		
		JPanel descPanel = new JPanel();
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}