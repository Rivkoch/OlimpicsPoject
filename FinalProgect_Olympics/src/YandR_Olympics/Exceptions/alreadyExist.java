//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.Exceptions;

public class alreadyExist extends Exception {
	public alreadyExist() {
		super("Can't create this team\n This team already exists for the current country and sport type!");
	}
}
