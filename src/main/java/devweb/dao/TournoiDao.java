package devweb.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import devweb.entities.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiDao{

    public DataSource getDatasource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("pokheir");
        dataSource.setUser("root");
        dataSource.setPassword("");

        return dataSource;
    }

    public List<Tournoi> listTournois() {
        List<Tournoi> tournois = new ArrayList<>();
        try (Connection connection = getDatasource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tournois")) {
            while(resultSet.next()) {
                tournois.add(new Tournoi(
                        resultSet.getInt("idTournois"),
                        resultSet.getDate("date"),
                        resultSet.getInt("nombreInscrits"),
                        resultSet.getBoolean("classe")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournois;
    }

    public void addTournoi(Tournoi tournoi) {
        String query = "INSERT INTO tournois(date, nombreInscrits, classe) VALUES(?, 0, ?)";
        try (Connection connection = getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, tournoi.getDate());
            statement.setBoolean(2, tournoi.getClasse());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delTournoi(Integer idTournois) {
        String query = "DELETE FROM tournois WHERE idTournoi=? ";
        try (Connection connection = getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idTournois);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
