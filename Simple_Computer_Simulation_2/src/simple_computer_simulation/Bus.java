/*
    Bus
    -Holds an integer. We will assume that this bus is the only
    way to convey data to or from the Printer and Reader objects.
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Bus {
    //Properties
    private Integer val;
    
    //Default Constructor
    Bus(){
        val = 0;
    }
    
    //Constructor
    Bus(Integer value){
        val=value;
    }
    
    //Accessor
    Integer getVal(){
        return val;
    }
    
    //Mutator
    void setVal(Integer value){
        val = value;
    }
}
