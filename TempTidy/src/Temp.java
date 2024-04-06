import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Temp implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;
	private JButton cleanButton,exitButton;
	private JLabel fls;
	private String tempDirPath = System.getProperty("java.io.tmpdir");
	private Color Bg = new Color(15, 15, 15);
	private Color Pr = new Color(25, 51, 43);
	private Color As = new Color(0, 129, 112);
	private Color Tx = new Color(255, 255, 255);
	
	Temp(){

		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) ss.getHeight();
		int width = (int) ss.getWidth();
		
		
		panel = new JPanel();
		panel.setLayout(null);
		frame = new JFrame();
		frame.setBackground(Bg);
		frame.setUndecorated(true);
		frame.setTitle("Temp Cleaner");
		frame.setBounds((width/2)-175,(height/2)-75,350,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setBackground(Bg);
		
		panel.setLayout(null);
		
		cleanButton = new JButton("Clear Temp");
		cleanButton.setBounds(25,25,300,35);
		cleanButton.addActionListener(this);
		cleanButton.setBorder(null);
		cleanButton.setFocusPainted(false);
		cleanButton.setBackground(As);
		cleanButton.setForeground(Pr);
		panel.add(cleanButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(25,70,300,35);
		exitButton.addActionListener(this);
		exitButton.setBorder(null);
		exitButton.setFocusPainted(false);
		exitButton.setBackground(As);
		exitButton.setForeground(Pr);
		panel.add(exitButton);
		
		fls = new JLabel("Click 'Clear Temp' to clear your temp directories!");
		fls.setForeground(Tx);
		fls.setHorizontalAlignment(SwingConstants.CENTER);
		fls.setFont(new Font("Arial", Font.PLAIN, 10));
		fls.setBounds(30,115,300,15);
		panel.add(fls);
		frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new Temp();
	}
	
	void purgeDirectory(File dir) {
	    for (File file: dir.listFiles()) {
	        if (file.isDirectory()) purgeDirectory(file);
	        fls.setText(file.getAbsolutePath());
	        file.delete();
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			frame.dispose();
		}
		
		if(e.getSource()==cleanButton) {
			File dir = new File(tempDirPath);
			purgeDirectory(dir);
			File dirTEMP = new File("C:\\Windows\\Temp");
			purgeDirectory(dirTEMP);
			fls.setText("Cleared Temp Folders!");
		}
	}
}
