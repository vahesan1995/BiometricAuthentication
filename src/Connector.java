import javax.swing.*;
import  java.sql.*;
import java.util.ArrayList;

public class Connector {

    private Statement statement;
    private Connection connection;
    private String userid;

    public Connector(){
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmauthentication?useSSL=false", "root", "");
            statement = connection.createStatement();
            System.out.println("connected");
            userid= User.createUserid();

        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void addUser(User user){
        String username = user.getUserName();
        String password = user.getPassword();

        try {
            String query = "insert into user (`userid`,`username`,`password`) values ('"+userid+"','" + username + "','" + password + "')";
            int countInserted = statement.executeUpdate(query);

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void addTimeDataArray(ArrayList<Keycode> timeDataArray){
        int i = 0;
        for (Keycode keycode : timeDataArray){
            char key = keycode.getChar();
            long time = keycode.getTime();
            try {
                String query = "insert into keycode (`userid`,`id`,`key`,`time`) values('"+userid+"','" + i + "','" + key + "','" + time + "')";
                int countInserted = statement.executeUpdate(query);
                i++;
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
    }

    public int checkUsername(User user){
        String username=user.getUserName();
        int num;
        try{
            int num2=0;
            String statement2="SELECT * FROM user WHERE `username`='"+username+"'";
            ResultSet result = statement.executeQuery(statement2);
            if(result.next()){
                num2=1;
            }
            if (num2==0){
                num=0;
            }else{
                num=1;
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
            num=2;
        }
        return num;
    }

    public int checkPassword(User user){
        String username=user.getUserName();
        String password=user.getPassword();
        int num;
        try{
            String statement2="SELECT * FROM user WHERE `username`='"+username+"'";
            ResultSet result = statement.executeQuery(statement2);
            if(result.next()){
                String logpw=result.getString(3);
                if (password.equals(logpw)){
                    num=0;
                }else {
                    num=1;
                }
            }else {
                num=2;
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
            num=3;
        }
        return num;
    }

    public ArrayList<Long> getSignupArray(User user) {
        ArrayList<Long> signupArray;
        try {
            String username = user.getUserName();
            String query = "Select time From keycode,user where user.userid=keycode.userid AND `username`='" + username + "' order by id";
            ResultSet result = statement.executeQuery(query);
            ResultSetMetaData rsmd = result.getMetaData();
            int rows = rsmd.getColumnCount();

            signupArray = new ArrayList<>(rows);
            while (result.next()) {
                int i = 1;
                while (i <= rows) {
                    signupArray.add(result.getLong(i++));
                }
            }
            return signupArray;
        }
        catch (SQLException exception) {
            return null;
        }
    }
}