package kr.or.dgit.phonebookv02.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.or.dgit.phonebookv02.dto.Phone;

public class PhonePanel extends JPanel {
	private JTextField tfName;
	private JTextField tfAddr;
	private JTextField tfTell;
	private JButton btn;

	public PhonePanel() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblName = new JLabel("성명");
		add(lblName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
		
		JLabel lblAddr = new JLabel("주소");
		add(lblAddr);
		
		tfAddr = new JTextField();
		tfAddr.setColumns(10);
		add(tfAddr);
		
		JLabel lblTel = new JLabel("연락처");
		add(lblTel);
		
		tfTell = new JTextField();
		tfTell.setColumns(10);
		add(tfTell);
		
		btn = new JButton("추가");
		add(btn);

	}
	
	public JButton getBtn() {
		return btn;
	}
	
	
	public void setPhone(Phone phone){
		tfName.setText(phone.getName());
		tfAddr.setText(phone.getAddr());
		tfTell.setText(phone.getTel());
	}
	
	public Phone getPhone()	{
		String name = tfName.getText();
		String addr = tfAddr.getText();
		String tel = tfTell.getText();
	///공백체크하기
		if(name.isEmpty()||addr.isEmpty()||tel.isEmpty()){
			return null;
		}
		return new Phone(name, addr, tel);
	}

	public void clearTf(){
		tfName.setText("");
		tfAddr.setText("");
		tfTell.setText("");
	}

}










