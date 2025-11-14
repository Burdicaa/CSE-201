import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tester {

    private User user;
    private Pieces piece;

    @BeforeEach
    void setUp() {
        user = new User("alice", "password123");
        piece = new Pieces("Van Gogh", 1889, "Starry Night", "starry.jpg", "MoMA", 1000000,
                "A famous painting of a night sky.");
    }

    // -------------------- User Tests --------------------
    @Test
    void testUserConstructorAndGetters() {
        assertEquals("alice", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testUserSetters() {
        user.setUsername("bob");
        user.setPassword("newpass");
        assertEquals("bob", user.getUsername());
        assertEquals("newpass", user.getPassword());
    }

    @Test
    void testUserLogin() {
        assertTrue(user.login("alice", "password123"));
        assertFalse(user.login("alice", "wrongpass"));
        assertFalse(user.login("wronguser", "password123"));
    }

    @Test
    void testUserToString() {
        assertEquals("User: alice", user.toString());
    }

    // -------------------- Pieces Tests --------------------
    @Test
    void testPiecesConstructorAndGetters() {
        assertEquals("Van Gogh", piece.getCreator());
        assertEquals(1889, piece.getYear());
        assertEquals("Starry Night", piece.getName());
        assertEquals("MoMA", piece.getMuseum());
        assertEquals(1000000, piece.getWorth());
        assertEquals("A famous painting of a night sky.", piece.getDecription());
        assertEquals("starry.jpg", piece.getImage());
        assertEquals(0, piece.getCommentCount());
    }

    @Test
    void testAddAndGetComments() {
        piece.addComments("Amazing!");
        piece.addComments("Beautiful!");
        assertEquals(2, piece.getCommentCount());
        assertEquals("Amazing!", piece.getComment(0));
        assertEquals("Beautiful!", piece.getComment(1));
    }

    @Test
    void testRemoveComments() {
        piece.addComments("First comment");
        piece.addComments("Second comment");
        piece.removeComments(0);
        assertEquals(1, piece.getCommentCount());
        assertEquals("Second comment", piece.getComment(0));
    }

    @Test
    void testToArray() {
        String[] arr = piece.toArray();
        assertEquals("Van Gogh", arr[0]);
        assertEquals("1889", arr[1]);
        assertEquals("Starry Night", arr[2]);
        assertEquals("starry.jpg", arr[3]);
        assertEquals("MoMA", arr[4]);
        assertEquals("1000000", arr[5]);
        assertEquals("A famous painting of a night sky.", arr[6]);
    }

    @Test
    void testToString() {
        String expected = "\nName: Starry Night \nCreator: Van Gogh \nYear: 1889 \nMuseum: MoMA \nWorth: $1000000 \nDescription: A famous painting of a night sky.";
        assertEquals(expected, piece.toString());
    }
}




