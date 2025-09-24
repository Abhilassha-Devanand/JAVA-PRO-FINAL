import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


class JournalManager {

    private List<JournalEntry> entries = new ArrayList<>();

    public void addEntry(JournalEntry entry) {
        entries.add(entry);
    }

    public void viewAll() {
        if (entries.isEmpty()) {
            System.out.println("No journal entries yet.");
        } else {
            for (JournalEntry e : entries) {
                System.out.println(e);
            }
        }
    }

    public void deleteLastEntry() {
        if (!entries.isEmpty()) {
            entries.remove(entries.size() - 1);
            System.out.println("Last entry deleted.");
        } else {
            System.out.println("No entries to delete.");
        }
    }

    public void generateMoodStatistics() {
        if (entries.isEmpty()) {
            System.out.println("No entries to analyze.");
            return;
        }
        Map<String, Integer> moodCount = new HashMap<>();
        for (JournalEntry e : entries) {
            moodCount.put(e.getMood(), moodCount.getOrDefault(e.getMood(), 0) + 1);
        }
        System.out.println("\n--- Mood Statistics ---");
        for (String mood : moodCount.keySet()) {
            System.out.println(mood + ": " + moodCount.get(mood));
        }
    }

    public void generateReport() {
        System.out.println("\n===== Journal Report =====");
        viewAll();
        generateMoodStatistics();
        System.out.println("==========================");
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (JournalEntry e : entries) {
                writer.write(e.getDate().getTime() + ";" + e.getMood() + ";" + e.getNote());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Date date = new Date(Long.parseLong(parts[0].trim()));
                    String mood = parts[1].trim();
                    String note = parts[2].trim();
                    entries.add(new JournalEntry(date, mood, note));
                }
            }
        }
    }
}
