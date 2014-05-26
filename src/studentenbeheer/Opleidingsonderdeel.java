/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author vongenae
 */
public class Opleidingsonderdeel  implements Serializable {
    private String naam;  
    private Docent titularis;
    private int studiejaar, aantalStudiepunten; 
    private Set<Student> studenten; //aantalingeschrevenstudenten

    public Opleidingsonderdeel(String naam, Docent titularis, int studiejaar, int aantalstudiepunten) {
        this.naam = naam;
        this.titularis = titularis;
        this.studiejaar = studiejaar;
        this.aantalStudiepunten = aantalstudiepunten;
        this.studenten = new HashSet<>();
    }   

    public int getAantalStudiepunten() {
        return aantalStudiepunten;
    }

    public String getTitel() {
        return naam;
    }
    
    public void schrijfStudentIn(Student student) {
        studenten.add(student);
    }
    
    public int getAantalStudenten() {
        return studenten.size();
    }
    
    @Override
    public String toString() {
        return "Opleidingsonderdeel{" + "naam=" + naam + ", titularis=" + titularis + ", studiejaar=" + studiejaar + ", aantalStudiepunten=" + aantalStudiepunten + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.naam);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opleidingsonderdeel other = (Opleidingsonderdeel) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }

    
}
