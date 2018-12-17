import java.util.Date;
import java.text.SimpleDateFormat;

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
	 * L'URI de la ressource.
	 */
	private String ressource;

	/**
	 * Le statut de la ressource.
	 * -1 = Ressource non trouvée.
	 * 0 = Ressource trouvée.
	 * 1 = Ressource déplacée.
	 */
	private int ressourceStatus;

	/**
	 * Le corps de la réponse.
	 */
	private String responseData;

	/**
	 * Constructeur d'une requête.
	 * @param requestMethod La méthode de la requête.
	 * @param ressource L'URI de la ressource.
	 */
	public Request(int requestMethod, String ressource) {
		// Initialisation du type de la requête.
		this.requestMethod = requestMethod;

		// Initialisation de la taille de la requête.
		this.requestLength = 0;

		// Initialisation du nom de domaine du serveur.
		this.host = "";

		// Initialisation de l'URI de la ressource.
		this.ressource = ressource;

		// Initialisation du statut de la ressource.
		this.ressourceStatus = -1;

		// Initialisation du corps de la réponse.
		this.responseData = "";
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
	 * Récupère le corps de la réponse.
	 */
	public String getResponseData() {
		return this.responseData;
	}

	/**
	 * Recherche et mets à jour le statut de la ressource.
	 */
	public void findRessource() {
		// Ressource trouvée.
		if (this.ressource.equals("/date/")) {
			this.ressourceStatus = 0;
		}

		// Ressource déplacée.
		else if (this.ressource.equals("/date")) {
			// Mise à jour du statut de la ressource.
			this.ressourceStatus = 1;

			// Mise à jour de l'URI de la ressource.
			this.ressource = "/date/";
		}

		// Ressource non trouvée.
		else {
			this.ressourceStatus = -1;
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
		if (this.ressourceStatus == 0) {
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
		return "text/plain";
	}

	/**
	 * Récupère la taille du corps de la réponse.
	 * @return La taille du corps de la réponse.
	 */
	public int getResponseLength() {
		// Ressource trouvée.
		if (this.ressourceStatus == 0) {
			// Récupération de la date.
			this.responseData = getDate();
		}

		// Ressource non trouvée.
		else if (this.ressourceStatus == -1) {
			// Message d'erreur.
			this.responseData = "La ressource '" + ressource + "' est introuvable.";
		}

		// On renvoit la taille du corps de la réponse.
		return this.responseData.length();
	}
}
