package modele;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * The contract between clients and the database content provider.
 */
public final class DataBaseAccess {

	/** Autorité de ce fournisseur. */
	public static final String AUTHORITY = "com.example.basededonnees.DataBaseProvider";

	/** URI */
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

	/**
	 * Constantes pour la table des emplois du temps du provider.
	 */
	public static final class Emploi implements BaseColumns {
		/**
		 * The content URI for this table.
		 */
		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				DataBaseAccess.CONTENT_URI, DbSchema.EDT_TABLE_NAME);
		/**
		 * The mime type of a directory of items.
		 */
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.EDT_TABLE_NAME;

		/**
		 * The mime type of a single item.
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.EDT_TABLE_NAME;
		/**
		 * A projection of all columns in the table.
		 */
		public static final String[] PROJECTION_ALL = { _ID, DbSchema.EDT_NOM,
				DbSchema.EDT_VALIDE };

	}

	/**
	 * Constantes pour la table des activites du provider.
	 */
	public static final class Activite implements BaseColumns {
		/**
		 * The content URI for this table.
		 */
		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				DataBaseAccess.CONTENT_URI, DbSchema.TASK_TABLE_NAME);
		/**
		 * The mime type of a directory of items.
		 */
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.TASK_TABLE_NAME;

		/**
		 * The mime type of a single item.
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.TASK_TABLE_NAME;
		/**
		 * A projection of all columns in the table.
		 */
		public static final String[] PROJECTION_ALL = { _ID, DbSchema.EDT_NOM,
				DbSchema.TASK_NOM, DbSchema.TASK_DEBUT, DbSchema.TASK_FIN,
				DbSchema.TASK_IMAGE, DbSchema.TASK_COULEUR,
				DbSchema.TASK_DESCRIPTION };

	}

	/**
	 * Constantes pour la table des heures du provider.
	 */
	public static final class Heure implements BaseColumns {
		/**
		 * The content URI for this table.
		 */
		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				DataBaseAccess.CONTENT_URI, DbSchema.HEUREM_TABLE_NAME);
		/**
		 * The mime type of a directory of items.
		 */
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.HEUREM_TABLE_NAME;

		/**
		 * The mime type of a single item.
		 */
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/vnd." + AUTHORITY + "." + DbSchema.HEUREM_TABLE_NAME;
		/**
		 * A projection of all columns in the table.
		 */
		public static final String[] PROJECTION_ALL = { _ID, DbSchema.EDT_NOM,
				DbSchema.HEUREM_HEURE };

	}

}
