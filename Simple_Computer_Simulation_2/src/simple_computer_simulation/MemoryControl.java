/*
    Memory Control: 
 */
package simple_computer_simulation;

import java.util.BitSet;

/**
 *
 * @author bhitt
 */
public class MemoryControl {
    //Properties
    private BitSet val;  //1024 word (2^10 bits)
    
    //Default constructor
    MemoryControl(){
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
