import java.time.LocalDate;

public class TestUS03 {
    public static void main(String[] args) {
        GestionnaireTaches gestionnaire = new GestionnaireTaches();

        LocalDate date = LocalDate.of(2026, 10, 25);

        int id = gestionnaire.ajouterTacheAvecEcheance(
            "Terminer le projet TaskFlow",
            "Normale",
            date
        );

        System.out.println("Test US-03 réussi !");
        System.out.println("Identifiant de la tâche : " + id);
        System.out.println("Échéance prévue : " + date);
    }
}