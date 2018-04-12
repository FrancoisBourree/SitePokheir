package devweb.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import devweb.entities.Article;
import devweb.entities.Tournoi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void addTournoi(Tournoi tournoi) {
        String query = "INSERT INTO tournois(date, nombreInscrits, classe) VALUES(?, 0, ?)";
        try (Connection connection = getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, tournoi.getDate());
            statement.setInt(2, tournoi.getNombreInscrit());
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
