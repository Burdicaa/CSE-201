import java.util.Scanner;

public class Search {
	private String keyword;
	private Pieces[] galleryKey;

	public Search (String keyword, Pieces[] gallery) {
		this.keyword;
		this.gallery;
	}
	
	public void searchInput() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Search: ");
		
		keyword = keyboard.nextLine();
		System.out.println("Search: " + keyword);
		keyboard.close();
	}
	
	public Pieces[] searchKey(String key) {
		if (key.equals(null)) {
			System.out.println("No keyword selected");
			return galleryKey;
		}
		
		return galleryKey;
	}
	
	public String getKeyword() {
		return keyword;
	}
}
