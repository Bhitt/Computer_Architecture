/*
    Program Counter: holds the next address of instruction
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class ProgramCounter {
    //Properties
    private Integer address;
    //Default Constructor
    ProgramCounter(){
        address=0;
    }
    //Mutator
    void setCounter(Integer add){
        address = add;
    }
    //Accessor
    Integer getCounter(){
        return address;
    }
}
