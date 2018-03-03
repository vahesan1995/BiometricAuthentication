public class Keycode {
    private Object[] set;

    public Keycode(char a, long x) {
        set = new Object[2];
        set[0] = a;
        set[1] = x;
    }

    public Object[] getSet() {
        return set;
    }

    public void setSet(Object[] set) {
        this.set = set;
    }

    public char getChar(){
        return (char) set[0];
    }

    public long getTime(){
        return (long) set[1];
    }
}