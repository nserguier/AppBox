package modele;


/**
 * A helper interface which defines constants for work with the DB.
 *
 */
public interface DbSchema {

	String DB_NAME = "database.db";
	
	// attributes of all tables
	/** ID EDT */
	public static final String ID = "_id";
	
	/** Nom de l'EDT */
	public static final String EDT_NOM = "nom_edt";
	/** Validite de l'edt.*/
	public static final String EDT_VALIDE = "valide";

	/** Nom de l'activite. */
	public static final String TASK_NOM = "activite";
	/** Heure de debut de l'activite. */
	public static final String TASK_DEBUT = "debut";
	/** Heure de fin de l'activite. */
	public static final String TASK_FIN = "fin";
	/** Image de l'activite. */
	public static final String TASK_IMAGE = "image";
	/** Couleur de l'activite. */
	public static final String TASK_COULEUR = "couleur";
	/** Descritpion de l'activite. */
	public static final String TASK_DESCRIPTION = "description";

	/** Heure marquee. */
	public static final String HEUREM_HEURE = "heure";

	// name of tables
	/** Table avec les EDT. */
	public static final String EDT_TABLE_NAME = "emploi_du_temps";
	/** Table avec les activites. */
	public static final String TASK_TABLE_NAME = "activites";
	/** Table avec les heures marquees. */
	public static final String HEUREM_TABLE_NAME = "heures_marquees";

	// creation of tables
	/** Creation de la table des EDT. */
	public static final String EDT_TABLE_CREATE = "CREATE TABLE "
			+ EDT_TABLE_NAME + " (" + ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + EDT_NOM
			+ " TEXT NOT NULL, "+EDT_VALIDE +" INTEGER);";
	/** Creation de la table des activites. */
	public static final String TASK_TABLE_CREATE = "CREATE TABLE "
			+ TASK_TABLE_NAME + " (" + ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + EDT_NOM + " TEXT, "
			+ TASK_NOM + " TEXT, " + TASK_DEBUT + " REAL, " + TASK_FIN
			+ " REAL, " + TASK_IMAGE + " TEXT, " + TASK_COULEUR + " INTEGER, "
			+ TASK_DESCRIPTION + " TEXT);";
	/** Creation de la table des heures marquees. */
	public static final String HEUREM_TABLE_CREATE = "CREATE TABLE "
			+ HEUREM_TABLE_NAME + " (" + ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + EDT_NOM + " TEXT, "
			+ HEUREM_HEURE + " REAL);";

	// suppression des tables ancienne lors de la MAJ
	/** Suppression de la table des EDT. */
	public static final String EDT_TABLE_DROP = "DROP TABLE IF EXISTS "
			+ EDT_TABLE_NAME + ";";
	/** Suppression de la table des activites. */
	public static final String TASK_TABLE_DROP = "DROP TABLE IF EXISTS "
			+ TASK_TABLE_NAME + ";";
	/** Suppression de la table des heures marquees. */
	public static final String HEUREM_TABLE_DROP = "DROP TABLE IF EXISTS "
			+ HEUREM_TABLE_NAME + ";";
	
	
}
