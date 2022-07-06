import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class CountdownTimer extends JFrame implements ActionListener {
	JFormattedTextField input;
	JLabel display;
	JButton startStop;
	JButton reset;
	int time = 10000;
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	String hoursString = String.format("%02d", hours);
	String minutesString = String.format("%02d", minutes);
	String secondsString = String.format("%02d", seconds);
	Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			hours = (time / 3600000);
			minutes = (time /60000) % 60;
			seconds = (time / 1000) % 60;
			hoursString = String.format("%02d", hours);
			minutesString = String.format("%02d", minutes);
			secondsString = String.format("%02d", seconds);
			display.setText(hoursString + ":" + minutesString + "." + secondsString);
			if (time == 0 ) {
				timer.stop();
				display.setText("Finished");
			}
			time -= 1000;
		}
		
	});
	
	boolean started = false;
	
	CountdownTimer() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(260, 200);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		input = new JFormattedTextField();
//		input.setPreferredSize(new Dimension(200, 50));
//		input.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		
		display = new JLabel();
		display.setText(hoursString + ":" + minutesString + "." + secondsString);
		display.setBounds(20, 20, 220, 60);
		display.setFont(new Font("Monospace", Font.PLAIN, 30));
		display.setHorizontalAlignment(JLabel.CENTER);
		display.setBackground(Color.yellow);
		display.setOpaque(true);
		
		startStop = new JButton("Start");
		startStop.setBounds(20, 100, 100, 50);
		startStop.setBackground(Color.GREEN);
		startStop.setOpaque(true);
		startStop.addActionListener(this);
		
		reset = new JButton("Reset");
		reset.setBounds(140, 100, 100, 50);
		reset.setBackground(Color.BLUE);
		reset.setOpaque(true);
		reset.addActionListener(this);
		
		this.add(display);
//		this.add(input);
		this.add(startStop);
		this.add(reset);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startStop) {
			if (started == false) {
				started = true;
				startStop.setText("Stop");
				start();
			} else {
				started = false;
				startStop.setText("Start");
				stop();
			}
		}
		if (e.getSource() == reset) {
			started = false;
			startStop.setText("Start");
			stop();
			reset();
		}
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		time = 0;
		hours = 0;
		minutes = 0;
		seconds = 0;
		hoursString = String.format("%02d", hours);
		minutesString = String.format("%02d", minutes);
		secondsString = String.format("%02d", seconds);
		display.setText(hoursString + ":" + minutesString + "." + secondsString);
	}

}
