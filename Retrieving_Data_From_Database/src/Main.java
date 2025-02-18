import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataRetriever connection = new DataRetriever();
        connection.connectToDatabase();
        connection.retrieveData();
//        connection.retrieveDataByName("Jane");
//        connection.retrieveDataByLastName("Smith");

        // find a user to find a student with user input name
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the name of the student you'd like to find: ");

        while (true) {
            String userInputName = userInput.nextLine();
            ArrayList<Student> userInputNameList;
            connection.setUserInputName(userInputName);
            userInputNameList = connection.retrieveDataByNameWithUserInput(userInputName);
            if (!userInputNameList.isEmpty()) {
                System.out.println(userInputNameList);
                break;
            } else {
                System.out.println("Student not found, please try again: ");
            }

        }

        // find a user to find a student with user input last name
        System.out.println("Please enter the last name of the student you'd like to find: ");

        while (true) {
            String userInputLastName = userInput.nextLine();
            ArrayList<Student> userInputNameList;
            connection.setUserInputLastName(userInputLastName);
            userInputNameList = connection.retrieveDataByLastNameWithUserInput(userInputLastName);
            if (!userInputNameList.isEmpty()) {
                System.out.println(userInputNameList);
                break;
            } else {
                System.out.println("Student not found, please try again: ");
            }

        }

    }
}
