
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
        				System.out.println("here");
        				ArrayList<String> formatedEmail = new ArrayList<String>();
        				Scanner s = new Scanner(System.in);
        				String path = s.nextLine();
        				System.out.println(path);
        				s.close();
        				String fileName = "defauslt";
        				
        				try 
        				{
            				BufferedReader rawEmail = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\ben4t\\Desktop\\mailraw.txt"),"UTF-16"));
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
        					if(fileName.contains(":")||fileName.contains("\\"))
        					{
        						fileName.replace(":", " ");
        						fileName.replace("\\", " ");
        						System.out.println("contanes ileagal char");
        					}
        					System.out.println(fileName);
        					File f = new File("C:\\Users\\ben4t\\Desktop\\"+fileName+".txt");
        					// will need to verify directorry latter
        					f.createNewFile();
							BufferedWriter formatedEmailWriter = new BufferedWriter(new FileWriter("C:\\Users\\ben4t\\Desktop\\"+fileName+".txt"));
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