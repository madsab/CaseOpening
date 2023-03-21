# Description of App
This application is simulating a case opening from the game Counter Strike: Global Operation, where you can open a case and get a random weapon. The weapons have different rarities and the knife is the golden prize. There will be a login system to keep track of a users weapons, and as an added bonus: A shooting-range for testing out the different weapons

## Classes
>**User.java**: Keeping track of user information. Keys, weapons and such.

>**GunTemplate.java**: A Template that contains methods that all guns use. Example fire or reload.

> **"Weapon_Name.".java**: Weapon class with information about the desired weapon. Extends from a GunTemplate.java.

>**Case.java**: The Case that contains all added weapons and chooses a random one. Relation: One to many

>**Key.java**: A Key that is used to open a Case.

More coming. Shooting range dlc coming soon.

## File storaging
- Store users and password in a file. So authorization is valid if user is in the file and the password is correct.
- A Users weapons and amount of keys is stored in an own file for that user.


## Tests
- Test if case can be opened with key, and not if user has no keys
- Test if weapons aquired from case is stored in users owned weapons
- Test if case works properly and is random
- Test if user loggin works
- (Optional) Test if shooting range works on picked weapon

