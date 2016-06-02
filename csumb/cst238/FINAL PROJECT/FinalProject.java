/*
 * PROGRAMMER: VANESSA ULLOA
 * CST 436: INTRO TO DATA STRUCTURES
 * Java Program that will:
 * 	1. accept a text file
 * 	2. display all the letters and list frequency
 * 	3. list all words alphabetically and frequency
 * 	4. average number of letters in a word
 * 	5. average number of words in a sentence
 * 
 */

import java.util.*;
import java.io.*;

public class FinalProject 
{

	public static void main(String[] args) throws IOException
	{
		//  read from the file and check if file exists, if not then return an error. 
        
        Scanner read = new Scanner(System.in);
        
        //  user enters full file name to read
        System.out.println("Please enter a File to read:\t");
        String theFile = read.next();
        //String theFile = "C:\\Users\\Vanessa\\Desktop\\statement.txt";
        
        //  catch the exception created
        
        try
        {
            ReadFromFile RtheFile = new ReadFromFile(theFile);       
            String[] lines = RtheFile.OpenTheFile();
            CountResults(lines);
        }
        catch(Exception e)
        {
          System.out.println("\n\n");
          System.out.println("Error! " + theFile+ " does not exist");
          System.out.println("Error Message: " + e.getMessage());
        }
        
        read.close();
        
	}	//	end main()
	
	
	public static void CountResults(String[] lines)
	{
		//	count all the leters
				
		//	variables
		int wordcount = 0;
		int sentence = 0;
		int charCount = 0;
		String allTheWords = " ";
		
		//	arrays - Alphabet Array will not be used, only used to copy
		char[] Alphabet = { 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'	,'U','V','W','X','Y','Z' };
		char[] AlphabetCopy = new char[Alphabet.length];
		
		System.arraycopy(Alphabet, 0, AlphabetCopy, 0, Alphabet.length);
		int[] AlphabetCount = new int[Alphabet.length];
		
		//	to count the words, count all the " " spaces plus 1
		//String[] AllWords = lines[0].split(" ");
		
		//	concatenate all lines into one string
		for(int i = 0 ; i < lines.length ; i++)
		{
			allTheWords += lines[i];
		}
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//	split the string based on space deliminator
		String[] AllWords = allTheWords.split(" ");
		wordcount = AllWords.length;	//	count of all the words in the document
		
		//	count the characters and count the sentences
		for(int x = 0 ; x < AllWords.length ; x++)
		{
			//	counts the length of each word and
			charCount += AllWords[x].length();
			
			if(AllWords[x].indexOf('.') > 0 || AllWords[x].indexOf('!') > 0 || AllWords[x].indexOf('?') > 0)
			{
				sentence++;
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//	count the letters
			//	convert word to uppercase
			//	then loop through each char in the word
			//	compare char to Alphabet
			//	then increment int Alphabet array
		
		for(int a = 0 ; a < AllWords.length ; a++)
		{
			String word = AllWords[a];
			String uWord = word.toUpperCase();
			//	loop through the word
			for(int b = 0 ; b < uWord.length(); b++)
			{
				char letter = uWord.charAt(b);
				//	loop through the Alphabet to compare against select char in 2nd loop
				for(int c = 0 ; c < Alphabet.length ; c++)
				{
					char Alpha = Alphabet[c];
					if( letter == Alpha)
					{
						AlphabetCount[c]++;
					}
				}
			}
		}	//	end first loop
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//	copy the AlphabetCount array so it can be sorted
		
		int[] AlphabetCountCopy = new int[AlphabetCount.length];
		System.arraycopy(AlphabetCount,0,AlphabetCountCopy,0,AlphabetCount.length);
		
		boolean flag = true;
		int temp;
		char temp2;
		
		while(flag)
		{
			flag = false;
			for(int x = 0 ; x < AlphabetCountCopy.length - 1 ; x++)
			{
				if(AlphabetCountCopy[x] < AlphabetCountCopy[x + 1])
				{
					temp = AlphabetCountCopy[x];
					AlphabetCountCopy[x] = AlphabetCountCopy[x + 1];
					AlphabetCountCopy[x + 1] = temp;
					
					temp2 = AlphabetCopy[x];
					AlphabetCopy[x] = AlphabetCopy[x + 1];
					AlphabetCopy[x + 1] = temp2;
					
					flag = true;
					
				}
			}
		}	//	end while()
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//	sort the words
		//	remove all the puncutation from the strings
		//System.out.println(Arrays.toString(AllWords));
		for(int x = 0 ; x < AllWords.length ; x++)
		{
			AllWords[x] = AllWords[x].replaceAll("[^A-Za-z0-9]","");
		}
		
		String[] SortedWords = new String[AllWords.length];
		System.arraycopy(AllWords,0,SortedWords,0,AllWords.length);
		
		//	SortedWords Array will be used to hold all the words without duplicates for counting
		
		int duplicate = 0;
		int times = 0;
		for(int x = 0 ; x < AllWords.length ; x++)
		{
			times = 0;
			for(int y = 0 ; y < SortedWords.length ; y++)
			{
				if(AllWords[x].equals(SortedWords[y]))
				{
					times++;
					if(times > 1)
					{
						SortedWords[y] = SortedWords[y].replace(AllWords[x], "");
						duplicate++;	//	int variable to know what to reducate array size by
					}
				}
			}
		}	//	end for loop
		
		//	final string array to have an array of non-duplicate Strings
		
		String[] theWords = new String[AllWords.length - (duplicate+1)];
		int index = 0;
		for( int x = 0 ; x < SortedWords.length ; x++)
		{
			if(!SortedWords[x].isEmpty())
			{
				theWords[index] = SortedWords[x];
				index++;
			}
		}
		//System.out.println(Arrays.toString(theWords));
		
		//	use theWords Array to keep count of words, make a int Array to keep track
		//	loop through theWords against AllWords and count how many times each appears
		int[] theWordsCount = new int[theWords.length];
		
		for(int x = 0 ; x < theWords.length ; x++)
		{
			for(int y = 0 ; y < AllWords.length ; y++)
			{ 
				//System.out.println("theWords[" + x + "] " + theWords[x] + " EQUALS AllWords[" + y + "] " + AllWords[y]);
				if(theWords[x].equals(AllWords[y]))
				{
					theWordsCount[x]++;
				}
			}
		}	//	end for loop
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//	display the information in seperate method
		/*
		 * pass variables sentence, AllWords, charCount, AlphabetCopy, AlphabetCountCopy
		 * 
		 */
		
		DisplayResults(sentence,charCount,wordcount,AllWords,theWords,theWordsCount,AlphabetCopy,AlphabetCountCopy);
	
	}	//	end CountChars()
	
	public static void DisplayResults(
			int sentence, int charCount, int wordcount, String[] AllWords, String[] theWords, int[] theWordsCount ,char[] AlphabetCopy, int[] AlphabetCopyCount
	)
	{
		/*
		 * display all the results:
		 * 1. all leters and frequency
		 * 2. all words and frequency
		 * 3. average number of letters
		 * 4. average number of words
		 * 
		 */
		
		//	print letter frequence
		System.out.println("\nLetter Frequency: ");
		for(int i = 0 ; i < AlphabetCopy.length ; i++)
		{
			System.out.println("\t" + AlphabetCopy[i] + "\t" + AlphabetCopyCount[i]);
		}
		
		//	print word frequency
		System.out.println("\nWord Frequency: ");
		Arrays.sort(theWords);
		for(int i = 0 ; i < theWords.length ; i++)
		{
			System.out.println("\t" + theWords[i] + " " + theWordsCount[i]);
		}
		
		System.out.println("\nFINAL PROJECT RESULTS: ");
		System.out.println("\tHow Many Sentences" + sentence);
		System.out.println("\tTotal Words: " + AllWords.length);
		System.out.println("\tTotal Characters: " + charCount);
		System.out.println("\tAverage number of letters: " + (charCount/AllWords.length));
		System.out.println("\tAverage number of words: " + (AllWords.length/sentence));
		
	}
		
		
}	//	end FinalProject Class

