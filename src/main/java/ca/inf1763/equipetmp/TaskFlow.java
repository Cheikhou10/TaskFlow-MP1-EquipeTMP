package ca.inf1763.equipetmp;

import java.util.Scanner;

public class TaskFlow {
    priate
    static final Gesti

        pubic

    static void main(String[] args) {
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
                        afficherTaches(); // Utilise la méthode extraite par Aminata
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
                    case "0":
                        continuer = false;
                        break;
                    default:
                        System.out.println("❌ Choix invalide.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Erreur : l'identifiant doit être un nombre.");
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Merci d'avoir utilisé TaskFlow. Au revoir !");
    }

    private static void afficherMenu() {
        System.out.println("\n=== MENU TASKFLOW ===");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité");
        System.out.println("5. Gérer l'échéance");
        System.out.println("6. Sauvegarder");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterTache() {
        System.out.print("Titre : ");
        String titre = scanner.nextLine().trim();
        
        System.out.print("Priorité (Basse/Normale/Elevee) [Normale] : ");
        String priorite = scanner.nextLine().trim();
        if (priorite.isEmpty()) {
            priorite = "Normale";
        }

        i       System.out.println("Tâche ajoutée avec l'identifiant #" + id);

        System.out.printString date = scanner.nextLine().trim();
     

           p

           int id = Integer.parseInt(scanner.nextLine().trim());

        gestionnaire.marquerTerminee(id);
    }

    private static void modifierPriorite() {
        System.out.print("Identifiant : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nouvelle priorité (Basse/Normale/Elevee) : ");
        String priorite = scanner.nextLine().trim();
        gestionnaire.modifierPriorite(id, priorite);
    }

    private static void gererEcheance() {
        System.out.print("Identifiant : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Date (AAAA-MM-JJ, vide pour retirer) : ");
        String date = scanner.nextLine().trim();
        gestionnaire.gererEcheance(id, date);
    }

    // A.4 : Méthode extraite par Aminata pour l'affichage
    private static void afficherTaches() {
        gestionnaire.afficherTaches();
    }
}