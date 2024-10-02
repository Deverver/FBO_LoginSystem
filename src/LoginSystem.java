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

Tips og Tricks:
Brug Scanner klassen til at indlæse input fra brugeren.
Brug en for-loop til at tillade op til tre login forsøg.
Brug Arrays.equals metode til at sammenligne adgangskoder.
Sørg for at adskille logikken i forskellige metoder for-
at gøre koden mere struktureret og lettere at vedligeholde.
 */

import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {

        // Initialises data used in the program for testing
        boolean appIsRunning = true;
        short loginAttempts = 0;
        boolean nameDataMatch;
        boolean passwordDataMatch;
        int userKey;
        String userVal;


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

        do {
            String userName = userNameInput();
            nameDataMatch = dataIsValid(userNameDB, userName);
            if (nameDataMatch) {
                userKey = getDataBaseID(userNameDB, userName);
                String userPassword = userPasswordInput();
                passwordDataMatch = dataIsValid(passwordDB, userPassword);
                if (passwordDataMatch) {
                    userVal = getDataBaseValue(passwordDB, userKey);
                    loginSuccessMessage(userName, userKey, userPassword, userVal);
                }
            }

            loginAttempts++;
            if (loginAttempts == 3){
                closeAppMessage();
                appIsRunning = false;
            }
        } while (appIsRunning);



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
    }// gets a String as input for username

    public static boolean dataIsValid(String[] dataBase, String userInput) {
        System.out.println("Checking DataBase...");
        for (String index : dataBase) {
            if (index.equals(userInput)) {
                System.out.println("Match Found");
                return true;
            }
        }
        System.out.println("Match Not Found");
        return false;
    }// checks if input data can be found in array

    public static int getDataBaseID(String[] dataBase, String userKey) {
        int foundID = -1;
        System.out.println("Getting ID...");
        for (int i = 0; i < dataBase.length; i++) {
            if (dataBase[i].equals(userKey)) {
                foundID = i;
            }
        }
        return foundID;
    }// getDataBaseID End

    public static String userPasswordInput() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your Password");
        return userInput.next();
    }// userPasswordInput End

    public static String getDataBaseValue(String[] dataBase, int keyIndex) {
        return dataBase[keyIndex];
    }


}// Class End