public class Unknown extends Command{

    public Unknown(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Mmm ben niet zeker wat je hier mee wil bedoelen ...");
        return false;
    }
}
