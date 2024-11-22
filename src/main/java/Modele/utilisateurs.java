package Modele;

import java.util.Date;

public class utilisateurs {
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private Date date_Naiss;
    private String poste;
    private String nomDep;
    private String adresse;
    public utilisateurs(int id,String nom,String prenom,String sexe,Date date_Naiss,String poste,String nomDep,String adresse ){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.sexe=sexe;
        this.poste=poste;
        this.date_Naiss=date_Naiss;
        this.nomDep=nomDep;
        this.adresse=adresse;
    }
    public void setid(int id){this.id=id;}
    public void setnom(String nom){this.nom=nom;
    }
    public void setprenom(String prenom){this.prenom=prenom;}
    public void setsexe(String sexe){this.sexe=sexe;
    }
    public void setdate_naiss(Date date){date_Naiss=date;}
    public void setposte(String contrat){poste=poste;}
    public void setnomDep(String nomDep){ nomDep=nomDep;}
    public void setadresse(String adresse){adresse=adresse;}
    public int getId() {
        return id;
    }
    public String getPoste () { return poste;}
    public String getNom(){return nom;}
    public String getPrenom(){return prenom;}
    public String getSexe(){return sexe;}
    public Date getDate_Naiss(){return date_Naiss;}
    public String getNomDep(){return nomDep;}
    public String getAdresse() {return adresse;}

    public String toString(){return "Nom et Prénom du: "+getNom()+ getPrenom()+"sexe:"+ getSexe()+"poste:"+getPoste()+"nomdep"+getNomDep()+" date de Naissance: "+getDate_Naiss()+"adr:"+getAdresse();}

    public void afficherpersonnel() {
        System.out.println("Le nom : " + this.nom);
        System.out.println("Le prenom :" + this.prenom);
        System.out.println("Le sexe : " + this.sexe);
        //System.out.println("La date de naissance : "+this.date_naiss.getDay()+" /"+this.date_naiss.getMonth()+" /"+this.date_naiss.getYear());
    } }



