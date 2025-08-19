import java.util.Scanner;

/*
 * This class is the main class that takes in the user inputs and adds each of the
 * users to the array, and continues to iterate through the array until all 4 users are
 * created with valid username and password. Uses a try/catch to catch any errors and throws
 * an error message specifying what is wrong and asking the user to try again until they enter
 * a valid username or password.
 */

public class UserCreationMain {
    public static void main(String[] args) throws PasswordVerifyException {
        Scanner scnr = new Scanner(System.in);
        PasswordVerify[] allUsers = new PasswordVerify[4];

        // This for loop is used to iterate through the array to add a valid username and password for each user.
        for (int i = 0; i < allUsers.length; i++) {
            String username;
            String password;

            System.out.println("Hello new user!\n");
            PasswordVerify.userRules();

            System.out.print("Please enter a valid username: ");
            username = scnr.nextLine();
            System.out.println();
            System.out.print("Please enter a valid password: ");
            password = scnr.nextLine();

            System.out.println();

            allUsers[i] = new PasswordVerify(username, password, allUsers);
            System.out.println("User " + allUsers[i].getUsername() + " created!\n\n");
        }

        System.out.println("Printing all users: ");
        // This for loop is used to iterate through all the users printing them using the toString method
        // in the PasswordVerify class.
        for (int i = 0; i < allUsers.length; i++) {
            System.out.println(allUsers[i]);
        }

        scnr.close();
    }

}
