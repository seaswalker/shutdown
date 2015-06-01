package service;

import java.io.IOException;

import util.StringUtil;
import exception.TimeExceedLimitException;

/**
 * �߼�
 * @author skywalker
 *
 */
public class Service {
	
	//��¼�û������ʱ��
	private int hour;
	private int min;

	/**
	 * ������ʱ����
	 */
	public void start(String hour, String min) throws TimeExceedLimitException, IOException {
		if(!StringUtil.isValid(hour) && !StringUtil.isValid(min)) {
			throw new TimeExceedLimitException("������Ϸ���ֵ");
		}
		this.hour = StringUtil.isValid(hour) ? Integer.parseInt(hour) : 0;
		this.min = StringUtil.isValid(min) ? Integer.parseInt(min) : 0;
		check();
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "shutdown", "-s", "-t", getSeconds().toString());
		processBuilder.start();
	}
	
	/**
	 * ȡ����ʱ����
	 */
	public void cancel() {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "shutdown", "-a");
		try {
			processBuilder.start();
		} catch (IOException e) {
		}
	}
	
	/**
	 * ��������ֵ�Ƿ����
	 */
	private void check() throws TimeExceedLimitException {
		if(hour < 0 || hour > 12 || min < 0 || min > 60 || (hour == 12 && min > 0)) {
			throw new TimeExceedLimitException("��Ҫ����12Сʱ��");
		}
	}
	
	/**
	 * �������Сʱ�Է���תΪ��
	 */
	private Long getSeconds() {
		return new Long(hour * 3600 + min * 60);
	}
	
}
