package ui.pageslistener;

import model.Person;
import ui.pages.AddPage;
import ui.pages.MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener invokes by the click event of the confirm button from AddPage class.
public class ConfirmListener implements ActionListener {
    private AddPage addPage;
    private MainPage mainPage;

    //EFFECTS:initialize the listener with the corresponding mainpage and addpage.
    public ConfirmListener(AddPage addPage, MainPage mainPage) {
        this.addPage = addPage;
        this.mainPage = mainPage;
    }

    // MODIFIES:this
    // EFFECTS:adds this person to both the ArrayList and the Jlist.
    @Override
    public void actionPerformed(ActionEvent e) {
        Person person = addPage.buildPerson();
        mainPage.getDiary().addPerson(person);
        mainPage.addPersontoJList(person);
        addPage.dispose();
    }
}
