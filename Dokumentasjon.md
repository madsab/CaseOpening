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
* <span style="color: green">Arv:</span> Knife-klassen arver fra Weapon-klassen

