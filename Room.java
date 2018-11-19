import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean isLocked;
    private boolean trapDoor;
    private Item key;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        key = new Item(0, "");
        isLocked = true;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void addItem(Item newItem)
    {
        items.add(newItem);
    }
    
    public void setKey(String newItem)
    {
        key = new Item(0, newItem);
        isLocked = false;
    }
    
    public String getKey()
    {
        return key.getDescription();
    }
    public void removeItem(Item delItem)
    {
        items.remove(delItem);
    }
    
    public Item getItem(String itemSearch){
        for(Item search : items){
            if (search.getDescription().equals(itemSearch)){              
                return search;
            }
        }
        return new Item(0, "");
    }
    
    public boolean findItem(String itemSearch){
        for(Item search : items){
            if (search.getDescription().equals(itemSearch)){              
                return true;
            }
        }
        return false;
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + 
                getItemString();
    }

    /**
     * 
     */
    public String getItemString()
    {
        String returnString = "\nItems:";
        for(Item itemDescription: items)
        {
            returnString += itemDescription;
        }
        return returnString;
    }
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public boolean getIsLocked(Player player){
        if (player.checkItem(key.getDescription())){
            return true;
        }
        return isLocked;
    }
    
    public void setTrapDoor(){
        trapDoor = true;
    }
    
    public boolean getTrapDoor(){
        return trapDoor;
    }
}

