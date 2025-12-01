package FFSSM;

import java.util.ArrayList;

import lombok.Getter;

public class Plongeur extends Personne{

	private int niveau;

    @Getter
    private ArrayList<Licence> licences;

    private GroupeSanguin groupeSanguin;

    public Plongeur(String nom) {
        super(nom);
        this.licences = new ArrayList<>();
    }

    public Plongeur(String nom, int niveau, GroupeSanguin groupeSanguin) {
        super(nom);
        this.niveau = niveau;
        this.groupeSanguin = groupeSanguin;
        this.licences = new ArrayList<>();
    }

    public void ajouteLicence(Licence l) {
        if (l != null){
            licences.add(l);
        }else{
            throw new IllegalArgumentException("La licence n'a pas de données" );
        }
    }

    public Licence derniereLicence() {
        if (licences.isEmpty()) {
            return null;
        }
        Licence derniere = licences.get(0);
        for (Licence l : licences) {
            if (l.getDelivrance().isAfter(derniere.getDelivrance())) {
                derniere = l;
            }
        }
        return derniere;
    }
}
