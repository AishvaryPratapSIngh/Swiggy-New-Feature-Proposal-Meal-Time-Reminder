import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MealTimeReminder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for meal times
        System.out.println("Welcome to Meal Time Reminder!");
        System.out.println("Please enter the time for breakfast (HH:MM format):");
        String breakfastTime = scanner.nextLine();
        System.out.println("Please enter the time for lunch (HH:MM format):");
        String lunchTime = scanner.nextLine();
        System.out.println("Please enter the time for dinner (HH:MM format):");
        String dinnerTime = scanner.nextLine();

        // Convert meal times to milliseconds
        long breakfastDelay = getTimeDelay(breakfastTime);
        long lunchDelay = getTimeDelay(lunchTime);
        long dinnerDelay = getTimeDelay(dinnerTime);

        // Schedule reminders for each meal time
        scheduleReminder("Breakfast", breakfastDelay);
        scheduleReminder("Lunch", lunchDelay);
        scheduleReminder("Dinner", dinnerDelay);

        System.out.println("Meal Time Reminders scheduled successfully!");
    }

    // Method to convert time to milliseconds
    public static long getTimeDelay(String time) {
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new IllegalArgumentException("Invalid time format or values.");
            }
            return ((hours * 60) + minutes) * 60 * 1000; // Convert to milliseconds
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Error: Invalid time format or values. Please use HH:MM format and ensure valid values.");
            System.exit(1); // Exit the program if an error occurs
            return 0; // This line will never be reached, added to satisfy compiler
        }
    }

    // Method to schedule reminders
    public static void scheduleReminder(String meal, long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("It's time for " + meal + "! Don't forget to order your meal.");
            }
        }, delay);
    }
}
