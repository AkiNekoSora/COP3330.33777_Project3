import java.util.Random;
/*
 * This method is a Password Verification Exception, that extends Exception that is called if a username or
 * password does not adhere to all the rules stated. It has a few constructors depending on what is calling it.
 * It also has a switch method that has a list of messages that will get output depending on the rule that
 * has been violated. (This was found during my googling, and it was so cool that I wanted to try it!!)
 * The main constructor takes in the username, password and the int of the condition violated. Using those
 * to print out the information to the user including the username or password that is the current problem.
 */

public class PasswordVerifyException extends Exception {

    // This method is the main constructor. It accepts the username, password and conditionViolated int which
    // uses the pintMessage method to output the specific error and the current password or username that is wrong.
    public PasswordVerifyException (String username, String password, int conditionViolated) {
        super("");
        System.out.println(printMessage(username, password, conditionViolated));
    }

    // This is another constructor used as a backup if nothing are not sent to the exception class.
    public PasswordVerifyException () {
        super();
    }

    /*
     * This method is the list of outputs that can be sent to the Exception to output to the user.
     * I used a switch case method to call the text based what is called by the isValidUsername() or
     * isValidPassword() methods. It accepts the username, password, and conditionViolated to use in
     * the error message sent to the user. It specifies the error, the string that is causing the error and
     * a possible fix they could try.
     * The return type is string because it returns the string message that is sent to the Exception method.
     * It returns an unknown error if none of the switch cases are called.
     */
    public String printMessage(String username, String password, int conditionViolated) {
        Random random = new Random();

        // A switch used to call a specific message that will be sent to the exception output. This method is
        // called by the isValidUsername and isValidPassword methods in the PasswordVerify class.
        switch (conditionViolated) {

        case 1: //NO FIELDS BLANK
            String blankResult = "";
            //This if/else is used to figure out which one is blank to decide final result.
            if (username == null || username.isEmpty()) {
                blankResult = "Username is blank: " + username + "\nPlease fix.";}
            else if(password == null || password.isEmpty()) {
                blankResult = "Password is blank: " + password + "\n Please fix.";}

            return ("Please do not leave any fields blank.\n" + blankResult );

        case 2: //USERNAME MUST CONTAIN "MEOW"
            return ("Username: " + username + " does not contain \"meow\".\n" +
                    "Possibly try: " + username + "meow");

        case 3: //USERNAME MUST CONTAIN "^-^" (Either together or apart)
            return ("Username: " + username + " does not contain \"^-^\".\n" +
                    "Possibly try: " + username + "^-^");

        case 4: //USERNAME MUST NOT CONTAIN SPACES
            return ("Username: " + username + " contains spaces.\n" +
                    "Possibly try: " + username.replaceAll(" ", ""));

        case 5: //USERNAME ALREADY TAKEN
            //Adds a random number to the end of the username
            return ("Username: " + username + " has already been taken.\n" +
                    "Possibly try: " + username + random.nextInt(10, 500));

        case 6: //PASSWORD IS TOO LONG OR SHORT (12 - 20 characters)
            // This if/else is used to see if the password is too long or too short. It remakes the password
            // by either removing the rest or by repeating "12" to the end until it hits 12 digits
            if (password.length() > 20) {
                return ("Password: " + password + " is too long.\n" +
                        "Possibly try: " + password.substring(0, 20));
            }
            else if (password.length() < 12) {
                int lengthDifference = 12 - password.length();
                return ("Password: " + password + " is too short.\n" +
                        "Possibly try: " + password + "12".repeat(lengthDifference / 2));
            }

        case 7: //PASSWORD MUST NOT HAVE "C", "A", OR "T"
            //These replace alls take the "c, a or t" or "C, A, or T" and replaces with the letter to the
            //right on the keyboard instead.
            return ("Password: " + password + " contains \"c\", \"a\" or \"t\".\n" +
                    "Possibly try: " + password.replaceAll("c", "v")
                                               .replaceAll("a", "s")
                                               .replaceAll("t", "y")
                                               .replaceAll("C", "V")
                                               .replaceAll("A", "S")
                                               .replaceAll("T", "Y"));

        case 8: //PASSWORD CANNOT HAVE 2 CONSECUTIVE CHARACTERS
            StringBuilder newNonDuplicatePassword = new StringBuilder(password);

            //This for loop goes through the length of the password, and uses the String builder to make a new
            //password that replaces the duplicate letter or number with the opposite randomly picked.
            for (int i = 1; i < password.length() - 1; i++) {
                //Checks to see if the character at "i" matches the next char.
                if (password.charAt(i) == password.charAt(i - 1)) {
                    //This if/else is used to see if the duplicated character is a number or a letter to
                    //replace it with the opposite. Using Rand int as a number or a char.
                    if (Character.isDigit(password.charAt(i))) {
                        newNonDuplicatePassword.setCharAt (i, (char) (random.nextInt('A', 'z' + 1)));
                    }
                    else {
                        newNonDuplicatePassword.setCharAt (i, (char) (random.nextInt(0, 10)));
                    }
                }
            }
            return ("Password: " + password + " contains the same character twice in a row.\n" +
                    "Possibly try: " + newNonDuplicatePassword);

        case 9: //PASSWORD MATCHES USERNAME
            StringBuilder newPassword = new StringBuilder();

            // This for loop goes through the password length and uses a String Builder to reverse the password,
            // adding the last letter to the start of the new password and continues going backwards through the
            // original password.
            for (int i = password.length(); i > 1; i--) {
                newPassword.append(password.charAt(i - 1));
            }
            return ("Password: " + password + " matches your username.\n" +
                    "Possibly try: " + newPassword);

        case 10: //PASSWORD MUST NOT CONTAIN SPACES
            // Replaces the spaces with nothing.
            return ("Password: " + password + " contains spaces.\n" +
                    "Possibly try: " + password.replaceAll(" ", ""));

        default: //DEFAULT. UNKNOWN ISSUE.
            return ("Unknown Username/Password Error! Please try again.");

        }

    }

}
