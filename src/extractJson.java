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


public class extractJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper op = new ObjectMapper();
		CustomerDetails cd = op.readValue(new File("C:\\EclipseWorkspaces\\csse1202\\JsonJava\\CustomerInfo1.json"),CustomerDetails.class);
		System.out.println(cd.getCourseName());
		
		
	}

}
