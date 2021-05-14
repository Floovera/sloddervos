/**
 *  Deze klasse is de main class van het Sloddervossen -spel.
 *  In dit spel is het de bedoeling dat de speler zo snel mogelijk
 *  alle kledingstukken die hij liet rondslingeren in het huis
 *  verzameld en dropt in zijn slaapkamer.
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Floo Veraghtert
 * @version 2021.05.14
 */

public class Game
{
    private Parser parser;
    private Player player;
        
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
        Room inkom, bureau, garage, living, keuken, nachthal, slaapkamer, logeerkamer, badkamer;
        Item kousen,jas,pantoffels,pyjama,hemdje;
      
        // create the rooms
        inkom = new Room("in de inkomhal");
        bureau = new Room("in het bureau");
        garage = new Room("in de garage");
        living = new Room("in de living");
        keuken = new Room("in de keuken");
        nachthal = new Room("boven in de nachthal");
        logeerkamer = new Room("in de logeerkamer");
        slaapkamer = new Room("in de slaapkamer");
        badkamer= new Room("in de badkamer");

        // create the items
        kousen = new Item("kousen","glitterkousjes",0.5);
        jas = new Item("jas","regenjas",2.0);
        pantoffels = new Item("pantoffels","pantoffels maatje 38",3.0);
        pyjama = new Item("pyjama","japonneke",1.8);
        hemdje = new Item("hemdje","witte blouse",1.5);


        // create a player

        player = new Player("Floo",inkom);

        // initialise room exits
        inkom.setExit("east", bureau);
        inkom.setExit("south" ,living);
        inkom.setExit("west" ,garage);
        inkom.setExit("up",nachthal);
        bureau.setExit("west", inkom);
        garage.setExit("east", inkom);
        living.setExit("north" ,inkom);
        living.setExit("east", keuken);
        keuken.setExit("east", living);
        nachthal.setExit("down",inkom);
        nachthal.setExit("east",logeerkamer);
        nachthal.setExit("south",badkamer);
        nachthal.setExit("west",slaapkamer);
        logeerkamer.setExit("west", nachthal);
        slaapkamer.setExit("east", nachthal);
        badkamer.setExit("north" ,nachthal);


        // add items to room
        bureau.addItem(pantoffels);
        living.addItem(kousen);
        keuken.addItem(jas);
        logeerkamer.addItem(hemdje);
        badkamer.addItem(pyjama);

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
        System.out.println("Type '" + CommandWord.HELP.toString() + "' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private void printLocationInfo() {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getLongDescription());
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case LOOK:
                look();
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
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
        System.out.println(player.getName() + " is lost and alone, and wanders ");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:   " + parser.showCommands());
    }

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
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
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if(nextRoom==null) {
            System.out.println("There is no door!");
        }else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
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

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.take(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.drop(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

}
