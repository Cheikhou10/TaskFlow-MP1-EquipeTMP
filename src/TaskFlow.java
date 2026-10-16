import java.util.Scanner;

public class TaskFlow {
    private static final GestionnaireTaches gestionnaire = new GestionnaireTaches();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            gestionnaire.recharger();
        } catch (UnsupportedOperationException e) {
            System.out.println("[Non implémenté] " + e.getMessage());
        }

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine().trim();
            try {
                switch (choix) {
                    case "1": ajouterTache(); break;
                    case "2": gestionnaire.afficherTaches(); break;
                    case "3": marquerTerminee(); break;
                    case "4": modifierPriorite(); break;
                    case "5": gererEcheance(); break;
                    case "6": gestionnaire.sauvegarder(); break;
                    case "0": continuer = false; break;
                    default: System.out.println("Choix invalide.");
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("[Non implémenté] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erreur : identifiant invalide.");
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Au revoir !");
    }

    private static void afficherMenu() {
        System.out.println("\n=== TaskFlow ===");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer comme terminée");
        System.out.println("4. Modifier la priorité");
        System.out.println("5. Gérer l'échéance");
        System.out.println("6. Sauvegarder");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }

    private static void ajouterTache() {
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Priorité (Basse/Normale/Élevée) [Normale] : ");
        String priorite = scanner.nextLine().trim();
        if (priorite.isEmpty()) priorite = "Normale";
        int id = gestionnaire.ajouterTache(titre, priorite);
        System.out.println("Tâche ajoutée avec l'identifiant #" + id);
    }

    private static void marquerTerminee() {
        System.out.print("Identifiant : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        gestionnaire.marquerTerminee(id);
    }

    private static void modifierPriorite() {
        System.out.print("Identifiant : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nouvelle priorité (Basse/Normale/Élevée) : ");
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
}