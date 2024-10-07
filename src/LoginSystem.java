/*
Key Changes:
Removed Redundant Checks:
Removed unnecessary username and password rechecks inside the loops.

Return After
Successful Login: Instead of break, a return is used to terminate the program after successful login, ensuring no further iterations occur.

Simplified Password Check:
Directly compare the password for the userIndex without searching the database again for the password.

Loop Simplification:
By removing unnecessary checks and returns, the logic becomes more linear and readable.
 */


import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {

        // Initialises data used in the program for testing
        Scanner userInput = new Scanner(System.in);
        short maxLoginAttempts = 2;
        short nameLoginAttempts;
        short passwordLoginAttempts;
        boolean isNameValid;
        boolean isPasswordValid;
        int userIndex;
        String userPasswordFromDB;


        // Our "Database"
        String[] userNameDB = {"John", "Jack", "Jones", "Jimmy"};
        String[] passwordDB = {"0000", "1111", "2222", "3333"};

        // Start of program
        startAppMessage();

        for (nameLoginAttempts = 0; nameLoginAttempts <= maxLoginAttempts; nameLoginAttempts++) {
            String userName = userNameInput(userInput);
            isNameValid = dataIsInDataBse(userNameDB, userName);

            if (isNameValid) {
                foundMatchMessage();
                userIndex = getDataBaseID(userNameDB, userName);

                for (passwordLoginAttempts = 0; passwordLoginAttempts <= maxLoginAttempts; passwordLoginAttempts++){

                    if (userName.equals(userNameDB[userIndex])) {
                        String userPassword = userPasswordInput(userInput);
                        isPasswordValid = dataIsInDataBse(passwordDB, userPassword);

                        if (isPasswordValid && userPassword.equals(passwordDB[userIndex])) {
                            foundMatchMessage();
                            userPasswordFromDB = getDataBaseValue(passwordDB, userIndex);

                            if (userName.equals(userNameDB[userIndex]) && userPassword.equals(passwordDB[userIndex])) {
                                loginSuccessMessage(userName, userIndex, userPassword, userPasswordFromDB);
                                return;// Exist out of loop and ends program if login is successful
                            }
                        } else {
                            foundNoMatchMessage(passwordLoginAttempts, maxLoginAttempts);
                        }
                    }
                }// end for loop
                closeAppMessage();

            } else {
                foundNoMatchMessage(nameLoginAttempts, maxLoginAttempts);
            }
        }// end for loop
        closeAppMessage();

    }// Main End

    public static void startAppMessage() {
        System.out.println("""
                
                Login System has started successfully
                *************************************""");
    }

    public static void closeAppMessage() {
        System.out.println("""
                Max login attempts reached
                Closing down...""");
        System.exit(0);
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

    public static String userNameInput(Scanner userInput) {
        System.out.println("Please enter your Username");
        return userInput.next();
    }// Gets a String as input for username

    public static String userPasswordInput(Scanner userInput) {
        System.out.println("Please enter your Password");
        return userInput.next();
    }// Gets a String as input for user password

    public static boolean dataIsInDataBse(String[] dataBase, String userInput) {
        System.out.println("Checking DataBase...");
        for (String index : dataBase) {
            if (index.equals(userInput)) {
                return true;
            }
        }
        return false;
    }// Checks if input data can be found in array

    public static int getDataBaseID(String[] dataBase, String userKey) {
        int foundID = -1;
        System.out.println("Getting ID...");
        for (int i = 0; i < dataBase.length; i++) {
            if (dataBase[i].equals(userKey)) {
                foundID = i;
            }
        }
        return foundID;
    }// Gets the index of string array based on string input

    public static String getDataBaseValue(String[] dataBase, int keyIndex) {
        return dataBase[keyIndex];
    }// Get the valure of array via index input


}// Class End