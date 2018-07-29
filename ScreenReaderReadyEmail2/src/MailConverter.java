
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class MailConverter 
{

    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater
        (
        		new Runnable() 
        		{
        			public void run() 
        			{
        				
        				ArrayList<String> formatedEmail = new ArrayList<String>();
        				Scanner s = new Scanner(System.in);
        				String path = s.nextLine();
        				System.out.println(path);
        				
        				try 
        				{
            				BufferedReader rawEmail = new BufferedReader(new FileReader(path));
        				    String line = rawEmail.readLine();
        				    s.close();
        				    while (line != null)
        				    {
        				    	String[] lineArray = line.split(" ");
        				    	for(int i = 0; i < lineArray.length; i++)
        				    	{
        				    		formatedEmail.add(lineArray[i]);
        				    	}
        				    	
        				    }
        				    rawEmail.close();
        				}
        				catch(IOException e)
        				{
        					System.out.println(e.getMessage());
        				}
        				
        				String eMail = "";
        				removeHeader(formatedEmail);
        				for(int i = 0; i < formatedEmail.size(); i++) 
        				{
							
						}
        				
        				try 
        				{
							BufferedWriter formatedEmailWriter = new BufferedWriter(new FileWriter("ScreenReaderReadyEmail"));
							formatedEmailWriter.write(eMail);
							
							formatedEmailWriter.close();
						} 
        				catch (IOException e) 
        				{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				
        			}
        			
        			/*checks if @param word is a url*/
        			public boolean isUrl(String word)
        			{
        				if(word.contains("https:////"))
        				{
        					return true;
        				}
        				return false;
        			}
        			
        			/*@returns a string which contains a description of the url @param url */
        			public void processUrl(String url)
        			{
        				
        			}
        			
        			/*identifies and removes the email header and replaces it with only who it is from*/
        			public void removeHeader(ArrayList<String> email)
        			{
        				
        			}
        		}
        );
    }
}