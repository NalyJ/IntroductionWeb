## Exercice 7

Puis, changez la méthode par `POST` dans l’éditeur, enregistrez, rechargez la page
sur votre navigateur pour que la modification effectuée soit disponible, et
testez à nouveau le formulaire de la page. Commentez : quelle est la différence
que vous pouvez observer entre la méthode `GET` et `POST` grâce à ce formulaire.
Profitez-en aussi pour commenter l'ensemble  des lignes que vous avez vu apparaitre
dans `netcat`.

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

Nous constatons que nous recevons une requête `POST` contenant un paramètre `var`
de valeur celle inscrite dans le formulaire **dans le contenu de la requête**.

A la différence d'une requête `GET`, les données sont envoyées dans le contenu
de la requête et pas dans l'URI demandé, ce qui permet une meilleure protection
des données qui n'apparaissent alors pas en clair.
