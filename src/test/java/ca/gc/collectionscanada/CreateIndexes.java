package ca.gc.collectionscanada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import ca.gc.collectionscanada.common.dao.CrawlJobDetailDAO;

public class CreateIndexes {
	public static void main (String k []) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		
		List<String> mockList = CrawlJobDetailDAO.retrieveCrawlJobList(k[0]);
		
		System.out.println("The size of a list is =" + mockList.size());
		int counter = 0;
		
		for(String crawlJobs: mockList) {
			
			
			File newFile = new File("/u/webarcdb01/"+crawlJobs+"/arcs/path-index.txt");
			
			System.out.println("The file path = " + newFile.getPath());
			
			if(newFile.isFile()) {
				
		
				BufferedReader br = new BufferedReader(new FileReader(newFile.getPath()));
				
				String line;
				
				while ((line = br.readLine()) != null) {
					counter++;
					String[] tabArray = line.split("\t");
					String fileName = tabArray[1].toString();

					String command1 = "./cdx-indexer " + fileName + " index"+counter+".cdx;";

					Process p1 = Runtime.getRuntime().exec(command1);
					InputStreamReader isr1 = new InputStreamReader(p1.getInputStream());
					BufferedReader br1  = new BufferedReader(isr1);
					String lines1 = br1.readLine();
					int rc1 = p1.waitFor();
					br1.close();
					isr1.close();
					
					System.out.println("CDX Index created for file = " + fileName);
					
					String command2 = "chmod -R 0755 index"+counter+".cdx;";
					
					Process p2 = Runtime.getRuntime().exec(command2);
					InputStreamReader isr2 = new InputStreamReader(p2.getInputStream());
					BufferedReader br2  = new BufferedReader(isr2);
					String lines2 = br2.readLine();
					int rc2 = p2.waitFor();
					br2.close();
					isr2.close();
				}
				
				if(br != null) {
					br.close();
				}
				
			}
			
			else {
				System.out.println("There is no file....");
			}			
		}
		
		
		String command3 = "LC_ALL=C sort -m -u -o all.cdx index*.cdx;";
		
		Process p3 = Runtime.getRuntime().exec(command3);
		InputStreamReader isr3 = new InputStreamReader(p3.getInputStream());
		BufferedReader br3  = new BufferedReader(isr3);
		String lines3 = br3.readLine();
		int rc3 = p3.waitFor();
		br3.close();
		isr3.close();
		
		System.out.println("Command 3 completed");
		
		String command4 = "rm -rf index*.cdx;";
		
		Process p4 = Runtime.getRuntime().exec(command4);
		InputStreamReader isr4 = new InputStreamReader(p4.getInputStream());
		BufferedReader br4  = new BufferedReader(isr4);
		String lines4 = br4.readLine();
		int rc4 = p4.waitFor();
		br4.close();
		isr4.close();
		
		System.out.println("Command 4 completed");
		
		String command5 = "LC_ALL=C sort -u all.cdx > " + k[1]+";";
		
		Process p5 = Runtime.getRuntime().exec(command5);
		InputStreamReader isr5 = new InputStreamReader(p5.getInputStream());
		BufferedReader br5  = new BufferedReader(isr5);
		String lines5 = br5.readLine();
		int rc5 = p5.waitFor();
		br5.close();
		isr5.close();
		
		System.out.println("Command 5 completed");
		
		String command6 = "rm -rf all.cdx;";
		
		Process p6 = Runtime.getRuntime().exec(command6);
		InputStreamReader isr6 = new InputStreamReader(p6.getInputStream());
		BufferedReader br6  = new BufferedReader(isr6);
		String lines6 = br6.readLine();
		int rc6 = p6.waitFor();
		br6.close();
		isr6.close();
		
		System.out.println("Command 6 completed");
		
		String command7 = "chmod -R 755 "+k[1]+";";
		
		Process p7 = Runtime.getRuntime().exec(command7);
		InputStreamReader isr7 = new InputStreamReader(p7.getInputStream());
		BufferedReader br7  = new BufferedReader(isr7);
		String lines7 = br7.readLine();
		int rc7 = p7.waitFor();
		br7.close();
		isr7.close();		
		
		System.out.println("Command 7 completed");
	}	
}
