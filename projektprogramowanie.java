import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.math.BigDecimal;

class projektprogramowanie{

    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void generowaniePrzerwy(int iloscLinijekPrzerwy){
        for(int i = 0; i < iloscLinijekPrzerwy; i++){
            System.out.println("");
        }
    }

    public static void generowanieKreski(int najdluzszeSlowo, int najdluzszaPunktacja, int najdluzszyPrzebieg, int najdluzszaCena, int najdluzszaPojemnoscSilnika, int procenty){
        String kreska = " ";
        for (int i = 0; i < najdluzszeSlowo + najdluzszaPunktacja + najdluzszyPrzebieg + najdluzszaCena + najdluzszaPojemnoscSilnika + procenty + 19; i++){
            kreska += "-";
        }
        System.out.println(ANSI_WHITE + kreska);
    }

    public static String generowanieSpacji(int dlugoscSlowa, int najdluzszeSlowo){
        String spacje = "";
        for (int i = 0; i <najdluzszeSlowo-dlugoscSlowa; i++){
            spacje += " ";
        }
        return(spacje);
    }

    public static void wygenerujTabelke(List<Samochod> auta, HashMap<String, Integer> wagi){
        generowaniePrzerwy(2);
        int najdluzszaNazwaSamochodu = 7;
        int najdluzszaPunktacja = 9;
        int najdluzszyPrzebieg = 13;
        int najdluzszaCena = 9;
        int najdluzszaPojemnoscSilnika = 24;
        int najmniejszaIloscPunktow = 13451345; //losowe liczby
        int najdluzszyBigDecimal = 0;



        for(Samochod auto : auta){
            String[] dane = auto.getValues(wagi);

            double procenty = (double) Integer.valueOf(dane[1]) / najmniejszaIloscPunktow*100;
            BigDecimal procentyBigDecimal = BigDecimal.valueOf(procenty);
            if (String.valueOf(procentyBigDecimal).length() > najdluzszyBigDecimal){
                najdluzszyBigDecimal = String.valueOf(procentyBigDecimal).length();
            }


            if(Integer.parseInt(dane[1]) < najmniejszaIloscPunktow){
                najmniejszaIloscPunktow = Integer.parseInt(dane[1]);
            }
            

            if(dane[0].length() > najdluzszaNazwaSamochodu){
                najdluzszaNazwaSamochodu = dane[0].length();
            }
            if(dane[1].length() > najdluzszaPunktacja){
                najdluzszaPunktacja = dane[1].length();
            }
        }
        String najmniejszaIloscPunktowString = "" + najmniejszaIloscPunktow;
        

        // granice tabelki
        generowanieKreski(najdluzszaNazwaSamochodu, najdluzszaPunktacja, najdluzszyPrzebieg, najdluzszaCena, najdluzszaPojemnoscSilnika, 22);
        System.out.println(" | Model" + generowanieSpacji(5, najdluzszaNazwaSamochodu) 
                            + " | " + "Punktacja" + generowanieSpacji(9, najdluzszaPunktacja) 
                            + " | " + "Przebieg w km" + generowanieSpacji(13, najdluzszyPrzebieg)
                            + " | " + "Cena w zł" + generowanieSpacji(9, najdluzszaCena)
                            + " | " + "Pojemność silnika w cm^3" + generowanieSpacji(24, najdluzszaPojemnoscSilnika)
                            + " | " + "Punktacja w procentach" + generowanieSpacji(24, 22) + " | "

                        );
        generowanieKreski(najdluzszaNazwaSamochodu, najdluzszaPunktacja, najdluzszyPrzebieg, najdluzszaCena, najdluzszaPojemnoscSilnika, 22);
        
        for(Samochod samochod : auta){
            String[] dane = samochod.getValues(wagi);
            double procenty = (double) Integer.valueOf(dane[1]) / najmniejszaIloscPunktow*100;
            BigDecimal procentyBigDecimal = BigDecimal.valueOf(procenty);
            if(!najmniejszaIloscPunktowString.equals(dane[1])){
                System.out.println(ANSI_WHITE + " | " + dane[0] + generowanieSpacji(dane[0].length(), najdluzszaNazwaSamochodu) +
                                 " | " + generowanieSpacji(dane[1].length(),najdluzszaPunktacja) + dane[1]
                                  + " | " + generowanieSpacji(dane[2].length(), najdluzszyPrzebieg)  + dane[2]
                                  + " | " + generowanieSpacji(dane[3].length(), najdluzszaCena) + dane[3]
                                  + " | " + generowanieSpacji(dane[4].length(), najdluzszaPojemnoscSilnika) + dane[4]
                                  + " | " + generowanieSpacji(String.valueOf(procentyBigDecimal).length(), najdluzszyBigDecimal+2) + procentyBigDecimal + "% |"
                                  
                                  );
            }else{
                System.out.println(ANSI_GREEN + " | " + dane[0] + generowanieSpacji(dane[0].length(), najdluzszaNazwaSamochodu) +
                                 " | " + generowanieSpacji(dane[1].length(),najdluzszaPunktacja) + dane[1]
                                  + " | " + generowanieSpacji(dane[2].length(), najdluzszyPrzebieg)  + dane[2]
                                  + " | " + generowanieSpacji(dane[3].length(), najdluzszaCena) + dane[3]
                                  + " | " + generowanieSpacji(dane[4].length(), najdluzszaPojemnoscSilnika)  + dane[4]
                                  + " | " + generowanieSpacji(String.valueOf(procentyBigDecimal).length(), najdluzszyBigDecimal+2) + procentyBigDecimal + "% |"
                                  );
            }
            generowanieKreski(najdluzszaNazwaSamochodu, najdluzszaPunktacja, najdluzszyPrzebieg, najdluzszaCena, najdluzszaPojemnoscSilnika, 22);
        }
    }

    // dzięki tej metodzie można ustawiać kolor tekstu 
    public static void enableAnsiColors() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "echo"," ");
                builder.inheritIO().start().waitFor();
                Process process = new ProcessBuilder("cmd", "/c", "echo", "\u001b[37m")
                    .inheritIO().start();
                    
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] dodajSamochod(int i){
        
        Scanner scanner = new Scanner(System.in);
        // ----------------------------------
        // |  Wprowadzanie nazwy samochodu  |
        // ----------------------------------
        System.out.println("Wprowadź nazwę auta nr " + (i + 1) + ": ");
        String nazwaAuta = scanner.nextLine();
        while (nazwaAuta.length() == 0){
            System.out.println("Trzeba uzupełnić nazwę samochodu, wpisz ponownie nazwę auta nr "+ (i+1));
            nazwaAuta = scanner.nextLine();
        }



        generowaniePrzerwy(1);

        // ---------------------------------
        // |  Wprowadzanie przebiegu auta  |
        // ---------------------------------
        System.out.println("Wprowadź przebieg auta nr " + (i + 1) + " w km: ");
        int przebiegAuta = scanner.nextInt();
        generowaniePrzerwy(1);
        while (przebiegAuta <= 0){
            System.out.println("Wprowadzony przebieg auta przyjmuje wartość niedodatnią lub nie jest liczbą całkowitą, czy chcesz jeszcze raz wprowadzić przebieg auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
            scanner.nextLine();
            String kontynuacja = scanner.nextLine();
            generowaniePrzerwy(1);
            if (kontynuacja.trim().toLowerCase().equals("yes")) {
                System.out.println("Wprowadź nowy przebieg auta:");
                przebiegAuta = scanner.nextInt();
                generowaniePrzerwy(1);
            } 
            else {
                System.out.println("To koniec");
                System.exit(0);
            }    
        }

        // ----------------------------------
        // |  Wprowadzanie ceny samochodu  |
        // ----------------------------------
        System.out.println("Wprowadź cenę auta nr " + (i + 1) + " w zł: ");
        int cenasamochodu = scanner.nextInt();
        generowaniePrzerwy(1);
        while (cenasamochodu <= 0) {
            System.out.println("Wprowadzona cena samochodu przyjmuje wartości niedodatnie lub nie jest liczbą całkowitą, czy chcesz jeszcze raz wprowadzić wagę przebiegu auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
            scanner.nextLine();
            String kontynuacja = scanner.nextLine();
            generowaniePrzerwy(1);

            if (kontynuacja.trim().toLowerCase().equals("yes")) {
                System.out.println("Wprowadź cenę samochodu jeszcze raz:");
                cenasamochodu = scanner.nextInt();
                generowaniePrzerwy(1);

            } 
            else {
                System.out.println("To koniec");
                System.exit(0);
            }    
        }

        // -----------------------------------------------
        //|| Wprowadzanie pojemności silnika samochodu  |
        // -----------------------------------------------
        System.out.println("Wprowadź pojemność silnika dla auta nr " + (i + 1)  + " w [cm^3]: ");
        int pojemnoscSilnika = scanner.nextInt();


        while (pojemnoscSilnika<=0) {
            System.out.println("Wprowadzona pojemność silnika samochodu przyjmuje wartości niedodatnie lub nie jest liczbą całkowitą, czy chcesz jeszcze raz wprowadzić wagę przebiegu auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
            scanner.nextLine();
            String kontynuacja = scanner.nextLine();
            generowaniePrzerwy(1);
            if (kontynuacja.trim().toLowerCase().equals("yes")) {
                System.out.println("Wprowadź pojemność silnika samochodu jeszcze raz:");
                pojemnoscSilnika = scanner.nextInt();
                generowaniePrzerwy(1);
            } 
            else {
                System.out.println("To koniec");
                System.exit(0);
            }    
        }
        String[] dodanySamochod = new String[]{nazwaAuta, String.valueOf(przebiegAuta), String.valueOf(cenasamochodu), String.valueOf(pojemnoscSilnika)};
        return dodanySamochod;
    }

    public static void main (String[] args){

        enableAnsiColors();
        System.out.println("Witaj w programie porównującym auta!");
        generowaniePrzerwy(2);

        List<Samochod> auta = new ArrayList<Samochod>();
        HashMap<String, Integer> wagi = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("uzyj danych przykladowych (1)");
        System.out.println("samemu wpisz dane (2)");
        
        if(scanner.nextInt() == 2){
            generowaniePrzerwy(1);
            System.out.println("Wprowadź ilość porównywanych aut: ");
            int liczbaAut = scanner.nextInt();
            generowaniePrzerwy(1);
            while (liczbaAut <= 0){
                generowaniePrzerwy(1);
                System.out.println("Wprowadzona liczba  nie jest liczbą całkowitą, jest za duża lub jest liczbą ujemną");
                System.out.println("Wprowadź liczbę aut jeszcze raz: ");
                liczbaAut = scanner.nextInt();
                generowaniePrzerwy(1);
                System.out.println("Nowa liczba aut jest równa: " + liczbaAut);
                generowaniePrzerwy(1);
            }
            

            for(int i = 0; i < liczbaAut; i++){
                String[] nowySamochod = dodajSamochod(i);
                
                auta.add(new Samochod(nowySamochod[0], Integer.parseInt(nowySamochod[1]), Integer.parseInt(nowySamochod[2]), Integer.parseInt(nowySamochod[3])));
            }

            // ---------------------------------------------------
            // |  Wprowadzanie wag pojemności silnika samochodu  |
            // ---------------------------------------------------
            System.out.println("Wprowadź wagę pojemnośc w skali <1,10>: ");
            int wagaPojemnosciSilnika = scanner.nextInt();
            generowaniePrzerwy(1);

            while (wagaPojemnosciSilnika < 1 || wagaPojemnosciSilnika > 10) {
                System.out.println("Wprowadzona waga pojemności silnika samochodu przyjmuje wartości spoza skali <1,10>, czy chcesz jeszcze raz wprowadzić wagę przebiegu auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
                scanner.nextLine();
                String kontynuacja = scanner.nextLine();
                generowaniePrzerwy(1);
                if (kontynuacja.trim().toLowerCase().equals("yes")) {
                        System.out.println("Wprowadź wagę pojemności silnika samochodu jeszcze raz:");
                        wagaPojemnosciSilnika = scanner.nextInt();
                        generowaniePrzerwy(1);
                        
                    } 
                else{
                        System.out.println("To koniec");
                        System.exit(0);
                    }    
            }

            // --------------------------------------
            // |  Wprowadzanie wagi ceny samochodu  |
            // --------------------------------------
            System.out.println("Wprowadź wagę ceny auta w skali <1,10>: ");
            int wagaCenySamochodu = scanner.nextInt();
            generowaniePrzerwy(1);

            while (wagaCenySamochodu < 1|| wagaCenySamochodu > 10) {
                System.out.println("Wprowadzona waga ceny samochodu przyjmuje wartości spoza zakresu <1,10> lub nie jest liczbą całkowitą, czy chcesz jeszcze raz wprowadzić wagę przebiegu auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
                scanner.nextLine();
                String kontynuacja = scanner.nextLine();
                generowaniePrzerwy(1);
                if (kontynuacja.trim().toLowerCase().equals("yes")) 
                    {
                        System.out.println("Wprowadź wagę ceny samochodu jeszcze raz:");
                        wagaCenySamochodu = scanner.nextInt();
                        generowaniePrzerwy(1);
                    } 
                else
                    {
                        System.out.println("To koniec");
                        System.exit(0);
                    }    
            }

            // --------------------------------------
            // |  Wprowadzanie wagi przebiegu auta  |
            // --------------------------------------
            System.out.println("Wprowadź wagę przebiegu auta w skali <1,10>: ");
            int wagaPrzebieguAuta = scanner.nextInt();
            generowaniePrzerwy(1);
            while (wagaPrzebieguAuta > 10||wagaPrzebieguAuta < 1) {
                System.out.println("Wprowadzona waga przebiegu auta przyjmuje wartości spoza zakresu <1,10> lub nie jest liczbą całkowitą, czy chcesz jeszcze raz wprowadzić wagę przebiegu auta? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
                scanner.nextLine();
                String kontynuacja = scanner.nextLine();
                generowaniePrzerwy(1);
                if (kontynuacja.trim().toLowerCase().equals("yes")) 
                    {
                        System.out.println("Wprowadź nową wagę przebiegu auta:");
                        wagaPrzebieguAuta = scanner.nextInt();
                        generowaniePrzerwy(1);
                        
                    } 
                else
                    {
                        System.out.println("To koniec");
                        System.exit(0);
                    }
            }
            wagi.put("wagaPojemnosciSilnika", wagaPojemnosciSilnika);
            wagi.put("wagaCenySamochodu", wagaCenySamochodu);
            wagi.put("wagaPrzebieguAuta", wagaPrzebieguAuta);



        }else{
            generowaniePrzerwy(1);
            auta.add(new Samochod("corsa", 1000, 45000, 1200));
            auta.add(new Samochod("camaro", 60000, 90000, 8212));
            auta.add(new Samochod("lancetti", 200000, 15000, 4213));
            auta.add(new Samochod("aveo", 40000, 13000, 4200));

            wagi.put("wagaPojemnosciSilnika", 3);
            wagi.put("wagaCenySamochodu", 2);
            wagi.put("wagaPrzebieguAuta", 4);
        }

        wygenerujTabelke(auta, wagi);

        boolean edytujUsun = true;
        if(auta.size()<=0){
            edytujUsun = false;
        }
        int indexPetli = 0;
        while(edytujUsun){
            generowaniePrzerwy(2);
            System.out.println("Czy chcesz edytować dane któregoś z pojazdów? Jeżeli tak wpisz w konsoli 'yes', w przeciwnym razie program się zakończy.");
            if (indexPetli < 1){
                scanner.nextLine();
            }
            
            String kontynuacja = scanner.nextLine();
            generowaniePrzerwy(1);
            if (kontynuacja.trim().toLowerCase().equals("yes")){
                System.out.println("Usuń pojazd (1)");
                System.out.println("Edytuj pojazd (2)");
                System.out.println("Dodaj pojazd (3)");
                int opcja = scanner.nextInt();
                generowaniePrzerwy(1);
                boolean kontynuowac = true;
                int iteracja = 0;
                switch (opcja) {
                    case 1:
                        
                        while(kontynuowac){
                            iteracja = 0;
                            System.out.println("wpisz nazwę modelu pojazdu które chcesz usunąć: ");
                            if(iteracja < 1){
                                scanner.nextLine();
                            }
                            
                            String nazwa = scanner.nextLine();
                            generowaniePrzerwy(1);
                            int index = 0;
                            int iloscElementowtablicy = auta.size();
                            for(Samochod samochod: auta){
                                if(samochod.getNazwa().trim().toLowerCase().equals(nazwa.trim().toLowerCase())){
                                    auta.remove(index);
                                    break;
                                };
                                index++;
                            }
                            if(iloscElementowtablicy == auta.size()){
                                System.out.println("Nie odnaleziono tego auta. Jeżeli chcesz jeszcze raz wprowadzić dane, wpisz w konsoli 'yes'");
                                kontynuowac = scanner.nextLine().trim().toLowerCase().equals("yes")? true:false;
                                generowaniePrzerwy(1);
                                iteracja++;
                            }else{
                                System.out.println("Samochód został usunięty z kolekcji.");
                                wygenerujTabelke(auta, wagi);
                                kontynuowac = false;
                                
                            }
                        }
                        break;

                    case 2:
                        iteracja = 0;
                        while(kontynuowac){
                            System.out.println("wpisz nazwę modelu pojazdu które chcesz edytować: ");
                            if(iteracja < 1){
                                scanner.nextLine();
                            }
                            String nazwa = scanner.nextLine();
                            generowaniePrzerwy(1);
                            boolean wszystkoOk = false;
                            for(Samochod samochod: auta){
                                if(samochod.getNazwa().trim().toLowerCase().equals(nazwa.trim().toLowerCase())){
                                    generowaniePrzerwy(1);
                                    System.out.println("Nazwa modelu (1)");
                                    System.out.println("Przebieg (2)");
                                    System.out.println("Cena (3)");
                                    System.out.println("Pojemność silnika (4)");
                                    int nowaOpcja = scanner.nextInt();
                                    generowaniePrzerwy(1);

                                    switch(nowaOpcja){
                                        case 1:
                                            System.out.println("Podaj nową nazwę modelu: ");
                                            scanner.nextLine();
                                            samochod.changeNazwa(scanner.nextLine());
                                            wszystkoOk = true;
                                            break;
                                        case 2:
                                            System.out.println("Podaj nowy przebieg: ");
                                            samochod.changePrzebieg(scanner.nextInt());
                                            wszystkoOk = true;
                                            break;
                                        case 3:
                                            System.out.println("Podaj nową cenę: ");
                                            samochod.changeCena(scanner.nextInt());
                                            wszystkoOk = true;
                                            break;
                                        case 4:
                                            System.out.println("Podaj nową Pojemność silnika: ");
                                            samochod.changePojemnoscSilnika(scanner.nextInt());
                                            wszystkoOk = true;
                                            break;
                                        default:
                                            System.out.println("Wpisano nieprawidłową opcję");
                                    }

                                    wygenerujTabelke(auta, wagi);
                                    break;
                                }
                            }
                            if(!wszystkoOk){
                                System.out.println("Nie ma takiego samochodu, jeżeli chcesz wpisać jego nazwę jeszcze raz, wpisz w konsoli 'yes'.");
                                kontynuowac = scanner.nextLine().trim().toLowerCase().equals("yes")? true:false;
                                generowaniePrzerwy(1);
                                iteracja++;
                            }
                            
                            
                        }
                        break;


                    case 3:
                        String[] nowySamochod = dodajSamochod(auta.size());
                        auta.add(new Samochod(nowySamochod[0], Integer.parseInt(nowySamochod[1]), Integer.parseInt(nowySamochod[2]), Integer.parseInt(nowySamochod[3])));
                        wygenerujTabelke(auta, wagi);
                        scanner.nextLine();
                        break;    


                    default:
                        System.out.println("Wpisano nieprawidłową opcję");
                }
                
            }else{
                System.out.println("To koniec");
                System.exit(0);
            }
            indexPetli++;
        }

    }


}





