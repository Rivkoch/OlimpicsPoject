//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import YandR_Olympics.Exceptions.alreadyExist;
import YandR_Olympics.Exceptions.cantAdd;
import YandR_Olympics.Exceptions.duplicateWinner;
import YandR_Olympics.Exceptions.wrongAmountOfParticipants;
import YandR_Olympics.Listener.ModelEventListener;
import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;
import YandR_Olympics.Model.Judge.JudgeArea;

public class OlympicsSystemManagement {
	private ArrayList<ModelEventListener> listeners;
	private ArrayList<Athlete> allAthletes;
	private ArrayList<Athlete> allRunners;
	private ArrayList<Athlete> allJumpers;
	private ArrayList<Athlete> allRunnersAndJumpers;
	private ArrayList<Team> allTeams;
	private ArrayList<Judge> allJudges;
	private ArrayList<Stadium> allStudiums;
	private ArrayList<PersonalCompetition> allPC;
	private ArrayList<TeamCompetition> allTC;
	private ArrayList<Athlete> allFirstPlaceAthletes;
	private ArrayList<Athlete> allSecondPlaceAthletes;
	private ArrayList<Athlete> allThirdPlaceAthletes;
	private ArrayList<Team> allFirstPlaceTeams;
	private ArrayList<Team> allSecondPlaceTeams;
	private ArrayList<Team> allThirdPlaceTeams;	
	


	public OlympicsSystemManagement() {
		listeners = new ArrayList<ModelEventListener>();
		allAthletes = new ArrayList<Athlete>();
		allJumpers = new ArrayList<Athlete>();
		allRunners = new ArrayList<Athlete>();
		allRunnersAndJumpers = new ArrayList<Athlete>();
		allTeams = new ArrayList<Team>();
		allJudges = new ArrayList<Judge>();
		allStudiums = new ArrayList<Stadium>();
		allPC = new ArrayList<PersonalCompetition>();
		allTC = new ArrayList<TeamCompetition>();
		allFirstPlaceAthletes  = new ArrayList<Athlete>();
		allSecondPlaceAthletes = new ArrayList<Athlete>();
		allThirdPlaceAthletes = new ArrayList<Athlete>();
		allFirstPlaceTeams = new ArrayList<Team>();
		allSecondPlaceTeams = new ArrayList<Team>();
		allThirdPlaceTeams = new ArrayList<Team>();
		
	}

	public void registerListener(ModelEventListener newListener) {
		listeners.add(newListener);
	}

	// Athletes
	// Add athlete
	public void addAthlete(String name, Countries country, SportType sportType, int numberOfMedals) throws cantAdd {
		Athlete newAthlete = new Athlete(name, country, sportType, numberOfMedals);
		
			allAthletes.add(newAthlete);
			System.out.println(name + " is added as athlete");

			if (newAthlete.getSportType() == SportType.Jumper) {
				allJumpers.add(newAthlete);
				System.out.println(name + " is added as jumper\n");
				fireJumperIsAddedToModelEvent(newAthlete);
			}

			if (newAthlete.getSportType() == SportType.Runner) {
				allRunners.add(newAthlete);
				System.out.println(name + " is added as runner\n");
				fireRunnerIsAddedToModelEvent(newAthlete);
			}

			if (newAthlete.getSportType() == SportType.RunnerAndJumper) {
				allRunnersAndJumpers.add(newAthlete);
				System.out.println(name + " is added as runner and jumper\n");
				fireJandRAddedToModelEvent(newAthlete);
			}
		
	}
	
	private void fireJumperIsAddedToModelEvent(Athlete newAthlete) {
		for (ModelEventListener l : listeners) {
			l.addednewJumperToModelEvent(newAthlete);
		}
	}

	private void fireRunnerIsAddedToModelEvent(Athlete newAthlete) {
		for (ModelEventListener l : listeners) {
			l.addednewRunnerToModelEvent(newAthlete);
		}
	}

	private void fireJandRAddedToModelEvent(Athlete newAthlete) {
		for (ModelEventListener l : listeners) {
			l.addednewJandRToModelEvent(newAthlete);
		}
	}

	// Show All Athletes
	public String showAllTheAthletes() {
		StringBuffer sbAllA = new StringBuffer("All Athletes that join the Olimpiad: \n\n");
		
		sbAllA.append(athletesToString() + "\n");
		
		fireAllAShowed(sbAllA.toString());
		return sbAllA.toString();
	}

	private void fireAllAShowed(String allTheAthletes) {
		for (ModelEventListener l : listeners) {
			l.allAShowedInModelEvent(allTheAthletes);
		}
	}

	// Update athlete sport type
	public void updateAthleteST(int athleteNumber, SportType sportType) {
		for (int i = 0; i < allAthletes.size(); i++) {
			if (allAthletes.get(i).getAthletNumber() == (athleteNumber + 1)) {
				allAthletes.get(i).setSportType(sportType);
			}
		}
		fireUpdatedAthleteST();
	}

	private void fireUpdatedAthleteST() {
		for (ModelEventListener l : listeners) {
			l.updatedAthleteSTToModel();
		}
	}

	// Update athlete number of medals
	public void updateAthleteM(int athleteNumber, int numberOfMedals) {
		for (int i = 0; i < allAthletes.size(); i++) {
			if (allAthletes.get(i).getAthletNumber() == (athleteNumber + 1)) {
				allAthletes.get(i).setNumberOfMedals(numberOfMedals);
			}
		}
		fireUpdateAthleteM();
	}

	private void fireUpdateAthleteM() {
		for (ModelEventListener l : listeners) {
			l.updatedAthleteMToModelEvent();
		}
	}
	
	// Remove athlete from Olimpiad 
	public void deleteAthlete(int athleteNumber) {
		for (int i = 0; i < allAthletes.size(); i++) {
			if (allAthletes.get(i).getAthletNumber() == (athleteNumber + 1)) {
				allAthletes.remove(i);
			}
		}
		fireDeleteAthlete(athleteNumber);
	}

	
	private void fireDeleteAthlete(int athleteNumber) {
		for (ModelEventListener l : listeners) {
			l.athleteRemovedInModelEven(athleteNumber);
		}
	}
	
	// Save athletes into file
		public void saveAthletes(String FileName) throws FileNotFoundException {
			PrintWriter pwSaveA = new PrintWriter(new File(FileName));

			// name + country + sportType + numberOfMedals;

			pwSaveA.println(allAthletes.size());
			for (int i = 0; i < allAthletes.size(); i++) {
				pwSaveA.println(allAthletes.get(i).getName());
				pwSaveA.println(allAthletes.get(i).getCountry());
				pwSaveA.println(allAthletes.get(i).getSportType());
				pwSaveA.println(allAthletes.get(i).getNumberOfMedals());
			}
			
			fireSaveFileInModelEvent(new File(FileName));
			pwSaveA.close();
		}

		private void fireSaveFileInModelEvent(File FileName) {
			for (ModelEventListener l : listeners) {
				l.savedFileInModelEvent(FileName);
			}

		}

		// Read athletes from file
		public void ReadAthletes(String FileName) throws FileNotFoundException, cantAdd {
			Scanner scanA = new Scanner(new File(FileName));

			while (scanA.hasNext()) {
				int numberOfA = scanA.nextInt();
				scanA.nextLine();
				for (int i = 0; i < numberOfA; i++) {
					String AthleteName = scanA.nextLine();
					Athlete.Countries country = Athlete.Countries.valueOf(scanA.nextLine());
					Athlete.SportType sportType = Athlete.SportType.valueOf(scanA.nextLine());
					int ANumM = scanA.nextInt();
					scanA.nextLine();
					addAthlete(AthleteName, country, sportType, ANumM);
				}

			}
			fireReadFileInModelEvent(FileName);
			scanA.close();
		}

		private void fireReadFileInModelEvent(String FileName) {
			for (ModelEventListener l : listeners) {
				l.readedFileInModelEvent(FileName);
			}
		}

	// Teams
	// Add the team
	public void creatingTeam(Athlete.Countries teamCounty, SportType sportType, ArrayList<Athlete> AthletesNew,
			int numberOfMedals) {
		ArrayList<Athlete> tempJ = new ArrayList<Athlete>();
		ArrayList<Athlete> tempR = new ArrayList<Athlete>();
		ArrayList<Athlete> tempRandJ = new ArrayList<Athlete>();
		JumpersTeam newJT = new JumpersTeam(teamCounty, sportType, tempJ, numberOfMedals);
		RunnersTeam newRT = new RunnersTeam(teamCounty, sportType, tempR, numberOfMedals);
		RandJTeam newRandJ = new RandJTeam(teamCounty, sportType, tempRandJ, numberOfMedals);

		for (int i = 0; i < AthletesNew.size(); i++) {

			if (AthletesNew.get(i).getSportType() == SportType.Jumper) {
				tempJ.add(AthletesNew.get(i));
				newJT.setAllTeamAthletes(tempJ);
				System.out.println(AthletesNew.get(i).getName() + " added to jumpers team");
				System.out.println("Jumpers added");
			}

			if (AthletesNew.get(i).getSportType() == SportType.Runner) {
				tempR.add(AthletesNew.get(i));
				newRT.setAllTeamAthletes(tempR);
				System.out.println(AthletesNew.get(i).getName() + " added to runners team");
				System.out.println("Runners added");
			}

			if (AthletesNew.get(i).getSportType() == SportType.RunnerAndJumper) {
				tempRandJ.add(AthletesNew.get(i));
				newRandJ.setAllTeamAthletes(tempRandJ);
				System.out.println(AthletesNew.get(i).getName()+ " added to jumpers and runners team");
				System.out.println("JandR added");
			}

		}
		
		// Adding the new team into all teams array
		if (newJT.getAllTeamAthletes().size() != 0) {	
			allTeams.add(newJT);
		}
		if (newRT.getAllTeamAthletes().size() != 0) {
			allTeams.add(newRT);
		}
		if (newRandJ.getAllTeamAthletes().size() != 0) {
			allTeams.add(newRandJ);
		}

		fireUpdateNumberOfTeams();
	}

	public void fireUpdateNumberOfTeams() {
		for (ModelEventListener l : listeners)
			l.updatedNumOfTeamsInModel(allTeams.size());
	}

	// show all athletes from specific country and sport type
	public ArrayList<Athlete> showAthletsInCountry(Countries teamCounty, SportType sportType) throws alreadyExist {
		ArrayList<Athlete> showAByCountry = new ArrayList<Athlete>();
		
		for (int j = 0; j < allAthletes.size(); j++) {
			if (allAthletes.get(j).getCountry() == teamCounty) {
				if (allAthletes.get(j).getSportType() == sportType
						|| allAthletes.get(j).getSportType() == SportType.RunnerAndJumper) {
					showAByCountry.add(allAthletes.get(j));
				}
			}
		}
	
			fireShowAllAthletesInCountry(showAByCountry);

			return showAByCountry;		
	}
	
	private void fireShowAllAthletesInCountry(ArrayList<Athlete> showAByCountry) {
		for (ModelEventListener l : listeners) {
			l.showedAllAthletesInCountryInModelEvent(showAByCountry);
		}

	}

	// Update number of teams medals
	public void updatedTNumberOfMedals(int teamNumber, int numberOfTeamsM) {
		for (int i = 0; i < allTeams.size(); i++) {
			if (allTeams.get(i).getTeamNumber() == (teamNumber + 1)) {
				allTeams.get(i).setNumberOfTeamsMedals(numberOfTeamsM);
			}
		}
		fireUpdateTeamM();
	}

	private void fireUpdateTeamM() {
		for (ModelEventListener l : listeners) {
			l.updatedTeamMToModelEvent();
		}
	}	
	
	// Show teams
	public String showAllTeams() {
		StringBuffer sbShowAllT = new StringBuffer("All Teams that join the Olimpiad: \n\n");
		
	
		sbShowAllT.append(teamToString() + "\n");
		
		fireShowAllT(sbShowAllT.toString());
		return sbShowAllT.toString();
	}
	
	private void fireShowAllT(String string) {
		for (ModelEventListener l : listeners) {
			l.showAllTeamsInModelEvent(string);
		}
	}
	
	// Remove Team from Olimpiad 
		public void deleteTeam(int teamNumber) {
			for (int i = 0; i < allTeams.size(); i++) {
				if (allTeams.get(i).getTeamNumber() == (teamNumber + 1)) {
					allTeams.remove(i);
				}
			}
			fireDeleteTeam(teamNumber);
		}

		
		private void fireDeleteTeam(int teamNumber) {
			for (ModelEventListener l : listeners) {
				l.teamRemovedInModelEven(teamNumber);
			}
		}


		// Save Teams into file
		public void saveTeams(String fileName) throws FileNotFoundException {
			PrintWriter pwSavaT = new PrintWriter(new File(fileName));
			
			pwSavaT.println(allTeams.size());
			for (int i = 0; i < allTeams.size(); i++) {
				pwSavaT.println(allTeams.get(i).getTeamCounty());
				pwSavaT.println(allTeams.get(i).getSportType());
				pwSavaT.println(allTeams.get(i).getAllTeamAthletes().size());
				for (int j = 0; j < allTeams.get(i).getAllTeamAthletes().size(); j++) {
					pwSavaT.println(allTeams.get(i).getAllTeamAthletes().get(j).getName());
					pwSavaT.println(allTeams.get(i).getAllTeamAthletes().get(j).getCountry());
					pwSavaT.println(allTeams.get(i).getAllTeamAthletes().get(j).getSportType());
					pwSavaT.println(allTeams.get(i).getAllTeamAthletes().get(j).getNumberOfMedals());
				}
				pwSavaT.println(allTeams.get(i).getNumberOfTeamsMedals());
			}
			fireSaveTeamInModel(fileName);
			pwSavaT.close();		
		}

		private void fireSaveTeamInModel(String fileName) {
			for (ModelEventListener l : listeners) {
				l.savedTeamsInModelEvant(fileName);
			}
		}

		// Read Teams from File
		public void ReadTeam(String FileName) throws FileNotFoundException {
			Scanner scanT = new Scanner(new File(FileName));
			ArrayList<Athlete> AInT = new ArrayList<Athlete>();

			while (scanT.hasNext()) {
				int numOfT = scanT.nextInt();
				scanT.nextLine();

				for (int i = 0; i < numOfT; i++) {
					Countries Tcountry = Countries.valueOf(scanT.nextLine());
					SportType TsportType = SportType.valueOf(scanT.nextLine());
					int numOfAthInT = scanT.nextInt();
					scanT.nextLine();

					for (int j = 0; j < numOfAthInT; j++) {
						String AthleteName = scanT.nextLine();
						Countries Acountry = Countries.valueOf(scanT.nextLine());
						Athlete.SportType AsportType = Athlete.SportType.valueOf(scanT.nextLine());
						int ANumM = scanT.nextInt();
						scanT.nextLine();
						AInT.add(new Athlete(AthleteName, Acountry, AsportType, ANumM));
					}

					int TNumM = scanT.nextInt();
					scanT.nextLine();
					creatingTeam(Tcountry, TsportType, AInT, TNumM);
				}

			}
			fireLoadTeamFromFile(FileName);
			scanT.close();
		}
		
		private void fireLoadTeamFromFile(String FileName) {
			for (ModelEventListener l : listeners) {
				l.teamsReadedFromFileInModelEvent(FileName);
			}			
		}

	// Add judge
		public void addJudge(String name, Judge.Countries country, Judge.JudgeArea sportType) {
			Judge newJ = new Judge(name, country, sportType);
		
			allJudges.add(newJ);
			
			fireAddNJudge();
		}
		
		public void fireAddNJudge() {
			for (ModelEventListener l : listeners) {
				l.judgeAddedToModelEvet();
			}
		}
	
		// Update Judge Sport Type
		private void fireaddStadium() {
			for (ModelEventListener l : listeners) {
				l.addStadiumToModelEvent();
			}
		}

	public void updateJudgeST(int judgeNumber, Judge.JudgeArea  sportType) {
		for (int i = 0; i < allJudges.size(); i++) {
			if (allJudges.get(i).getJudgeNumber() == (judgeNumber + 1)) {
				allJudges.get(i).setSportType(sportType);
			}
		}
		fireUpdatedJudgeeST();
	}

	private void fireUpdatedJudgeeST() {
		for (ModelEventListener l : listeners) {
			l.updatedJudgeSTToModel();
		}
	}
	
	// Remove Judge from Olimpiad 
	public void deleteJudge(int judgeNumber) {
		for (int i = 0; i < allJudges.size(); i++) {
			if (allJudges.get(i).getJudgeNumber() == (judgeNumber + 1)) {
				allJudges.remove(i);
			}
		}
		fireDeleteJudge(judgeNumber);
	}

	
	private void fireDeleteJudge(int judgeNumber) {
		for (ModelEventListener l : listeners) {
			l.judgeRemovedInModelEven(judgeNumber);
		}
	}
	
	// Show all judges
	public String showAllTheJudges() {
		StringBuffer sbAllJ = new StringBuffer("All Judges in Olimpiad: \n\n");
		for (int i = 0; i < allJudges.size(); i++) {
			sbAllJ.append(i + 1 + ") " + allJudges.get(i).toString() + "\n");
		}
		fireAllJShowed(sbAllJ.toString());
		return sbAllJ.toString();
	}

	private void fireAllJShowed(String allJudges) {
		for (ModelEventListener l : listeners) {
			l.allJShowedInModelEvent(allJudges);
		}
	}
	
	// Save all judges into file
	public void saveJudges(String FileName) throws FileNotFoundException {
		PrintWriter pwSaveJ = new PrintWriter(new File(FileName));

		// name + country + sportType + numberOfMedals;

		pwSaveJ.println(allJudges.size());
		for (int i = 0; i < allJudges.size(); i++) {
			pwSaveJ.println(allJudges.get(i).getName());
			pwSaveJ.println(allJudges.get(i).getCountry());
			pwSaveJ.println(allJudges.get(i).getSportType());
		
		}
		
		fireSaveJFileInModelEvent(new File(FileName));
		pwSaveJ.close();
	}

	private void fireSaveJFileInModelEvent(File FileName) {
		for (ModelEventListener l : listeners) {
			l.savedJFileInModelEvent(FileName);
		}

	}
	// Read Judges from file
		public void ReadJudges(String FileName) throws FileNotFoundException {
			Scanner scanJ = new Scanner(new File(FileName));

			while (scanJ.hasNext()) {
				int numberOfJ = scanJ.nextInt();
				scanJ.nextLine();
				for (int i = 0; i < numberOfJ; i++) {
					String name = scanJ.nextLine();
					Judge.Countries country = Judge.Countries.valueOf(scanJ.nextLine());
					Judge.JudgeArea sportType = Judge.JudgeArea.valueOf(scanJ.nextLine());
					addJudge(name, country, sportType);
				}

			}
			fireReadFileJInModelEvent(FileName);
			scanJ.close();
		}

		private void fireReadFileJInModelEvent(String FileName) {
			for (ModelEventListener l : listeners) {
				l.readedFileJInModelEvent(FileName);
			}
		}
	
	// Stadiums
	// Add Stadium		
		public void addStadium(String nameStadium, Stadium.StadiumLocation locationsStadium, long seatsStadium) {
			Stadium newS = new Stadium(nameStadium, locationsStadium, seatsStadium);
		
			allStudiums.add(newS);
			System.out.println(newS.getStadiumName() + " stadium added");
			
			fireaddStadium();
		}
	// Update Stadium number of seats
	public void updateStadiumS(int StadiumNumber, int numberOfSeats) {
		for (int i = 0; i < allStudiums.size(); i++) {
			if (allStudiums.get(i).getStadiumNumber() == (StadiumNumber + 1)) {
				allStudiums.get(i).setNumberOfSetes(numberOfSeats);
			}
		}
		fireUpdateStadiumS();
	}

	private void fireUpdateStadiumS() {
		for (ModelEventListener l : listeners) {
			l.updatedStadiumSToModelEvent();
		}
	}

	// Remove Stadium from Olimpiad 
	public void deleteStadium(int stadiumNumber) {
		for (int i = 0; i < allJudges.size(); i++) {
			if (allJudges.get(i).getJudgeNumber() == (stadiumNumber + 1)) {
				allJudges.remove(i);
			}
		}
		fireDeleteStadium(stadiumNumber);
	}

	
	private void fireDeleteStadium(int stadiumNumber) {
		for (ModelEventListener l : listeners) {
			l.stadiumRemovedInModelEven(stadiumNumber);
		}
	}
	
	// Show all Stadiums
/*	public String showAllTheStadiums() {
		StringBuffer sbAllS = new StringBuffer("All Stadiums that join the Olimpiad: \n\n");
		
		sbAllS.append(stadiumToString()+ "\n");
		
		fireAllSShowed(sbAllS.toString());
		return sbAllS.toString();
	}

	private void fireAllSShowed(String allStudiums) {
		for (ModelEventListener l : listeners) {
			l.allStudiumsShowedInModelEvent(allStudiums);
		}
	}
*/	// Show all judges
		public String showAllTheStadiums() {
			StringBuffer sbAllS = new StringBuffer("All Stadiums that join the Olimpiad: \n\n");
			for (int i = 0; i < allStudiums.size(); i++) {
				sbAllS.append(i + 1 + ") " + allStudiums.get(i).toString() + "\n");
			}
			fireAllSShowed(sbAllS.toString());
			return sbAllS.toString();
		}

		private void fireAllSShowed(String allStudiums) {
			for (ModelEventListener l : listeners) {
				l.allStudiumsShowedInModelEvent(allStudiums);
			}
		}

	// Save all Stadiums into file
	public void saveStadium(String FileName) throws FileNotFoundException {
		PrintWriter pwSaveS = new PrintWriter(new File(FileName));

		// name + country + sportType + numberOfMedals;

		pwSaveS.println(allStudiums.size());
		for (int i = 0; i < allStudiums.size(); i++) {
			pwSaveS.println(allStudiums.get(i).getStadiumName());
			pwSaveS.println(allStudiums.get(i).getLocation());
			pwSaveS.println(allStudiums.get(i).getNumberOfSetes());
		
		}
		
		fireSaveSFileInModelEvent(new File(FileName));
		pwSaveS.close();
	}

	private void fireSaveSFileInModelEvent(File FileName) {
		for (ModelEventListener l : listeners) {
			l.savedSFileInModelEvent(FileName);
		}

	}
	// Read Stadiums from file
		public void ReadStadiums(String FileName) throws FileNotFoundException {
			Scanner scanS = new Scanner(new File(FileName));

			while (scanS.hasNext()) {
				int numberOfS = scanS.nextInt();
				scanS.nextLine();
				for (int i = 0; i < numberOfS; i++) {
					String stadiumName = scanS.nextLine();
					Stadium.StadiumLocation location = Stadium.StadiumLocation.valueOf(scanS.nextLine());
					int numberOfSetes = scanS.nextInt();
					scanS.nextLine();
					addStadium(stadiumName, location, numberOfSetes);
				}

			}
			fireReadFileSInModelEvent(FileName);
			scanS.close();
		}

		private void fireReadFileSInModelEvent(String FileName) {
			for (ModelEventListener l : listeners) {
				l.readedFileSInModelEvent(FileName);
			}
		}
	
	// show all judges from specific country and sport type
	public ArrayList<Judge> showJudgesBySTFor_PC(Judge.JudgeArea sportType)   {
		
		ArrayList<Judge> showJByST = new ArrayList<Judge>();
		for (int i = 0; i < allJudges.size(); i++) {
			if (allJudges.get(i).getSportType() == sportType || allJudges.get(i).getSportType() == JudgeArea.RunnersAndJumpers) {
				showJByST.add(allJudges.get(i));
			}
		}
		fireShowAllJByST(showJByST);
		return showJByST;
	}
	private void fireShowAllJByST(ArrayList<Judge> showJByST) {
		for (ModelEventListener l : listeners) {
			l.showedAllJudgesBySTInModel(showJByST);
		}
	}

	// show all athletes from specific sport type
		public ArrayList<Athlete> showAthletesBySTFor_PC(Athlete.SportType sportType)   {
			
			ArrayList<Athlete> showAByST_PC = new ArrayList<Athlete>();
			for (int i = 0; i < allAthletes.size(); i++) {
				if (allAthletes.get(i).getSportType() == sportType || allAthletes.get(i).getSportType() == SportType.RunnerAndJumper) {
					showAByST_PC.add(allAthletes.get(i));
				}
			}
			fireshowAthletesBySTFor_PC(showAByST_PC);
			return showAByST_PC;
		}
		
		private void fireshowAthletesBySTFor_PC(ArrayList<Athlete> showAByST_PC) {
			for (ModelEventListener l : listeners) {
				l.showAthletesBySTInModel(showAByST_PC);
			}
		}
	
		// Competitions:
		// create personal competition
		public void createPersonalCompetition(int limit, int stadium, JudgeArea sportType, Judge judge,
				ArrayList<Athlete> participants) throws wrongAmountOfParticipants {
			if (participants.size() == limit ) {
				for (int i = 0; i < allStudiums.size(); i++) {
					if (allStudiums.get(i).getStadiumNumber() == (stadium + 1)) {
						PersonalCompetition newPersonalCompetition = new PersonalCompetition(allStudiums.get(i),
								sportType, judge, participants);
						allPC.add(newPersonalCompetition);
						System.out.println("Created: " + newPersonalCompetition.toString());
					}
				}
			} else {
				throw new wrongAmountOfParticipants();
			}
			firePCompetitionCreated();

		}
	
	private void firePCompetitionCreated() {
		for (ModelEventListener l : listeners) {
			l.personalCCreatedInModelEvent();
		}
		
	}

	// create team competition
	public void createdTeamCInModelEvent(int limit, int stadium, Judge judge, JudgeArea sportType,
			ArrayList<Team> participantedTeams) throws wrongAmountOfParticipants {

		if (participantedTeams.size() == limit) {

			for (int i = 0; i < allStudiums.size(); i++) {
				if (allStudiums.get(i).getStadiumNumber() == (stadium + 1)) {
					TeamCompetition newTeamCompetition = new TeamCompetition(allStudiums.get(i), sportType, judge,
							participantedTeams);
					allTC.add(newTeamCompetition);
					System.out.println("Created: " + newTeamCompetition.toString());

				}
			}

		} else {
			throw new wrongAmountOfParticipants();

		}
		fireTCompetitionCreated();

	}

	private void fireTCompetitionCreated() {
		for (ModelEventListener l : listeners) {
			l.teamCCreatedInModelEvent();
		}
		
	}

	// show all teams from specific sport type - to create a team
	public ArrayList<Team> allTeamsShowedInModelEvent(Athlete.SportType sportType) {
		ArrayList<Team> showTByST_TC = new ArrayList<Team>();
		
		for (int i = 0; i < allTeams.size(); i++) {
			if ((allTeams.get(i).getSportType() == sportType) || (allTeams.get(i).getSportType() == SportType.RunnerAndJumper)) {
				showTByST_TC.add(allTeams.get(i));
			}
		}
		fireShowTeamsBySTFor_TC(showTByST_TC);
		return showTByST_TC;
	}
	

	private void fireShowTeamsBySTFor_TC(ArrayList<Team> showAByST_PC) {
		for (ModelEventListener l : listeners) {
			l.showTeamsBySTInModel(showAByST_PC);
		}
	}

	
	//Set Results
	// show all existing personal  competition
	public String repositoryAllPCInModelEvent() {
		StringBuffer sbAllPC = new StringBuffer("All Olimpiad personal competitions: \n\n");
		System.out.println("READING personal competitions from repository . . .");
		for (int i = 0; i <allPC.size(); i++) {
			sbAllPC.append(allPC.get(i).toString() + "\n");
		}
		fireShowAllPC(sbAllPC.toString());
		return sbAllPC.toString();
	}

	private void fireShowAllPC(String string) {
		for (ModelEventListener l : listeners) {
			l.repositoryAllPCInModelEvent(string);
		}
	}
	
	// return array personal participants to combo box
	public ArrayList<Athlete> showAllAthletesInPC(int numberofPC) throws Exception {
		ArrayList<Athlete> allAPC_Participants = new ArrayList<Athlete>();
		for (int i = 0; i < allPC.size(); i++) {
			if (allPC.get(i).getPCNumber() == (numberofPC + 1)) {
				for (int j = 0; j < allPC.get(i).getParticipants().size(); j++) {
					allAPC_Participants.add(allPC.get(i).getParticipants().get(j));
				}
			} else {
				i= allPC.size();
				throw new Exception("This competition doesn't exist");
			}
		}	
		
		fireshowAllAthletesInPCInModelEvent(allAPC_Participants);
		return allAPC_Participants;
	}

	private void fireshowAllAthletesInPCInModelEvent(ArrayList<Athlete> allAPC_Participants) {
		for (ModelEventListener l : listeners) {
			l.showAllAthletesInPCInModelEvent(allAPC_Participants);
		}
	}

	
	public void setPersonalCompetitionsResult(Athlete firstPlace, Athlete secondPlace, Athlete thirdPlace) throws duplicateWinner{
		if (firstPlace == secondPlace || secondPlace == thirdPlace || thirdPlace == firstPlace ) {
			throw new duplicateWinner();
		} else {

			for (int i = 0; i < allAthletes.size(); i++) {
				if (allAthletes.get(i).getAthletNumber() == firstPlace.getAthletNumber()) {
					allAthletes.get(i).setFirslPlace(firstPlace);
					allFirstPlaceAthletes.add(firstPlace);
				}

				if (allAthletes.get(i).getAthletNumber() == firstPlace.getAthletNumber()) {
					allAthletes.get(i).setSecondlPlace(secondPlace);
					allSecondPlaceAthletes.add(secondPlace);
				}

				if (allAthletes.get(i).getAthletNumber() == firstPlace.getAthletNumber()) {
					allAthletes.get(i).setThirddlPlace(thirdPlace);
					allThirdPlaceAthletes.add(thirdPlace);
				}
			}
		}
		firesetPersonalCompetitionsResults();
	}

	private void firesetPersonalCompetitionsResults() {
		for (ModelEventListener l : listeners) {
			l.setPersonalCompetitionsResultsInModelEvent();
		}
	}

	// show all team competition
	public String repositoryAllTCInModelEvent() {
		StringBuffer sbAllTC = new StringBuffer("All Olimpiad team competitions: \n\n");
		System.out.println("READING team competitions from repository . . .");

		for (int i = 0; i <allTC.size(); i++) {
			sbAllTC.append(allTC.get(i).toString() + "\n");
		}
		fireShowAllTC(sbAllTC.toString());
		return sbAllTC.toString();
	}

	private void fireShowAllTC(String string) {
		for (ModelEventListener l : listeners) {
			l.repositoryAllTCInModelEvent(string);
		}
	}
	
	// return array team participants to combo box
	public ArrayList<Team> showAllTeamsInTC(int numberofTC) throws Exception{
		ArrayList<Team> allTTC_Participants = new ArrayList<Team>();
		
		for (int i = 0; i < allTC.size(); i++) {
			if (allTC.get(i).getTCNumber() == (numberofTC + 1)) {
				for (int j= 0; j < allTC.get(i).getParticipantedTeams().size(); j++) {
					allTTC_Participants.add(allTC.get(i).getParticipantedTeams().get(j));
				}
			}else {
				throw new Exception("This competition doesn't exist");
			}
		}
			
		
		fireshowAllAthletesInTC(allTTC_Participants);
		return allTTC_Participants;
	}

	private void fireshowAllAthletesInTC(ArrayList<Team> allTTC_Participants) {
		for (ModelEventListener l : listeners) {
			l.showAllTeamsInTCInModelEvent(allTTC_Participants);
		}
	}

	// set team competitions results
	public void setTeamCompetitionsResultsInModelEvent(Team firstPlace, Team secondPlace, Team thirdPlace) throws duplicateWinner {
		if (firstPlace == secondPlace || secondPlace == thirdPlace || thirdPlace == firstPlace) {
			throw new duplicateWinner();
		} else {
			for (int i = 0; i < allTeams.size(); i++) {
				if (allTeams.get(i).getTeamNumber() == firstPlace.getTeamNumber()) {
					allTeams.get(i).setFirslPlace(firstPlace);
					allFirstPlaceTeams.add(firstPlace);
				}

				if (allTeams.get(i).getTeamNumber() == firstPlace.getTeamNumber()) {
					allTeams.get(i).setSecondlPlace(secondPlace);
					allSecondPlaceTeams.add(secondPlace);
				}

				if (allTeams.get(i).getTeamNumber() == firstPlace.getTeamNumber()) {
					allTeams.get(i).setThirddlPlace(thirdPlace);
					allThirdPlaceTeams.add(thirdPlace);
				}

			}
		}
		firesetTeamCompetitionsResults();
	}

	private void firesetTeamCompetitionsResults() {
		for (ModelEventListener l : listeners) {
			l.setTeamCompetitionsResultsInModelEvent();
		}
	}

	// The final winners
	public String finalWinners() throws IndexOutOfBoundsException {
		if (allAthletes.size() == 0 || allTeams.size() == 0) {
			throw new IndexOutOfBoundsException("Something is missing for counting the final result");
		} else {
			int allIsraelM = 0, allChinaM = 0, allJapanM = 0, allAmericaM = 0, allArgentinaM = 0, allItalyM = 0,
					allRussiaM = 0;
			ArrayList<Integer> medals = new ArrayList<Integer>();

			// Personal competitions
			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {

					if (allAthletes.get(i).getCountry() == Countries.Israel
							&& Countries.Israel == allTeams.get(j).getTeamCounty()) {
						allIsraelM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allIsraelM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.China
							&& Countries.China == allTeams.get(j).getTeamCounty()) {
						allChinaM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allChinaM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.Japan
							&& Countries.Japan == allTeams.get(j).getTeamCounty()) {
						allJapanM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allJapanM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.America
							&& Countries.America == allTeams.get(j).getTeamCounty()) {
						allAmericaM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allAmericaM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.Argentina
							&& Countries.Argentina == allTeams.get(j).getTeamCounty()) {
						allArgentinaM = allAthletes.get(i).getNumberOfMedals()
								+ allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allArgentinaM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.Italy
							&& Countries.Italy == allTeams.get(j).getTeamCounty()) {
						allItalyM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allItalyM);
					}
				}}

			for (int i = 0; i < allAthletes.size(); i++) {
				for (int j = 0; j < allTeams.size(); j++) {
					if (allAthletes.get(i).getCountry() == Countries.Russia
							&& Countries.Russia == allTeams.get(j).getTeamCounty()) {
						allRussiaM = allAthletes.get(i).getNumberOfMedals() + allTeams.get(j).getNumberOfTeamsMedals();
						medals.add(allRussiaM);
					}

				}
			}

			// sorting by number of medals
			Collections.sort(medals);

			StringBuffer sbTheWinners = new StringBuffer("2020 Olympic Games Winners Are: ");

			// First place********************
			if (medals.get(0) == allIsraelM) {
				sbTheWinners.append("First Place: Israel\n");
			}

			if (medals.get(0) == allChinaM) {
				sbTheWinners.append("First Place: China\n");
			}

			if (medals.get(0) == allJapanM) {
				sbTheWinners.append("First Place: Japan\n");
			}

			if (medals.get(0) == allAmericaM) {
				sbTheWinners.append("First Place: America\n");
			}

			if (medals.get(0) == allArgentinaM) {
				sbTheWinners.append("First Place: Argentina\n");
			}

			if (medals.get(0) == allItalyM) {
				sbTheWinners.append("First Place: Italy\n");
			}

			if (medals.get(0) == allRussiaM) {
				sbTheWinners.append("First Place: Russia\n");
			}

			// Second place**********************
			if (medals.get(1) == allIsraelM) {
				sbTheWinners.append("Second Place: Israel\n");
			}
			if (medals.get(1) == allChinaM) {
				sbTheWinners.append("Second Place: China\n");
			}

			if (medals.get(1) == allJapanM) {
				sbTheWinners.append("Second Place: Japan\n");
			}

			if (medals.get(1) == allAmericaM) {
				sbTheWinners.append("Second Place: America\n");
			}

			if (medals.get(1) == allArgentinaM) {
				sbTheWinners.append("Second Place: Argentina\n");
			}

			if (medals.get(1) == allItalyM) {
				sbTheWinners.append("Second Place: Italy\n");
			}

			if (medals.get(1) == allRussiaM) {
				sbTheWinners.append("Second Place: Russia\n");
			}

			// Third place*******************
			if (medals.get(2) == allIsraelM) {
				sbTheWinners.append("Third Place: Israel\n");
			}

			if (medals.get(2) == allChinaM) {
				sbTheWinners.append("Third Place: China\n");
			}

			if (medals.get(2) == allJapanM) {
				sbTheWinners.append("Third Place: Japan\n");
			}

			if (medals.get(2) == allAmericaM) {
				sbTheWinners.append("Third Place: America\n");
			}

			if (medals.get(2) == allArgentinaM) {
				sbTheWinners.append("Third Place: Argentina\n");
			}

			if (medals.get(2) == allItalyM) {
				sbTheWinners.append("Third Place: Italy\n");
			}

			if (medals.get(2) == allRussiaM) {
				sbTheWinners.append("Third Place: Russia\n");
			}
			fireWinners(sbTheWinners.toString());
			return sbTheWinners.toString();
		}
	}

	private void fireWinners(String string) {
		for (ModelEventListener l : listeners) {
			l.winnersShowedInModelEvent(string);
		}
	}
	
	//print array - not as to string
	public String athletesToString() {
		StringBuffer sbAthletes = new StringBuffer();
		
		for (int i = 0; i < allAthletes.size(); i++) {
			sbAthletes.append(i+1+ ") Athlete number "  + (i + 1) + ": " + allAthletes.get(i).toString()+ "\n");
		}
		
		return sbAthletes.toString();
	}
	
	//print team - not as to string
	public String teamToString() {
		StringBuffer sbTeam = new StringBuffer();
		
		for (int i = 0; i < allTeams.size(); i++) {
			sbTeam.append(i+1 +") Team number " + (i + 1) + ": "  + allTeams.get(i).toString()+ "\n");
		}
		
		return sbTeam.toString();
	}
	
	//print stadiums - not as to string
	public String stadiumToString() {
		StringBuffer sbStadium = new StringBuffer();
		
		for (int i = 0; i < allTeams.size(); i++) {
			sbStadium.append(i+1 +") Stadium number " + (i + 1) + ": "  + allStudiums.get(i).toString()+ "\n");
		}
		return sbStadium.toString();
	}

}
