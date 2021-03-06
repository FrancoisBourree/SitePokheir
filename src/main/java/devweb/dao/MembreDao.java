package devweb.dao;

import devweb.entities.Membre;

import java.util.List;

public interface MembreDao {

  /*  protected DataSource getDatasource() {
        PostgresqlData dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("hei");
        dataSource.setUser("hei");
        dataSource.setPassword("hei");

        return dataSource;
    }*/

    public List<Membre> listMembres();

    public List<Membre> listParticipantClasse();

    public List<Membre> listParticipantRandom();

    public int compterLesInscrits();

    public Membre getMembre(String email);

    public void addMembre(String email, String nom, String prenom, String classe, String mdp);

    public String getMdp(String email);

    public void deleteMembre(String email);

    public void modifMdp(String email, String mdp);

    public void addPoint(String email, Integer nbpoint);

    public void inscrire(String email);

    public void desinscrire(String email);

    public  void desinscrireTous();
}

