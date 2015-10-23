package ca.gc.collectionscanada;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is a utility class which will create a path-index.txt file to be used to configure wayback
 * This takes one argument i.e. /any_path/path-index.txt
 * The jar file usage is as follows
 * 
 * from the command prompt > java -jar WritePathText.jar "/any_path/path-index.txt" and enter
 * 
 * 
 * @author khatrz
 *
 */
public class WritePathTextFromWarcAndArcs {
	
	public static void main(String k[]) throws IOException {
		
		File output = new File(k[0]);
		
		FileOutputStream fos = new FileOutputStream(output);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
			for(int i = 1; i < 15; i++) {
			File f1 = new File("/u/webarch"+ String.format("%03d", i)+"/"+"heritrix");
			
			if(f1.isDirectory()){
				for(final File folder : f1.listFiles()) {

					if(folder.isDirectory()) {
						//System.out.println("Folder list size for " + folder.getPath() + " is = " + folder.listFiles().length);
						
						for (final File undertheme : folder.listFiles()) {
							if(undertheme.isDirectory()) {
								//System.out.println("Folder list size for " + undertheme.getPath() + " is = " + undertheme.listFiles().length);
								
								for(final File underCrawlJob : undertheme.listFiles()) {
									if(underCrawlJob.isDirectory() && underCrawlJob.getName().equalsIgnoreCase("arcs")) {
										//System.out.println("Folder list size for " + underCrawlJob.getPath() + " is = " + underCrawlJob.listFiles().length);
										
										for(final File arcFiles: underCrawlJob.listFiles()){
											if(validateFileExtn(arcFiles.getName())) {
												System.out.println("File name = " + arcFiles.getName());
												bw.write(arcFiles.getName() + "\t" + arcFiles.getPath());
												bw.newLine();
											}
										}
									}
								}								
							}
						}
					}
				}
			}
		}
			
			

		if(bw != null)

				bw.close();			
	}
	
	static Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(arc.gz|warc.gz))$)");
	
	public static boolean validateFileExtn(String fileName) {
		Matcher mtch = fileExtnPtrn.matcher(fileName);
		
		if(mtch.matches())		
		return true;
		
		return false;
	}
}