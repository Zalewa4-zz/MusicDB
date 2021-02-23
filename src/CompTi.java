import java.util.Comparator;
/**
 * Klasa warstwy logicznej, która odpowiada za sortowanie wed³ug nazwy Tytu³u.
 * @author Adrian Zalewski
 *
 */
public class CompTi implements Comparator<Muzyka> {

	@Override
	public int compare(Muzyka o1, Muzyka o2) {
		if(o1.getTitle().compareTo(o2.getTitle())>0){
			return 1;
		}else{
			return -1;
		}
	}

}