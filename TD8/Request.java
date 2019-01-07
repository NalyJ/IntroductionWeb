import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

/**
 * Requête.
 * @author Joao Brilhante.
 * @version 1.0
 */
public class Request {
	/**
	 * Le méthode de la requête.
	 * 0 = Requête GET.
	 * 1 = Requête POST.
	 */
	private int requestMethod;

	/**
	 * La taille du corps de la requête.
	 */
	private int requestLength;

	/**
	 * Le nom de domaine du serveur.
	 */
	private String host;

	/**
	 * Le fichier de la ressource.
	 */
	private File file;

	/**
	 * L'URI de la ressource.
	 */
	private String ressource;

	/**
	 * Le statut de la ressource.
	 * -1 = Ressource non trouvée.
	 * 0 = Ressource trouvée.
	 * 1 = Ressource déplacée.
	 * 2 = Ressource fichier trouvée.
	 * 3 = Ressource dossier trouvée.
	 */
	private int ressourceStatus;

	/**
	 * Le corps de la réponse.
	 */
	private String responseData;

	/**
	 * Le type de la réponse.
	 */
	private String responseType;

	/**
	 * Le tableau de conversion des types de ressources.
	 */
	private HashMap<String, String> mimetypes;

	/**
	 * Constructeur d'une requête.
	 * @param requestMethod La méthode de la requête.
	 * @param ressource L'URI de la ressource.
	 */
	public Request(int requestMethod, String ressource) {
		// Initialisation du type de la requête.
		this.requestMethod = requestMethod;

		// Initialisation de la taille de la requête.
		this.requestLength = 0; // Pas de contenu, par défaut.

		// Initialisation du nom de domaine du serveur.
		this.host = ""; // Pas de host, par défaut.

		// Initialisation de l'URI de la ressource.
		this.ressource = ressource;

		// Initialisation du statut de la ressource.
		this.ressourceStatus = -1; // Non trouvée, par défaut.

		// Initialisation du corps de la réponse.
		this.responseData = ""; // Pas de contenu, par défaut.

		// Initialisation du type de la réponse.
		this.responseType = "text/plain"; // Texte clair, par défaut.

		// Initialisation du tableau de conversion des types.
		this.mimetypes = new HashMap<>();
		this.mimetypes.put("txt", "text/plain");
		this.mimetypes.put("html", "text/html");
		this.mimetypes.put("jpg", "image/jpeg");
		this.mimetypes.put("png", "image/png");
		this.mimetypes.put("mpeg", "video/mpeg");
		this.mimetypes.put("pdf", "application/pdf");
		this.mimetypes.put("css", "text/css");
	}

	/**
	 * Constructeur d'une requête.
	 * @param requestMethod La méthode de la requête.
	 */
	public Request(int requestMethod) {
		this(requestMethod, "/");
	}

	/**
	 * Constructeur d'une requête.
	 */
	public Request() {
		this(1, "/");
	}

	/**
	 * Récupère la méthode de la requête.
	 * @return La méthode de la requête.
	 */
	public int getRequestMethod() {
		return this.requestMethod;
	}

	/**
	 * Vérifie s'il s'agit d'une requête GET.
	 * @return <code>true</code> s'il s'agit d'une requête GET.
	 */
	public boolean isGetRequest() {
		return this.requestMethod == 0;
	}

	/**
	 * Vérifie s'il s'agit d'une requête POST.
	 * @return <code>true</code> s'il s'agit d'une requête POST.
	 */
	public boolean isPostRequest() {
		return this.requestMethod == 1;
	}

	/**
	 * Récupère la taille de la requête.
	 * @return La taille de la requête.
	 */
	public int getRequestLength() {
		return this.requestLength;
	}

	/**
	 * Définit la taille de la requête.
	 * @param requestLength La taille de la requête.
	 */
	public void setRequestLength(int requestLength) {
		this.requestLength = requestLength;
	}

	/**
	 * Récupère le nom de domaine du serveur.
 	* @return Le nom de domaine du serveur.
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 * Définit le nom de domaine du serveur.
	 * @param host Le nom de domaine du serveur.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Récupère l'URI de la ressource.
	 * @return L'URI de la ressource.
	 */
	public String getRessource() {
		return this.ressource;
	}

	/**
	 * Récupère le statut de la ressource.
	 * @return Le statut de la ressource.
	 */
	public int getStatus() {
		return this.ressourceStatus;
	}

	/**
	 * Récupère le fichier de la ressource.
	 * @return Le fichier de la ressource.
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * Récupère le corps de la réponse.
	 */
	public String getResponseData() {
		return this.responseData;
	}

	/**
	 * Recherche et mets à jour le statut de la ressource.
	 */
	public void findRessource() throws IOException {
		// Dossier date.
		if (this.ressource.equals("/date/")) {
			findDateFolder();
			return;
		}

		// Fichier date.
		if (this.ressource.equals("/date")) {
			findDateFile();
			return;
		}

		// Initialisation du chemin d'accès au fichier.
		String filePath = getFilePath();

		// Initialisation du fichier.
		this.file = new File(filePath);

		// Fichier trouvé.
		if (file.exists()) {
			// Dossier.
			if (file.isDirectory()) {
				findDirectory(file);
				return;
			}

			// Fichier.
			else {
				findFile(file);
				return;
			}
		}

		else {
			// Ressource non trouvée.
			this.ressourceStatus = -1;
			return;
		}
	}

	/**
	 * Recherche le dossier date et mets à jour le statut de la ressource.
	 */
	private void findDateFolder() {
		// Ressource trouvée.
		this.ressourceStatus = 0;

		// Récupération de la date.
		this.responseData = getDate();
	}

	/**
	 * Recherche le fichier date et mets à jour le statut de la ressource.
	 */
	private void findDateFile() {
		// Ressource déplacée.
		this.ressourceStatus = 1;

		// Mise à jour de l'URI de la ressource.
		this.ressource = "/date/";
	}

	/**
	 * Récupère le chemin d'accès au fichier.
	 * @return Le chemin d'accès au fichier.
	 */
	private String getFilePath() {
		// Initialisation du chemin d'accès au fichier.
		String filePath = this.ressource;

		// Si le chemin d'accès commence par un slash.
		if (filePath.startsWith("/")) {
			// Suprression du premier caractère.
			filePath = filePath.substring(1);
		}

		return filePath;
	}

	/**
	 * Récupère l'extension du fichier.
	 * @return L'extension du fichier.
	 */
	private String getFileExtension(File file) {
		// Initialisation de l'extension du fichier.
		String fileExtension = null;

		// Récupération du nom du fichier.
		String fileName = file.getName();

		// Récupération de la position du dernier point dans le nom du fichier.
		int index = fileName.lastIndexOf(".");

		// Si le nom du fichier contient un point.
		if (index != -1 && index != 0) {
			// Récupération de l'extension qui suit le point.
			fileExtension = fileName.substring(index + 1);
		}

		return fileExtension;
	}

	/**
	 * Recherche un fichier et mets à jour le statut de la ressource.
	 */
	private void findFile(File file) throws IOException {
		// Ressource fichier trouvée.
		this.ressourceStatus = 2;

		// Initialisation de l'extension du fichier.
		String fileExtension = getFileExtension(file);

		// Si le nom du fichier contient une extension.
		if (fileExtension != null) {
			// Récupération du type de réponse correspond à l'extension.
			String mimetype = mimetypes.get(fileExtension);

			// Si le type de réponse existe.
			if (mimetype != null) {
				this.responseType = mimetype;
			}
		}
	}

	/**
	 * Recherche un dossier et mets à jour le statut de la ressource.
	 */
	private void findDirectory(File file) {
		if (!this.ressource.endsWith("/")) {
			// Ressource déplacée.
			this.ressourceStatus = 1;

			// Mise à jour de l'URI de la ressource.
			this.ressource += "/";
		} else {
			String[] extensions = {"html", "pdf", "txt"};

			for (String extension : extensions) {
				File indexFile = new File("index." + extension);

				if (indexFile.exists()) {
					this.file = indexFile;

					// Ressource déplacée.
					this.ressourceStatus = 2;
					return;
				}
			}
			this.ressourceStatus = 0;
		}
	}

	/**
	 * Récupère le code de la réponse.
	 * @return Le code de la réponse.
	 */
	public String getResponseCode() {
		// Initialisation du code de la réponse.
		String replyCode;

		// Ressource trouvée.
		if (this.ressourceStatus == 0 || this.ressourceStatus == 2) {
			replyCode = "200 OK";
		}

		// Ressource déplacée.
		else if (this.ressourceStatus == 1) {
			replyCode = "301 Moved Permanently";
		}

		// Ressource non trouvée.
		else {
			replyCode = "404 Not Found";
		}

		// On retourne le code de la réponse.
		return replyCode;
	}

	/**
	 * Récupère la date actuelle formatée.
	 * @return La date actuelle formatée.
	 */
	public String getDate() {
		// Initialisation du formateur de date.
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MM yyyy HH:mm:ss");

		// Initialisation de la date.
		Date date = new Date();

		// On retourne la date formatée.
		return dateFormat.format(date);
	}

	/**
	 * Récupère le type du corps de la réponse.
	 * @return Le type du corps de la réponse.
	 */
	public String getResponseType() {
		return this.responseType;
	}

	/**
	 * Récupère la taille du corps de la réponse.
	 * @return La taille du corps de la réponse.
	 */
	public int getResponseLength() {
		// Ressource non trouvée.
		if (this.ressourceStatus == -1) {
			// Message d'erreur.
			this.responseData = "La ressource '" + ressource + "' est introuvable.";
		}

		else if (this.ressourceStatus == 2) {
			return (int) this.file.length();
		}

		// On renvoit la taille du corps de la réponse.
		return this.responseData.length();
	}
}
