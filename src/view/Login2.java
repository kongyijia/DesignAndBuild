package login1;
import models.*;
import mapping.ClientMapping;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
public class Login2 extends JFrame{
	JPanel panel  = new JPanel(); 
	JPanel panela = new GUILogin().panel;  
	GUILogin f2 =new GUILogin();
	JLabel userLabel = new JLabel("User:        ");
	JLabel passwordLabel = new JLabel("Password:");
	JLabel noAccount = new JLabel("");
	JLabel passwordError = new JLabel("");
	JTextField userText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JButton loginButton = new JButton("login");
    JButton registerButton = new JButton("enroll");
    
	public Login2() {
		this.setSize(1200, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.init();
		this.addListener();
		this.setVisible(true);
	}
	public void init() {	
		panel.setLayout(null);
		panel.add(userLabel);
		panel.add(userText);
        panel.add(noAccount);
        userLabel.setFont(new Font("宋体", 0, 25));
        userLabel.setBounds(450,150, 200, 30);
        userText.setBounds(600,150, 200, 25);
        noAccount.setBounds(450,190, 500,30);
           
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(passwordError);
        passwordLabel.setFont(new Font("宋体", 0, 25));
        passwordLabel.setBounds(450,220, 200, 30);
        passwordText.setBounds(600,220, 200, 25);
        passwordError.setBounds(450,260, 500,30);
        
        loginButton.setPreferredSize(new Dimension(120,25));
        panel.add(loginButton);
        loginButton.setBounds(480, 300, 100, 25);
        
        registerButton.setPreferredSize(new Dimension(120,25));
        panel.add(registerButton);
        registerButton.setBounds(630, 300, 100, 25);
        
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
		this.setVisible(false);
		f2.setVisible(true);
		System.out.println("enroll");
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
		    } else if(clients.get(0).getRole()==0){//0管理员
		    	//TODO login as admin	    
		    	
		    	System.out.println("管理员登入");
		    		    	
		    }else if(clients.get(0).getRole()==1) {//1教练 
		    	//TODO login as coach
		    	
		    	System.out.println("教练登入");
		    	
		    	
		    }else if(clients.get(0).getRole()==2) {//2用户
		    	//TODO login as user
		    	System.out.println("用户登入");
		    	  	
		    }else {//信息错误
		    }
		    System.out.println(clients.get(0));
		    return clients.get(0);
	    }	         
	}
}
