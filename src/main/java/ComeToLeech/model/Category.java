package ComeToLeech.model;

public enum Category {
    // VEHICULE
    VEHICULE_4x4("4x4"),
    VOITURES("Voitures"),
    MOTOS("Motos & scooters"),
    PIECES_DETACHEES("Pièces détachées"),
    VEHICULE_DIVERS("Divers (VEHICULES)"),
    
    // NAUTISME
    VOILIERS("Voiliers"),
    BATEAUX_MOTEUR("Bateaux moteur"),
    NAUTISME_SPORT_LOISIRS("Sport & loisirs"),
    PECHE("Pêche"),
    NAUTISME_DIVERS("Divers (NAUTISME)");

    public String libelle;

    Category(String l) {
        libelle = l;
    }
}
