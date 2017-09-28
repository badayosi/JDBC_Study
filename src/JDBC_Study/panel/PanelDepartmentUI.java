package JDBC_Study.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;

@SuppressWarnings("serial")
public class PanelDepartmentUI extends JPanel {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private JTable tbDeptList;
	private DepartmentDao deptDao;
	DefaultTableModel deptModel;
	public static final String[] DEPT_NAMES = {"부서번호", "부서이름", "부서위치"};
	public static final int DEPT_COUNT = 3;
	private JButton btnDeptOK;
	private JPanel inputPanel;

	public PanelDepartmentUI() {
		setDeptDao();
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		deptModel = new DefaultTableModel(loadList(), DEPT_NAMES);
		tbDeptList = new JTable();
		tbDeptList.setModel(deptModel);
		tbDeptList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(tbDeptList.getSelectedRow());
				if(btnDeptOK.getText() != "추가"){
					tfDeptNo.setText(String.valueOf(deptModel.getValueAt(tbDeptList.getSelectedRow(), 0)));
					tfDeptName.setText(String.valueOf(deptModel.getValueAt(tbDeptList.getSelectedRow(), 1)));
					tfFloor.setText(String.valueOf(deptModel.getValueAt(tbDeptList.getSelectedRow(), 2)));
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		scrollPane.setViewportView(tbDeptList);
		
		inputPanel = new JPanel();
		add(inputPanel, BorderLayout.NORTH);
		inputPanel.setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblDeptNo = new JLabel("부서번호");
		inputPanel.add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		inputPanel.add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서이름");
		inputPanel.add(lblDeptName);
		
		tfDeptName = new JTextField();
		inputPanel.add(tfDeptName);
		tfDeptName.setColumns(10);
		
		JLabel lblFloor = new JLabel("부서위치");
		inputPanel.add(lblFloor);
		
		tfFloor = new JTextField();
		inputPanel.add(tfFloor);
		tfFloor.setColumns(10);
		
		btnDeptOK = new JButton("추가");
		btnDeptOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnDeptOK.getText() == "추가"){
					Department newList = new Department(deptModel.getRowCount()+1, tfDeptName.getText(), Integer.valueOf(tfFloor.getText()));
					Object[] arr = newList.toArray();
					deptModel.addRow(arr);
					deptDao.insertDepartment(newList);
					
					tfClear();
				}
				if(btnDeptOK.getText() == "삭제"){
					Department delList = new Department(Integer.valueOf(tfDeptNo.getText()));
					deptModel.removeRow(Integer.valueOf(tfDeptNo.getText())-1);
					deptDao.deleteDepartment(delList);
					
					tfClear();
				}
				if(btnDeptOK.getText() == "수정"){
					Department revList = new Department(Integer.valueOf(tfDeptNo.getText()), tfDeptName.getText(), Integer.valueOf(tfFloor.getText()));
					Object[] arr = revList.toArray();
					deptModel.removeRow(Integer.valueOf(tfDeptNo.getText())-1);
					deptModel.addRow(arr);
					deptDao.updateDepartment(revList);
					deptModel = new DefaultTableModel(loadList(), PanelDepartmentUI.DEPT_NAMES);
					tbDeptList.setModel(deptModel);

					tfClear();
				}
				if(btnDeptOK.getText() == "검색"){
					Department serachDept = new Department(Integer.valueOf(tfDeptNo.getText()));
					Object[] arr = deptDao.selectDepartmentByNo(serachDept).toArray();
					Object[][] dept = new Object[1][arr.length];
					dept[0] = arr;
					DefaultTableModel searchModel = new DefaultTableModel(dept, PanelDepartmentUI.DEPT_NAMES);
					tbDeptList.setModel(searchModel);
					tfDeptName.setText(searchModel.getValueAt(0, 1).toString());
					tfFloor.setText(searchModel.getValueAt(0, 2).toString());
					
					//종료시
					//tbDeptList.setModel(deptModel);
					//tfClear();
				}
			}
		});
		inputPanel.add(btnDeptOK);
		
		JButton btnDeptCancel = new JButton("취소");
		inputPanel.add(btnDeptCancel);
	}

	public void setDeptDao() {
		this.deptDao = DepartmentDao.getInstanse();
	}
	
	public Object[][] loadList(){
		List<Department> lists = deptDao.selectDepartmentByAll();
		Object[][] transData = new Object[lists.size()][];
		
		int i =0;
		for(Department d : lists){
			Object[] arr = new Object[DEPT_COUNT];
			Object[] dept = d.toArray();
			System.arraycopy(dept, 0, arr, 0, dept.length);
			transData[i] =arr;
			i++;
		}
		return transData;
	}
	
	public void setMode(String mode){
		if(mode == "추가"){
			inputPanel.show();
			btnDeptOK.setText(mode);
			tfDeptNo.setEnabled(false);
			tfDeptName.setEnabled(true);
			tfFloor.setEnabled(true);
		}
		if(mode == "삭제"){
			inputPanel.show();
			btnDeptOK.setText(mode);
			tfDeptNo.setEnabled(false);
			tfDeptName.setEnabled(false);
			tfFloor.setEnabled(false);
		}
		if(mode == "수정"){
			inputPanel.show();
			btnDeptOK.setText(mode);
			tfDeptNo.setEnabled(false);
			tfDeptName.setEnabled(true);
			tfFloor.setEnabled(true);
		}
		if(mode == "검색"){
			inputPanel.show();
			btnDeptOK.setText(mode);
			tfDeptNo.setEnabled(true);
			tfDeptName.setEnabled(false);
			tfFloor.setEnabled(false);
		}
		if(mode == "전체"){
			inputPanel.hide();
		}
	}
	
	public void tfClear(){
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
	}
}
