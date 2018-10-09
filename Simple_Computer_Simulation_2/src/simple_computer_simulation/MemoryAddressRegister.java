/*
    Memory address register: either stores the memory address
    from which data will be fetched from the CPU, or the address
    to which data will be sent and stored
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class MemoryAddressRegister {
    //Properties
    private Integer val;
    
    //Default Constructor
    MemoryAddressRegister(){
        val=0;
    }
    
    //Mutator
    void setVal(Integer value){
        val=value;
    }
    
    //Accessor
    Integer getVal(){
        return val;
    }
}
