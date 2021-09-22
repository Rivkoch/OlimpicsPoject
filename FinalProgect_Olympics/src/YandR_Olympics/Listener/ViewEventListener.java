//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Listener;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import YandR_Olympics.Model.Athlete;
import YandR_Olympics.Model.Judge;
import YandR_Olympics.Model.Stadium;
import YandR_Olympics.Model.Team;
import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;
import YandR_Olympics.Model.Judge.JudgeArea;

public interface ViewEventListener {

	void addedJumperToUI(String name, Countries country, SportType sportType, int numberOfMedals);
	
	void addedRunnerToUI(String name, Countries country, SportType sportType, int numberOfMedals);
	
	void addedRAndJToUI(String name, Countries country, SportType sportType, int numberOfMedals);
	
//	void addAthleteToUI(String name, Countries country, Athlete.SportType sportType, int numberOfMedals);


	ArrayList<Athlete> showedAllAByCountryAndSTInUI(Countries countries, SportType sportType) ;

	void saveAthletesToFileInUI() throws FileNotFoundException;

	void readAthletesFromFileInUI() throws FileNotFoundException;

	void addedTeamTOUI(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes , int numberOfMedals);

	String showedAllAInUI();

	void updatedAthleteSpotTypeInUI(int athleteNumber, SportType sportType);

	void updatedAthleteNumberOfMInUI(int athleteNumber, int numberOfMedals);

	void removedAthleteFromUI(int athleteNumber);

	void savedTeamInUI(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes, int numberOfTeamsMedals);

	String showedAllTeamsInUI();

	void updatedJudgeSpotTypeInUI(int judgeNumber, Judge.JudgeArea  sportType);
	
	void addStadiumInUI(String nameStadium, Stadium.StadiumLocation locationSStadium, long seatsStadium);
	
	void addJudgeInUI(String name, Judge.Countries country, Judge.JudgeArea sportType);
	
	void removedJudgeFromUI(int judgeNumber);
	
	public void updatedStadiumSeatsInUI(int judgeNumber, int numberOfSeats);
	
	void removedStadiumFromUI(int stadiumNumber);

	String showAllJudgesInUI();

	String showedAllSInUI();

	void updatedTeamInUI(int teamNumber, int numberOfTeamsM);

	void removedTeamFromUI(int teamNumber);

	ArrayList<Judge> showedAllJBySTInUI(Judge.JudgeArea sportType);

	ArrayList<Athlete>  showedAllABySTInUI(Athlete.SportType value);

	void createdPersonalCompetitionInUI(int limit, int stadium, JudgeArea sportType, Judge judge, ArrayList<Athlete> participants);

	void saveStadiumsToFileInUI() throws FileNotFoundException;

	void saveJudgesToFileInUI() throws FileNotFoundException;

	void readStadiumsFromFileInUI() throws FileNotFoundException;

	void readJudgesFromFileInUI() throws FileNotFoundException;

	void readTeamsFromFileInUI() throws FileNotFoundException;

	String showAllExistingStadiumsInUI();

	ArrayList<Team>  showedAllTBySTInUI(SportType valueOf);

	void createdTCompetitionInUI(int limit, int stadium,JudgeArea sportType, Judge judge,
			ArrayList<Team> participantedTeams) ;

	String repositoryAllPCInUI();

	ArrayList<Athlete> showAllPCInUI(int numberofPC)  throws Exception;

	void setPersonalCompetitionsResultsInUI(Athlete firstPlace, Athlete secondPlace, Athlete thirdPlace);

	ArrayList<Team> showAllTCInUI(int numberofTC)throws Exception;

	void setTeamCompetitionsResultsInUI(Team firstPlaceT, Team secondPlaceT, Team thirdPlaceT) ;

	String repositoryAllTCInUI();

	void loadAllReposytory();

	String finalWinnersShowedInUI() throws IndexOutOfBoundsException;
	
	





	





}
