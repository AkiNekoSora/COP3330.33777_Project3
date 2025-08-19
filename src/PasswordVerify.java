import java.util.Scanner;

/*
 * This class is used to create a user with a username and password. Once a new object  is attempted to
 * be created and checks to see if the username and password are valid or not. If it does not adhere to
 * the rules then it throws an exception and asks the user to input a new username or password until it
 * adheres to all rules. Once it does, it initializes each to the object and is added to the array in UserCreationMain.
 * This class overrides the toString method, has all the Getters and Setters, has a method that checks the username
 * against all the rules and one for the password too. There are also a few other methods used by the testing
 * methods. Lastly, there is a method that lists all the rules.
 */

public class PasswordVerify {
    Scanner scnr = new Scanner(System.in);
    String username;
    String password;

    public PasswordVerify(String username, String password, PasswordVerify[] allUsers) { //constructor
        // This while loop is used to continue asking the user for a valid username until they enter a valid
        // one. It will send an error message stating what they missed before asking them to try again.
        // Once a valid username is entered, it is initialized in the object.
        while (true) { //USERNAME
            // This try/catch it used to catch any errors, if a valid username is entered then it
            // breaks the while loop
            try {
                isValidUsername(username, password, allUsers);
                this.username = username;
                System.out.println("Username verified successfully!\n");
                break;
            } catch (PasswordVerifyException e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter a new valid username: ");
                username = scnr.nextLine();
                System.out.println();
            }
        }

        // This while loop is used to continue asking the user for a valid password until they enter a valid
        // one. It will send an error message stating what they missed before asking them to try again.
        // Once a valid password is entered, it is initialized in the object.
        while (true) { //PASSWORD

            // This try/catch it used to catch any errors, if a valid password is entered then it
            // breaks the while loop
            try {
                isValidPassword(username, password);
                this.password = password;
                System.out.println("Password verified successfully!\n");
                break;
            } catch (PasswordVerifyException e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter a new valid password: ");
                password = scnr.nextLine();
                System.out.println();
            }
        }

    }

    // Overrides the toString method, used to output all the information.
    // Changed to match the healer facts and make it a bit different.
    // Returns a string to output!
    @Override
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\n";
    }

    // The Getters!
    // Both return Strings!
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}

    // The Setters!
    // Both have void return types because they just update a variable and output a message.
    public void setUsername(String username) {
        System.out.println(this.username + " username changed to " + username);
        this.username = username;
    } public void setPassword(String password) {
        this.password = password;
        System.out.println("Password has been successfully changed!");
    }

    /*
     * This method checks if a username is valid. It takes in the username, password, and Array that is used
     * in the main class. The Array is needed to see if the username has already been used or not.
     * It then checks all possible rules to make sure that they are all valid. If any of the rules are activated
     * they throw a PasswordVerifyException with a number that calls a message to print in the Exception.
     * The return type is void since it either calls an Exception or is just ignored.
     */
    public static void isValidUsername(String username, String password, PasswordVerify[] allUsers) throws PasswordVerifyException {
        //These if statements check each rule to see if they have been activated or not.
        if (username == null || username.isEmpty()) {
            throw new PasswordVerifyException(username, password, 1);  //NO FIELDS BLANK
        }
        //Checks to see if the username has "meow"
        if (!username.contains("meow")) {  //USERNAME MUST CONTAIN "MEOW"
            throw new PasswordVerifyException(username, password, 2);
        }
        //This calls a method that count the char given to see if it is at least 2
        if ((!(checkAmountOfChar(username, '^') >= 2)) ||
                (!username.contains("-"))) {  //USERNAME MUST CONTAIN "^-^" (Either together or apart)
            throw new PasswordVerifyException(username, password, 3);
        }
        //Checks to see if the username has any spaces.
        if (username.contains(" ")) {  //USERNAME MUST NOT CONTAIN SPACES
            throw new PasswordVerifyException(username, password, 4);
        }
        //This calls a method that goes through the Array to see if the username has been taken or not.
        if (checkIfUsernameIsDuplicate (username, allUsers)) {  //USERNAME ALREADY TAKEN
            throw new PasswordVerifyException(username, password, 5);
        }
    }

    /*
     * This method checks if a password is valid. It takes in the password and the username that is tied
     * to the password. The username is needed to check the password against it to verify if the username is in
     * the password. It then checks all possible rules to make sure that they are all valid. If any of the rules
     * are activated they throw a PasswordVerifyException with a number that calls a message to print in the Exception.
     * The return type is void since it either calls an Exception or is just ignored.
     */
    public static void isValidPassword(String username, String password) throws PasswordVerifyException {
        //These if statements check each rule to see if they have been activated or not.

        //Checks to see if the password is null or empty.
        if (password == null || password.isEmpty()) {  //NO FIELDS BLANK
            throw new PasswordVerifyException(username, password, 1);
        }
        //checks to see if the password is less than 12 or longer than 20.
        if (password.length() < 12 || password.length() > 20) {  //PASSWORD IS TOO LONG OR SHORT (12 - 20 characters)
            throw new PasswordVerifyException(username, password, 6);
        }
        //This turns the password into lowercase letters to check to see if "c, a, or t" are in the password.
        if ((password.toLowerCase().contains("c")) || (password.toLowerCase().contains("a")) ||
                (password.toLowerCase().contains("t"))){  //PASSWORD MUST NOT HAVE "C", "A", OR "T"
            throw new PasswordVerifyException(username, password, 7);
        }
        //This calls a method that checks to see if there are any consecutive characters.
        if (checkIfConsecutiveCharacter(password)) {  //PASSWORD CANNOT HAVE 2 CONSECUTIVE CHARACTERS
            throw new PasswordVerifyException(username, password, 8);
        }
        //Checks to see if the username is in the password.
        if (password.contains(username)) {  //PASSWORD MATCHES USERNAME
            throw new PasswordVerifyException(username, password, 9);
        }
        //Checks to see if there are spaces in the password.
        if (password.contains(" ")) {  //PASSWORD MUST NOT CONTAIN SPACES
            throw new PasswordVerifyException(username, password, 10);
        }
    }

    /*
     * This method is called by isValidUsername()
     * This method takes in the username and character and counts to see how many times the char appears.
     * Return type is int to return the amount of times the char shows up. It needs to have at least 2 "^"
     */
    public static int checkAmountOfChar(String username, char character) {
        int count = 0;
        // This for loop uses the length of the username and checks to see if the char matches the char at each
        // iteration. Using the if statement to add to the count if it matches.
        for (int i = 0; i < username.length(); i++) {
            // Checks to see if the Char at "i" matches the char provided. If it does then it adds to the count.
            if (username.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }

    /*
     * This method is called by isValidUsername()
     * This method takes in the username and the Array to check through the length of the array to see if
     * the username matches any of the previous usernames. If it is "null" that means it is the first one
     * so it automatically returns false since there is nothing to compare to. If the username matches
     * one of the previous usernames than it returns true which calls the Exception if the method that
     * called this.
     * The return type is boolean. Returning true if they find a matching username and false if there are
     * none found or if the Array is empty.
     */
    public static boolean checkIfUsernameIsDuplicate(String username, PasswordVerify[] allUsers) {
        // This for loop goes through the Array checking to see if the username matches any of the usernames
        // in the array.
        for (PasswordVerify allUser : allUsers) {
            // These if/else statements check to see if the Array or the username is null and there is a
            // matching username, returning true if they do. If any are false the method returns false.
            if ((allUser != null) &&
                    (allUser.getUsername() != null) &&
                    (allUser.getUsername().equals(username))) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method is called by isValidPassword()
     * This method takes in a password and checks to see if the char at int "i" matches the char at the following
     * location. If it does, it returns true stating that yes there is a duplicate letter. If no chars are
     * duplicated then it returns false.
     * Return type is boolean. Returning true if it finds a consecutive char and false if it does not.
     */
    public static boolean checkIfConsecutiveCharacter(String password) {
        // This for loop goes through the entire password checking to see if the char at location "i" matches
        // the following char. If it matches then it returns true. Else it returns false.
        for (int i = 0; i < password.length() - 1; i++) {
            // Checks to see if the char at "i" matches the next char.
            if (password.charAt(i) == password.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method is the list of rules that prints all the rules, for both Username and Password when called.
     * Return type is void since nothing is returned. Things are just printed.
     */
    public static void userRules (){
        String rules = """
            --------------------------------------------------
            Username rules:
                Must include the word "meow"
                Must contain "^-^". Either all together or separated by other characters.
                Must not contain spaces.
                Must not match another user's username.
            --------------------------------------------------
            Password rules:
                Must be between 12 and 20 characters.
                Must not have the letters "c", "a" or "t".
                Must not have the same character twice in a row.
                Must not match your username.
                Must not contain spaces.
            --------------------------------------------------

            """;

        System.out.println(rules);
    }
}
