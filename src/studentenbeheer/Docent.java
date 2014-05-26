/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author vongenae
 */
public class Docent extends Persoon  implements Serializable {
    private Set<Opleidingsonderdeel> vakken;

    public Docent(String voornaam, String naam) {
        super(voornaam, naam);
        vakken = new HashSet<>();
    }
    
    public void voegVakToe(Opleidingsonderdeel vak) {
        vakken.add(vak);
    }

    @Override
    public String toString() {
        return getVoornaam() + " " + getNaam();
    }
    
    public Iterable<Opleidingsonderdeel> getVakken() {
        return vakken;
    }
    
}
