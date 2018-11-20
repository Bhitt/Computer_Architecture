/*
    Instruction Register: holds the current instruction code
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class InstructionRegister {
    //Properties
    private Integer value;
    //Default Constructor
    InstructionRegister(){
        value=0;
    }
    //Accessor
    Integer getVal(){
        return value;
    }
    //Mutator
    void setVal(Integer val){
        value = val;
    }
}
