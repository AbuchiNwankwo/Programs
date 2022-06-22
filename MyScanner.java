import java.util.Scanner;

class MyScanner{
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);

    System.out.println("Enter First name, last name and age:");

    // String input
    String firstname = myObj.nextLine();
	
	String lastname = myObj.nextLine();

    // Numerical input
    int age = myObj.nextInt();

    // Output input by user
    System.out.println("First Name: " + firstname);
	System.out.println("Last Name: " + lastname);
    System.out.println("Age: " + age);
  }
}