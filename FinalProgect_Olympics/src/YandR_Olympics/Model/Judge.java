//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

public class Judge {
	public enum Countries {Israel, China, Japan, America, Argentina, Italy, Russia};
	public enum JudgeArea {Runners, Jumpers, RunnersAndJumpers};
	
	private String name;
	private Countries country;
	private JudgeArea sportType;
	private static int judgeNumberCounter = 1;
	private int judgeNumber;	
	
	public Judge(String name, Countries country, JudgeArea sportType) {
		this.name = name;
		this.country = country;
		this.sportType = sportType;
		this.judgeNumber = judgeNumberCounter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public JudgeArea getSportType() {
		return sportType;
	}

	public void setSportType(JudgeArea sportType) {
		this.sportType = sportType;
	}

	public int getJudgeNumber() {
		return judgeNumber + 1;
	}
	
	public void setJudgeNumber() {
		this.judgeNumber = judgeNumberCounter++;
	}

	@Override
	public String toString() {
		return  name  + " from country " + country + " - Is judging: "	+ sportType +".";
	}
	
	
}
