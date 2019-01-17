## Exercice 2
Pour vous connecter à une machine, vous devez connaître au moins son nom canonique qui
permettra à l'application de trouver l'adresse IP de la machine distante et d'initier
une demande de connexion.

a. Utilisez la commande `host` (sous Linux) ou `nslookup` (sous Windows) afin de trouver
l'adresse IP de la machine www.i3s.unice.fr. Ensuite, ouvrez votre navigateur web et
écrivez dans la barre d'adresse l'adresse IP trouvée. Vérifiez que vous téléchargez en
effet la page web du site.

	joaobrilhante@GlaDOS:~$ host www.i3s.unice.fr
	www.i3s.unice.fr is an alias for niouze.i3s.unice.fr.
	niouze.i3s.unice.fr has address 134.59.130.2

b. Ecrire une adresse IP dans la barre d’adresse et réussir à télécharger une page web
normale a donné lieu à une faille de sécurité : on pourrait donner n’importe quelle adresse
(eg. celle d’un serveur malveillant) et montrer par exemple, une page similaire à celle
d’une banque afin de voler les informations des utilisateurs (en faisant croire à l’utilisateur
qu’il s’agit de l’adresse IP légitime du serveur). Donc, la possibilité testée en 2.a est
actuellement souvent désactivée. Explorez avec www.unice.fr et www.mit.edu les 2 réponses 
ouvent données à ce problème de sécurité. Pour cela, commencez par chercher les adresses IP
de ces deux sites web. Puis saisissez-les dans la barre d’adresse du navigateur.
Et commentez quant aux résultats/effets obtenus.

	joaobrilhante@GlaDOS:~$ host www.unice.fr
	www.unice.fr is an alias for sites.unice.fr.
	sites.unice.fr has address 134.59.204.9

	joaobrilhante@GlaDOS:~$ host www.mit.edu
	www.mit.edu is an alias for www.mit.edu.edgekey.net.
	www.mit.edu.edgekey.net is an alias for e9566.dscb.akamaiedge.net.
	e9566.dscb.akamaiedge.net has address 23.217.237.133
	e9566.dscb.akamaiedge.net has IPv6 address 2a02:26f0:82:195::255e
	e9566.dscb.akamaiedge.net has IPv6 address 2a02:26f0:82:18c::255e

Dans le cas de www.unice.fr, lorsque nous tapons l'adresse ip du serveur, nous
sommes redirigés vers le nom de domaine www.unice.fr afin de corriger le problème
de sécurité.

Dans le cas de www.mit.edu, lorsque nous tapons l'adresse ip du serveur, nous
recevons une page d'erreur. En effet, les requêtes utilisant une adresse ip en
clair au lieu du nom de domaine www.mit.edu sont refusées.
