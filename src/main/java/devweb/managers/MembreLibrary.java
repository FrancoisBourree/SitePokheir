package devweb.managers;

import devweb.dao.MembreDao;
import devweb.dao.impl.MembreDaoImpl;
import devweb.entities.Membre;

import java.util.List;

public class MembreLibrary {

    public List<Membre> listMembres() {
        return membreDao.listMembres();
    }

    public List<Membre> listParticipantRandom() {
        return membreDao.listParticipantRandom();
    }

    public List<Membre> listParticipantClasse() {
        return membreDao.listParticipantClasse();
    }

    private static class MembreLibraryHolder {
        private final static MembreLibrary instance = new MembreLibrary();
    }

    public static MembreLibrary getInstance() {

        return MembreLibraryHolder.instance;
    }

    private MembreDao membreDao = new MembreDaoImpl();

    public void addMembre(String email,String nom,String prenom,String classe,String mdp) {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("L'email ne doit pas être vide.");
        }
        membreDao.addMembre(email,nom,prenom,classe,mdp);
    }

    public String getMdp(String email) {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("L'email ne doit pas être vide.");
        }
        return membreDao.getMdp(email);
    }

    public void deleteMembre(String email) {

        membreDao.deleteMembre(email);
    }

    public void modifMdp(String email,String mdp){
        membreDao.modifMdp(email,mdp);
    }

    public void inscrire(String email){
        membreDao.inscrire(email);
    }

    public void desinscrire(String email){
        membreDao.desinscrire(email);
    }

    public void addPoint(String email, Integer nbPoints){

        membreDao.addPoint(email,nbPoints);
    };
}
