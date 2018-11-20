/*
    Industrial Standard Architecture
 */
package simple_computer_simulation;

import java.time.LocalDateTime;
import java.util.List;

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
    private ProgramCounter programCounter;
    private InstructionRegister instructionRegister;
    
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
        programCounter = new ProgramCounter();
        instructionRegister = new InstructionRegister();
        //set status running to true
        status.setRunning(true);
    }
    
    //load program memory
    void loadProg(Integer fW, Integer fI, List<Integer> instructions){
        //set the program counter to the first instruction address
        programCounter.setCounter(fI);
        //load program into memory starting at the first word
        for(Integer number: instructions){
            memoryControl.setMemory(fW, number);
            fW++;
        }
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
        //obtain the instruction from memory
        //deliver the instruction to the instruction register
        instructionRegister.setVal(memoryControl.getMemory(programCounter.getCounter()));
        //trace stuff
        System.out.println("Starting Location: "+programCounter.getCounter()+"   OPCode:"+instructionRegister.getVal());
    }
    
    void adjustPC(){
        //change the program counter to its new value
        programCounter.setCounter(programCounter.getCounter()+1);
    }
    
    void execute(){
        //variables
        Integer op1,op2,op3;
        //get the instruction from the the instruction register
        Integer code = instructionRegister.getVal();
//        System.out.println("---------------");
//        System.out.println("code:"+code);
//        System.out.println("program counter:"+programCounter.getCounter());
        //decode the instruction
        //call the appropriate method to carry out the instruction
        if(code==110){
            //get three operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            op3 = memoryControl.getMemory(programCounter.getCounter()+2);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+3);
            //call correct version of add
            ADD(op1,op2,op3);
        }else if(code==120){
            //get three operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            op3 = memoryControl.getMemory(programCounter.getCounter()+2);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+3);
            //call subInstruction
            SUB(op1,op2,op3);
        }else if(code==160){
            //get operand
            op1 = memoryControl.getMemory(programCounter.getCounter());
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+1);
            //call Dec
            if(op1==0){
                DEC(R0);
            }else if(op1==1){
                DEC(R1);
            }else if(op1==2){
                DEC(R2);
            }else if(op1==3){
                DEC(R3);
            }else{
                HALT();
            }
        }else if(code==440){
            //get operand
            op1 = memoryControl.getMemory(programCounter.getCounter());
            //increment program counter 
            programCounter.setCounter(programCounter.getCounter()+1);
            //call BRNZ
            BRNZ(op1);
        }else if(code==810){
            //call READ
            readInstruction();
        }else if(code==820){
            //call Print
            printInstruction();
        }else if(code==000){
           //call NOOP
           NOOP();
        }else if(code==999){
            //call HALT
            HALT();
        }else if(code==510){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //call MOVE
            MOVE(op1,op2);
        }else if(code==610){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //Load Absolute - use address parameter with no modifications
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //call load for correct register
            if(op1==0) LOAD(R0,readMemory(op2));
            else if(op1==1) LOAD(R1,readMemory(op2));
            else if(op1==2) LOAD(R2,readMemory(op2));
            else if(op1==3) LOAD(R3,readMemory(op2));
            else HALT();
        }else if(code==620){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //LOAD register indirect - get value from register
            Integer temp = 0;
            if(op2==0) temp = R0.getVal();
            else if(op2==1) temp = R1.getVal();
            else if(op2==2) temp = R2.getVal();
            else if(op2==3) temp = R3.getVal();
            else HALT();
            //choose correct destination
            //call load for correct register
            if(op1==0) LOAD(R0,temp);
            else if(op1==1) LOAD(R1,temp);
            else if(op1==2) LOAD(R2,temp);
            else if(op1==3) LOAD(R3,temp);
            else HALT();
        }else if(code==630){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //LOAD auto increment register indirect - get value from register and increment
            Integer temp = 0;
            if(op2==0) temp = R0.getVal()+1;
            else if(op2==1) temp = R1.getVal()+1;
            else if(op2==2) temp = R2.getVal()+1;
            else if(op2==3) temp = R3.getVal()+1;
            else HALT();
            //choose correct destination
            //call load for correct register
            if(op1==0) LOAD(R0,temp);
            else if(op1==1) LOAD(R1,temp);
            else if(op1==2) LOAD(R2,temp);
            else if(op1==3) LOAD(R3,temp);
            else HALT();
        }else if(code==640){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //Load immediate
            //call load for correct register
            if(op1==0) LOAD(R0,op2);
            else if(op1==1) LOAD(R1,op2);
            else if(op1==2) LOAD(R2,op2);
            else if(op1==3) LOAD(R3,op2);
            else HALT();
        }else if(code==710){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //call Store on correct reg
            if(op1==0) STORE(R0,op2);
            else if(op1==1) STORE(R1,op2);
            else if(op1==2) STORE(R2,op2);
            else if(op1==3) STORE(R3,op2);
            else HALT();
        }else if(code==720){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //get the correct address
            Integer add=0;
            if(op2==0) add=R0.getVal();
            else if(op2==1) add=R1.getVal();
            else if(op2==2) add=R2.getVal();
            else if(op2==3) add=R3.getVal();
            else HALT();
            //Store the source at the address
            if(op1==0) STORE(R0,add);
            else if(op1==1) STORE(R1,add);
            else if(op1==2) STORE(R2,add);
            else if(op1==3) STORE(R3,add);
            else HALT();
        }else if(code==730){
            //get two operands
            op1 = memoryControl.getMemory(programCounter.getCounter());
            op2 = memoryControl.getMemory(programCounter.getCounter()+1);
            //increment program counter
            programCounter.setCounter(programCounter.getCounter()+2);
            //get the correct address
            Integer add=0;
            if(op2==0) add=R0.getVal()+1;
            else if(op2==1) add=R1.getVal()+1;
            else if(op2==2) add=R2.getVal()+1;
            else if(op2==3) add=R3.getVal()+1;
            else HALT();
            //Store the source at the address
            if(op1==0) STORE(R0,add);
            else if(op1==1) STORE(R1,add);
            else if(op1==2) STORE(R2,add);
            else if(op1==3) STORE(R3,add);
            else HALT();
        }else{
            //if the instruction is invalid, dump memory and halt   
            HALT();
        }

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
    void MOVE(Integer regA, Integer regB){
        //get values
        Integer temp1 =0;
        //regA value
        if(regA==0) temp1 = R0.getVal();
        else if(regA==1) temp1 = R1.getVal();
        else if(regA==2) temp1 = R2.getVal();
        else if(regA==3) temp1 = R3.getVal();
        else HALT();
        //store in regB
        if(regB==0) R0.setVal(temp1);
        else if(regB==1) R1.setVal(temp1);
        else if(regB==2) R2.setVal(temp1);
        else if(regB==3) R3.setVal(temp1);
        else HALT();
    }
    
    //Add instruction
    //regC <-[regA] + [regB]
    void addInstruction(Register regA, Register regB, Register regC){
            //System.err.println("\t\t\tADD " + regA + "," + regB + "," + regC); //for instruction trace
        //add the values and store in regC
        regC.setVal(adder.add(regA.getVal(),regB.getVal()));
    }
    
    void ADD(Integer regA, Integer regB, Integer regC){
        //get values
        Integer temp1 =0, temp2=0;
        //regA value
        if(regA==0) temp1 = R0.getVal();
        else if(regA==1) temp1 = R1.getVal();
        else if(regA==2) temp1 = R2.getVal();
        else if(regA==3) temp1 = R3.getVal();
        else HALT();
        //regB value
        if(regB==0) temp2 = R0.getVal();
        else if(regB==1) temp2 = R1.getVal();
        else if(regB==2) temp2 = R2.getVal();
        else if(regB==3) temp2 = R3.getVal();
        else HALT();
        //store in regC
        if(regC==0) R0.setVal(adder.add(temp1, temp2));
        else if(regC==1) R1.setVal(adder.add(temp1, temp2));
        else if(regC==2) R2.setVal(adder.add(temp1, temp2));
        else if(regC==3) R3.setVal(adder.add(temp1, temp2));
        else HALT();
    }
    
    //Sub instruction
    //regC <- [regB] - [regA]
    void subInstruction(Register regA, Register regB, Register regC){
        //System.err.println("\t\t\tSUB " + regA + "," + regB + "," + regC);
        // add regB and the complement of regA and store into regC
        regC.setVal(adder.add(regB.getVal(),complementer.complement(regA.getVal())));
    }
    
    void SUB(Integer regA, Integer regB, Integer regC){
        //get values
        Integer temp1 =0, temp2=0;
        //regA value
        if(regA==0) temp1 = R0.getVal();
        else if(regA==1) temp1 = R1.getVal();
        else if(regA==2) temp1 = R2.getVal();
        else if(regA==3) temp1 = R3.getVal();
        else HALT();
        //regB value
        if(regB==0) temp2 = R0.getVal();
        else if(regB==1) temp2 = R1.getVal();
        else if(regB==2) temp2 = R2.getVal();
        else if(regB==3) temp2 = R3.getVal();
        else HALT();
        //store in regC
        if(regC==0) R0.setVal(adder.add(complementer.complement(temp1), temp2));
        else if(regC==1) R1.setVal(adder.add(complementer.complement(temp1), temp2));
        else if(regC==2) R2.setVal(adder.add(complementer.complement(temp1), temp2));
        else if(regC==3) R3.setVal(adder.add(complementer.complement(temp1), temp2));
        else HALT();
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
        System.out.println("HALT() -> Program will terminate.");
        memoryDump();
        status.setRunning(false);
    }
    
    //NOOP instruction
    //will be ignored when executed
    void NOOP(){
        //nothing happens
    }
    
    //BRNZ instruction
    //branch to an absolute address
    void BRNZ(Integer address){
        programCounter.setCounter(address);
    }
    
    //Memory Dump
    void memoryDump(){
        memoryControl.memoryDump();
    }
}
