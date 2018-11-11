/*
    Address Lines: pass memory addresses to one another over the address bus
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class AddressLines {
    //Properties
    private Integer val;
    
    //Default Constructor
    AddressLines(){
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
