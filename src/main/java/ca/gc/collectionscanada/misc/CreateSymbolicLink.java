package ca.gc.collectionscanada.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import ca.gc.collectionscanada.repository.CrawlJobDetailDAO;

/**
 * parameter k[0] = theme number and k[1] is the directory where the shortcuts will be placed
 * so usage for this jar will be
 * java -jar jar_name "5" "/tmp/any_directory"
 * you will need ojdbc-14.jar for database access
 * @param k
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws IOException
 */
public class CreateSymbolicLink {

	public static void main (String k []) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
		List<String> mockList = CrawlJobDetailDAO.retrieveCrawlJobList(k[0]);
		
		System.out.println("The size of a list is =" + mockList.size());
		
		for(String crawlJob : mockList) {
			
			File newFile = new File("/u/webarcdb01/"+crawlJob+"/arcs/path-index.txt");
			
			System.out.println("The file path = " + newFile.getPath());
			
			if(newFile.isFile()) {
				
		
				BufferedReader br = new BufferedReader(new FileReader(newFile.getPath()));
				
				String line;
				
				while ((line = br.readLine()) != null) {

					String[] tabArray = line.split("\t");
					String fileName = tabArray[1].toString();

					String command = "ln -s " + fileName + " ./"+ new File(fileName).getName();

					Process p = Runtime.getRuntime().exec(command);
					InputStreamReader input = new InputStreamReader(p.getInputStream());
					BufferedReader reader  = new BufferedReader(input);
					String lines = reader.readLine();
					int rc = p.waitFor();
					reader.close();
					input.close();
					
					System.out.println("Symbolic link created for file = " + fileName);
				}
				
				if(br != null) {
					br.close();
				}
				
			}
			
			else {
				System.out.println("There is no file....");
			}			
		}
	}
}
