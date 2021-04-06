package view;
import model.*;
import model.mapping.ClientMapping;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
public class LoginPanel extends JPanel{
	private MainPanel mainPanel;
	JLabel userLabel = new JLabel("User:");
	JLabel passwordLabel = new JLabel("Password:");
	JLabel noAccount = new JLabel("");
	JLabel passwordError = new JLabel("");
	JTextField userText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JButton loginButton = new JButton("login");
    JButton registerButton = new JButton("enroll");

	public LoginPanel(MainPanel mainPanel) {
		this.init();
		this.addListener();
		this.setLayout(null);
		this.mainPanel = mainPanel;
	}
	public void init() {	
		this.setLayout(null);
		this.add(userLabel);
		this.add(userText);
        this.add(noAccount);
        userLabel.setFont(new Font("宋体", 0, 25));
        userLabel.setBounds(20,85, 150, 30);
        userText.setBounds(170,85, 200, 25);
        noAccount.setBounds(30,130, 500,30);
           
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(passwordError);
        passwordLabel.setFont(new Font("宋体", 0, 25));
        passwordLabel.setBounds(20,170, 200, 30);
        passwordText.setBounds(170,170, 200, 25);
        passwordError.setBounds(30,200, 500,30);
        
        loginButton.setPreferredSize(new Dimension(120,25));
        this.add(loginButton);
        loginButton.setBounds(70, 240, 100, 25);
        
        registerButton.setPreferredSize(new Dimension(120,25));
        this.add(registerButton);
        registerButton.setBounds(220, 240, 100, 25);
	}
	private void addListener() {
		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clean();
					forLogin(userText.getText(),passwordText.getPassword());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		registerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				forRegister();
			}
			
		});
	}
	private void forLogin(String admin,char[] pwd) throws FileNotFoundException {
		String pin=String.valueOf(pwd);
		checkin(admin,pin);
	}
	
	private void forRegister() {
		//TODO 跳转到登陆界面
		mainPanel.changeToEnroll();
	}
	private void clean() {
		passwordError.setText("");
		noAccount.setText("");
	}
	private Client checkin(String nickName,String password) throws FileNotFoundException {			
		HashMap<String, String> map = new HashMap<>();
		map.put("nickName", nickName);
	    ArrayList<Client> clients1 = ClientMapping.find(map);
	    ArrayList<Client> clients = new ArrayList<Client>();
	    if(clients1.isEmpty()) {
	    	noAccount.setText("the account is not exist");
	    	noAccount.setForeground(Color.red);
	    	return null;
	    }  	
	    else {
	    	map.put("password", password);
		    clients = ClientMapping.find(map);
		    //不能登录
		    if(clients.isEmpty()) {
		    	passwordError.setText("your password is wrong, please try again!");
		    	passwordError.setForeground(Color.red);
		    	System.out.println("can not login");
		    	return null;
		   	}
			else{
				mainPanel.changeToFunction(clients.get(0));
				}
		    return clients.get(0);
	    }	         
	}
}
