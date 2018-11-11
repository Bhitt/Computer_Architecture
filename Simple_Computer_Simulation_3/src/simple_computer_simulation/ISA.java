/*
    Industrial Standard Architecture
 */
package simple_computer_simulation;

import java.time.LocalDateTime;

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
    private Printer printer;
    private Reader reader;
    private AddressLines addressLines;
    private DataLines dataLines;
    private ControlLines controlLines;
    private MemoryControl memoryControl;
    private MemoryAddressRegister mAR;
    private MemoryDataRegister mDR;
    private Status status;
    
    //Default Constructor
    ISA(){
        build();
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
        printer = new Printer();
        reader = new Reader();
        addressLines = new AddressLines();
        dataLines = new DataLines();
        controlLines = new ControlLines();
        status = new Status();
        memoryControl = new MemoryControl();
        mAR = new MemoryAddressRegister();
        mDR = new MemoryDataRegister();
        status = new Status();
        //set status running to true
        status.setRunning(true);
    }
    
    //run method : simulates the program execution 
    void run(){
        while(status.getRunning()){
            fetch();
            adjustPC();
            execute();
        }
        
       //final display information
       System.out.println("Branden Hitt " + LocalDateTime.now());
    }
    
    void fetch(){
        //get an address from the PC
        
        //obatin the instruction from memory
        
        //deliver the instruction to the instruction register
    }
    
    void adjustPC(){
        //change the program counter to its new value
        
    }
    
    void execute(){
        //get the instruction from the the instruction register
        
        //decode the instruction
        
        //if the instruction is invalid, dump memory and halt
        
        //else
        //call the appropriate method to carry out the instruction
    }
    
    //--------INSTRUCTION SET--------//
    
    //Read instruction
    //-reads an integer from the keyboard and stores it in R0
    void readInstruction(){
        //put read signal on the control lines
        controlLines.set(0);
        //read in through reader
        reader.setBuffer();
        //throw read value onto bus
        dataLines.set(reader.getOutput());
        //throw bus value onto register zero
        R0.setVal(dataLines.get());
    }
    
    //Print instruction
    //-prints(displays) the integer contained in R0
    void printInstruction(){
            //System.err.println("\t\t\tprintInstruction " + R0);  //for instruction trace
        //put write signal on control lines object
        controlLines.set(1);
        //Throw data on the bus using its components
        dataLines.set(R0.getVal());
        //grab data from the bus and throw it on the printer
        printer.setBuffer(dataLines.get());
        //print data from the printer
        System.out.println(">> " + printer.getBuffer());
    }
    
    //Move instruction
    //regB <- [regA]
    void moveInstruction(Register regA, Register regB){
            //System.err.println("\t\t\tMOVE " + regA + "," + regB);  //for instruction trace
        regB.setVal(regA.getVal());
    }
    
    //Add instruction
    //regC <-[regA] + [regB]
    void addInstruction(Register regA, Register regB, Register regC){
            //System.err.println("\t\t\tADD " + regA + "," + regB + "," + regC); //for instruction trace
        //add the values and store in regC
        regC.setVal(adder.add(regA.getVal(),regB.getVal()));
    }
    
    //Sub instruction
    //regC <- [regB] - [regA]
    void subInstruction(Register regA, Register regB, Register regC){
        //System.err.println("\t\t\tSUB " + regA + "," + regB + "," + regC);
        // add regB and the complement of regA and store into regC
        regC.setVal(adder.add(regB.getVal(),complementer.complement(regA.getVal())));
    }
    
    //LOAD instruction
    //loads a value into the register
    void LOAD(Register destination, Integer source){
            //System.err.println("\t\t\tLOAD " + destination + "," + source);  //for instruction trace
        //store source into destination
        destination.setVal(source);
    }
    
    //STORE instruction
    //stores the value from a register into memory location
    void STORE(Register source, Integer destination){
            //System.err.println("\t\t\tSTORE " + source + "," + destination);  //for instruction trace
        //Grab source value and place it in memory at the destination address
        storeMemory(destination,source.getVal());
    }
    
    //DEC instruction
    //decrements a register value
    void DEC(Register reg){
        //decrement a register value by one through adder
        reg.setVal(adder.add(reg.getVal(),-1));
    }
    
    //Reading a word from memory
    Integer readMemory(Integer address){
        //put address on the MAR
        mAR.set(address);
        //mAR -> address lines
        addressLines.set(mAR.get());
        //set signal on control lines
        controlLines.set(0);
        //address lines -> memory control -> memory -> mDR
        dataLines.set(memoryControl.getMemory(addressLines.get()));
        //datalines -> mDR
        mDR.set(dataLines.get());
        //return data from mDR
        return mDR.get();
    }
    
    //Store a word in memory at address
    void storeMemory(Integer address, Integer value){
        //put value on the MDR
        mDR.set(value);
        //send data to the data lines from the MDR
        dataLines.set(mDR.get());
        //put address on the MAR
        mAR.set(address);
        //send address to the address lines from the MAR
        addressLines.set(mAR.get());
        //set signal on the control lines
        controlLines.set(1);
        //use memory control to store the data in memory and the address
        memoryControl.setMemory(addressLines.get(), dataLines.get());
    }
    
    //HALT instruction
    //signals end of execution
    void HALT(){
        status.setRunning(false);
    }
    
    //NOOP instruction
    //will be ignored when executed
    void NOOP(){
        //nothing happens
    }
    
    //BRNZ instruction
    //branch to an absolute address
    void BRNZ(){
        
    }
 
    void loop(){
        boolean looping =true;
        looptop:
        while(looping){
            if(!status.getFlag()){
                continue looptop;
            }
            looping=false;
        }
    }
    
    //Memory Dump
    void memoryDump(){
        memoryControl.memoryDump();
    }
    
}
