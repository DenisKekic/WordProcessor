import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WordProcessor {

    public static void text() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Geben Sie eine Zeichenkette ein: ");
            String eingabe = in.nextLine();
            System.out.println(WordProcessor.processWord(eingabe));
        } catch (InputMismatchException ex) {
            System.out.println("Es wurde eine Zeichenkette erwartet");
        }
        in.close();
    }

    public static void datei() {
        try {
            System.out.println("Geben Sie den Pfad zur Datei ein: ");
            Scanner in = new Scanner(System.in);
            String pfad = in.nextLine();
            Scanner scanner = new Scanner(new File(pfad));
            String temp = "";
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    //temp = temp + s[i];
                    //System.out.println(s[i]);
                    System.out.println(WordProcessor.processWord(s[i]));
                }
                //System.out.println(temp);
                //System.out.println(WordProcessor.processWord(temp));
                //scanner.close();
            }
            //System.out.println(WordProcessor.processWord(temp));
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static String processWord(String str) {
        String[] split = str.split("\\s+"); //Unterteilt den String in eigene Teile
        int i;
        String temp = "";
        for (i = 0; i < split.length; i++) // split.length
        {
            String wort = split[i]; // Einzelne Woerter
            int laenge = wort.length(); // laenge von dem wort

            if (wort.endsWith("_")) // prüft ob letztes zeichen vom wort ein underscore ist
            {
                wort = wort.toLowerCase(); // das wort wird klein geschrieben
                StringBuilder loeschen = new StringBuilder(wort); // stringbuilder zum einfügen und löschen .. bearbeiten
                loeschen.deleteCharAt(laenge - 1); // löschen vom letzten zeichen

                if (loeschen.charAt(0) == '_') //löscht das erste zeichen wenn es ein underscore ist wie bei zb _Hallo_
                {
                    loeschen.deleteCharAt(0); // löscht erstes zeichen
                }

                wort = loeschen.toString();
            } else if (wort.startsWith("_")) // prüft ob erstes zeichen ein underscore ist
            {
                wort = wort.toUpperCase(); // das wort gross schreiben
                StringBuilder loeschen = new StringBuilder(wort); // stringbuilder zum einfügen und löschen .. bearbeiten
                loeschen.deleteCharAt(0); // löscht den underscore
                wort = loeschen.toString(); // der builder wird wieder zum string
            }
            temp = temp + " " + wort;
            str = temp;
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int auswahl;

        try {
            System.out.println("Wie moechten sie auslesen:\n1...von der Konsole\n2...aus der Datei");
            auswahl = in.nextInt();

            if (auswahl == 1) {
                WordProcessor.text();
            } else if (auswahl == 2) {
                WordProcessor.datei();
            } else {
                System.out.println("Waehlen Sie einen Modus aus.");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Waehlen Sie einen Modus aus.");
        }

        //System.out.println(WordProcessor.processWord(str));
    }
}