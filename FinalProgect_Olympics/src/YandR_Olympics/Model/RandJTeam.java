//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

import java.util.ArrayList;

import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;

public class RandJTeam extends Team{

	public RandJTeam(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes,
			int numberOfTeamsMedals) {
		super(teamCounty, sportType, allTeamAthletes, numberOfTeamsMedals);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
