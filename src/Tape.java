/*
 * CS475 - Assignment 3
 */
import java.util.*;
/**
 * This class represents a TM's tape object.  The operations for this class
 * object are as follows:
 * - setting and returning the value of the tape cells
 * - setting and returning the value of the headPosition of the Tape
 *
 * @author David Rausch
 * @version 1.0 May-23-2021
 */
public class Tape {
    private ArrayList<Character>cells;
    private int headPosition;

    /**
     * Default Constructor for Tape Object
     */
    public Tape(){
        headPosition = 0;
    }

    /**
     * Gets the current value of the cells of the Tape object
     * @return an ArrayList containing chars representing the cells of the tape
     */
    public ArrayList<Character>getCells(){
        return cells;
    }

    /**
     * Sets the current value of the cells of the Tape object
     * @param cells
     */
    public void setCells(ArrayList<Character>cells){
        this.cells = cells;
    }

    /**
     * Gets the current value of the head position of the Tape object
     * @return an int representing the head position
     */
    public int getHeadPosition(){
        return headPosition;
    }

    /**
     * Sets the current value of the head position of the Tape object
     * @param headPosition
     */
    public void setHeadPosition(int headPosition){
        this.headPosition = headPosition;
    }
}
