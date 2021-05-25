public class Drop extends Command{

    public Drop(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if (!hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop wat?");
            return false;
        }
        String itemName = getSecondWord();
        if (player.drop(itemName)) {
            player.printLocationInfo();
        } else {
            System.out.println("Oei, het lijkt alsof je je" + itemName + " vergeten bent ...");
        }
        return false;
    }
}
