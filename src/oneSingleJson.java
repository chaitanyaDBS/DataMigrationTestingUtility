import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class oneSingleJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
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
		Gson gs = new Gson();
		String jsonString = gs.toJson(al.get(i));
		ja.add(jsonString);
		}
		
		jo.put("data", ja);
		System.out.println(jo.toJSONString());
		String modifiedJsonString = StringEscapeUtils.unescapeJava(jo.toJSONString());
		String stringNew = modifiedJsonString.replace("\"{", "{");
		String finalString = stringNew.replace("}\"", "}");
		System.out.println(finalString);	
		
		try (FileWriter file = new FileWriter("C:\\EclipseWorkspaces\\csse1202\\JsonJava\\SingleJson.json"))
		{
		file.write(finalString);
		}
		conn.close();
	}	

}
