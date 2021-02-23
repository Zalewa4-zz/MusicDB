import java.util.ArrayList;
/**
 * Klasa Database, w której zainicjowana jest ArrayLista, która jest bufforem danych w programie.
 * @author Adrian Zalewski
 *
 */
public class Database {
	ArrayList<Muzyka> muz = new ArrayList<Muzyka>();
	/**
	 * Metoda toString, która odpowiada za wygl¹d bazy danych w oknie konsoli.
	 */
	public String toString(){
		String x = null;
		for(int i=0; i<muz.size(); i++){
			x += muz.get(i) + "\n";
		}
		return x;
		
		
	}
}