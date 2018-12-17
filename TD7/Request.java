import java.util.Date;
import java.text.SimpleDateFormat;

public class Request {
	private int requestType;
	private String ressource;
	private int ressourceType;
	private String data;

	public Request(int type, String ressource) {
		this.requestType = type;
		this.ressource = ressource;
		this.ressourceType = 0;
		this.data = "";
	}

	public Request(int type) {
		this(type, "/");
	}

	public Request() {
		this(1, "/");
	}

	public void findRessourceType() {
		this.ressourceType = 0;
	}

	public int getRequestType() {
		return this.requestType;
	}

	public int getRessourceType() {
		return this.ressourceType;
	}

	public String getReplyCode() {
		// Initialisation du code de la réponse.
		String replyCode;

		// Si le type de la ressource est défini.
		if (ressourceType == 0) {
			// Requête valide.
			replyCode = "200 OK";
		} else {
			// Requête invalide.
			replyCode = "404 Not Found";
		}

		// On retourne le code de la réponse.
		return replyCode;
	}

	/**
	 * Récupère la date actuelle.
	 */
	public String getDate() {
		// Initialisation d'un formatteur.
		SimpleDateFormat formatter = new SimpleDateFormat("EE dd MM yyyy HH:mm:ss");

		// Initialisation de la date.
		Date date = new Date();

		// On retourne la date.
		return formatter.format(date);
	}

	/**
	 * Récupère le corps de la réponse.
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Récupère le type du corps de la réponse.
	 */
	public String getMimeType() {
		return "text/plain";
	}

	/**
	 * Récupère la taille du corps de la réponse.
	 */
	public int getContentLength() {
		// Initialisation de la longueur.
		int length = 0;

		// Si le type de la ressource est défini.
		if (ressourceType == 0) {
			// Récupération de la date.
			this.data = getDate();

			// Récupération de la longueur.
			length = this.data.length();
		}

		// On renvoit la longueur.
		return length;
	}
}
