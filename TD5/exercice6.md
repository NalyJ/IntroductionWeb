## Exercice 6

Démarrez un serveur avec la commande netcat sur le port 1234. Ensuite, téléchargez
et éditez le fichier  `form.html` (disponible avec les ressources fournies pour ce TP).
Indiquez la méthode `GET` pour l'envoi de données (où sont envoyées les données
d'ailleurs?) et testez la page avec votre navigateur en y chargeant le fichier
html édité.

	joaobrilhante@GlaDOS:~$ netcat -l 1234

	GET /?var=Hello%2C+world+%21 HTTP/1.1
	Host: localhost:1234
	Connection: keep-alive
	Upgrade-Insecure-Requests: 1
	User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
	Accept-Encoding: gzip, deflate, br
	Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7

Nous constatons que nous recevons une requête `GET` contenant un paramètre `var`
de valeur celle inscrite dans le formulaire **dans l'URI demandé**.
