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
    private MemoryAddressRegister mAR;
    private MemoryDataRegister mDR;
    private Memory memory;
    
    //Default constructor
    MemoryControl(){
        mAR = new MemoryAddressRegister();
        mDR = new MemoryDataRegister();
        memory = new Memory();
    }
    
    //Mutators
    void setMDR(Integer value){
        mDR.set(value);
    }
    
    void setMAR(Integer address){
        mAR.set(address);
    }
    
    void storeWord(Integer address, Integer value){
        
    }
    
    //Accessors
    Integer getMDR(){
        return mDR.get();
    }
    
    Integer getMAR(){
        return mAR.get();
    }
    
    Integer readWord(Integer address){
        mAR.set(address);
        addressLines.set(mAR.get());
        controlLines.set(0); //signal for read
        memoryControl.set(controlLines.get());
        mDR.set(memory.get(addressLines.get()));
        return mDR.get();
    }
    
    //Memory dump call
    void memoryDump(){
        memory.memoryDump();
    }
}
