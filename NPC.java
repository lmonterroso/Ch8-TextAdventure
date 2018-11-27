
/**
 * Write a description of class NPC here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
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
    
    public void setMessage(String sentence)
    {
        message = sentence;
    }
    
    
    public String message()
    {
        return message;
    }
}
