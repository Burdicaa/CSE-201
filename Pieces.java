
public class Pieces {
	private int year;
	private String name;
	private String museum;
	private int worth;
	private String description;
	private String creator;
	private String image;
	private String[] comments;
	private int commentcount;
	
	public Pieces(String creator, int year, String name, String image, String museum, int worth,
			String description) {
		this.year = year;
		this.name = name;
		this.museum = museum;
		this.worth = worth;
		this.description = description;
		this.creator = creator;
		this.image = image;
		
		comments = new String[10];
	}
	
	public int getYear() {
		return year;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMuseum() {
		return museum;
	}
	
	public int getWorth() {
		return worth;
	}
	
	public String getDecription() {
		return description;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public String getImage() {
		return image;
	}
	
	public String[] getComments() {
		return comments;
	}
	
	public int getCommentCount() {
		return commentcount;
	}
	
	public String getComment(int index) {
		return comments[index];
	}
	
	public void addComments(String s) {
		if (commentcount == comments.length) {
			String[] temp = new String[comments.length * 2];
			for (int i = 0; i < comments.length; i++) {
				temp[i] = comments[i];
			}
			comments = temp;
		}
		comments[commentcount] = s;
		commentcount++;
	}
	
	public void removeComments(int n) {
		for (int i = 0; i < commentcount; i++) {
			if (comments[i] == comments[n]) {
				comments[i] = comments[commentcount - 1];
				comments[commentcount - 1] = null;
				commentcount--;
			}
		}
	}
	
	public String[] toArray() {
		String[] result = new String[7];
		result[0] = creator;
		result[1] = "" + year;
		result[2] = name;
		result[3] = image;
		result[4] = museum;
		result[5] = "" + worth;
		result[6] = description;

		return result;
	}
	
	public String toString() {
		String f = String.format("\nName: %s \nCreator: %s \nYear: %d \nMuseum: %s \nWorth: $%d"
				+ " \nDescription: %s", name, creator, year, museum, worth, description);
		return f;
	}
}
