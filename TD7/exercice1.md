## Exercice 1

Dans cette première version de notre serveur, nous allons nous borner à renvoyer
seulement la date (quelle que soit la requête faite par l’utilisateur).
Ceci est volontaire pour vous éviter de devoir analyser finement la requête
envoyée par le navigateur. Pour ce faire, suite à une requête reçue bien formée
(validé dans le TP précédent), votre serveur renverra la date courante précédée
de l’entête standard `HTTP`.

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
trois paramètres requis**. Nous constatons que le serveur répond alors à notre
requête avec la date actuelle quel que soit la ressource demandée :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	GET aaa bbb
	ccccc

	HTTP/1.1 200 OK
	Content-Type: text/plain
	Content-Length: 24
	Connection: close

	lun. 17 12 2018 14:40:05

Nous pouvons essayer d'envoyer une requête contenant un **en-tête `POST` avec les
trois paramètres requis**. Nous constatons que le serveur lit les cinq caractères
et répond alors à notre requête avec la date actuelle quel que soit la ressource
demandée :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	POST aaa bbb
	Content-Length: 5

	ccccc
	HTTP/1.1 200 OK
	Content-Type: text/plain
	Content-Length: 24
	Connection: close

	lun. 17 12 2018 14:41:50
