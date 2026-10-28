import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GestionnaireTaches {
    private final List<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // US-01 / US-02 / US-04 (Code d'Aminata)
    public int ajouterTache(String titre, String priorite) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire et ne peut pas être vide.");
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
                priorite = "Élevée";
                break;
            default:
                throw new IllegalArgumentException("Priorité invalide. Choisissez Basse, Normale ou Elevee.");
        }

        Tache nouvelleTache = new Tache(prochainId++, titre);
        nouvelleTache.setPriorite(priorite);
        taches.add(nouvelleTache);

        return nouvelleTache.getId();
    }

    // US-05 / US-06 (Pas encore fusionné par Fatou)
    public void afficherTaches() {
        throw new UnsupportedOperationException("US-05 / US-06 : à implémenter");
    }

    // US-07 / US-08 (Code de Fatima)
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

    // US-09 (Code de Fatima)
    public void modifierPriorite(int id, String priorite) {
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

        if (priorite == null || !(priorite.equalsIgnoreCase("Basse") || priorite.equalsIgnoreCase("Normale")
                || priorite.equalsIgnoreCase("Élevée") || priorite.equalsIgnoreCase("Elevee"))) {
            System.out.println("Erreur : priorité invalide. Choisissez Basse, Normale ou Élevée.");
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
        System.out.println("La priorité de la tâche #" + id + " a été modifiée à " + priorite + ".");
    }

    // US-10 / US-11 / US-12 (Pas encore fusionné par Fatygueye)
    public void gererEcheance(int id, String date) {
        throw new UnsupportedOperationException("US-10 / US-11 / US-12 : à implémenter");
    }

    // US-13 / US-17 (CODE DE CHEIKHOU)
    public void sauvegarder() {
        java.io.File dossier = new java.io.File("data");
        if (!dossier.exists())
            dossier.mkdirs();

        try (java.io.PrintWriter writer = new java.io.PrintWriter("data/taches.csv")) {
            for (Tache t : taches) {
                String ech = (t.getEcheance() == null) ? "" : t.getEcheance().toString();
                writer.println(
                        t.getId() + ";" +
                                t.getTitre() + ";" +
                                t.getPriorite() + ";" +
                                ech + ";" +
                                t.isTerminee());
            }
            System.out.println("Sauvegarde réussie : " + taches.size() + " tâche(s).");
        } catch (java.io.IOException e) {
            System.out.println("Échec de la sauvegarde : " + e.getMessage());
        }
    }

    // US-14 / US-15 / US-16 (CODE DE CHEIKHOU)
    public void recharger() {
        java.io.File fichier = new java.io.File("data/taches.csv");
        if (!fichier.exists()) {
            System.out.println("Aucun fichier de données : démarrage avec une liste vide.");
            return;
        }

        taches.clear();
        int maxId = 0;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty())
                    continue;

                String[] parts = ligne.split(";", -1);
                if (parts.length < 5) {
                    System.out.println("Ligne ignorée (format invalide) : " + ligne);
                    continue;
                }

                int id = Integer.parseInt(parts[0]);
                String titre = parts[1];
                String priorite = parts[2];
                String echStr = parts[3];
                boolean terminee = Boolean.parseBoolean(parts[4]);

                Tache t = new Tache(id, titre);
                t.setPriorite(priorite);
                if (!echStr.isEmpty()) {
                    t.setEcheance(java.time.LocalDate.parse(echStr));
                }
                t.setTerminee(terminee);
                taches.add(t);

                if (id > maxId)
                    maxId = id;
            }
            prochainId = maxId + 1;
            System.out.println("Données rechargées : " + taches.size() + " tâche(s).");
        } catch (Exception e) {
            System.out.println("Fichier illisible : " + e.getMessage());
            taches.clear();
            prochainId = 1;
        }
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