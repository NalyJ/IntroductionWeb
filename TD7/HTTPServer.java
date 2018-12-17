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
						// Requête terminée.
						read = false;
					}

					// Ligne vide.
					else if (line.isEmpty()) {
						// Ajout d'une ligne vide à la requête.
						//output += "\n";

						// Requête POST.
						if (request.getRequestType() == 2) {
							// Récupération du corps de la requête.
							line = readChars(5);

							// Ajout du corps à la requête.
							//output += line;
						//}

						// Requête terminée.
						read = false;
					}

					// Ligne classique.
					else {
						// Ajout de la ligne à la requête.
						//output += line + "\n";
					}
				}

				// Définition du type de la ressource.
				request.findRessourceType();
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
			// Initialisation du type de la requête.
			int requestType = 0;

			// S'il s'agit d'une requête GET ou POST.
			if (header[0].equals("GET")) {
				// Requête GET.
				requestType = 1;

			} else if (header[0].equals("POST")) {
				// Requête POST.
				requestType = 2;
			}

			// Initialisation de la requête.
			request = new Request(requestType, header[1]);
		}

		// On retourne la requête.
		return request;
	}

	/**
	 * Envoie au client l'en-tête de la réponse.
	 */
	private void replyHeader(Request request) {
		// Récupération du code de la réponse.
		String replyCode = request.getReplyCode();

		// En-tête de la réponse.
		writeLine("HTTP/1.1 " + replyCode);

		// Récupération du type de la réponse.
		String replyType = request.getMimeType();

		// Type de contenu.
		writeLine("Content-Type: " + replyType);

		// Récupération de la longueur du corps de la réponse.
		int replyLength = request.getContentLength();

		// Longueur du corps de la réponse.
		writeLine("Content-Length: " + replyLength);

		// Type de connexion.
		writeLine("Connection: close\n");
	}

	/**
	 * Envoie le corps de la réponse au client.
	 * @param request La requête du client.
	 */
	private void replyData(Request request) {
		// Récupération du corps de la réponse.
		String data = request.getData();

		// Corps de la réponse.
		writeLine(data);
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
