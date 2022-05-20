import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.sql.ResultSet;



public class dbConnect {
	static Connection con;
	static Statement mystat;
	static ResultSet myres;
	
	
	dbConnect() throws SQLException{
		con = (Connection) DriverManager.getConnection("jdbc:sqlite:muhasebe.db");
		mystat = (Statement) con.createStatement();
		
	}
	
	public void	yazdir(JPanel p) {
		PrinterJob job=PrinterJob.getPrinterJob(); job.setJobName("Printer Data"); job.setPrintable(new Printable(){

            public int print(Graphics pg,PageFormat pf,int pageNum){

                                if(pageNum>0){
                                return Printable.NO_SUCH_PAGE;
                                }
                                Graphics2D g2=(Graphics2D)pg;
                                g2.translate(pf.getImageableX(),pf.getImageableY());
                                g2.scale(1,2);

                                p.paint(g2);
                                return Printable.PAGE_EXISTS;
                            }



        });
        boolean ok=job.printDialog(); if(ok){ try{ job.print(); } catch(PrinterException ex){} }
	
	}
	
	
	public boolean dbContainUser(String user) throws SQLException {
		myres = null;
		
		myres =mystat.executeQuery("SELECT username FROM personel_giris ;");
		ArrayList<String> usrnms = new ArrayList<String>();
		
		while(myres.next()) {
			usrnms.add(myres.getString("username"));
		}
		
		return usrnms.contains(user);
		
	}
	
	
public boolean dbPasswordCheck(String pass) throws SQLException {
		myres=null;
		myres =mystat.executeQuery("SELECT password FROM personel_giris ;");
		ArrayList<String> pases = new ArrayList<String>();
		
		while(myres.next()) {
			pases.add(myres.getString("password"));
		}
		
		return pases.contains(pass);
	}
	
public int dbid(String user) throws SQLException {
		
		myres = mystat.executeQuery("SELECT personel_id FROM personel_giris WHERE username="+"'"+user+"'"+" ;");
		ArrayList<Integer> id = new ArrayList<Integer>();
		int i = 0;
		while(myres.next()) {
			if (i == 1) {
				System.out.println("id iki giriþ oldu i = "+i);//hata mesajý
				System.exit(0);
			}
			else {
				id.add(myres.getInt("personel_id"));
			}
			i++;
		}
		
		return id.get(0) ; 
	}
	
public String dbgetIsim(int id) throws SQLException {
		myres = mystat.executeQuery("SELECT ad,soyad FROM personel WHERE personel_id="+id+";");
		ArrayList<String>	isim = new ArrayList<String>();
		while(myres.next()) {
			isim.add(myres.getString("ad"));
			isim.add(myres.getString("soyad"));
		}
		return isim.get(0)+" "+isim.get(1)+" ";
	}
	
public int dbgetYetki(int id) throws SQLException {
		myres = mystat.executeQuery("SELECT yetki FROM personel WHERE personel_id="+id+";");
		ArrayList<Integer> yetki = new ArrayList<Integer>();
		while(myres.next()) {
			yetki.add(myres.getInt("yetki"));
		}
		return yetki.get(0);
	}
public Object[][] dbGetPersonel() throws SQLException{
		myres = mystat.executeQuery("SELECT personel_id,ad,soyad,maas,telno,gorev FROM personel;");
		ArrayList<Object> bir = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
		int q=0;
		while(myres.next()) {
			bir.clear();
			bir.add(myres.getObject("personel_id"));
			bir.add(myres.getObject("ad"));
			bir.add(myres.getObject("soyad"));
			bir.add(myres.getObject("maas"));
			bir.add(myres.getObject("telno"));
			bir.add(myres.getObject("gorev"));
			
			
			tum.add(new ArrayList<Object>(bir));
			
			
		}
		
		Object[][] obje = new Object[tum.size()][6];
		
		for(int i=0;i<tum.size();i++) {
			for(int j=0;j<6;j++) {
				obje[i][j]=tum.get(i).get(j);
			}
		}
		
		return obje;

	}
public void dbUpdate(JTextField idt,JTextField adt,JTextField soyt,JTextField maast,JTextField telt,JTextField gort) throws SQLException {
	try {
	int id = Integer.parseInt(idt.getText());
	String ad = adt.getText();
	String soy = soyt.getText();
	

	int maas = Integer.parseInt(maast.getText());

	
	String tel = telt.getText();
	String gor = gort.getText();
	int yetki = 3 ;
	
	if(gor.contains("Yönetici")||gor.contains("YÖNETÝCÝ")||gor.contains("yönetici")) {
		yetki = 0;
	}
	else if(gor.contains("Muhasebe")||gor.contains("muhasebe")||gor.contains("MUHASEBE")) {
		yetki = 1 ;
	}
	else if(gor.contains("depo")||gor.contains("Depo")||gor.contains("DEPO")||gor.contains("Stok")||gor.contains("STOK")||gor.contains("stok")) {
		yetki = 2;
	}
	else {
		yetki = 3;
	}
	mystat.executeUpdate("UPDATE `personel` SET `ad` = '"+ad+" ', `soyad` = '"+soy+"', `maas` = '"+maas+"', `telno` = '"+tel+"', `gorev` = '"+gor+"', `yetki` = '"+yetki+"' WHERE (`personel_id` = '"+id+"');");
	}catch(NumberFormatException e1) {
		JOptionPane.showMessageDialog(null,"Lütfen maas kýsmýna sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
		}

}
public void dbEkle(JTextField adt,JTextField soyt,JTextField maast,JTextField telt,JTextField gort,String usr,String pass) throws SQLException {
	try {
	String ad = adt.getText();
	String soy = soyt.getText();
	int maas = Integer.parseInt(maast.getText());
	String tel = telt.getText();
	String gor = gort.getText();
	int yetki = 3 ;
	
	if(gor.contains("Yönetici")||gor.contains("YÖNETÝCÝ")||gor.contains("yönetici")) {
		yetki = 0;
	}
	else if(gor.contains("Muhasebe")||gor.contains("muhasebe")||gor.contains("MUHASEBE")) {
		yetki = 1 ;
	}
	else if(gor.contains("depo")||gor.contains("Depo")||gor.contains("DEPO")||gor.contains("Stok")||gor.contains("STOK")||gor.contains("stok")) {
		yetki = 2;
	}
	else {
		yetki = 3;
	}
	mystat.executeUpdate("INSERT INTO `personel` ( `ad`, `soyad`, `maas`, `telno`, `gorev`, `yetki`) VALUES ( '"+ad+"', '"+soy+"', '"+maas+"', '"+tel+"', '"+gor+	"', '"+yetki+"');");
	mystat.executeUpdate("INSERT INTO personel_giris (`username`,`password`) VALUES ('"+usr+"','"+pass+"');");
	}catch(NumberFormatException e1) {
		JOptionPane.showMessageDialog(null,"Lütfen maas kýsmýna sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
		}
}

public void dbSil(JTextField idt,String tab) throws SQLException {
	int id = Integer.parseInt(idt.getText());
	mystat.executeUpdate("DELETE FROM `"+tab+"` WHERE (`personel_id` = '"+id+"');");
	mystat.executeUpdate("DELETE FROM `personel_giris` WHERE (`personel_id` = '"+id+"');");
}
public Object[][] dbAra(JTextField txt,JComboBox box) throws SQLException {
	String ara = txt.getText();
	String col;
	
	switch(box.getSelectedItem().toString()) {
	case "ID":col = "personel_id";break;
	case "Ýsim":col = "ad";break;
	case "SoyÝsim":col = "soyad";break;
	case "Maaþ":col ="maas";break;
	case "Telefon Numarasý":col="telno";break;
	default:col="gorev";break;
	}
	if(col.equals("personel_id")||col.equals("maas")) {
		myres = mystat.executeQuery("SELECT personel_id,ad,soyad,maas,telno,gorev FROM personel WHERE "+col+"="+ara+";");
	}
	else {
		myres = mystat.executeQuery("SELECT personel_id,ad,soyad,maas,telno,gorev FROM personel WHERE "+col+" like '%"+ara+"%';");
	}
	
	
	
	ArrayList<Object> bir = new ArrayList<Object>();
	ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
	int q=0;
	while(myres.next()) {
		bir.clear();
		bir.add(myres.getObject("personel_id"));
		bir.add(myres.getObject("ad"));
		bir.add(myres.getObject("soyad"));
		bir.add(myres.getObject("maas"));
		bir.add(myres.getObject("telno"));
		bir.add(myres.getObject("gorev"));
		
		
		tum.add(new ArrayList<Object>(bir));
		
		
	}
	
	Object[][] obje = new Object[tum.size()][6];
	
	for(int i=0;i<tum.size();i++) {
		for(int j=0;j<6;j++) {
			obje[i][j]=tum.get(i).get(j);
		}
	}
	
	return obje;
	
}

public void dbUpdateM(JTextField idt,JTextField adt,JTextField soyt,JTextField bakt,JTextField telt)throws SQLException {
	try {
		int id = Integer.parseInt(idt.getText());
		String ad = adt.getText();
		String soy = soyt.getText();
		

		int bakiye = Integer.parseInt(bakt.getText());

		
		String tel = telt.getText();
		
		mystat.executeUpdate("UPDATE `musteri` SET `ad` = '"+ad+" ', `soyad` = '"+soy+"', `bakiye` = '"+bakiye+"', `tel` = '"+tel+"' WHERE (`id` = '"+id+"');");
		}catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null,"Lütfen bakiye kýsmýna sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
			}
}
public Object[][] dbGetMusteri() throws SQLException{
	myres = mystat.executeQuery("SELECT id,ad,soyad,bakiye,tel FROM musteri;");
	ArrayList<Object> bir = new ArrayList<Object>();
	ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
	while(myres.next()) {
		bir.clear();
		bir.add(myres.getObject("id"));
		bir.add(myres.getObject("ad"));
		bir.add(myres.getObject("soyad"));
		bir.add(myres.getObject("bakiye"));
		bir.add(myres.getObject("tel"));
		
		
		tum.add(new ArrayList<Object>(bir));
		
		
	}
	
	Object[][] obje = new Object[tum.size()][6];
	
	for(int i=0;i<tum.size();i++) {
		for(int j=0;j<5;j++) {
			obje[i][j]=tum.get(i).get(j);
		}
	}
	
	return obje;
	}
public void dbEkleM(JTextField adt,JTextField soyt,JTextField bakt,JTextField telt) throws SQLException{
	try {
		String ad = adt.getText();
		String soy = soyt.getText();
		int bakiye = Integer.parseInt(bakt.getText());
		String tel = telt.getText();
		
		mystat.executeUpdate("INSERT INTO `musteri` ( `ad`, `soyad`, `bakiye`, `tel`) VALUES ( '"+ad+"', '"+soy+"', "+bakiye+", '"+tel+"');");
		}catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null,"Lütfen bakiye kýsmýna sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
			}
}
public Object[][] dbAraM(JTextField txt,JComboBox box) throws SQLException{
	String ara = txt.getText();
	String col;
	
	switch(box.getSelectedItem().toString()) {
	case "ID":col = "id";break;
	case "Ýsim":col = "ad";break;
	case "SoyÝsim":col = "soyad";break;
	case "Maaþ":col ="bakiye";break;
	default:col="tel";break;
	}
	if(col.equals("id")||col.equals("bakiye")) {
		myres = mystat.executeQuery("SELECT id,ad,soyad,bakiye,tel FROM musteri WHERE "+col+"="+ara+";");
	}
	else {
		myres = mystat.executeQuery("SELECT id,ad,soyad,bakiye,tel FROM musteri WHERE "+col+" like '%"+ara+"%';");
	}
	
	
	
	ArrayList<Object> bir = new ArrayList<Object>();
	ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
	int q=0;
	while(myres.next()) {
		bir.clear();
		bir.add(myres.getObject("id"));
		bir.add(myres.getObject("ad"));
		bir.add(myres.getObject("soyad"));
		bir.add(myres.getObject("bakiye"));
		bir.add(myres.getObject("tel"));
		
		
		tum.add(new ArrayList<Object>(bir));
		
		
	}
	
	Object[][] obje = new Object[tum.size()][6];
	
	for(int i=0;i<tum.size();i++) {
		for(int j=0;j<5;j++) {
			obje[i][j]=tum.get(i).get(j);
		}
	}
	
	return obje;
}
public void dbSilM(JTextField idt,String tab) throws SQLException {
	int id = Integer.parseInt(idt.getText());
	mystat.executeUpdate("DELETE FROM `"+tab+"` WHERE (`id` = '"+id+"');");
	}
public void dbUpdateS(JTextField idt,JTextField adt,JTextField fiyt,JTextField adett)throws SQLException {
	try {
		int id = Integer.parseInt(idt.getText());
		String ad = adt.getText();
		int fiyat = Integer.parseInt(fiyt.getText());
		int adet = Integer.parseInt(adett.getText());
		
		mystat.executeUpdate("UPDATE `stok` SET `ad` = '"+ad+" ', `fiyat` = '"+fiyat+"', `adet` = '"+adet+"' WHERE (`id` = '"+id+"');");
		}catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null,"Lütfen istenilen kýsmýma sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
			}
}
public Object[][] dbGetStok() throws SQLException{
	myres = mystat.executeQuery("SELECT id,ad,fiyat,adet FROM stok;");
	ArrayList<Object> bir = new ArrayList<Object>();
	ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
	while(myres.next()) {
		bir.clear();
		bir.add(myres.getObject("id"));
		bir.add(myres.getObject("ad"));
		bir.add(myres.getObject("fiyat"));
		bir.add(myres.getObject("adet"));
		
		
		tum.add(new ArrayList<Object>(bir));
		
		
	}
	
	Object[][] obje = new Object[tum.size()][5];
	
	for(int i=0;i<tum.size();i++) {
		for(int j=0;j<4;j++) {
			obje[i][j]=tum.get(i).get(j);
		}
	}
	
	return obje;
	}
public void dbEkleS(JTextField adt,JTextField fiyatt,JTextField adett) throws SQLException{
	try {
		String ad = adt.getText();
		int fiyat = Integer.parseInt(fiyatt.getText());
		int adet = Integer.parseInt(adett.getText());
		
		mystat.executeUpdate("INSERT INTO `stok` ( `ad`,  `fiyat`, `adet`) VALUES ( '"+ad+"', "+fiyat+", '"+adet+"');");
		}catch(NumberFormatException e1) {
			JOptionPane.showMessageDialog(null,"Lütfen istenilen kýsýmlara sayý deðeri giriniz","Uyarý",JOptionPane.WARNING_MESSAGE);
			}
}
public Object[][] dbAraS(JTextField txt,JComboBox box) throws SQLException{
	String ara = txt.getText();
	String col;
	
	switch(box.getSelectedItem().toString()) {
	case "ID":col = "id";break;
	case "Ürün Adý":col = "ad";break;
	case "Fiyat":col ="fiyat";break;
	default:col="adet";break;
	}
	if(col.equals("id")||col.equals("diyat")) {
		myres = mystat.executeQuery("SELECT id,ad,fiyat,adet FROM stok WHERE "+col+"="+ara+";");
	}
	else {
		myres = mystat.executeQuery("SELECT id,ad,fiyat,adet FROM stok WHERE "+col+" like '%"+ara+"%';");
	}
	
	
	
	ArrayList<Object> bir = new ArrayList<Object>();
	ArrayList<ArrayList<Object>> tum = new ArrayList<ArrayList<Object>>();
	int q=0;
	while(myres.next()) {
		bir.clear();
		bir.add(myres.getObject("id"));
		bir.add(myres.getObject("ad"));
		bir.add(myres.getObject("fiyat"));
		bir.add(myres.getObject("adet"));
		
		
		tum.add(new ArrayList<Object>(bir));
		
		
	}
	
	Object[][] obje = new Object[tum.size()][4];
	
	for(int i=0;i<tum.size();i++) {
		for(int j=0;j<4;j++) {
			obje[i][j]=tum.get(i).get(j);
		}
	}
	
	return obje;
}
public boolean FrameCk(int id) throws SQLException {
	
	myres=mystat.executeQuery("SELECT op FROM frame WHERE id="+id+";");
	
	ArrayList<Integer>op = new ArrayList<Integer>();
	
	while(myres.next()) {
		op.add(myres.getInt("op"));
	}
	if(op.get(0)==1) {
		return true;
	}
	else {
	return false;
	}
}
public void FrameAc(int id) throws SQLException{
	mystat.executeUpdate("UPDATE frame SET op=1 WHERE id="+id+";");
}
public void FrameKp(int id)throws SQLException{
	mystat.executeUpdate("UPDATE frame SET op=0 WHERE id="+id+";");
}
}













