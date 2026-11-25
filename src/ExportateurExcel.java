import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportateurExcel {

    public static void exporter(List<Tache> taches, String cheminFichier) {

        // Vérifier si la liste des tâches est vide
        if (taches == null || taches.isEmpty()) {
            System.out.println("Aucune tâche à exporter.");
            return;
        }

        Path chemin = Path.of(cheminFichier);
        Path dossier = chemin.toAbsolutePath().getParent();

        // Vérifier si le dossier existe
        if (dossier != null && !Files.isDirectory(dossier)) {
            System.out.println("Erreur : le dossier indiqué n'existe pas.");
            return;
        }

        // Vérifier l'extension du fichier
        if (!cheminFichier.toLowerCase().endsWith(".xlsx")) {
            System.out.println("Erreur : le fichier doit avoir l'extension .xlsx.");
            return;
        }

        try (Workbook classeur = new XSSFWorkbook()) {

            Sheet feuille = classeur.createSheet("Tâches");

            // Créer les en-têtes
            Row entetes = feuille.createRow(0);
            entetes.createCell(0).setCellValue("ID");
            entetes.createCell(1).setCellValue("Titre");
            entetes.createCell(2).setCellValue("Priorité");
            entetes.createCell(3).setCellValue("Échéance");
            entetes.createCell(4).setCellValue("Statut");

            // Ajouter les tâches
            int numeroLigne = 1;

            for (Tache tache : taches) {
                Row ligne = feuille.createRow(numeroLigne++);

                ligne.createCell(0).setCellValue(tache.getId());
                ligne.createCell(1).setCellValue(tache.getTitre());
                ligne.createCell(2).setCellValue(tache.getPriorite());

                String echeance = (tache.getEcheance() == null)
                        ? "Aucune"
                        : tache.getEcheance().toString();

                ligne.createCell(3).setCellValue(echeance);

                String statut = tache.isTerminee()
                        ? "Terminée"
                        : "À faire";

                ligne.createCell(4).setCellValue(statut);
            }

            // Ajuster automatiquement la largeur des colonnes
            for (int i = 0; i < 5; i++) {
                feuille.autoSizeColumn(i);
            }

            // Écrire le fichier Excel
            try (FileOutputStream fichier = new FileOutputStream(chemin.toFile())) {
                classeur.write(fichier);
            }

            System.out.println("Exportation Excel réussie : " + cheminFichier);

        } catch (IOException e) {
            System.out.println(
                    "Erreur lors de l'exportation. Vérifiez que le fichier n'est pas ouvert "
                    + "et que vous avez les permissions nécessaires."
            );
        }
    }
}
