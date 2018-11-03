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
        //Store R0 value into memory address 0
        STORE(R0,0);
        //Load R0 with immediate operand #30
        LOAD(R0,30);
        //Store R0 value into memory address
        
        //Load R0 with memory address 0 value
        
        //Print R0 value
        
        //Load R0 with memory address 1023
        
        //Print R0 value
        
        //Read 10 numbers from the keyboard and store them in memory (ListA)
        
        //Sum the 10 numbers (loop)
        
        //Store the sum in location 1001
        
        //Load the value at location 1001 into R0
        
        //Print R0 value
        
        //Copy the 10 numbers to another section and reverse the order (ListB)
        
        //Print the difference (ListB[i] - ListA[i])
     
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
            //printer.setBuffer(bus.getVal());
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
        //add the values and store in regC
        regC.setVal(adder.add(regA.getVal(),regB.getVal()));
    }
    
    //Sub instruction
    //regC <- [regB] - [regA]
    void subInstruction(Register regA, Register regB, Register regC){
        System.err.println("\t\t\tSUB " + regA + "," + regB + "," + regC);
        // add regB and the complement of regA and store into regC
        regC.setVal(adder.add(regB.getVal(),complementer.complement(regA.getVal())));
    }
    
    void LOAD(Register destination, Integer source){
            //grab value at source address
        //put address on MAR
        mAR.set(source);
        //pass address to address control line
        addressLines.set(mAR.get());
        //put a read signal on control line causing mem control to get the address
        //  from the control lines
        memoryControl.set(controlLines.set(0));
        //retrieve the word, and put it in the MDR using data lines
        dataLines.set(memory.get(addressLines.get()));
        mDR.set(dataLines.get());
    }
    
    void STORE(Register source, Integer destination){
            //Grab source value and place it in memory at the destination address
        //put value in the MDR
        mDR.set(source.getVal());
        //put address on control lines
        addressLines.set(destination);
        //put write signal on the control lines
        //memory control gets the address from the control lines
        memoryControl.set(controlLines.set(1));
        //store the word from the MDR into memory
        memory.set(addressLines.get(), mDR.get());
    }
    
    void DEC(Register reg){
        //decrement a register value by one through adder
        reg.setVal(adder.add(reg.getVal(),-1));
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
