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
        Room inkom, bureau, garage, living, keuken, tuin ,nachthal, slaapkamer, logeerkamer, badkamer;
        Item kousen,jas,pantoffels,pyjama,hemdje;
      
        // create the rooms
        inkom = new Room("in de inkomhal");
        bureau = new Room("in het bureau");
        garage = new Room("in de garage");
        living = new Room("in de living");
        keuken = new Room("in de keuken");
        tuin = new Room("in de tuin");
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
        living.setExit("zuid" ,tuin);
        keuken.setExit("oost", living);
        nachthal.setExit("beneden",inkom);
        nachthal.setExit("oost",logeerkamer);
        nachthal.setExit("zuid",badkamer);
        nachthal.setExit("west",slaapkamer);
        logeerkamer.setExit("west", nachthal);
        slaapkamer.setExit("oost", nachthal);
        badkamer.setExit("noord" ,nachthal);
        tuin.setExit("noord",living);


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
            finished = command.execute(player);
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
        player.printLocationInfo();
    }



    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private void checkEnd(){
        String omschrijvingkamer;
        omschrijvingkamer = player.getCurrentRoom().getDescription();

        if(omschrijvingkamer.equals("in de slaapkamer") && player.getCurrentRoom().hasItem("kousen")){
            System.out.println("Het is gelukt!");
        }
    }

}
