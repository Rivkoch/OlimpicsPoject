//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Listener;

import java.io.File;
import java.util.ArrayList;

import YandR_Olympics.Model.Athlete;
import YandR_Olympics.Model.Judge;
import YandR_Olympics.Model.Team;

public interface ModelEventListener {
	
	void addednewJumperToModelEvent(Athlete newAthlete);
	
	void addednewRunnerToModelEvent(Athlete newAthlete);
	
	void addednewJandRToModelEvent(Athlete newAthlete);
	
	void showedAllAthletesInCountryInModelEvent(ArrayList<Athlete> showAByCountry);

	void updatedNumOfTeamsInModel(int numberOfTeams);

	void addedNewAthleteToTeamToModel(String name);

	void savedFileInModelEvent(File athletesFile);

	void readedFileInModelEvent(String fileName);

	void allAShowedInModelEvent(String allTheAthletes);

	void updatedAthleteSTToModel();

	void updatedAthleteMToModelEvent();

	void athleteRemovedInModelEven(int athleteNumber);

	void savedTeamsInModelEvant(String fileName);

	void showAllTeamsInModelEvent(String string);
	
	void updatedJudgeSTToModel();
	
	void addStadiumToModelEvent();
	
	void judgeAddedToModelEvet();

	void judgeRemovedInModelEven(int judgeNumber);
	
	void updatedStadiumSToModelEvent() ;	
	
	void stadiumRemovedInModelEven(int stadiumNumber);

	void allJShowedInModelEvent(String allJudges);

	void allStudiumsShowedInModelEvent(String allStudiums);

	void updatedTeamMToModelEvent();

	void teamRemovedInModelEven(int teamNumber);

	void showedAllJudgesBySTInModel(ArrayList<Judge> showJByST);

	void showAthletesBySTInModel(ArrayList<Athlete> showAByST_PC);

	void personalCCreatedInModelEvent();

	void savedJFileInModelEvent(File fileName);

	void savedSFileInModelEvent(File fileName);

	void readedFileJInModelEvent(String fileName);

	void readedFileSInModelEvent(String fileName);

	void teamsReadedFromFileInModelEvent(String fileName);

	void showTeamsBySTInModel(ArrayList<Team> showAByST_PC);

	void teamCCreatedInModelEvent();

	String repositoryAllPCInModelEvent(String sbAllPC);
	
	String repositoryAllTCInModelEvent(String sbAllTC);

	void setPersonalCompetitionsResultsInModelEvent();

	void setTeamCompetitionsResultsInModelEvent();

	ArrayList<Athlete> showAllAthletesInPCInModelEvent(ArrayList<Athlete> allAPC_Participants);

	ArrayList<Team> showAllTeamsInTCInModelEvent(ArrayList<Team> allTTC_Participants);

	void winnersShowedInModelEvent(String string);




	

}
