package kr.or.dgit.phonebookv02.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.phonebookv02.ctrl.PhoneControl;
import kr.or.dgit.phonebookv02.dto.Phone;

@SuppressWarnings("serial")
public class PhoneBookMainUI extends JFrame {
	public static final String[] COL_NAMES = { "번호", "이름", "주소", "전화번호" };

	private JPanel contentPane;
	private JTable table;
	private PhoneControl phoneControl;
	private DefaultTableModel model;
	private PhonePanel pPhone;

	public PhoneBookMainUI() {
		phoneControl = new PhoneControl();

		setTitle("연락처 관리 앱");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pPhone = new PhonePanel();
		contentPane.add(pPhone, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		model = new DefaultTableModel(getDatas(), PhoneBookMainUI.COL_NAMES);
		table = new JTable(model);

		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem delMenuItem = new JMenuItem("삭제");
		JMenuItem reMenuItem = new JMenuItem("수정");

		popMenu.add(delMenuItem);
		popMenu.add(reMenuItem);

		delMenuItem.addActionListener(delListener);
		reMenuItem.addActionListener(reListener);

		table.setComponentPopupMenu(popMenu);

		scrollPane.setViewportView(table);

		pPhone.getBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "추가") {
					addTable();
				}

				if (e.getActionCommand() == "수정") {
					reTable();
				}
				pPhone.getBtn().setText("추가");
				pPhone.clearTf();
			}

		});

	}

	ActionListener reListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			int selRow = table.getSelectedRow();
			Phone searchPhone = new Phone(String.valueOf(table.getValueAt(selRow, 1)));
			Phone rePhone = phoneControl.searchPhone(searchPhone);
			pPhone.setPhone(rePhone);
			pPhone.getBtn().setText("수정");

			/*
			 * String name = String.valueOf(table.getValueAt(selRow, 1)); String
			 * addr = String.valueOf(table.getValueAt(selRow, 2)); String tel =
			 * String.valueOf(table.getValueAt(selRow, 3));
			 */
			// pPhone.setPhone(new Phone(name, addr, tel));

		}
	};

	ActionListener delListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteTable();
		}
	};

	private void addTable() {
	
		Phone add = pPhone.getPhone();
		
		if(isInfo(add)){
			String name = add.getName();
			String addr = add.getAddr();
			String tel = add.getTel();

			if (isOk(add)) {
				model.addRow(new Object[] { table.getRowCount() + 1, name, addr, tel });
			}
		}
	

	}

	private void deleteTable() {
		int selRow = table.getSelectedRow();

		Phone delPhone = new Phone(String.valueOf(table.getValueAt(selRow, 1)));
		phoneControl.deletePhone(delPhone);

		model.removeRow(selRow);
	}

	private void reTable() {
		int selRow = table.getSelectedRow();
		Phone re = pPhone.getPhone();
		String num = String.valueOf(table.getValueAt(selRow, 0));
		
		if(isInfo(re)){
			deleteTable();
			model.insertRow(selRow, new Object[] { num, re.getName(), re.getAddr(), re.getTel() });
			phoneControl.insertPhone(re);
		}
		
	}

	private boolean isOk(Phone newPhone) {
		boolean isOk = phoneControl.insertPhone(newPhone);

		if (!isOk) {
			JOptionPane.showMessageDialog(null, "동일한 이름이 존재합니다.");
			return false;
		}
		return true;
	}
	private boolean isInfo(Phone checkPhone){
		if(checkPhone==null){
			JOptionPane.showMessageDialog(null, "공백을 입력하셨습니다.");
			return false;
		}
		return true;
	}
	private Object[][] getDatas() {
		return phoneControl.showPhones();
	}

}
