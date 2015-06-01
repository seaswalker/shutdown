package service;

import java.io.IOException;

import util.StringUtil;
import exception.TimeExceedLimitException;

/**
 * 逻辑
 * @author skywalker
 *
 */
public class Service {
	
	//记录用户输入的时间
	private int hour;
	private int min;

	/**
	 * 启动定时任务
	 */
	public void start(String hour, String min) throws TimeExceedLimitException, IOException {
		if(!StringUtil.isValid(hour) && !StringUtil.isValid(min)) {
			throw new TimeExceedLimitException("请输入合法的值");
		}
		this.hour = StringUtil.isValid(hour) ? Integer.parseInt(hour) : 0;
		this.min = StringUtil.isValid(min) ? Integer.parseInt(min) : 0;
		check();
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "shutdown", "-s", "-t", getSeconds().toString());
		processBuilder.start();
	}
	
	/**
	 * 取消定时任务
	 */
	public void cancel() {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "shutdown", "-a");
		try {
			processBuilder.start();
		} catch (IOException e) {
		}
	}
	
	/**
	 * 检测输入的值是否合理
	 */
	private void check() throws TimeExceedLimitException {
		if(hour < 0 || hour > 12 || min < 0 || min > 60 || (hour == 12 && min > 0)) {
			throw new TimeExceedLimitException("不要超过12小时！");
		}
	}
	
	/**
	 * 把输入的小时以分钟转为秒
	 */
	private Long getSeconds() {
		return new Long(hour * 3600 + min * 60);
	}
	
}
