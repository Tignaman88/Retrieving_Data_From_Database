import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DataRetriever {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String userInputName;
    String userInputLastName;
    ArrayList<String> studentsName = new ArrayList<>();

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

    public void retrieveData() throws SQLException {

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT * FROM students");
    }

    public void retrieveDataByName(String name) throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {

            if (Objects.equals(resultSet.getString("name"), name)) {
                System.out.println(resultSet.getInt("idstudents"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println(resultSet.getInt("overall_score"));
            }

        }

    }

    public void retrieveDataByLastName(String last_name) throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {

            if (Objects.equals(resultSet.getString("last_name"), last_name)) {
                System.out.println(resultSet.getInt("idstudents"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println(resultSet.getInt("overall_score"));
            }
        }
    }

    public String retrieveDataByNameWithUserInput(Scanner inputUser) throws SQLException {

        System.out.println("Please enter the name of the student you'd like to find: ");
        System.out.println("Please enter done when you're finished: ");

//        resultSet = statement.executeQuery("SELECT * FROM students");

        boolean loop = true;
//        while (studentsName.isEmpty()) {
//
//            while (resultSet.next()) {
//
//                if (Objects.equals(resultSet.getString("name"), userInputName)) {
//                    studentsName.add(String.valueOf(resultSet.getInt("idstudents")));
//                    studentsName.add(resultSet.getString("name"));
//                    studentsName.add(resultSet.getString("last_name"));
//                    studentsName.add(String.valueOf(resultSet.getInt("overall_score")));
//                } else {
//                    System.out.println("User not found, please try again: ");
//                }
//
//
//
//            }

        try {
            PreparedStatement ps = null;
            String sql = "SELECT * FROM students ";
            sql += "WHERE name = ?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userInputName);
            resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                if (resultSet.next() && Objects.equals(resultSet.getString("name"), userInputName)) {
//
//                    System.out.println("Found the student");
//
//
//                    studentsName.add(String.valueOf(resultSet.getInt("idstudents")));
//                    studentsName.add(resultSet.getString("name"));
//                    studentsName.add(resultSet.getString("last_name"));
//                    studentsName.add(String.valueOf(resultSet.getInt("overall_score")));
//
//
//
//
//                } else {
//                    System.out.println("Did not find the student");
//                }
//
//            }

            while (loop) {
                userInputName = inputUser.nextLine();
                userInputName = userInputName.substring(0, 1).toUpperCase() + userInputName.substring(1).toLowerCase();
                if (userInputName.equals("done")) {
                    loop = false;
                } else {
                    while (resultSet.next()) {

                        if (Objects.equals(resultSet.getString("name"), userInputName)) {


                            studentsName.add(String.valueOf(resultSet.getInt("idstudents")));
                            studentsName.add(resultSet.getString("name"));
                            studentsName.add(resultSet.getString("last_name"));
                            studentsName.add(String.valueOf(resultSet.getInt("overall_score")));
                        }
                    }
                }
            }
            }    catch (SQLException e) {
            // handle exception
        } finally {
            // cleanup, close connections, etc.
            for (String element : studentsName) {
                System.out.print(element + " ");
            }
        }

        return userInputName;
    }



    }

