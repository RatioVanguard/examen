/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenbeheer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author vongenae
 */
public class Persoon  implements Serializable {
    private String voornaam, naam, adres, postcode, gemeente;
    private Date geboortedatum ;

    public Persoon(String voornaam, String naam, String adres, String postcode, String gemeente, Date geboortedatum) {
        this.voornaam = voornaam;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.geboortedatum = geboortedatum;
    }

    public Persoon(String voornaam, String naam) {
        this.voornaam = voornaam;
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }
    
    @Override
    public String toString() {
        return "Persoon{" + "voornaam=" + voornaam + ", naam=" + naam + ", adres=" + adres + ", postcode=" + postcode + ", gemeente=" + gemeente + ", geboortedatum=" + geboortedatum + '}';
    }
    


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persoon other = (Persoon) obj;
        if (!Objects.equals(this.voornaam, other.voornaam)) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }
}
