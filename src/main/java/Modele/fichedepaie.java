package Modele;

import java.util.Date;

public class fichedepaie {
    private int  matricule;
    private String nomprenom;
    private Date datedeb;
    private Date datef;
    private Date dateembauche;
    private int NoSecurite;
    private String contrat;
    private String echelon;
    private String poste;
    private double h_menseuel;
    private String situation ;
    private int nbenfants;
    private double salairebrute;
    private double netàpayer;
    private String assurance;
    private double cotisation ;
    private double netfiscale;


    public fichedepaie(int matricule , String nomprenom, Date datedeb ,Date datef ,Date dateembauche ,int Nosecurite , String  contrat, String echelon ,String poste ,Double h_menseuel ,String situation , int nbenfants ,Double  salairebrute, Double netàpayer, String assurance , double cotisation,double netfiscale) {
        this.matricule=matricule;
        this.nomprenom=nomprenom;
        this.datedeb=datedeb;
        this.datef=datef;
        this.dateembauche=dateembauche;
        this.NoSecurite=Nosecurite;
        this.contrat=contrat;
        this.echelon=echelon;
        this.poste=poste;
        this.h_menseuel=h_menseuel;
        this.situation=situation;
        this.nbenfants=nbenfants;
        this.salairebrute=salairebrute;
        this.netàpayer=netàpayer;
        this.assurance=assurance;
        this.cotisation=cotisation;
        this.netfiscale=netfiscale;

    }
    public void setMatricule(String matricule){ matricule=matricule;}
    public void setNomprenom(String nomprenom){nomprenom=nomprenom;}
    public void setDatedeb(Date datedeb){datedeb=datedeb;}
    public void setDatef(Date datef){datef=datef;}
    public void setDateembauche(Date dateembauche){dateembauche=dateembauche;}
    public void setNoSecurite(int noSecurite) {
        NoSecurite = noSecurite;
    }
    public void setContrat(String contrat ){contrat=contrat;}
    public void setEchelon(String echelon){echelon=echelon;}
    public void setPoste(String poste){poste=poste;}
    public void setH_menseuel(double h_menseuel){h_menseuel=h_menseuel;}
    public void setSituation(String situation){situation=situation;}
    public void setNbenfants(int nbenfants){nbenfants=nbenfants;}



    public int getNoSecurite() {
        return NoSecurite;
    }

    public void setCotisation(double cotisation) {
        this.cotisation = cotisation;
    }

    public void setNetfiscale(double netfiscale) {
        this.netfiscale = netfiscale;
    }

    public void setSalairebrute(double salairebrute) {
        this.salairebrute = salairebrute;
    }

    public void setNetàpayer(double netàpayer) {
        this.netàpayer = netàpayer;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public int getMatricule() {
        return matricule;
    }

    public int getNbenfants() {
        return nbenfants;
    }

    public double getCotisation() {
        return cotisation;
    }

    public double getH_menseuel() {
        return h_menseuel;
    }

    public double getNetfiscale() {
        return netfiscale;
    }

    public double getNetàpayer() {
        return netàpayer;
    }

    public double getSalairebrute() {
        return salairebrute;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public Date getDateembauche() {
        return dateembauche;
    }

    public Date getDatef() {
        return datef;
    }

    public String getAssurance() {
        return assurance;
    }

    public String getContrat() {
        return contrat;
    }

    public String getEchelon() {
        return echelon;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public String getPoste() {
        return poste;
    }

    public String getSituation() {
        return situation;
    }

    @Override
    public String toString() {
        return "fichedepaie{" +
                "matricule=" + matricule +
                ", nomprenom='" + nomprenom + '\'' +
                ", datedeb=" + datedeb +
                ", datef=" + datef +
                ", dateembauche=" + dateembauche +
                ", NoSecurite=" + NoSecurite +
                ", contrat='" + contrat + '\'' +
                ", echelon='" + echelon + '\'' +
                ", poste='" + poste + '\'' +
                ", h_menseuel=" + h_menseuel +
                ", situation='" + situation + '\'' +
                ", nbenfants=" + nbenfants +
                ", salairebrute=" + salairebrute +
                ", netàpayer=" + netàpayer +
                ", assurance='" + assurance + '\'' +
                ", cotisation=" + cotisation +
                ", netfiscale=" + netfiscale +
                '}';
    }
}
