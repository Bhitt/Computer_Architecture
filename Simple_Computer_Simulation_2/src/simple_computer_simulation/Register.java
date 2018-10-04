/*
    Register
    -holds an integer value
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Register {
    //Properties
    private Integer val;
    
    //Default Constructor
    Register(){
        val = 0;
    }
    
    //Constructor
    Register(Integer value){
        val = value;
    }
    
    //Mutator
    void setVal(Integer value){
        val = value;
    }
    
    //Accessor
    Integer getVal(){
        return val;
    }
    
}
