import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class jsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
		
		while(rs.next())
		{
			CustomerDetails c = new CustomerDetails();	
			c.setCourseName(rs.getString(1));
			c.setPurchasedDate(rs.getString(2));
			c.setAmount(rs.getInt(3));
			c.setLocation(rs.getString(4));
			al.add(c);		
		}
		for(int i=0;i<al.size();i++)
		{
		int j=i+1;	
		ObjectMapper op = new ObjectMapper();
		op.writeValue(new File("C:\\EclipseWorkspaces\\csse1202\\JsonJava\\CustomerInfo"+j+".json"),al.get(i));
		}
		
		conn.close();
	}

}
