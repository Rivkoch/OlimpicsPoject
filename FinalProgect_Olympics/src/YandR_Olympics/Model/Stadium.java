//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Model;

public class Stadium {
	public enum StadiumLocation { Center1, East1, South1, South2, North1, North2, West1};
	
	private String stadiumName;
	private StadiumLocation location;
	private long numberOfSetes;
	private static int stadiumNumberCounter =1;
	private int stadiumNumber;
	
	public Stadium(String stadiumName, StadiumLocation location, long numberOfSetes) {
		this.stadiumName = stadiumName;
		this.location = location;
		this.numberOfSetes = numberOfSetes;
		this.stadiumNumber = stadiumNumberCounter++;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public StadiumLocation getLocation() {
		return location;
	}

	public void setLocation(StadiumLocation location) {
		this.location = location;
	}

	public long getNumberOfSetes() {
		return numberOfSetes;
	}

	public void setNumberOfSetes(long numberOfSetes) {
		this.numberOfSetes = numberOfSetes;
	}

	public int getStadiumNumber() {
		return stadiumNumber + 1;
	}

	public void setStadiumNumber(int stadiumNumber) {
		this.stadiumNumber = stadiumNumberCounter++;
	}

	@Override
	public String toString() {
		return "\"" + stadiumName + "\" in location " + location + " has " + numberOfSetes + " number of setes.";
	}

	
	
}
