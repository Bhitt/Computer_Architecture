/*
    Complementer
    -changes the sign of the value it holds
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Complementer {
    //Properties
    private Integer val;
    
    //Default Constructor
    Complementer(){
        val=0;
    }
    
    //Contstructor
    Complementer(Integer value){
        val = value;
    }
    
    //complement function
    Integer comple(Integer value){
        return value*-1;
    }
    
    //complement held value function
    Integer compleHeld(){
        //complement then return
        val*=-1;
        return val;
    }
    
    //Accesor
    Integer getVal(){
        return val;
    }
    
    //Mutator
    void setVal(Integer value){
        val = value;
    }
    
}
