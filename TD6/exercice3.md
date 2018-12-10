## Exercice 3

Modifiez votre serveur pour qu'il lise plusieurs lignes. Validez votre programme
avec la commande `netcat` en tant que client. Donnez les commandes et textes de
test que vous envoyez depuis `netcat` et les résultats obtenus. Argumentez ensuite
pourquoi les résultats obtenus vous donnent satisfaction. Expliquez pourquoi un
vrai navigateur n’est pas souhaitable pour valider votre programme.

Sur un terminal, nous ouvrons notre serveur `HTTP` sur le port `1234` :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234

Sur un autre terminal, nous pouvons tester les différentes requêtes des clients
afin de valider notre programme.

Nous pouvons essayer d'envoyer une requête contenant uniquement une **ligne nulle**.
Nous constatons que le serveur ne répond pas à notre requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	^C

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46048
	Connection with /127.0.0.1:46048 successfully closed

Nous pouvons essayer d'envoyer une requête contenant uniquement une **ligne**. Nous
constatons que le serveur répond alors à notre requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	Hello, world !

	HTTP/1.1 200 OK
	Content-Type: text/plain; charset=utf-8

	Bien reçu. Au revoir.

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46050
	Connection with /127.0.0.1:46050 successfully closed

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` sans les
trois paramètres requis**. Nous constatons que le serveur répond alors à notre
requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET aaa

	HTTP/1.1 200 OK
	Content-Type: text/plain; charset=utf-8

	Bien reçu. Au revoir.

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46052
	Connection with /127.0.0.1:46052 successfully closed

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` avec les
trois paramètres requis**. Nous constatons que le serveur répond alors à notre
requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET aaa bbb
	ccccccccccc

	HTTP/1.1 200 OK
	Content-Type: text/plain; charset=utf-8

	Bien reçu. Au revoir.

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46054
	Connection with /127.0.0.1:46054 successfully closed

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis**. Nous constatons que le serveur répond alors à notre
requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST aaa bbb
	ccccccccccc

	ddddddddddddddddd

	HTTP/1.1 200 OK
	Content-Type: text/plain; charset=utf-8

	Bien reçu. Au revoir.

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46056
	Connection with /127.0.0.1:46056 successfully closed

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis** et envoyer une **ligne nulle** en cours de requête. Nous
constatons que le serveur ne répond pas à notre requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST aaa bbb
	ccccccccccc
	^C

Nous remarquons les messages de connexion et de fermeture de la connexion du
client :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46058
	Connection with /127.0.0.1:46058 successfully closed

Finalement, nous constatons que le serveur ouvre et ferme correctement les
connexions avec les clients. De plus, le serveur ne répond qu'aux requêtes non
nulles.

Un navigateur n'est pas souhaitable pour tester notre serveur. En effet, un
navigateur enverra toujours des requêtes bien construites et le plus souvent
sans erreurs. De ce fait, avec un navigateur il n'est pas possible de vérifier
le comportement du serveur face à toutes sortes de requêtes.
