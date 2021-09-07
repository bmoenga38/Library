
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

public class LoginPanel extends JPanel implements FocusListener,
ActionListener{
    JLabel lb_user,lb_pass,lb_header;
    JTextField tf_user;
    JPasswordField pf_pass;
    static JButton btn_login,btn_register;
    GridBagLayout gbl;
    GridBagConstraints c;
    
    LoginPanel(){
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
    tf_user.addFocusListener(this);
    
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
    pf_pass.addFocusListener(this);
    
    btn_login = new JButton("Login");
    btn_login.setFont(new Font("arial",Font.BOLD,30));
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=3;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(btn_login,c);
    btn_login.addActionListener(this);
    
    btn_register = new JButton("Click to register");
    btn_register.setFont(new Font("arial",Font.BOLD,30));
    btn_register.setBackground(Color.CYAN);
    c.anchor=GridBagConstraints.CENTER;
    c.fill=GridBagConstraints.BOTH;
    c.gridy=4;
    c.gridx=1;
    c.insets=new Insets(5,5,5,5);
    add(btn_register,c); 
}

    //@Override
    public void focusGained(FocusEvent e) {
     if(e.getSource()==tf_user){   
         tf_user.setBackground(Color.YELLOW);
                 
     }else{
         pf_pass.setBackground(Color.YELLOW);
     }
    }

   // @Override
    public void focusLost(FocusEvent e) {
    if(e.getSource()==tf_user){   
         tf_user.setBackground(Color.WHITE);
                 
     }else{
        pf_pass.setBackground(Color.WHITE); 
    }    
    }

	public void actionPerformed(ActionEvent arg0) {
		String user = tf_user.getText();
		String pass = pf_pass.getText();
		PreparedStatement ps;
		String query="select * from users where username=?" +
				"and password=?";
		ConnectionPool cpool = new ConnectionPool("root","");
		Connection con = cpool.connector();
		if(tf_user.equals("")||pf_pass.equals("")){
			JOptionPane.showMessageDialog(null, "One or more fields is required!!");		
		}else{
			if(con!=null){
				try {
					ps=con.prepareStatement(query);
					ps.setString(1, user);
					ps.setString(2, pass);
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
		JOptionPane.showMessageDialog(null,"Login Successful...");		
					}else{
		JOptionPane.showMessageDialog(null,"Login Failed...");	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Connection failed!!");	
			}
		}
	}
}