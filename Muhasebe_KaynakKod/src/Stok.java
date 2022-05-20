import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Stok extends JFrame implements ActionListener,MouseListener,WindowListener {

dbConnect conn;
	
	
	JPanel solPanel;
	JPanel sagPanel;
	JPanel tabloPanel;
	JPanel girisPanel;
	JPanel aramaPanel;
	JPanel butonPanel;
	JPanel dolguPanel;
	JPanel dolguPanel2;
	JPanel btnPanel;
	
	JTable tablo;
	
	JScrollPane scrol;
	
	JTextField idTxt;
	JTextField adTxt;
	JTextField fiyatTxt;
	JTextField adetTxt;
	JTextField araTxt;
	
	JLabel idLabel;
	JLabel adLabel;
	JLabel fiyatLabel;
	JLabel adetLabel;
	
	JButton kayýtbtn;
	JButton eklebtn;
	JButton silbtn;
	JButton yenile;
	JButton arabtn;
	
	JButton yazdir;
	
	String[] col= {"ID","Ürün Adý","Fiyat","adet"};
	
	JComboBox Kat;
	


	Stok() throws SQLException{
		conn = new dbConnect();
		
		
		
		
		
		solPanel = new JPanel();
		solPanel.setPreferredSize(new Dimension(600,700));
		
		sagPanel = new JPanel();
		sagPanel.setPreferredSize(new Dimension(400,700));
		
		tabloPanel = new JPanel();
		tabloPanel.setPreferredSize(new Dimension(600,500));
		tabloPanel.setLayout(new FlowLayout());
		tabloPanel.setBackground(Color.gray);
		solPanel.add(tabloPanel);
		
		girisPanel = new JPanel();
		girisPanel.setPreferredSize(new Dimension(400,300));
		girisPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		girisPanel.setBackground(Color.LIGHT_GRAY);
		sagPanel.add(girisPanel);
		
		aramaPanel=new JPanel();
		aramaPanel.setPreferredSize(new Dimension(600,200));
		aramaPanel.setLayout(new FlowLayout());
		aramaPanel.setBackground(Color.lightGray);
		solPanel.add(aramaPanel);
		
		butonPanel = new JPanel();
		butonPanel.setPreferredSize(new Dimension(400,400));
		butonPanel.setLayout(new GridLayout(2,3,10,10));
		butonPanel.setBackground(Color.gray);
		sagPanel.add(butonPanel);
		
		scrol = new JScrollPane();
		scrol.setPreferredSize(new Dimension(580,480));
		tabloPanel.add(scrol);
		
		tablo = new JTable(conn.dbGetStok(),col);
		tablo.setPreferredSize(new Dimension(580,480));
		tablo.addMouseListener(this);
		scrol.setViewportView(tablo);
		
		dolguPanel = new JPanel();
		dolguPanel.setBackground(Color.LIGHT_GRAY);
		dolguPanel.setPreferredSize(new Dimension(400,30));
		girisPanel.add(dolguPanel);
		
		idLabel = new JLabel("ID");
		idLabel.setPreferredSize(new Dimension(100,20));
		idLabel.setHorizontalTextPosition(JLabel.CENTER);
		girisPanel.add(idLabel);
		idTxt = new JTextField();
		idTxt.setPreferredSize(new Dimension(200,20));
		idTxt.setEditable(false);
		girisPanel.add(idTxt);
		
		adLabel = new JLabel("Ürün Adý");
		adLabel.setPreferredSize(new Dimension(100,20));
		idLabel.setHorizontalTextPosition(JLabel.CENTER);
		girisPanel.add(adLabel);
		adTxt = new JTextField();
		adTxt.setPreferredSize(new Dimension(200,20));
		girisPanel.add(adTxt);
		
		fiyatLabel = new JLabel("Fiyat");
		fiyatLabel.setPreferredSize(new Dimension(100,20));
		idLabel.setHorizontalTextPosition(JLabel.CENTER);
		girisPanel.add(fiyatLabel);
		fiyatTxt = new JTextField();
		fiyatTxt.setPreferredSize(new Dimension(200,20));
		girisPanel.add(fiyatTxt);
		
		adetLabel = new JLabel("Adet");
		adetLabel.setPreferredSize(new Dimension(100,20));
		idLabel.setHorizontalTextPosition(JLabel.CENTER);
		girisPanel.add(adetLabel);
		adetTxt = new JTextField();
		adetTxt.setPreferredSize(new Dimension(200,20));
		girisPanel.add(adetTxt);
		
		
		kayýtbtn= new JButton("Kaydet");
		kayýtbtn.setFocusable(false);
		kayýtbtn.addActionListener(this);
		
		eklebtn = new JButton("Ekle");
		eklebtn.setFocusable(false);
		eklebtn.addActionListener(this);
		
		silbtn = new JButton("Sil");
		silbtn.setFocusable(false);
		silbtn.addActionListener(this);
		
		yenile = new JButton("Yenile");
		yenile.setFocusable(false);
		yenile.addActionListener(this);
		
		dolguPanel2 = new JPanel();
		dolguPanel2.setBackground(Color.LIGHT_GRAY);
		dolguPanel2.setPreferredSize(new Dimension(400,30));
		girisPanel.add(dolguPanel2);
		
		btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(350,40));
		btnPanel.setLayout(new GridLayout(1,4,10,10));
		btnPanel.setBackground(Color.gray);
		btnPanel.add(kayýtbtn);		
		btnPanel.add(eklebtn);
		btnPanel.add(silbtn);
		btnPanel.add(yenile);
		
		Kat = new JComboBox(col);
		aramaPanel.add(Kat);
		
		araTxt = new JTextField();
		araTxt.setPreferredSize(new Dimension(200,30));
		aramaPanel.add(araTxt);
		
		arabtn = new JButton("Ara");
		arabtn.setFocusable(false);
		arabtn.addActionListener(this);
		aramaPanel.add(arabtn);
		
		girisPanel.add(btnPanel);
		
		yazdir = new JButton("Yazdýr");
		yazdir.setFocusable(false);
		yazdir.addActionListener(this);
		aramaPanel.add(yazdir);
		
		
				
		this.setDefaultCloseOperation();
		this.setSize(1000,600);
		ImageIcon icon = new ImageIcon("das.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Stok Ýþlemleri");
		this.setLayout(new BorderLayout(0,0));
		this.add(solPanel,BorderLayout.WEST);
		this.add(sagPanel,BorderLayout.EAST);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(this);
	}

	private void setDefaultCloseOperation() throws SQLException {
		conn.FrameKp(3);
		this.dispose();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==kayýtbtn) {
			try {
				conn.dbUpdateS(idTxt, adTxt, fiyatTxt, adetTxt);
				tablo = new JTable(conn.dbGetStok(),col);
				tablo.setPreferredSize(new Dimension(580,480));
				tablo.addMouseListener(this);
				scrol.setViewportView(tablo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==yenile) {
			try {
				tablo = new JTable(conn.dbGetStok(),col);
				tablo.setPreferredSize(new Dimension(580,480));
				tablo.addMouseListener(this);
				scrol.setViewportView(tablo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			idTxt.setText("");
			adTxt.setText("");
			fiyatTxt.setText("");
			adetTxt.setText("");
			
			
		}
		else if(e.getSource()==eklebtn) {
			try {
				conn.dbEkleS(adTxt, fiyatTxt, adetTxt);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==silbtn) {
			try {
				conn.dbSilM(idTxt, "stok");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==arabtn) {
			try {
				tablo = new JTable(conn.dbAraS(araTxt,Kat),col);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tablo.setPreferredSize(new Dimension(580,480));
			tablo.addMouseListener(this);
			scrol.setViewportView(tablo);
		}
		else if(e.getSource()==yazdir) {
			conn.yazdir(tabloPanel);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==tablo) {
			idTxt.setText(tablo.getValueAt(tablo.getSelectedRow(),0).toString());
			adTxt.setText(tablo.getValueAt(tablo.getSelectedRow(),1).toString());
			fiyatTxt.setText(tablo.getValueAt(tablo.getSelectedRow(),2).toString());
			adetTxt.setText(tablo.getValueAt(tablo.getSelectedRow(),3).toString());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			conn.FrameKp(3);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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


