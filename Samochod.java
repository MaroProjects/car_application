import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.math.BigDecimal;


public class Samochod {
    String nazwa;
    int przebiegWKm;
    int cena;
    int pojemnoscSilnika;
    int wagaPrzebiegWKm;
    int wagaCena;
    int wagaPojemnoscSilnika;

    public Samochod(String nowaNazwa, int nowaPrzebiegWKm, int nowaCena, int nowaPojemnoscSilnika) {
        this.nazwa = nowaNazwa;
        this.przebiegWKm = nowaPrzebiegWKm;
        this.cena = nowaCena;
        this.pojemnoscSilnika = nowaPojemnoscSilnika;
    }

    public String[] getValues( HashMap<String, Integer> wagi) {
        return new String[]{nazwa, String.valueOf(przebiegWKm * wagi.get("wagaPrzebieguAuta") + cena * wagi.get("wagaCenySamochodu") - pojemnoscSilnika * 50 * wagi.get("wagaPojemnosciSilnika")), String.valueOf(przebiegWKm), String.valueOf(cena), String.valueOf(pojemnoscSilnika)};
    }

    public String getNazwa(){
        return this.nazwa;
    }


    public void changeNazwa(String nazwa){
        this.nazwa = nazwa;
    }
    
    public void changePrzebieg(int przebiegWKm){
        this.przebiegWKm = przebiegWKm;
    }
    
    public void changeCena(int cena){
        this.cena = cena;
    }

    public void changePojemnoscSilnika(int pojemnoscSilnika){
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

}

