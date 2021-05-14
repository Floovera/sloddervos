public class Count {

    private int count;

    public Count() {
        count = 0;
    }

    public void raise(){
        count = count + 1;
    }

    public String toString(){
        return "Aantal kamers bezocht: " + count;
    }
}
