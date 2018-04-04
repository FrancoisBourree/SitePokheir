package devweb.entities;

import java.io.File;

public class Article {

    // Les attributs
    private Integer idArticle;
    private String titre;
    private String image;
    private String texte;

    // Le constructeur
    public Article(Integer idArticle, String titre, String image, String texte) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.image = image;
        this.texte = texte;
    }

    // Getters et Setters
    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}


