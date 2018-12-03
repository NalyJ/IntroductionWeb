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
de valeur celle inscrite dans le formulaire **dans le corps de la requête**.

A la différence d'une requête `GET`, les données sont envoyées dans le corps
de la requête et non plus dans l'URI de la ressource demandée, ce qui permet une
meilleure protection des données qui n'apparaissent alors pas en clair.

Nous analysons la requête :
- `POST / HTTP/1.1` : Ligne de commande d'une requête POST pour la ressource à
la racine du serveur en utisant le protocole HTTP 1.1.
- `Host: localhost:1234` : En-tête permettant de préciser le site web concerné
par la requête, ce qui est nécessaire pour un serveur hébergeant plusieurs sites
à la même adresse IP.
- `Connection: keep-alive` : En-tête permettant d'établir une connexion persistante.
C'est-à-dire que la connexion entre le serveur et le client reste ouverte même
après la requête permettant d'accélérer le chargement de pages contenant plusieurs
ressources.
- `Content-Length: 22` : En-tête permettant de préciser la taille en octets du
corps de la requête.
- `Cache-Control: max-age=0` : En-tête permettant de fixer durée maximale en secondes
du cache. Une valeur définie à zéro indique que la cible **devrait** être rechargée
à chaque accès.
- `Upgrade-Insecure-Requests: 1` : En-tête permettant d'indiquer que des connexions
sécurisées via HTTPS seront toujours préférées.
- `Origin: null` : En-tête permettant d'indiquer la provenance de la requête.
- `Content-Type: application/x-www-form-urlencoded` : En-tête permettant d'indiquer
le type du corps de la requête. Nous utilisons ici l'encodage par défaut de la
méthode POST : `form-urlencoded`. Les valeurs sont encodées sous forme de couples
clé-valeur séparés par '&', avec un '=' entre la clé et la valeur et les caractères
non alphanumériques sont encodés en pourcent.
- `User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36` : En-tête permettant d'indiquer des caractéristiques sur le
client, le type d'application, le système d'exploitation, le moteur de rendu,
l'éditeur du logiciel ou bien la version du logiciel utilisé.
- `Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8` :
En-tête permettant d'indiquer les types de contenu, exprimés sous la forme de
types MIME, que le client sera capable d'interpréter avec poids.
- `Accept-Encoding: gzip, deflate, br` : En-tête permettant d'indiquer les formats
d'encodage du contenu que le client supporte. C'est le serveur qui choisi parmi
les formats d'encodages proposés.
- `Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7` : En-tête permettant
d'indiquer les langues le client est capable de comprendre, et quelle variante
locale est préférée avec un poids.
- `\n` : Ligne vide pour séparer l'en-tête et le corps de la requête.
- `var=Hello%2C+world+%21` : Corps de la requête POST contenant une variable
`var` de valeur `Hello, world !` en encodage `form-urlencoded`.
