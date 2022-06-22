/*
Steps to create a program in Java
1. Write Program code
2. Save prg as classname.java
3. Compile using javac Filename.java
4. execute java ClassName
*/

public class HelloName{
	public static void main(String [] args){
	//String name ="Maduabuchi";
	String name;
	//Command Line Arguments
	name=args[0];
	String lastName=args [1];
	int favNum=Integer.parseInt(args [2]);
	System.out.println("Welcome "+name +" "+lastName +"" + favNum);

	}
		
}
