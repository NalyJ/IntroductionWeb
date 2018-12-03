## Exercice 11

Publiez la page web et feuilles de styles de l'exercice 4 du TD3 sur le serveur
`morag.polytech.unice.fr`, qui sera accessible via l’URL
`http://users.polytech.unice.fr/~ab123456/dir1/page1.html`, où `ab123456` correspond
à votre login utilisateur. Vous utiliserez pour cela les commande `ssh` et `scp`.

Nous transférons les fichiers de l'exercice 4 du TD3 via `SSH` sur notre session
sur le serveur `morag.polytech.unice.fr` :

	joaobrilhante@GlaDOS:~$ cd /IAW/TD3/Exercice4/
	joaobrilhante@GlaDOS:~/IAW/TD3/Exercice4$ scp ./* bj701093@morag.polytech.unice.fr:~

	bj701093@morag.polytech.unice.fr's password:
	default.css                                   100% 1017    27.3KB/s   00:00    
	index.html                                    100% 4991   120.8KB/s   00:00    
	smartphone.css                                100%  895    21.8KB/s   00:00

Ensuite, nous nous connectons via `SSH` à notre session sur le serveur
`morag.polytech.unice.fr` et nous déplaçons les fichiers transférés dans notre
dossier du serveur web de polytech :

	joaobrilhante@GlaDOS:~$ ssh bj701093@morag.polytech.unice.fr

	bj701093@morag.polytech.unice.fr's password:
	Last login: Mon Dec  3 11:17:59 2018 from 143.14.136.77.rev.sfr.net

	[bj701093@morag]~% mv *.css *.html /net/web/b/bj701093/public/
	[bj701093@morag]~% exit

Nous constatons alors que notre page de l'exercice 4 du TD3 via l'adresse
`http://users.polytech.unice.fr/~bj701093/`.
