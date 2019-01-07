import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

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
	 * Le corps de la requête.
	 */
	private String requestContent;

	/**
	 * Le nom de domaine du serveur.
	 */
	private String host;

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
	 */
	private int ressourceStatus;

	/**
	 * Le fichier de la ressource.
	 */
	private File file;

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

		// Initialisation du corps de la requêtes.
		this.requestContent = "";

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
		this.mimetypes = new HashMap<String, String>();
		this.mimetypes.put("txt", "text/plain");
		this.mimetypes.put("html", "text/html");
		this.mimetypes.put("jpeg", "image/jpeg");
		this.mimetypes.put("jpg", "image/jpeg");
		this.mimetypes.put("png", "image/png");
		this.mimetypes.put("mpg", "video/mpeg");
		this.mimetypes.put("mpeg", "video/mpeg");
		this.mimetypes.put("mp4", "video/mp4");
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
	 * Définit le corps de la requête.
	 * @param requestContent Le corps de la requête.
	 */
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
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
	 * Récupère le chemin d'accès au fichier.
	 * @param path Le chemin d'accès à la ressource.
	 * @return Le chemin d'accès au fichier.
	 */
	private String getFilePath(String path) {
		// Initialisation du chemin d'accès au fichier.
		String filePath = path;

		// Si le chemin d'accès commence par un slash.
		if (filePath.startsWith("/")) {
			// Suppression du premier caractère.
			filePath = filePath.substring(1);
		}

		// Ajout du préfixe du chemin d'accès au fichier.
		filePath = "./" + filePath;

		// On retourne le chemin d'accès au fichier.
		return filePath;
	}

	/**
	 * Décode les paramètres du corps de la requête.
	 * @return Le tableau de paramètres du corps de la requête.
	 */
	private HashMap<String, String> decodeRequestContent() {
		// Initialisation du tableau de paramètres.
		HashMap<String, String> params = new HashMap<String, String>();

		// Récupération des couples de paramètres.
		String[] paramValues = this.requestContent.split("&");

		// Pour chaque couple de paramètre.
		for (String paramValue : paramValues) {
			// Récupération de l'indice et de la valeur.
			String[] pair = paramValue.split("=");

			// Si le couple de paramètre est complet.
			if (pair.length == 2) {
				// Ajout du paramètre.
				params.put(pair[0], pair[1]);
			} else {
				// Ajout de l'indice.
				params.put(pair[0], null);
			}
		}

		// On retourne le tableau de paramètres.
		return params;
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

		// On retourne l'extension du fichier.
		return fileExtension;
	}

	/**
	 * Convertit l'extension du fichier en type de la réponse.
	 * @param fileExtension L'extension du fichier.
	 * @return Le type de réponse.
	 */
	private String convertExtension(String fileExtension) {
		// Initialisation du type de la réponse.
		String mimetype = "text/plain";

		// Si le nom du fichier contient une extension.
		if (fileExtension != null) {
			// Récupération du type de réponse correspondant à l'extension.
			String temp = mimetypes.get(fileExtension);

			// Si le type de réponse existe.
			if (mimetype != null) {
				// Définition du type de réponse.
				mimetype = temp;
			}
		}

		// On retourne le type de la réponse.
		return mimetype;
	}

	/**
	 * Recherche et mets à jour le statut de la ressource.
	 */
	public void findRessource() throws IOException {
		// Requête POST.
		if (this.requestMethod == 1) {
			displayPost();
			return;
		}

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
		String filePath = getFilePath(this.ressource);

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

			// Message d'erreur.
			this.responseData = "La ressource '" + this.ressource + "' est introuvable.";
			return;
		}
	}

	/**
	 * Affiche la liste des paramètres reçus par la méthode POST.
	 */
	private void displayPost() {
		// Ressource trouvée.
		this.ressourceStatus = 0;

		// Définition du type de la réponse.
		this.responseType = "text/html";

		// Définition du corps de la réponse.
		this.responseData = "<!DOCTYPE html>\n"
			+ "<html>\n"
			+ "\t<head>\n"
			+ "\t\t<meta charset=\"utf-8\">\n"
			+ "\t\t<title>Méthode POST</title>\n"
			+ "\t</head>\n"
			+ "\t<body>\n"
			+ "\t\t<h1>Variables et valeurs reçues par la méthode POST</h1>\n"
			+ "\t\t<pre>\n";

		// Récupération des paramètres du corps de la requête.
		HashMap<String, String> params = decodeRequestContent();

		// Pour chaque paramètre du corps de la requête.
		for (Map.Entry<String, String> param : params.entrySet()) {
			// Si l'indice n'est pas vide.
			if (!param.getKey().isEmpty()) {
				// Si la valeur n'est pas nulle.
				if (param.getValue() != null) {
					// Ajout du paramètre.
					this.responseData += "* " + param.getKey() + " = " + param.getValue() + "\n";
				} else {
					// Ajout de l'indice du paramètre.
					this.responseData += "* " + param.getKey() + " (Pas de valeur associée)\n";
				}
			} else {
				// Ajout d'un message d'erreur.
				this.responseData += "Pas de variables.\n";
			}

		}

		// Définition du corps de la réponse.
		this.responseData += "\t\t</pre>\n"
			+ "\t</body>\n"
			+ "</html>";
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
	 * Recherche un fichier et mets à jour le statut de la ressource.
	 */
	private void findFile(File file) throws IOException {
		// Ressource fichier trouvée.
		this.ressourceStatus = 2;

		// Initialisation de l'extension du fichier.
		String fileExtension = getFileExtension(file);

		// Définition du type de la réponse à partir de l'extension du fichier.
		this.responseType = convertExtension(fileExtension);
	}

	/**
	 * Recherche un dossier et mets à jour le statut de la ressource.
	 */
	private void findDirectory(File file) throws IOException {
		// Si le chemin ne se termine pas par un slash.
		if (!this.ressource.endsWith("/")) {
			// Ressource déplacée.
			this.ressourceStatus = 1;

			// Mise à jour de l'URI de la ressource.
			this.ressource += "/";
		} else {
			// Recherche d'un fichier index.
			File indexFile = findIndex();

			// Si le fichier index existe.
			if (indexFile != null) {
				// Affichage du fichier index.
				displayIndex(indexFile);
			} else {
				// Affichage d'une liste des sous-éléments.
				displayList();
			}
		}
	}

	/**
	 * Recherche un fichier index dans le dossier actuel.
	 * @return Le fichier index s'il existe.
	 */
	private File findIndex() throws IOException {
		// Initialisation du fichier index.
		File indexFile = null;

		// Définition des extensions des fichiers index.
		String[] extensions = {"html", "pdf", "txt"};

		// Pour chaque extension des fichiers index.
		for (String extension : extensions) {
			// Récupération du chemin d'accès au fichier index.
			String tempPath = getFilePath(this.ressource);

			// Récupération du fichier index.
			File tempFile = new File(tempPath + "index." + extension);

			// Si le fichier existe.
			if (tempFile.exists()) {
				// Récupération du fichier index.
				indexFile = tempFile;
				break;
			}
		}

		// On retourne le fichier index.
		return indexFile;
	}

	/**
	 * Affiche le fichier index.
	 * @param indexFile Le fichier index à afficher.
	 */
	private void displayIndex(File indexFile) {
		// Ressource fichier trouvée.
		this.ressourceStatus = 2;

		// Définition du fichier de la réponse.
		this.file = indexFile;

		// Récupération de l'extension du fichier index.
		String fileExtension = getFileExtension(indexFile);

		// Définition du type de la réponse à partir de l'extension du fichier.
		this.responseType = convertExtension(fileExtension);
	}

	/**
	 * Affiche une liste des éléments du dossier.
	 */
	private void displayList() {
		// Ressource trouvée.
		this.ressourceStatus = 0;

		// Définition du type de la réponse.
		this.responseType = "text/html";

		// Définition du corps de la réponse.
		this.responseData = "<!DOCTYPE html>\n"
			+ "<html>\n"
			+ "\t<head>\n"
			+ "\t\t<meta charset=\"utf-8\">\n"
			+ "\t\t<title>Index of " + this.ressource + "</title>\n"
			+ "\t</head>\n"
			+ "\t<body>\n"
			+ "\t\t<h1>Index of " + this.ressource + "</h1>\n"
			+ "\t\t<pre>\n";

		// Récupération du chemin d'accès au dossier.
		String folderPath = getFilePath(this.ressource);

		// Récupération du dossier.
		File folder = new File(folderPath);

		// Si le dossier courant n'est pas la racine du serveur.
		if (!this.ressource.equals("/")) {
			// Récupération du chemin absolu du dossier parent.
			String parentPath = folder.getParentFile().getPath().substring(1);

			// Ajout du dossier parent.
			this.responseData += "* [DIRECTORY] <a href=\"" + parentPath + "/\">..</a>\n";
		}

		// Pour chaque sous-élément du dossier.
		for (File fileEntry : folder.listFiles()) {
			// S'il s'agit d'un dossier.
			if (fileEntry.isDirectory()) {
				// Ajout du dossier.
				this.responseData += "* [DIRECTORY] <a href=\"" + fileEntry.getName() + "/\">" + fileEntry.getName() + "/</a>\n";
			}

			//  S'il s'agit d'un fichier.
			else {
				// Ajout du fichier.
				this.responseData += "* [FILE] <a href=\"" + fileEntry.getName() + "\">" + fileEntry.getName() + "</a>\n";
			}
		}

		// Définition du corps de la réponse.
		this.responseData += "\t\t</pre>\n"
			+ "\t</body>\n"
			+ "</html>";
	}

	/**
	 * Récupère le code de la réponse.
	 * @return Le code de la réponse.
	 */
	public String getResponseCode() {
		// Initialisation du code de la réponse.
		String responseCode;

		// Ressource trouvée.
		if (this.ressourceStatus == 0 || this.ressourceStatus == 2) {
			responseCode = "200 OK";
		}

		// Ressource déplacée.
		else if (this.ressourceStatus == 1) {
			responseCode = "301 Moved Permanently";
		}

		// Ressource non trouvée.
		else {
			responseCode = "404 Not Found";
		}

		// On retourne le code de la réponse.
		return responseCode;
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
		// Ressource fichier trouvée.
		if (this.ressourceStatus == 2) {
			// On retourne la taille du fichier.
			return (int) this.file.length();
		}

		// On retourne la taille du corps de la réponse.
		return this.responseData.length();
	}
}
