import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DataRetriever {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String userInputName;
    String userInputLastName;
    ArrayList<Student> studentsDescriptionList = new ArrayList<>();

    public void setUserInputName(String userInputName) {
        this.userInputName = userInputName;
    }

    public void setUserInputLastName(String userInputLastName) {
        this.userInputLastName = userInputLastName;
    }

    public void connectToDatabase() {

        try{
             connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/students",
                    "root",
                    "BankAppProject"
            );


            if (!connection.isClosed()) {
                System.out.println("Database connected successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Method to create an object for each student in database and add each student object to an Array list
     */
    public void retrieveData() throws SQLException {


        String idStudents;
        String name;
        String last_name;
        String overall_score;

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {
            idStudents = (String.valueOf(resultSet.getInt("idstudents")));
            name = (resultSet.getString("name"));
            last_name = (resultSet.getString("last_name"));
            overall_score = (String.valueOf(resultSet.getInt("overall_score")));
            Student student = new Student(idStudents, name, last_name, overall_score);
            studentsDescriptionList.add(student);
        }


    }


    /*
    Method to find a student in a database by asking the user an input based on name
     */
    public ArrayList<Student> retrieveDataByNameWithUserInput(String inputUser) throws SQLException {

        inputUser = inputUser.substring(0, 1).toUpperCase() + userInputName.substring(1).toLowerCase();


        resultSet = statement.executeQuery("SELECT * FROM students");

        ArrayList<Student> studentUserInputList = new ArrayList<>();
        for (Student element : studentsDescriptionList) {
            if (Objects.equals(inputUser, element.name)) {
                studentUserInputList.add(element);
            }
        }


        return studentUserInputList;
    }

    /*
    Method to find a student in a database by asking the user an input based on last name
     */
    public ArrayList<Student> retrieveDataByLastNameWithUserInput(String inputUser) throws SQLException {

        inputUser = inputUser.substring(0, 1).toUpperCase() + userInputLastName.substring(1).toLowerCase();


        resultSet = statement.executeQuery("SELECT * FROM students");

        ArrayList<Student> studentUserInputList = new ArrayList<>();
        for (Student element : studentsDescriptionList) {
            if (Objects.equals(inputUser, element.last_name)) {
                studentUserInputList.add(element);
            }
        }


        return studentUserInputList;
    }
}


