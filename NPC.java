
/**
 * NPC's are non-playable characters who may have an item to give and 
 * an item that they want.
 *
 * @author Luis Monterroso
 * @version 2018.11.05
 */
public class NPC
{
    // instance variables - replace the example below with your own
    private Item want;
    private Item give;
    private String message;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(Item want, Item give)
    {
        // initialise instance variables
        this.want = want;
        this.give = give;
    }

    /**
     * Checks if the given string matches the description 
     * of the item the NPC wants.
     *
     * @param wants the string to be compared to wanted item's description
     * @param player the player that will recieve an item if the NPC get's what they want
     */
    public void wantItem(String wants, Player player)
    {
        if (want.getDescription().equalsIgnoreCase(wants))
        {
            System.out.println("The stranger takes your " + wants
             + " and gives you something special.");
            this.giveItem(player);
        }
        else
            System.out.println("They don't seem to want that");
    }
    /**
     * gives the player an item and prints out a message, if 
     * the NPC has nothing to give then they just print out their message. 
     */
    public void giveItem(Player player)
    {
        player.removeItem(want);
        if (give != null)
        {
        System.out.println(message);
        player.addItem(give);
        }
        else
            System.out.println(message);
    }
    
    /**
     * sets the message the NPC will give when it recieves the item wants
     * @param the string holding the message to be assigned.
     */
    public void setMessage(String sentence)
    {
        message = sentence;
    }
    
    /**
     * gives the message the NPC has when it recieves what it wants.
     * @return the message the NPC has when it recieves it's item
     */
    public String message()
    {
        return message;
    }
}
