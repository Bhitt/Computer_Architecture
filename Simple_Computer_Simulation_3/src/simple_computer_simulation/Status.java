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
   private boolean running;
    
    //Default Constructor
    Status(){
        flag = false;
    }
    
    //Mutators
    void setFlag(boolean val){
        flag = val;
    }
    
    void setRunning(boolean val){
        running = val;
    }
    
    //Accessors
    boolean getFlag(){
        return flag;
    }
    
    boolean getRunning(){
        return running;
    }
    
}
