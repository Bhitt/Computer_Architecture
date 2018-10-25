/*
    Control Lines: used to send signals to coordinate
    and manage the activities of the motherboard components
    Read  -> 0
    Write -> 1
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class ControlLines {
    //Properties
    private Integer val;
    
    //Default Constructor
    ControlLines(){
        val=0;
    }
    
    //Mutator
    void set(Integer value){
        val = value;
    }
    
    //Accessor
    Integer get(){
        return val;
    }
}
