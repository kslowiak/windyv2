# Elevator System

## Opis
Ten projekt, składający się z dwóch klas ElevatorSystem i Elevator, implementuje system zarządzania windami. 
System obsługuje wiele wind (ich liczba jest przechowywana w zmiennej numElevators w ElevatorSystem) i zarządza ich ruchem na podstawie zgłoszonych żądań.

![image](https://github.com/kslowiak/windyv2/assets/149679912/8e4b6c8a-4441-4da6-be22-48e936d0721b)

Klasa **Elevator** przechowuje dane dotyczące pojedynczej windy (poniżej konstruktor): 

    public Elevator(int id) {
        this.id = id; 
        this.currentFloor = 0; // Default starting floor
        this.targetFloor = 0;
        this.direction = 0; // Initially idle
        this.isTaken = false;
    }

od góry:
id windy, obecne piętro, piętro docelowe, kierunek (1 w górę, -1 w dół, 0 w spoczynku), wartość boolean czy winda jest zajęta

Klasa **ElevatorSystem** przechowuje główną część programu: 

- liste wind oraz listę ich wezwań 
- algorytm operowania windami w metodzie step()

algorytm polega na wezwaniu do zgłoszenia najbliższej windy wolnej lub windy zajętej przemieszczającej się w tym samym kierunku.
umożliwia to dużą rotację wind (algorytm preferuje przemieszczanie się windy w tym samym kierunku, a więc na dalekie odległości)
oraz uwzględnia kolejność wezwań, wraz z utworzeniem kolejki w przypadku przeładowania wind.
Co prawda algorytm mógłby działać szybciej z odpowiednim sortowaniem zgłoszeń, lecz mogłoby to, w przypadku nieprzychylnej ich kolejności, doprowadzić do bardzo długiego czasu oczekiwania pojedynczej osoby.
Z tego powodu postanowiłem nie poświęcać tejże osoby dla dobra ogółu i ustanowić chronologiczną kolejność zgłoszeń, z wyjątkiem osób które dało się zebrać po drodze.

Output:
![image](https://github.com/kslowiak/windyv2/assets/149679912/2508c67c-5ad3-403f-bee8-40d5e709fe48)

Przykład dla 3 wind: jak można zobaczyć każda z wind jest opisana 5 wartościami: id, obecnym piętrem, piętrem docelowym, kierunkiem oraz informacjączy jest zajęta.
Windy pokazują się w blokach, każdy z nich jest kolejną iteracją algorytmu (liczba iteracji znajduje się w pętli for w main())
Dodatkowo pojawiają się informacje o przyjęciu danego wezwania przez windę o określonym id. 
Przy niektórych operacjach występuje także dopiska "additional passanger". Pojawia się ona w przypadku gdy dana winda operuje więcej niż jedno zgłoszenie.
Wezwania na piętro docelowe z wewnątrz windy mogą być obsługiwane przez metodę move() znajdującą się w klasie ElevatorSystem.


