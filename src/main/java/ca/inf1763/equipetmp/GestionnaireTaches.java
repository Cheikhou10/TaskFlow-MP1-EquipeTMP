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

        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
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

    public void sauvegarder() {
        java.io.File dossier = new java.io.File("data");
        if (!dossier.exists())
            dossier.mkdirs();

        try (java.io.PrintWriter writer = new java.io.PrintWriter("data/taches.csv")) {
            for (Tache tache : taches) {
                String echeance = (tache.getEcheance() == null) ? "" : tache.getEcheance().toString();
                writer.println(tache.getId() + ";" + tache.getTitre() + ";" + tache.getPriorite() + ";" + echeance + ";"
                        + tache.isTerminee());
            }
            System.out.println("Sauvegarde reussie : " + taches.size() + " tache(s).");
        } catch (java.io.IOException e) {
            System.out.println("Echec de la sauvegarde : " + e.getMessage());
        }
    }

    public void recharger() {
        java.io.File fichier = new java.io.File("data/taches.csv");
        if (!fichier.exists()) {
            System.out.println("Aucun fichier de donnees : demarrage avec une liste vide.");
            return;
        }

        taches.clear();
        int identifiantMaximum = 0;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty())
                    continue;

                String[] colonnes = ligne.split(";", -1);
                if (colonnes.length < 5) {
                    System.out.println("Ligne ignoree (format invalide) : " + ligne);
                    continue;
                }

                int identifiant = Integer.parseInt(colonnes[0]);
                String titre = colonnes[1];
                String priorite = colonnes[2];
                String echeanceTexte = colonnes[3];
                boolean terminee = Boolean.parseBoolean(colonnes[4]);

                Tache tache = new Tache(identifiant, titre);
                tache.setPriorite(priorite);
                if (!echeanceTexte.isEmpty()) {
                    tache.setEcheance(java.time.LocalDate.parse(echeanceTexte));
                }
                tache.setTerminee(terminee);
                taches.add(tache);

                if (identifiant > identifiantMaximum) {
                    identifiantMaximum = identifiant;
                }
            }

            prochainId = identifiantMaximum + 1;
            System.out.println("Donnees rechargees : " + taches.size() + " tache(s).");
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