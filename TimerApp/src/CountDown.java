public class CountDown {
	protected long value;
	protected boolean isRunning;
	
	CountDown() {
		this.value = 0;
		this.isRunning = false;
	}
	
	public void setValue(long val) {
		this.value = val;
	}

	public void resetTimer() {
		this.value = 0;
	}
}
