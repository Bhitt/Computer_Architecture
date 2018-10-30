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
    private Reader reader;
    private Printer printer;
    //private Bus bus;
    private AddressLines addressLines;
    private DataLines dataLines;
    private ControlLines controlLines;
    private MemoryAddressRegister mAR;
    private MemoryDataRegister mDR;
    private Memory memory;
    private MemoryControl memoryControl;
    private Status status;
    
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
        //bus = new Bus();
        addressLines = new AddressLines();
        dataLines = new DataLines();
        controlLines = new ControlLines();
        status = new Status();
    }
    
    //run method : simulates the program execution 
    void run(){
        //run the program
        program();
        
       //final display information
       System.out.println("Branden Hitt " + LocalDateTime.now());
    }
    
    //--------Program----------------//
    void program(){
        //Load R0 with immediate operand #42
        LOAD(R0,42);
        //Store R0 value into R1
        STORE(R0,);
    }
    
    //--------INSTRUCTION SET--------//
    
    //Read instruction
    //-reads an integer from the keyboard and stores it in R0
    void readInstruction(){
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
        //Throw data on the bus using its components
            //bus.setVal(R0.getVal());
        
        //grab data from the bus and throw it on the printer
        printer.setBuffer(bus.getVal());
        //print data from the printer
        System.out.println(">> " + printer.getBuffer());
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
        //throw register values on the adder
        adder.setVal1(regA.getVal());
        adder.setVal2(regB.getVal());
        //add the values and store in regC
        regC.setVal(adder.add());
    }
    
    //Sub instruction
    //regC <- [regB] - [regA]
    void subInstruction(Register regA, Register regB, Register regC){
        System.err.println("\t\t\tSUB " + regA + "," + regB + "," + regC);
        //compliment registerA
        complementer.setVal(regA.getVal());
        complementer.complement();
        //throw both values on the adder
        adder.setVal1(regB.getVal());
        adder.setVal2(complementer.getVal());
        //add the values and store in regC
        regC.setVal(adder.add());
    }
    
    void LOAD(Register destination, Integer source){
        destination.setVal(source);
    }
    
    void STORE(Register source, Integer destination){
        
    }
    
    void DEC(Register reg){
        //decrement a register value by one through adder
        adder.setVal1(reg.getVal());
        adder.setVal2(-1);
        reg.setVal(adder.add());
    }
    
    //Reading a word from memory
    void readMemory(Integer address){
        mAR.set(address);
        addressLines.set(mAR.get());
        controlLines.set(0); //signal for read
        memoryControl.set(controlLines.get());
        mDR.set(memory.get(addressLines.get()));
    }
    
    void storeMemory(Integer address, Integer value){
        
    }
 
    void loop(){
        boolean looping =true;
        while(looping){
            if(!status.zero()){
                continue;
            }
            looping=false;
        }
    }
    
    //Memory Dump
    void memoryDump(){
        memory.memoryDump();
    }
    
}
