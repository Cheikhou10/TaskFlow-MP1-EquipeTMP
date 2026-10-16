import java.time.LocalDate;

public class Tache {
    private final int id;
    private final String titre;
    private String priorite;
    private LocalDate echeance;
    private boolean terminee;

    public Tache(int id, String titre) {
        this.id = id;
        this.titre = titre;
        this.priorite = "Normale";
        this.echeance = null;
        this.terminee = false;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }
    public LocalDate getEcheance() { return echeance; }
    public void setEcheance(LocalDate echeance) { this.echeance = echeance; }
    public boolean isTerminee() { return terminee; }
    public void setTerminee(boolean terminee) { this.terminee = terminee; }

    @Override
    public String toString() {
        String ech = (echeance == null) ? "aucune" : echeance.toString();
        String statut = terminee ? "Terminée" : "À faire";
        return "#" + id + " | " + titre + " | " + priorite + " | " + ech + " | " + statut;
    }
}