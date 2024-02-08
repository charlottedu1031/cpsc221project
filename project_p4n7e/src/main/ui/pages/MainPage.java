package ui.pages;

import model.DatingDiary;
import model.Person;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;
import ui.pageslistener.MainListener;
import ui.pageslistener.PromptListener;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//Represents the main page with the add and delete buttons and a panel to //display all the people.
public class MainPage extends JFrame implements Writable {
    private static final String ownerName = "Cat";
    private static final String JSON_STORE = "./data/datingdiary.json";
    private static final String addString = "Add";
    private static final String deleteString = "Delete";
    private static final String loadString = "Would you like to load the diary from the file?";
    private DefaultListModel<String> listModel;
    private JButton addBtn;
    private JButton deleteBtn;
    private JList<String> jlist;
    private JPanel contentPane;
    private DatingDiary diary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS:initialize the main page and run it.
    public MainPage(String title) {
        super(title);

        runMainPage();
        init();

        // set up frame
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new PromptListener(this));
        setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS:display splash.jpg in a splash screen before app starts.Prompt user with the option to load data.
    public void runMainPage() {
        splashScreen();
        listModel = new DefaultListModel<>();
        promptLoad();
    }

    //EFFECTS:creates the splash effect.
    public void splashScreen() {
        WindowSplashFrame frame = new WindowSplashFrame();
        frame.prepareSplash();
        frame.startSplash();
        try {
            Thread.currentThread().sleep(700);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.stopSplash();
    }

    //MODIFIES:this
    //EFFECTS: Prompt user if load data from json file. If yes,load diary from json file.Otherwise,create a new diary.
    public void promptLoad() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        int option = JOptionPane.showConfirmDialog(this, loadString, "Load", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            loadDatingDiary();
        } else if (option == 1) {
            diary = new DatingDiary(ownerName);
        }
    }

    // EFFECTS: saves the dating diary to file
    public void saveDatingDiary() {
        try {
            jsonWriter.open();
            jsonWriter.write(diary);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads dating diary from file
    public void loadDatingDiary() {
        try {
            diary = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        List<Person> people = diary.getPeople();
        for (Person p : people) {
            listModel.addElement(p.toString());
        }
    }

    //MODIFIES:this
    //EFFECTS:initialize all components and add listener to 2 buttons and the window.
    public void init() {
        //panel
        contentPane = new JPanel();
        setContentPane(contentPane);

        //scrollpane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 500));
        contentPane.add(scrollPane);

        //jlist
        jlist = new JList<>(listModel);
        jlist.setPreferredSize(new Dimension(800, 600));
        scrollPane.setViewportView(jlist);

        //add button
        addBtn = new JButton(addString);
        contentPane.add(addBtn, BorderLayout.PAGE_END);
        addBtn.addActionListener(new MainListener(this)
        );
        deleteBtn = new JButton(deleteString);
        contentPane.add(deleteBtn);
        deleteBtn.addActionListener(new MainListener(this)
        );
    }

    //MODIFIES:this
    //EFFECTS:add person to Jlist
    public void addPersontoJList(Person person) {
        listModel.addElement(person.toString());
    }

    //MODIFIES:this
    //EFFECTS:check the index of the line selected by user, then delete it.If no line is seleted, pop-up reminder msg.
    public void deletePerson() {
        if (jlist.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a line to delete");
        } else {
            diary.deletePerson(jlist.getSelectedIndex());
            listModel.removeElement(jlist.getSelectedValue());
        }
    }

    //EFFECTS:return the diary
    public DatingDiary getDiary() {
        return diary;
    }

    //EFFECTS: returns this daitng diary as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("Owner Name", ownerName);
        json.put("People", personToJson());
        return json;
    }

    // EFFECTS: returns people in this datingdiary as a JSON array
    private JSONArray personToJson() {
        JSONArray jsonArray = new JSONArray();
        List<Person> people = getDiary().getPeople();
        for (Person p : people) {
            jsonArray.put(p.toJson());
        }
        return new JSONArray();
    }
}
