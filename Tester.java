import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
		Pieces test = new Pieces("c", 1990, "n", "i", "m", 1000, "d");
		System.out.println(test);
		
		String s1 = "This is a test";
		String s2 = "This is a test2";
		String s3 = "This is a test3";
		String s4 = "This is a test4";
		String s5 = "This is a test5";
		String s6 = "This is a test6";
		String s7 = "This is a test7";
		String s8 = "This is a test8";
		String s9 = "This is a test9";
		String s10 = "This is a test10";


		test.addComments(s1);
		test.addComments(s2);
		test.addComments(s3);
		test.addComments(s4);
		test.addComments(s5);
		test.addComments(s6);
		test.addComments(s7);
		test.addComments(s8);
		test.addComments(s9);
		test.addComments(s10);

		System.out.println(Arrays.toString(test.getComments()));
		System.out.println(test.getComment(0));
		System.out.println(test.getComment(1));
		System.out.println(test.getComment(2));	
		System.out.println(test.getCommentCount());
		test.addComments("s11");
		
		System.out.println(Arrays.toString(test.getComments()));
		test.removeComments(2);
		System.out.println(Arrays.toString(test.getComments()));


		

	}

}


