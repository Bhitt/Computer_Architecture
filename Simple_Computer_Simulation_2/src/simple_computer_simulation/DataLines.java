/*
    Data lines: data transferred between peripherals
    , memory, and the CPU
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class DataLines {
    //Properties
    private Integer val;
    
    //Default Constructor
    DataLines(){
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
