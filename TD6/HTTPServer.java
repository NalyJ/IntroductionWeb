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
		super(port, false);
	}

	/**
	 * Communication du serveur.
	 */
	public void communicate() throws IOException {
		// Autorisation d'une connexion.
		this.acceptConn();

		/*// Récupération de la requête du client.
		String reply = this.readline();

		// Si la réponse n'est pas nulle.
		if (reply != null) {
			// Envoi de la réponse au client.
			this.writeline(reply);

			// Réinitialisation de la réponse.
			reply = null;
		}*/

		// Récupération de la requête.
		String request = getRequest();

		// Affichage de la requête.
		//.out.println(request);

		// Si la requête n'est pas nulle.
		if (request != null) {
			// Réponse à la requête.
			replyRequest(request);
		}

		// Fermeture de la connexion.
		this.closeConn();
	}

	/**
	 * Récupère la requête du client.
	 */
	private String getRequest() throws IOException {
		// Initialisation de la requête.
		String output = null;

		// Récupération de l'en-tête.
		String line = this.readline();

		// Si l'en-tête est nulle.
		if (line == null) {
			// On renvoit la requête.
			return output;
		}

		// Récupération des paramètres de l'en-tête.
		String[] header = line.split(" ");

		// Si l'en-tête de la requête contient trois paramètres.
		if (header.length == 3) {
			// S'il s'agit d'une requête GET ou POST.
			if (header[0].equals("GET") || header[0].equals("POST")) {
				// Ajout de l'en-tête à la requête.
				output = line + "\n";

				// Initialisation de la lecture.
				boolean request = true;

				// Tant que la requête n'est pas terminée.
				while (request) {
					// Ligne de requête.
					line = this.readline();

					// Ligne nulle.
					if (line == null) {
						// Requête terminée.
						request = false;
					}

					// Ligne vide.
					else if (line.isEmpty()) {
						// Ajout d'une ligne vide à la requête.
						output += "\n";

						// Requête POST.
						if (header[0].equals("POST")) {
							// Récupération du corps de la requête.
							line = readchars(30);

							// Ajout du corps à la requête.
							output += line;
						}

						// Requête terminée.
						request = false;
					}

					// Ligne classique.
					else {
						// Ajout de la ligne à la requête.
						output += line + "\n";
					}
				}
			}
		}

		// On retourne la requête.
		return output;
	}

	/**
	 * Répond à la requête du client.
	 * @param request La requête du client.
	 */
	private void replyRequest(String request) {
		// En-tête de la réponse.
		this.writeline("HTTP/1.1 200 OK");

		// Type de contenu.
		this.writeline("Content-Type: text/plain; charset=utf-8\n");

		// Corps de la réponse.
		this.writeline("Bien reçu. Au revoir.");
	}
}
