/*
    Memory Control: 
 */
package simple_computer_simulation;


/**
 *
 * @author bhitt
 */
public class MemoryControl {
    //Properties
    private Integer val;
    
    //Default constructor
    MemoryControl(){
        val = 0;
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
