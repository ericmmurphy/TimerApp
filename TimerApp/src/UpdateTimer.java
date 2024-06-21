import java.util.Date;

public class UpdateTimer implements Runnable {
	private TimerComponent timerComponent;
	
	UpdateTimer(TimerComponent timerComponent) {
		this.timerComponent = timerComponent;
	}
	
	@Override
	public void run() {
		long prevTime = new Date().getTime();
		long currTime = new Date().getTime();
		long elapsedTime;
		long newValue;
		
		this.timerComponent.statusButton.setText("Pause");
		this.timerComponent.textFieldSeconds.setFocusable(false);
		while (timerComponent.isRunning && timerComponent.value > 0) {
			elapsedTime = currTime - prevTime;
			newValue = this.timerComponent.value - elapsedTime;
			
			if (newValue < 0) {
				this.timerComponent.setValue(0);	
			} else {
				this.timerComponent.setValue(newValue);
			}
			
			System.out.println("Days: " + ((newValue / (1000 * 60 * 60 * 24)) % 365)
					+ " | Hours: " + ((newValue / (1000 * 60 * 60)) % 24) 
					+ " | Minutes: " + ((newValue / (1000 * 60)) % 60) 
					+ " | Seconds: " + (newValue / 1000) % 60);
			this.timerComponent.textFieldSeconds.setText("" + this.timerComponent.value);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			prevTime = currTime;
			currTime = new Date().getTime();
		}
		this.timerComponent.statusButton.setText("Start");
		this.timerComponent.textFieldSeconds.setFocusable(true);
		this.timerComponent.titleComponent.enable();
	}
}