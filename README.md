# ab4systems-backend-app

Popescu Mihaela
Universitatea Politehnica Bucuresti
Facultatea de Automatica si Calculatoare

Descrierea inputului de la intrare
Inputul este de forma:
<Numele tarii/judetului/orasului>:[COUNTRY | REGION | CITY]
	<Numele judet/oras ce apartine de tara de mai sus>:[REGION | CITY]
		?locatie1:pret_mediu_pe_zi:[activitate1/pret_activ1, ...]:startdate:enddate
	-
-

Nota: "-" e folosit pentru semnalarea faptului ca terminam de parsat copiii ierarhici si
posibilele locatii ce apartin de o tara/un judet/un oras.
Locatiile pot fi pe oricare nivel ierarhic (tara/judet/oras).

Arhitectura propusa
Am ales sa ma folosesc (pentru a rezolva cerinta bonusului care cere permiterea adaugarii
de nivele de ierarhizare in viitor) de design pattern abstract factory.

<<abstract class AbstractPlaceFactory>>              <------ FactoryProducer
		        ^			       uses
		extends |
			|
			|

		class PlaceFactory (versiunea tari/judete/orase, ce poate fi inlocuita)

			|
			| creates
			|
		       \/

		<< abstract class Place >>
			^
			|
			|	extends
			|
			|

		class Country
		class Region
		class City


Asta a fost design patternul pentru abstract factory.

Mai departe, toate datele de intrare vor fi parsate dintr-un fisier de intrare, cu
formatul ca cel prezentat la inceputul README-ului. Datele vor fi abstractizate
si salvate intr-un obiect de tipul Database. Acest obiect contine ierarhia de
tari/judete/orase, cat si niste hashmapuri ce permit accesul rapid si convenient
la anumite date, necesare pt rezolvarea taskurilor 1/2/3.

Clasele ce extind Place nu au suprascris nicio metoda, dar am ales totusi sa pastrez
Place ca fiind clasa abstracta in loc de clasa, pentru ca exista cu siguranta
functionalitati ce pot fi implementate mai tarziu, care vor arata diferit in functie
de clasa pentru care le implementez.
