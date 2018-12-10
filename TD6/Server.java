import java.io.*;
import java.net.*;

/**
 * Serveur.
 */
public class Server {
	/**
	 * Le port du serveur.
	 */
	private int port;

	/**
	 * Le serveur affiche-t-il les messages ?
	 */
	private boolean verbose;

	/**
	 * Le serveur est-il connecté à un client ?
	 */
	private boolean connected = false;

	/**
	 * Le socket du serveur.
	 */
	private ServerSocket server = null;

	/**
	 * Le socket du client.
	 */
	private Socket client = null;

	/**
	 * Le flux entrant du serveur.
	 */
	private BufferedReader input = null;

	/**
	 * Le flux sortant du serveur.
	 */
	private PrintWriter output = null;

	/**
	 * Constructeur d'un serveur.
	 * @param port Le port du serveur.
	 * @param verbose Le serveur affiche-t-il les messages ?
	 */
	public Server (int port, boolean verbose) throws IOException {
		// Initialisation du port du serveur.
		this.port = port;

		// Initialisation du debug du serveur.
		this.verbose = verbose;

		// Initialisation du serveur sur le port.
		this.server = new ServerSocket(port);

		// Affichage d'un message de création du serveur.
		debug("Server created on port " + Integer.toString(port));
	}

	/**
	 * Constructeur d'un serveur.
	 * @param port Le port du serveur.
	 */
	public Server (int port) throws IOException {
		this(port, false);
	}

	/**
	 * Constructeur d'un serveur.
	 * @param verbose Le serveur affiche-t-il les messages ?
	 */
	public Server (boolean verbose) throws IOException {
		this(1234, verbose);
	}

	/**
	 * Constructeur d'un serveur.
	 */
	public Server () throws IOException {
		this(1234, false);
	}

	/**
	 * Affiche un message d'information si le serveur affiche les messages.
	 * @param message Le message d'information à afficher.
	 */
	protected void debug(String message) {
		// Si le serveur affiche les messages.
		if (verbose) {
			// Affichage du message.
			System.out.println(message);
		}
	}

	/**
	 * Autorise la connexion d'un client.
	 */
	public void acceptConnection() throws IOException {
		// Autorisation de la connexion d'un client.
		client = server.accept();

		// Initialisation du flux entrant du serveur.
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));

		// Initialisation du flux sortant du serveur.
		output = new PrintWriter(client.getOutputStream(), true);

		// Le serveur est connecté à un client.
		connected = true;

		// Affichage d'un message de connexion avec un client.
		debug("Connected with peer " + client.getRemoteSocketAddress());
	}

	/**
	 * Récupère une ligne depuis le flux entrant du serveur.
	 * @return Une ligne depuis le flux entrant du serveur.
	 */
	public String readLine() throws IOException {
		// On retourne une ligne depuis le flux entrant du serveur.
		return input.readLine();
	}

	/**
	 * Récupère un nombre de caractères depuis le flux entrant du serveur.
	 * @param n Le nombre de caractères à récupérer.
	 * @return Un nombre de caractères depuis le flux entrant du serveur.
	 */
	public String readChars(int n) throws IOException {
		// Initialisation d'un tableau de caractères.
		char[] chars = new char[n];

		// Récupération des caractères et de leur nombre.
		int charsCount = input.read(chars, 0, n);

		// S'il n'a pas été possible de récupérer le nombre de caractères.
		if (charsCount != n) {
			// Affichage d'un message d'erreur.
			debug("Asked " + n + "characters, but read only " + charsCount);
		}

		// S'il n'a pas été possible de récupérer des caractères.
		if (charsCount == -1) {
			// Affichage d'un message d'erreur.
			debug("Read 0 characters!!");
		}

		// On retourne les caractères sous forme d'une chaine de caractères.
		return String.valueOf(chars);
	}

	/**
	 * Envoi une ligne sur le flux sortant du serveur.
	 * @param line La ligne à envoyer sur le flux sortant du serveur.
	 */
	public void writeLine(String line) {
		// Affichage de la ligne sur le flux sortant du serveur.
		output.println(line);
	}

	/**
	 * Envoi un ensemble d'octets sur le flux sortant du serveur.
	 *
	 * Cette methode est à utiliser lorsqu'on souhaite transférer un ensemble
	 * d'octets qui ne sont pas affichables.
	 *
	 * @param bytes L'ensemble d'octets à envoyer sur le flux sortant du serveur.
	 */
	public void write(byte[] bytes) throws IOException {
		// Ecriture de l'ensembles d'octets sur le flux sortant du serveur.
		new DataOutputStream(client.getOutputStream()).write(bytes);
	}

	/**
	 * Ferme la connexion avec un client.
	 */
	public void closeConnection() {
		try {
			// Si le serveur est connecté.
			if (connected) {
				// Fermeture du flux entrant du serveur.
				input.close();

				// Fermeture du flux sortant du serveur.
				output.close();

				// Récupération de l'adresse socket du client.
				SocketAddress c = client.getRemoteSocketAddress();

				// Fermeture de la connexion avec le client.
				client.close();

				// Le serveur n'est plus connecté à un client.
				connected = false;

				// Affichage d'un message de fermeture de connexion.
				debug("Connection with " + c + " successfully closed");
			}
		} catch (IOException e) {
			// Affichage d'un message d'erreur de fermeture de connexion.
			System.out.println("Closing connection failed!");
		}
	}

	/**
	 * Ferme le serveur.
	 */
	public void close(){
		try {
			// Fermeture du serveur.
			server.close();

			// Affichage d'un message de fermeture du serveur.
			debug("Your server is now closed");
		} catch (IOException e) {
			// Affichage d'un message d'erreur de fermeture du serveur.
			System.out.println("Closing sockets failed!");
		}
	}
}
