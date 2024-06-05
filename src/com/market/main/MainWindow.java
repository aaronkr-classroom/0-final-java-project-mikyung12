
package com.market.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.market.page.GuestInfoPage;
import com.market.cart.Cart;
import com.market.bookitem.BookInIt;
import com.market.page.CartAddItemPage;
import com.market.page.CartItemListPage;

public class MainWindow extends JFrame {
	static Cart mCart;
	static JPanel mMenuPanel, mPagePanel;
	
	public MainWindow(String title, int x, int y, int width, int height) {
		initContainer(title, x, y, width, height);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("./images/shop.png").getImage());
	}
	
	private void initContainer(String title, int x, int y, int width, int height) {
		setTitle(title);
		setBounds(x, y, width, height);
		setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - 1000) / 2, (screenSize.height - 750) / 2);
		
		mMenuPanel = new JPanel();
		mMenuPanel.setBounds(0, 20, width, 130);
		menuIntroduction();
		add(mMenuPanel);
		
		mPagePanel = new JPanel();
		mPagePanel.setBounds(0, 150, width, height);
		add(mPagePanel);		
		
		this.addWindowListener(new WindowAdapter () {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);  
				new GuestWindow("고객 정보 입력", 0, 0, 1000, 750);
			}
		});
	}
	
	private void menuIntroduction() {
		mCart = new Cart();
		Font ft = new Font("함초롬돋움", Font.BOLD, 15);
		
		JButton bt1 = new JButton("고객 정보 확인하기", new ImageIcon("./images/1.png"));
		bt1.setFont(ft);
		mMenuPanel.add(bt1);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mPagePanel.removeAll();  
				mPagePanel.add(new GuestInfoPage(mPagePanel));
				mPagePanel.revalidate();   
				mPagePanel.repaint();      
			}
		});
		
		JButton bt2 = new JButton("장바구니 상품 목록 보기", new ImageIcon("./images/2.png"));
		bt2.setFont(ft);
		mMenuPanel.add(bt2);
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mCart.mCartCount == 0) {
					JOptionPane.showMessageDialog(bt2, "장바구니에 항목이 없습니다", "장바구니 상품 목록 보기", JOptionPane.ERROR_MESSAGE);
				} else {
					mPagePanel.removeAll();
					mPagePanel.add(new CartItemListPage(mPagePanel, mCart));
					mPagePanel.revalidate();
					mPagePanel.repaint();
				}
			}
		});
		
		JButton bt3 = new JButton("장바구니 비우기", new ImageIcon("./images/3.png"));
		bt3.setFont(ft);
		mMenuPanel.add(bt3);
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mCart.mCartCount == 0) {
					JOptionPane.showMessageDialog(bt3, "장바구니에 항목이 없습니다", "장바구니 비우기", JOptionPane.ERROR_MESSAGE);
				} else { 
					mPagePanel.removeAll();
					menuCartClear(bt3);
					mPagePanel.add(new CartItemListPage(mPagePanel, mCart));
					mPagePanel.revalidate();
					mPagePanel.repaint();
				}
			}
		});

		JButton bt4 = new JButton("장바구니에 항목 추가하기", new ImageIcon("./images/4.png"));
		bt4.setFont(ft);
		mMenuPanel.add(bt4);
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mPagePanel.removeAll();
				BookInIt.init();
				mPagePanel.add(new CartAddItemPage(mPagePanel, mCart));
				mPagePanel.revalidate();
				mPagePanel.repaint();
			}
		});
		
		JButton bt5 = new JButton("장바구니에 항목 수량 줄이기", new ImageIcon("./images/5.png"));
		bt5.setFont(ft);
	    mMenuPanel.add(bt5);
	    
	    JButton bt6 = new JButton("장바구니에 항목 삭제하기", new ImageIcon("./images/6.png"));
	    bt6.setFont(ft);
	    mMenuPanel.add(bt6);
	    
	    bt6.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (mCart.mCartCount == 0)
	    			JOptionPane.showMessageDialog(bt3,  "장바구니에 항목이 없습니다", "장바구니 비우기", JOptionPane.ERROR_MESSAGE);
	    		else {
	    			
	    			mPagePanel.removeAll();
	    			CartItemListPage cartList = new CartItemListPage(mPagePanel, mCart);
	    			if (mCart.mCartCount == 0)
	    				JOptionPane.showMessageDialog(bt6, "장바구니에 항목이 없습니다");
	    			else if (cartList.mSelectRow == -1)
	    				JOptionPane.showMessageDialog(bt6,  "장바구니에서 삭제할 항목을 선택하세요");
	    			else {
	    				mCart.removeCart(cartList.mSelectRow);
	    				// 장바구니에서 선택 항목 삭제하기
	    				cartList.mSelectRow = -1;
	    			}
	    		}
	    		mPagePanel.add("장바구니의 항목 삭제하기", new CartItemListPage(mPagePanel, mCart));
	    		mPagePanel.revalidate();
	    		mPagePanel.repaint();
	    	}
	    });
	    
	    JButton bt7 = new JButton("주문하기", new ImageIcon("./images/7.png"));
	    bt7.setFont(ft);
	    mMenuPanel.add(bt7);
	    
	    JButton bt8 = new JButton("종료", new ImageIcon("./images/8.png"));
	    bt8.setFont(ft);
	    mMenuPanel.add(bt8);
	    
	    JButton bt9 = new JButton("관리자", new ImageIcon("./images/9.png"));
	    bt9.setFont(ft);
	    mMenuPanel.add(bt9);	
	}
	
	private void menuCartClear(JButton button) {
		if (mCart.mCartCount == 0) {
			JOptionPane.showMessageDialog(button, "장바구니에 항목이 없습니다");
		} else {
			int select = JOptionPane.showConfirmDialog(button, "장바구니의 모든 항목을 삭제하겠습니까?");
			if (select == 0) {
				mCart.deleteBook();
				JOptionPane.showMessageDialog(button, "장바구니의 모든 항목을 삭제했습니다");
			}
		}
	}
}
