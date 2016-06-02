
//	Methods to read and open file
//	extract each line and put into String array

import java.io.*;

public class ReadFromFile 
{
//  variable to hold the file name
    private String theFile;
    
    //  constructor to grab the file name
    
    public ReadFromFile(String file)
    {
        //  theFile will be the variable used in the class methods
        theFile = file;
    }	//	end ReadFromFile()
    
    public String[] OpenTheFile() throws IOException
    {
        //  method opens the file, reads the lines and stores in an array
        //  returns file text data
        FileReader f = new FileReader(theFile);
        BufferedReader t = new BufferedReader(f);
        
        //  variable to hold number of lines for the reading loop
        //  string array to hold all the lines in the document
        int lineCount = countLines();
        String[] fileData = new String[lineCount];
        
        //  loop through the document 
        //  return each line to the array and store
        
        for(int x = 0 ; x < lineCount ; x++)
        {
            fileData[x] = t.readLine();
        }
        
        t.close();
        return fileData;
        
    }	//	end OpenTheFile()
    
    int countLines() throws IOException
    {
        //  returns the number of lines in a file to be used when extracting the Strings
        
        FileReader f = new FileReader(theFile);
        BufferedReader b = new BufferedReader(f);
        String line;
        int lineCount = 0;
        
        //  loop through the file and if the line read returns a value then incriment the counter
        
        while((line = b.readLine()) != null)
        {
            lineCount++;
            //System.out.println(line);
        }
        
        //  close the file
        b.close();
        
        return lineCount;
        
    }	//	end countLines()
    
}	//	end ReadFromFile Class
