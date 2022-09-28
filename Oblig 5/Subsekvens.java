public class Subsekvens {
	private String subsekvens;
	private int antall;

	public Subsekvens (String s) {
        subsekvens = s;
        antall = 1;
    }

    //Henter nøkkel
	public String nokkel() {
        return subsekvens;
    }

    //henter antall
	public int antall() {
        return antall;
    }

    //endrer antall
	public void leggTil(int ant) {
        antall = antall + ant;
    }
}
