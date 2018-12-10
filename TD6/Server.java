import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

  private int port = 1234;
  private boolean verbose = false;
  private boolean connected = false;
  private ServerSocket server = null;
  private BufferedReader is = null;
  private PrintWriter os = null;
  private String rep = null;

  Socket client = null;

  public Server (int port, boolean verbose) throws IOException {
    this.port = port;
    this.verbose = verbose;
    this.server = new ServerSocket(port);
    debug("Server created on port " + Integer.toString(port));
  }

  protected void debug(String messg) {
    if (verbose) {
      System.out.println(messg);
    }
  }

  public void acceptConn() throws IOException {
      client = server.accept();
      is = new BufferedReader(new InputStreamReader(client.getInputStream()));
      os = new PrintWriter(client.getOutputStream(), true);
      connected = true;
      debug("Connected with peer " + client.getRemoteSocketAddress());
  }

  public String readline() throws IOException {
    return is.readLine();
  }

  public String readchars(int nomChars) throws IOException{
    char[] line_aux;
    String line;
    line_aux = new char[nomChars];
    int ccars = is.read(line_aux, 0, nomChars);
    if (ccars != nomChars)
      debug("Asked " + nomChars + "characters, but read only " + ccars);
    if (ccars == -1)
      debug("Read 0 characters!!");
    line = String.valueOf(line_aux);
    return line;
  }

  public void writeline(String buff){
      os.println(buff);
  }

  // Cette methode est a utiliser quand on veut transferer un ensemble de bytes, qui ne sont pas printables.
  public void write(byte[] buff) throws IOException{
	  new DataOutputStream(client.getOutputStream()).write(buff);
	}

  public void closeConn(){
    try{
      if (connected) {
	os.close();
	is.close();
	SocketAddress c = client.getRemoteSocketAddress();
	client.close();
	connected = false;
	debug("Connection with " + c + " successfully closed");
      }
    } catch (IOException e) {
      System.out.println("Closing Connection failed!");
    }
  }

  public void close(){
    try {
      server.close();
      debug("Your server is now closed");
    } catch (IOException e) {
      System.out.println("Closing sockets failed!");
    }
  }
}
