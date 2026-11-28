package ca.inf1763.equipetmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistanceTaches {

    private static final String DOSSIER_DATA = "data";
    private static final String FICHIER_CSV = "data/taches.csv";

    public static void sauvegarder(List<Tache> taches) {
        File dossier = new File(DOSSIER_DATA);
        if (!dossier.exists()) {
            dossier.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(FICHIER_CSV)) {
            for (Tache tache : taches) {
                String echeance = (tache.getEcheance() == null) ? "" : tache.getEcheance().toString();
                writer.println(
                        tache.getId() + ";" +
                                tache.getTitre() + ";" +
                                tache.getPriorite() + ";" +
                                echeance + ";" +
                                tache.isTerminee());
            }
            System.out.println("Sauvegarde reussie : " + taches.size() + " tache(s).");
        } catch (IOException e) {
            System.out.println("Echec de la sauvegarde : " + e.getMessage());
        }
    }

    public static List<Tache> charger() {
        List<Tache> taches = new ArrayList<>();
        File fichier = new File(FICHIER_CSV);
        if (!fichier.exists()) {
            System.out.println("Aucun fichier de donnees : demarrage avec une liste vide.");
            return taches;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
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
                    tache.setEcheance(LocalDate.parse(echeanceTexte));
                }
                tache.setTerminee(terminee);
                taches.add(tache);
            }
            System.out.println("Donnees rechargees : " + taches.size() + " tache(s).");
        } catch (Exception e) {
            System.out.println("Fichier illisible : " + e.getMessage());
        }
        return taches;
    }
}