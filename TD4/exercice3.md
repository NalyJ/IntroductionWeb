## Exercice 3
Indiquez et comprenez chaque élément que vous identifiez dans l'URL suivante :

	http://Jojo:lApIn@www.example.com:8888/chemin/d/acc%C3%A8s.php?q=req&q2=req2#signet

- `http` = Protocole de communication HTTP pour communiquer au serveur web.
- `:` = Séparateur obligatoire.
- `//` = Chaîne obligatoire pour les protocoles dont la requête comprend un chemin d'accès.
- `Jojo` = Nom d'utilisateur.
- `:` = Séparateur pour indiquer un mot de passe.
- `lApIn` = Mot de passe.
- `@` = Séparateur terminant les données d'identification.
- `www` = Sous domaine.
- `example` = Nom de domaine de deuxième niveau.
- `com` = Nom de domaine de premier niveau.
- `:` = Séparateur permettant d'indiquer un port.
- `8888` = Numéro de port TCP/IP du serveur HTTP.
- `/chemin/d` = Chemin absolu vers la ressource.
- `acc%C3%A8s.php` = Nom de la page web avec un codage Unicode.
- `?` = Séparateur pour indiquer des données complémentaires.
- `q=req&q2=req2` = Chaine de requête à deux paramètres GET.
- `#` = Séparateur pour indiquer un signet.
- `signet` = Identificateur du signet désignant un emplacement dans la page web.

Décomposez cette URL :
	
	ftp://myname@host.dom/%2Fetc/motd
	
- `ftp` = Protocole de communication FTP destiné au partage de fichiers sur un réseau TCP/IP.
- `:` = Séparateur obligatoire.
- `//` = Chaîne obligatoire pour les protocoles dont la requête comprend un chemin d'accès.
- `myname` = Nom d'utilisateur.
- `@` = Séparateur terminant les données d'identification.
- `host` = Nom de domaine de deuxième niveau.
- `com` = Nom de domaine de premier niveau.
- `%2Fetc/motd` = Chemin absolu vers la ressource.
- `%2F` = Caractère `/` encodé en pourcent.
