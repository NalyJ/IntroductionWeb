## Exercice 9

Exécutez 2 fois la commande `netcat` en mode serveur : l'un pour écouter le port
`1234` et l'autre, le port `1235`. Depuis votre navigateur firefox, exécutez une
requête vers `http://localhost:1234/`. Logiquement, vous devriez avoir une requête
sur le premier serveur netcat. Répondez à la requête et vérifiez maintenant ce
qui s’affiche dans le 2ème serveur netcat et expliquez ce comportement.

Sur un terminal, nous ouvrons un serveur local sur le port `1234`, nous recevons
une requête `POST` depuis notre navigateur firefox et nous y répondons par une
page web contenant une image se trouvant sur un serveur local sur le port `1235` :

	joaobrilhante@GlaDOS:~$ netcat -l 1234
	POST / HTTP/1.1
	Host: localhost:1234
	Connection: keep-alive
	Content-Length: 22
	Cache-Control: max-age=0
	Upgrade-Insecure-Requests: 1
	Origin: null
	Content-Type: application/x-www-form-urlencoded
	User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
	Accept-Encoding: gzip, deflate, br
	Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7

	var=Hello%2C+world+%21

	HTTP/1.1 200 OK

	<html>
	<head></head>
	<body><img src="http://localhost:1235/test.jpg"/></body>
	</html>

Sur un autre terminal, nous ouvrons un serveur local sur le port `1235` et nous
recevons une requête `GET` depuis notre navigateur firefox pour l'image contenu
dans la page web de la réponse envoyée par serveur local sur le port `1234` au
navigateur :

	joaobrilhante@GlaDOS:~$ netcat -l 1235
	GET /test.jpg HTTP/1.1
	Host: localhost:1235
	Connection: keep-alive
	User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36
	Accept: image/webp,image/apng,image/*,*/*;q=0.8
	Referer: http://localhost:1234/
	Accept-Encoding: gzip, deflate, br
	Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7

En effet, lorsque notre navigateur firefox reçoit une page web en réponse à une
requête au premier serveur local, il envoie toutes les requêtes nécessaires à
l'affichage de cette page web. Notamment, il envoie une requête afin d'obtenir
l'image se trouvant sur le deuxième serveur local.
