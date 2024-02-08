package ui;

import ui.pages.MainPage;

//Runs the GUI application.
public class GuiApp {
    private static final String title = "Cat's Dating Diary Recorder";

    public static void main(String[] args) {
        new MainPage(title);
    }
}