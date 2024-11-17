package edu.fiuba.algo3;

public enum Palo {
    CORAZONES,
    PICAS,
    DIAMANTES,
    TREBOLES;

    public static Palo obtenerPaloDesdeString(String paloStr) {
        // Convertir la cadena a mayúsculas y compararla con las opciones válidas
        switch (paloStr.trim().toUpperCase()) {
            case "CORAZONES":
                return Palo.CORAZONES;
            case "PICAS":
                return Palo.PICAS;
            case "DIAMANTES":
                return Palo.DIAMANTES;
            case "TREBOL":
            case "TREBOLES":  // Agregar ambas variaciones posibles
                return Palo.TREBOLES;
            default:
                throw new IllegalArgumentException("Palo no reconocido: " + paloStr);
        }
    }
}
