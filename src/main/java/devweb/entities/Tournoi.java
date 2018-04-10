package devweb.entities;

import java.sql.Date;

public class Tournoi{

    // Les attributs
    private Integer idTournois;
    private Date date;
    private Integer nombreInscrit;
    private Boolean classe;

    // Le constructeur
    public Tournoi(Integer idTournois, Date date, Integer nombreInscrit, Boolean classe) {
        this.idTournois = idTournois;
        this.date = date;
        this.nombreInscrit = nombreInscrit;
        this.classe = classe;
    }

    // Getters et Setters

    public Integer getIdTournois() {
        return idTournois;
    }

    public void setIdTournois(Integer idTournois) {
        this.idTournois = idTournois;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNombreInscrit() {
        return nombreInscrit;
    }

    public void setNombreInscrit(Integer nombreInscrit) {
        this.nombreInscrit = nombreInscrit;
    }

    public Boolean getClasse() {
        return classe;
    }

    public void setClasse(Boolean classe) {
        this.classe = classe;
    }
}
