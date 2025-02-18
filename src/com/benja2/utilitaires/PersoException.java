
package com.benja2.utilitaires;

/**
 * Exception personnalisée pour capturer les erreurs générales et spécifiques liées à l'entité.
 */
public class PersoException extends Exception {

    /**
     * Constructeur de l'exception personnalisée.
     *
     * @param message Le message d'erreur.
     */
    public PersoException(String message) {
        super(message);
    }

    /**
     * Constructeur de l'exception personnalisée avec une cause spécifique.
     *
     * @param message Le message d'erreur.
     * @param cause   La cause de l'exception.
     */
    public PersoException(String message, Throwable cause) {
        super(message, cause);
    }
}
