public enum CommandWord {
        GO("ga"), LOOK("kijk"), TAKE("neem"), DROP("verwijder"), QUIT("stop"),
        HELP("help"), CHECK("check"), UNKNOWN("?");

        private String commandString;

        CommandWord(String commandString) {
                this.commandString = commandString;
        }

        public String toString() {
                return commandString;
        }
}

