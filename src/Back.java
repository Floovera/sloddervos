public class Back extends Command{

    public Back(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {

        Room previousRoom = player.getPreviousRoom();

        if (hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Terug naar?");
            return false;
        }

        if (previousRoom == null) {
            System.out.println("Je kan niet terug. Dit is  het begin.");
        }
        else if (previousRoom == player.getCurrentRoom()) {
            System.out.println("Je bent al terug waar je daarnet was.");
        } else {
            player.setCurrentRoom(previousRoom);
            player.printLocationInfo();
        }
        return false;
    }
}
