
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
/**
 *
 * @author David Bickford
 *  Email: drb56@pitt.edu
 *  Project 1 for CS 1501 Spring 2016
 */
public class pw_check {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        String password = " ";
//        char[] pwd;
	String dictionaryString;
        String validPWString;
//	StringBuilder sb = new StringBuilder("");
//        int letterCount = 0;
//        int numberCount = 0;
//        int symbolCount = 0;
        char[] validChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
                            'u', 'v', 'w', 'x', 'y', 'z', '0' , '1', '2', '3', '4',
                            '5', '6', '7', '8', '9', '!', '@', '$', '^', '_', '*'};
        //"7" for "t", "4" for "a", "0" for "o", "3" for "e", "1" for "i", "1" for "l", or "$" for "s"
//        char[][] substitutions = new char[][]{{'t', 'a', 'o', 'e', 'i', 'l', 's'},
//                                              {'7', '4', '0', '3', '1', '1', '$'}};
//        int[] specialLoc = {0, 0, 0, 0, 0};
//        int dlbAdds = 0;
        DLB dictionary;
        DLB acceptedPW;
        Scanner reader = new Scanner(System.in);
        
        if(args[0].equals("-g"))
        {
            //setting a new scanner to dictionary and the board input
            Scanner dictionaryScan = new Scanner(new FileInputStream("dictionary.txt"));
            //creating a new DLB object called dictionary which holds the dictionary
            dictionary = new DLB();
            PrintWriter myDictionary = new PrintWriter("my_dictionary.txt");

            while(dictionaryScan.hasNext())
            {
                dictionaryString = dictionaryScan.nextLine();
                dictionary.add(dictionaryString);
                char[] dictChar = dictionaryString.toCharArray();
                myDictionary.println(dictionaryString);

                for(int i=0; i<dictChar.length; i++)
                {
                    if(dictChar[i] != substitution(dictChar[i]))
                    {
                        dictChar[i] = substitution(dictChar[i]);
                        String output = new String(dictChar);
                        myDictionary.println(output);
                    }

                }
            }
            myDictionary.close();

            //create valid passwords:
            //print writer
            PrintWriter file = new PrintWriter("good_passwords.txt");

            StringBuilder newpw = new StringBuilder();
            for(int j=0; j<validChars.length; j++)
            {
                newpw.append(validChars[j]);
                if(!dictionary.search(newpw))
                {
                    for(int k=0; k<validChars.length; k++)
                    {
                        newpw.append(validChars[k]);
                        if(!dictionary.search(newpw))
                        {
                            for(int l=0; l<validChars.length; l++)
                            {
                                newpw.append(validChars[l]);
                                if(!dictionary.search(newpw))
                                {
                                    for(int t=0; t<validChars.length; t++)
                                    {
                                        newpw.append(validChars[j]);
                                        if(!dictionary.search(newpw))
                                        {
                                            for(int p=0; p<validChars.length; p++)
                                            {
                                                newpw.append(validChars[p]);
                                                if(!dictionary.search(newpw));
                                                {
                                                    if(legalPassword(newpw.toString()))
                                                    {
                                                        file.println(newpw.toString());
                                                    }
                                                }
                                                newpw.deleteCharAt(4);
                                            }
                                        }
                                        newpw.deleteCharAt(3);
                                    }
                                }
                                newpw.deleteCharAt(2);
                            }
                        }
                        newpw.deleteCharAt(1);
                    }
                }
                newpw.deleteCharAt(0);
            }

            //closing the data stream.
            file.close();

            Scanner acceptedPWScan = new Scanner(new FileInputStream("good_passwords.txt"));
            //creating a new DLB object called dictionary which holds the dictionary
            acceptedPW = new DLB();

            while(acceptedPWScan.hasNext())
            {
                validPWString = acceptedPWScan.nextLine();
                acceptedPW.add(validPWString);
            }
        }
        else
        {
            String goodPWString;
            String[] goodPasswords = new String[10];
            int pwCount = 0;
            int[] ten = new int[10];
            //enforces the character count, letter, numbers, and symbol count
            while(true)
            {
                do{
                    System.out.println("Enter a password or type 'quit' to exit: ");
                    password = reader.next(); 
                    password = password.toLowerCase();
                    if(password.equals("quit"))
                    {
                        System.out.println("Thanks for using the password tester!");
                        System.exit(0);
                    }

                    if(password.length() != 5)
                    {
                        System.out.println("I'm sorry, password must be exactly 5 characters long.");
                    }
                    else
                    {
                        if(!legalPassword(password))
                        {
                            System.out.println("I'm sorry, that is not a legal password.\n"
                                    + "Password must have 1-3 letters, 1-2 numbers, and 1-2 symbols.");
                            Random rand = new Random();
                            int randomNum;
                            ten[0] = rand.nextInt((10000 - 0) + 1);
                            ten[1] = rand.nextInt((10000 - 0) + 1);
                            ten[2] = rand.nextInt((10000 - 0) + 1);
                            ten[3] = rand.nextInt((10000 - 0) + 1);
                            ten[4] = rand.nextInt((10000 - 0) + 1);
                            ten[5] = rand.nextInt((10000 - 0) + 1);
                            ten[6] = rand.nextInt((10000 - 0) + 1);
                            ten[7] = rand.nextInt((10000 - 0) + 1);
                            ten[8] = rand.nextInt((10000 - 0) + 1);
                            ten[9] = rand.nextInt((10000 - 0) + 1);
                            Scanner goodPws = new Scanner(new FileInputStream("good_passwords.txt"));

                            while(goodPws.hasNext())
                            {
                                goodPWString = goodPws.nextLine();
                                for(int i=0; i<ten.length; i++)
                                {
                                    if(pwCount == ten[i])
                                    {
                                        goodPasswords[i] = goodPWString;
                                    }
                                }
                                pwCount++;
                            }
                            System.out.println("Here are a list of good passwords:");
                            for(int i=0; i<goodPasswords.length; i++)
                            {
                                System.out.println(goodPasswords[i]);
                            }
                        }
                        else
                        {
                            System.out.println("Great password choice! Very secure!");
                        }
                    }
                }while(!legalPassword(password));
            }
        }
    }
    
    
    public static int countSpecialChars(String input)
    {
        int charCount = 0;
        char[] charArray = input.toCharArray();
        
        for(int i=0; i<charArray.length; i++)
        {
            if(charArray[i] == 'a')
            {
                charCount++;
            }
            else if(charArray[i] == 't')
            {
                charCount++;
            }
            else if(charArray[i] == 'o')
            {
                charCount++;
            }
            else if(charArray[i] == 'e')
            {
                charCount++;
            }
            else if(charArray[i] == 'i' || charArray[i] == 'l')
            {
                charCount++;
            }
            else if(charArray[i] == 's')
            {
                charCount++;
            }
        }
        
        return charCount;
    }
    
    public static char substitution(char currChar)
    {
        //"7" for "t", "4" for "a", "0" for "o", "3" for "e", "1" for "i", "1" for "l", or "$" for "s"
        if(currChar == 'a')
        {
            return '4';
        }
        else if(currChar == 't')
        {
            return '7';
        }
        else if(currChar == 'o')
        {
            return '0';
        }
        else if(currChar == 'e')
        {
            return '3';
        }
        else if(currChar == 'i' || currChar == 'l')
        {
            return '1';
        }
        else if(currChar == 's')
        {
            return '$';
        }
        return currChar;
    }
    
    public static boolean legalPassword(String password)
    {
        boolean returnVal = false;
        char[] pwd = password.toCharArray();
        int letters = 0;
        int numbers = 0;
        int symbols = 0;
        int pwLen = 0;
        for(int i=0; i<pwd.length; i++)
        {
            if(isLetter(pwd[i]))
            {
                letters++;
            }
            else if(isNumber(pwd[i]))
            {
                numbers++;
            }
            else if(isSymbol(pwd[i]))
            {
                symbols++;
            }
            pwLen++;
        }
        
        if(letters >= 1 && letters <= 3 && numbers >= 1 && numbers <= 2 && 
                symbols >= 1 && symbols <= 2 && pwLen == 5)
        {
            returnVal = true;
        }
        
        return returnVal;
    }
    
    public static boolean isSymbol(char input)
    {
        boolean returnVal = false;
        
        if(input == '!')
        {
            returnVal = true;
        }
        else if(input == '@')
        {
            returnVal = true;
        }
        else if(input == '$')
        {
            returnVal = true;
        }
        else if(input == '^')
        {
            returnVal = true;
        }
        else if(input == '_')
        {
            returnVal = true;
        }
        else if(input == '*')
        {
            returnVal = true;
        }
        
        return returnVal;
    }
    
    public static boolean isNumber(char input)
    {
        boolean returnVal = false;
        
        if(input == '0')
        {
            returnVal = true;
        }
        else if(input == '1')
        {
            returnVal = true;
        }
        else if(input == '2')
        {
            returnVal = true;
        }
        else if(input == '3')
        {
            returnVal = true;
        }
        else if(input == '4')
        {
            returnVal = true;
        }
        else if(input == '5')
        {
            returnVal = true;
        }
        else if(input == '6')
        {
            returnVal = true;
        }
        else if(input == '7')
        {
            returnVal = true;
        }
        else if(input == '8')
        {
            returnVal = true;
        }
        else if(input == '9')
        {
            returnVal = true;
        }
        
        return returnVal;
    }
    
    public static boolean isLetter(char input)
    {
        boolean returnVal = false;
        
        if(input == 'a')
        {
            returnVal = true;
        }
        else if(input == 'b')
        {
            returnVal = true;
        }
        else if(input == 'c')
        {
            returnVal = true;
        }
        else if(input == 'd')
        {
            returnVal = true;
        }
        else if(input == 'e')
        {
            returnVal = true;
        }
        else if(input == 'f')
        {
            returnVal = true;
        }
        else if(input == 'g')
        {
            returnVal = true;
        }
        else if(input == 'h')
        {
            returnVal = true;
        }
        else if(input == 'i')
        {
            returnVal = true;
        }
        else if(input == 'j')
        {
            returnVal = true;
        }
        else if(input == 'k')
        {
            returnVal = true;
        }
        else if(input == 'l')
        {
            returnVal = true;
        }
        else if(input == 'm')
        {
            returnVal = true;
        }
        else if(input == 'n')
        {
            returnVal = true;
        }
        else if(input == 'o')
        {
            returnVal = true;
        }
        else if(input == 'p')
        {
            returnVal = true;
        }
        else if(input == 'q')
        {
            returnVal = true;
        }
        else if(input == 'r')
        {
            returnVal = true;
        }
        else if(input == 's')
        {
            returnVal = true;
        }
        else if(input == 't')
        {
            returnVal = true;
        }
        else if(input == 'u')
        {
            returnVal = true;
        }
        else if(input == 'v')
        {
            returnVal = true;
        }
        else if(input == 'w')
        {
            returnVal = true;
        }
        else if(input == 'x')
        {
            returnVal = true;
        }
        else if(input == 'y')
        {
            returnVal = true;
        }
        else if(input == 'z')
        {
            returnVal = true;
        }
        
        return returnVal;
    }
    
}
