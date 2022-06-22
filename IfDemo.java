// Find the greatest of 3 numbers

public class IfDemo{
	public static void main(String [] args){
	int num1=Integer.parseInt(args[0]); //100
	int num2=Integer.parseInt(args[1]); //15
	int num3=Integer.parseInt(args[2]); //15	

	if(num1>num2 && num1>num3){
		System.out.println("Num 1 is greater");
	}
	else if (num2 >num1 && num2>num3)
	{
	System.out.println("Num2 is greater");
	}
	else
	{
	System.out.println("Num3 is greater");
	}
}
}