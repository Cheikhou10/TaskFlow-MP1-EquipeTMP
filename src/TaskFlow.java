import java.util.Scanner;

public class TaskFlow {
    // Initialisation des variables statiques
    private static final GestionnaireTaches gestionnaire = new GestionnaireTaches();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            gestionnaire.recharger();
        } catch (UnsupportedOperationException e) {
            System.out.println("[Non implémenté] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }

        boolean continuer = true;
        
        while (continuer) {
            afficherMenu(); // Appel de la méthode pour afficher le menu
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
                    case "4": // AJOUT IMPORTANT : Option pour quitter
                        continuer = false;
                        System.out.println("Merci d'avoir utilisé TaskFlow. Au revoir !");
                        break;
                    default:
                        System.out.println("❌ Choix invalide. Veuillez taper un chiffre entre 1 et 4.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("❌ Une erreur est survenue : " + e.getMessage());
            }
        }
        
        scanner.close(); // Bonne pratique : fermer le scanner à la fin
    }

    // --- MÉTHODES À COMPLÉTER PLUS TARD ---

    private static void afficherMenu() {
        System.out.println("\n=== MENU TASKFLOW ===");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterTache() {
        System.out.print("Entrez le nom de la tâche à ajouter : ");
        String nomTache = scanner.nextLine().trim();
        
        // À adapter selon ce que tu as dans GestionnaireTaches.java
        // Exemple : gestionnaire.ajouter(new Tache(nomTache));
       gestionnaire.ajouterTache(nomTache, "Normale");
System.out.println("Tâche ajoutée : " + nomTache);
    }

    private static void marquerTerminee() {
        System.out.print("Entrez le nom ou l'ID de la tâche terminée : ");
        String nomTache = scanner.nextLine().trim();
        
        // À adapter selon ce que tu as dans GestionnaireTaches.java
        // Exemple : gestionnaire.marquerTerminee(nomTache);
        System.out.println("✅ Tâche '" + nomTache + "' marquée comme terminée (à lier avec GestionnaireTaches).");
    }
}