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
        kousen = new Item("kousen","glitterkousjes");
        jas = new Item("jas","regenjas");
        pantoffels = new Item("pantoffels","pantoffels maatje 38");
        pyjama = new Item("pyjama","japonneke");
        hemdje = new Item("hemdje","witte blouse");


        // create a player

        player = new Player("Floo",inkom);

        // initialise room exits
        inkom.setExit("oost", bureau);
        inkom.setExit("zuid" ,living);
        inkom.setExit("west" ,garage);
        inkom.setExit("boven",nachthal);
        bureau.setExit("west", inkom);
        garage.setExit("oost", inkom);
        living.setExit("noord" ,inkom);
        living.setExit("oost", keuken);
        keuken.setExit("oost", living);
        nachthal.setExit("beneden",inkom);
        nachthal.setExit("oost",logeerkamer);
        nachthal.setExit("zuid",badkamer);
        nachthal.setExit("west",slaapkamer);
        logeerkamer.setExit("west", nachthal);
        slaapkamer.setExit("oost", nachthal);
        badkamer.setExit("noord" ,nachthal);


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
        System.out.println("Bedankt om te spelen! Tot ziens.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welkom in het Sloddervossen -spel!");
        System.out.println("In dit spel is het de bedoeling dat je zo snel mogelijk de kledingstukken die je liet rondslingeren verzameld in de slaapkamer.");
        System.out.println("Wanneer je hulp kan gebruiken, typ dan " + CommandWord.HELP.toString() + " en dan helpen we je verder.");
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
                System.out.println("Mmm ben niet zeker wat je hier mee wil bedoelen ...");
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
        System.out.println(player.getName() + " lijkt verloren.");
        System.out.println("Gebruik volgende commands om verder te spelen:   " + parser.showCommands());
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
            System.out.println("Ga naar waar?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if(nextRoom==null) {
            System.out.println("Oei er is geen deur ...");
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
            System.out.println("Stop met spelen?");
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
            System.out.println("Neem wat?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.take(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("Oei, "+ itemName + " vind ik niet terug in deze kamer.");
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop wat?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.drop(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("Oei, het lijkt alsof je je" + itemName + " vergeten bent ...");
        }
    }

}
