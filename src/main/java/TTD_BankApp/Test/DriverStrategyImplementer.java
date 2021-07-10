package TTD_BankApp.Test;

public class DriverStrategyImplementer {

	public static DriverStrategy chooseStrategy(String Strategy) {
		switch(Strategy)
		{
		case "chrome":
			return new chrome();
		default:
			return null;
		}
	}
}
