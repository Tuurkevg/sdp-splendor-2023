package dto;

import enums.EdelsteenType;
import javafx.util.Pair;

import java.util.List;

public record OverzichtDTO(List<EdeleDTO> edeleDTOS, List<OntwikkelingskaartDTO> ontwikkelingskaartDTOS,
		GedekteStapelDTO[] gedekteStapels, List<Pair<EdelsteenType, Integer>> aantalFiches,
		List<SpelerDTO> spelerDTOS) {
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator()).append("Edelen: ").append(System.lineSeparator());
		for (EdeleDTO edeleDTO : edeleDTOS) {
			sb.append(edeleDTO).append(System.lineSeparator());
		}
		sb.append(System.lineSeparator()).append("Ontwikkelingskaarten: ").append(System.lineSeparator());
		for (OntwikkelingskaartDTO ontwikkelingskaartDTO : ontwikkelingskaartDTOS) {
			sb.append(ontwikkelingskaartDTO).append(System.lineSeparator());
		}
		sb.append(System.lineSeparator()).append("Edelstenen: ").append(System.lineSeparator());
		for (Pair<EdelsteenType, Integer> edelsteen : aantalFiches) {
			sb.append(edelsteen.getKey()).append(": ").append(edelsteen.getValue()).append(System.lineSeparator());
		}
		sb.append(System.lineSeparator()).append("Spelers: ").append(System.lineSeparator());
		for (SpelerDTO spelerDTO : spelerDTOS) {
			sb.append(spelerDTO).append(System.lineSeparator());
		}
		return sb.toString();
	}
}
