/*
 * CS475 - Assignment 3
 */

import java.io.*;
import static java.lang.System.exit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main class of the program.  The class will load a Turning machine from the user from
 * a .txt input file as well as testing multiple .txt files to determine
 * acceptance by the Turning machine
 *
 * Big-O analysis
 * The tape is infinite in both ends. So we cannot know
 * when it stops at the accepted state or it goes to unaccepted state.
 *
 * Big-O for findTransition method of TurningMachine class
 * Assume the number of transitions is T, then this method takes O(T)
 *
 * Time complexity for run method of TurningMachine class in one case:
 * Assume that the length of input is N and
 * Each input character is visited once. Then time complexity is O(N * T)
 *
 *
 * @author David Rausch
 * @version 1.0 May-23-2021
 */
public class CS475RauschAssn3 {

    /**
     * The main method of the entire program
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ask for input Turning Mamchine file
        JFileChooser jFile = new JFileChooser("Please choose the Turning machine file");

        //show the input
        int returnVal = jFile.showOpenDialog(null);

        //if file is chosen
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            TuringMachine tm = loadTuringMachine(jFile.getSelectedFile());
            //TuringMachine tm = loadTuringMachine(new File("input/TM1.txt"));

            while (true){

                // user input to get test string and testing via run method
                String inputString = JOptionPane.showInputDialog(null,
                        "Enter the " + "String to be tested.");

                //use click Cancel
                if (inputString == null){
                    break;
                }

                //check if it is accepted or not
                if (tm.run(inputString)) {
                    JOptionPane.showMessageDialog(null, "String accepted by the Turing Machine");
                } else {
                    JOptionPane.showMessageDialog(null, "String not accepted by the Turing Machine");
                }

                //ask for input more
                if (JOptionPane.showConfirmDialog(null, "Run more", "Do you want to input more?",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.NO_OPTION){
                    break;
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "No input file chosen by the"
                    + " user or another error occcured.");
        }

    }//end main

    /**
     * load TuringMachine from file
     */
    private static TuringMachine loadTuringMachine(File input) {

        //create TuringMachine
        TuringMachine tm = new TuringMachine();

        //store read information
        StringBuffer sb = new StringBuffer();

        try {
            Scanner scan = new Scanner(input);

            //all states in Turning machine
            ArrayList<String> statesList = new ArrayList<>();

            //set start state from first line
            tm.setStartState(scan.nextLine());

            //test start state input
            sb.append("State state: " + tm.getStartState() + "\n");

            statesList.add(tm.getStartState());

            //set accept states
            String acceptStates = scan.nextLine();
            String str[] = acceptStates.split(" ");
            ArrayList<String> acceptStatesList = new ArrayList<>();
            acceptStatesList.addAll(Arrays.asList(str));
            tm.setAcceptStates(acceptStatesList);

            statesList.addAll(tm.getAcceptStates());

            //test accept states' input
            sb.append("Accept states: " + tm.getAcceptStates().toString() + "\n");

            // Create a transitions list from user input and set list in auto
            ArrayList<Transition> tranList = new ArrayList<>();

            while (scan.hasNext()) {
                Transition transition = new Transition();

                transition.setFromState(scan.next());
                //e.g aa>
                //e.g bb>
                //e.g ^^^
                String temp = scan.next().trim();

                temp = temp.substring(1); //remove '('
                temp = temp.substring(0, temp.length() - 1); //remove ')'

                transition.setInputSymbol(temp.charAt(0));
                transition.setWriteSymbol(temp.charAt(1));
                transition.setDirection(String.valueOf(temp.charAt(2)));

                //to state
                transition.setToState(scan.next());

                tranList.add(transition);
                sb.append("Transition: " + transition.getFromState() + " "
                        + transition.getInputSymbol() + " "
                        + transition.getWriteSymbol() + " "
                        + transition.getDirection()+ " "
                        + transition.getToState() + "\n");

                //add to all states
                if (!statesList.contains(transition.getFromState())){
                    statesList.add(transition.getFromState());
                }
                if (!statesList.contains(transition.getToState())){
                    statesList.add(transition.getToState());
                }
            }

            tm.setTransitions(tranList);
            tm.setStates(statesList);

            //add states
            sb.append("States: " + statesList.toString() + "\n");

            //close file
            scan.close();

            //show message
            JOptionPane.showMessageDialog(null, sb.toString());

        } catch (FileNotFoundException e) {
            Logger.getLogger(CS475RauschAssn3.class.getName()).log(
                    Level.SEVERE, null, e);
        }

        return tm;
    }
    
}
