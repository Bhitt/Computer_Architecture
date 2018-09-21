/*
    Reader
    -reads an integer from the keyboard and stores
    it in its buffer. Precedes the actual read by sending
    a "*" character as a prompt.
 */
package simple_computer_simulation;

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
    Integer getBuffer(){
        return buffer;
    }
    
    //Mutator 
    void setBuffer(Integer buff){
        buffer = buff;
    }
}
