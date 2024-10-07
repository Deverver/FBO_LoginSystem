import java.util.Scanner;

public class GPTImprovedLoginSystem {
    public static void main(String[] args) {

        // Initialises data used in the program for testing
        Scanner userInput = new Scanner(System.in);
        short maxLoginAttempts = 2;
        short nameLoginAttempts, passwordLoginAttempts;
        int userIndex;

        // Our "Database"
        String[] userNameDB = {"John", "Jack", "Jones", "Jimmy"};
        String[] passwordDB = {"0000", "1111", "2222", "3333"};

        // Start of program
        startAppMessage();

        for (nameLoginAttempts = 0; nameLoginAttempts <= maxLoginAttempts; nameLoginAttempts++) {
            String userName = userNameInput(userInput);
            userIndex = getDataBaseID(userNameDB, userName);

            if (userIndex != -1) {  // Username found
                foundMatchMessage();

                for (passwordLoginAttempts = 0; passwordLoginAttempts <= maxLoginAttempts; passwordLoginAttempts++) {
                    String userPassword = userPasswordInput(userInput);

                    // Directly compare password with the one stored at the same index
                    if (userPassword.equals(passwordDB[userIndex])) {
                        loginSuccessMessage(userName, userIndex, userPassword, passwordDB[userIndex]);
                        return;  // Exit the program gracefully after successful login

                    } else {
                        foundNoMatchMessage(passwordLoginAttempts, maxLoginAttempts);
                    }

                }
                closeAppMessage();  // Too many password attempts, closing program

            } else {
                foundNoMatchMessage(nameLoginAttempts, maxLoginAttempts);
            }
        }
        closeAppMessage();  // Too many username attempts, closing program
    }// Main End

    public static void startAppMessage() {
        System.out.println("""
                
                Login System has started successfully
                *************************************""");
    }

    public static void closeAppMessage() {
        System.out.println("Max login attempts reached. Closing down...");
        System.exit(0);
    }

    public static void foundNoMatchMessage(short loginAttempts, short maxLoginAttempts) {
        int attemptsLeft = maxLoginAttempts - loginAttempts;
        System.out.println("Match Not Found");
        System.out.println("You have: " + attemptsLeft + " login attempts remaining");
    }

    public static void foundMatchMessage() {
        System.out.println("Match Found. System proceeding...");
    }

    public static void loginSuccessMessage(String userName, int idKey, String userPassword, String userVal) {
        System.out.println("You have successfully logged in");
        System.out.println("Welcome user: " + userName);
        System.out.println("Your login code was: " + userPassword);
        System.out.println("Your ID in our database is: " + idKey);
        System.out.println("The code to access this account should be: " + userVal);
    }

    public static String userNameInput(Scanner userInput) {
        System.out.println("Please enter your Username:");
        return userInput.next();
    }

    public static String userPasswordInput(Scanner userInput) {
        System.out.println("Please enter your Password:");
        return userInput.next();
    }

    public static int getDataBaseID(String[] dataBase, String userKey) {
        for (int i = 0; i < dataBase.length; i++) {
            if (dataBase[i].equals(userKey)) {
                return i;
            }
        }
        return -1;  // Username not found
    }


}// Class End