import java.io.*;
import java.util.*;

public class MentalWellnessJournal { 

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        User user = new User(sc.nextLine());
        String filename = user.getUsername() + "_journal.txt";
	//Abhila.txt 
	 // Using the sample file name - Sample.txt
        JournalManager manager = new JournalManager();
        manager.loadFromFile(filename);

        int choice;
        do {
            System.out.println("\n--- Mental Wellness Journal ---");
            System.out.println("1. Add entry");
            System.out.println("2. View all entries");
            System.out.println("3. Mood statistics");
            System.out.println("4. Generate report");
            System.out.println("5. Delete last entry");
            System.out.println("6. Save and exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Mood: ");
                    String mood = sc.nextLine();
                    System.out.print("Note: ");
                    String note = sc.nextLine();
                    manager.addEntry(new JournalEntry(new Date(), mood, note));
                    break;
                case 2:
                    manager.viewAll();
                    break;
		case 3:
                    manager.generateMoodStatistics();
                    break;
                case 4:
                    manager.generateReport();
                    break;
                case 5:
                    manager.deleteLastEntry();
                    break;
                case 6:
                    manager.saveToFile(filename);
                    System.out.println("Entries saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6); // Changed to 6 to match the menu

        sc.close();
    }
}