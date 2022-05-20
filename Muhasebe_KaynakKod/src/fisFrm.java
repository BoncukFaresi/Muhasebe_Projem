import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class fisFrm extends JFrame implements KeyListener,ActionListener, WindowListener{
	private JPanel contentPane;
	private JTextField adTxt;
	private JTextField fytTxt;
	private JTextField mktrTxt;
	JTextArea area;
	JButton btnEkle;
	String ara = "" ;
	JButton btnFis;
	JButton btnTemizle;
	JButton btnYazdr;
	ArrayList<String> adlar = new ArrayList<String>() ;
	ArrayList<Double> fiyatlar = new ArrayList<Double>() ;
	ArrayList<Double> adetler = new ArrayList<Double>() ;
	JScrollPane scrol;
	int ID = 0;
	dbConnect conn;

	public fisFrm(int id) throws ParseException, SQLException {
		conn=new dbConnect();
		ID = id;
		setTitle("Fatura sistemi");
		setDefaultCloseOperation();
		setBounds(100, 100, 922, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00DCr\u00FCn Fiyat");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(49, 134, 87, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblrnAd = new JLabel("\u00DCr\u00FCn Ad");
		lblrnAd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrnAd.setBounds(49, 56, 87, 34);
		contentPane.add(lblrnAd);
		
		adTxt = new JTextField();
		adTxt.setBounds(171, 56, 148, 30);
		contentPane.add(adTxt);
		adTxt.setColumns(10);
		
		
		fytTxt = new JTextField();
		fytTxt.setColumns(10);
		fytTxt.setBounds(171, 134, 148, 30);
		fytTxt.addKeyListener(this); 
		    
		;
		contentPane.add(fytTxt);
		
		JLabel lblrnMiktar = new JLabel("\u00DCr\u00FCn Miktar");
		lblrnMiktar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblrnMiktar.setBounds(49, 212, 101, 34);
		contentPane.add(lblrnMiktar);
		
		mktrTxt = new JTextField();
		mktrTxt.setColumns(10);
		mktrTxt.setBounds(171, 212, 148, 30);
		mktrTxt.addKeyListener(this);
		contentPane.add(mktrTxt);
		
		scrol = new JScrollPane();
		scrol.setBounds(434, 56, 425, 344);
		contentPane.add(scrol);
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 15));
		area.setTabSize(10);
		area.setBounds(434, 56, 425, 344);
		
		scrol.setViewportView(area);
		
		
		
		
		
		
		btnFis = new JButton("Olu\u015Ftur");
		btnFis.setFocusable(false);
		btnFis.addActionListener(this);
		btnFis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFis.setBounds(160, 366, 101, 34);
		contentPane.add(btnFis);
		
		btnTemizle = new JButton("Temizle");
		btnTemizle.setFocusable(false);
		btnTemizle.addActionListener(this);
		btnTemizle.setForeground(Color.RED);
		btnTemizle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTemizle.setBounds(35, 366, 101, 34);
		contentPane.add(btnTemizle);
		
		btnEkle = new JButton("Ekle");
		btnEkle.setFocusable(false);
		btnEkle.addActionListener(this);
		btnEkle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEkle.setBounds(285, 366, 101, 34);
		btnEkle.setEnabled(false);
		contentPane.add(btnEkle);
		
		btnYazdr = new JButton("Yazd\u0131r");
		btnYazdr.setFocusable(false);
		btnYazdr.addActionListener(this);
		btnYazdr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnYazdr.setBounds(434, 415, 101, 34);
		contentPane.add(btnYazdr);
		
		this.setSize(940,520);
		this.setResizable(false);
		ImageIcon icon1 = new ImageIcon("das.png");
		this.setIconImage(icon1.getImage());
		this.setVisible(true);
		this.addWindowListener(this);
		
		
	}
	


	private void setDefaultCloseOperation() throws SQLException {
		conn.FrameKp(4);
		this.dispose();
		
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		 char c = e.getKeyChar();
	        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
	            e.consume();  // if it's not a number, ignore the event
	        }
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnFis) {
				
				int toplam = Integer.parseInt(fytTxt.getText()) * Integer.parseInt(mktrTxt.getText());
				
				
				
				area.setText("*********************************************\n");
				area.setText(area.getText() + "*               Fatura Sistemi              *\n");
				area.setText(area.getText() + "*********************************************\n");
				
				java.util.Date date = new java.util.Date();
				area.setText(area.getText() + "\n" + date + "\n\n"); 
				
				area.setText(area.getText() + " Ürün Adý : " + adTxt.getText() + "\n" ); 
				area.setText(area.getText() + " Ürün Fiyat : " + fytTxt.getText() + " TL\n" ); 
				area.setText(area.getText() + " Ürün Miktar : " + mktrTxt.getText() + "\n\n" );
				
				adlar.add(adTxt.getText());
				fiyatlar.add(Double.parseDouble(fytTxt.getText()));
				adetler.add(Double.parseDouble(mktrTxt.getText()));
				
				
				
				
				//burada ekle butonu acilip surekli daha cok urun eklenebilir
				
				area.setText(area.getText() + "                           +\n");
				area.setText(area.getText() + "----------------------------\n");
				
				area.setText(area.getText() + " Toplam Fiyat : " + toplam+"\n"); 
				try {
					area.setText(area.getText() + " Ýþlemi Yapan : "+conn.dbgetIsim(ID));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnEkle.setEnabled(true);
				btnFis.setEnabled(false);
				adTxt.setText("");
				fytTxt.setText("");
				mktrTxt.setText("");
				
			}
		else if(e.getSource()==btnYazdr) {
			try {
				area.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==btnTemizle) {
			area.setText("");
			adTxt.setText("");
			fytTxt.setText("");
			mktrTxt.setText("");
			btnEkle.setEnabled(false);
			btnFis.setEnabled(true);
			adlar.clear();
			fiyatlar.clear();
			adetler.clear();
		}
		else if(e.getSource()==btnEkle) {
			
			Double toplam=0.0;
			adlar.add(adTxt.getText());
			fiyatlar.add(Double.parseDouble(fytTxt.getText()));
			adetler.add(Double.parseDouble(mktrTxt.getText()));
			
			
			area.setText("*********************************************\n");
			area.setText(area.getText() + "*               Fatura Sistemi              *\n");
			area.setText(area.getText() + "*********************************************\n");
			
			java.util.Date date = new java.util.Date();
			area.setText(area.getText() + "\n" + date + "\n\n"); 
			
			for(int i = 0 ; i<adlar.size();i++) {
			area.setText(area.getText() + " Ürün Adý : " + adlar.get(i) + "\n" ); 
			area.setText(area.getText() + " Ürün Fiyat : " + fiyatlar.get(i).toString() + " TL\n" ); 
			area.setText(area.getText() + " Ürün Miktar : " + adetler.get(i).toString() + "\n\n" );
			
			}
			
			for(int i = 0 ; i<adlar.size();i++) {
				toplam = toplam +(fiyatlar.get(i)*adetler.get(i));
			}
			
			//burada ekle butonu acilip surekli daha cok urun eklenebilir
			
			area.setText(area.getText() + "                           +\n");
			area.setText(area.getText() + "----------------------------\n");
			
			area.setText(area.getText() + " Toplam Fiyat : " + toplam+"\n"); 
			try {
				area.setText(area.getText() + " Ýþlemi Yapan : "+conn.dbgetIsim(ID));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnEkle.setEnabled(true);
			btnFis.setEnabled(false);
			adTxt.setText("");
			fytTxt.setText("");
			mktrTxt.setText("");
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
			conn.FrameKp(4);
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


