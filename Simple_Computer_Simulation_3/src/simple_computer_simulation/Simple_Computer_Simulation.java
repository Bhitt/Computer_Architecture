/*

 */
package simple_computer_simulation;

/**
 *
 * @author bhitt
 */
public class Simple_Computer_Simulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instantiate the computer object and build it
        ISA computer = new ISA();
        // load the program
        ProgramLoader programLoader = new ProgramLoader();
        computer.loadProg(programLoader.getFW(), programLoader.getFI(), programLoader.getInstruction());
        //run the ISA
        computer.run();
        
    }
    
}
