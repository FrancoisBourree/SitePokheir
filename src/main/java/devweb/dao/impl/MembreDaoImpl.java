package devweb.dao.impl;

import devweb.dao.MembreDao;
import devweb.entities.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao{

    @Override
    public List<Membre> listMembres(){
        String query = "SELECT * FROM membre ORDER BY nbPoints DESC;";

        List<Membre> listofMembres = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                listofMembres.add(
                        new Membre(resultSet.getString("email"),resultSet.getString("nom"),
                                resultSet.getString("prenom"),resultSet.getString("classe"),
                                resultSet.getString("mdp"),resultSet.getInt("nbPoints"),
                                resultSet.getInt("partiesGagnees"), resultSet.getInt("partiesJouees"),
                                resultSet.getBoolean("participe"), resultSet.getInt("numeroTable")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofMembres;
    }


    public List<Membre> listParticipantClasse() {
        String query = "SELECT * FROM membre WHERE NOT email='admin@hei.yncrea.fr' AND participe='1' ORDER BY nbPoints DESC";
        List<Membre> listofParticipantClasse = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                listofParticipantClasse.add(
                        new Membre(resultSet.getString("email"), resultSet.getString("nom"),
                                resultSet.getString("prenom"), resultSet.getString("classe"),
                                resultSet.getString("mdp"), resultSet.getInt("nbPoints"),
                                resultSet.getInt("partiesGagnees"),resultSet.getInt("partiesJouees"),
                                resultSet.getBoolean("participe"), resultSet.getInt("numeroTable")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofParticipantClasse;
    }

    public List<Membre> listParticipantRandom() {
        String query = "SELECT * FROM membre WHERE NOT email='admin@hei.yncrea.fr' AND participe='1' ORDER BY RAND()";
        List<Membre> listofParticipantRandom = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                listofParticipantRandom.add(
                        new Membre(resultSet.getString("email"), resultSet.getString("nom"),
                                resultSet.getString("prenom"), resultSet.getString("classe"),
                                resultSet.getString("mdp"), resultSet.getInt("nbPoints"),
                                resultSet.getInt("partiesGagnees"), resultSet.getInt("partiesJouees"),
                                resultSet.getBoolean("participe"), resultSet.getInt("numeroTable")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofParticipantRandom;
    }


    @Override
    public Membre getMembre(String email) {
        String query = "SELECT * FROM membre WHERE email=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Membre(
                            resultSet.getString("email"),resultSet.getString("nom"),
                            resultSet.getString("prenom"),resultSet.getString("classe"),
                            resultSet.getString("mdp"),resultSet.getInt("nbPoints"),
                            resultSet.getInt("partiesGagnees"), resultSet.getInt("partiesJouees"),
                            resultSet.getBoolean("participe"), resultSet.getInt("numeroTable"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMembre(String email, String nom, String prenom, String classe, String mdp) {
        String query = "INSERT INTO membre(email, nom, prenom, classe, mdp, nbPoints, partiesGagnees, partiesJouees, participe, numeroTable) VALUES(?,?,?,?,?,'0','0','0','0','0')";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, classe);
            statement.setString(5, mdp);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMdp(String email) {
        String query = "SELECT mdp FROM membre WHERE email=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new String(resultSet.getString("mdp"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMembre(String email) {
        String query = "DELETE FROM membre WHERE email=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifMdp(String email, String mdp){
        String query ="UPDATE membre SET mdp=? WHERE email=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, mdp);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inscrire(String email) {
        String query = "UPDATE membre SET participe='1' WHERE email=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void desinscrire(String email) {
        String query = "UPDATE membre SET participe='0' WHERE email=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void desinscrireTous() {
        String query = "UPDATE membre SET participe='0'";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPoint(String email, Integer nbpoint) {
        String query = "UPDATE membre SET nbPoints=nbPoints+?,partiesJouees=partiesJouees+1 WHERE email=?";
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                 statement.setInt(1, nbpoint);
                 statement.setString(2, email);
                 statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int compterLesInscrits(){
        Integer nbInscrits = 0;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("Select count(participe) AS nombreInscrits From membre WHERE participe='1'")) {
            resultSet.next();
            nbInscrits=resultSet.getInt("nombreInscrits");
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbInscrits;
    }


}
