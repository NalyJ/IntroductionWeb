## Exercice 2

On décide d’être plus restrictif et de gérer la demande des ressources
non-disponibles. Votre serveur affichera donc la date que si l’URI est « /date/ »
Dans tous les autres cas, vous renverrez une erreur (code de retour « 404 Not Found »).
En plus du code d'erreur renvoyé au navigateur, prévoyez d'afficher un message texte « La ressource "uri" est introuvable » (uri = ressource demandée), sans oublier évidemment le paramètre « Content-Length » proprement annoncé.

Comment tester avec `netcat` et votre navigateur que votre serveur réponds
correctement à la requête ? Donnez les captures d’écran prouvant vos tests.

Sur un terminal, nous ouvrons notre serveur `HTTP` sur le port `1234` :

	joaobrilhante@GlaDOS:~/IAW/TD7$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234

Sur un autre terminal, nous pouvons tester les différentes requêtes des clients
afin de valider notre programme.

Nous pouvons essayer d'envoyer une requête contenant uniquement une **ligne nulle**.
Nous constatons que le serveur ne répond pas à notre requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	^C

Nous pouvons essayer d'envoyer une requête contenant uniquement une **ligne**.
Nous constatons que le serveur ne répond pas à notre requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	Lorem ipsum

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` sans les
trois paramètres requis**. Nous constatons que le serveur ne répond pas à notre
requête :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET aaa

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` avec les
trois paramètres requis** pour une ressource quelconque. Nous constatons que le
serveur répond alors à notre requête avec une erreur `404 Not Found` et un
message d'erreur :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET /test bbb
	ccc

	HTTP/1.1 404 Not Found
	Content-Type: text/plain
	Content-Length: 37
	Connection: close

	La ressource '/test' est introuvable.

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` avec les
trois paramètres requis** pour la ressource `/date/`. Nous constatons que le
serveur répond alors à notre requête avec la date actuelle :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET /date/ bbb
	ccccc

	HTTP/1.1 200 OK
	Content-Type: text/plain
	Content-Length: 24
	Connection: close

	lun. 17 12 2018 15:03:30

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis** pour une ressource quelconque. Nous constatons que le
serveur répond alors à notre requête avec une erreur `404 Not Found` et un
message d'erreur :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST /lorem/ bbb
	Content-Length: 5

	ccccc
	HTTP/1.1 404 Not Found
	Content-Type: text/plain
	Content-Length: 39
	Connection: close

	La ressource '/lorem/' est introuvable.

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis** pour la ressource `/date/`. Nous constatons que le
serveur répond alors à notre requête avec la date actuelle :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST /date/ bbb
	Content-Length: 5

	ccccc
	HTTP/1.1 200 OK
	Content-Type: text/plain
	Content-Length: 24
	Connection: close

	lun. 17 12 2018 15:08:03
