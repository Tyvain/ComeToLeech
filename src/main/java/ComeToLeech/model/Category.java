package ComeToLeech.model;

public enum Category {
    VEHICULE_4x4("4x4"),
    VOITURES("Voitures"),
    MOTOS("Motos & scooters"),
    PIECES_DETACHEES("Pièces détachées"),
    VEHICULE_DIVERS("Divers (VEHICULES)");

    public String libelle;

    Category(String l) {
        libelle = l;
    }
}
