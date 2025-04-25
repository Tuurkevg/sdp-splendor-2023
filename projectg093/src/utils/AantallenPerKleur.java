package utils;

import enums.EdelsteenType;
import exceptions.IllegalArguments;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AantallenPerKleur extends ArrayList<Pair<EdelsteenType, Integer>> {

	public int getAantal(EdelsteenType kleur) {
		for (Pair<EdelsteenType, Integer> pair : this) {
			if (pair.getKey() == kleur) {
				return pair.getValue();
			}
		}
		return 0;
	}

	public void setAantal(EdelsteenType kleur, int aantal) {
		if (aantal == 0)
			return;
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getKey() == kleur) {
				this.set(i, new Pair<>(kleur, aantal));
				return;
			}
		}
		this.add(new Pair<>(kleur, aantal));
	}

	public void verlaagAantal(EdelsteenType kleur, int aantal) {
		if (aantal < 0)
			throw new IllegalArguments("Aantal kan niet negatief zijn");
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getKey() == kleur) {
				if (this.get(i).getValue() - aantal < 0)
					throw new IllegalArgumentException("Aantal kan niet negatief worden");
				if (this.get(i).getValue() - aantal == 0) {
					this.remove(i);
				} else {
					this.set(i, new Pair<>(kleur, this.get(i).getValue() - aantal));
				}
				return;
			}
		}
		throw new IllegalArguments("Kleur niet gevonden");
	}

	public void verhoogAantal(EdelsteenType kleur, int aantal) {
		if (aantal < 0)
			throw new IllegalArguments("Aantal kan niet negatief zijn");
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getKey() == kleur) {
				this.set(i, new Pair<>(kleur, this.get(i).getValue() + aantal));
				return;
			}
		}
		this.add(new Pair<>(kleur, aantal));
	}

	public boolean kanBetalen(AantallenPerKleur kosten) {
		for (Pair<EdelsteenType, Integer> pair : kosten) {
			if (pair.getValue() == null)
				throw new NullPointerException("Aantal moet geldig zijn.");
			if (this.getAantal(pair.getKey()) < pair.getValue()) {
				return false;
			}
		}
		return true;
	}

	public static AantallenPerKleur bonussenToAantallenPerKleur(List<EdelsteenType> edelsteenTypes) {
		AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
		for (EdelsteenType edelsteenType : edelsteenTypes) {
			aantallenPerKleur.verhoogAantal(edelsteenType, 1);
		}
		return aantallenPerKleur;
	}

	public static AantallenPerKleur voegAantallenPerKleurSamen(AantallenPerKleur aantallenPerKleur1,
			AantallenPerKleur aantallenPerKleur2) {
		AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur1) {
			aantallenPerKleur.verhoogAantal(pair.getKey(), pair.getValue());
		}
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur2) {
			aantallenPerKleur.verhoogAantal(pair.getKey(), pair.getValue());
		}
		return aantallenPerKleur;
	}

	public void voegAantallenPerKleurSamen(AantallenPerKleur aantallenPerKleur) {
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur) {
			this.verhoogAantal(pair.getKey(), pair.getValue());
		}
	}

	public static AantallenPerKleur trekAantallenPerKleurAf(AantallenPerKleur aantallenPerKleur1,
			AantallenPerKleur aantallenPerKleur2) {
		AantallenPerKleur aantallenPerKleur = (AantallenPerKleur) aantallenPerKleur1.clone();
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur2) {
			aantallenPerKleur.verlaagAantal(pair.getKey(), pair.getValue());
		}
		return aantallenPerKleur;
	}

	public void trekAantallenPerKleurAf(AantallenPerKleur aantallenPerKleur) {
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur) {
			this.verlaagAantal(pair.getKey(), pair.getValue());
		}
	}

	public static AantallenPerKleur trekAantallenPerKleurAfSafe(AantallenPerKleur aantallenPerKleur1,
			AantallenPerKleur aantallenPerKleur2) {
		AantallenPerKleur aantallenPerKleur = (AantallenPerKleur) aantallenPerKleur1.clone();
		for (Pair<EdelsteenType, Integer> pair : aantallenPerKleur2) {
			aantallenPerKleur.trekAfIfExists(pair.getKey(), pair.getValue());
		}
		return aantallenPerKleur;
	}

	private void trekAfIfExists(EdelsteenType edelsteenType, int aantal) {
		if (this.getAantal(edelsteenType) == 0) {
			return;
		}
		this.verlaagAantal(edelsteenType, Math.min(this.getAantal(edelsteenType), aantal));
	}

	public int geefSomAlleEdelsteenfiches() {
		int som = 0;
		for (Pair<EdelsteenType, Integer> pair : this) {
			som += pair.getValue();
		}
		return som;
	}

}
