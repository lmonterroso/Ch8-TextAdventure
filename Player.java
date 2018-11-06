import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int carryCapacity;
    private int weight;
    private ArrayList<Item> inventory;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        carryCapacity = 100;
        weight = 0;
        inventory = new ArrayList<Item>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addItem(Item pickUp)
    {
        // put your code here
        weight += pickUp.getWeight();
        inventory.add(pickUp);
    }
    
    /**
     * 
     */
    public void removeItem(Item remove)
    {
        inventory.remove(remove);
    }
}
