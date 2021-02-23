import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import java.io.IOException;
import javax.xml.parsers.*;


import org.xml.sax.SAXException;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa Window to g³ówna klasa interfejsu graficznego, w której zawarte s¹ komponenty ca³ego wygl¹du programu, oraz odwo³ania do metod.
 * @author Adrian Zalewski
 *
 */
@SuppressWarnings("serial")

public class Window extends JFrame {

	Methods mtd = new Methods();
	Tekst txt = new Tekst();
	JPanel contentPane;
	JTable table;
	JTable table_1;

	/**
	 * G³ówna metoda klasy Window, w której okreœlony jest rozmiar, kolor okna itp.
	 */
	public static void main(String[] args)  {
		
		EventQueue.invokeLater(new Runnable() {
			/**
			 * Uruchomienie aplikacji
			 */
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setTitle("Program Zalewy");

					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/**
 * G³ówna Metoda interfejsu graficznego, w której zawarte s¹ wszystkie komponenty.
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws IOException
 */
	@SuppressWarnings("serial")
	public Window()throws ParserConfigurationException,
    SAXException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.CYAN);
		setJMenuBar(menuBar);
		
		JMenu mnPlik = new JMenu("Plik");
		menuBar.add(mnPlik);
		
		
		JMenuItem mntmPoczZBaz = new JMenuItem(new AbstractAction("Po\u0142\u0105cz z baz\u0105") {//Wczytanie bazy z SQL do ArrayListy
			
		    public void actionPerformed(ActionEvent e) {
		    	mtd.ConnectSQL();
		    }
		});
		
		mnPlik.add(mntmPoczZBaz);
		mntmPoczZBaz.setText("Wczytaj z bazy danych (SQL)");
		mntmPoczZBaz.setMnemonic(KeyEvent.VK_F1);
		mntmPoczZBaz.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F1, ActionEvent.CTRL_MASK));  
		JMenuItem mntmWczytajZPliku = new JMenuItem("Wczytaj z pliku (XML)");
		mntmWczytajZPliku.addActionListener(new ActionListener() {//Wczytanie bazy z XML do ArrayListy
			public void actionPerformed(ActionEvent e)  {
				mtd.ReadFromXML();
			}
		});
		mntmWczytajZPliku.setMnemonic(KeyEvent.VK_F3);
		mntmWczytajZPliku.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
		JMenuItem mntmNewMenuItem = new JMenuItem("Zapisz do bazy danych (SQL)");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mtd.SaveToArrayList(table);
				mtd.SaveToSQL();
			}
		});
		mntmNewMenuItem.setMnemonic(KeyEvent.VK_F2);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		mnPlik.add(mntmNewMenuItem);
		mnPlik.add(mntmWczytajZPliku);
		
		JMenuItem mntmZapiszDoPliku = new JMenuItem("Zapisz do pliku (XML)");
		mntmZapiszDoPliku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mtd.SaveToArrayList(table);
				mtd.SaveToXML();
			}
		});
		mntmZapiszDoPliku.setMnemonic(KeyEvent.VK_F4);
		mntmZapiszDoPliku.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
		mnPlik.add(mntmZapiszDoPliku);
		
		JMenu mnOpcje = new JMenu("Ustawienia");
		menuBar.add(mnOpcje);
		
		JMenuItem mntmWyloguj = new JMenuItem("Wyloguj");
		mntmWyloguj.addActionListener(new ActionListener() {//Wylogowanie
			public void actionPerformed(ActionEvent e) {
				JFrame Login = new Login();
				Login.setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showConfirmDialog(contentPane, "Wylogowano!");
				dispose();	
			}
		});
		mntmWyloguj.setMnemonic(KeyEvent.VK_Q);
		mntmWyloguj.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		JMenuItem mntmTrybTekstowy = new JMenuItem("Tryb Tekstowy");
		mnOpcje.add(mntmTrybTekstowy);
		mntmTrybTekstowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				txt.Tekstowy();
			}
		});
		mntmTrybTekstowy.setMnemonic(KeyEvent.VK_T);
		mntmTrybTekstowy.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		mnOpcje.add(mntmWyloguj);
		
		JMenu mnWidok = new JMenu("Pomoc");
		menuBar.add(mnWidok);
		
		JMenuItem mntmOProgramie = new JMenuItem("O programie");
		mntmOProgramie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mtd.AboutApp();
			}
		});
		mntmOProgramie.setMnemonic(KeyEvent.VK_I);
		mntmOProgramie.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		mnWidok.add(mntmOProgramie);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.darkShadow"));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton btnPokaDane = new JButton("Za\u0142aduj Tabel\u0119");
		btnPokaDane.setBounds(766, 14, 134, 23);
		btnPokaDane.addActionListener(new ActionListener() {//Za³adowanie tabeli
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
				model.setRowCount(0);
				model1.setRowCount(0);
				mtd.AddRowToJTable(table, table_1);
			}
		});
		contentPane.add(btnPokaDane);
		
		JButton btnNewButton = new JButton("Wyczy\u015B\u0107");
		btnNewButton.setBounds(766, 48, 134, 23);
		btnNewButton.addActionListener(new ActionListener() {//Czyszczenie tabeli1
			public void actionPerformed(ActionEvent arg0) {
				mtd.ClearTable(table, table_1);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dodaj Rekord");
		btnNewButton_1.setBounds(766, 82, 134, 23);
		btnNewButton_1.addActionListener(new ActionListener() {//Dodawanie pustego rekordu
			public void actionPerformed(ActionEvent e) {
				mtd.AddEmptyRow(table);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblPrzedEdycj = new JLabel("Przed edycj\u0105:");
		lblPrzedEdycj.setBounds(10, 299, 81, 14);
		contentPane.add(lblPrzedEdycj);
		
		JButton btnNewButton_3 = new JButton("Zapisz (XML)");
		btnNewButton_3.setBounds(910, 115, 134, 23);
		btnNewButton_3.addActionListener(new ActionListener() {//Zapis do XML
			public void actionPerformed(ActionEvent e) {
				mtd.SaveToArrayList(table);
				mtd.SaveToXML();
			}
		});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Zapisz (SQL)");
		btnNewButton_4.setBounds(910, 48, 134, 23);
		btnNewButton_4.addActionListener(new ActionListener() {//Zapis do SQL
			public void actionPerformed(ActionEvent arg0) {
				mtd.SaveToArrayList(table);
				mtd.SaveToSQL();
			}
		});
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Usu\u0144 Rekord");
		btnNewButton_5.addActionListener(new ActionListener() {//Usuwanie zaznaczonego rekordu
			public void actionPerformed(ActionEvent arg0) {
				mtd.RemoveSelectedRow(table);
			}
		});
		btnNewButton_5.setBounds(766, 115, 134, 23);
		contentPane.add(btnNewButton_5);
		//Tabele
		//Tabela 1 (na niej wykonuje operacje)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 746, 271);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Artist", "Album", "Year"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		//Table druga do podgl¹du (podgl¹d wczytanej bazy)
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 324, 746, 249);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Arist", "Album", "Year"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton btnWczytajxml = new JButton("Wczytaj (XML)");
		btnWczytajxml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mtd.ReadFromXML();
			}
		});
		btnWczytajxml.setBounds(910, 82, 134, 23);
		contentPane.add(btnWczytajxml);
		
		JButton btnNewButton_2 = new JButton("Wczytaj (SQL)");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mtd.ConnectSQL();
			}
		});
		btnNewButton_2.setBounds(910, 14, 134, 23);
		contentPane.add(btnNewButton_2);

	}
	}

