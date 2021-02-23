import java.util.Comparator;
/**
 * Klasa warstwy logicznej, kt�ra odpowiada za sortowanie wed�ug nazwy Artysty.
 * @author Adrian Zalewski
 *
 */
public class CompAr implements Comparator<Muzyka> {

	@Override
	public int compare(Muzyka o1, Muzyka o2) {
		if(o1.getArtist().compareTo(o2.getArtist())>0){
			return 1;
		}else{
			return -1;
		}
	}

}