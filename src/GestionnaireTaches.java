import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // US-01 / US-02 / US-04
    public int ajouterTache(String titre, String priorite) {
        throw new UnsupportedOperationException("US-01 / US-02 / US-04 : à implémenter");
    }

    // US-03 : Ajouter une tâche avec échéance
    public int ajouterTacheAvecEcheance(String titre, String priorite, LocalDate echeance) {
    if (echeance == null) {
        throw new IllegalArgumentException("Une échéance est obligatoire");
    }

    Tache tache = new Tache(prochainId++, titre);
    tache.setPriorite(priorite);
    tache.setEcheance(echeance);
    taches.add(tache);

    return tache.getId();
}
    // US-05 / US-06
    public void afficherTaches() {
        throw new UnsupportedOperationException("US-05 / US-06 : à implémenter");
    }

    // US-07 / US-08
    public void marquerTerminee(int id) {
        throw new UnsupportedOperationException("US-07 / US-08 : à implémenter");
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