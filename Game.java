import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> moves = new Stack<Room>();
    private Player player;
    private Room room1, room2, room3, room4, room5, room6, room7, 
        room8, room9, room10, room11, room12, room13, room14, room15;    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        player = new Player();
        Item cane, handle, bluekey, eyepatch, greenkey, oil;
        
        
        room1 = new Room("in a cold dark room");
        room2 = new Room("in a dusty closet");
        room3 = new Room("in a library");
        room4 = new Room("in a small hallway");
        room5 = new Room("in a lounge with a fireplace and an old man resting in the corner");
        room6 = new Room("in a room with maps all over the walls and a one eyed pirate looking through them");
        room7 = new Room("in  a room with stairs leading up");
        room8 = new Room("in a stone room with paintings on the wall");
        room9 = new Room("in a closet with robot parts everywhere");
        room10 = new Room("in a boiler room");
        room11 = new Room("in a room with two elevators to your north and south");
        room12 = new Room("");
        room13 = new Room("in  a toy room with a metal that's missing a few pieces");
        room14 = new Room("in  a dusty room with a  robot that hasn't seemed to move in ages");
        room15 = new Room("");
        
        room1.setExit("north", room2);
        
        room2.setExit("south", room1);
        room2.setExit("north", room3);
        cane = new Item(3, "old-cane");
        room2.addItem(cane);
        
        room3.setExit("south", room2);
        room3.setExit("west", room4);
        room3.setExit("east", room5);
        handle = new Item(4,"metal-crank");
        room3.addItem(handle);
        
        room4.setExit("east", room3);
        room4.setExit("south", room6);
        room4.setExit("north", room7);
        
        
        room6.setExit("north", room4);
        eyepatch = new Item(1, "eye-patch");
        bluekey = new Item(0, "blue-key");
        NPC pirate = new NPC(eyepatch, bluekey);
        room6.setNPC(pirate);
        pirate.setMessage("Yarr! Thanks for finding me an eye-patch, take this key it doesn't lead to any of the treasure on these maps");
        
        room7.setExit("south", room4);
        room7.setExit("stairs", room11);
        room7.setKey("green-key");
        
        
        room5.setExit("west", room3);
        room5.setExit("north", room8);
        NPC oldMan= new NPC(cane, eyepatch);
        room5.setNPC(oldMan);
        oldMan.setMessage(">Thanks! I found this on the ground, you can have it.");
        
        room8.setExit("south", room5);
        room8.setExit("north", room9);
        
        room9.setExit("south", room8);
        room9.setExit("east", room10);
        oil = new Item(4, "oil-can");
        room9.addItem(oil);
        
        room10.setExit("west", room9);
        greenkey = new Item(0, "green-key");
        room10.setKey("blue-key");
        room10.addItem(greenkey);
        
        room11.setTrapDoor(true);
        room11.setExit("south", room12);
        room11.setExit("east", room13);
        room11.setExit("west", room14);
        room11.setExit("north", room15);
        
        room13.setExit("west", room11);
        NPC robot = new NPC(handle, null);
        robot.setMessage("You need to walk through the valley to cross the mountain");
        
        room14.setExit("east", room11);
        NPC oldRobot = new NPC(oil, null);
        oldRobot.setMessage("Follow the stars and you will surely get lost");
        
        currentRoom = room1;  // start game outside
        moves.push(room1);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("You wake up in a dark basement with no memmory of the night before." +
                           "You must find your way out of this basement so you don't miss class");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                lookRoom(command);
                break;
                
            case GIVE:
                giveItem(command);
                break;
            case BACK:
                back(command);
                break;
            case GRAB:
                grabItem(command);
                break;
                
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom.getIsLocked(player)){
            moves.push(currentRoom);
            currentRoom = nextRoom;
            checkRoom(nextRoom);
        }
        else 
            System.out.println("The door is locked you need a " + nextRoom.getKey());
    }
    
    /**
     * 
     */

    private void back(Command command)
    {
        if(!command.hasSecondWord()) {
            if (!moves.empty())
                currentRoom = moves.pop();
        System.out.println(currentRoom.getLongDescription());
        return;
        }
        else if (command.isInteger(command.getSecondWord(), 10)){
            
            int numberOfMoves = Integer.parseInt(command.getSecondWord());
            while (numberOfMoves > 0 && !moves.empty())
            {
                currentRoom = moves.pop();
                numberOfMoves--;
            }
            System.out.println(currentRoom.getLongDescription());
        
        }
        else System.out.println("I don't know what you mean...");
    }   
        
    
    private void checkRoom(Room check){
        if (check.getTrapDoor()){
            while (!moves.empty())
            {
               moves.pop();
            }
            System.out.println("The way behind you suddenly closes!");
            currentRoom.setTrapDoor(false);
        }
        
        if (currentRoom == room11)
        {
            System.out.println(currentRoom.getLongDescription());
            System.out.println("You notice  a sign that reads: Warning! " + 
            "Choose carefully only elevator will take you to freedom, the other will be your end!!!");
        }
        
        else if (currentRoom == room12)
        {
            while (!moves.empty())
            {
               moves.pop();
            }
            System.out.println("The elevator whirs on and begins to move, the door opens and you see sunlight. \n" +
                               "Congratulations you have escaped from Zuul! Type quit to end the game."); 
        }
        
        else if (currentRoom == room15)
        {
            while (!moves.empty())
            {
               moves.pop();
            }
            System.out.println("The elevator whirs on and begins to move, suddenly the floor under you collapses and you fall to your death. \n" +
                               "You lose! Try again next time. Type quit to end the game."); 
        }
        else
            System.out.println(currentRoom.getLongDescription());
    }
    
    /** 
     * Try to look in one direction. If there is an exit, view the new
     * room, otherwise print an error message.
     */
    private void lookRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to look...
            System.out.println(currentRoom.getLongDescription());
            return;
        }

        String direction = command.getSecondWord();

        // Try to look at the new room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is nothing there!");
        }
        else {
            System.out.print("If you go " + direction + " then you wil be ");
            System.out.println(nextRoom.getShortDescription());
        }
    }
    
    private void giveItem(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Give what?");
            return;
        }
        
        String item = command.getSecondWord();
        if (currentRoom.giveNPC())
        {
            currentRoom.getNPC().wantItem(item, player);
        } 
    } 
    
    
    /**
     * 
     */
    
    private void grabItem(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Grab what?");
            return;
        }
        
        String item = command.getSecondWord();
        Item newItem;
        if (currentRoom.findItem(item)){
            newItem = currentRoom.getItem(item);
            if (player.addItem(newItem))
            {
            currentRoom.removeItem(newItem);
            }
        }
        else System.out.println("That's not a valid item!");
        
        
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
