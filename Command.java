/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second words must be supplied, but
     * the second may be null.
     * @param commandWord The CommandWord. UNKNOWN if the command word
     *                  was not recognised.
     * @param secondWord The second word of the command. May be null.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command.
     * @return The command word.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Author: corsiKa, Source: https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java  
     * Checks to see if the given String is an integer, used in this project only 
     * to check if the second word is an integer for the back command
     * @param s the string to be checked if it is an int
     * @param radix checks if the integers in each character are between 0 and 10
     * @return if the String is an integer
     */
    public static boolean isInteger(String s, int radix) {
    if(s.isEmpty()) return false; //if the string is empty then it isn't an int
    for(int i = 0; i < s.length(); i++) { //iterate through thw string
        if(i == 0 && s.charAt(i) == '-') { //checks the first char to see if the number is negative
            if(s.length() == 1) return false; // if the string is only a '-' then it isn't an int
            else continue;
        }
        if(Character.digit(s.charAt(i),radix) < 0) return false; //Character.digit returns -1 if it is not an int between 0 and 10
    }
    return true;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

