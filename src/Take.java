public class Take extends Command{

    public Take(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
            if (!hasSecondWord()) {
                // if there is no second word, we don't know what to take...
                System.out.println("Neem wat?");
                return false;
            }
            String itemName = getSecondWord();
            if (player.take(itemName)) {
                player.printLocationInfo();
            } else {
                System.out.println("Oei, "+ itemName + " vind ik niet terug in deze kamer.");
            }
        return false;
    }
}
