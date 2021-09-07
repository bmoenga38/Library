package mdi;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class Dashboard extends JFrame{
	JDesktopPane pane;
	Dimension d;
	Dashboard(){
	d = Toolkit.getDefaultToolkit().getScreenSize();
	pane = new JDesktopPane();
	JInternalFrame bookstock = new StockBooks();
	
	pane.add(bookstock);
	
	add(pane);
	setSize(d);
	setVisible(true);
	}
	public static void main(String[] args){
		new Dashboard();
	}
}
