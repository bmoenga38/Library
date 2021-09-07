
package authenticate;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel 
                       implements ActionListener,FocusListener{
	private static final long serialVersionUID = 7662392250058400671L;
	JLabel lb_user,lb_pass,lb_cpass,lb_header;
    JTextField tf_user;
    JPasswordField pf_pass,pf_cpass;
    static JButton btn_login,btn_register;
    GridBagLayout gbl;
    GridBagConstraints c;
    
    RegisterPanel(){
    gbl = new GridBagLayout();
    c = new GridBagConstraints();
    setBorder(BorderFactory.createLoweredSoftBevelBorder());
    setLayout(gbl);
    setBackground(Color.cyan);
    
    lb_header = new JLabel("Enter Login details");
    lb_header.setFont(new Font("arial",Font.BOLD,40));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=0;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(lb_header,c);
    
    lb_user = new JLabel("Enter Username");
    lb_user.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=1;
    c.gridx=0;
    c.insets=new Insets(5,5,5,5);
    add(lb_user,c);
    
    tf_user = new JTextField(20);
    tf_user.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=1;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(tf_user,c);
    
    lb_pass = new JLabel("Enter Password");
    lb_pass.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=2;
    c.gridx=0;
    c.insets=new Insets(5,5,5,5);
    add(lb_pass,c);
    
    pf_pass = new JPasswordField(20);
    pf_pass.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=2;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(pf_pass,c);
    
    lb_cpass = new JLabel("Confirm Password");
    lb_cpass.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=3;
    c.gridx=0;
    c.insets=new Insets(5,5,5,5);
    add(lb_cpass,c);
    
    pf_cpass = new JPasswordField(20);
    pf_cpass.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=3;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(pf_cpass,c);
    
    btn_register = new JButton("Click to register");
    btn_register.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=4;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(btn_register,c); 
    btn_register.addActionListener(this);
    
    btn_login = new JButton("Login");
    btn_login.setFont(new Font("arial",Font.BOLD,30));
    btn_login.setBackground(Color.CYAN);
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=5;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(btn_login,c);
    }

	public void actionPerformed(ActionEvent e) {
	ConnectionPool cpool = new ConnectionPool("root","");
	Connection con = cpool.connector();
	PreparedStatement ps;
	String query1,query2,username,password,cpass;
	username=tf_user.getText();
	password=pf_pass.getText();
	cpass=pf_cpass.getText();
	query1="select username from users where username=?";
	query2="insert into users(username,password)" +
			"values(?,?)";
	if(e.getSource()==btn_register){
	if(tf_user.equals("") || pf_pass.equals("")||cpass.equals("")){
		JOptionPane.showMessageDialog(null, "One or more fields is required!!");	
	}else{
		if(password.equals(cpass)){
			if(con!=null){
				try {
					ps = con.prepareStatement(query1);
					ps.setString(1, username);
					ResultSet rs = ps.executeQuery();
					//int row=rs.getRow();
					if(rs.next()){
		JOptionPane.showMessageDialog(null,	"User exists");
		}else{
			ps=con.prepareStatement(query2);
			ps.setString(1, username);
			ps.setString(2, password);
			int insert = ps.executeUpdate();
			
			if(insert == 1){
			JOptionPane.showMessageDialog(null,	"User added");	
			}else{
		 JOptionPane.showMessageDialog(null,"User insert failed!!");	
			}
		}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}	
				}else{
					JOptionPane.showMessageDialog(null, "Connection failed!!");
				}	
		}else{
			JOptionPane.showMessageDialog(null, "Passwords dont match!!");	
		}
		
		
	}
	}
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}}
