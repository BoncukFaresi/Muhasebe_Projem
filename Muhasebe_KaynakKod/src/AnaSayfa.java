import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.http.WebSocket.Listener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AnaSayfa extends JFrame implements ActionListener,WindowListener {
	
	Personel personel;

	JPanel sgPanel;
	JPanel altPanel;
	JPanel slPanel;
	
	JButton personelfr; 
	JButton musterifr;
	JButton stok;
	JButton islem;
	JButton Muhasebe;
	JButton fatura;
	dbConnect conn;
	
	ImageIcon ayar;

	JMenuItem LogOut;
	JMenuItem Exit;
	
	WindowListener listen;
	
	JLabel hg;
	int ID=0;
	
	AnaSayfa(int id) throws SQLException{
		ID=id;
		ayar = new ImageIcon(new ImageIcon("ayarlar.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		conn = new dbConnect();			
		
		JMenuBar bar = new JMenuBar();
		
		JMenu Oturum = new JMenu("Oturum");
		
		LogOut = new JMenuItem("Oturumu Kapat");
		LogOut.addActionListener(this);
		
		Exit = new JMenuItem("Programý Kapat");
		Exit.addActionListener(this);
		
		bar.add(Oturum);
		Oturum.add(LogOut);
		Oturum.add(Exit);
		
		
		int yetki =conn.dbgetYetki(id);
		ImageIcon icon = new ImageIcon("das.png");
		
		personelfr = new JButton("Personel iþlemleri");
		personelfr.setFocusable(false);
		personelfr.addActionListener(this);
		
		musterifr = new JButton("Müþteri Ýþlemleri");
		musterifr.setFocusable(false);
		musterifr.addActionListener(this);
		
		stok = new JButton("Stok Takip");
		stok.setFocusable(false);
		stok.addActionListener(this);
		
		fatura = new JButton("Fatura Ýþlemleri");
        fatura.setFocusable(false);
        fatura.addActionListener(this);
		
		hg = new JLabel("Hoþ Geldiniz, "+conn.dbgetIsim(id));
		
		sgPanel = new JPanel();
		sgPanel.setLayout(new GridLayout(5,1,10,10));
		sgPanel.setBackground(Color.LIGHT_GRAY);
		sgPanel.setPreferredSize(new Dimension(200,200));
		sgPanel.add(personelfr);
		sgPanel.add(musterifr);
		sgPanel.add(stok);
		sgPanel.add(fatura);
		
		altPanel = new JPanel();
		altPanel.setLayout(new FlowLayout());
		altPanel.setBackground(Color.gray);
		altPanel.setPreferredSize(new Dimension(100,30));
		altPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		altPanel.add(hg);
			
		//listen = new WindowAdapter(); 
		
		this.setLayout(new BorderLayout(10,10));
		this.setSize(500,300);
		this.setTitle("Ana Sayfa");
		this.setIconImage(icon.getImage());
		this.add(altPanel,BorderLayout.SOUTH);
		this.add(sgPanel,BorderLayout.EAST);
		this.setJMenuBar(bar);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(this);
		this.setResizable(false);
		//this.setDefaultCloseOperation();
		
		if(yetki>0) {
			personelfr.setEnabled(false);
		}
		if(yetki>1) {
			musterifr.setEnabled(false);
		}
		if(yetki>2) {
			stok.setEnabled(false);
		}
		
		
	}
	
	private void setDefaultCloseOperation()  {
		
		try {
			conn.FrameKp(1);
			conn.FrameKp(2);
			conn.FrameKp(3);
			conn.FrameKp(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==personelfr) {
			
				try {
					if(conn.FrameCk(1)==false) {
						personel = new Personel();
						conn.FrameAc(1);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}
		else if(e.getSource()==musterifr) {
			try {
				if(conn.FrameCk(2)==false) {
					new Musteri();
					conn.FrameAc(2);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==stok) {
			try {
				if(conn.FrameCk(3)==false) {
					new Stok();
					conn.FrameAc(3);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==LogOut) {
			try {
				new LogIn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}
		else if(e.getSource()==Exit) {
			System.exit(0);
		}
		else if(e.getSource()==fatura) {
	           
				try {
					if(conn.FrameCk(4)==false) {
						System.out.println(conn.FrameCk(4));
						new fisFrm(ID);
						conn.FrameAc(4);
					}
	    
	              
	            } catch (ParseException | SQLException e1) {

	                e1.printStackTrace();
	            }
	            
		}
			
		}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			conn.FrameKp(1);
			conn.FrameKp(2);
			conn.FrameKp(3);
			conn.FrameKp(4);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	}


