package ui.pageslistener;

import model.Event;
import model.EventLog;
import ui.pages.MainPage;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


//This listener invokes by closing the window event .
public class PromptListener implements WindowListener {
    private MainPage mainPage;
    private static final String saveString = "Would you like to save this diary?";

    //EFFECTS:initialize the listener with the corresponding mainpage.
    public PromptListener(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    //EFFECTS:prints all the events to the console since the start of the application
    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    //EFFECTS:prints all the events to the console.Pop-up a save file window option.If yes,then save the diary to the
    // json file.Otherwise, just close app.//
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        int option = JOptionPane.showConfirmDialog(mainPage,saveString,"Save",JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            mainPage.saveDatingDiary();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
