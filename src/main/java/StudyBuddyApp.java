//import UI
import AccountCreation.Account;
import UI.LoginUI;
import UI.UserAuthorizationUI;
import data.persistency.UserDatabase;
import matching.MatchingAlgorithm;
import swipe.SwiperInputBoundary;
import swipe.SwiperInteractor;
import swipe.SwiperPresenter;
import swipe.screen.SwipeScreen;
import swipe.screen.SwiperController;
import swipe.screen.SwiperPresenterFormatter;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class StudyBuddyApp {
    static UserDatabase userDatabase;
    public static void main(String[] args){
        // deserializing the userDatabase.txt file
        UserDatabase userDatabase = new UserDatabase();
        try {
            //Creating stream to read the object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("userDatabase.txt"));
            userDatabase = (UserDatabase) in.readObject();
            //closing the stream
            in.close();
            System.out.println("successful deserialization");
        } catch (Exception e) {
            System.out.println(e);
        }
        // if this is the first time program is running:
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }


        //initial page: user authorization
        LoginUI frame = new LoginUI();
        frame.setTitle("Login Page");
        frame.setVisible(true);
        frame.setBounds(0, 0, 1440, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);




        LoginUI.getFrames()[0].addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    // serialization to userDatabase.txt file
                    try {
                        //Creating stream and writing the object
                        FileOutputStream fout = new FileOutputStream("userDatabase.txt");
                        ObjectOutputStream out = new ObjectOutputStream(fout);

                        //check how many accounts are in the userDatabase
                        int numOfAccounts = UserDatabase.getAccounts().size();
                        System.out.println(numOfAccounts);

                        out.writeObject(UserDatabase.getAccounts().size());
                        out.flush();
                        //closing the stream
                        out.close();
                        System.out.println("successful serialization");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.exit(0);
                }
            }
        });

    }
}
