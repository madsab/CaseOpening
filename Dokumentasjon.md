# Dokumentasjon
---
## Beskrivelse
CaseOpening er en JavaFx-applikasjon som er inspirert av det populære spillet Counter-Strike Global Offensive sin "Case Opening". Applikasjonen gir brukeren muligheten til å åpne sine egne våpenkasser og samle opp våpen i forskjellige rariteter, inkludert sjeldne kniver.

Ved å lage en brukerprofil i applikasjonen, kan man åpne kasser som gir ulike typer våpen. For å få flere kasser kan man selge våpen i butikken og få "nøkler" som kan brukes til å åpne nye kasser. Butikken i applikasjonen gir også brukeren muligheten til å kjøpe våpen direkte med "nøkler" som valuta.

Applikasjonen gir brukeren en spennende opplevelse med å åpne kasser og samle på våpen. Det er enkelt å navigere i applikasjonen og det gir en god brukeropplevelse. 

CaseOpening er en morsom og spennende applikasjon for spillentusiaster som ønsker å oppleve spenningen ved å åpne virtuelle våpenkasser og samle på våpen. Applikasjonen tilbyr en autentisk opplevelse som ligner på den i spillet, og gir brukeren muligheten til å få tilgang til sjeldne våpen uten å måtte betale for det i spillet.

## Klasse-Diagram
Dette klassediagrammet viser sammenhengen mellom klassene i selve åpningene av kassene:

![Bilde av et klasseDiagram](/images/KlasseDiagram.png)

Diagrammet over viser sammenhengen mellom hvordan en CaseSpinner har en Case den henter Weapon fra og kan gi de våpene videre til User som igjen har et sett med våpen. Knife-klassen arver fra Weapon og Case-klassen har en oversikt over knivene sine.

## Spørsmål
1. **Hvilke deler av pensum i emnet dekkes i prosjektet, og på hvilken måte? (For
eksempel bruk av arv, interface, delegering osv.)**
* <span style="color: green">Arv:</span> Knife-klassen arver fra Weapon-klassen fordi en kniv skal ha de samme metodene som et våpen, men må ha sin egen funksjon for å bruke et spesielt bilde.
* <span style="color: green">Interface:</span> WeaponNameComparator og WeaponRarityComparator klassene implementerer Comparator-interfacet. Dette brukes til å sortere lister etter ønsket måte ved blant annet skriving til fil.
* <span style="color: green">Delegering:</span> Delegeringen blir brukt ofte av Controller-klassene. For eksempel vil spinCase()-funksjonen i CaseOpenerController delegere oppgaven videre ned til CaseSpinner sin spinCase()-funksjon.

2. **Dersom deler av pensum ikke er dekket i prosjektet deres, hvordan kunne dere
brukt disse delene av pensum i appen?**
* <span style="color: red">Observatør-Observert-teknikken:</span> Denne teknikken kunne for eksempel vært brukt ved at User's kan abonnere på en Case og få en melding hvis Casen forander hvilke våpen den kan gi. På den måten vil Casen bli observert, og User's være observatører.

3. **Hvordan forholder koden deres seg til Model-View-Controller-prinsippet? (Merk:
det er ikke nødvendig at koden er helt perfekt i forhold til Model-View-Controller
standarder. Det er mulig (og bra) å reflektere rundt svakheter i egen kode)**
* Selve koden følger MVC-prinsippet til en god grad. Det meste av logikk ligger i klassene. På denne måten oppretter Controlleren instanser av disse klassene for å bruke logikken. På den andre siden er det ikke Modellen som oppdaterer View (Brukergrensensittet), men heller Controller. Dette vil si at Controllerne spør Modellene om logikken. Modellene gir tilbake svar, og Controlleren oppdaterer hva brukeren ser i View. Det skal sies at det finnes instanser av perfekt bruk av MVC-prinsippet hvor Modellen oppdaterer View. Dette tas i bruk i CaseSpinner klassen for eksempel.

4. **Hvordan har dere gått frem når dere skulle teste appen deres, og hvorfor har
dere valgt de testene dere har? Har dere testet alle deler av koden? Hvis ikke,
hvordan har dere prioritert hvilke deler som testes og ikke? (Her er tanken at
dere skal reflektere rundt egen bruk av tester)**
* Testene er laget for å teste de mest grunnleggende klassene i koden. Dette er fordi det grunleggende må fungere korrekt for at resten av logikken skal stemme. Av denne grunnen blir ikke alle klassene testet, ettersom mange av de driver bare med oppdateringen av brukergrensesnittet. Grunnklassene Weapon, User og UserFileWriterReader er de som testes fordi ikke er avhengig av View for å teste funksjonene sine. 

