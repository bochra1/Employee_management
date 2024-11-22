package Modele;

import Modele.utilisateurs;

import java.util.Date;
import java.lang.String;
public class employes extends utilisateurs {

    //private String nomDep;
    //private String adresse;
    public employes(int id, String nom, String prenom, String sexe, Date date_Naiss,String poste,String nomDep , String adresse) {
        super(id,nom,prenom,sexe,date_Naiss,poste,nomDep,adresse);
        //this.adresse=adresse;
    }

    //public void setadresse(String adresse){this.adresse=adresse;}
    //public String getadresse() {return adresse;}
    //public String getnomDep(){return nomDep;}
    //public String toString() {return "iD :"+getId()+"adresse" + getadresse();
   // }

}
