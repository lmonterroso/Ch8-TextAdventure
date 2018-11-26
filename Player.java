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
        carryCapacity = 10;
        weight = 0;
        inventory = new ArrayList<Item>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean addItem(Item pickUp)
    {
        // put your code here
        weight += pickUp.getWeight();
        
        if (weight > carryCapacity)
        {
            System.out.println("You are carrying too much you can't pick this up");
            return false;
        }
        else {
            System.out.println(pickUp.getDescription() + " added to your inventory");
            inventory.add(pickUp);
            return true;
        }
    }
    
    /**
     * 
     */
    public void removeItem(Item remove)
    {
        inventory.remove(remove);
    }
    
    public boolean checkItem(String check){
        boolean test = false;
        for(Item n : inventory){
            if (n.getDescription() == check)
                test = true;
            }
            return test;
        }
}
