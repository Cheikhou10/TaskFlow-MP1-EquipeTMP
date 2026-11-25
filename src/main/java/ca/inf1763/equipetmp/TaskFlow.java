package ca.inf1763.equipetmp;

import java.util.Scanner;

public class TaskFlow {
    private static final GestionnaireTaches gestionnaire = new GestionnaireTaches();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            gestionnaire.recharger();
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();

            try {
                switch (choix) {

                    case "1":
                        ajouterTache();
                        break;

                    case "2":
                        gestionnaire.afficherTaches();
                        break;

                    case "3":
                        marquerTerminee();
                        break;

                    case "4":
                        modifierPriorite();
                        break;

                    case "5":
                        gererEcheance();
                        break;

                    case "6":
                        gestionnaire.sauvegarder();
                        break;

                    case "7":
                        exporterExcel();
                        break;

                    case "0":
                        continuer = false;
                        System.out.println("Merci d'avoir utilisé TaskFlow. Au revoir !");
                        break;

                    default:
                        System.out.println("❌ Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Erreur : l'identifiant doit être un nombre.");
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Merci d'avoir utilisé TaskFlow. Au revoir !");
    }

    private static void afficherMenu() {
        System.out.println("\n========== MENU TASKFLOW ==========");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité d'une tâche");
        System.out.println("5. Gérer l'échéance d'une tâche");
        System.out.println("6. Sauvegarder les tâches");
        System.out.println("7. Exporter les tâches vers Excel");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterTache() {
        System.out.print("Titre de la tâche : ");
        String titre = scanner.nextLine().trim();

        System.out.print("Priorité (Basse/Normale/Élevée) [Normale] : ");
        String priorite = scanner.nextLine().trim();

        if (priorite.isEmpty()) {
            priorite = "Normale";
        }

        int id = gestionnaire.ajouterTache(titre, priorite);
        System.out.println("Tâche ajoutée avec succès. Identifiant : #" + id);

        System.out.print("Échéance (AAAA-MM-JJ, vide pour ignorer) : ");
        String date = scanner.nextLine().trim();
        if (!date.isEmpty()) {
            gestionnaire.gererEcheance(id, date);
        }
    }

    private static void marquerTerminee() {
        System.out.print("Identifiant de la tâche : ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        gestionnaire.marquerTerminee(id);
    }

    private static void modifierPriorite() {
        System.out.print("Identifiant de la tâche : ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Nouvelle priorité (Basse/Normale/Élevée) : ");
        String priorite = scanner.nextLine().trim();

        gestionnaire.modifierPriorite(id, priorite);
    }

    private static void gererEcheance() {
        System.out.print("Identifiant de la tâche : ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Date (AAAA-MM-JJ, vide pour retirer l'échéance) : ");
        String date = scanner.nextLine().trim();

        gestionnaire.gererEcheance(id, date);
    }

    private static void exporterExcel() {
        System.out.print("Nom ou chemin du fichier Excel [taches.xlsx] : ");
        String chemin = scanner.nextLine().trim();

        if (chemin.isEmpty()) {
            chemin = "taches.xlsx";
        }

        ExportateurExcel.exporter(gestionnaire.getTaches(), chemin);
    }
}