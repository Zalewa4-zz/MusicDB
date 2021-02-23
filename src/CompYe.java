import java.util.Comparator;
/**
 * Klasa warstwy logicznej, która odpowiada za sortowanie wed³ug Roku.
 * @author Adrian Zalewski
 *
 */
public class CompYe implements Comparator<Muzyka> {

	@Override
	public int compare(Muzyka o1, Muzyka o2) {
		if(o1.getYear()>o2.getYear()){
			return 1;
		}else{
			return -1;
		}
	}

}
