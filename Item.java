
/**
 * Items can be used to interact with NPC's and doors. 
 * They have a  weight and description attached to them.
 *
 * @author Luis Monterroso
 * @version 2018.11.05
 */
public class Item
{
    // instance variables
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(int weight, String name)
    {
        // initialise instance variables
        this.weight = weight;
        description = name;
    }
    
    /**
     * returns the items description
     * @return the description of the item
     */
    public String getDescription(){
        return description;
    }
    
     /**
      * @override
      * Specifies how to print an item if is called in a print statement.
      * @return  The items and description followed by it's weight
      */
    public String toString()
    {
        // put your code here
        return " " + description + ", Weight: " + weight;
    }
    
    /**
     * Get the items weight 
     * @return the weight of an item
     */
    public int getWeight(){
        return weight;
    }
}
