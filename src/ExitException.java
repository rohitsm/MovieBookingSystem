package MovieBookingSystem;


public class ExitException extends Exception{
	public ExitException(){
		super("Exit Request Detected.");
	}
	
	public ExitException(String message){
		super(message);
	}
}

