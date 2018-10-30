/*
    Status: includes a Zero indicator to be set if the result
    of any arithmetic operation is zero
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Status {
    //Properties
   private boolean flag;
    
    //Default Constructor
    Status(){
        flag = false;
    }
    
    //Mutator
    void set(boolean val){
        flag = val;
    }
    
    //return zero for false and one for true
    boolean zero(){
        return flag;
    }
    
    
    
}
