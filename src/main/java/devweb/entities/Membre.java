package devweb.entities;

public class Membre {

    // Les attributs
    private String email;
    private String nom;
    private String prenom;
    private String classe;
    private String mdp;
    private Integer nbPoints;
    private Integer partiesGagnees;
    private Integer partiesJouees;
    private Boolean participe;
    private Integer numeroTable;

    // Le constructeur
    public Membre(String email, String nom, String prenom, String classe, String mdp, Integer nbPoints, Integer partiesGagnees, Integer partiesJouees, Boolean participe,Integer numeroTable) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.mdp = mdp;
        this.nbPoints = nbPoints;
        this.partiesGagnees = partiesGagnees;
        this.partiesJouees = partiesJouees;
        this.participe = participe;
        this.numeroTable = numeroTable;
    }

    // Getters et Setters
    public Integer getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(Integer nbPoints) {
        this.nbPoints = nbPoints;
    }

    public Integer getPartiesGagnees() {
        return partiesGagnees;
    }

    public void setPartiesGagnees(Integer partiesGagnees) {
        this.partiesGagnees = partiesGagnees;
    }

    public Integer getPartiesJouees() {
        return partiesJouees;
    }

    public void setPartiesJouees(Integer partiesJouees) {
        this.partiesJouees = partiesJouees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Boolean getParticipe() {
        return participe;
    }

    public void setParticipe(Boolean participe) {
        this.participe = participe;
    }

    public void setNumeroTable(Integer numeroTable) {
        this.numeroTable = numeroTable;
    }

    public Integer getNumeroTable() {
        return numeroTable;
    }
}
