import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SalesTable {
	public static void main(String[] args)
	{	// Setting username, password, and a jdbc url to establish a connection to the database using getConnection
		String usr ="postgres";
		String pwd ="rohit";
		String url ="jdbc:postgresql://localhost:5432/postgres";

		try
		{
			Class.forName("org.postgresql.Driver");
			//System.out.println("Success loading Driver!");
		}

		catch(Exception e)
		{
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		try
		{ 	//getConnection() method is used to establish a connection to a database.
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			//System.out.println("Success connecting server!");
			//execute SQL statements
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sales");
			//Headers of the table
			System.out.printf("%-11s %-11s %-11s %-11s %-11s %-11s %-11s %-11s %-11s\n","Customer","Product","MAX_Q" ,"DATE","ST","MIN_Q","DATE", "ST", "AVG_Q" );
			String s = "===========";
			System.out.printf("%-8s %-7s %-6s %s %1s %1s %-6s %1s %1s \n",s,s,s,s,s,s,s,s,s);
			// Data structure to store result Table 
			Map<String,Sales> salesMap = new HashMap<String,Sales>(); 
			int  count = 1, sum= 0;  // Variables used to calculate average
			while (rs.next())
			{
				
				Sales currentSales = new Sales ();
				// Capturing the current cursor contents
				currentSales.setCustomer_name(rs.getString("cust"));
				currentSales.setProduct(rs.getString("prod"));
				currentSales.setState(rs.getString("state"));
				currentSales.setQuant(rs.getInt("quant"));
				int currentQuantity = currentSales.GetQuant();
				// Defining the Key for Hash map ie Key = Customer_name + Product_name
				String key = currentSales.getcustomer() + currentSales.GetProduct();
				// to check if the entry already exists in the HashMap 
				boolean check = salesMap.containsKey(key);
				if( check == true) // will return true if current Customer+Product combination exists in the HashMap
				{					
					Sales prevSales = salesMap.get(key); // Retrieve the previous record for respective Customer+Product combination
							
					if (currentQuantity > prevSales.getMax_q()){
							// Updating the Max value, corresponding State and date 
							prevSales.setMax_q(currentQuantity);
							prevSales.setMaxState(currentSales.GetState());
							prevSales.setMaxDate(rs.getString("month"),rs.getString("day"),rs.getString("year"));
							
					}
					if (currentQuantity < prevSales.getMin_q()){
							// Updating the Min value, corresponding State and date 
							prevSales.setMin_q(currentQuantity);
							prevSales.setMinState(currentSales.GetState());
							prevSales.setMinDate(rs.getString("month"),rs.getString("day"),rs.getString("year"));
							
					}
					// Populating the variables needed to calculate the Average of product quantity
					count= prevSales.getCount()+1;
					prevSales.setCount(count);
					sum = prevSales.getSum();
					sum=sum+currentQuantity;
					prevSales.setSum(sum);
					
				}
				else 
				{
					// Initializing the object variable for the first entry
					
					currentSales.setMax_q(currentQuantity); 
					currentSales.setMin_q(currentQuantity);
					currentSales.setSum(currentQuantity);
					currentSales.setMaxState(rs.getString("state"));
					currentSales.setMinState(rs.getString("state"));
					currentSales.setMinDate(rs.getString("month"),rs.getString("day"),rs.getString("year") );
					currentSales.setMaxDate(rs.getString("month"),rs.getString("day"),rs.getString("year") );
					count=1;
					currentSales.setCount(count);
					salesMap.put(key, currentSales); //Pushing the Customer + product and the class Object into HashMap
					
				}


			}
		
			for(Map.Entry<String, Sales> salesEntry: salesMap.entrySet() ) // Iterate over each entry in a Map 
			{
				Sales sales = salesEntry.getValue(); //Retrieve the value (Class object) assigned to the key 
				//Procedure to Calculate the average and round it to the nearest Integer value
				float decimalAvg = ((float)sales.getSum())/sales.getCount();
				int roundedAvg = Math.round(decimalAvg);
				sales.setAvg_q(roundedAvg);
				// Printing the Result Table
				System.out.printf( "%-11s %-11s %11d %-11s %-11s %11d %-11s %-11s %11d \n",sales.getcustomer(), sales.GetProduct(), sales.getMax_q(),
						sales.getMaxDate(), sales.getMaxState(),sales.getMin_q(), sales.getMinDate(), sales.getMinState(),  sales.getAvg_q());
			}
		}

		catch(SQLException e)
		{
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
