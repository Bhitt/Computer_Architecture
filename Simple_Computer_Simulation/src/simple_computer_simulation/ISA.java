/*
    Industrial Standard Architecture
 */
package simple_computer_simulation;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author bhitt
 */
public class ISA {
    //Properties
    private Register R0;
    private Register R1;
    private Register R2;
    private Register R3;
    private Adder adder;
    private Complementer complementer;
    private Reader reader;
    private Printer printer;
    private Bus bus;
    
    //Default Constructor
    ISA(){
        build();
        run();
    }
    
    //build method : instantiates necessary components
    void build(){
        //instantiate four registers
        R0 = new Register();
        R1 = new Register();
        R2 = new Register();
        R3 = new Register();
        //Instantiate components
        adder = new Adder();
        complementer = new Complementer();
        reader = new Reader();
        printer = new Printer();
        bus = new Bus();
    }
    
    //run method : simulates the program execution 
    void run(){
        //READ
            readInstruction();
        //MOVE R0, R1
            moveInstruction(R0,R1);
        //READ
            readInstruction();
        //MOVE R0,R2
            moveInstruction(R0,R2);
        //ADD R1,R2,R3
            addInstruction(R1,R2,R3);
        //MOVE R3,R0
            moveInstruction(R3,R0);
        //PRINT
            printInstruction();
        //READ
            readInstruction();
        //MOVE  R0, R1
            moveInstruction(R0,R1);
        //SUBTRACT R1,R3,R0
            subInstruction(R1,R3,R0);
        //PRINT
            printInstruction();
        //READ
            readInstruction();
        //MOVE R0,R2
            moveInstruction(R0,R2);
        //ADD R3,R2,R1
            addInstruction(R3,R2,R1);
        //MOVE R1,R0
            moveInstruction(R1,R0);
        //PRINT
            printInstruction();
        
        
       //final display information
       System.out.println("Branden Hitt " + LocalDateTime.now());
    }
    
    //--------INSTRUCTION SET--------//
    
    //Read instruction
    //-reads an integer from the keyboard and stores it in R0
    void readInstruction(){
        //Prompt for input
        System.out.print("* ");
        //grab input
        Scanner input = new Scanner(System.in);
        R0.setVal(input.nextInt());
    }
    
    //Print instruction
    //-prints(displays) the integer contained in R0
    void printInstruction(){
        System.out.println(">> " + R0.getVal());
    }
    
    //Move instruction
    //regB <- [regA]
    void moveInstruction(Register regA, Register regB){
        System.err.println("\t\t\tMOVE " + regA + "," + regB);  //for instruction trace
        regB.setVal(regA.getVal());
    }
    
    //Add instruction
    //regC <-[regA] + [regB]
    void addInstruction(Register regA, Register regB, Register regC){
        System.err.println("\t\t\tADD " + regA + "," + regB + "," + regC); //for instruction trace
        regC.setVal(regB.getVal() + regA.getVal());
    }
    
    //Sub instruction
    //regC <- [regB] - [regA]
    void subInstruction(Register regA, Register regB, Register regC){
        System.err.println("\t\t\tSUB " + regA + "," + regB + "," + regC);
        regC.setVal(regB.getVal() - regA.getVal());
    }
}
