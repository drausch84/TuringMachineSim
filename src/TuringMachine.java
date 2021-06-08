/*
 * CS475 - Assignment 3 (Topic 5) Turing Machines
 */

import java.util.*;

/**
 * This class file represents a Turing Machine object.  It is designed to handle
 * both an input alphabet as well as tape alphabet.  The run method will 
 * determine whether the alphabets are conflicting
 * 
 * @author David Rausch
 * @version 1.0 May-23-2021
 */
public class TuringMachine {
    private String startState;
    private ArrayList<String> acceptStates;
    private ArrayList<String> states;

    private ArrayList<Transition> transitions;
    private Tape tape;

    private ArrayList<Character> inputAlphabets;
    private ArrayList<Character> tapeAlphabets;

    /**
     * Default Constructor for Turing Machine Object
     */
    public TuringMachine(){
        
    }
    
    /**
     * Gets the current value of the start state of the TM
     * @return a String representing the start state of the TM
     */
    public String getStartState(){
        return startState;
    }
    
    /**
     * Sets the start state of the TM
     * @param startState
     */
    public void setStartState(String startState){
        this.startState = startState;
    }
    
    /**
     * Gets the current value of the accepted states of the TM
     * @return an ArrayList representing the accepted states
     */
    public ArrayList<String> getAcceptStates(){
        return acceptStates;
    }
    
    /**
     * Sets the current value of the accepted states of the TM
     * @param acceptStates
     */
    public void setAcceptStates(ArrayList<String>acceptStates){
        this.acceptStates = acceptStates;
    }
    
    /**
     * Gets the current value of the states of the TM
     * @return an ArrayList representing the states
     */
    public ArrayList<String> getStates(){
        return states;
    }
    
    /**
     * Sets the current value of the states of the TM
     * @param states
     */
    public void setStates(ArrayList<String>states){
        this.states = states;
    }

    /**
     * getter of inputAlphabets
     */
    public ArrayList<Character>inputAlphabet(){
        return inputAlphabets;
    }
    
    /**
     * getter of tapeAlphabets
     */
    public ArrayList<Character>tapeAlphabet(){
        return tapeAlphabets;
    }

    /**
     * run
     */
    public boolean run(String input) {

        tape = new Tape();

        input = input + "^";

        //start with the start state
        String current = startState;

        //process the input characters
        char[] inputs = input.toCharArray();

        //set cells of tape
        ArrayList<Character> cells = new ArrayList<>();
        for (char ch : inputs) {
            cells.add(ch);
        }
        tape.setCells(cells);

        //check input and do transition
        while (true) {

            //out of head
            if (tape.getHeadPosition() < 0){
                return false;
            }

            //get input at the head position of tape
            char in =  tape.getCells().get(tape.getHeadPosition());

            //get the transaction
            Transition tran = findTransition(current, in);

            if (tran == null) { //Transition not found
                return false;
            }

            //write
            if (tran.getWriteSymbol() != '^') {
                tape.getCells().set(tape.getHeadPosition(), tran.getWriteSymbol());
            }

            //move
            if (tran.getDirection().equals(">")) {//forward

                tape.setHeadPosition(tape.getHeadPosition() + 1);

                if (tape.getHeadPosition() == tape.getCells().size()) {
                    tape.getCells().add('^');//empty
                }
            } else  if (tran.getDirection().equals("<")) {//backward
                tape.setHeadPosition(tape.getHeadPosition() - 1);
                if (tape.getHeadPosition() < 0) { //cannot move backward

                    //add an empty cell at index 0
                    tape.getCells().add(0, '^');
                    tape.setHeadPosition(0);
                }
            }

            //move to next state
            current = tran.getToState();

            if (acceptStates.contains(current)) {
                return true;
            }
        }
    }

    /**
     * find the Transition based on the state and input
     *
     * @param state state
     * @param input input
     * @return Transition object or null
     */
    private Transition findTransition(String state, char input) {

        //find the the array list
        for (Transition tran : transitions) {
            if (tran.getFromState().equals(state)
                    && tran.getInputSymbol() == input) {
                return tran;
            }
        }

        return null;
    }
    
    /**
     * getter of states
     */
    public ArrayList<String>states(){
        return states;
    }

    /**
     * setter of transition
     * @param tranList Transitions list
     */
    public void setTransitions(ArrayList<Transition> tranList) {
        this.transitions = tranList;
    }
}
