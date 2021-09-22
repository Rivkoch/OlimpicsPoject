//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

import java.util.ArrayList;

import YandR_Olympics.Model.Judge.JudgeArea;

public class PersonalCompetition {
	private Stadium stadium;
	private Judge judge;
	private Judge.JudgeArea sportType;
	private ArrayList<Athlete> participants;
	private static int PCNumberCounter =1;
	private int PCNumber;

	public PersonalCompetition(Stadium stadium, JudgeArea sportType, Judge judge, ArrayList<Athlete> participants) {
		this.stadium = stadium;
		this.judge = judge;
		this.sportType = sportType;
		this.participants = participants;
		this.PCNumber = PCNumberCounter++;
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

	public ArrayList<Athlete> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Athlete> participants) {
		this.participants = participants;
	}

	public int getPCNumber() {
		return PCNumber + 1;
	}

	public void setPCNumber(int pCNumber) {
		PCNumber = PCNumberCounter++;
	}
	
	@Override
	public String toString() {
		String athleteST= "change";
		if(sportType.equals(JudgeArea.Jumpers)) {
			athleteST = "Jumpers";
		}
		
		if(sportType.equals(JudgeArea.Runners)) {
			athleteST = "Runners";
		}
		
		if(sportType.equals(JudgeArea.RunnersAndJumpers)) {
			athleteST = "Runners and Jumpers ";
		}
		
		return PCNumber + ") " + athleteST + " single competition  is occure at stadium \"" + 
		stadium + " and judged by "+ judge + " contain " +
		participants.size() + " participants.\n\n";
	}
	
}
