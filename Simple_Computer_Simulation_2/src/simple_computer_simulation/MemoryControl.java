/*
    Memory Control: controls the memory
 */
package simple_computer_simulation;


/**
 *
 * @author bhitt
 */
public class MemoryControl {
    //Properties
    private Memory memory;
    
    //Default constructor
    MemoryControl(){
        memory = new Memory();
    }
    
    //Mutators
    void setMemory(Integer address, Integer value){
        memory.set(address, value);
    }
    
    //Accessors
    Integer getMemory(Integer address){
        return memory.get(address);
    }
    
    //Memory dump call
    void memoryDump(){
        memory.memoryDump();
    }
}
