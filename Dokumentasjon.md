# Dokumentasjon
---
## Beskrivelse
CaseOpening er et javaFx prosjekt skrevet i Java som tar insperasjon i det populære spillet Counter Strike Global Offensive sin "Case Opening". I CaseOpening kan man lage sin egen bruker og åpne våpenkasser. Disse kassene gir forskjellige våpen i forskjellige rarities, der blant annet kniver er de mest sjeldene våpnene å få. Du kan samle opp våpen og selge de i butikken for "nøkler", som igjen brukes til å åpne flere våpenkasser.

## Klasse-Diagram
Dette klassediagrammet viser sammenhengen mellom klassene i selve åpningene av kassene:

![Bilde av et klasseDiagram](/images/KlasseDiagram.png)

Diagrammet over viser sammenhengen mellom hvordan en CaseSpinner har en Case den henter Weapon fra og kan gi de våpene videre til User som igjen har et sett med våpen. Knife-klassen arver fra Weapon og Case-klassen har en oversikt over knivene sine.

## Spørsmål
1. Hvilke deler av pensum i emnet dekkes i prosjektet, og på hvilken måte? (For
eksempel bruk av arv, interface, delegering osv.)
* <span style="color: green">Arv:</span> Knife-klassen arver fra Weapon-klassen fordi en kniv skal ha de samme metodene som et våpen, men må ha sin egen funksjon for å bruke et spesielt bilde.
* <span style="color: green">Interface:</span> WeaponNameComparator og WeaponRarityComparator klassene implementerer Comparator-interfacet. Dette brukes til å sortere lister etter ønsket måte ved blant annet skriving til fil.
* <span style="color: green">Delegering:</span> Delegeringen blir brukt ofte av Controller-klassene. For eksempel vil spinCase()-funksjonen i CaseOpenerController delegere oppgaven videre ned til CaseSpinner sin spinCase()-funksjon.

2. Dersom deler av pensum ikke er dekket i prosjektet deres, hvordan kunne dere
brukt disse delene av pensum i appen?
* <span style="color: red">Observatør-Observert-teknikken:</span> Denne teknikken kunne for eksempel vært brukt  

3. Hvordan forholder koden deres seg til Model-View-Controller-prinsippet? (Merk:
det er ikke nødvendig at koden er helt perfekt i forhold til Model-View-Controller
standarder. Det er mulig (og bra) å reflektere rundt svakheter i egen kode)
4. Hvordan har dere gått frem når dere skulle teste appen deres, og hvorfor har
dere valgt de testene dere har? Har dere testet alle deler av koden? Hvis ikke,
hvordan har dere prioritert hvilke deler som testes og ikke? (Her er tanken at
dere skal reflektere rundt egen bruk av tester) 

