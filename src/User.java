import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class User {
    private String userName;
    private String password;
    private Connector connector;


    public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    public void register(ArrayList<Keycode> timeDataArray){
        connector = new Connector();
        connector.connect();
        if (connector.checkUsername(this)==0){
            connector.addUser(this);
            connector.addTimeDataArray(timeDataArray);
            JOptionPane.showMessageDialog(null, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }else if(connector.checkUsername(this)==1){
            JOptionPane.showMessageDialog(null, "Username already exists", "Register failed", JOptionPane.INFORMATION_MESSAGE);
        }else {
            System.out.println("Connection error");
        }
    }

    public void checkLogin(ArrayList<Keycode> loginArray){
        connector=new Connector();
        connector.connect();
        if(connector.checkUsername(this)==1){
            if(connector.checkPassword(this)==0){
                int length=loginArray.size();
                int checker=0;
                ArrayList<Long> signupArray=connector.getSignupArray(this);
                if(signupArray.size()==length) {    //handling the error caused due to using backspace while entering password
                    for (int i = 0; i < length; i++) {
                        long logintime = loginArray.get(i).getTime();
                        long signuptime = signupArray.get(i);

                        if (logintime <= signuptime * 1.20 && logintime >= signuptime * 0.80) {
                            checker++;
                        }
                        System.out.println(signuptime);
                        System.out.println(logintime);
                    }

                    if (checker >= length*0.75) {
                        JOptionPane.showMessageDialog(null, "Successfully Logged in", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Access denied", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Please retry", "System error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Incorrect password", "Login failed", JOptionPane.INFORMATION_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Incorrect username", "Login failed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static String createUserid(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddhhmmssMs");
        String userid = df.format(date);
        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
