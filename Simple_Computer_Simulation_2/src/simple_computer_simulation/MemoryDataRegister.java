/*
    Memory Data Register: register of the computer's
    control unit that contains the data to be stored
    in the computer storage, OR the data after a fetch
    from computer storage.
 */
package simple_computer_simulation;

import java.util.BitSet;

/**
 *
 * @author bhitt
 */
public class MemoryDataRegister {
    //Properties
    private BitSet val;
    
    //Default Constructor
    MemoryDataRegister(){
    //1024 word size register
        val = new BitSet(10);
    }
    
    //Mutator
    void set(BitSet address){
        for(int i=0;i<10;i++){
            val.set(i, address.get(i));
        }
    }
    
    //Accessor
    BitSet get(){
        return val;
    }
    
    
}
