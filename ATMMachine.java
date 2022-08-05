
//BANK
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
class ATM 
{
  static double user_balance,user_balance2;
	static int user_account,user_account2;
	static String user_name2;
	
	//It will Firstly Opens Connection for postgresql Database Connectivity of particular Users Account
	public static Connection letConnect()
	
	{
		Connection con=null;
		String url,username,password;
		url="jdbc:postgresql://maduabuchi-db.cw51wm3v5bmw.us-east-1.rds.amazonaws.com:5432/";
		username="postgres";
		password="iLOVEMYLIFE2K22";
		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection(url,username,password);                    
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	
	//This will Fetch Particular data corresponding to the Users input 
	public static void FetchData()
	{
		 BufferedReader inn=new BufferedReader (new InputStreamReader(System.in));
		 Connection con=letConnect();

		try
		{
			System.out.println("***********WELCOME TO THE FIRST BANK OF BATMAN**************");
			System.out.println("\nEnter your 9-digit Batccount number");
			user_account=Integer.parseInt(inn.readLine());
			System.out.println("Please enter your Batpin");
          int user_PIN=Integer.parseInt(inn.readLine());
			String sql="select * from users where user_account='"+user_account+"' and user_PIN='"+user_PIN+"'";
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			if(rs.next())
			{
				System.out.println("\n**********LOGIN SUCCESSFUL**********");
				System.out.println("\nNAME: "+rs.getString(3)+"");
				System.out.println("CURRENT BALANCE :Rs."+rs.getDouble(4)+"");
				user_balance=rs.getDouble(4);
			}
			else
			{
				System.out.println("INVALID ACCOUNT OR PIN");
				System.exit(0);
			}
		    con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}
	
	//Throws Choice to the User for Required Transaction
	public static void User_Choice()throws IOException
	{
		
		BufferedReader in=new BufferedReader (new InputStreamReader(System.in));
		System.out.println("\nPLEASE CHOOSE YOUR DESIRED ACCOUNT");
      System.out.println("\n1.SAVINGS ACCOUNT");
		System.out.println("2.CURRENT ACCOUNT");
		System.out.print("\nENTER THE FULL NUMBER OF THE ACCOUNT YOU WANT TO OPEN: ");
		int i=Integer.parseInt(in.readLine());
			switch(i)
			{
				case 1:
                  String acctype1="Savings Account";
			        System.out.println("\nAccount Type: "+acctype1);
                  System.out.println("Balance: Rs."+user_balance);
					break;
			    case 2:
				    String acctype2="Current Account";
			        System.out.println("\nAccount Type: "+acctype2);
                  System.out.println("Balance: Rs."+user_balance);
					break;
			}
      System.out.println("\n1.DEPOSIT");
		System.out.println("2.WITHDRAW");
		System.out.println("3.TRANSFER");
		System.out.println("4.BALANCE ENQUIRY");
		System.out.println("5.EXIT");
		System.out.print("\nENTER THE NUMBER OF THE ACTION YOU WANT TO PERFORM: ");
      int ch=Integer.parseInt(in.readLine());
      switch(ch)
      {
          case 1:
              System.out.println("\nEnter the amount to Deposit ");
              double bal1=Double.parseDouble(in.readLine());
              user_balance=user_balance+bal1;
              System.out.println("\nNew Balance: Rs."+user_balance);
			    System.out.println("Your deposit was successful");
              break;
          case 2:
			    System.out.print("\nEnter the amount to Withdraw: Rs.");
              double bal2=Double.parseDouble(in.readLine());
              user_balance=user_balance-bal2;
              if(user_balance<=0)
                  System.out.println("Not Enough Money!");
              else
			    {
                  System.out.println("\nNEW BALANCE: Rs."+user_balance);
			        System.out.println("***Please collect your money***");
			    } 
              break;
			case 3:
			    System.out.print("\nEnter the amount to Transfer :Rs.");
				double amount=Double.parseDouble(in.readLine());
				System.out.print("\nEnter the Account Number to Transfer money :");
				user_account2=Integer.parseInt(in.readLine());
				if(user_balance<amount)
				{     
					System.out.println("Transfer Failed");
				}
				else
				{
					user_balance=user_balance-amount;
				    System.out.println("\nYOUR BALANCE LEFT: Rs."+user_balance);
				
				  //Database connectivity to the Receivers Account
					try
		            {
						Class.forName("org.postgresql.Driver");	
		                Connection con=DriverManager.getConnection("jdbc:postgresql://maduabuchi-db.cw51wm3v5bmw.us-east-1.rds.amazonaws.com:5432/", "postgres", "iLOVEMYLIFE2K22");	
						String sql="select * from transfers where user_account2='"+user_account2+"'";
						PreparedStatement st=con.prepareStatement(sql);
			            ResultSet rs=st.executeQuery();
			            if(rs.next())
			            {
				            System.out.println("Selected Account Owner's NAME: "+rs.getString(3)+"");
				            user_balance2=rs.getDouble(4);
						    user_name2=rs.getString(3);
			            }
			            else
			            {
				            System.out.println("INVALID ACCOUNT NUMBER");
				            System.exit(0);
			            }
					//Update Information of the Receiver's Account
				        user_balance2=user_balance2+amount;
		                String sql1="update transfers set user_balance2=? where user_account2=?";
                      PreparedStatement ps=con.prepareStatement(sql1);
		                ps.setDouble(1,user_balance2);
		                ps.setInt(2,user_account2);
		                int j=ps.executeUpdate();
		                if(j==1)
		                {
							System.out.println("Amount Rs."+amount+" is Successfully Transfered to account owner Name:"+user_name2);
		                }
		                con.close();	
					}
					catch(Exception e2)
					{
						System.out.println(e2);
					}
				}
				break;
			case 4:
			    System.out.println("Your Current Balance is Rs."+user_balance);
				break;
          case 5:
    			System.exit(0);
              break;
          default: 
			    System.out.println("INVALID CHOICE!");
      }
		
  }
	
	//This will Update corresponding Information of user in mysql Database
  public static void update_info()
	{
		try
		{
		Class.forName("org.postgresql.Driver");	
		Connection con=DriverManager.getConnection("jdbc:postgresql://maduabuchi-db.cw51wm3v5bmw.us-east-1.rds.amazonaws.com:5432/", "postgres", "iLOVEMYLIFE2K22");	
		String sql="update users set user_balance=? where user_account=?";
      PreparedStatement ps=con.prepareStatement(sql);
		ps.setDouble(1,user_balance);
		ps.setInt(2,user_account);
		int i=ps.executeUpdate();
		if(i==1)
		{
			System.out.println("\n***##RECORD UPDATED##***");
		}
		con.close();
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}
  }
	
	//Program execution starts from here
  public static void main(String args[])throws IOException
  {
		ATM.letConnect();
		ATM.FetchData();
		ATM.User_Choice();
		ATM.update_info();
      System.out.println("THANK YOU FOR BANKING WITH THE BAT  <^+^>");
  }
	
}