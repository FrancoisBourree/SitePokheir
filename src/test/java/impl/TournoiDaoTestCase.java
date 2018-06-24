package impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import devweb.dao.TournoiDao;
import devweb.dao.impl.DataSourceProvider;
import devweb.entities.Membre;
import devweb.entities.Tournoi;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class TournoiDaoTestCase {



    private TournoiDao tournoiDao = new TournoiDao();

    public static class DataSourceTestProvider {

        public static MysqlDataSource dataSourceTest;

        public static DataSource getDataSourceTest() {
            if (dataSourceTest == null) {
                dataSourceTest = new MysqlDataSource();
                dataSourceTest.setServerName("localhost");
                dataSourceTest.setPort(3306);
                dataSourceTest.setDatabaseName("pokheir_test");
                dataSourceTest.setUser("root");
                dataSourceTest.setPassword("");

            }
            return dataSourceTest;
        }



    }

    @Before
    public void initDb() throws Exception {
        try (Connection connection = TournoiDaoTestCase.DataSourceTestProvider.getDataSourceTest().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM tournois");
            stmt.executeUpdate("INSERT INTO tournois VALUES ('1','2018-06-15','0','100','0')");
            stmt.executeUpdate("INSERT INTO tournois VALUES ('2','2018-06-16','10','50','0')");
            stmt.executeUpdate("INSERT INTO tournois VALUES ('3','2018-06-17','50','10','1')");
            stmt.executeUpdate("INSERT INTO tournois VALUES ('4','2018-06-18','100','0','1')");

        }
    }

    @Test
    public void listTournois() {
        // WHEN
        List<Tournoi> tournois = tournoiDao.listTournois();
        // THEN
        assertThat(tournois).hasSize(4);
        assertThat(tournois).extracting("date", "nombreInscrits","placesTable").containsOnly(tuple("2018-06-15", 0,100), tuple("2018-06-16", 10,50),
                tuple("2018-06-17", 50,10), tuple("2018-06-18", 100,0));
    }

    @Test
    public void choosePlacesParTable() throws Exception{

        tournoiDao.choosePlacesParTable(1, 200);

        try (Connection connection = TournoiDaoTestCase.DataSourceTestProvider.getDataSourceTest().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE tournois SET placesTable =200 WHERE idTournois=1 ");

        }
    }





}
