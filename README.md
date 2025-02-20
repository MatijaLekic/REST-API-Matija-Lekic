# REST API za upravljanje zadacima

Ovo je sistem koji može da vrši CRUD operacije (kreiranje, pretraživanje, ažuriranje i brisanje) nad zadacima i 
članovima tima koji rade na tim zadacima. Osim toga, projekat pokazuje i kako se kreiraju veze između zadataka
i članova, kako se komunicira sa bazom podataka i kako se prave test primeri. 

Od dodatnih funkcionalnosti, ovaj sistem može da vrši sortiranje, filtriranje, paginaciju i validaciju podataka.


## Alati i tehnologije koji su potrebni za pokretanje projekta:

- Programski jezik:		Java 17+
- Framework: 			Spring Boot 3
- Baza podataka:		H2
- Alati:				Eclipse, Maven
- Testiranje:			JUnit 5


## Instalacija datih tehnologija:

- Java 17+: 		Preuzmite i instalirajte JDK 17 sa sajta Oracle 
					(https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)	
					
- Eclipse:  		Preuzmite i instalirajte Eclipse IDE sa Eclipse zvaničnog sajta
					(https://www.eclipse.org/downloads/)
					Prilikom pokretanja, izaberite "Eclipse IDE for Java Developers"
					
- Maven:    		Integrisan sa Eclipse-om

- Spring Boot 3:	Projekat je tipa Spring Boot, tako da ga je dovoljno samo otvoriti u Eclipsu
		 
- H2:				Već ugrađen u Spring Boot projekat

- JUnit 5:      	Već ugrađen u Spring Boot projekat


## Organizacija projekta

Ceo projekat je podeljen u 5 sekcija (foldera): Models, Repo, Controllers, Validators i Test.

- Models sadrži entitete koji će se mapirati u bazi podataka. U ovom slučaju, to su Zadatak i Clan.

- Repo omogućuje CRUD operacije nad tim entitetima. Sadrži fajlove za svaki entitet.

- Controllers implementira metode koje se pozivaju od strane HTTP adrese ili JUnit 5 testa. Takođe sadrži 
  fajlove za svaki entitet.
  
- Validators sadrži posebnu logiku za validaciju podataka. Njega poziva neki controller kako bi saznao da 
  li su parametri metode ispravni, pre nego što nastavi sa izvršenjem te metode.
  
- Test poziva metode controller-a. Svaki fajl u ovom folderu je jedan test.


## Pokretanje projekta:

Nakon otvaranja u Eclipse-u, projekat se pokreće desnim klikom na DemoApplication.java ->
-> Run as -> Java Application.

Nakon pokretanja, H2 baza podataka se može otvoriti na adresi http://localhost:8080/h2-console sa 
podacima za prijavu navedenim u application.properties:

	spring.datasource.url=jdbc:h2:mem:testdb
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=password
	spring.h2.console.enabled=true

Funkcionalnosti controllera se mogu isprobati direktnim pristupom HTTP adresi ili JUnit 5 testom. JUnit 5 testovi 
se pokreću desnim klikom na neki test fajl -> Run as -> JUnit test.


## Pronašli ste grešku?

Ukoliko uočite grešku ili imate neku ideju kako bi mogao da se poboljša ovaj projekat, popunite Google 
formu na linku:

	https://forms.gle/TXR3KncVGgw8btPN6


## Poznate greške (radimo na ispravljanju)

	/
