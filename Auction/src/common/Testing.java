package common;

import java.util.*;

class Testing {
	
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM dd yyyy hh:mm:ss");

	public Testing() {
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
		new Testing();
	}
}
