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
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import math.Statistics;

/**
 * This class contains the UI component of 
 * Scientific calculator and their functionalities. 
 *
 * @author Kshitij Yerande
 * @version 1.0
 * @since 14-July-2022
 */
public class AppFrame extends JFrame implements ActionListener {
    
  JTextField jtextInput;
  JButton btnUpload;
  JButton btnCalculate;
  JLabel lblResult;
  JLabel lblFile;
  JLabel lblCountNum;
    
  File inputFile;
  ArrayList<Double> elements;
    
  public static final String INITIAL_TEXT = "Result:";
  
  /**
   * Constructor for launching Java App.
   * This constructor initializes the UI components.
   * It initializes:
   *  1. Text Field : to take input from user from UI.
   *  2. Calculate Button: to calculate the standard deviation.
   *  1. Upload Button : to take input file from user.
   *  1. Result Label : to display result of standard deviation.
   *  1. Upload File Label : to display name of uploaded file.
   */
  public AppFrame() {
        
    elements = new ArrayList<Double>();
        
    this.setTitle("Standard Deviation Calculator");
        
    jtextInput = new JTextField("Enter comma separated numbers", 100);
    jtextInput.setBounds(10, 10, 370, 30);
        
    btnUpload = new JButton();
    btnUpload.setBounds(280, 50, 80, 30);
    btnUpload.setText("Upload");
        
    btnCalculate = new JButton();
    btnCalculate.setBounds(280, 100, 100, 30);
    btnCalculate.setText("Calculate");
        
    lblResult = new JLabel("Result:");
    lblResult.setBounds(10, 100, 180, 20);
    
    lblFile = new JLabel("Uploaded File:");
    lblFile.setBounds(10, 130, 180, 20);
    
    lblCountNum = new JLabel("Count:");
    lblCountNum.setBounds(10, 80, 180, 20);
        
    btnCalculate.addActionListener(this);
    btnUpload.addActionListener(this);
        
    this.add(jtextInput);
    this.add(btnCalculate);
    this.add(btnUpload);
    this.add(lblResult);
    this.add(lblFile);
    this.add(lblCountNum);
    
    this.setTitle("Standard Deviation Calculator");
    this.setSize(400, 200);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
        
    if (e.getActionCommand().equals("Calculate")) {
      calculate();
    } else if (e.getActionCommand().equals("Upload")) {
      uploadFile();
    }
        
  }
  
  /**
   * The method is used to upload the input file
   * from user system.
   * Called: On click of Upload button
   */
  public void uploadFile() {
        
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue = jfc.showOpenDialog(null);
        
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      inputFile = jfc.getSelectedFile();
      JOptionPane.showMessageDialog(this, "File Uploaded Successfully");
      lblFile.setText("Uploaded File" + inputFile.getName()); 
    }
        
  }
  
  /**
   * This method is used to call standard 
   * deviation function and display the result on UI.
   * Called: On click of calculate button.
   */
  public void calculate() {
    
    boolean success = false;
    
    if (!jtextInput.getText().isEmpty()  
        && !jtextInput.getText().equalsIgnoreCase("Enter comma separated numbers")) {
      success = parseTextField(jtextInput.getText().trim());
    } else if (inputFile != null) {
      success = readFile();
    } else {
      JOptionPane.showMessageDialog(this, "No Input Provided For Calculation.");
    }
    
    if (success) {
      double stdv = Statistics.stdv(elements);
      lblResult.setText(INITIAL_TEXT + stdv);
      lblCountNum.setText("Count:" + elements.size()); 
    }
        
  }
  
  /**
   * The method parses the comma separated input string from user
   * and populates the element array list.
   *
   * @param text - comma separated numbers from UI.
   * @return boolean - true if successful or else false.
   */
  public boolean parseTextField(String text) {
    String[] stringArray = text.split(",");
    elements.clear();
    try {
      for (String s : stringArray) {
        elements.add(Double.parseDouble(s));
      }
      return true;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid Number in Input.");
      return false;
    }
  }
  
  /**
   * This method reads content of file uploaded by user. 
   *
   * @return boolean - true if successful or else false.
   */
  public boolean readFile() {      
    try {
      Scanner sc = new Scanner(inputFile);
      elements.clear();
      while (sc.hasNextLine()) {
        elements.add(Double.parseDouble(sc.next()));
      }
      sc.close();  
      return true;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid Number in Input File.");
      return false;
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(this, "File Not Found.");
      return false;
    }
  }
}
