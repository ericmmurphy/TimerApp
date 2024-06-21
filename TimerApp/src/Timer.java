import java.awt.*;
import javax.swing.*;

public class Timer {
	protected JPanel mainPanel;
	protected TitleComponent titleComponent;
	protected TimerComponent timerComponent;
	
	Timer() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridLayout(2, 1));
		
		this.titleComponent = new TitleComponent();
		this.timerComponent = new TimerComponent();
		this.linkComponents();
		
		this.mainPanel.add(this.titleComponent.titlePanel);
		this.mainPanel.add(this.timerComponent.panel);
	}
	
	private void linkComponents() {
		this.titleComponent.setTimerComponent(this.timerComponent);
		this.timerComponent.setTitleComponent(this.titleComponent);
	}
}
