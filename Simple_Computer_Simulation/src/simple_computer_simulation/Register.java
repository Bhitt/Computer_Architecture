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
    private Integer value;
    
    //Default Constructor
    Register(){
        value=0;
    }
    
    //Constructor
    Register(Integer val){
        value = val;
    }
    
    //Mutator
    void changeVal(Integer val){
        value = val;
    }
    
    //Accessor
    Integer getVal(){
        return value;
    }
    
}
