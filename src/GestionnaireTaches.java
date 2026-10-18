import java.util.ArrayList;
import java.util.List;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // US-01 / US-02 / US-04
    public int ajouterTache(String titre, String priorite) {
        throw new UnsupportedOperationException("US-01 / US-02 / US-04 : à implémenter");
    }

    // US-05 / US-06
    public void afficherTaches() {
        throw new UnsupportedOperationException("US-05 / US-06 : à implémenter");
    }

    
    // US-07 / US-08
    public void marquerTerminee(int id) {
        Tache tacheTrouvee = null;

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tacheTrouvee = tache;
                break;
            }
        }

        if (tacheTrouvee == null) {
            System.out.println("Erreur : aucune tâche ne possède l'identifiant #" + id + ".");
            return;
        }

        if (tacheTrouvee.isTerminee()) {
            System.out.println("La tâche #" + id + " est déjà terminée.");
            return;
        }

        tacheTrouvee.setTerminee(true);
        System.out.println("La tâche #" + id + " a été marquée comme terminée.");
    }


    // US-09
    public void modifierPriorite(int id, String priorite) {
        throw new UnsupportedOperationException("US-09 : à implémenter");
    }

    // US-10 / US-11 / US-12
    public void gererEcheance(int id, String date) {
        throw new UnsupportedOperationException("US-10 / US-11 / US-12 : à implémenter");
    }

    // US-13 / US-17
    public void sauvegarder() {
        throw new UnsupportedOperationException("US-13 / US-17 : à implémenter");
    }

    // US-14 / US-15 / US-16
    public void recharger() {
        throw new UnsupportedOperationException("US-14 / US-15 / US-16 : à implémenter");
    }

    public List<Tache> getTaches() { return taches; }
    public int getProchainId() { return prochainId; }
    public void setProchainId(int id) { this.prochainId = id; }
}