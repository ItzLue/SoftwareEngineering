# SoftwareHuset A/S

## Om projektet 
Dette projekt går ud på at lave et program som kan gøre det nemmere for SoftwareHuset A/S. 

## Guide til programmet 
Vi har lavet et console UI til vores program, for at starte programmet skal filen [UI](src/main/java/ui/UI.java) køres.
Herefter vises vores hovedmenu med følgende muligheder.
### Hovedmenu
```console
Welcome to SoftwareHuset A/S
1) Developer menu
2) Project menu
3) Quit
```
### Medarbejder menu
```console
Devs menu
1) Set an active developer
2) Show developers
3) Add developer
4) Set worked hours
5) Remove developer
6) Back
```
### Projekt menu
```console
Project menu
1) Show projects
2) Add project
3) Activity menu
4) Add project leader
5) Set start date for a project
6) Set end date for a project
7) Change name
8) Remove project
9) Back
```
#### Hvordan tilføjes en medarbejder?
For at tilføje en medbarbejder til systemet vælges **Developer menu** herefter vælges **Add developer**
Der vil nu være mulighed for at indtaste et fornavn og efternavn.
```console
Add developer
Enter the first name: John
Enter the last name: Doe
Action successful!
```
Hvis både fornavn og efternavn lever op til kravene. Vil man få beskeden **Action successful!** medarbejderen har nu fået et ID og er blevet oprettet i systemet.
#### Hvordan tilføjes et projekt?
Et projekt tilføjes ved at vælge **Project menu** herefter vælges **Add project**. Når et projekt skal oprettes skal det blot gives et navn.
```console
Add a project could be something like 'Marcosoft'
Please enter a name for the project: Marcosoft
Action successful!
```
Hvis projektnavnet er gyldigt vil projektet blive tilføjet til systemet. 
## Gruppe 08
- Christian Emil Tchernokojev Houmann -- s194602 @194602
- Joachim Touveneau Petersen -- s194296 @JoachimTepe
- Regin Steffansson Kunoy -- s183462 @OGummi
- Loui Nissen-Petersen -- s194294 @ItzLue
