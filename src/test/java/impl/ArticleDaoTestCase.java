package impl;

import devweb.dao.ArticleDao;
import devweb.entities.Article;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.Null;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ArticleDaoTestCase {

        private ArticleDao articleDao = new ArticleDao();

        @Before
        public void initDatabase() throws SQLException {
            try (Connection connection = articleDao.getDatasource().getConnection();
                 Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("DELETE FROM articles");
                stmt.executeUpdate("INSERT INTO articles(idArticle, titre, image, texte) VALUES(1, 'Article 1', 'First image', 'First texte')");
                stmt.executeUpdate("INSERT INTO articles(idArticle, titre, image, texte) VALUES(2, 'Article 2', 'Second image', 'Second texte')");
                stmt.executeUpdate("INSERT INTO articles(idArticle, titre, image, texte) VALUES(3, 'Article 3', 'Third image', 'Third texte')");

            }
        }

        @Test
        public void shouldListArticles() {
            // WHEN
            List<Article> articles = articleDao.listArticles();
            // THEN
            assertThat(articles).hasSize(3);
            assertThat(articles).extracting("idArticle", "titre", "image", "texte").containsOnly(
                    tuple(1, "Article 1", "First image", "First texte"),
                    tuple(2, "Article 2", "Second image", "Second texte"),
                    tuple(3, "Article 3", "Third image", "Third texte")
            );
        }

        @Test
        public void shouldAddArticle() throws SQLException {
            // GIVEN
            Article article = new Article(null, "new titre", "new image", "bonjour à tous, bonjour à tous, bonjour à tous, bonjour à tous, bonjour à tous");
            // WHEN
            articleDao.addArticle(article);
            // THEN
            try (Connection connection = articleDao.getDatasource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM articles WHERE titre = 'new titre' ")) {
                    assertThat(rs.next()).isTrue();
                    assertThat(rs.getInt("idArticle")).isGreaterThan(0);
                    assertThat(rs.getString("titre")).isEqualTo("new titre");
                    assertThat(rs.getString("image")).isEqualTo("new image");
                    assertThat(rs.getString("texte")).isEqualTo("bonjour à tous, bonjour à tous, bonjour à tous, bonjour à tous, bonjour à tous");
                    assertThat(rs.next()).isFalse();
                }
            }
        }

        @Test
        public void shouldDelArticle() throws SQLException {

            articleDao.delArticle(4);

            try (Connection connection = articleDao.getDatasource().getConnection();
                 Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("DELETE FROM articles WHERE idArticle=4 ");

                }
        }
}
