package ui.console;

import model.DatingDiary;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Represents a dating diary application
public class DiaryApp {
    private static final String JSON_STORE = "./data/datingdiary.json";
    private Scanner input;
    private DatingDiary diary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: initialize the diary application and runs application,
    // throws FileNotFoundException if JSON_STORE not found
    public DiaryApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDiary();
    }

    //EFFECTS:asks user if want to open diary from the file.
    //Yes then open Json file and return true; otherwise return false.
    private boolean promptLoading() {
        boolean load = false;
        System.out.println("Would you like to load the diary from the file? Yes to open; No to continue ");
        String command = input.next();
        if (command.equalsIgnoreCase("Yes")) {
            loadDatingDiary();
            load = true;
        }
        return load;
    }

    //MODIFIES:this
    //EFFECTS:prompts for the owner name, and constructs a dating diary with this ownername
    private void init() {
        System.out.println("\nCreating a diary");
        System.out.print("\nEnter the owner name of the diary: ");
        String ownerName = input.next();
        diary = new DatingDiary(ownerName);
    }

    //MODIFIES: this
    //EFFECTS: processes user input.(This method was based off the runTeller() in the TellerApp class)
    private void runDiary() {
        boolean load = promptLoading();
        boolean keepGoing = true;
        String command;
        if (!load) {
            init();
        }

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                quitToPromptSave();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a person to my diary");
        System.out.println("\tp -> Print someone's all detail");
        System.out.println("\tr -> Rate someone");
        System.out.println("\tc -> Comment someone");
        System.out.println("\tq -> Quit");
        System.out.println("\tp-all -> Print every person");
    }

    //MODIFIES: this
    //EFFECTS: process user command, yes then save diary.
    private void processQuitCommand(String command) {
        if (command.equals("Yes")) {
            saveDatingDiary();
        }
    }

    //EFFECTS: prompts user whether want to save this diary to a file.
    private void quitToPromptSave() {
        System.out.println("Do you want to save this diary to a file? Yes to save; No to exit");
        String command = input.next();
        processQuitCommand(command);
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAddPerson();
                break;
            case "r":
                doRate();
                break;
            case "c":
                doComment();
                break;
            case "p":
                printThisPersonDetail();
                break;
            case "q":
                quitToPromptSave();
                break;
            case "p-all":
                printAll();
                break;
            default:
                System.out.println("Please enter a valid selection:  ");
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts user for name, nationality,age and gender
    // of this person, and then add this person to people list.
    private void doAddPerson() {
        System.out.print("Please enter the name of the person: ");
        String name = input.next();
        System.out.print("Nationality: ");
        String nationality = input.next();
        System.out.print("Age(must be a non-negative number): ");
        int age = input.nextInt();
        System.out.print("Gender(Female/Male): ");
        String gender = input.next();
        Person p = new Person(name, nationality, age, gender);
        diary.addPerson(p);
        System.out.println("Added successfully!");
    }

    //EFFECTS:conducts see selected person's detail information function
    private void printThisPersonDetail() {
        if (diary.getPeople().isEmpty()) {
            System.out.println("There is no one in your diary, choose to add a person first.");
        } else {
            System.out.print("Enter the name of the person you want to see details: ");
            Person chosedPerson = diary.selectPersonByName(input.next());
            if (chosedPerson == null) {
                System.out.println("This person is not in your diary.");
                printThisPersonDetail();
            } else {
                System.out.println(chosedPerson);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: if people list is empty,print choose to add person first; otherwise
    // prompts for person's name and rating,if this person not in the list of
    // people,print not in your diary; otherwise add rating for this person.
    private void doRate() {
        if (diary.getPeople().isEmpty()) {
            System.out.println("There is no one in your diary, choose to add a person first.");
        } else {
            System.out.print("Enter the name of the person you want to rate: ");
            String chosedPerson = input.next();
            System.out.print("Enter your rating for " + chosedPerson + " from -10.0 to 10.0 (numbers only): ");
            double rating = input.nextDouble();
            if (!diary.selectAddRate(chosedPerson, rating)) {
                System.out.println("This person is not in your diary.");
                doRate();
            } else {
                diary.selectAddRate(chosedPerson, rating);
                System.out.println("Rated successfully!");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: if people list is empty,print choose to add person first; otherwise
    // prompts for person's name and comment,if this person not in the list of
    // people,print not in your diary;otherwise add comment for this person.
    private void doComment() {
        if (diary.getPeople().isEmpty()) {
            System.out.println("There is no one in your diary, choose to add a person first.");
        } else {
            System.out.print("Enter the name of the person you want to comment: ");
            String chosedPerson = input.next();
            System.out.println("Enter your comment for " + chosedPerson + ": ");
            String comment = input.next();
            if (!diary.selectAddComment(chosedPerson, comment)) {
                System.out.println("This person is not in your diary.");
                doComment();
            } else {
                diary.selectAddComment(chosedPerson, comment);
                System.out.println("Commented successfully!");
            }
        }
    }

    //EFFECTS: prints details of every person in the diary
    private void printAll() {
        List<Person> people = diary.getPeople();
        if (people.isEmpty()) {
            System.out.println("There is no one in your diary, choose to add a person first.");
        } else {
            for (Person p : people) {
                System.out.println(p);
            }
        }
    }

    // EFFECTS: saves the dating diary to file
    private void saveDatingDiary() {
        try {
            jsonWriter.open();
            jsonWriter.write(diary);
            jsonWriter.close();
            System.out.println("Saved " + diary.getOwnerName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads dating diary from file
    protected void loadDatingDiary() {
        try {
            diary = jsonReader.read();
            System.out.println("Loaded " + diary.getOwnerName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
