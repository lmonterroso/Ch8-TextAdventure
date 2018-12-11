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
 * @author Luis Monterroso
 * @version 2018.11.05
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean locked;
    private boolean trapDoor;
    private Item key;
    private NPC stranger;

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
        locked = true;
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

    /**
     * adds an item to the room
     */
    public void addItem(Item newItem)
    {
        items.add(newItem);
    }
    
    /**
     * sets a key to a room and designates the room as locked
     */
    public void setKey(String newItem)
    {
        key = new Item(0, newItem);
        locked = false;
    }
    
    /**
     * returns the description of the key
     * @return the description field of the key
     */
    public String getKey()
    {
        return key.getDescription();
    }
    
    /**
     * removes an item from a room
     */
    public void removeItem(Item delItem)
    {
        items.remove(delItem);
    }
    
    /**
     * returns an item in the room
     * @param the string to be used to search for your item
     * @return the item you are looking for
     */
    public Item getItem(String itemSearch){
        for(Item search : items){
            if (search.getDescription().equals(itemSearch)){              
                return search;
            }
        }
        return new Item(0, "");
    }
    
    /**
     * @param string being used to look for certain item
     * @return true if a match is found and false if one is not
     */
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
     * Prints out all the items in the room by concatonating them into one string. 
     * @return a string of all the items in the room.
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
    
    /**
     * checks if the player has the key and if they do return true otherwise return the value 
     * of locked, (false means the rooom is open, true means the room is locked)
     * @return true or false, first based on if there is a matching key then if the rooom is locked if the first tesst fails.
     */
    public boolean getLocked(Player player){
        if (player.checkItem(key.getDescription())){
            return true;
        }
        return locked;
    }
    
    /**
     * sets whether or not the room is a trap door
     * @param true or false value to be passed on to the room's trapDoor field
     */
    public void setTrapDoor(boolean check){
        trapDoor = check;
    }
    
    /**
     * checks if the room is a trapDoor or not
     *@return the value of trapDoor 
     */
    public boolean getTrapDoor(){
        return trapDoor;
    }
    
    /**
     * checks if there is an NPC to give something to and reurns true or false
     * @return true if there is an NPC, false if there isn't.
     */
    public boolean giveNPC()
    {
        if (stranger != null)
            return true;
        else
        {
            System.out.println("No one to give to");
            return false;
        }
    }
    
    /**
     * get the Rooms NPC 
     * @return the NPC in the stranger field of the room
     */
    public NPC getNPC()
    {
        return stranger;
    }
    
    /**
     * sets the NPC for the room
     * @param the NPC to be assigned to the rooms stranger field.
     */
    public void setNPC(NPC newStranger)
    {
        stranger = newStranger;
    }
}

