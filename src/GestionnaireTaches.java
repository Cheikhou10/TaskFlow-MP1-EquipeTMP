import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Locale;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // US-01 / US-02 / US-04
    // US-01 / US-02 / US-04
    public int ajouterTache(String titre, String priorite) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Le titre est obligatoire et ne peut pas être vide."
            );
        }

        titre = titre.trim();

        if (priorite == null || priorite.trim().isEmpty()) {
            priorite = "Normale";
        }

        switch (priorite.trim().toLowerCase(Locale.ROOT)) {
            case "basse":
                priorite = "Basse";
                break;
            case "normale":
                priorite = "Normale";
                break;
            case "élevée":
            case "elevee":
                priorite = "Élevée";
                break;
            default:
                throw new IllegalArgumentException(
                    "Priorité invalide. Choisissez Basse, Normale ou Élevée."
                );
        }

        Tache nouvelleTache = new Tache(prochainId++, titre);
        nouvelleTache.setPriorite(priorite);
        taches.add(nouvelleTache);

        return nouvelleTache.getId();
    }

    // US-03 : Ajouter une tâche avec échéance
    public int ajouterTacheAvecEcheance(
            String titre, String priorite, LocalDate echeance) {

        if (echeance == null) {
            throw new IllegalArgumentException(
                "Une échéance est obligatoire"
            );
        }

        int id = ajouterTache(titre, priorite);

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tache.setEcheance(echeance);
                break;
            }
        }

        return id;
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
    Tache tacheTrouvee = null;

    for (Tache tache : taches) {
        if (tache.getId() == id) {
            tacheTrouvee = tache;
            break;
        }
    }

    if (tacheTrouvee == null) {
        System.out.println(
            "Erreur : aucune tâche ne possède l'identifiant #" + id + "."
        );
        return;
    }

    if (priorite == null ||
        !(priorite.equalsIgnoreCase("Basse") ||
          priorite.equalsIgnoreCase("Normale") ||
          priorite.equalsIgnoreCase("Élevée") ||
          priorite.equalsIgnoreCase("Elevee"))) {

        System.out.println(
            "Erreur : priorité invalide. Choisissez Basse, Normale ou Élevée."
        );
        return;
    }

    if (priorite.equalsIgnoreCase("Basse")) {
        priorite = "Basse";
    } else if (priorite.equalsIgnoreCase("Normale")) {
        priorite = "Normale";
    } else {
        priorite = "Élevée";
    }

    tacheTrouvee.setPriorite(priorite);

    System.out.println(
        "La priorité de la tâche #" + id +
        " a été modifiée à " + priorite + "."
    );
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