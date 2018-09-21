/*
    Adder
    -adds the two integer values it holds, makes the result available to be
    obtained by another object
 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Adder {
    //Properties
    private Integer val1;
    private Integer val2;
    
    //Default Constructor
    Adder(){
        val1 = 0;
        val2 = 0;
    }
    
    //Constructor
    Adder(Integer value1, Integer value2){
        val1 = value1;
        val2 = value2;
    }
    
    //Add function
    Integer add(Integer value1, Integer value2){
        return value1+value2;
    }
    
    //Add held values function
    Integer addHeld(){
        return val1+val2;
    }
    
    //Accessors
    Integer getVal1(){
        return val1;
    }
    
    Integer getVal2(){
        return val2;
    }
    
    //Mutators
    void setVal1(Integer value){
        val1=value;
    }
    
    void setVal2(Integer value){
        val2=value;
    }
}
