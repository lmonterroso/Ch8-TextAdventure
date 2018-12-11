import java.util.ArrayList;
/**
 * The Player class holds any items picked up during the game. Also specifies a current
 * weight for the items in Players inventory and a max weight for the Player
 *
 * @author Luis Monterroso
 * @version 2018.11.05
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
     * Checks to see if an item can be picked up, if it can it is added to your 
     * inventory and true is returned. Otherwise, false.
     *
     * @param the item to be picked up
     * @return true if the item is able to be picked up, false otherwise. 
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
     * remove an item from the players inventory
     * @param the item to be removed
     */
    public void removeItem(Item remove)
    {
        inventory.remove(remove);
        weight -= remove.getWeight();
    }
    
    /**
     * checks to see if an item is in the players inventory 
     * and return true or false based on the answer
     * @param the string to be compared to every item in the inventory.
     * @return true if an item's description matches param check
     */
    public boolean checkItem(String check){
        boolean test = false;
        for(Item n : inventory){
            if (n.getDescription() == check)
                test = true;
            }
            return test;
        }
}
