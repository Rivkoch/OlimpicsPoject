//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import YandR_Olympics.Exceptions.alreadyExist;
import YandR_Olympics.Exceptions.cantAdd;
import YandR_Olympics.Exceptions.duplicateWinner;
import YandR_Olympics.Exceptions.wrongAmountOfParticipants;
import YandR_Olympics.Listener.ModelEventListener;
import YandR_Olympics.Listener.ViewEventListener;
import YandR_Olympics.Model.Athlete;
import YandR_Olympics.Model.Judge;
import YandR_Olympics.Model.OlympicsSystemManagement;
import YandR_Olympics.Model.Stadium;
import YandR_Olympics.Model.Team;
import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;
import YandR_Olympics.Model.Judge.JudgeArea;
import YandR_Olympics.View.OlympicsView;

public class Controller implements ModelEventListener, ViewEventListener {
	private OlympicsSystemManagement systemModel;
	private OlympicsView systemView;
	
	
	public Controller(OlympicsSystemManagement model, OlympicsView view) {
		systemModel = model;
		systemView = view;
		
		systemModel.registerListener(this);
		systemView.registerListener(this); 
	}


	@Override
	public void addedJumperToUI(String name, Countries country, SportType sportType, int numberOfMedals) {
		try {
			systemModel.addAthlete(name, country, sportType, numberOfMedals);
		} catch (cantAdd e) {
			systemView.cantAddMsg(e.getMessage());
		}		
	}



	@Override
	public void addedRunnerToUI(String name, Countries country, SportType sportType, int numberOfMedals) {
		try {
			systemModel.addAthlete(name, country, sportType, numberOfMedals);
		} catch (cantAdd e) {
			systemView.cantAddMsg(e.getMessage());
		}		
	}



	@Override
	public void addedRAndJToUI(String name, Countries country, SportType sportType, int numberOfMedals) {
		try {
			systemModel.addAthlete(name, country, sportType, numberOfMedals);
		} catch (cantAdd e) {
			systemView.cantAddMsg(e.getMessage());
		}		
	}



	@Override
	public void addednewJumperToModelEvent(Athlete newAthlete) {
		systemView.addedJumperMsg(newAthlete.getName());
		
	}



	@Override
	public void addednewRunnerToModelEvent(Athlete newAthlete) {
		systemView.addedRunnerMsg(newAthlete.getName());		
	}



	@Override
	public void addednewJandRToModelEvent(Athlete newAthlete) {
		systemView.addedJandRMsg(newAthlete.getName());			
	}



	@Override
	public void addedNewAthleteToTeamToModel(String name) {
		systemView.newAthleteAddedToTeam(name);
		
	}
	
	@Override
	public void updatedNumOfTeamsInModel(int numberOfTeams) {
		systemView.teamIsAddedMsg();
		
	}

	@Override
	public ArrayList<Athlete> showedAllAByCountryAndSTInUI(Countries teamCounty, SportType sportType) {
		try {
			return systemModel.showAthletsInCountry(teamCounty, sportType);
		} catch (alreadyExist e) {
			systemView.alredyExist(e.getMessage());
		}
		return systemView.blaBla3();
		
		
	}

	@Override
	public void showedAllAthletesInCountryInModelEvent(ArrayList<Athlete> showAByCountry) {
		systemView.successfullyMessage();
		
	}


	@Override
	public void saveAthletesToFileInUI() throws FileNotFoundException{	
		systemModel.saveAthletes("Athletes.txt");	
	}


	@Override
	public void savedFileInModelEvent(File athletesFile) {
		systemView.fileSaved();
	}


	@Override
	public void readAthletesFromFileInUI()  {
		systemView.readFile();
		try {
			systemModel.ReadAthletes("Athletes.txt");
		} catch (FileNotFoundException e) {
			systemView.cantReadF();
		} catch (cantAdd e) {
			systemView.cantAddMsg(e.getMessage());
		}
	}


	@Override
	public void readedFileInModelEvent(String fileName) {
		systemView.endedReading();
	}


	@Override
	public void addedTeamTOUI(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes,
			int numberOfMedals) {
		systemModel.creatingTeam(teamCounty, sportType, allTeamAthletes, numberOfMedals);
		
	}


	@Override
	public String showedAllAInUI() {
		return systemModel.showAllTheAthletes();
	}


	@Override
	public void allAShowedInModelEvent(String allTheAthletes) {
		systemView.loadFromFileMsg();
	}


	@Override
	public void updatedAthleteSpotTypeInUI(int athleteNumber, SportType sportType) {
		systemModel.updateAthleteST(athleteNumber, sportType);
	}

	@Override
	public void updatedAthleteSTToModel() {
		systemView.STUpdated();
	}


	@Override
	public void updatedAthleteNumberOfMInUI(int athleteNumber, int numberOfMedals) {
		systemModel.updateAthleteM(athleteNumber, numberOfMedals);
	}



	@Override
	public void updatedAthleteMToModelEvent() {		
		systemView.athleteNMUpdated();
	}


	@Override
	public void removedAthleteFromUI(int athleteNumber) {
		systemModel.deleteAthlete(athleteNumber);
	}


	@Override
	public void athleteRemovedInModelEven(int athleteNumber) {
		systemView.athleteDeleted(athleteNumber);
	}


	@Override
	public void savedTeamInUI(Countries teamCounty, SportType sportType, ArrayList<Athlete> allTeamAthletes,
			int numberOfTeamsMedals) {
		try {
			systemModel.saveTeams("TeamsFile.txt");
		} catch (FileNotFoundException e) {
			systemView.cantReadF();
		}
		
	}


	@Override
	public void savedTeamsInModelEvant(String fileName) {
		systemView.fileSaved();		
	}


	@Override
	public String showedAllTeamsInUI() {
		return systemModel.showAllTeams();		
	}


	@Override
	public void showAllTeamsInModelEvent(String string) {
		systemView.showAllItems();
	}
	
	
	@Override
	public void addJudgeInUI(String name, Judge.Countries country, Judge.JudgeArea sportType) {
		systemModel.addJudge(name, country, sportType);
	}

	@Override
	public void judgeAddedToModelEvet() {		
		systemView.judgeAdded();
	}
	
	@Override
	public void addStadiumInUI(String nameStadium, Stadium.StadiumLocation locationSStadium, long seatsStadium) {
		systemModel.addStadium(nameStadium, locationSStadium, seatsStadium);
	}
	
	public void addStadiumToModelEvent() {		
		systemView.stadiumAdded();
	}
	
	public void updatedJudgeSpotTypeInUI(int judgeNumber, Judge.JudgeArea  sportType) {
		systemModel.updateJudgeST(judgeNumber, sportType);
	}
	
	public void updatedJudgeSTToModel() {		
		systemView.STUpdated();
	}
	
	@Override
	public void removedJudgeFromUI(int judgeNumber) {
		systemModel.deleteJudge(judgeNumber);
	}


	@Override
	public void judgeRemovedInModelEven(int judgeNumber) {
		systemView.judgeDeleted(judgeNumber);
	}
	
	@Override
	public void updatedStadiumSeatsInUI(int judgeNumber, int numberOfSeats) {
		systemModel.updateStadiumS(judgeNumber, numberOfSeats);
	}


	@Override
	public void updatedStadiumSToModelEvent() {		
		systemView.StadiumNSUpdated();
	}
	
	@Override
	public void removedStadiumFromUI(int stadiumNumber) {
		systemModel.deleteStadium(stadiumNumber);
	}


	@Override
	public void stadiumRemovedInModelEven(int stadiumNumber) {
		systemView.stadiumDeleted(stadiumNumber);
	}


	@Override
	public String showAllJudgesInUI() {
		return systemModel.showAllTheJudges();
	}


	@Override
	public void allJShowedInModelEvent(String allJudges) {
		systemView.showAllItems();
	}


	@Override
	public String showedAllSInUI() {
		return systemModel.showAllTheStadiums();
	}


	@Override
	public void allStudiumsShowedInModelEvent(String allStudiums) {
		systemView.showAllItems();
	}


	@Override
	public void updatedTeamInUI(int teamNumber, int numberOfTeamsM) {
		systemModel.updatedTNumberOfMedals(teamNumber, numberOfTeamsM);
		
	}


	@Override
	public void updatedTeamMToModelEvent() {
		systemView.numberOfTMUpdated();
		
	}

	@Override
	public void removedTeamFromUI(int teamNumber) {
		systemModel.deleteTeam(teamNumber);
	}


	@Override
	public void teamRemovedInModelEven(int teamNumber) {
		systemView.teamDeleted(teamNumber);
	}


	@Override
	public ArrayList<Judge> showedAllJBySTInUI(JudgeArea sportType) {
		return systemModel.showJudgesBySTFor_PC(sportType);
		
	}


	@Override
	public void showedAllJudgesBySTInModel(ArrayList<Judge> showJByST) {
	 systemView.successfullyMessage();
	}


	@Override
	public ArrayList<Athlete> showedAllABySTInUI(SportType sportType) {
		return systemModel.showAthletesBySTFor_PC(sportType);
	}


	@Override
	public void createdPersonalCompetitionInUI(int limit, int stadium, JudgeArea sportType, Judge judge,
			ArrayList<Athlete> participants)  {
		try {
			systemModel.createPersonalCompetition(limit, stadium, sportType, judge, participants);
		} catch (wrongAmountOfParticipants e) {
			systemView.cantCreatePC(e.getMessage());
			
		}
	}


	@Override
	public void showAthletesBySTInModel(ArrayList<Athlete> showAByST_PC) {
		systemView.blaBla();
	}
	
	@Override
	public void saveJudgesToFileInUI() throws FileNotFoundException{	
		systemModel.saveJudges("Judges.txt");	
	}


	@Override
	public void savedJFileInModelEvent(File stadiumsFile) {
		systemView.fileSaved();
	}
	
	@Override
	public void saveStadiumsToFileInUI() throws FileNotFoundException {	
		systemModel.saveStadium("Stadiums.txt");	
	}


	@Override
	public void savedSFileInModelEvent(File stadiumsFile) {
		systemView.fileSaved();
	}
	
	@Override
	public void readStadiumsFromFileInUI() throws FileNotFoundException {
		systemView.readFile();
		systemModel.ReadStadiums("Stadiums.txt");
	}


	@Override
	public void readedFileSInModelEvent(String fileName) {
		systemView.endedReading();
	}
	
	@Override
	public void readJudgesFromFileInUI() throws FileNotFoundException {
		systemView.readFile();
		systemModel.ReadJudges("Judges.txt");
	}


	@Override
	public void readedFileJInModelEvent(String fileName) {
		systemView.endedReading();
	}

	@Override
	public void personalCCreatedInModelEvent() {
		systemView.competitionCreated();
	}


	@Override
	public void teamsReadedFromFileInModelEvent(String fileName) {
		systemView.endedReading();
		
	}


	@Override
	public void readTeamsFromFileInUI() throws FileNotFoundException {
		systemView.readFile();
		systemModel.ReadTeam("TeamsFile.txt");
		
	}


	@Override
	public String showAllExistingStadiumsInUI() {
		return systemModel.showAllTheStadiums();
	}


	@Override
	public ArrayList<Team> showedAllTBySTInUI(SportType sportType) {
		return systemModel.allTeamsShowedInModelEvent(sportType);
	}


	@Override
	public void showTeamsBySTInModel(ArrayList<Team> showAByST_PC) {
		systemView.blaBla();
	}


	@Override
	public void createdTCompetitionInUI(int limit, int stadium, JudgeArea sportType, Judge judge,
			ArrayList<Team> participantedTeams)  {
		try {
			systemModel.createdTeamCInModelEvent(limit, stadium, judge, sportType, participantedTeams);
		} catch (wrongAmountOfParticipants e) {
			systemView.cantcreateTC(e.getMessage());
		}
		
	}


	@Override
	public void teamCCreatedInModelEvent() {
		systemView.competitionCreated();
	}


	@Override
	public String repositoryAllPCInUI() {
		return systemModel.repositoryAllPCInModelEvent();
	}


	@Override
	public ArrayList<Athlete> showAllPCInUI(int numberofPC) throws Exception {
		return systemModel.showAllAthletesInPC(numberofPC);
	}


	@Override
	public void setPersonalCompetitionsResultsInUI(Athlete firstPlace, Athlete secondPlace, Athlete thirdPlace)  {
		try {
			systemModel.setPersonalCompetitionsResult(firstPlace, secondPlace, thirdPlace);
		} catch (duplicateWinner e) {
			systemView.cantWinPC(e.getMessage());
		}
		
	}


	@Override
	public String repositoryAllPCInModelEvent(String sbAllPC) {
		return systemView.blaBla2();
	}

	@Override
	public String repositoryAllTCInModelEvent(String sbAllTC) {
		return systemView.blaBla2();
		}

	@Override
	public void setPersonalCompetitionsResultsInModelEvent() {
		systemView.resultsSaved();
	}

	@Override
	public void setTeamCompetitionsResultsInModelEvent() {
		systemView.resultsSaved();

	}

	@Override
	public ArrayList<Athlete> showAllAthletesInPCInModelEvent(ArrayList<Athlete> allAPC_Participants) {
		return systemView.blaBla3();
		
	}

	@Override
	public ArrayList<Team> showAllTeamsInTCInModelEvent(ArrayList<Team> allTTC_Participants) {
		return systemView.blaBla4()	;	
		
	}


/*	@Override
	public ArrayList<Integer> numbersOfExisting_PCInModelEvent(ArrayList<Integer> pCNum) {
		return systemView.blaBla5();
	}
*/

	@Override
	public ArrayList<Team> showAllTCInUI(int numberofTC) throws Exception {
		return systemModel.showAllTeamsInTC(numberofTC);
	}


	@Override
	public void setTeamCompetitionsResultsInUI(Team firstPlaceT, Team secondPlaceT, Team thirdPlaceT) {
		try {
			systemModel.setTeamCompetitionsResultsInModelEvent(firstPlaceT, secondPlaceT, thirdPlaceT);
		} catch (duplicateWinner e) {
			systemView.cantWinTC(e.getMessage());
		}
		
	}


	@Override
	public String repositoryAllTCInUI() {
		return systemModel.repositoryAllTCInModelEvent();
	}


	@Override
	public void loadAllReposytory() {
		systemView.loadFromFileMsg();
	}


	@Override
	public String finalWinnersShowedInUI() {

			return systemModel.finalWinners();
		
	}


	@Override
	public void winnersShowedInModelEvent(String string) {
		systemView.blaBla();
	}


	
}
