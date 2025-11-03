package framework.dataset;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonData(String FilePath) throws IOException
	{

		//1. Read Jason to string
		String jsonContent = FileUtils.readFileToString(
			    new File(FilePath),StandardCharsets.UTF_8);


		//2. Convert string to HashMAp--> for this we need jackson databind dependancy. Add this depandency in pom.xml file

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		}
	//NEW COmment Added ---test
	//Another New comment added ---


}
