/*
    Memory Data Register: register of the computer's
    control unit that contains the data to be stored
    in the computer storage, OR the data after a fetch
    from computer storage.
 */
package simple_computer_simulation;


/**
 *
 * @author bhitt
 */
public class MemoryDataRegister {
    //Properties
    private Integer val;
    
    //Default Constructor
    MemoryDataRegister(){
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
