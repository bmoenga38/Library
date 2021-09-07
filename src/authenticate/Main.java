

package authenticate;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener{
    Dimension d;
    JPanel pan_login,pan_title,pan_foot,pan_center,pan_register;
    JLabel lb_user,lb_pass,lb_header,lb_title,lb_foot;
    CardLayout card;
Main(){
    super("Login Panel");
    d = Toolkit.getDefaultToolkit().getScreenSize();
    
    card = new CardLayout();
    pan_center = new JPanel();
    pan_center.setLayout(card);
    
    pan_login = new LoginPanel();
    pan_register = new RegisterPanel();
    pan_center.add(pan_login);
    pan_center.add(pan_register);
            
    pan_title = new JPanel();
    pan_title.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    lb_title=new JLabel("KISE LIBRARY SYSTEM");
    lb_title.setFont(new Font("arial",Font.BOLD,50));
    pan_title.add( lb_title);
     
    pan_foot = new JPanel();
    pan_foot.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    lb_foot = new JLabel("Owned by kise");
    pan_foot.add(lb_foot);
    
   addWindowListener(new WindowAdapter(){
    public void windowClosing(WindowEvent e){
     int x = JOptionPane.showConfirmDialog(Main.this,"Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
   if(x == JOptionPane.YES_OPTION){
     setDefaultCloseOperation(EXIT_ON_CLOSE);  
   }else{
       setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
   }}});
   
   LoginPanel.btn_register.addActionListener(this);
   RegisterPanel.btn_login.addActionListener(this);
   
    add(pan_center,BorderLayout.CENTER);
    add(pan_title,BorderLayout.NORTH);
    add(pan_foot,BorderLayout.SOUTH);
    setSize(d);
    setVisible(true);
}
    public static void main(String[] args) {
        new Main();
    }

    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==LoginPanel.btn_register){
          card.next(pan_center);
        }
        else if(e.getSource()==RegisterPanel.btn_login){
          card.next(pan_center);  
        }
    }
}
