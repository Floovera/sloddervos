public class Factory {
    public Command getCommand(CommandWord command, String secondWord) {
        switch (command) {
            case DROP:
                return new Drop(command, secondWord);
            case GO:
                return new Go(command, secondWord);
            case HELP:
                return new Help(command, secondWord);
            case LOOK:
                return new Look(command, secondWord);
            case QUIT:
                return new Quit(command, secondWord);
            case TAKE:
                return new Take(command, secondWord);
            case CHECK:
                return new Check(command, secondWord);
            case UNKNOWN:
            default:
                return new Unknown(command, secondWord);
        }
    }
}

