package it.uniroma2.dicii.ispw.enumeration;

public enum IssueState {
    NEW,        // guasto appena inserito
    CONFIRMED,  // guasto confermato dalla segreteria
    CANCELED,   // guasto cancellato dalla segreteria
    TAKEN,      // guasto preso in carico dai tecnici
    REPAIRING,  // guasto in riparazione da parte dei tecnici
    TESTING,    // guasto sotto test da parte dei tecnici
    REJECTED,   // riparazione respinta per qualche motivo da parte dei tecnici
    REPAIRED    // oggetto riparato
}
