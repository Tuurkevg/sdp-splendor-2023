package dto;

import domein.GedekteStapel;
import java.util.List;

public record GedekteStapelDTO(int niveau, List<OntwikkelingskaartDTO> ontwikkelingskaartDTOS) {
	public static GedekteStapelDTO[] gedekteStapelsToDTOS(GedekteStapel[] gedekteStapels) {
		GedekteStapelDTO[] uitvoer = new GedekteStapelDTO[gedekteStapels.length];
		for (int i = 0; i < gedekteStapels.length; i++) {
			uitvoer[i] = new GedekteStapelDTO(gedekteStapels[i].getNiveau(),
					OntwikkelingskaartDTO.OntwikkelingskaartenToDTOS(gedekteStapels[i].getGedekteKaarten()));
		}
		return uitvoer;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gedekte stapel: ").append(System.lineSeparator());
		sb.append("Niveau: ").append(niveau).append(System.lineSeparator());
		sb.append("Ontwikkelingskaarten: ").append(System.lineSeparator());
		for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaartDTOS) {
			sb.append(ontwikkelingskaartDTO).append(System.lineSeparator());
		}
		return sb.toString();
	}
}
