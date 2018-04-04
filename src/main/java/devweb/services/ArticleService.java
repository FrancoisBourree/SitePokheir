package devweb.services;

import devweb.dao.ArticleDao;
import devweb.entities.Article;

import java.util.List;

public class ArticleService {


    private static class ArticleServiceHolder {
        private static ArticleService instance = new ArticleService();
    }

    public static ArticleService getInstance() {
        return ArticleServiceHolder.instance;
    }

    private ArticleService() {
    }

    private ArticleDao articleDao = new ArticleDao();

   public List<Article> listArticles() {
        return articleDao.listArticles();
    }

    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }

    public void delArticle(Integer idArticle){
        articleDao.delArticle(idArticle);
    }

}

