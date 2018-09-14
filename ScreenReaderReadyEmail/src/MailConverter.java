/*next implement looking in folder and operating on all files there
 * jemail to get sender and signing the email
 * jemail to get and format all new emails
 * removing auto signatures with emails phone numbers fax numbers ect*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class MailConverter 
{

    public static void main(final String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater
        (
        		new Runnable() 
        		{
        			public void run() 
        			{
        				ArrayList<String> formatedEmail = new ArrayList<String>();
        				String fileLocation = args[0]; //file location of the raw email and output file
        				String email = args[1]; // name of raw email
        				System.out.println(fileLocation+email); // manual check the args
        				String fileName = "default";
        				String  path = fileLocation+email;
        				try 
        				{																						
            				BufferedReader rawEmail = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-16"));
        				    String line = rawEmail.readLine();
        				    fileName = line;
        				    line = rawEmail.readLine();
        				    //cut email up in to array list of words
        				    while (line != null)
        				    {
        				    	String[] lineArray = line.split(" ");
        				    	//System.out.println(lineArray[0]);
        				    	for(int i = 0; i < lineArray.length; i++)
        				    	{
        				    		formatedEmail.add(lineArray[i]+" ");
        				    		System.out.println(lineArray[i]);
        				    	}
        				    	line = rawEmail.readLine();
        				    }
        				    rawEmail.close();
        				}
        				catch(IOException e)
        				{
        					System.out.println(e.getMessage());
        				}
        				
        				String eMail = "";
        				removeHeader(formatedEmail);
        				//find and replace urls
        				for(int i = 0; i < formatedEmail.size(); i++) 
        				{
							if(isUrl(formatedEmail.get(i)))
							{
								formatedEmail.set(i, processUrl(formatedEmail.get(i)));
							}
							eMail+=formatedEmail.get(i);
						}
        				
        				try 
        				{
        					System.out.println("file location is "+fileLocation);
        					System.out.println(fileName);
        					File f = new File(fileLocation+fileName+".txt");
        					// will need to verify directory latter
        					f.createNewFile();
        					// write new file to same location as raw
							BufferedWriter formatedEmailWriter = new BufferedWriter(new FileWriter(fileLocation+fileName+".txt"));
							formatedEmailWriter.write(eMail);
							
							formatedEmailWriter.close();
						} 
        				catch (IOException e) 
        				{
							e.printStackTrace();
						}
        				
        			}
        			
        			/*checks if @param word is a url*/
        			public boolean isUrl(String word)
        			{
        				if(word.contains("https://"))
        				{
        					return true;
        				}
        				return false;
        			}
        			
        			/*@returns a string which contains a description of the url @param url */
        			public String processUrl(String url)
        			{
        				String[] urlParts = url.split("/");
        				String UrlExplanation = "Link to, "+urlParts[2];
        				return UrlExplanation;
        			}
        			
        			/*identifies and removes the email header and replaces it with only who it is from*/
        			public void removeHeader(ArrayList<String> email)
        			{
        				
        			}
        		}
        );
    }
}