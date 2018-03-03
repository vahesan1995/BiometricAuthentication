import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Register extends JFrame implements ActionListener{

    private static JButton LoginBtn;
    private static JButton RegisterBtn;
    private JTextField NameText;
    private JPasswordField PasswordText;
    private JPasswordField PassCheckText;
    private KeycodeFinder keycodeFinder;

    public Register(){
        setLocation(500,250);
        setSize(350,200);
        JPanel jpanel = new JPanel();
        add(jpanel);
        panelSetup(jpanel);
        setTitle("Register User");
        setVisible(true);
    }

    private void panelSetup(JPanel jpanel) {

        keycodeFinder = new KeycodeFinder();

        jpanel.setLayout(null);

        Label lblName = new Label("Username");
        lblName.setBounds(30,10,120,25);
        jpanel.add(lblName);

        NameText = new JTextField(20);
        NameText.setBounds(170, 10, 150, 25);
        jpanel.add(NameText);

        Label lblPassword = new Label("Password");
        lblPassword.setBounds(30,50,120,25);
        jpanel.add(lblPassword);

        PasswordText = new JPasswordField(20);
        PasswordText.setBounds(170, 50, 150, 25);
        jpanel.add(PasswordText);

        Label lblCheckpassword = new Label("Check password");
        lblCheckpassword.setBounds(30,90,120,25);
        jpanel.add(lblCheckpassword);

        PassCheckText = new JPasswordField(20);
        PassCheckText.setBounds(170, 90, 150, 25);
        PassCheckText.addKeyListener(keycodeFinder);
        jpanel.add(PassCheckText);

        LoginBtn = new JButton("Login");
        LoginBtn.setBounds(30, 130, 100, 25);
        LoginBtn.addActionListener(this);
        jpanel.add(LoginBtn);

        RegisterBtn = new JButton("Register");
        RegisterBtn.setBounds(200, 130, 100, 25);
        RegisterBtn.addActionListener(this);
        jpanel.add(RegisterBtn);
        jpanel.getRootPane().setDefaultButton(RegisterBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == LoginBtn){
            this.hide();
            Login login = new Login();
        }else if(e.getSource() == RegisterBtn){
            String userName = NameText.getText();
            String password = PasswordText.getText();
            String passcheck = PassCheckText.getText();

            if (userName.equals("") || userName.equals(" ")){
                JOptionPane.showMessageDialog(null, "Username cannot be empty", "Register failed", JOptionPane.INFORMATION_MESSAGE);
            }else {
                if(password.equals("") || password.equals(" ")) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty", "Register failed", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    if (password.equals(passcheck)) {
                        ArrayList<Keycode> timeDataArray = keycodeFinder.getTimeDataArray();

                        User user = new User(userName, password);
                        user.register(timeDataArray);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password and Check password are not similar", "Register failed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            this.hide();
            Register register=new Register();
        }
    }
}