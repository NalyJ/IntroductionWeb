## Exercice 1
Toute machine connectée au réseau possède une adresse IP associée. Si vous êtes capable de
naviguer sur Internet c'est que votre machine est connectée au réseau et qu'elle possède
une adresse IP. La commande `ifconfig` (sous Linux) ou `ipconfig` (sous Windows) vous permet
de connaître les informations liées aux cartes réseaux vues par le système.

	joaobrilhante@GlaDOS:~$ ipconfig
	...
	wlp2s0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
		inet 10.154.121.107  netmask 255.255.224.0  broadcast 10.154.127.255
		inet6 fe80::ba6c:336:a722:6d05  prefixlen 64  scopeid 0x20<link>
		ether b0:c0:90:b9:67:19  txqueuelen 1000  (Ethernet)
		RX packets 21815  bytes 6546308 (6.5 MB)
		RX errors 0  dropped 0  overruns 0  frame 0
		TX packets 3739  bytes 676425 (676.4 KB)
		TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

Mon adresse IP est donc **10.154.121.107**.
