package modele;

import java.io.Serializable;

public class HeuresMarquees implements Serializable{


	private static final long serialVersionUID = -6117788630352741640L;
	
	private int id;// ID de la base de donnees
	private double heure_marquee; // l'heure a marquer
	private String nomEdt; // le nom de l'edt

	/**
	 * @param id
	 * @param heure_marquee
	 * @param nomEdt
	 */
	public HeuresMarquees(int id, double heure_marquee, String nomEdt) {
		this.id = id;
		this.heure_marquee = heure_marquee;
		this.nomEdt = nomEdt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeure_marquee() {
		return heure_marquee;
	}

	public void setHeure_marquee(double heure_marquee) {
		this.heure_marquee = heure_marquee;
	}

	public String getNomEdt() {
		return nomEdt;
	}

	public void setNomEdt(String nomEdt) {
		this.nomEdt = nomEdt;
	}

	public static String toString(final double heure) {
		final String h = String.valueOf((int) heure);
		final int m = (int) (60 * (heure - (int) heure));
		if (m == 0) {
			return h + "h";
		}
		final String min = String.valueOf(m);
		return h + "h" + min;
	}
	
	public static String toSpeak(final double heure){
		final String h = String.valueOf((int) heure);
		final int m = (int) (60 * (heure - (int) heure));
		if (m == 0) {
			return "Il est " +h + " heure.";
		}
		final String min = String.valueOf(m);
		return "Il est "+h + " heure et " + min+" minutes.";
	}

	public static double traductionHeure(final String h) {
		final String[] sep = h.split("h");
		final int heure = Integer.valueOf(sep[0]);
		double minute = 0;
		if (sep.length >= 2) {
			final int valeur = Integer.valueOf(sep[1]);
			minute = valeur / 60.0;
		}
		return heure + minute;
	}
	
	public static boolean isValidHeure(final String h)
    {

        if (!h.contains("h") && !h.startsWith("h"))
        {
            return false;
        }
        final String[] sep = h.split("h");

        // verifie que l'heure est un nombre compris entre 0 et 24
        int i;
        try
        {
            i = Integer.parseInt(sep[0]);
        }
        catch (final NumberFormatException e)
        {
            return false;
        }
        if (i < 0 || i > 23)
        {
            return false;
        }
        // verifie que les minutes sont un nombre compris entre 0 et 59
        if (sep.length == 2)
        {
            try
            {
                i = Integer.parseInt(sep[1]);
            }
            catch (final NumberFormatException e)
            {
                return false;
            }
            if (i < 0 || i > 59)
            {
                return false;
            }
        }

        return true;
    }

}
