/*
    Program Loader: loads in the program and stores it in memory
 */
package simple_computer_simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhitt
 */
public class ProgramLoader {
    //Properties
    private String displayName;
    private Integer firstWord;
    private Integer firstInstruction;
    private List<Integer> instructions;
    //Default Constructor
    ProgramLoader(){
        //instantiate list
        instructions = new ArrayList<>();
        //Read in from the file "SimpleComputer_Program1.txt
        File programFile = new File("SimpleComputer_Program1.txt");
        //check to see the file exists
        if(!programFile.exists()){
            System.out.println("The program file 'SimpleComputer_Program1.txt' does not exist.");
        }else{
            try {
                //read in from file
                Scanner fileIn = new Scanner(programFile);
                //read in display name
                displayName = fileIn.nextLine();
                //display the program name
                System.out.println(displayName);
                //get the memory location where the first program word is to be loaded
                firstWord = fileIn.nextInt();
                    //System.out.println("First word at memory location:"+firstWord);
                //get the memory location of the first instuction to be executed
                firstInstruction = fileIn.nextInt();
                    //System.out.println("First instruction to be executed at memory location:"+firstInstruction);
                //read in the rest of the instructions
                while(fileIn.hasNextLine()){
                    //get a line
                    String line = fileIn.nextLine();
                    //System.out.println(line);
                    line = line.split("/")[0];
                    //System.out.println(line);
                    String[] numbers = line.split(" ");
                    for(int i=0;i<numbers.length;i++){
                        if(!numbers[i].isEmpty()){
                            instructions.add(Integer.parseInt(numbers[i]));    
                        }
                    }
                }

                //output list TESTING PURPOSES
//                for(int i=0;i<instructions.size();i++){
//                    System.out.println("["+i+"] :"+instructions.get(i));
//                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProgramLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Accessors
    Integer getFW(){
        return firstWord;
    }
    Integer getFI(){
        return firstInstruction;
    }
    List<Integer> getInstruction(){
        return instructions;
    }
}
