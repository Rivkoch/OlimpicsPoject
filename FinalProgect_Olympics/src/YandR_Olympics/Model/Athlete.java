//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

public class Athlete {
	public enum Countries {Israel, China, Japan, America, Argentina, Italy, Russia};
	public enum SportType {Runner, Jumper, RunnerAndJumper};

	private String name;
	private int numberOfMedals;
	private Countries country;
	private SportType sportType;
	private static int athletNumberCounter = 1;
	private int athletNumber;
	

	public Athlete(String name, Countries country, SportType sportType, int numberOfMedals) {
		this.name = name;
		this.numberOfMedals = numberOfMedals;
		this.country = country;
		this.sportType = sportType;
		this.athletNumber = athletNumberCounter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfMedals() {
		return numberOfMedals;
	}

	public void setNumberOfMedals(int numberOfMedals) {
		this.numberOfMedals = numberOfMedals;
	}

	public Countries getCountry() {
		return country;
	}

	public SportType getSportType() {
		return sportType;
	}

	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}

	public int getAthletNumber() {
		return athletNumber + 1;
	}
	
	public void setAthletNumber() {
		this.athletNumber = athletNumberCounter++;
	}
	
	//Add Points To Winners
	public void setFirslPlace(Athlete athlete) {
		int firstPlace = athlete.getNumberOfMedals() +25;
	}

	public void setSecondlPlace(Athlete athlete) {
		int secondPlace = athlete.getNumberOfMedals() +10;
	}
	
	public void setThirddlPlace(Athlete athlete) {
		int thirddPlace = athlete.getNumberOfMedals() +5;
	}
	
	
	
	
	@Override
	public String toString() {
		return  name + " from " + country + ", is " + sportType + ", have " + numberOfMedals + " medals.";
	}

	
}
