package jeopardy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Jeopardy {

	public static JPanel board; 
	public static JPanel info;
	public static JPanel panel = new JPanel();
	public static JButton [] btnDisplay; //Array for buttons
	public static JButton reset;
	public static JLabel player1Wins;
	public static JLabel player2Wins;
	public static String player1; //names
	public static String player2;
	public static String answer;
	public static int scorePlayer1; 
	public static int scorePlayer2; 
	public static int score;
	public static int turn;

	private static void guiApp()
	{
		JPanel panelMenu = new JPanel();
		JRadioButton[] rMenu = new JRadioButton[4];
		rMenu[0] = new JRadioButton("1. Rules");
		rMenu[1] = new JRadioButton("2. Enter Names");
		rMenu[2] = new JRadioButton("3. Play");
		rMenu[3] = new JRadioButton("4. Exit");
		ButtonGroup menuGroup = new ButtonGroup();
		for(int x = 0; x<4; x++) {
			menuGroup.add(rMenu[x]);
			panelMenu.add(rMenu[x]);
		}

		while (rMenu[3].isSelected() == false)
		{

			JOptionPane.showMessageDialog(null, panelMenu);



			//Menu to display rules, enter player names, play game and exit menu


			if (rMenu[0].isSelected() == true){ 
				JOptionPane.showMessageDialog(null, "1. Two player game. \n" + "2. Pick a category and a point value by clicking the respective buttons. \n"
						+ "3. Answer within text box for the question. \n" + "4. Enter the correct responses with the given clues. (spelling matters, captilization doesn't) \n"
						+ "5. Click the 'Restart' button to start all over.\n" + "6. Player with the most addScore wins.", "Rules",
						JOptionPane.INFORMATION_MESSAGE);

			}
			else if (rMenu[1].isSelected() == true){ //enter names
				player1 = JOptionPane.showInputDialog("Player One's name: ");
				JOptionPane.showMessageDialog(null, "Hello, " + player1);
				player2 = JOptionPane.showInputDialog("Player Two's name: ");
				JOptionPane.showMessageDialog(null, "Hello, " + player2);
			}
			else if (rMenu[2].isSelected() == true){
				while (player1 == null || player2 == null){ 
					JOptionPane.showMessageDialog(null, "Please enter names for Player One or Player Two: ");
					player1 = JOptionPane.showInputDialog("Player One's name: ");
					JOptionPane.showMessageDialog(null, "Hello, " + player1);
					player2 = JOptionPane.showInputDialog("Player Two's name: ");
					JOptionPane.showMessageDialog(null, "Hello, " + player2);
				}



				//Start Game
				JFrame frame = new JFrame("Canadian Jeopardy"); 
				frame.setSize(1100, 1000); 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel.setLayout(new BorderLayout(5,5));

				board = new JPanel(new GridLayout(4, 3,15,5)); 

				btnDisplay = new JButton[12];

				ButtonHandler onClick = new ButtonHandler();

				for (int i = 0; i < btnDisplay.length; i++){ 
					btnDisplay[i] = new JButton(""+i);
					fillButtons(i);

					
					if(i > 2){
						btnDisplay[i].addActionListener(onClick); 
					}

					btnDisplay[i].setSize(12,12);
					categories(i); 
					btnDisplay[i].setForeground(Color.WHITE);
					board.add(btnDisplay[i]); 
				}// End for

				panel.add(board, BorderLayout.CENTER);
				info = new JPanel(new GridLayout());
				reset = new JButton("RESET"); 
				reset.addActionListener(onClick); 
				player1Wins = new JLabel(player1 + "'s Score: ");
				player2Wins = new JLabel(player2 + "'s Score: ");
				info.add(player1Wins); 
				info.add(reset);
				info.add(player2Wins);
				panel.add(info, BorderLayout.PAGE_END);
				
				frame.add(panel);
				frame.setVisible(true);


			}//End else if
		}

	}

	private static class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String whichOne = e.getActionCommand();
			
			if ( whichOne.equals("RESET")){
				resetButtons(); 
			}
			else{
				int pos = Integer.parseInt(whichOne);
				System.out.println(pos);
				if (turn % 2 == 0){
					questions(pos, score);
					scorePlayer1 += score; 
					player1Wins.setText(player1 + "'s Score: $" + scorePlayer1); 
					turn++;
				}
				else if (turn % 2 == 1){
					questions(pos, score);
					scorePlayer2 += score;
					player2Wins.setText(player2 + "'s Score: $" + scorePlayer2); 
					turn++;
				}
			}
			ending();

		}
	}
	
	
	public static void categories(int i){		
		if(i == 0 || i == 1 || i == 2 ){
			btnDisplay[i].setBackground(Color.WHITE);
			btnDisplay[i].setEnabled(true);
		}
	}
	
	public static void fillButtons(int i){			
		switch (i){								//case instead of if for simplicity
		case 0: 
			ImageIcon im = new ImageIcon(new ImageIcon("Ontario.png").getImage().getScaledInstance(300,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(im);
		break;
		case 1: 
			ImageIcon ima = new ImageIcon(new ImageIcon("oscars.jpg").getImage().getScaledInstance(300,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(ima);
		break;
		case 2: 
			ImageIcon imag = new ImageIcon(new ImageIcon("maple.jpg").getImage().getScaledInstance(300,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(imag);
		break;
		
		/**********************/
		
		
		case 3: 
			ImageIcon image3 = new ImageIcon(new ImageIcon("geography100.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image3);
		break;
		case 6: 
			ImageIcon image4 = new ImageIcon(new ImageIcon("geography200.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image4);
		break;
		case 9: 
			ImageIcon image5 = new ImageIcon(new ImageIcon("geography300.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image5);
		break;
		
		
/************************/
		
	
		case 4: 
			ImageIcon image9 = new ImageIcon(new ImageIcon("actor100.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image9);
		break;
		case 7: 
			ImageIcon image10 = new ImageIcon(new ImageIcon("actor200.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image10);
		break;
		case 10: 
			ImageIcon image11 = new ImageIcon(new ImageIcon("actor300.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image11);
		break;
		
/**********************/
		
		
		case 5: 
			ImageIcon image15 = new ImageIcon(new ImageIcon("flag100.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image15);
		break;
		case 8: 
			ImageIcon image16 = new ImageIcon(new ImageIcon("flag200.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image16);
		break;
		case 11: 
			ImageIcon image17 = new ImageIcon(new ImageIcon("flag300.png").getImage().getScaledInstance(250,150, Image.SCALE_DEFAULT));
			btnDisplay[i].setIcon(image17);
		break;
		
		
/************************/
		
		}//End switch for pics
	}
	
	
	
	
	public static void ending(){
	
		if ( btnDisplay[3].isEnabled() == false && btnDisplay[4].isEnabled() == false && btnDisplay[5].isEnabled() == false && btnDisplay[6].isEnabled() == false && btnDisplay[7].isEnabled() == false && btnDisplay[8].isEnabled() == false &&
			btnDisplay[9].isEnabled() == false && btnDisplay[10].isEnabled() == false && btnDisplay[11].isEnabled() == false  ){
				if (scorePlayer1 > scorePlayer2){
					JOptionPane.showMessageDialog(null, player1 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
					disableButtons(); 
				}
				else if (scorePlayer1 < scorePlayer2){
					JOptionPane.showMessageDialog(null, player2 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
					disableButtons(); 
				}else {
					JOptionPane.showMessageDialog(null, "There is the no WINNER :( ","NO Winner",JOptionPane.INFORMATION_MESSAGE);
					disableButtons();
				}
		}
	}
	
	/*******************************************************************************************************************************************/
	
	
	
	public static int questions(int pos, int score){
	
		switch (pos){
	
		case 3: btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>Number of Canadian provinces that do not have marine coastlines.<html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Geography for $100 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("2")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 2");
					score = wrongResponse(pos);
				}
			break;
			/*******************************************************************************/
		case 9:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>The Canadian province that does not adopt Daylight Saving Time.<html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Geography for $300 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("Saskatchewan")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Saskatchewan");
					score = wrongResponse(pos);
				}
				break;
			/***********************************************************************************/
		case 6: btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>Number of great lakes within the canadian border.<html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Geography for $200 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("gillyweed") || answer.equalsIgnoreCase("4")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 4");
					score = wrongResponse(pos);
				}
			break;
		
		/*************************************************************************************************************************************************************************/
		
		case 4:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>Lead Deadpool actor. </html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Actor/Actress for $100 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("Ryan Reynolds")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Ryan Reynolds");
					score = wrongResponse(pos);
				}
			break;
			/********************************************************************************/
		case 7:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>Jim Carrey was born in this province </html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Actor/Actress $200 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("Ontario")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Ontario");
					score = wrongResponse(pos);
				}
			break;
			/*******************************************************************************/
		case 10:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
				btnDisplay[pos].setText("<html>This actor was born in London, Ontario. Began as a child star on the Disney Channel.</html>");
				btnDisplay[pos].setEnabled(false);
				btnDisplay[pos].setBackground(Color.BLACK);
				btnDisplay[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Canadian Actor/Actress for $300 \nWhat is: ");
				answer = answer.toLowerCase();
				
				if (answer.equalsIgnoreCase("Ryan Gosling")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = addScore(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Ryan Gosling");
					score = wrongResponse(pos);
				}
			break;
		
			/*************************************************************************************************************************************************************************/
			
			case 5:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
					btnDisplay[pos].setText("<html> Number of Points on the maple leaf on the flag.</html>");
					btnDisplay[pos].setEnabled(false);
					btnDisplay[pos].setBackground(Color.BLACK);
					btnDisplay[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Canada for $100 \nWhat is: ");
					answer = answer.toLowerCase();
					
					if (answer.equalsIgnoreCase("11")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = addScore(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 11");
						score = wrongResponse(pos);
					}
				break;
				/********************************************************************************/
			case 8:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
					btnDisplay[pos].setText("<html>Canada's national sport.</html>");
					btnDisplay[pos].setEnabled(false);
					btnDisplay[pos].setBackground(Color.BLACK);
					btnDisplay[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Canada for $200 \nWhat is: ");
					answer = answer.toLowerCase();
					
					if (answer.equalsIgnoreCase("Hockey") || answer.equalsIgnoreCase("Lacrosse")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = addScore(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Hockey or Lacrosse");
						score = wrongResponse(pos);
					}
				break;
				/*******************************************************************************/
			case 11:btnDisplay[pos].setFont(new Font("Arial", Font.BOLD,11));
					btnDisplay[pos].setText("<html>Home to the first North American YMCA.</html>");
					btnDisplay[pos].setEnabled(false);
					btnDisplay[pos].setBackground(Color.BLACK);
					btnDisplay[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Canada for $300 \nWhat is: ");
					answer = answer.toLowerCase();
					
					if (answer.equalsIgnoreCase("Montreal")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = addScore(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Montreal");
						score = wrongResponse(pos);
					}
				break;
				
		/*************************************************************************************************************************************************************************/
		}//End switch
		return score;
	}
	
	
	public static int addScore(int pos){
		//Method to set what each button is worth score wise
		if (pos == 3 || pos == 4 || pos == 5){
			score = 100;
		}
		else if (pos == 6 || pos == 7 || pos == 8){
			score = 200;
		}
		else if (pos == 9 || pos == 10 || pos == 11){
			score = 300;
		}
		return score;
	}
	
	/*******************************************************************************************************************************************/
	public static int wrongResponse(int pos){
		//Method to display a score of ) if answer is incorrect
		if ( pos == 3 || pos == 4 || pos == 5 || pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11 || pos == 12){
			score = 0;
		}//End if
		
		return score;
	}
	
	/*******************************************************************************************************************************************/
	
	public static void resetButtons(){
		for (int i = 0; i < btnDisplay.length; i++){
			btnDisplay[i].setEnabled(true);
			btnDisplay[i].setText("" + i);
			categories(i);
			fillButtons(i);
			btnDisplay[i].setForeground(Color.WHITE);
			if (i>2)
			btnDisplay[i].setBackground(null);
			scorePlayer1 = 0;
			scorePlayer2 = 0;
			player1Wins.setText(player1 + "'s Score: " + scorePlayer1);
			player2Wins.setText(player2 + "'s Score: " + scorePlayer2);
		}
	}
/**************************************************************************************************************************************/
	public static void disableButtons(){
		for (int i = 0; i < btnDisplay.length; i++){
			btnDisplay[i].setEnabled(false);
			btnDisplay[i].setText("" + i);
			btnDisplay[i].setForeground(Color.BLACK);
			btnDisplay[i].setForeground(Color.BLACK);
		}//End for
	}//End disableBoard	
	
	
	public static void main (String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				guiApp();
			}
		}
		);
	}

}
