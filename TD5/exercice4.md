## Exercice 4

En utilisant deux commandes netcat dans deux terminaux différents, copiez
un fichier de la  machine cliente sur la machine serveur (en supposant pour
simplifier qu'il s'agit de la même machine). Cet exercice utilise seulement
des redirections pour échanger le contenu du fichier.

Sur un terminal, nous ouvrons un serveur local sur le port `1234` et
nous redirigeons les données reçues vers un fichier `texte2.txt` :

	joaobrilhante@GlaDOS:~$ netcat -l 1234 > texte2.txt

Sur un autre terminal, nous envoyons le contenu d'un fichier `texte.txt`
à travers le réseau local sur le port `1234` :

	joaobrilhante@GlaDOS:~$ cat > texte.txt
	Lorem ipsum dolor sit amet
	To be or not to be that is the question

	joaobrilhante@GlaDOS:~$ netcat localhost 1234 < texte.txt

Ainsi, nous reçevons une copie du fichier `texte.txt` :

	joaobrilhante@GlaDOS:~$ cat texte2.txt
	Lorem ipsum dolor sit amet
	To be or not to be that is the question
