/*
    Printer
    -displays the content of its integer buffer. Precedes the value
    with the string ">>" to flag it as output from the program.
    Note: the blank spaces following the * and >>.
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Printer {
    //Properties
    private Integer buffer;
    
    //Default Constructor
    Printer(){
        buffer=0;
    }
    
    //Constructor
    Printer(Integer buff){
        buffer = buff;
    }
    
    //Accessor
    Integer getBuffer(){
        return buffer;
    }
    
    //Mutator
    void setBuffer(Integer buff){
        buffer = buff;
    }
    
    
}
