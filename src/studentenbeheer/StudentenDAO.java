/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vongenae
 */
public class StudentenDAO implements Serializable {

    private Map<String, Docent> docenten;
    private Map<String,Student> studenten;
    private Map<String,Opleidingsonderdeel> opleidingsonderdelen;

    public StudentenDAO() {
        docenten = new HashMap<>();
        studenten = new HashMap<>();
        opleidingsonderdelen = new HashMap<>();
    }
    
    public void voegVakToe(String titel, String titularis, int studiejaar, int aantalStudiepunten) {
        String[] stukjes = titularis.split(" ");
        String voornaam = stukjes[0];
        String naam = stukjes[1];
        Docent docent ;
        if (docenten.containsKey(titularis)) {
            docent = docenten.get(titularis);
        } else {
            docent = new Docent(voornaam,naam);
            docenten.put(titularis, docent);
        }
        Opleidingsonderdeel vak = new Opleidingsonderdeel(titel, docent, studiejaar,aantalStudiepunten);
        opleidingsonderdelen.put(titel,vak);
        docent.voegVakToe(vak);
    }
    
    public void voegStudentToe(Student student) {
        studenten.put(student.getVoornaam()+" "+student.getNaam(), student);
    }
    
    public String schrijfStudentIn(Student student, String vak) {
        String resultaat;
        Opleidingsonderdeel opleidingsonderdeel = opleidingsonderdelen.get(vak);
        if (opleidingsonderdeel != null && student.getAantalStudiepunten()+opleidingsonderdeel.getAantalStudiepunten() < 60) {
            opleidingsonderdeel.schrijfStudentIn(student);
            student.neemOpleidingsonderdeelOp(opleidingsonderdeel);
            resultaat = "Student is ingeschreven";
        } else {
            resultaat = "Onbestaand vak of te veel studiepunten";
        }
        return resultaat;
    }
    
    public Docent zoekDocent(String docent) {
        return docenten.get(docent);
    }

    Student zoekStudent(String naam) {
        return studenten.get(naam);
    }
    
    public Opleidingsonderdeel zoekOpleidingsonderdeel(String titel) {
        return opleidingsonderdelen.get(titel);
    }
}
