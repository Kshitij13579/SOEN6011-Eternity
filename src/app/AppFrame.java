package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import math.Statistics;

public class AppFrame extends JFrame implements ActionListener {
	JTextField jtext_input;
	JButton btn_upload;
	JButton btn_calculate;
	JLabel lbl_result;
	
	File input_file;
	ArrayList<Double> elements;
	
	public static final String INITIAL_TEXT = "Result:";
	
	public AppFrame() {
		
		elements = new ArrayList<Double>();
		
		this.setTitle("Standard Deviation Calculator");
		
		jtext_input = new JTextField(100);
		jtext_input.setBounds(10, 10, 370, 30);
		
		btn_upload = new JButton();
		btn_upload.setBounds(280, 50, 80, 30);
		btn_upload.setText("Upload");
		
		btn_calculate = new JButton();
		btn_calculate.setBounds(280, 100, 100, 30);
		btn_calculate.setText("Calculate");
		
		lbl_result = new JLabel("Result:");
		lbl_result.setBounds(10,100,180,20);
		
		btn_calculate.addActionListener(this);
		btn_upload.addActionListener(this);
		
		this.add(jtext_input);
		this.add(btn_calculate);
		this.add(btn_upload);
		this.add(lbl_result);
		
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Calculate")) {
			calculate();
		}else if(e.getActionCommand().equals("Upload")) {
			uploadFile();
		}
		
	}
	
	public void uploadFile() {
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			input_file = jfc.getSelectedFile();
			JOptionPane.showMessageDialog(this, "File Uploaded Successfully");
		}
		
	}
	
	public void calculate() {
		
		if(!jtext_input.getText().isEmpty()) {
			parseTextField(jtext_input.getText().trim());
		}else if(input_file.exists()) {
			readFile();
		}else {
			JOptionPane.showMessageDialog(this, "No Input Provided For Calculation.");
		}
		
		double stdv = Statistics.stdv(elements);
		lbl_result.setText(INITIAL_TEXT+stdv);
		
	}
	
	public void parseTextField(String text) {
		String[] s_array = text.split(",");
		try {
			for(String s:s_array) {
				elements.add(Double.parseDouble(s));
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Invalid Number in Input.");
		}
	}
	
	public void readFile() {
		
		try {
			Scanner sc = new Scanner(input_file);
			while(sc.hasNextLine()) {
				elements.add(Double.parseDouble(sc.next()));
			}
			sc.close();		
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Invalid Number in Input File.");
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "File Not Found.");
		}
	}
}
