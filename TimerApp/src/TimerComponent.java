import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerComponent extends CountDown implements ActionListener {
	protected TitleComponent titleComponent;
	protected JPanel panel;
	protected JTextField textFieldSeconds;
	protected JButton statusButton;
	protected JButton resetButton;

	TimerComponent() {
		super();
		
		this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
		
		this.textFieldSeconds = new JTextField();
        this.textFieldSeconds.setText("0");
        this.textFieldSeconds.setPreferredSize(new Dimension(80, 40));		
		
		this.statusButton = new JButton();
        this.statusButton.setText("Start");
        this.statusButton.setFocusable(false);
		this.statusButton.addActionListener(this);
        this.statusButton.setPreferredSize(new Dimension(80, 40));
		
		this.resetButton = new JButton();
		this.resetButton.setText("Reset");
		this.resetButton.setFocusable(false);
        this.resetButton.addActionListener(this);
        this.resetButton.setPreferredSize(new Dimension(80, 40));
		
        this.panel.add(this.textFieldSeconds);
        this.panel.add(this.statusButton);
        this.panel.add(this.resetButton);
	}

	public void setTitleComponent(TitleComponent titleComponent) {
		this.titleComponent = titleComponent;
	}
	
    private long checkInput(String value) throws Exception {
    	final long YEAR_IN_MS = 31536000000L;
    	int stringLength = value.length();
	    long count = stringLength - 1;
	    long number = 0;
	
	    for(int vIndex = 0; vIndex < stringLength; vIndex++) {
	        long charCode = (long)value.charAt(vIndex);
	        if (charCode < 48 || charCode > 57) {
	        	throw new Exception("Only numbers may be used.");
	        } else {
	        	number += (charCode - 48) * (long)Math.pow(10, count);
	        }
	        count--;
	    }
	    
	    if (number <= 0) throw new Exception("You must input a value that exceeds 0 seconds.");
	    if (number >= YEAR_IN_MS) throw new Exception("You must not input a value that exceeds 365 days.");
	    
	    return number;
    }
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.statusButton) {
			if (this.statusButton.getText() == "Start") {
				try {
					this.value = checkInput(this.textFieldSeconds.getText());
					this.isRunning = true;
					UpdateTimer update = new UpdateTimer(this);
					Thread thread = new Thread(update);
					thread.start();
					this.titleComponent.disable();
				} catch (Exception exc) {
					System.out.println(exc.getMessage());
				}
			} else {
				this.statusButton.setText("Start");
				this.isRunning = false;
				this.titleComponent.enable();
			}
		} else if (event.getSource() == this.resetButton) {
			this.isRunning = false;
			this.resetTimer();
			this.textFieldSeconds.setText("" + this.value);
			this.titleComponent.enable();
		}
	}
}
