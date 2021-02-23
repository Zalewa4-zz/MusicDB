import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mysql.jdbc.PreparedStatement;
import com.thoughtworks.xstream.XStream;
/**
 * Wszystkie Metody
 * @author Zalewa
 *
 */
public class Methods {
	Database baza = new Database();
	Tekst text = new Tekst();
	/**
	 * Metoda Logowania do trybu gragicznego
	 */
	public void Log() {
		JFrame Window;
		try {
			Window = new Window();
			Window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			Window.setVisible(true);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	/**
	 * Dodawanie rekordów do tabeli.
	 * @param table
	 * @param table_1
	 */
	public void AddRowToJTable(JTable table, JTable table_1){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
        Object rowData[] = new Object[5];
        for(int i = 0; i < baza.muz.size(); i++)
        {
            rowData[0] = baza.muz.get(i).getID();
            rowData[1] = baza.muz.get(i).getTitle();
            rowData[2] = baza.muz.get(i).getArtist();
            rowData[3] = baza.muz.get(i).getAlbum();
            rowData[4] = baza.muz.get(i).getYear();
            model.addRow(rowData);
            model1.addRow(rowData);
            }
        }
	/**
	 * Pobieranie danych z bazy SQL do ArrayListy.
	 */
	public void ConnectSQL() { 
		baza.muz.clear();
		java.sql.Statement stmt = null;
	    String query = "SELECT ID, Title, Artist, Album, Year " +
	                   "FROM " + " baza " + " ORDER BY " + " ID";
	Connection conn = null;
	try {
	    conn =
	       DriverManager.getConnection("jdbc:mysql://localhost/baza?" +
                   "user=root&password=");

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	Muzyka muzyka = new Muzyka();
            muzyka.setID(rs.getInt("ID"));
            muzyka.setTitle(rs.getString("Title"));
            muzyka.setArtist(rs.getString("Artist"));
            muzyka.setAlbum(rs.getString("Album"));
            muzyka.setYear(rs.getInt("Year"));
            baza.muz.add(muzyka);
	}} catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	}
    /**
     * Odczyt z XML
     */
	public void ReadFromXML() {
		baza.muz.clear();
		   try {
				File fXmlFile = new File("data/Test.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("Muzyka");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element elem = (Element) nNode;
	                    int ID = Integer.parseInt(elem.getElementsByTagName("ID")
                             .item(0).getChildNodes().item(0).getNodeValue());
	                    
	                    String Title = elem.getElementsByTagName("title")
	                                        .item(0).getChildNodes().item(0).getNodeValue();

	                    String Artist = elem.getElementsByTagName("artist").item(0)
	                                        .getChildNodes().item(0).getNodeValue();

	                    String Album = elem.getElementsByTagName("album").item(0)
                             			.getChildNodes().item(0).getNodeValue();

	                    int Year = Integer.parseInt(elem.getElementsByTagName("year")
	                                        .item(0).getChildNodes().item(0).getNodeValue());

	                    baza.muz.add(new Muzyka(ID, Title, Artist, Album, Year));
	               }
	          }
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		        }
    }
    /**
     * Dodanie pustego wiersza.
     * @param table
     */
	public void AddEmptyRow(JTable table) {
	DefaultTableModel model = (DefaultTableModel) table.getModel();
	Object rowData[] = new Object[baza.muz.size()];
	model.addRow(new Object[]{table.getRowCount()+1});
}
   /**
    * Zapis do ArrayListy.
    * @param table
    */
	public void SaveToArrayList(JTable table) {
	baza.muz.clear();

	for(int row = 0; row < table.getRowCount(); row++) {
		Muzyka muzyka = new Muzyka();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(int column = 0; column < table.getColumnCount(); column++) {
			switch(column) {
				case 0:		muzyka.setID((int)model.getValueAt(row, column));
							break;
				case 1: 	muzyka.setTitle((String)model.getValueAt(row, column));
							break;
				case 2: 	muzyka.setArtist((String)model.getValueAt(row, column));
							break;
				case 3: 	muzyka.setAlbum((String)model.getValueAt(row, column));
							break;
				case 4: 	muzyka.setYear((int)model.getValueAt(row, column));
							break;
}
}

baza.muz.add(muzyka);
}
}
    /**
     * Zapis do SQL
     */
	public void SaveToSQL() {
		java.sql.Statement stmt = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/baza?" +
                   "user=root&password=");

			stmt = conn.createStatement();
			//Query do wyczyszczenia tabeli.
			String query2 = "DELETE FROM baza";
			PreparedStatement ps2=(PreparedStatement) conn.prepareStatement(query2);
			ps2.addBatch();
			ps2.executeBatch();
			//Query do dodania rekordów.
			String query = "INSERT INTO baza(ID, Title, Artist, Album, Year) "
	    		+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query); 
			for(int i = 0; i<baza.muz.size(); i++) {
				ps.setInt(1, baza.muz.get(i).getID()); 
				ps.setString(2, baza.muz.get(i).getTitle());
				ps.setString(3, baza.muz.get(i).getArtist());
				ps.setString(4, baza.muz.get(i).getAlbum());
				ps.setInt(5, baza.muz.get(i).getYear());
				ps.addBatch();
        }
			ps.executeBatch();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
	}

    }
   /**
    * Zapis do XML
    */
	public void SaveToXML() {
		File plik = new File("data/Test.xml");
		 try {
			plik.createNewFile();
			FileWriter strumienZapisu = new FileWriter(plik);
			XStream xstream = new XStream();
	   		String xml = xstream.toXML(baza.muz.toString());
			strumienZapisu.write(xstream.toXML(baza.muz)); 
			strumienZapisu.close(); 	
	     }
	     catch (IOException io)												
		 	{System.out.println(io.getMessage());}

	     catch (Exception se)
		 	{System.err.println("blad sec");
		 } 
    }
    /**
     * Czyszczenie tabeli
     * @param table
     * @param table_1
     */
	public void ClearTable(JTable table, JTable table_1) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);
    }
    /**
     * Usuwanie zaznaczonego wiersza
     * @param table
     */
	public void RemoveSelectedRow(JTable table) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.removeRow(table.getSelectedRow());;
    	for (int i = 0; i<table.getRowCount(); i++) {
    		table.setValueAt(i+1, i, 0); 
    	}
    }
	/**
	 * Metoda do otworzenia okienka "O Programie"
	 */
	public void AboutApp() {
	JFrame About;
	About = new About();
	About.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	About.setBounds(100, 100, 450, 300);
	About.setVisible(true);
	}

	
	/**
	 * Pokazywanie bazy danych w trybie tekstowym. 
	 */
	public void ShowDataBase() {
		System.out.println(baza.muz);
	}
	/**
	 * Dodawanie Objektu w trybie tekstowy.
	 */
	public void AddObject() {
		ShowDataBase();
		String Title, Artist, Album, Year;
		Title = JOptionPane.showInputDialog("Podaj Tytu³");
		Artist = JOptionPane.showInputDialog("Podaj Wykonawcê");
		Album = JOptionPane.showInputDialog("Podaj Album");
		Year = JOptionPane.showInputDialog("Podaj Rok");
		int x;
		x = Integer.parseInt(Year);
		if(x!=0 && Title!=null && Artist!=null && Album!=null) {
		Muzyka muzyka = new Muzyka();
		muzyka.setID(baza.muz.size()+1);
		muzyka.setTitle(Title);;
		muzyka.setArtist(Artist);
		muzyka.setAlbum(Album);
		muzyka.setYear(x);
		baza.muz.add(muzyka);
		ShowDataBase();
		}else {
			AddObject();
		}
	}
	/**
	 * Usuwanie obiektu w trybie tekstowym.
	 */
	public void RemoveObject() {
		ShowDataBase();
		String ID;
		ID = JOptionPane.showInputDialog("Podaj ID objektu, który chcesz usun¹æ:");
		int x1 = Integer.parseInt(ID);
		baza.muz.remove(x1-1);
		for(int i = 0; i<baza.muz.size(); i++) {
			baza.muz.get(i).setID(i+1);
		}
		ShowDataBase();
	}
}

