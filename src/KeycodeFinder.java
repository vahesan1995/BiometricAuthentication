import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeycodeFinder implements KeyListener {
    private long kptime;        //key pressed time
    private ArrayList<Keycode> timeDataArray = new ArrayList<>();
    private Character key;      //pressed key

    public void keyPressed(KeyEvent e) {
        onKeyPressed(e);
        key = e.getKeyChar();
    }

    public void keyReleased(KeyEvent e) {
        onKeyReleased(e);
    }

    public void keyTyped(KeyEvent e) {

    }

    private void onKeyPressed(KeyEvent event) {
        kptime = System.currentTimeMillis();
        System.out.println("key pressed");
    }

    private void onKeyReleased(KeyEvent event) {
        long keyPressLength = System.currentTimeMillis()- kptime;
        Keycode keycode = new Keycode(key,keyPressLength);
        timeDataArray.add(keycode);
    }

    public ArrayList<Keycode> getTimeDataArray(){
        return timeDataArray;
    }
}