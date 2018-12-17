## Exercice 3

Si la ressource demandée est `/date` au lieu de `/date/`, vous devez envoyer un
code de réponse `301 Moved Permanently`. Il est obligatoire d’indiquer le paramètre
`Location` avec le bon emplacement de la ressource.

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
	ccccc

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

	lun. 17 12 2018 15:14:35

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `GET` avec les
trois paramètres requis** pour la ressource `/date`. Nous constatons que le
serveur répond alors à notre requête avec une erreur `301 Moved Permanently` et
sans corps de réponse :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET /date bbb
	Host: localhost:1234

	HTTP/1.1 301 Moved Permanently
	Location: http://localhost:1234/date/
	Connection: close

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

	lun. 17 12 2018 15:15:14

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis** pour la ressource `/date`. Nous constatons que le
serveur répond alors à notre requête avec une erreur `301 Moved Permanently` et
sans corps de réponse :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST /date bbb
	Host: localhost:1234
	Content-Length: 5

	ccccc
	HTTP/1.1 301 Moved Permanently
	Location: http://localhost:1234/date/
	Connection: close
