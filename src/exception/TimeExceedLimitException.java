package exception;

/**
 * 输入的时间超出12小时的限制
 * @author skywalker
 *
 */
public class TimeExceedLimitException extends Exception {

	private static final long serialVersionUID = -3176645532402479691L;
	
	public TimeExceedLimitException(String message) {
		super(message);
	}

}
