package ca.gc.collectionscanada.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

public class CopyAllWarcFilesCrawlJobRange {
	
	public static void main (String k []) throws ClassNotFoundException, SQLException, IOException {

		String argument = k[0];
		String array [] = argument.split(",");
		
		System.out.println("The size of a list is =" + array.length);
		
		for(String crawlJob : array) {
			
			File newFile = new File("/u/webarcdb01/"+crawlJob+"/arcs/path-index.txt");
			
			System.out.println("The absolute path file = " + newFile.getPath());
			
			if(newFile.isFile()) {
				//System.out.println(newFile.getName());
				
				BufferedReader br = new BufferedReader(new FileReader(newFile.getPath()));
				
				String line;
				
				while ((line = br.readLine()) != null) {
					// System.out.println("Content in file = " +
					// newFile.getAbsolutePath() + " " + line);

					String[] tabArray = line.split("\t");

					System.out.println("File Name  = " + tabArray[1].toString());

					FileChannel inputChannel = null;
					FileChannel outputChannel = null;

					try {
						File inputFile = new File(tabArray[1].toString());
						File outputFile = new File(k[1] + "/" + new File(tabArray[1].toString()).getName());
						
						if (inputFile != null && outputFile != null) {
							inputChannel = new FileInputStream(inputFile).getChannel();
							outputChannel = new FileOutputStream(outputFile).getChannel();
							outputChannel.transferFrom(inputChannel, 0,	inputChannel.size());
						}
					} finally {
						if(inputChannel != null) {
							inputChannel.close();
						}
						if(outputChannel != null) {
							outputChannel.close();
						}
					}
				}
			}
			
			else {
				System.out.println("This is not a file......");
			}
			
		}
	}

}
