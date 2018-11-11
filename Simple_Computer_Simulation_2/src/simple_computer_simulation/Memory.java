/*
    Memory:
 */
package simple_computer_simulation;


/**
 *
 * @author bhitt
 */
public class Memory {
    //Properties
    private Integer [] values;
    
    //Default Constructor
    Memory(){
    //1024 word size register
        values = new Integer[1024];
        for(int i=0;i<1024;i++){
            values[i]=0;
        }
    }
    
    //Mutator
    void set(Integer address, Integer value){
        values[address] = value;
    }
    
    //Accessor
    Integer get(Integer address){
        return values[address];
    }
    
    
    //Memory Dump
    void memoryDump(){
        for(int i=0;i<1024;i++){
            if(values[i] != 0){
                System.out.println("Memory Dump Address:"+i+" | Content:"+values[i]);
            }
        }
    }
}
