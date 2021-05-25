public class Go extends Command {
    /**
     * Constructor for objects of class GoCommand
     */
    public Go(CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public boolean execute(Player player)
    {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ga naar?");
            return false;
        }

        String direction = getSecondWord();
        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("Oei ... er is geen deur.");
        } else {
            player.setPreviousRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            player.printLocationInfo();
        }
        return false;
    }
}
