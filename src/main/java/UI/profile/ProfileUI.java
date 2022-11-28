package UI.profile;


import UI.ViewModel;
import UI.components.Button;
import profile.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileUI extends JFrame implements ActionListener, ViewModel {
    JButton editBtn = new JButton("Edit");
    JPanel personalInfoSection = new JPanel(); // figure out layout later
    JLabel nameLbl = new JLabel("Name");
    JTextField nameTF = new JTextField("N/A");
    JLabel pronounLbl = new JLabel("Preferred pronouns");
    JTextField pronounTF = new JTextField("N/A");
    JPanel SBSection = new JPanel(); // figure out layout later

    JPanel SSSection = new JPanel(new FlowLayout());

    public ProfileUI() {
        // first set up personal info section of the profile page
        // name
        nameTF.setEditable(false);
        personalInfoSection.add(nameLbl);
        personalInfoSection.add(nameTF);

        // pronouns
        pronounTF.setEditable(false);
        personalInfoSection.add(pronounLbl);
        personalInfoSection.add(pronounTF);

        // year in uni
        JLabel yearLbl = new JLabel("Year");
        JComboBox<String> yearCB = new JComboBox<String>(Profile.YEARS);
        personalInfoSection.add(yearLbl);
        personalInfoSection.add(yearCB);

        // field of study (single selection)
        JLabel fieldLbl = new JLabel("Field of study");
        JComboBox<String> fieldCB = new JComboBox<String>(Profile.FIELDS);
        personalInfoSection.add(fieldLbl);
        personalInfoSection.add(fieldCB);

        // study styles (up to 3 selections)
        JLabel styleLbl = new JLabel("Study styles");
        personalInfoSection.add(styleLbl);
        for (String style : Profile.STYLES) {
            JToggleButton styleBtn = new JToggleButton(style, false);
            personalInfoSection.add(styleBtn);
        }

        // set up the Study Buddy Preferences section
        // title
        JLabel studyBuddyLbl = new JLabel("-- Study Buddy Preferences --");
        SBSection.add(studyBuddyLbl);
        studyBuddyLbl.setHorizontalAlignment(JLabel.CENTER);

        // year pref (multiple selection, can select all)
        JLabel yearPref = new JLabel("Preferred study buddy year");
        JList yearsList = new JList<String>(Profile.YEARS);
        SBSection.add(yearPref);
        SBSection.add(yearsList);


        // field of study pref (up to 3 selections)
        JLabel fieldPrefLbl = new JLabel("Preferred study buddy field of study");
        JList fieldsList = new JList<String>(Profile.FIELDS);
        SBSection.add(fieldPrefLbl);
        SBSection.add(fieldsList);

        // field of study pref (up to 3 selections)
        JLabel stylesLbl = new JLabel("Preferred study buddy study styles");
        for (String style : Profile.STYLES) {
            SBSection.add(new JButton(style));
        }

        // set up the Study Spots Preferences section
        // title
        JLabel studySpotsLbl = new JLabel("-- Preferred Study Spots --");
        SSSection.add(studySpotsLbl);
        studySpotsLbl.setHorizontalAlignment(JLabel.CENTER);

        // adding the three dropdowns of all study spots
        SSSection.add(new JComboBox<String>(Profile.studySpots));
        SSSection.add(new JComboBox<String>(Profile.studySpots));
        SSSection.add(new JComboBox<String>(Profile.studySpots));

        editBtn.addActionListener(this);

        setVisible(true); // so the frame will show
    }

    @Override
    public void build() {

    }

    public static void main(String[] args) {
        ProfileUI profileUI = new ProfileUI();
        profileUI.build();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // turns page into edit mode
        if (editBtn.getText().equals("Edit")) {
            editBtn.setText("Save");
            nameTF.setEditable(true);
            pronounTF.setEditable(true);
        }
        // turns page into view mode
        else {
            editBtn.setText("Edit");
            nameTF.setEditable(false);
            pronounTF.setEditable(false);
        }
    }

    public class CountListener implements ActionListener {
        public int counter = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals()) {
                counter += 1;
            }
        }
    }

}