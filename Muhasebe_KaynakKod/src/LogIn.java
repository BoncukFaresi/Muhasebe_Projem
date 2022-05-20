import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame implements ActionListener , KeyListener {
	
	JLabel Kullanici;
	JLabel Sifre;
	JLabel warn;
	JTextField kul;
	JPasswordField sif;
	JButton buton;
	JButton iptal;
	dbConnect conn = new dbConnect();
	
	char[] pass={'b','1','d','2','m','3'};
	

	//---------------Constructor--------------------
	LogIn() throws SQLException{
		
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(180,140));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(180,20));
		panel2.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(180,30));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setBackground(Color.LIGHT_GRAY);
		
		Kullanici = new JLabel("Kullanýcý:");
		
		Sifre = new JLabel("Þifre:");
		
		warn=new JLabel("Yanlýþ Þifre");
		warn.setForeground(Color.red);
		warn.setVisible(false);
		
		kul = new JTextField();
		kul.setPreferredSize(new Dimension(100,20));
		kul.addKeyListener(this);
		
		sif = new JPasswordField(10);
		sif.setPreferredSize(new Dimension(100,20));
		sif.addKeyListener(this);
		
		
		
		buton = new JButton("Giriþ");
		buton.setFocusable(false);
		buton.addActionListener(this);
		
		iptal = new JButton("Ýptal");
		iptal.setFocusable(false);
		iptal.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("das.png");
		
		
		//-------------Frame Düzenleme--------------
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Giriþ");
		this.setIconImage(icon.getImage());
		panel2.add(warn);
		panel.add(Kullanici);
		panel.add(kul);
		panel.add(Sifre);
		panel.add(sif);
		panel.add(panel2);
		panel3.add(buton);
		panel3.add(iptal);
		panel.add(panel3);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buton) {
			String username = new String(kul.getText());
			String password = new String(sif.getPassword());
			try {
				if(conn.dbContainUser(username)) {
					if(conn.dbPasswordCheck(password)) {
						AnaSayfa ana = new AnaSayfa(conn.dbid(username));
						this.dispose();
					}
					else {
						warn.setText("Yanlýþ þifre");
						warn.setVisible(true);
					}
				}
				else {
					warn.setText("Yanlýþ Kullanýcý Adý");
					warn.setVisible(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==iptal) {
			System.exit(0);
		}
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10) {
			String username = new String(kul.getText());
			String password = new String(sif.getPassword());
			try {
				if(conn.dbContainUser(username)) {
					if(conn.dbPasswordCheck(password)) {
						AnaSayfa ana = new AnaSayfa(conn.dbid(username));
						this.dispose();
					}
					else {
						warn.setText("Yanlýþ þifre");
						warn.setVisible(true);
					}
				}
				else {
					warn.setText("Yanlýþ Kullanýcý Adý");
					warn.setVisible(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
