package ui.pages;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//Represents the splash screen.
public class WindowSplashFrame extends JFrame {
    private JWindow splashWin;

    //MODIFIES:this
    //EFFECTS:put splash.jpg image to the window and set the layout of the image too.
    public void prepareSplash() {
        splashWin = new JWindow(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Image image = toolkit
                .getImage("./data/images" + File.separator + "splash.jpg");
        JLabel label = new JLabel();
        label.setSize(new Dimension(100, 100));
        label.setIcon(new ImageIcon(image));
        label.setVisible(true);
        splashWin.add(label);

        Dimension scmSize = toolkit.getScreenSize();
        int imgWidth = image.getWidth(this);
        int imgHeight = image.getHeight(this);
        splashWin.setLocation(scmSize.width / 2 - (imgWidth / 2), scmSize.height / 2
                - (imgHeight / 2));
        splashWin.setSize(imgWidth, imgHeight);
        splashWin.setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS:start displaying window
    public void startSplash() {
        splashWin.setVisible(true);
        splashWin.toFront();
    }

    //MODIFIES:this
    //EFFECTS:close the displaying window
    public void stopSplash() {
        splashWin.dispose();
    }
}
