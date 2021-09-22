//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

import java.util.ArrayList;

import YandR_Olympics.Model.Judge.JudgeArea;

public class TeamCompetition {
	private Stadium stadium;
	private Judge judge;
	private Judge.JudgeArea sportType;
	private ArrayList<Team> participantedTeams;
	private static int TCNumberCounter =1;
	private int TCNumber;

	public TeamCompetition(Stadium stadium, JudgeArea sportType, Judge judge, ArrayList<Team> participantedTeams) {
		this.stadium = stadium;
		this.judge = judge;
		this.sportType = sportType;
		this.participantedTeams = participantedTeams;
		this.TCNumber = TCNumberCounter++;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	public Judge.JudgeArea getSportType() {
		return sportType;
	}

	public void setSportType(Judge.JudgeArea sportType) {
		this.sportType = sportType;
	}

	public ArrayList<Team> getParticipantedTeams() {
		return participantedTeams;
	}

	public void setParticipantedTeams(ArrayList<Team> participantedTeams) {
		this.participantedTeams = participantedTeams;
	}

	public int getTCNumber() {
		return TCNumber + 1;
	}

	public void setTCNumber(int tCNumber) {
		TCNumber = TCNumberCounter++;
	}
	
	@Override
	public String toString() {
		 String teamST= "change";
		if(sportType.equals(JudgeArea.Jumpers)) {
			teamST = "Jumpers";
		}
		
		if(sportType.equals(JudgeArea.Runners)) {
			teamST = "Runners";
		}
		
		if(sportType.equals(JudgeArea.RunnersAndJumpers)) {
			teamST = "Runners and Jumpers ";
		}
		
		return TCNumber + ") " + teamST + " teams competition  is occure at stadium \"" + 
		stadium + " and judged by "+ judge + " contain " + participantedTeams.size() + " participants.\n\n";
	}
	
}
