## Exercice 4
Utilisez la commande `ping` afin de confirmer que les machines de vos camarades sont joignables
par le réseau. Par défaut, la commande `ping` doit être arrêté avec `Ctrl+C`.

	joaobrilhante@GlaDOS:~$ ping 10.154.121.147
	PING 10.154.121.147 (10.154.121.147) 56(84) bytes of data.
	64 bytes from 10.154.121.147: icmp_seq=1 ttl=64 time=181 ms
	64 bytes from 10.154.121.147: icmp_seq=2 ttl=64 time=20.0 ms
	64 bytes from 10.154.121.147: icmp_seq=3 ttl=64 time=65.3 ms
	64 bytes from 10.154.121.147: icmp_seq=4 ttl=64 time=36.5 ms
	64 bytes from 10.154.121.147: icmp_seq=5 ttl=64 time=114 ms
	64 bytes from 10.154.121.147: icmp_seq=6 ttl=64 time=193 ms

	--- 10.154.121.147 ping statistics ---
	6 packets transmitted, 6 received, 0% packet loss, time 5005ms
	rtt min/avg/max/mdev = 20.090/102.066/193.850/67.459 ms

Nous constatons que les machines locales sont accessibles.
