public class Quit extends Command{

    public Quit(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        if(hasSecondWord()) {
            System.out.println("Stop met spelen?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
