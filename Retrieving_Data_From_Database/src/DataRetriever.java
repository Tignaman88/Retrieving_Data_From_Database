import java.sql.*;
import java.util.Objects;

public class DataRetriever {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

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

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM students");
    }

    public void retrieveDataByName(String name) throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {

            if (Objects.equals(resultSet.getString("name"), name)) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println(resultSet.getInt("overall_score"));
            }

        }

    }

}
