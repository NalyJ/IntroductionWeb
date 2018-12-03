## Exercice 10

Testez la présence d'un mécanisme de détection User-agent chez TripAdvisor.


Dans un premier temps, nous récupérons la page `index.html` se trouvant à l'adresse
`www.tripadvisor.com` sans indiquer de `User-Agent` :

	joaobrilhante@GlaDOS:~$ wget www.tripadvisor.com

Dans un deuxème temps, nous récupérons la même page en indiquant un `User-Agent`
d'un appareil mobile sous Android :

	joaobrilhante@GlaDOS:~$ wget www.tripadvisor.com --user-agent="Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppelWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"

En comparant les deux fichiers `HTML`, nous constatons qu'il y a une différence
de style `CSS` afin que l'affichage de la page web s'adapte aux appareils mobiles.

De plus, pour le premier fichier `HTML` puisque nous n'avons indiqué aucun
`User-Agent`, une bannière d'erreur s'affiche nous indiquant que des problèmes
d'affichage peuvent survenir puisque le serveur ne sait pas à quel type d'appareil
il a affaire.
