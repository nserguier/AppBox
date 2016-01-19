package services;

/*
 * Projet SIT, @copyright 2015 SAGEM DS
 * Les informations contenues dans ce fichier sont la propriété de
 * SAGEM DS et diffusées à titre confidentiel dans un but spécifique.
 * Le destinataire assure la garde et la surveillance de ce fichier et
 * convient qu'il ne sera ni copié ni reproduit en tout ou partie et
 * que son contenu ne sera révélé en aucune manière à aucune personne,
 * excepté pour répondre au but pour le quel il a été transmis.
 * Cette recommandation est applicable à tous les documents générés à
 * partir de ce fichier.
 */

/**
 * @author local
 */
public interface Bluetooth_Constants
{

    // Message types sent from the CommService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;

    public static final int MESSAGE_READ = 2;

    public static final int MESSAGE_WRITE = 3;

    public static final int MESSAGE_DEVICE_NAME = 4;

    public static final int MESSAGE_TOAST = 5;

    // Key names received from the CommService Handler
    public static final String DEVICE_NAME = "device_name";

    public static final String TOAST = "toast";
}
