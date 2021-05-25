public class Help extends Command{

    private String commandWords;

    /**
     * Constructor for objects of class HelpCommand
     */
    public Help (CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
        commandWords = "";
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                commandWords += command.toString() + " ";
            }
        }
    }

    /**
     * Print out some help information. Here we print some stupid,
     * cryptic message and a list of the command words.
     * Returns always false.
     */
    public boolean execute(Player player)
    {
        System.out.println(player.getName() + " lijkt verloren.");
        System.out.println("Gebruik volgende commands om verder te spelen:   " + commandWords);
        return false;
    }
}
