# Contacts
Super-simple online contact management app


Wymagania:
Ąby zapewnić prawidłowe działanie aplikacji potrzebna jest pusta baza danych PostgreSQL o nazwie "kontakty"
Ponadto, potrzebny jest użytkownik o nazwie "postgres" i haśle "000000"

Uruchomienie:
Należy aktywować plik contacts.jar z konsoli używając polecenia "java -jar contacts.jar"
(Po pierwszym uruchomieniu należy dodać przynajmniej jeden wiersz do tabeli "users" (z poziomu pgAdmin))
Otworzyć przeglądarkę internetową, wpisując localhost:8090 w pasek adresu

Działanie:
Przed wejściem do aplikacji pojawi się komunikat proszący o podanie loginu i hasła - należy podać login i hasło zgodne z tym dodanym do tabeli users.
Aplikacja ma dwa widoki: Użytkownicy i Kontakty. Przełącza się między nimi używając hiperłącza górnego navbara
W widoku users można dodawać użytkowników, zmieniać ich hasła bądź też usuwać ich.
Danego nowego użytkownika (oraz nowe dane edytowanego użytkownika) należy wpisać w textboxy pod tabelą

W widoku Kontakty widzimy jedynie kontakty użytkownika przez którego zalogowaliśmy się do aplikacji.
Dodawanie i edytowanie kontaktów odbywa się tak samo jak w przypadku użytkowników, z tą różnicą, że wszystkie pola poza imieniem i nazwiskiem mogą przyjąc wartość pustą (null)
