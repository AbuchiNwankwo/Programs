public class SwitchDemo{
    public static void main(String []args){
    int day=Integer.parseInt(args[0]);
    switch(day){
    case 1:
        System.out.println("Monday");
        break; // break is used to come out of the loop or condition
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break; 
    case 4:
        System.out.println("Thursday");
        break;
    case 5:
        System.out.println("Friday");
        break;
    case 6:
        System.out.println("Saturday");
            break;
    default:
        System.out.println("Holiday");
        break;
}
}
}
