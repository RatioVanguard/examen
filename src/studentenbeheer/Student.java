/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author vongenae
 */
public class Student extends Persoon  implements Serializable {
    private Set<Opleidingsonderdeel> curriculum;

    public Student(String voornaam, String naam, String adres, String postcode, String gemeente, Date geboortedatum) {
        super(voornaam, naam, adres, postcode, gemeente, geboortedatum);
        curriculum = new HashSet<>() ;
    }
    
    public int getAantalStudiepunten() {
        int aantalStudiepunten = 0;
        for (Opleidingsonderdeel vak : curriculum) {
            aantalStudiepunten += vak.getAantalStudiepunten();
        }
        return aantalStudiepunten;
    }

    Iterable<Opleidingsonderdeel> getCurriculum() {
        return curriculum;
    }
    
    public void neemOpleidingsonderdeelOp(Opleidingsonderdeel vak) {
        curriculum.add(vak);
    }
}
