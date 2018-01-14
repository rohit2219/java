
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Sales {
		  private String customer_name;
		  private String product;
		  private int day;
		  private int month;
		  private int year;
		  private int quant;
		  private String state;
		
		  
		  private int max_q ;
		  private int min_q ;
		  private int avg_q;
		  private int sum; 
		  private int count; 
		  private String minState;
		  private String maxState;
		  private String minDate;
		  private String maxDate;
		  
		  public String getMinDate() {
			String min = getDate(minDate);
			return min ;
		}
		public void setMinDate(String month, String day, String year) {
			this. minDate = month + "/" +day + "/" + year;
		}
		public String getMaxDate() {
			String max = getDate(maxDate);
			return max;
		}
		public void setMaxDate(String month, String day, String year) {
			
			this.maxDate = month + "/" +day + "/" + year;
		}
		// getters for all fields 
		  public String getcustomer() {
		    return customer_name;
		  }
		  public int getMax_q() {
			return max_q;
		}
		public void setMax_q(int max_q) {
			this.max_q = max_q;
		}
		public int getMin_q() {
			return min_q;
		}
		public void setMin_q(int min_q) {
			this.min_q = min_q;
		}
		public int getAvg_q() {
			return avg_q;
		}
		public void setAvg_q(int avg_q) {
			
			this.avg_q = avg_q;
		}
		public int getSum() {
			return sum;
		}
		public void setSum(int sum) {
			this.sum = sum;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getMinState() {
			return minState;
		}
		public void setMinState(String minState) {
			this.minState = minState;
		}
		public String getMaxState() {
			return maxState;
		}
		public void setMaxState(String maxState) {
			this.maxState = maxState;
		}
		public String GetProduct() {
			  return product;
		  	}
		  public String GetState() {
			  return state;
		  	}
		  public int GetQuant() {
			  return quant;
		  	}
		  public int GetDay() {
			  return day;
		  	}
		  public int GetMonth() {
			  return month;
		  	}
		  public int GetYear() {
			  return year;
		  	}
		  public void setCustomer_name(String customer) {
			    this.customer_name = customer; }
		  public void setProduct(String prod) {
			    this.product = prod; }
		  public void setQuant(int qt) {
			    this.quant=qt; }
		  public void setDay(int d) {
			    this.day=d; }
		  public void setMonth(int m) {
			    this.month=m; }
		  public void setYear(int y) {
			    this.year=y; }
		  public void setState(String c) {
			    this.state=c; }
		  public String getDate(String dateString) {
			  SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyy");
			  String currentDate = new String();
			  try {
				  java.util.Date date = formatter.parse(dateString);
				  currentDate = formatter.format(date);
				  return currentDate;
			  }
			  catch (ParseException e){
				  e.printStackTrace();
				  return null;
			  }
				
			}
		
}  
			  
			 



