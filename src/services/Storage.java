package services;
/*
 * Projet SIT, @copyright 2015 SAGEM DS
 *
 * Les informations contenues dans ce fichier sont la propriété de
 * SAGEM DS et diffusées à titre confidentiel dans un but spécifique.
 * Le destinataire assure la garde et la surveillance de ce fichier et
 * convient qu'il ne sera ni copié ni reproduit en tout ou partie et
 * que son contenu ne sera révélé en aucune manière à aucune personne,
 * excepté pour répondre au but pour le quel il a été transmis.
 * Cette recommandation est applicable à tous les documents générés à
 * partir de ce fichier.
 */

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @author local
 */
public class Storage
{

 // enregistrement dans dans un fichier de sauvegarde, lecture.

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable()
    {
        final String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable()
    {
        final String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
            || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }

    /**
     * Suprime le fichier existant.
     * Ecrit les emplois du temps dans un nouveau fichier pour sauvegarde.
     * @param emplois
     */
    public static void writeFriseFile(final byte[] emplois)
    {
        // Get the directory for the user's public text document directory.
        try
        {
            if (isExternalStorageWritable())
            {

                deleteFriseFile();
                // This will get the SD Card directory and create a folder named
                // MyFiles in it.
                final File sdCard = Environment.getExternalStorageDirectory();
                final File directory = new File(sdCard.getAbsolutePath() + "/FilesFrise");
                directory.mkdirs();

                // Now create the file in the above directory and write the
                // contents into it == Serialization
                final File file = new File(directory, "frise.txt");
                final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(emplois);
                oos.flush();
                oos.close();
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Lit le contenut du fichier
     * @return les emplois du temps en byte[] ou null si le fichier n'existe pas/ est vide
     */
    public static byte[] readFriseFile()
    {
        try
        {
            if (isExternalStorageReadable())
            {
                final File sdCard = Environment.getExternalStorageDirectory();
                final File directory = new File(sdCard.getAbsolutePath() + "/FilesFrise");
                directory.mkdirs();
                final File file = new File(directory, "frise.txt");
                if (file.exists())
                {
                    // deserialization
                    final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    byte[] emplois = null;
                    emplois = (byte[]) ois.readObject();
                    ois.close();
                    return emplois;
                }
                else
                {
                    return null;
                }
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * supprime le fichier
     */
    private static void deleteFriseFile()
    {
        final File sdCard = Environment.getExternalStorageDirectory();
        final File directory = new File(sdCard.getAbsolutePath() + "/FilesFrise");
        final File file = new File(directory, "frise.txt");
        if (file.exists())
        {
            file.delete();
        }

    }

}
