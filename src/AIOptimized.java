/*
Optimizations and Changes Made:
    Refactored Variable Declarations: Moved declarations closer to where they are used, removing unnecessary declarations.

    Scanner Management: Created a single Scanner object that is passed to input methods. This avoids potential resource leaks by reducing unnecessary Scanner creations.

    Login Logic Simplification: Simplified the nested loops and checks, reducing code duplication and making it more readable.

    Efficient Data Matching: Removed the dataIsInDataBse() method and directly incorporated the logic into getDataBaseID(). Now the program directly gets the index from the database instead of doing two separate searches (one for the username and one for the password).

    Graceful Program Termination: Instead of using System.exit(0) to terminate the program, I used return to exit gracefully after a successful login.

Possible Future Optimizations:
    You could use HashMap<String, String> to store the username-password pairs, which would allow constant-time lookup for both username and password validation.
    Add encryption to store passwords securely rather than storing them as plain text.

This refactoring improves readability, efficiency, and maintainability without changing the core logic of your program.
 */



import java.util.Scanner;

public class AIOptimized {
    public static void main(String[] args) {
        // Initial data setup
        short maxLoginAttempts = 2;
        String[] userNameDB = {"John", "Jack", "Jones", "Jimmy"};
        String[] passwordDB = {"0000", "1111", "2222", "3333"};

        // Create a single Scanner object for the entire program
        Scanner scanner = new Scanner(System.in);

        // Start of the program
        startAppMessage();

        for (short nameLoginAttempts = 0; nameLoginAttempts <= maxLoginAttempts; nameLoginAttempts++) {
            String userName = userNameInput(scanner);
            int userIndex = getDataBaseID(userNameDB, userName);

            if (userIndex != -1) {  // User found
                foundMatchMessage();

                for (short passwordLoginAttempts = 0; passwordLoginAttempts <= maxLoginAttempts; passwordLoginAttempts++) {
                    String userPassword = userPasswordInput(scanner);

                    if (userPassword.equals(passwordDB[userIndex])) {  // Correct password
                        foundMatchMessage();
                        loginSuccessMessage(userName, userIndex, userPassword, passwordDB[userIndex]);
                        return;  // Exit the program gracefully
                    } else {
                        foundNoMatchMessage(passwordLoginAttempts, maxLoginAttempts);
                    }
                }
                closeAppMessage();  // Too many password attempts
            } else {
                foundNoMatchMessage(nameLoginAttempts, maxLoginAttempts);
            }
        }

        closeAppMessage();  // Too many username attempts
    }

    public static void startAppMessage() {
        System.out.println("""
                
                Login System has started successfully
                *************************************""");
    }

    public static void closeAppMessage() {
        System.out.println("Max login attempts reached. Closing down...");
    }

    public static void foundNoMatchMessage(short loginAttempts, short maxLoginAttempts) {
        int attemptsLeft = maxLoginAttempts - loginAttempts;
        System.out.println("Match Not Found");
        System.out.println("You have: " + attemptsLeft + " login attempts remaining");
    }

    public static void foundMatchMessage() {
        System.out.println("Match Found");
        System.out.println("System proceeding...");
    }

    public static void loginSuccessMessage(String userName, int idKey, String userPassword, String userVal) {
        System.out.println("You have successfully logged in");
        System.out.println("Welcome user: " + userName);
        System.out.println("Your login code was: " + userPassword);
        System.out.println("Your ID in our database is: " + idKey);
        System.out.println("The code to access this account should be: " + userVal);
    }

    public static String userNameInput(Scanner scanner) {
        System.out.println("Please enter your Username");
        return scanner.next();
    }

    public static String userPasswordInput(Scanner scanner) {
        System.out.println("Please enter your Password");
        return scanner.next();
    }

    public static int getDataBaseID(String[] dataBase, String userKey) {
        for (int i = 0; i < dataBase.length; i++) {
            if (dataBase[i].equals(userKey)) {
                return i;
            }
        }
        return -1;  // Not found
    }
}
