import java.util.Comparator;
/**
 * Klasa warstwy logicznej, która odpowiada za sortowanie wed³ug nazwy Albumu.
 * @author Adrian Zalewski
 *
 */
public class CompAl implements Comparator<Muzyka> {

	@Override
	public int compare(Muzyka o1, Muzyka o2) {
		if(o1.getAlbum().compareTo(o2.getAlbum())>0){
			return 1;
		}else{
			return -1;
		}
	}
}