package dto;

import java.util.List;

import domein.Edele;
import enums.EdelsteenType;
import utils.AantallenPerKleur;

public record EdeleDTO(AantallenPerKleur aantalBonusPrijs, int prestigePunten) {
	
	public static List<EdeleDTO> EdelenToDTOS(List<Edele> edelen) {
		return edelen.stream().map(x -> new EdeleDTO(x.getAantalBonusPrijs(), Edele.getPrestigePunten())).toList();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Prestigepunten: ").append(prestigePunten).append(System.lineSeparator());
		sb.append("Aantal kost van bonussen: ").append(System.lineSeparator());
		for (EdelsteenType edelsteenType : EdelsteenType.values()) {
			if (aantalBonusPrijs.getAantal(edelsteenType) != 0) {
				sb.append(edelsteenType).append(": ").append(aantalBonusPrijs.getAantal(edelsteenType))
						.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
}
