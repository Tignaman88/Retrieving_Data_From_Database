import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataRetriever connection = new DataRetriever();
        connection.connectToDatabase();
        connection.retrieveData();
//        connection.retrieveDataByName("Jane");
//        connection.retrieveDataByLastName("Smith");
        Scanner userInput = new Scanner(System.in);
        String userInputName;
        userInputName = connection.retrieveDataByNameWithUserInput(userInput);
        connection.setUserInputName(userInputName);

//        userInputLastName = userInputLastName.substring(0, 1).toUpperCase() + userInputLastName.substring(1).toLowerCase();
 //       connection.retrieveDataByLastName(userInputLastName);
    }
}
