/*
Opgave: Login system
Et array til brugernavne.
Et array til adgangskoder.

Brugerinput:
Bed brugeren om at indtaste deres brugernavn.
Bed brugeren om at indtaste deres adgangskode.

Validering:
Check om brugernavnet findes i array.
Hvis brugernavnet findes, valider adgangskoden.
Brugeren har maksimalt tre forsøg til at indtaste den korrekte adgangskode.

Metoder:
En metode til at validere brugernavn og adgangskode.
En metode til at håndtere login forsøg.

Output:
Hvis login er succesfuld, vis en velkomstbesked.
Hvis brugeren mislykkes tre gange, vis en besked om, at kontoen er låst.

 */

import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {

        // Initialises data used in the program for testing
        short maxLoginAttempts = 2;
        short nameLoginAttempts;
        short passwordLoginAttempts;
        boolean nameDataMatch;
        boolean passwordDataMatch;
        int userKey;
        String userVal;

        // Our "Database"
        String[] userNameDB = new String[4];
        userNameDB[0] = "John";
        userNameDB[1] = "Jack";
        userNameDB[2] = "Jones";
        userNameDB[3] = "Jimmy";

        String[] passwordDB = new String[4];
        passwordDB[0] = "0000";
        passwordDB[1] = "1111";
        passwordDB[2] = "2222";
        passwordDB[3] = "3333";


        // Start of program
        startAppMessage();

        for (nameLoginAttempts = 0; nameLoginAttempts <= maxLoginAttempts; nameLoginAttempts++) {
            String userName = userNameInput();
            nameDataMatch = dataIsInDataBse(userNameDB, userName);

            if (nameDataMatch) {
                foundMatchMessage();
                userKey = getDataBaseID(userNameDB, userName);

                for (passwordLoginAttempts = 0; passwordLoginAttempts <= maxLoginAttempts; passwordLoginAttempts++){

                    if (userName.equals(userNameDB[userKey])) {
                        String userPassword = userPasswordInput();
                        passwordDataMatch = dataIsInDataBse(passwordDB, userPassword);

                        if (passwordDataMatch && userPassword.equals(passwordDB[userKey])) {
                            foundMatchMessage();
                            userVal = getDataBaseValue(passwordDB, userKey);

                            if (userName.equals(userNameDB[userKey]) && userPassword.equals(passwordDB[userKey])) {
                                loginSuccessMessage(userName, userKey, userPassword, userVal);
                                System.exit(0);
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

    public static String userNameInput() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your Username");
        return userInput.next();
    }// Gets a String as input for username

    public static String userPasswordInput() {
        Scanner userInput = new Scanner(System.in);
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