package devweb.services;

import devweb.dao.TournoiDao;
import devweb.entities.Tournoi;

import java.util.List;

public class TournoiService {

    private static class TournoiServiceHolder {
        private static TournoiService instance = new TournoiService();
    }

    public static TournoiService getInstance(){ return TournoiService.TournoiServiceHolder.instance; }

    private TournoiService() {
    }

    private TournoiDao tournoiDao = new TournoiDao();

    public List<Tournoi> listTournois() {
        return tournoiDao.listTournois();
    }

    public int trouverplacesTable(Integer idTournoi){
        return tournoiDao.trouverplacesTable(idTournoi);
    }

    public void addTournoi(Tournoi tournoi) {
        tournoiDao.addTournoi(tournoi);
    }

    public void delTournoi(Integer idTournoi){
        tournoiDao.delTournoi(idTournoi);
    }

}
