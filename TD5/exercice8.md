## Exercice 8

Pour aller plus loin dans cet exercice expérimental, essayez de faire en sorte
que le serveur réponde quelque chose au navigateur dans les 2 cas expérimentés
(pensez à envoyer un Ctrl-C ou un Ctrl-D depuis `netcat` pour "pousser" la réponse
sur le réseau, mais, en même temps, regardez bien votre navigateur avant que
l'onglet où était visualisé le formulaire ne se ferme).

	joaobrilhante@GlaDOS:~$ netcat -l 1234

	GET /?var=Hello%2C+world+%21 HTTP/1.1
	Host: localhost:1234
	Connection: keep-alive
	Upgrade-Insecure-Requests: 1
	User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
	Accept-Encoding: gzip, deflate, br
	Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7

	HTTP/1.1 200 OK
	Content-Type: text/plain; charset=utf-8

	Hello, world !
	Lorem ipsum dolor sit amet

Nous constatons que nous recevons une requête `GET` contenant un paramètre `var`
de valeur celle inscrite dans le formulaire **dans l'URI demandé**.

Nous répondons alors à cette requête par un texte en clair. Nous constatons qu'une
fois que la réponse a été envoyée, nous sommes redirigés depuis la page `form.html`
vers `localhost:1234` contenant le texte clair envoyé dans le contenu de la requête.
