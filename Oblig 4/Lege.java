

public class Lege implements Comparable<Lege> { 
    protected String navn;
    protected Lenkeliste<Resept> utskrivendeResepter = new Lenkeliste<Resept>();

    public Lege(String n){
        navn = n;
       

    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        return "\nnavn paa lege: " + navn;
    }

    @Override
    public int compareTo(Lege annen){
        return this.navn.compareTo(annen.navn); //om this er stoerre enn annen, faar vi 1
        //om this er mindre enn annen, faar -1
    }

    public Lenkeliste<Resept> hentUt(){
        return utskrivendeResepter;
    }

    public boolean erSpes(){//sjekker om legen er spesialist
        return this instanceof Spesialist;

    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
    UlovligUtskrift{
        

        if(legemiddel instanceof Narkotisk && !erSpes()){
            throw new UlovligUtskrift(this, legemiddel);
        }

        HvitResept resept = new HvitResept(legemiddel,this, pasient, reit);
        

        utskrivendeResepter.leggTil(resept);
        return resept;
    }

    public MResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int
    reit) throws UlovligUtskrift{
    

        if(legemiddel instanceof Narkotisk && !erSpes()){
            throw new UlovligUtskrift(this, legemiddel);
        }

        MResept resept = new MResept(legemiddel, this, pasient, reit);
        utskrivendeResepter.leggTil(resept);

        return resept;

    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{

        if(legemiddel instanceof Narkotisk && !erSpes()){
            throw new UlovligUtskrift(this, legemiddel);
        }

        PResept resept = new PResept(legemiddel, this, pasient);
        utskrivendeResepter.leggTil(resept);

        return resept;

    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws
    UlovligUtskrift{

        if(legemiddel instanceof Narkotisk && !erSpes()){
            throw new UlovligUtskrift(this, legemiddel);
        }

        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrivendeResepter.leggTil(resept);

        return resept;

    }
}
