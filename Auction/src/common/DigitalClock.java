package common;

import java.util.Date;
import java.util.TimerTask;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class DigitalClock {
	
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");

	public DigitalClock() {
		final java.util.Timer tmr = new java.util.Timer();
		tmr.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				try {

					System.out.print("\r" + sdf.format(new Date()));
					
					finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 1000);
	}

	public static void main(String args[]) {
		new DigitalClock();
	}
}