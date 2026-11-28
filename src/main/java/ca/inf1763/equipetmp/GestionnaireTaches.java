package ca.inf1763.equipetmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    public int ajouterTache(String titre, String priorite) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire et ne peut pas etre vide.");
        }
        titre = titre.trim();

        if (priorite == null || priorite.trim().isEmpty()) {
            priorite = "Normale";
        }

        String prioriteNormalisee = priorite.trim().toLowerCase(Locale.ROOT);
        prioriteNormalisee = prioriteNormalisee
                .replace("é", "e")
                .replace("è", "e")
                .replace("ê", "e")
                .replace("ë", "e");

        switch (prioriteNormalisee) {
            case "basse":
                priorite = "Basse";
                break;
            case "normale":
                priorite = "Normale";
                break;
            case "elevee":
            case "haute":
                priorite = "Elevee";
                break;
            default:
                throw new IllegalArgumentException("Priorite invalide. Choisissez Basse, Normale ou Elevee.");
        }

        Tache nouvelleTache = new Tache(prochainId++, titre);
        nouvelleTache.setPriorite(priorite);
        taches.add(nouvelleTache);

        return nouvelleTache.getId();
    }

    public void afficherTaches() {
        if (taches.isEmpty()) {
            System.out.println("Aucune tache a afficher.");
            return;
        }
        System.out.println("Liste des taches :");
        for (Tache tache : taches) {
            System.out.println(tache);
        }
    }

    public void marquerTerminee(int id) {
        Tache tacheTrouvee = null;

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tacheTrouvee = tache;
                break;
            }
        }

        if (tacheTrouvee == null) {
            System.out.println("Erreur : aucune tache ne possede l'identifiant #" + id + ".");
            return;
        }

        if (tacheTrouvee.isTerminee()) {
            System.out.println("La tache #" + id + " est deja terminee.");
            return;
        }

        tacheTrouvee.setTerminee(true);
        System.out.println("La tache #" + id + " a ete marquee comme terminee.");
    }

    public void modifierPriorite(int id, String priorite) {
        Tache tacheTrouvee = null;

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tacheTrouvee = tache;
                break;
            }
        }

        if (tacheTrouvee == null) {
            System.out.println("Erreur : aucune tache ne possede l'identifiant #" + id + ".");
            return;
        }

        if (priorite == null || !(priorite.equalsIgnoreCase("Basse") || priorite.equalsIgnoreCase("Normale")
                || priorite.equalsIgnoreCase("Elevee"))) {
            System.out.println("Erreur : priorite invalide. Choisissez Basse, Normale ou Elevee.");
            return;
        }

        if (priorite.equalsIgnoreCase("Basse")) {
            priorite = "Basse";
        } else if (priorite.equalsIgnoreCase("Normale")) {
            priorite = "Normale";
        } else {
            priorite = "Elevee";
        }

        tacheTrouvee.setPriorite(priorite);
        System.out.println("La priorite de la tache #" + id + " a ete modifiee a " + priorite + ".");
    }

    public void gererEcheance(int id, String date) {
        Tache tacheTrouvee = null;

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tacheTrouvee = tache;
                break;
            }
        }

        if (tacheTrouvee == null) {
            System.out.println("Erreur : aucune tache ne possede l'identifiant #" + id + ".");
            return;
        }

        if (date != null && date.isEmpty()) {
            tacheTrouvee.setEcheance(null);
            System.out.println("L'echeance de la tache #" + id + " a ete retiree.");
            return;
        }

        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Erreur : la date doit etre au format AAAA-MM-JJ.");
            return;
        }

        DateTimeFormatter format = DateTimeFormatter
                .ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDate nouvelleDate;

        try {
            nouvelleDate = LocalDate.parse(date, format);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : la date saisie est invalide.");
            return;
        }

        boolean avaitEcheance = tacheTrouvee.getEcheance() != null;
        tacheTrouvee.setEcheance(nouvelleDate);

        if (avaitEcheance) {
            System.out.println("L'echeance de la tache #" + id + " a ete modifiee au " + nouvelleDate + ".");
        } else {
            System.out.println("L'echeance de la tache #" + id + " est fixee au " + nouvelleDate + ".");
        }
    }

    // Refactoring A.6 : On délègue la sauvegarde à la classe PersistanceTaches
    public void sauvegarder() {
        PersistanceTaches.sauvegarder(taches);
    }

    // Refactoring A.6 : On délègue le chargement à la classe PersistanceTaches
    public void recharger() {
        List<Tache> tachesChargees = PersistanceTaches.charger();
        this.taches.clear();
        this.taches.addAll(tachesChargees);

        int identifiantMaximum = 0;
        for (Tache t : this.taches) {
            if (t.getId() > identifiantMaximum) {
                identifiantMaximum = t.getId();
            }
        }
        this.prochainId = identifiantMaximum + 1;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public int getProchainId() {
        return prochainId;
    }

    public void setProchainId(int id) {
        this.prochainId = id;
    }
}