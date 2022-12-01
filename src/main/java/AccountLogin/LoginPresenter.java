package AccountLogin;

import AccountCreation.Account;
import UI.LoginUI;
import UI.SwiperUI;
import UI.ViewModel;
import matching.MatchingAlgorithm;
import swipe.SwiperInputBoundary;
import swipe.SwiperInteractor;
import swipe.SwiperPresenter;
import swipe.screen.EmptyScreen;
import swipe.screen.SwipeScreen;
import swipe.screen.SwiperController;
import swipe.screen.SwiperPresenterFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LoginPresenter implements LoginOutBoundary{

    ViewModel loginUI;

    public LoginPresenter(ViewModel UI){
        this.loginUI = UI;
    }

    @Override
    public void loginOutcome(LoginOutModel responseModel) {
        LoginUI loginUI = new LoginUI();
        if (responseModel.getLoginStatus()) {
            loginUI.loginSuccessMechanism();
            // TODO: go to swiperUI of this user's account.
            JFrame frame = new JFrame("FrameDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        } else {
            //Carry out fail outcome in loginUI.
            loginUI.loginFailMechanism();
        }
    }
    public void swipePage(){
        LinkedList<Account> potentialMatches = MatchingAlgorithm.finalMatches();
        SwiperPresenter swiperPresenter = new SwiperPresenterFormatter();
        SwiperInputBoundary swiperInputBoundary = new SwiperInteractor(swiperPresenter);
        SwiperController swiperController = new SwiperController(swiperInputBoundary);
        if (potentialMatches.isEmpty()) {
            EmptyScreen em = new EmptyScreen();
            JFrame swiper = new JFrame("");
            CardLayout cardLayout = new CardLayout();
            JPanel screens = new JPanel(cardLayout);
            swiper.add(screens);
            screens.add(em, "welcome");
            cardLayout.show(screens, "swipe");
            swiper.pack();
            swiper.setBounds(0, 0, 1440, 1000);
            swiper.setResizable(false);
            swiper.setVisible(true);
        }
        else {
            for (Account pot: potentialMatches) {
                SwipeScreen swipeScreen = new SwipeScreen(swiperController, pot);
                JFrame swiper = new JFrame("Time to swipe");
                CardLayout cardLayout = new CardLayout();
                JPanel screens = new JPanel(cardLayout);
                swiper.add(screens);
                screens.add(swipeScreen, "welcome");
                cardLayout.show(screens, "swipe");

                swiper.pack();
                swiper.setBounds(0, 0, 1440, 1000);
                swiper.setResizable(false);
                swiper.setVisible(true);
                while (!swipeScreen.getPressed()) {
                    int i = 1;
                    i++;
                }
                swiper.setVisible(false);}
        }
    }
}
