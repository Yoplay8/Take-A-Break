import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main
{

	public static void main(String[] args)
	{

		JFrame F_Window = Create_Window(200, 150, "Screen Blocker", false);
		
		F_Window.add(Design_Start(F_Window));
		
		F_Window.setVisible(true);

	}

	
	private static JFrame Create_Window(int Screen_Width, int Screen_Height, String Title, boolean Flag)
	{
		
		JFrame F_Window = new JFrame();
		F_Window.setSize(Screen_Width, Screen_Height);
		F_Window.setTitle(Title);
		F_Window.setResizable(false);
		F_Window.setLocationRelativeTo(null);
		F_Window.setAlwaysOnTop(Flag);
		
		if(Flag)
		{
		
			F_Window.setUndecorated(true);
			F_Window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		}
		else
			F_Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				

		return F_Window;
		
	}
	
	private static JPanel Design_Start(JFrame F_Window)
	{
		
		JLabel L_Header = new JLabel("Set Interval");
		JLabel  L_Mins = new JLabel("Minutes");
		JLabel L_Hours = new JLabel("Hours");
		
		JTextField TF_Mins = new JTextField(3);
		JTextField TF_Hours = new JTextField(3);
		
		JButton B_Start = new JButton("Start");
		B_Start.setPreferredSize(new Dimension(70, 20));
		
			
		TF_Hours.addKeyListener(new KeyListener()
		{
			
			String Temp_1 = "";

			
			@Override
			public void keyPressed(KeyEvent arg0) 
			{	
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				
				char Key_Pressed = arg0.getKeyChar();
				
				
				if(((Key_Pressed >= '0') && (Key_Pressed <= '9')) || (Key_Pressed == '\b'))
					Temp_1 = TF_Hours.getText();

				
				TF_Hours.setText(Temp_1);

			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
			}
			
			
		});
		
		TF_Mins.addKeyListener(new KeyListener()
		{
			
			String Temp_1 = "";

			
			@Override
			public void keyPressed(KeyEvent arg0) 
			{	
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				
				char Key_Pressed = arg0.getKeyChar();
				
				
				if(((Key_Pressed >= '0') && (Key_Pressed <= '9')) || (Key_Pressed == '\b'))
					Temp_1 = TF_Mins.getText();

				
				TF_Mins.setText(Temp_1);

			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
			}
			
			
		});
			
		B_Start.addActionListener(new ActionListener()
		{
			

			public void actionPerformed(ActionEvent e)
			{
				
				F_Window.setVisible(false);
				F_Window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				
				Dimension Screen_Size = Toolkit.getDefaultToolkit().getScreenSize();// Used to get the max size of a screen.
				
				JFrame F_Break_Window  = Create_Window(Screen_Size.width, Screen_Size.height, "Take A Break", true);
				int Hours = 0, Minutes = 0;
				
				
				F_Break_Window.setVisible(true);

				
				try
				{
					
					Hours = Integer.parseInt(TF_Hours.getText());
					
				}
				catch(Exception error)
				{
				}
						
				try
				{
					
					Minutes = Integer.parseInt(TF_Mins.getText());
					
				}
				catch(Exception error)
				{
				}
				
				
				Create_Intervals(F_Break_Window, (((Hours * 60) + Minutes) * 60), Screen_Size);
				
			}
			
		});
		

		return Place_Components(L_Header, L_Mins, L_Hours, TF_Mins, TF_Hours, B_Start);
		
	}
	
	private static JPanel Place_Components(JLabel L_Header, JLabel L_Mins, JLabel L_Hours, JTextField TF_Mins, JTextField TF_Hours, JButton B_Start)
	{
		
		JPanel P_Main_Panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints GBC_Grid_Bag = new GridBagConstraints();
		
		GBC_Grid_Bag.insets = new Insets(4, 2, 4, 0);
		
		int X = 0, Y = 0;
		
		
		GBC_Grid_Bag.gridwidth = 10;
		GBC_Grid_Bag.gridx = X;
		GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(L_Header, GBC_Grid_Bag);
		
		
		X++;
		Y++;
		GBC_Grid_Bag.gridwidth = 1;
		GBC_Grid_Bag.gridx = X;
		GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(L_Hours, GBC_Grid_Bag);
		
		X++;
		GBC_Grid_Bag.gridx = X;
		//GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(TF_Hours, GBC_Grid_Bag);
		
		X++;
		GBC_Grid_Bag.gridx = X;
		//GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(L_Mins, GBC_Grid_Bag);
		
		X++;
		GBC_Grid_Bag.gridx = X;
		//GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(TF_Mins, GBC_Grid_Bag);
		
		Y++;
		X = 0;
		GBC_Grid_Bag.gridwidth = 10;
		GBC_Grid_Bag.gridx = X;
		GBC_Grid_Bag.gridy = Y;
		
		P_Main_Panel.add(B_Start, GBC_Grid_Bag);
		
		return P_Main_Panel;
		
		
	}
	
	private static void Create_Intervals(JFrame F_Break_Window, int Interval, Dimension Screen_Size)
	{
		Timer Time_1 = new Timer("Timer");
		
		double Break_Percentage = .12;
		int Milliseconds = 1000;
		long Period =((Interval) * Milliseconds);
		long Delay = (long) (Period * Break_Percentage);

		
		TimerTask Task_1 = new TimerTask()
		{

			public void run()
			{

				JLabel Temp_Hold = Create_Background(Screen_Size);
				
				F_Break_Window.add(Temp_Hold);
				
				F_Break_Window.setVisible(true);
				
				try
				{
					
					TimeUnit.SECONDS.sleep(1);
					
				}
				catch (InterruptedException e)
				{
					
					e.printStackTrace();
					
				}
				
				F_Break_Window.remove(Temp_Hold);

			}
			
		};
		
		TimerTask Task_2 = new TimerTask()
		{
			
			public void run()
			{

				F_Break_Window.setVisible(false);

			}

		};
		
		
		Time_1.scheduleAtFixedRate(Task_2, 0, (Period + Delay));
		Time_1.scheduleAtFixedRate(Task_1, Period, (Period + Delay));
		
		
		try
		{
			
			F_Break_Window.addFocusListener(new FocusAdapter()
			{
				
				Robot Click = new Robot();

				public void focusLost(FocusEvent e)
				{

					if(F_Break_Window.isVisible())
					{

						Click.mouseMove(0, 0);
						Click.mousePress(MouseEvent.BUTTON1_MASK);
						Click.mouseRelease(MouseEvent.BUTTON1_MASK);
						
						F_Break_Window.toFront();
						F_Break_Window.requestFocus();
						
					}
					
				}
				
			});
			
		}
		catch (AWTException e)
		{
			
			e.printStackTrace();
			
		}
		
	}
	
	private static JLabel Create_Background(Dimension Screen_Size)
	{
		
		File Quotes_Folder = new File("Quotes");
		String[] Quotes = Quotes_Folder.list();
		
		Random Rand= new Random();
		int Index = Rand.nextInt((int) Quotes.length);
		
		Toolkit T_Tools = Toolkit.getDefaultToolkit();
		Image I_Background = T_Tools.getImage("Quotes/" + Quotes[Index]);
		
		
		I_Background = I_Background.getScaledInstance(Screen_Size.width, Screen_Size.height, Image.SCALE_DEFAULT);
		
		
		ImageIcon II_Icon = new ImageIcon(I_Background);
		JLabel L_Scaled_BG = new JLabel("", II_Icon, JLabel.CENTER);
		
		
		L_Scaled_BG.setBounds(0, 0, Screen_Size.width, Screen_Size.height);
		
		
		return L_Scaled_BG;
	}
	
}
