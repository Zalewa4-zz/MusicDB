import java.util.Collections;
import javax.swing.*;
/**
 * Klasa interfejsu tekstowego.
 * @author Adrian Zalewski
 *
 */
public class Tekst {
	/**
	 * G³ówna metoda interfejsu tekstowego.
	 */
	public void Tekstowy() {
	Methods mtd = new Methods();
	boolean koniec = false;
	while(!koniec){
	String txt1;
	txt1 = JOptionPane.showInputDialog("1 - Wczytaj Bazê danych:\n2 - Sortuj wed³ug:"
			+ "\n3 - Dodaj rekord.\n4 - Usuñ rekord."
			+ "\n5 - Zapisz do:\n9 - Przejdz do trybu graficznego \n0 - Zakoñcz.");
	int a = Integer.parseInt(txt1);
	switch (a) {
	case 1: koniec = true;
			boolean koniec2 = false;
			while(!koniec2) {
			String txt2;
			txt2 = JOptionPane.showInputDialog("1 - SQL \n2 - XML\n0 - Powrót");
			int b = Integer.parseInt(txt2);
			switch (b) {
				case 1: mtd.ConnectSQL();
			 			mtd.ShowDataBase();
			 			koniec2 = true;
			 			koniec = false;
			 			break;
			 	case 2: mtd.ReadFromXML();
			 			mtd.ShowDataBase();
			 			koniec2 = true;
			 			koniec = false;
			 			break;
			 	case 0: koniec2 = true;
			 			koniec = false;
			 			break;
			 	default: break;
			 }
			 }
			break;
	case 2: koniec = true;
			boolean koniec3 = false;
			while(!koniec3) {
				String txt3;
				txt3 = JOptionPane.showInputDialog("1 - ID \n2 - Tytu³\n3 - Artysta\n4 - Album\n5 - Rok\n0 - Powrót.");
				int c = Integer.parseInt(txt3);
				switch (c) {
				case 1: Collections.sort(mtd.baza.muz);
						mtd.ShowDataBase();
						koniec3 = true;
						koniec = false;
						break;
				case 2: Collections.sort(mtd.baza.muz, new CompTi());
	 					mtd.ShowDataBase();
	 					koniec3 = true;
	 					koniec = false;
	 					break;
				case 3: Collections.sort(mtd.baza.muz, new CompAr());
						mtd.ShowDataBase();
						koniec3 = true;
						koniec = false;
						break;
				case 4: Collections.sort(mtd.baza.muz, new CompAl());
						mtd.ShowDataBase();
						koniec3 = true;
						koniec = false;
						break;
				case 5: Collections.sort(mtd.baza.muz, new CompYe());
						mtd.ShowDataBase();
						koniec3 = true;
						koniec = false;
						break;
				case 0: koniec3 = true;
						koniec = false;
				default: break;
				}
			}
			break;
	case 3: mtd.AddObject();
			break;
	case 4: mtd.RemoveObject();
			break;
	case 5:	koniec = true;
			boolean koniec4 = false;
			while(!koniec4) {
				String txt4;
				txt4 = JOptionPane.showInputDialog("1 - SQL \n2 - XML\n0 - Powrót.");
				int d = Integer.parseInt(txt4);
				switch (d) {
					case 1: mtd.SaveToSQL();;
							koniec4 = true;
							koniec = false;
							break;
					case 2: mtd.SaveToXML();
							koniec4 = true;
							koniec = false;
							break;
					case 0: koniec4 = true;
							koniec = false;
					default: break;
				}
			}
			break;
	case 9: mtd.Log();
			koniec=true;
	case 0:	koniec=true;
	default: break;
	}
}
}}
