package composants;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Options implements Serializable{

	private boolean sound;
	private boolean gnar;
	private boolean horloge;
	private boolean sommaire;
	private boolean bulle;	
	
	/**
	 * @param sound
	 * @param gnar
	 * @param horloge
	 * @param sommaire
	 * @param bulle
	 */
	public Options(boolean sound, boolean gnar, boolean horloge,
			boolean sommaire, boolean bulle) {
		this.sound = sound;
		this.gnar = gnar;
		this.horloge = horloge;
		this.sommaire = sommaire;
		this.bulle = bulle;
	}
	public boolean getHorloge() {
		return horloge;
	}
	public void setHorloge(boolean horloge) {
		this.horloge = horloge;
	}
	public boolean getGnar() {
		return gnar;
	}
	public void setGnar(boolean gnar) {
		this.gnar = gnar;
	}
	public boolean getSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	public boolean getBulle() {
		return bulle;
	}
	public void setBulle(boolean bulle) {
		this.bulle = bulle;
	}
	public boolean getSommaire() {
		return sommaire;
	}
	public void setSommaire(boolean sommaire) {
		this.sommaire = sommaire;
	}
	
	
	
}

