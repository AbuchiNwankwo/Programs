import java.util.Scanner;

class IScan{
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);

    System.out.println("Enter name and age:");

    // String input
    String name = myObj.nextLine();

    // Numerical input
    int age = myObj.nextInt();

    // Output input by user
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);

  }
}