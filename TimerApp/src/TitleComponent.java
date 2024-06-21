import java.awt.*;
import javax.swing.*;

public class TitleComponent {
	protected TimerComponent timer;
	protected JPanel titlePanel;
	protected JTextField textField;
	
	TitleComponent() {
		this.titlePanel = new JPanel();
		this.titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		this.textField = new JTextField();
		this.textField.setText("Untitled");
		this.textField.setPreferredSize(new Dimension(80, 40));
		
		this.titlePanel.add(this.textField);
	}
	
	public void setTimerComponent(TimerComponent timerComponent) {
		this.timer = timerComponent;
	}
	
	public void disable() {
		this.textField.setEditable(false);
		this.textField.setFocusable(false);
	}
	
	public void enable() {
		this.textField.setEditable(true);
		this.textField.setFocusable(true);
	}	
}