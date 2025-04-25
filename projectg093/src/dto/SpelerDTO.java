package dto;

import domein.Speler;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

import java.util.List;

public record SpelerDTO(String gebruikersnaam, int prestigePunten, AantallenPerKleur aantalFiches,
						List<OntwikkelingskaartDTO> ontwikkelingskaartDTOS, List<EdeleDTO> edeleDTOS, String plaats, boolean isAanBeurt,
						boolean isStartSpeler, int geboortejaar) {
	public static List<SpelerDTO> spelersToDTOS(List<Speler> spelers, Speler spelerAanBeurt, Speler startSpeler) {
		return spelers.stream()
				.map(x -> new SpelerDTO(x.getGebruikersnaam(), x.bepaalPrestigePunten(), x.getEdelsteenfiches(),
						OntwikkelingskaartDTO.OntwikkelingskaartenToDTOS(x.getOntwikkelingskaarten()),
						EdeleDTO.EdelenToDTOS(x.getEdelen()), switch (spelers.indexOf(x)) {
					case 0 -> "Rechts";
					case 1 -> "Onder";
					case 2 -> "Links";
					default -> "Boven";
				}, x == spelerAanBeurt, x == startSpeler, x.getGeboortejaar()))
				.toList();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gebruikersnaam: ").append(gebruikersnaam).append(System.lineSeparator());
		sb.append("Prestigepunten: ").append(prestigePunten).append(System.lineSeparator());
		sb.append("Aantal edelstenen: ").append(System.lineSeparator());
		for (EdelsteenType edelsteenType : EdelsteenType.values()) {
			if (aantalFiches.getAantal(edelsteenType) != 0) {
				sb.append(edelsteenType).append(": ").append(aantalFiches.getAantal(edelsteenType))
						.append(System.lineSeparator());
			}
		}
		sb.append("Ontwikkelingskaarten: ").append(System.lineSeparator());
		for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaartDTOS) {
			sb.append(ontwikkelingskaartDTO).append(System.lineSeparator());
		}
		sb.append("Edelen: ").append(System.lineSeparator());
		for (EdeleDTO edeleDTO : edeleDTOS) {
			sb.append(edeleDTO).append(System.lineSeparator());
		}
		sb.append("Plaats: ").append(plaats).append(System.lineSeparator());
		if (isAanBeurt) {
			sb.append("Is aan beurt").append(System.lineSeparator());
		}
		if (isStartSpeler) {
			sb.append("Is startspeler").append(System.lineSeparator());
		}
		return sb.toString();
	}


	public AantallenPerKleur getBonussen() {
		AantallenPerKleur bonussen = new AantallenPerKleur();
		for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaartDTOS.stream().filter(x -> x.bonus() != null).toList()) {
			bonussen.verhoogAantal(ontwikkelingskaartDTO.bonus(), 1);
		}
		return bonussen;
	}
}
