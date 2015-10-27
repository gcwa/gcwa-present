package ca.gc.collectionscanada.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import ca.gc.collectionscanada.repository.CrawlJobDetailDAO;

public class WritePathText {
	public static void main(String k[]) throws ClassNotFoundException, SQLException, IOException{
		List<String> mockList = CrawlJobDetailDAO.retrieveCrawlJobList(k[0]);		

		System.out.println("The size of a list is =" + mockList.size());
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(k[1])));
		for(String crawlJob : mockList) {

			File newFile = new File("/u/webarcdb01/"+crawlJob+"/arcs/path-index.txt");
		
			System.out.println("The actual file path = " + newFile.getPath());
			
			if(newFile.isFile()) {
				
				BufferedReader br = new BufferedReader(new FileReader(newFile.getPath()));
				
				String line;
				
				while ((line = br.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
				
				if(br!=null) {
					br.close();
				}				
			}
			
			else {
				System.out.println("This is not a file......");
			}
		//Got a list of file
	}
	
		if(writer != null) {
			writer.close();
			System.out.println("Buffered Writer closed completely");
		}
			
		
	}
}
