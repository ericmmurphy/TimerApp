import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

public class TimerUI implements ActionListener, MouseListener {
	protected final int buttonWidth = 122;
	protected final int buttonHeight = 30;
	protected final int panelWidth = buttonWidth * 2 + 20;
	protected final int panelHeight = 40;
	protected final int timerHeight = 90;
	protected final int frameWidth = 275;
	protected boolean deleteMode;
	protected LinkedList<Timer> timers;
	protected JFrame frame;
	protected JPanel panel;
	protected JButton addButton;
	protected JButton deleteButton;
	
	
	TimerUI() {
		this.deleteMode = false;
		this.timers = new LinkedList<>();
		this.frame = new JFrame();
		this.panel = new JPanel();
		this.addButton = new JButton();
		this.deleteButton = new JButton();
		
		this.setAddDeletePanel();
		this.setFrameValues();
	}
	
	public void setFrameValues() {
		//int frameWidth = 275;
		//int timerHeight = 90;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.frame.setResizable(false);
	    this.frame.setSize(frameWidth, (this.timers.size() * timerHeight) + 80);
	    this.frame.setLayout(null);
	    this.frame.setVisible(true);
	    this.frame.add(this.panel);
	}
	
	public void setAddDeletePanel() {
		//int buttonWidth = 122;
		//int buttonHeight = 30;
		//int panelWidth = buttonWidth * 2 + 20;
		//int panelHeight = 40;
		//int timerHeight = 90;
		
		this.addButton.setText("Add Timer");
		this.addButton.setFocusable(false);
	    this.addButton.setBounds(5, 5, buttonWidth, buttonHeight);
	    this.addButton.setLayout(null);
		this.addButton.addActionListener(this);
	    
		this.deleteButton.setText("Delete Timer");
		this.deleteButton.setFocusable(false);
	    
		if (this.timers.size() > 0) this.deleteButton.setEnabled(true);
	    else this.deleteButton.setEnabled(false);
		
		this.deleteButton.setBounds(6 + buttonWidth + 5, 5, buttonWidth, buttonHeight);
	    this.deleteButton.setLayout(null);
	    this.deleteButton.addActionListener(this);
	    		
	    this.panel.setBounds(0, this.timers.size() * timerHeight, panelWidth, panelHeight);
		this.panel.setLayout(null);
		this.panel.add(this.addButton);
		this.panel.add(this.deleteButton);
	}
	
	public void refreshFrameValues() {
		this.frame.remove(this.panel);
		
		this.removeAddDeletePanel();
		this.addTimer();
		this.setAddDeletePanel();
		this.setFrameValues();		
	}
	
	public void removeAddDeletePanel() {
		this.addButton.removeActionListener(this);
		this.deleteButton.removeActionListener(this);
		this.panel.remove(this.addButton);
		this.panel.remove(this.deleteButton);
	}
	
	public void addTimer() {
		//int timerHeight = 90;
		Timer timer = new Timer();
		timer.mainPanel.setBounds(0, (this.timers.size()) * timerHeight, 260, timerHeight);
		this.timers.add(timer);
		this.frame.add(timer.mainPanel);
	}
	
	public void removeTimerComponents(Timer timer) {
		timer.timerComponent.resetTimer(); // Ends running thread

		timer.timerComponent.panel.remove(timer.timerComponent.resetButton);
		timer.timerComponent.panel.remove(timer.timerComponent.statusButton);
		timer.timerComponent.panel.remove(timer.timerComponent.textFieldSeconds);
		timer.mainPanel.remove(timer.timerComponent.panel);
		
		timer.titleComponent.titlePanel.remove(timer.titleComponent.textField);
		timer.mainPanel.remove(timer.titleComponent.titlePanel);
		
		this.frame.remove(timer.mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.addButton) {
			this.refreshFrameValues(); // Includes adding new Timer to the frame
		} else if (event.getSource() == this.deleteButton) {
			System.out.println("Clicked");
			
			//Timer currentTimer;
			if (!this.deleteMode) {
				if (this.timers.size() > 0) {
					this.deleteMode = true;
					this.deleteButton.setText("Cancel");
					this.addButton.setEnabled(false);
				} 

				// Iterate through list and add mouseListener
				for (Timer currentTimer : this.timers) {
					currentTimer.mainPanel.addMouseListener(this);
				}
			} else {
				this.deleteMode = false;
				this.deleteButton.setText("Delete Timer");
				this.addButton.setEnabled(true);

				// Iterate through list and remove mouseListener
				for (Timer currentTimer : this.timers) {
					currentTimer.mainPanel.removeMouseListener(this);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		//int buttonWidth = 122;
		//int buttonHeight = 30;
		//int panelWidth = buttonWidth * 2 + 20;
		//int panelHeight = 40;
		//int timerHeight = 90;
		//int frameWidth = 275;
		
		int count = 0;
		int foundIndex = 0;
		boolean locatedTimer = false;
		// Iterate through list
		for (Timer currentTimer : this.timers) {
			if (event.getSource() == currentTimer.mainPanel) {
				locatedTimer = true;
				foundIndex = count;
				
				this.deleteMode = false;
				this.deleteButton.setText("Delete Timer");
				this.addButton.setEnabled(true);
				
				currentTimer.mainPanel.removeMouseListener(this);
				this.removeTimerComponents(currentTimer);
				System.out.println("Timer found: " + currentTimer);
			} else {
				currentTimer.mainPanel.removeMouseListener(this);
				currentTimer.mainPanel.setBounds(0, count * timerHeight, 260, timerHeight);
				count++;
			}
		}
		this.panel.setBounds(0, count * timerHeight, panelWidth, panelHeight);
		this.frame.setSize(frameWidth, (count * timerHeight) + 80);
		
		if (locatedTimer) this.timers.remove(foundIndex);
		if (this.timers.size() == 0) this.deleteButton.setEnabled(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
