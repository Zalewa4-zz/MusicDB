/**
 * Klasa Muzyka to jedna z najwa¿niejszych wartsw danych w moim programie. 
 * Zainicjowane s¹ tu typy danych jakie przechodz¹ przez buffor.
 * S¹ tu zdefiniowane metody getterów i setterów.
 * 
 * @author Adrian Zalewski
 */
public class Muzyka implements Comparable<Muzyka>{
	private int ID;
	private String title;
	private String artist;
	private String album;
	private int year;
	
	
	public Muzyka(int ID, String title, String artist, String album, int year){
		this.ID = ID;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	public Muzyka() {
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * compareTo porównuje ID (ASC)
	 */
	public int compareTo(Muzyka a) {
		if(this.ID>a.ID){
			return 1;
		}else{
			return -1;
		}
	}

/**
 * Metoda toString, która definiuje postaæ jednej linjki bazy danych w oknie konsoli.
 */
@Override
public String toString() {
	return "Muzyka [ID: " + ID + " Tytu³: " + title + ", Wykonawca: " + artist + ", Album: " + album + ", Rok wydania: " + year + "]\n";
}

}


