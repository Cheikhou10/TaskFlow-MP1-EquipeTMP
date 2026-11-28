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
                        afficherTaches(); // Utilise la methode extraite par Aminata
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
                        exporterExcel(); // Methode de Fatima
                        break;
                    case "0":
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : l'identifiant doit etre un nombre.");
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Merci d'avoir utilise TaskFlow. Au revoir !");
    }

    private static void afficherMenu() {
        System.out.println("\n========== MENU TASKFLOW ==========");
        System.out.println("1. Ajouter une tache");
        System.out.println("2. Afficher les taches");
        System.out.println("3. Marquer une tache comme terminee");
        System.out.println("4. Modifier la priorite d'une tache");
        System.out.println("5. Gerer l'echeance d'une tache");
        System.out.println("6. Sauvegarder les taches");
        System.out.println("7. Exporter les taches vers Excel");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterTache() {
        System.out.print("Titre de la tache : ");
        String titre = scanner.nextLine().trim();

        System.out.print("Priorite (Basse/Normale/Elevee) [Normale] : ");
        String priorite = scanner.nextLine().trim();

        if (priorite.isEmpty()) {
            priorite = "Normale";
        }

        int id = gestionnaire.ajouterTache(titre, priorite);
        System.out.println("Tache ajoutee avec succes. Identifiant : #" + id);

        System.out.print("Echeance (AAAA-MM-JJ, vide pour ignorer) : ");
        String date = scanner.nextLine().trim();
        if (!date.isEmpty()) {
            gestionnaire.gererEcheance(id, date);
        }
    }

    private static void marquerTerminee() {
        System.out.print("Identifiant de la tache : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        gestionnaire.marquerTerminee(id);
    }

    private static void modifierPriorite() {
        System.out.print("Identifiant de la tache : ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Nouvelle priorite (Basse/Normale/Elevee) : ");
        String priorite = scanner.nextLine().trim();
        gestionnaire.modifierPriorite(id, priorite);
    }

    private static void gererEcheance() {
        System.out.print("Identifiant de la tache : ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Date (AAAA-MM-JJ, vide pour retirer l'echeance) : ");
        String date = scanner.nextLine().trim();
        gestionnaire.gererEcheance(id, date);
    }

    // A.4 : Methode extraite par Aminata pour l'affichage
    private static void afficherTaches() {
        gestionnaire.afficherTaches();
    }

    // Methode de Fatima pour l'export Excel
    private static void exporterExcel() {
        System.out.print("Nom ou chemin du fichier Excel [taches.xlsx] : ");
        String chemin = scanner.nextLine().trim();

        if (chemin.isEmpty()) {
            chemin = "taches.xlsx";
        }

        ExportateurExcel.exporter(gestionnaire.getTaches(), chemin);
    }
}