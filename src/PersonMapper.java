import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper {
    private Connection connection;

    public PersonMapper() {
        // Инициализация подключения к базе данных
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO persons (last_name, first_name, number_of_dependents) VALUES (?, ?, ?)");
            statement.setString(1, person.getLastName());
            statement.setString(2, person.getFirstName());
            statement.setInt(3, person.getNumberOfDependents());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE persons SET last_name=?, first_name=? WHERE numberOfDependens=?");
            statement.setString(1, person.getLastName());
            statement.setString(2, person.getFirstName());
            statement.setInt(3, person.getNumberOfDependents());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persons WHERE numberOfDependens=?");
            statement.setInt(1, person.getNumberOfDependents());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person findByNumberOfDependens(int numberOfDependens) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons WHERE numberOfDependens=?");
            statement.setInt(1, numberOfDependens);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPerson(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Person mapResultSetToPerson(ResultSet resultSet) throws SQLException {
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        int numberOfDependents = resultSet.getInt("number_of_dependents");
        return new Person(lastName, firstName, numberOfDependents);
    }
}

