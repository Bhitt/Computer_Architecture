/*
    Reader
    -reads an integer from the keyboard and stores
    it in its buffer. Precedes the actual read by sending
    a "*" character as a prompt.
 */
package simple_computer_simulation;

import java.util.Scanner;

/**
 *
 * @author bhitt
 */
public class Reader {
    //Properties
    private Integer buffer;
    
    //Default Constructor
    Reader(){
        buffer = 0;
    }
    //Constructor
    Reader(Integer buff){
        buffer = buff;
    }
    
    //Accessor
    Integer getOutput(){
        return buffer;
    }
    
    //Mutator 
    void setBuffer(){
        //Prompt for input
        System.out.print("* ");
        //grab input
        Scanner input = new Scanner(System.in);
        buffer = input.nextInt();
    }
}
