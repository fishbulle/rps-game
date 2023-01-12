# Reflektion

Jag valde att köra med JWT token då det kändes aktuellt och relativt enkelt att greppa samt passande för just det här projektet. 
Det var väldigt många klasser som behövdes i samband med implementeringen! Det kommer med andra ord inte vara så enkelt att 
reflektera över allting. Men jag ska försöka.

#### Väldigt enkelt förklarat hur säkerhetsaspekten ser ut/fungerar i mitt projekt i samband med registrering och inloggning:

Det första som nu händer i min applikation är att användaren måste registrera sig för att kunna spela och det sker 
i en process som ser ut ungefär så: användaren registrerar sig med förnamn, användarnamn och lösenord. 
Jag skulle eventuellt kunnat ha implementerat förnamn, efternamn och email också men då detta var första gången jag 
försökte mig på JWT så känns det som att det räcker med endast förnamn för tillfället.

I samband med denna registrering genereras ett random token på 256-mbit som sedan används i nästa metod för auktorisering 
som bekräftar att användaren skapats. Ett nytt token genereras igen och användaren kan börja spela.

Låter ju väldigt enkelt men i själva verket händer massa fler saker i bakgrunden. 
I JwtService klassen till exempel finns det metoder för att kolla så att token är validerad, 
att token inte har gått ut (när de skapas sätts ett utgångsdatum på 24 timmar fram i tiden) och 
även en metod för att signera token som skapas med en ’secret key’ för att bekräfta att det är rätt person som försöker ”claima” den.

Då detta är min första kontakt med denna typ av programmering (ur säkerhetsaspekt) har jag inte något att jämföra med, 
men tycker spontant att denna modell känns passande när det handlar om just sådana saker som ett relativt simpelt spel 
i webbläsaren tex. Jag antar att det finns ännu mer komplicerade system för säkerhet som man vill ha när det rör sig om 
kritisk information typ banker, regering, militär och så vidare.

Ett problem som jag stötte på i samband med implementeringen av registrering av en användare och som jag inte lyckats lösa 
är att metoderna getToken och setPlayerName i PlayerService blir överflödiga. De går inte att komma åt utan att registrera sig, 
och när man registrerar sig skapas automatiskt ett UUID playerId kopplat till användare och spelarens namn sätts baserat på vilket
namn man registrerar sig med (name inte username). Jag provade lägga till /auth/token och /user/name i SecurityConfig filen för 
endpoints som inte ska vara låsa bakom ett inloggningskrav, och det fungerar då att skapa en användare, men när jag kör nästa 
metod/endpoint för att starta ett nytt spel eller joina ett befintligt får jag 403 Forbidden. Så jag behöver klura lite på hur 
jag kan göra för att ge möjlighet att spela utan att behöva registrera sig.

Jag har lite svårt att se framför mig hur detta ska kunna kopplas ihop med frontend, särskilt delen för registrering/autentisering 
och inlogg, men jag litar på att det kommer bli hur bra som helst. Tycker att denna kurs har varit otroligt lärorik och rolig, 
absolut utmanande stundvis men det är bara kul. Det känns som att detta är något jag kommer ha stor nytta av i framtiden och 
jag kommer fortsätta öva genom att skapa egna projekt i Spring Boot :) 