package JDBC_Study.jdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JDBC_Study.panel.PanelDepartmentUI;
import JDBC_Study.panel.PanelEmployeeUI;

@SuppressWarnings("serial")
public class CompanyManagerUI extends JFrame {

	private JPanel contentPane;
	private PanelDepartmentUI showDepartment;
	private PanelEmployeeUI showEmployee;

	public CompanyManagerUI() {
		
		// 프로그램 실행 후 기본 세팅
		setTitle("관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		showDepartment = new PanelDepartmentUI();
		showEmployee = new PanelEmployeeUI();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEmployee = new JMenu("사원관리");
		menuBar.add(mnEmployee);
		
		JMenuItem mnEmpAdd = new JMenuItem("사원추가");
		mnEmpAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEmployee.setMode("추가");
				setContentPane(showEmployee);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnEmployee.add(mnEmpAdd);
		
		JMenuItem mnEmpDelete = new JMenuItem("사원삭제");
		mnEmployee.add(mnEmpDelete);
		
		JMenuItem mnEmpRev = new JMenuItem("사원수정");
		mnEmployee.add(mnEmpRev);
		
		JMenuItem mnEmpSearch = new JMenuItem("사원검색");
		mnEmployee.add(mnEmpSearch);
		
		JMenuItem mnEmpShowAll = new JMenuItem("전체보기");
		mnEmployee.add(mnEmpShowAll);
		
		JMenu mnDepartment = new JMenu("부서관리");
		menuBar.add(mnDepartment);
		
		JMenuItem mnDeptAdd = new JMenuItem("부서추가");
		mnDeptAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDepartment.setMode("추가");
				setContentPane(showDepartment);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnDepartment.add(mnDeptAdd);
		
		JMenuItem mnDeptDelete = new JMenuItem("부서삭제");
		mnDeptDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDepartment.setMode("삭제");
				setContentPane(showDepartment);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnDepartment.add(mnDeptDelete);
		
		JMenuItem mnDeptRev = new JMenuItem("부서수정");
		mnDeptRev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDepartment.setMode("수정");
				setContentPane(showDepartment);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnDepartment.add(mnDeptRev);
		
		JMenuItem mnDeptSearch = new JMenuItem("부서검색");
		mnDeptSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDepartment.setMode("검색");
				setContentPane(showDepartment);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnDepartment.add(mnDeptSearch);
		
		JMenuItem mnDeptShowAll = new JMenuItem("전체보기");
		mnDeptShowAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDepartment.setMode("전체");
				setContentPane(showDepartment);
				setBounds(getX(), getY(), getWidth(), 500);
			}
		});
		mnDepartment.add(mnDeptShowAll);		
	}
}
