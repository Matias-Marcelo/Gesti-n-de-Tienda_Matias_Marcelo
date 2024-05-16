package exception;

public class LimitLoginException extends Exception {
	private static final long serialVersionUID = 1L;
	private int counter;
	//Attribute to count errors.
	public LimitLoginException(int counter) {
		
		this.counter = counter;
		
	}

	//Method toString to print results:
	@Override
	public String toString() {
		
		return "El número de intentos de login ha superado el límite " +this.counter + " permitido";
		
	}
	
	
}
