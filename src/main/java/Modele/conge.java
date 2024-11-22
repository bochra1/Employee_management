package Modele;

import java.util.Date;

public class conge  {
    private int idconge ;
    private  int idemploye ;
    private String type;
    private Date dateDebut ;
    private Date dateFin;
    public conge(int idconge , int idemploye, String type, Date dateDebut, Date dateFin)  {
        this.idconge=idconge;
        this.idemploye=idemploye;
        this.type=type;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;}
    public void setIdconge(int idconge){idconge=idconge;
    }
    public void setIdemploye(int idemploye){idemploye=idemploye;}
    public void setType(String type){type=type;}
    public void setDateDebut(Date dateDebut){dateDebut=dateDebut;}
    public void setDateFin(Date dateFin){dateFin=dateFin;}
    public int getIdconge(){return idconge;}
    public int getIdemploye(){return idemploye;}
    public String getType(){return type;}
    public Date getDateDebut(){return dateDebut;}
    public Date getDateFin(){return dateFin;}
    public String toString(){return "id conges"+getIdconge()+"id employe"+getIdemploye()+"tyoe conges"+getType()+"date de debut conges"+getDateDebut()+"date fin"+getDateFin();}
}