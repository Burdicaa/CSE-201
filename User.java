
public class User {

	private String username;
    private String password;

    // Constructor with parameters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Default constructor
    public User() {
        this("","");
    }
    
    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Simple authentication check
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // For debugging: display user info (no password shown for security)
    @Override
    public String toString() {
        return "User: " + username;
    }
	
}
