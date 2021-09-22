//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

import java.util.ArrayList;

import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;

public abstract class Team {

	private ArrayList<Athlete> allTeamAthletes = new ArrayList<Athlete>();
	private Countries teamCounty;
	private SportType sportType;
	private int numberOfTeamsMedals = 0;
	private static int teamNumberCounter = 1;
	private int teamNumber;

	public Team(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes, int numberOfTeamsMedals) {
		this.allTeamAthletes = allTeamAthletes;
		this.teamCounty = teamCounty;
		this.numberOfTeamsMedals = numberOfTeamsMedals;
		this.sportType = sportType;
		this.teamNumber = teamNumberCounter++;
	}

	public ArrayList<Athlete> getAllTeamAthletes() {
		return allTeamAthletes;
	}

	public void setAllTeamAthletes(ArrayList<Athlete> allTeamAthletes) {
		this.allTeamAthletes = allTeamAthletes;
	}

	public Countries getTeamCounty() {
		return teamCounty;
	}

	public void setTeamCounty(Countries teamCounty) {
		this.teamCounty = teamCounty;
	}

	public int getNumberOfTeamsMedals() {
		return numberOfTeamsMedals;
	}

	public void setNumberOfTeamsMedals(int numberOfTeamsMedals) {
		this.numberOfTeamsMedals = numberOfTeamsMedals;
	}

	public SportType getSportType() {
		return sportType;
	}

	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}


	public int getTeamNumber() {
		return teamNumber + 1;
	}

	public void setTeamNumber() {
		this.teamNumber = teamNumberCounter++;
	}


	//Add Points To Winners
	public void setFirslPlace(Team team) {
		int firstPlace = team.getNumberOfTeamsMedals()+25;
	}

	public void setSecondlPlace(Team team) {
		int secondPlace = team.getNumberOfTeamsMedals() +10;
	}
	
	public void setThirddlPlace(Team team) {
		int thirddPlace = team.getNumberOfTeamsMedals() +5;
	}
	
	// Number of athletes in team
	public int getNumOfAthletesInTeam() {

		return this.allTeamAthletes.size();
	}

	// Add Athlete By Object
		public void addAthleteToTheTeamByObject(Athlete newA) {

			allTeamAthletes.add(newA);

		}

	@Override
	public String toString() {
		String teamST= "change";
		if(sportType.equals(sportType.Jumper)) {
			teamST = "Jumpers";
		}
		
		if(sportType.equals(sportType.Runner)) {
			teamST = "Runners";
		}
		
		if(sportType.equals(sportType.RunnerAndJumper)) {
			teamST = "Runners and Jumpers ";
		}
		
		return  teamST + " from " + teamCounty + " has " + allTeamAthletes.size() + " athletes and " + numberOfTeamsMedals + " medals.";
	}
	

	
}
