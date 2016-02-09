package modele;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.Atlas.framework.R;

import services.Storage;


public class EmploiDuTemps implements Serializable
{

    private static final long serialVersionUID = -1182694942750951619L;

    private long id;// id de l'edt dans la nase de donnees.

    private ArrayList<Task> emploi;

    private String nomEnfant;

    private ArrayList<HeuresMarquees> marqueTemps;

    private boolean isValid;

    /**
     * @param id
     * @param myTasks
     * @param nom
     * @param marqueTemps
     */
    public EmploiDuTemps(final long id, final ArrayList<Task> myTasks, final String nom,
        final ArrayList<HeuresMarquees> marqueTemps)
    {
        this.id = id;
        nomEnfant = nom;
        emploi = myTasks;
        this.marqueTemps = marqueTemps;
        isValid = true;
    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public boolean isValid()
    {
        return isValid;
    }

    public void setValid(final boolean isValid)
    {
        this.isValid = isValid;
    }

    public ArrayList<HeuresMarquees> getMarqueTemps()
    {
        return marqueTemps;
    }

    public void setMarqueTemps(final ArrayList<HeuresMarquees> marqueTemps)
    {
        this.marqueTemps = marqueTemps;
    }

    public ArrayList<Task> getEmploi()
    {
        return emploi;
    }

    public String getNomEnfant()
    {
        return nomEnfant;
    }

    public void setEmploi(final ArrayList<Task> emploi)
    {
        this.emploi = emploi;
    }

    public void setNomEnfant(final String nomEnfant)
    {
        this.nomEnfant = nomEnfant;
    }

    /**
     * methode d'usage graphique pour combler les trous des emplois du temps par des temps "libres"
     * Ne modifie ni le fichier de sauvegarde, ni la base de donnees
     */
    public void fillHoles()
    {
        for (int i = 0; i < emploi.size() - 1; i++)
        {
            final double hDebut = emploi.get(i).getHeureFin();
            final double hFin = emploi.get(i + 1).getHeureDebut();
            if (hDebut < hFin)
            {
                final Task libre =
                    new Task(-1, nomEnfant, "Temps libre", "On fait ce qu'on veut", hDebut,
                        hFin, "etoile", R.color.blanc);
                emploi.add(i + 1, libre);
            }
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////
    // Serialization / Deserialization
    // ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Serialize une liste d'emplois du temps
     * @param emplois
     * @return un byte[] contenant les emplois ou null si une exception est levee
     */
    public static byte[] serialize(final ArrayList<EmploiDuTemps> emplois)
    {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try
        {
            out = new ObjectOutputStream(bos);
            out.writeObject(emplois);
            bytes = bos.toByteArray();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                bos.close();
            }
            catch (final IOException e)
            {
                e.printStackTrace();
            }
        }
        return bytes;

    }

    /**
     * Deserialize un byte[] (depuis le fichier de sauvegarde si null)
     * @param b les byte a deserialiser ou null si on deserialise deuis le fichier
     * @return les emplois du temps sauvegardes ou une liste vide si une erreur survient
     * ou si rien n'est lu (pas de donnees)
     */
    public static ArrayList<EmploiDuTemps> deserialize(final byte[] b)
    {
        byte[] bytes = b;
        if (bytes == null)
        {
            bytes = Storage.readFriseFile();
        }
        ArrayList<EmploiDuTemps> emplois = new ArrayList<EmploiDuTemps>();
        if (bytes != null)
        {
            final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInput in = null;
            try
            {
                in = new ObjectInputStream(bis);
                emplois = (ArrayList<EmploiDuTemps>) in.readObject();
            }
            catch (final IOException e)
            {
                e.printStackTrace();
            }
            catch (final ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            finally
            {
                try
                {
                    if (in != null)
                    {
                        in.close();
                    }
                    bis.close();
                }
                catch (final IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return emplois;
    }

    public static EmploiDuTemps emploiTest(final String nom)
    {
    	final int[] colorsTab = Task.getColorTab();
        final ArrayList<Task> tasks = new ArrayList<Task>();
        final ArrayList<HeuresMarquees> heuresMarquees = new ArrayList<HeuresMarquees>();
        heuresMarquees.add(new HeuresMarquees(1, 7.0, nom));
        heuresMarquees.add(new HeuresMarquees(2, 12.0, nom));
        heuresMarquees.add(new HeuresMarquees(3, 14.0, nom));
        heuresMarquees.add(new HeuresMarquees(4, 17.0, nom));
        heuresMarquees.add(new HeuresMarquees(5, 19.0, nom));
        heuresMarquees.add(new HeuresMarquees(6, 20.5, nom));

        final Task t1 = new Task(1, nom, "Accueil", "On arrive a l'ecole", 8.0, 9.0, "maison", colorsTab[0]);
        tasks.add(t1);
        final Task t2 = new Task(2, nom, "Sport", "On joue au foot en equipe", 9.0, 10.5, "jeu", colorsTab[1]);
        tasks.add(t2);
        final Task t3 = new Task(3, nom, "Lecon", "On apprend a lire", 10.5, 11.5, "cours", colorsTab[2]);
        tasks.add(t3);
        final Task t4 =
            new Task(4, nom, "Temps libre", "On fait ce qu'on veut", 11.5, 12.0, "etoile", colorsTab[3]);
        tasks.add(t4);
        final Task t5 =
            new Task(5, nom, "Cantine", "On se lave les mains", 12.0, 13.0, "dejeuner", colorsTab[4]);
        tasks.add(t5);
        final Task t6 =
            new Task(6, nom, "Temps calme", "On s'amuse calmement", 13.0, 14.0, "maison", colorsTab[5]);
        tasks.add(t6);
        final Task t7 = new Task(7, nom, "Sortie", "On va au cinema", 14.0, 16.0, "dodo", colorsTab[6]);
        tasks.add(t7);
        final Task t8 = new Task(8, nom, "Chant", "La chorale de l'ecole !", 16.0, 17.0, "cours", colorsTab[7]);
        tasks.add(t8);
        final Task t9 =
            new Task(9, nom, "Temps libre", "On joue librement", 17.0, 17.5, "etoile", colorsTab[8]);
        tasks.add(t9);
        final Task t10 = new Task(10, nom, "Parents", "La venue des parents", 17.5, 19.0, "jeu", colorsTab[9]);
        tasks.add(t10);
        final Task t11 = new Task(11, nom, "Repas", "Le repas du soir", 19.0, 20.0, "dejeuner", colorsTab[10]);
        tasks.add(t11);
        final Task t12 =
            new Task(12, nom, "Temps libre", "On se lave les dents !", 20.0, 20.5, "etoile", colorsTab[11]);
        tasks.add(t12);
        final Task t13 = new Task(13, nom, "Dodo", "On va se coucher", 20.5, 23.5, "dodo", colorsTab[12]);
        tasks.add(t13);
        ArrayList<Task> tut = new ArrayList<Task>();
        tut.add(t1);
        tut.add(t2);
        tut.add(t3);
        tut.add(t4);
        tut.add(t5);
        tut.add(t7);
        tut.add(t8);
        return new EmploiDuTemps(-1232, tut, nom, heuresMarquees);
    }
}
