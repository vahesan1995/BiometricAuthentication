import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {
    private JButton LoginBtn;
    private JButton RegisterBtn;
    private JTextField NameText;
    private JPasswordField PasswordText;
    private KeycodeFinder keycodeFinder;

    public Login(){
        setLocation(520,270);
        setSize(320,170);
        JPanel jpanel= new JPanel();
        add(jpanel);
        panelSetup(jpanel);
        setTitle("User Login");
        setVisible(true);
    }

    private void panelSetup(JPanel jpanel) {
        keycodeFinder = new KeycodeFinder();

        jpanel.setLayout(null);

        Label lblName = new Label("User name");
        lblName.setBounds(30,10,100,25);
        jpanel.add(lblName);

        NameText = new JTextField(20);
        NameText.setBounds(130, 10, 150, 25);
        jpanel.add(NameText);

        Label lblPassword = new Label("Password");
        lblPassword.setBounds(30,50,100,25);
        jpanel.add(lblPassword);

        PasswordText = new JPasswordField(20);
        PasswordText.setBounds(130, 50, 150, 25);
        PasswordText.addKeyListener(keycodeFinder);
        jpanel.add(PasswordText);
        //PasswordText.setEchoChar('*');

        LoginBtn = new JButton("Login");
        LoginBtn.setBounds(190, 90, 90, 25);
        LoginBtn.addActionListener(this);
        jpanel.add(LoginBtn);
        jpanel.getRootPane().setDefaultButton(LoginBtn);

        RegisterBtn = new JButton("Register");
        RegisterBtn.setBounds(30 , 90, 90, 25);
        RegisterBtn.addActionListener(this);
        jpanel.add(RegisterBtn);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RegisterBtn){
            this.hide();
            Register register = new Register();
        }else if(e.getSource() == LoginBtn){
            String userName = NameText.getText();
            String password = PasswordText.getText();
            ArrayList <Keycode> timeDataArray = keycodeFinder.getTimeDataArray();

            User user = new User(userName,password);
            user.checkLogin(timeDataArray);

            this.hide();
            Login login=new Login();
        }
    }
}