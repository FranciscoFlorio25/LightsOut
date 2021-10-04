package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class Board {
	
	private JToggleButton[][] matrixButtons;

	public Board() {
		initialize();
	}

	private void initialize() {

		frmLightsout = new JFrame();
		frmLightsout.setTitle("LightsOut");
		frmLightsout.setIconImage(Toolkit.getDefaultToolkit().getImage("media/nathan-jennings-VsPsf4F5Pi0-unsplash.jpg"));
		//frmLightsout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmLightsout.getContentPane().setLayout(null);
		frmLightsout.setVisible(true);
		frmLightsout.setResizable(false);
		frmLightsout.setBackground(Color.black);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		frmLightsout.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		panel.setBackground(Color.black);

		lblTittle = new JLabel("Lights Out");
		lblTittle.setForeground(Color.WHITE);
		lblTittle.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 30));
		lblTittle.setBounds(72, 35, 229, 49);
		panel.add(lblTittle);

		lblMode = new JLabel("Modo");
		lblMode.setForeground(Color.WHITE);
		lblMode.setBounds(48, 130, 46, 14);
		panel.add(lblMode);

		btnPlay = new JButton("Jugar");
		btnPlay.setBounds(105, 182, 89, 23);
		panel.add(btnPlay);

		txtVictory = new JTextField();
		txtVictory.setHorizontalAlignment(SwingConstants.CENTER);
		txtVictory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVictory.setBackground(Color.BLACK);
		txtVictory.setForeground(Color.WHITE);
		txtVictory.setVisible(false);
		txtVictory.setEditable(false);
		txtVictory.setBounds(105, 11, 199, 20);
		panel.add(txtVictory);
		txtVictory.setColumns(10);

		btnNuevo.setBounds(173, 418, 128, 23);
		panel.add(btnNuevo);
		btnNuevo.setVisible(false);

		txtMovements = new JTextField();
		txtMovements.setForeground(Color.WHITE);
		txtMovements.setBackground(Color.BLACK);
		txtMovements.setEditable(false);
		txtMovements.setText("0");
		txtMovements.setBounds(252, 375, 86, 20);
		panel.add(txtMovements);
		txtMovements.setColumns(10);
		txtMovements.setVisible(false);

		lblMov = new JLabel("Movimientos");
		lblMov.setForeground(Color.WHITE);
		lblMov.setBounds(130, 378, 89, 14);
		panel.add(lblMov);

		chkboxSound = new JCheckBox("Sonido");
		chkboxSound.setForeground(Color.WHITE);
		chkboxSound.setBackground(Color.BLACK);
		chkboxSound.setBounds(180, 91, 97, 23);
		panel.add(chkboxSound);

		btnReset.setBounds(179, 430, 89, 23);
		panel.add(btnReset);
		btnReset.setVisible(false);

		chooseMode.add(modo4x4);
		modo4x4.setForeground(Color.WHITE);
		modo4x4.setBackground(Color.BLACK);
		modo4x4.setSelected(true);
		modo4x4.setBounds(114, 126, 50, 23);
		panel.add(modo4x4);

		chooseMode.add(modo5x5);
		modo5x5.setForeground(Color.WHITE);
		modo5x5.setBackground(Color.BLACK);
		modo5x5.setBounds(173, 126, 50, 23);
		panel.add(modo5x5);

		chooseMode.add(modo6x6);
		modo6x6.setForeground(Color.WHITE);
		modo6x6.setBackground(Color.BLACK);
		modo6x6.setBounds(238, 126, 50, 23);
		panel.add(modo6x6);

		btnBack.setBounds(10, 7, 70, 23);
		panel.add(btnBack);
		btnBack.setVisible(false);

		lblMov.setVisible(false);

		frmLightsout.setBounds(100, 100, 352, 268);
		frmLightsout.setLocationRelativeTo(null);
		chkbxDifficulty.setForeground(Color.WHITE);
		chkbxDifficulty.setBackground(Color.BLACK);

		chkbxDifficulty.setSelected(true);
		chkbxDifficulty.setBounds(35, 91, 144, 23);
		panel.add(chkbxDifficulty);
		chkbxDifficulty.setToolTipText("Deseleccionar para ingresar manualmente la dificultad");

		txtRandomMovementsOfNewBoard = new JTextField();
		txtRandomMovementsOfNewBoard.setForeground(Color.WHITE);
		txtRandomMovementsOfNewBoard.setBackground(Color.BLACK);
		txtRandomMovementsOfNewBoard.setBounds(215, 154, 86, 20);
		panel.add(txtRandomMovementsOfNewBoard);
		txtRandomMovementsOfNewBoard.setColumns(10);
		txtRandomMovementsOfNewBoard.setText("1");
		txtRandomMovementsOfNewBoard.setToolTipText("Ingrese la cantidad de movimientos para resolver el tablero");

		lblNovementSelection.setForeground(Color.WHITE);
		lblNovementSelection.setBounds(49, 157, 145, 14);
		txtRandomMovementsOfNewBoard.setText("1");
		txtRandomMovementsOfNewBoard.setEditable(false);

		panel.add(lblNovementSelection);

	}

	public void setButtonsLayout() // Sets the toggle buttons position on the frame for all game modes.
									 
	{
		int positionX = 35;
		int positionY = 114;

		for (int i = 0; i < matrixButtons.length; i++) {
			for (int j = 0; j < matrixButtons.length; j++) {
				panel.add(matrixButtons[i][j]);
				matrixButtons[i][j].setBounds(positionX, positionY, 59, 45);
				matrixButtons[i][j].setSelectedIcon(new ImageIcon("media/on.png"));
				matrixButtons[i][j].setSelectedIcon(new ImageIcon("media/on.png"));
				matrixButtons[i][j].setIcon(new ImageIcon("media/off.png"));
				matrixButtons[i][j].setBackground(UIManager.getColor("Button.highlight"));
				matrixButtons[i][j].setVisible(true);

				positionX += 69;
			}
			positionY += 56;
			positionX = 35;
		}

	}

	public void createToggleButtonMatrix(int size) {
		matrixButtons = new JToggleButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrixButtons[i][j] = new JToggleButton("");

			}
		}

	}

	public void setGameLayout() {		//Enables the visibility of all the objects that are part of the game screen and disables all the objects that are part of the main menu.
		btnBack.setVisible(true);
		btnNuevo.setVisible(true);
		btnReset.setVisible(true);
		modo4x4.setVisible(false);
		modo5x5.setVisible(false);
		modo6x6.setVisible(false);
		btnPlay.setVisible(false);
		lblMov.setVisible(true);
		lblMode.setVisible(false);
		lblTittle.setVisible(false);
		txtMovements.setVisible(true);
		chkboxSound.setVisible(false);
		txtRandomMovementsOfNewBoard.setVisible(false);
		chkbxDifficulty.setVisible(false);
		lblNovementSelection.setVisible(false);

		// frame.setBounds(100, 100, 473, 506);
		txtMovements.setBounds(10, 40, 30, 20);
		btnNuevo.setBounds(150, 40, 128, 23);
		btnReset.setBounds(150, 80, 89, 23);
		lblMov.setBounds(50, 40, 89, 14);
	}

	public void setFrameSize(int gameSize) {
		if (gameSize == 4)
			frmLightsout.setBounds(100, 100, 350, 400);
		else if (gameSize == 5)
			frmLightsout.setBounds(100, 100, 410, 450);
		else
			frmLightsout.setBounds(100, 100, 483, 506);

		frmLightsout.setLocationRelativeTo(null);
	}

	public void sound(String audio) {
		File file = new File(audio);
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip;
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();

			// try and catch generados y necesarios para funcionamiento
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void enableButtons(boolean b) {
		for (JToggleButton[] ArrBtn : matrixButtons)
			for (JToggleButton btn : ArrBtn)
				btn.setEnabled(b);
		txtVictory.setVisible(!b);
	}

	public void victoryFx() {
		if (chkboxSound.isSelected())
			sound("media/little_robot_sound_factory_Jingle_Win_Synth_00.wav");
		txtVictory.setText("Ganaste!");
		txtVictory.setVisible(true);
	}

	public void resetMovementsCount() {
		txtMovements.setText("0");
	}

	public void increaseMovementsCount() {
		if (chkboxSound.isSelected())
			sound("media/Light-Switch-On-Off-05-c-FesliyanStudios.com.wav");
		txtMovements.setText(String.valueOf(Integer.parseInt(txtMovements.getText()) + 1));
	}

	public int getMatrixButtonSize() {
		int cont = 0;
		for (JToggleButton[] mb : matrixButtons) {
			for (JToggleButton tb : mb) {
				cont++;
			}
		}
		return cont;
	}

	public void errorMessageEmptyAndZero() {
		JOptionPane.showMessageDialog(null, "los movimientos deben ser 1 o superior", "InfoBox: ",
				JOptionPane.INFORMATION_MESSAGE);
		;
	}

	public void errorMessageNotValidNumber() {
		JOptionPane.showMessageDialog(null, "Los movimientos deben ser numeros positivos", "InfoBox: ",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void actionDefaultDificulty(boolean b) {
		if(b) {
		txtRandomMovementsOfNewBoard.setEditable(false);
		txtRandomMovementsOfNewBoard.setText("1");
		}
		else
			txtRandomMovementsOfNewBoard.setEditable(true);
	}
		
	// Setters y Getters-------------------------------------------

	public JToggleButton[][] getMatrixButtons() {
		return matrixButtons;
	}

	public JToggleButton getButtonFromMatrix(int i, int j) {
		return matrixButtons[i][j];
	}

	public JTextField getTxtRandomMovementsOfNewBoard() {
		return txtRandomMovementsOfNewBoard;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JTextField getTxtVictory() {
		return txtVictory;
	}

	public JButton getBtnNew() {
		return btnNuevo;
	}

	public boolean getRadial4x4() {
		return modo4x4.isSelected();
	}

	public boolean getRadial5x5() {
		return modo5x5.isSelected();
	}

	public boolean getRadial6x6() {
		return modo6x6.isSelected();
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JCheckBox getChkboxSound() {
		return chkboxSound;
	}

	public JFrame getFrame() {
		return frmLightsout;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JCheckBox getChkbxDifficulty() {
		return chkbxDifficulty;
	}

	public void setChkbxDifficulty(JCheckBox chkbxDifficulty) {
		this.chkbxDifficulty = chkbxDifficulty;
	}

	private JFrame frmLightsout;
	private JPanel panel;
	private JButton btnBack = new JButton("Atras");
	private JRadioButton modo6x6 = new JRadioButton("6x6");
	private JRadioButton modo5x5 = new JRadioButton("5x5");
	private JRadioButton modo4x4 = new JRadioButton("4x4");
	private JButton btnReset = new JButton("Reiniciar");
	private final JButton btnNuevo = new JButton("Nueva Partida");
	private JButton btnPlay;
	private JTextField txtMovements;
	private JTextField txtVictory;
	private JCheckBox chkboxSound;
	private JLabel lblMov;
	private JLabel lblMode;
	private JLabel lblTittle;
	private ButtonGroup chooseMode = new ButtonGroup();
	JCheckBox chkbxDifficulty = new JCheckBox("Dificultad Default");
	private JTextField txtRandomMovementsOfNewBoard;
	private final JLabel lblNovementSelection = new JLabel("Cantidad de movimientos");
}
