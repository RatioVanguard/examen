/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author vongenae
 */
public class StudentenBeheer {

    StudentenDAO studentenDAO;

    public StudentenBeheer() throws ClassNotFoundException, IOException {
        studentenDAO = maakStudentenDAO();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        StudentenBeheer beheer = new StudentenBeheer();
        try (Scanner in = new Scanner(System.in)) {
            in.useDelimiter("\n");
            try (PrintWriter uit = new PrintWriter(System.out)) {
                int keuze = beheer.leesKeuze(uit, in);
                while (keuze > 0 && keuze < 4) {
                    if (keuze == 1) {
                        beheer.zoekDocent(uit, in);
                    } else if (keuze == 2) {
                        beheer.zoekStudent(uit, in);
                    } else if (keuze == 3) {
                        beheer.schrijfStudentIn(uit, in);
                    }
                    keuze = beheer.leesKeuze(uit, in);
                }
                beheer.sluitAf();
            }
        }
    }

    private StudentenDAO maakStudentenDAO() throws ClassNotFoundException, IOException {
        StudentenDAO doa;
        File opslag = new File("StudentenDAO");
        if (opslag.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(opslag))) {
                doa = (StudentenDAO) in.readObject();
            }
        } else {
            doa = new StudentenDAO();
            try (Scanner invoer = new Scanner(new File("opleidingsonderdelen.txt"))) {
                invoer.useDelimiter(";");
                while (invoer.hasNext()) {
                    int jaar = invoer.nextInt();
                    String vak = invoer.next();
                    String titularis = invoer.next();
                    int studiepunten = invoer.nextInt();
                    doa.voegVakToe(vak, titularis, jaar, studiepunten);
                    invoer.nextLine();
                }
            }
        }
        return doa;
    }

    private void sluitAf() throws IOException {
        File opslag = new File("StudentenDAO");
        try (ObjectOutputStream uitvoer = new ObjectOutputStream(new FileOutputStream(opslag))) {
           uitvoer.writeObject(studentenDAO);
        }
    }

    private int leesKeuze(PrintWriter uit, Scanner in) {
        uit.println("Maak een keuze:");
        uit.println();
        uit.println("1. Een docent zoeken");
        uit.println("2. Een student zoeken");
        uit.println("3. Een student inschrijven");
        uit.println("4. Stoppen");
        uit.print("Keuze: ");
        uit.flush();
        return in.nextInt();
    }

    private void zoekDocent(PrintWriter uit, Scanner in) {
        String naam = vraagInfo(uit, in, "Geef de naam van een docent: ");
        Docent docent = studentenDAO.zoekDocent(naam);
        if (docent != null) {
            uit.print(naam);
            uit.println(" geeft de volgende vakken:");
            for (Opleidingsonderdeel vak : docent.getVakken()) {
                uit.println(vak.getTitel());
            }
        } else {
            uit.println("Docent niet gevonden");
        }
        uit.flush();
    }

    private void schrijfStudentIn(PrintWriter uit, Scanner in) throws ParseException {
        uit.println("Deel I: Personalia");
        String voornaam = vraagInfo(uit, in, "Voornaam: ");
        String naam = vraagInfo(uit, in, "Naam: ");
        String geboortedatumString = vraagInfo(uit, in, "Geboortedatum (dd-MM-yyyy): ");
        Date geboortedatum = new SimpleDateFormat("dd-MM-yyyy").parse(geboortedatumString);;
        String adres = vraagInfo(uit, in, "Adres: ");
        String postcode = vraagInfo(uit, in, "Postcode: ");
        String gemeente = vraagInfo(uit, in, "Gemeente: ");

        Student student = new Student(voornaam, naam, adres, postcode, gemeente, geboortedatum);
        studentenDAO.voegStudentToe(student);
        
        uit.println("Deel II: Curriculum");
        String vak = vraagInfo(uit, in, "Geef de naam van een opleidingsonderdeel (lege string om te stoppen): ");
        while (!vak.equals("")) {
            Opleidingsonderdeel opleidingsonderdeel = studentenDAO.zoekOpleidingsonderdeel(vak);
            if (opleidingsonderdeel != null) {
                String gelukt = studentenDAO.schrijfStudentIn(student, vak);
                uit.println(gelukt);
            } else {
                uit.println("Onbestaand opleidingsonderdeel");
            }
            vak = vraagInfo(uit, in, "Geef de naam van een opleidingsonderdeel (lege string om te stoppen): ");
        }
    }

    private void zoekStudent(PrintWriter uit, Scanner in) {
        String naam = vraagInfo(uit, in, "Geef de naam van een student: ");
        Student student = studentenDAO.zoekStudent(naam);
        if (student != null) {
            uit.print(naam);
            uit.println(" is ingeschreven voor:");
            for (Opleidingsonderdeel vak : student.getCurriculum()) {
                uit.println(vak.getTitel());
            }
        } else {
            uit.println("Student niet gevonden");
        }
        uit.flush();
    }

    private String vraagInfo(PrintWriter uit, Scanner in, String bericht) {
        uit.print(bericht);
        uit.flush();
        String data = in.next();
        return data;
    }
}
