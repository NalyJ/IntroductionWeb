## Exercice 2

`joaobrilhante@GlaDOS:~$ host www.i3s.unice.fr

www.i3s.unice.fr is an alias for niouze.i3s.unice.fr.
niouze.i3s.unice.fr has address 134.59.130.2`

`joaobrilhante@GlaDOS:~$ host www.unice.fr

www.unice.fr is an alias for sites.unice.fr.
sites.unice.fr has address 134.59.204.9`

`joaobrilhante@GlaDOS:~$ host www.mit.edu

www.mit.edu is an alias for www.mit.edu.edgekey.net.
www.mit.edu.edgekey.net is an alias for e9566.dscb.akamaiedge.net.
e9566.dscb.akamaiedge.net has address 23.217.237.133
e9566.dscb.akamaiedge.net has IPv6 address 2a02:26f0:82:195::255e
e9566.dscb.akamaiedge.net has IPv6 address 2a02:26f0:82:18c::255e`

Dans le cas de www.unice.fr, lorsque nous tapons l'adresse ip du serveur, nous
sommes redirigés vers le nom de domaine www.unice.fr afin de corriger le problème
de sécurité.

Dans le cas de www.mit.edu, lorsque nous tapons l'adresse ip du serveur, nous
recevons une page d'erreur. En effet, les requêtes utilisant une adresse ip en
clair au lieu du nom de domaine www.mit.edu sont refusées.
