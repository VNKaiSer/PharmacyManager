package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import dao.KhachHang_DAO;

public class JOptionPaneGetThongTinKH implements ActionListener, KeyListener{
	JLabel lblTen;
	JLabel lblSDT;
	JTextField txtSDT;
	JTextField txtTenKH;

//	private void dislayJOP() {
//		JOptionPane.showConfirmDialog(null, getPanel(), "Nhập thông tin khách hàng", JOptionPane.OK_CANCEL_OPTION,
//				JOptionPane.PLAIN_MESSAGE);
//	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		JPanel panelTop = new JPanel();
		JPanel panelBot = new JPanel();
		panel.setLayout(new BorderLayout());
		lblTen = new JLabel("Tên KH");
		lblSDT = new JLabel("SĐT ");
		txtSDT = new JTextField(20);
		txtTenKH = new JTextField(20);
		txtSDT.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});
		
		panelTop.add(lblSDT);
		panelTop.add(txtSDT);

		panelBot.add(lblTen);
		panelBot.add(txtTenKH);

		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelBot, BorderLayout.SOUTH);
		txtSDT.requestFocus();
		return panel;
	}

	public String getTxtSDT() {
		return txtSDT.getText();
	}

	public String getTxtTenKH() {
		return txtTenKH.getText();
	}
	public boolean checkNhapDL() {
		
		if(getTxtSDT().length() == 0 || getTxtTenKH().length() == 0) {
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		KhachHang_DAO kh_Dao = new KhachHang_DAO();
//		String ten = kh_Dao.getTenKH(getTxtSDT());
//		txtTenKH.setText(ten);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		KhachHang_DAO kh_Dao = new KhachHang_DAO();
//		String ten = kh_Dao.getTenKH(getTxtSDT());
//		txtTenKH.setText(ten);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}