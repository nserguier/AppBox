package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import com.Atlas.framework.R;

public class Task implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 6170024542231825446L;

	// attributs de base de donnees
	private int id; // ID de l'activite

	private String nomEdt;// Nom de l'EDT contenant cette activite

	private String nom; // nom de l'activite

	private String description; // description de l'activite en quelques mots

	private double duree; // duree de l'activite en heure

	private double heureDebut; // heure du debut de l'activite

	private String nomImage; // une image illustrant l'activite

	/** l'id de la couleur choisie, a recuperer avec getRessources().getColor() */
	private int couleur;

	/** le tableau contenant toutes les couleurs possibles. */
	static final int[] colorTab = { R.color.light_green2, R.color.green2,
			R.color.teal2, R.color.cyan2, R.color.light_blue2, R.color.blue2,
			R.color.indigo2, R.color.deep_purple2, R.color.purple2,
			R.color.pink2, R.color.red2, R.color.deep_orange2, R.color.orange2,
			R.color.amber2, R.color.yellow2, R.color.yellow7 };

	/**
	 * 
	 * Constructor.
	 * 
	 * @param id
	 * @param nomEdt
	 * @param nom
	 * @param description
	 * @param duree
	 * @param heureDebut
	 * @param nomImage
	 * @param couleur
	 */
	public Task(final int id, final String nomEdt, final String nom,
			final String description, final double duree,
			final double heureDebut, final String nomImage, final int couleur) {
		this.id = id;
		this.nomEdt = nomEdt;
		this.nom = nom;
		this.description = description;
		this.duree = duree;
		this.heureDebut = heureDebut;
		this.nomImage = nomImage;
		this.couleur = couleur;

	}

	public static int[] getColorTab() {
		return colorTab;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getEdt_name() {
		return nomEdt;
	}

	public void setEdt_name(final String edt_name) {
		nomEdt = edt_name;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public double getDuree() {
		return duree;
	}

	public double getHeureDebut() {
		return heureDebut;
	}

	public double getHeureFin() {
		return heureDebut + duree;
	}

	public String getImage() {
		return nomImage;
	}
	
	/**
	 * l'id de la couleur choisie, a recuperer avec getRessources().getColor() 
	 *
	 * @return
	 */
	public int getCouleur() {
		return couleur;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setDuree(final double d) {
		duree = d;
	}

	public void setHeureDebut(final double heureDebut) {
		this.heureDebut = heureDebut;
	}

	public void setImage(final String nomImage) {
		this.nomImage = nomImage;
	}

	public void setCouleur(final int couleur) {
		this.couleur = couleur;
	}

	// ////////////////////////////////////////////////////
	// // Comparaisons d'activite selon leurs heures
	// ////////////////////////////////////////////////////

	/**
	 * Retourne 1 si t est avant l'activite (true). Retourne 0 si t est apres
	 * l'activite (false). Retourne -1 si les activites sont incoherentes
	 * (erreur).
	 * 
	 * @param t
	 *            l'activite avec qui l'on se compare
	 * @return -1, 0, 1
	 */
	private int isAfter(final Task t) {
		final double tFin = t.getHeureFin();
		final double tDebut = t.getHeureDebut();

		// compare this task with t :
		// comp1 > 0 : this after t,
		// comp1 ==0 : this before t,
		// comp1 < 0 : this before t,
		final int comp1 = (int) Math.signum(getHeureFin() - tDebut);
		// compare t with this task, should be the opposite sign or 0
		final int comp2 = (int) Math.signum(tFin - getHeureDebut());

		if (comp1 > 0) {
			if (comp2 <= 0) {
				return 1;
				// comp2>0 = incoherent
			} else {
				return -1;
			}
		}
		if (comp1 < 0) {
			if (comp2 >= 0) {
				return 0;
				// comp2<0 = incoherent
			} else {
				return -1;
			}
		}
		return 0;

	}

	/**
	 * Trie une ArrayList<Task> et renvoie cette liste dans l'ordre des
	 * activites (meme si la liste est vide) en revanche renvoie null si des
	 * activite se chevauchent et ne sont pas coherentes (voir heure de debut et
	 * de fin)
	 * Change les id des activites pour faciliter le trie de la base de donnees
	 * 
	 * @param list
	 *            La liste triee par "tri a bulle"
	 * @return
	 */
	public static ArrayList<Task> trierTask(final ArrayList<Task> list) {

		if (!list.isEmpty()) {

			boolean estTriee = false;
			while (!estTriee) {
				estTriee = true;
				for (int i = 0; i < list.size() - 1; i++) {
					final Task t = list.get(i);

					final Task prochaine = list.get(i + 1);
					// un element n'est pas a sa place, la liste n'est pas
					// encore triee
					// on change de place l'element
					if (t.isAfter(prochaine) == 1) {
						Collections.swap(list, i, i + 1);
						estTriee = false;
						// incoherence, on cesse tout et on renvoie null
					} else if (t.isAfter(prochaine) == -1) {
						return null;
					}

				}
			}

		}

		return list;

	}

	// ///////////////////////////////////////////////////////
	// // Methodes de placement des Task sur la frise
	// ///////////////////////////////////////////////////////

	/**
	 * Donne la position en pixel du debut de l'activite sur la frise
	 * 
	 * @param W
	 *            longueur de la frise en pixel
	 * @param h0
	 *            heure de debut de la frise
	 * @param h1
	 *            heure de fin de la frise
	 * @return la position du debut
	 */
	public int getXbegin(final int W, final double h0, final double h1) {
		return (int) ((W / (h1 - h0)) * heureDebut - (h0 * W) / (h1 - h0));
	}

	/**
	 * Donne la position en pixel du debut de l'activite sur la frise
	 * 
	 * @param W
	 *            longueur de la frise en pixel
	 * @param h0
	 *            heure de debut de la frise
	 * @param h1
	 *            heure de fin de la frise
	 * @return la position du debut
	 */
	public int getXbegin(final int W, final double h0, final double h1,
			final int margin, final ArrayList<Task> myTasks) {
		final int index = Task.indexOfTask(myTasks, this);
		return (int) ((W / (h1 - h0)) * heureDebut - (h0 * W) / (h1 - h0))
				- index * margin;
	}

	/**
	 * Donne la position en pixel du debut de l'activite sur la frise
	 * 
	 * @param W
	 *            longueur de la frise en pixel
	 * @param h0
	 *            heure de debut de la frise
	 * @param h1
	 *            heure de fin de la frise
	 * @return la position du debut
	 */
	public static int getXHour(final int W, final double h0, final double h1,
			final double heure) {
		return (int) ((W / (h1 - h0)) * heure - (h0 * W) / (h1 - h0));
	}

	/**
	 * Donne la largeur en pixel de l'activite sur la frise
	 * 
	 * @param W
	 *            longueur de la frise en pixel
	 * @param h0
	 *            heure de debut de la frise
	 * @param h1
	 *            heure de fin de la frise
	 * @return la largeur
	 */
	public int getXwidth(final int W, final double h0, final double h1,
			final int margin) {
		return (int) ((duree * W) / (h1 - h0)) - margin;
	}

	/**
	 * Donne la position en pixel du milieu de l'activite sur la frise
	 * 
	 * @param W
	 *            longueur de la frise en pixel
	 * @param h0
	 *            heure de debut de la frise
	 * @param h1
	 *            heure de fin de la frise
	 * @return la largeur du milieu
	 */
	public int getMiddle(final int W, final double h0, final double h1) {
		final int begin = getXbegin(W, h0, h1);
		final int width = getXwidth(W, h0, h1, 0);
		return begin + width / 2;
	}

	/**
	 * Trouve une activite dans un planning relativement a une activite donne
	 * 
	 * @param myTasks
	 *            Le planning
	 * @param t
	 *            L'activite de reference
	 * @param pas
	 *            la distance relative a laquelle on veut trouver l'autre
	 *            activite
	 * @return
	 */
	public static Task findRelativeTask(final ArrayList<Task> myTasks,
			final Task t, final int pas) {

		/* Retrouve l'indice de l'activite de reference dans le planning */
		final int id = indexOfTask(myTasks, t);

		/* Renvoie la bonne activite */
		if ((id + pas >= 0) && (id + pas < myTasks.size())) {
			return myTasks.get(id + pas);
		}
		/* Renvoie null si l'activite n'existe pas */
		else {
			return null;
		}
	}

	/**
	 * Trouve l'indice d'une activite dans un planning
	 * 
	 * @param myTasks
	 *            Le planning
	 * @param t
	 *            L'activite
	 * @return l'indice
	 */
	public static int indexOfTask(final ArrayList<Task> myTasks, final Task t) {

		/* Retrouve l'indice de l'activite de reference dans le planning */
		int id = 0;
		for (int i = 0; i < myTasks.size(); i++) {
			if (myTasks.get(i) == t) {
				id = i;
			}
		}

		/* Renvoie la bonne activite */
		return id;
	}

}
