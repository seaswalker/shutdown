package exception;

/**
 * �����ʱ�䳬��12Сʱ������
 * @author skywalker
 *
 */
public class TimeExceedLimitException extends Exception {

	private static final long serialVersionUID = -3176645532402479691L;
	
	public TimeExceedLimitException(String message) {
		super(message);
	}

}
