public enum CommandWord {
        GO("ga"), LOOK("kijk"), TAKE("neem"), DROP("drop"), QUIT("stop"),
        HELP("help"), CHECK("check"), UNKNOWN("?"), BACK("terug");

        private String commandString;

        CommandWord(String commandString) {
                this.commandString = commandString;
        }

        public String toString() {
                return commandString;
        }
}

