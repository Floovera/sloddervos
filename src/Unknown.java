public class Unknown extends Command{

    public Unknown(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Typ help om te kijken welke commando's je kan gebruiken.");
        return false;
    }
}
