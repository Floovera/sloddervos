public class Look extends Command{
    public Look(CommandWord firstWord, String secondWord)
    {
        super(firstWord, secondWord);
    }

    public boolean execute(Player player)
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
        return false;
    }
}
