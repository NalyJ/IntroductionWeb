import java.io.*;

/**
 * Serveur HTTP.
 * @author Joao Brilhante.
 * @version 1.0
 */
public class HTTPServer extends Server {
	/**
	 * Méthode principale.
	 * @param args Les paramètres d'exécution.
	 */
	public static void main(String[] args) {
		// Initialisation du serveur.
		HTTPServer server = null;

		try {
			// Serveur HTTP en verbose sur le port 1234.
			server = new HTTPServer(1234, true);
		} catch (IOException e) {
			// Erreur de création du serveur.
			System.out.println("Error while creating the server !");

			// Code d'erreur.
			System.exit(-1);
		}

		try {
			// Boucle du serveur.
			while (true) {
				// Communication du serveur.
				server.communicate();
			}
		} catch (IOException e) {
			// Erreur de communication.
			System.out.println("Error while communicating with the client !");
		} finally {
			// Erreur du serveur.
			System.out.println("Stopping the server !");

			// Fermeture du serveur.
			server.close();
		}
	}

	/**
	 * Constructeur d'un serveur HTTP.
	 * @param port Le port du serveur.
	 * @param verbose Le serveur affiche-t-il les messages ?
	 */
	public HTTPServer(int port, boolean verbose) throws IOException {
		super(port, verbose);
	}

	/**
	 * Constructeur d'un serveur HTTP.
	 * @param port Le port du serveur.
	 */
	public HTTPServer(int port) throws IOException {
		super(port);
	}

	/**
	 * Constructeur d'un serveur HTTP.
	 * @param port Le port du serveur.
	 */
	public HTTPServer() throws IOException {
		super();
	}

	/**
	 * Communication du serveur.
	 */
	public void communicate() throws IOException {
		// Autorisation de la connexion.
		acceptConnection();

		// Récupération de la requête.
		Request request = getRequest();

		// Si la requête n'est pas nulle.
		if (request != null) {
			// Réponse à la requête.
			replyRequest(request);
		}

		// Fermeture de la connexion.
		closeConnection();
	}

	/**
	 * Récupère la requête du client.
	 * @return La requête du client.
	 */
	private Request getRequest() throws IOException {
		// Initialisation de la requête.
		Request request = null;

		// Récupération de l'en-tête.
		String line = readLine();

		// Si l'en-tête n'est pas nulle.
		if (line != null) {
			// Analyse de la requête.
			request = analyseRequest(line);

			// Si la requête n'est pas nulle.
			if (request != null) {
				// Initialisation de la lecture.
				boolean read = true;

				// Tant que la requête n'est pas terminée.
				while (read) {
					// Ligne de requête.
					line = readLine();

					// Ligne nulle.
					if (line == null) {
						return null;
					}

					// Ligne vide.
					else if (line.isEmpty()) {
						// Requête POST.
						if (request.isPostRequest()) {
							// Récupération de la taille de la requête.
							int requestLength = request.getResponseLength();

							// Récupération du corps de la requête.
							line = readChars(requestLength);
						}

						// Requête terminée.
						read = false;
					}

					// Ligne classique.
					else {
						// Récupération des paramètres de la ligne.
						String[] content = line.split(" ");

						// Nom de domaine du serveur.
						if (content[0].startsWith("Host")) {
							// Définition du nom de domaine du serveur.
							request.setHost("http://" + content[1]);
						}

						// Taille du corps de la requête.
						else if (content[0].startsWith("Content-Length")) {
							// Conversion du paramètre en entier.
							int requestLength = Integer.parseInt(content[1]);
							
							// Définition de la taille du crops de la requête.
							request.setRequestLength(requestLength);
						}
					}
				}

				// Recherche de la ressource.
				request.findRessource();
			}
		}

		// On retourne la requête.
		return request;
	}

	/**
	 * Analyse la première ligne de la requête.
	 */
	public Request analyseRequest(String line) {
		// Initialisation de la requête.
		Request request = null;

		// Récupération des paramètres de l'en-tête.
		String[] header = line.split(" ");

		// Si l'en-tête de la requête admet trois paramètres.
		if (header.length == 3) {
			// Requête GET.
			if (header[0].equals("GET")) {
				// Initialisation de la requête.
				request = new Request(0, header[1]);
			}

			// Requête POST.
			else if (header[0].equals("POST")) {
				// Initialisation de la requête.
				request = new Request(1, header[1]);
			}
		}

		// On retourne la requête.
		return request;
	}

	/**
	 * Envoie au client l'en-tête de la réponse.
	 * @param request La requête du client.
	 */
	private void replyHeader(Request request) {
		// Récupération du code de la réponse.
		String responseCode = request.getResponseCode();

		// En-tête de la réponse.
		writeLine("HTTP/1.1 " + responseCode);

		// Ressource déplacée.
		if (request.getStatus() == 1) {
			// Récupération du nom de domaine du serveur.
			String host = request.getHost();

			// Récupération de l'URI de la ressource.
			String ressource = request.getRessource();

			// Chemin d'accès à la ressource.
			writeLine("Location: " + host + ressource);
		}

		// Ressource non déplacée.
		else {
			// Récupération du type de la réponse.
			String responseType = request.getResponseType();

			// Type de contenu.
			writeLine("Content-Type: " + responseType);

			// Récupération de la taille du corps de la réponse.
			int responseLength = request.getResponseLength();

			// Taille du corps de la réponse.
			writeLine("Content-Length: " + responseLength);
		}

		// Type de connexion.
		writeLine("Connection: close\n");
	}

	/**
	 * Envoie le corps de la réponse au client.
	 * @param request La requête du client.
	 */
	private void replyData(Request request) {
		// Ressource non déplacée.
		if (request.getStatus() != 1) {
			// Récupération du corps de la réponse.
			String responseData = request.getResponseData();

			// Corps de la réponse.
			writeLine(responseData);
		}
	}

	/**
	 * Répond à la requête du client.
	 * @param request La requête du client.
	 */
	private void replyRequest(Request request) {
		// En-tête de la réponse.
		replyHeader(request);

		// Corps de la réponse.
		replyData(request);
	}
}
