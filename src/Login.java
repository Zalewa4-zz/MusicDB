import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EtchedBorder;

/**
 * Klasa rozruchowa, odpowiedzialna za logowanie i wybór trybu w jakim ma pracowaæ program (tekstowy/graficzny).
 * 
 * @author Adrian Zalewski
 *
 */

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Database baza = new Database();
	Methods mtd = new Methods();
	private JPanel contentPane;
	private JTextField txtRoot;
	private JPasswordField passwordField;
	boolean choose = true;
	Tekst txt = new Tekst();
	

	/**
	 * Metoda main, odpowiada za uruchomienie okienka logowania. 
	 * Znajduje siê w niej metoda log(), która uruchamia tryb graficzny, je¿eli dokonamy takiego wyboru.
	 * @author Zalewa
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setTitle("Okno Logowania");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/**
 * Metoda Login(), to metoda, w której siê logujemy oraz dokonujemy wyboru pomiêdzy trybem graficznym, a tekstowym.
 */
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("U\u017Cytkownik:");
		lblNewLabel.setBounds(77, 51, 80, 25);
		panel.add(lblNewLabel);
		
		JLabel lblHaso = new JLabel("Has\u0142o:");
		lblHaso.setBounds(77, 81, 80, 25);
		panel.add(lblHaso);
		/**
		 * Login
		 */
		txtRoot = new JTextField();
		txtRoot.setText("root");
		txtRoot.setBounds(176, 52, 94, 22);
		panel.add(txtRoot);
		txtRoot.setColumns(10);
		/**
		 * Has³o
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 82, 94, 22);
		panel.add(passwordField);
		/**
		 * Przycisk logowania
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(176, 187, 94, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = txtRoot.getText();
				@SuppressWarnings("deprecation")
				String psd = passwordField.getText();
				if(uname.equals("root") && psd.equals("")) {
					if(choose==true) {
						mtd.Log();
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						JOptionPane.showConfirmDialog(contentPane, "Uda³o Ci siê zalogowaæ!");
						dispose();
					}else {
						txt.Tekstowy();
					}
			}	else {
				JOptionPane.showConfirmDialog(contentPane, "Z³e dane! WprowadŸ dane jeszcze raz.");
			}
				dispose();
			}
				
		});
		panel.add(btnLogin);
		
		JLabel lblTryb = new JLabel("Interfejs:");
		lblTryb.setBounds(77, 112, 80, 25);
		panel.add(lblTryb);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Graficzny");
		rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choose=true;
			}
		});
		rdbtnNewRadioButton.setBounds(87, 133, 109, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Tekstowy");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose=false;
			}
		});
		rdbtnNewRadioButton_1.setBounds(87, 159, 109, 23);
		panel.add(rdbtnNewRadioButton_1);
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnNewRadioButton);
	    group.add(rdbtnNewRadioButton_1);
	}
}
