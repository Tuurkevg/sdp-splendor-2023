package dto;

import domein.Ontwikkelingskaart;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

import java.util.List;

public record OntwikkelingskaartDTO(int niveau, EdelsteenType bonus, int prestigePunten,
		AantallenPerKleur aantalFiches) {
	public static List<OntwikkelingskaartDTO> OntwikkelingskaartenToDTOS(
			List<Ontwikkelingskaart> ontwikkelingskaarten) {
		return ontwikkelingskaarten.stream().map(
				x -> new OntwikkelingskaartDTO(x.getNiveau(), x.getBonus(), x.getPrestigePunten(), x.getAantalFiches()))
				.toList();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Niveau: ").append(niveau).append(System.lineSeparator());
		sb.append("Bonus: ").append(bonus).append(System.lineSeparator());
		sb.append("Prestigepunten: ").append(prestigePunten).append(System.lineSeparator());
		sb.append("Aantal fiches: ").append(System.lineSeparator());
		for (EdelsteenType edelsteenType : EdelsteenType.values()) {
			if (aantalFiches.getAantal(edelsteenType) != 0) {
				sb.append(edelsteenType).append(": ").append(aantalFiches.getAantal(edelsteenType))
						.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
}
