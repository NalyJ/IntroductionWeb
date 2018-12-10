## Exercice 2

En vous inspirant du listing 1, programmez la classe `HTTPServer` qui étend la
classe `Server`, où on aura une méthode `main()` qui ouvre un serveur écoutant
sur le port numéro `1234`, avec une boucle infinie autour de une méthode `dialogue()`.
Votre code reprendra le cœur de la méthode `dialogue()` donnée dans ce document,
mais votre méthode `dialogue()` sera cette fois-ci une méthode non static de la
classe `HTTPServer`. Testez votre programme Java avec la commande `netcat`.
Vérifiez bien que vos clients successifs peuvent se connecter au serveur sans
avoir à le relancer à chaque fois.

Sur un terminal, nous ouvrons notre serveur `HTTP` sur le port `1234` :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234

Sur un autre terminal, nous envoyons des requêtes au serveur `HTTP` local :

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	Hello, world !
	Hello, world !

	joaobrilhante@GlaDOS:~$ netcat localhost 1234
	Mexicanos, al grito de guerra  
	Mexicanos, al grito de guerra

Nous constatons que le serveur répond correctement aux requêtes des clients
successifs sans avoir à le relancer. De plus, nous remarquons les messages
de connexion et de fermeture de connexion avec les clients sur le premier terminal :

	joaobrilhante@GlaDOS:~/IAW/TD6$ javac HTTPServer.java && java HTTPServer
	Server created on port 1234
	Connected with peer /127.0.0.1:46010
	Connection with /127.0.0.1:46010 successfully closed
	Connected with peer /127.0.0.1:46012
	Connection with /127.0.0.1:46012 successfully closed
