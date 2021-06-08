/*
 * CS475 - Assignment 3
 */

/**
 * This class represents a transition object.  The operations for this class 
 * object are as follows:
 * - Setting and returning a from state transition
 * - Setting and returning an input symbol
 * - Setting and returning a write symbol
 * - Setting and returning a direction
 * - Setting and returning a to state
 *
 * @author David Rausch
 * @version 1.0 May-23-2021
 */
public class Transition {
    private String fromState;
    private char inputSymbol;
    private char writeSymbol;
    private String direction; // < or > or ^
    private String toState;

    /**
     * Default Constructor for Transition Object
     */
    public Transition(){

    }

    /**
     * Gets the current value of the from state
     * @return a String representing the from state
     */
    public String getFromState(){
        return fromState;
    }

    /**
     * Sets the current value of the from state
     * @param fromState
     */
    public void setFromState(String fromState){
        this.fromState = fromState;
    }

    /**
     * Gets the current value of the input symbol
     * @return a char representing the input symbol
     */
    public char getInputSymbol(){
        return inputSymbol;
    }

    /**
     * Sets the current value of the input symbol
     * @param inputSymbol
     */
    public void setInputSymbol(char inputSymbol){
        this.inputSymbol = inputSymbol;
    }

    /**
     * Gets the current value of the write symbol
     * @return a char representing the write symbol
     */
    public char getWriteSymbol(){
        return writeSymbol;
    }

    /**
     * Sets the current value of the write symbol
     * @param writeSymbol
     */
    public void setWriteSymbol(char writeSymbol){
        this.writeSymbol = writeSymbol;
    }

    /**
     * Gets the current value of the direction of the TM
     * @return a String representing the direction of the TM
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Sets the current value of the direction of the TM
     * @param direction
     */
    public void setDirection(String direction){
        this.direction = direction;
    }

    /**
     * Gets the current value of the to state
     * @return a String representing the to state
     */
    public String getToState(){
        return toState;
    }

    /**
     * Sets the current value of the to state
     * @param toState
     */
    public void setToState(String toState){
        this.toState = toState;
    }
}
