package ui.pages;

import model.Person;
import ui.pageslistener.ConfirmListener;

import javax.swing.*;
import java.awt.*;

//Represents the add pop-up page with confirm button, user input textfields and labels (extends JDialog).
public class AddPage extends JDialog {
    private static final String confirmString = "Confirm";
    private JPanel centerPanel;
    private JLabel nameLabel;
    private JLabel nationLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel ratingLabel;
    private JLabel commentLabel;
    private JTextField nameField;
    private JTextField nationField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField ratingField;
    private JTextArea commentArea;
    private JButton confirmBtn;
    private Container contentPane;
    private  SpringLayout slayout;
    private ConfirmListener confirmListner;

    //EFFECTS:only initialize the add page with owner (mainpage) if the add button is clicked from the main page.
    public AddPage(MainPage mainPage) {
        super(mainPage);
        confirmListner = new ConfirmListener(this, mainPage);
        setSize(700, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        addToPanel();
        setSize();
        contentPane.add(centerPanel,BorderLayout.CENTER);
        setCenterLabelsLayout();
        setCenterInputLayout();
    }

    //MODIDIES:this
    //EFFECTS:instantiates all page components objects.Add confirmlistener to the confirm button.
    private void init() {
        slayout = new SpringLayout();
        centerPanel = new JPanel(slayout);
        contentPane = getContentPane();
        nameLabel = new JLabel("Name:");
        nationLabel = new JLabel("Nationality:");
        ageLabel = new JLabel("Age:");
        genderLabel = new JLabel("Gender:");
        ratingLabel = new JLabel("Rating(-10 to 10):");
        commentLabel = new JLabel("Comment:");
        nameLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        ageLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        genderLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        ratingLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        nationLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        commentLabel.setFont(new Font("Calibri", Font.PLAIN,18));
        nameField = new JTextField();
        nationField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        ratingField = new JTextField();
        commentArea = new JTextArea();
        confirmBtn = new JButton(confirmString);
        confirmBtn.addActionListener(confirmListner);

    }

    //MODIFES:this
    //EFFECTS:set the sizes for all the textfields and the textarea.
    private void setSize() {
        nameField.setPreferredSize(new Dimension(200,30));
        nationField.setPreferredSize(new Dimension(200,30));
        ageField.setPreferredSize(new Dimension(200,30));
        genderField.setPreferredSize(new Dimension(200,30));
        ratingField.setPreferredSize(new Dimension(200,30));
        commentArea.setPreferredSize(new Dimension(200,200));
    }

    //MODIFES:this
    //EFFECTS:add all components to center panel.
    private void addToPanel() {
        centerPanel.add(nameLabel);
        centerPanel.add(nationLabel);
        centerPanel.add(ageLabel);
        centerPanel.add(genderLabel);
        centerPanel.add(ratingLabel);
        centerPanel.add(commentLabel);
        centerPanel.add(nameField);
        centerPanel.add(nationField);
        centerPanel.add(ageField);
        centerPanel.add(genderField);
        centerPanel.add(ratingField);
        centerPanel.add(commentArea);
        centerPanel.add(confirmBtn);
    }

    //MODIFES:this
    //EFFECTS:sets spring layout for all components
    private void setCenterLabelsLayout() {
        //nameLael
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(nameLabel),Spring.width(nameField)),Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        slayout.putConstraint(SpringLayout.WEST,nameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        slayout.putConstraint(SpringLayout.NORTH,nameLabel,20,SpringLayout.NORTH,centerPanel);

        //nationLabel
        slayout.putConstraint(SpringLayout.EAST,nationLabel,0,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,nationLabel,40,SpringLayout.NORTH,nameLabel);

        //ageLabel
        slayout.putConstraint(SpringLayout.EAST,ageLabel,0,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,ageLabel,80,SpringLayout.NORTH,nameLabel);

        //genderLabel
        slayout.putConstraint(SpringLayout.EAST,genderLabel,0,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,genderLabel,120,SpringLayout.NORTH,nameLabel);

        //ratingLabel
        slayout.putConstraint(SpringLayout.EAST,ratingLabel,0,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,ratingLabel,160,SpringLayout.NORTH,nameLabel);

        //commentLabel
        slayout.putConstraint(SpringLayout.EAST,commentLabel,0,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,commentLabel,200,SpringLayout.NORTH,nameLabel);

        //confirmBtn
        slayout.putConstraint(SpringLayout.WEST,confirmBtn,50,SpringLayout.EAST,commentLabel);
        slayout.putConstraint(SpringLayout.NORTH,confirmBtn,220,SpringLayout.NORTH,commentLabel);
    }

    //MODIFES:this
    //EFFECTS: sets spring layout for all input components.
    private void setCenterInputLayout() {
        //nameField
        slayout.putConstraint(SpringLayout.WEST,nameField,20,SpringLayout.EAST,nameLabel);
        slayout.putConstraint(SpringLayout.NORTH,nameField,-4,SpringLayout.NORTH,nameLabel);

        //nationField
        slayout.putConstraint(SpringLayout.WEST,nationField,20,SpringLayout.EAST,nationLabel);
        slayout.putConstraint(SpringLayout.NORTH,nationField,-4,SpringLayout.NORTH,nationLabel);

        //ageField
        slayout.putConstraint(SpringLayout.WEST,ageField,20,SpringLayout.EAST,ageLabel);
        slayout.putConstraint(SpringLayout.NORTH,ageField,-4,SpringLayout.NORTH,ageLabel);

        //genderField
        slayout.putConstraint(SpringLayout.WEST,genderField,20,SpringLayout.EAST,genderLabel);
        slayout.putConstraint(SpringLayout.NORTH,genderField,-4,SpringLayout.NORTH,genderLabel);

        //ratingField
        slayout.putConstraint(SpringLayout.WEST,ratingField,20,SpringLayout.EAST,ratingLabel);
        slayout.putConstraint(SpringLayout.NORTH,ratingField,-4,SpringLayout.NORTH,ratingLabel);

        //commentArea
        slayout.putConstraint(SpringLayout.WEST,commentArea,20,SpringLayout.EAST,commentLabel);
        slayout.putConstraint(SpringLayout.NORTH,commentArea,-4,SpringLayout.NORTH,commentLabel);
    }

    //EFFECTS:create and return a person object using the user input.
    public Person buildPerson() {
        Person person = new Person();
        person.setName(nameField.getText());
        person.setNationality(nationField.getText());
        person.setAge(Integer.valueOf(ageField.getText()));
        person.setGender(genderField.getText());
        person.setRating(Double.valueOf(ratingField.getText()));
        person.setComment(commentArea.getText());
        return person;
    }
}

