package ui.pageslistener;

import ui.pages.AddPage;
import ui.pages.MainPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener invokes by the click event of the add and delete buttons from MainPage class.
public class MainListener implements ActionListener {
    private MainPage mainPage;

    //EFFECTS:initialize the listener with the corresponding mainpage.
    public MainListener(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    // MODIFIES:this
    // EFFECTS:if add button is the source, then open the add page.otherwise,delete the selected person from JList.
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        String btnName = pressedButton.getText();
        if ("Add".equals(btnName)) {
            new AddPage(mainPage);
        } else if ("Delete".equals(btnName)) {
            mainPage.deletePerson();
        }
    }
}

