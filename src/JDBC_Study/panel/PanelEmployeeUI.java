package JDBC_Study.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Employee;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PanelEmployeeUI extends JPanel {
	private JTable tbEmployee;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private EmployeeDao empDao;
	private DefaultTableModel empModel;
	public static final String[] EMP_NAMES = {"사원번호", "사원명", "직책", "관리자", "급여", "부서", "위치"};
	public static final int EMP_COUNT = 7;

	public PanelEmployeeUI() {
		setEmpDao();
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane centerPane = new JScrollPane();
		add(centerPane, BorderLayout.CENTER);
		
		empModel = new DefaultTableModel(loadList(), EMP_NAMES);
		tbEmployee = new JTable();
		tbEmployee.setModel(empModel);
		centerPane.setViewportView(tbEmployee);
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmpNo = new JLabel("사원번호");
		topPanel.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		topPanel.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		topPanel.add(lblEmpName);
		
		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		topPanel.add(tfEmpName);
		
		JLabel lblEmpTitle = new JLabel("직책");
		topPanel.add(lblEmpTitle);
		
		JComboBox cbEmpTitle = new JComboBox();
		cbEmpTitle.setModel(new DefaultComboBoxModel(new String[] {"사장", "과장", "부장", "대리", "사원"}));
		topPanel.add(cbEmpTitle);
		
		JLabel lblEmpManager = new JLabel("관리자");
		topPanel.add(lblEmpManager);
		
		JComboBox cbEmpManager = new JComboBox();
		topPanel.add(cbEmpManager);
		
		JLabel lblEmpSalary = new JLabel("급여");
		topPanel.add(lblEmpSalary);
		
		JSpinner spEmpSalary = new JSpinner();
		spEmpSalary.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(10)));
		topPanel.add(spEmpSalary);
		
		JLabel lblEmpDept = new JLabel("부서");
		topPanel.add(lblEmpDept);
		
		JComboBox cbEmpDept = new JComboBox();
		topPanel.add(cbEmpDept);
		
		JButton btnOK = new JButton("추가");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		topPanel.add(btnOK);
		
		JButton btnCancel = new JButton("취소");
		topPanel.add(btnCancel);
	}
	
	public Object[][] loadList(){
		List<Employee> lists = empDao.selectEmployeeByAll();
		Object[][] transData = new Object[lists.size()][];
		
		int i =0;
		for(Employee e : lists){
			Object[] arr = new Object[EMP_COUNT];
			Object[] emp = e.toArray();
			System.arraycopy(emp, 0, arr, 0, emp.length);
			transData[i] =arr;
			i++;
		}
		return transData;
	}
	
	public void setEmpDao() {
		this.empDao = EmployeeDao.getInstance();
	}
	
	//public 

	public void setMode(String mode){
		if(mode == "추가"){

		}
		if(mode == "삭제"){

		}
		if(mode == "수정"){

		}
		if(mode == "검색"){

		}
		if(mode == "전체"){

		}
	}
}
