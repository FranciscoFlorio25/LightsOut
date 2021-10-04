package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;
import model.LightMatrix;
import view.Board;

public class Control {
	private LightMatrix model;
	private Board view;

	public Control(Board view, LightMatrix model) {
		super();
		this.view = view;
		this.model = model;
	}

	public void initView() {
		for (int i = 0; i < model.getLightMatrix().length; i++)
			for (int j = 0; j < model.getLightMatrix()[i].length; j++)
				view.getButtonFromMatrix(i, j).setSelected(model.getValue(i, j));
		view.getTxtVictory().setText("");
		model.cloneMatrix();
		;
	}

	public void refreshView() {
		JToggleButton[][] buttonMatrix = view.getMatrixButtons();
		for (int i = 0; i < model.getLightMatrix().length; i++)
			for (int j = 0; j < model.getLightMatrix()[i].length; j++) {
				buttonMatrix[i][j].setSelected(model.getValue(i, j));

			}
	}

	public void initIngameMenuButtons(int size) {
		view.getBtnNew().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (view.getChkboxSound().isSelected())
					view.sound("media/zapsplat_multimedia_cell_phone_smart_screen_button_press_click_feedback_001_60930.wav");
				newBoard(size);
				view.resetMovementsCount();
			}
		});
		view.getBtnReset().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (view.getChkboxSound().isSelected())
					view.sound("media/zapsplat_multimedia_cell_phone_smart_screen_button_press_click_feedback_001_60930.wav");
				resetBoard(size);
				view.resetMovementsCount();
			}
		});

		view.getBtnBack().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (view.getChkboxSound().isSelected())
					view.sound(
							"media/zapsplat_multimedia_cell_phone_smart_screen_button_press_click_feedback_001_60930.wav");
				returnMainMenu();
				view.resetMovementsCount();
			}
		});

	}

	public void addMouseListenerToggleButton(int i, int j) {

		JToggleButton[][] buttonMatrix = view.getMatrixButtons();

		buttonMatrix[i][j].addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (!model.checkVictory()) {
					model.setNewValues(i, j);
					view.increaseMovementsCount();
					msgVictory();
					refreshView();
				}
			}

		});
	}

	public void initToggleButtonsListeners() {
		JToggleButton[][] buttonMatrix = view.getMatrixButtons();
		for (int i = 0; i < buttonMatrix.length; i++) {
			for (int j = 0; j < buttonMatrix.length; j++) {
				addMouseListenerToggleButton(i, j);
			}

		}
		
	}

	public void initMainMenu() {
		view.getBtnPlay().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (isPositiveANumber(view.getTxtRandomMovementsOfNewBoard().getText()) == true) {
					setGameMode();
				}
			}
		});

		view.getChkbxDifficulty().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!view.getChkbxDifficulty().isSelected()) {
					view.actionDefaultDificulty(true);
				} else {
					view.actionDefaultDificulty(false);
				}
			}
		});
	}

	public void newBoard(int size) 
	{
		model.setLightMatrix(size, setGameDificulty());
		view.enableButtons(true);
		initView();
	}

	public int setGameDificulty() {
		return (view.getChkbxDifficulty().isSelected()) ? 20 : Integer.parseInt(view.getTxtRandomMovementsOfNewBoard().getText());
	}

	private boolean isPositiveANumber(String c) {
		if (c.contentEquals("") || c.contentEquals("0")) {
			view.errorMessageEmptyAndZero();
			return false;
		} else if (c.matches("[0-9]+") == false) {
			view.errorMessageNotValidNumber();
			return false;
		}
		return true;
	}

	public void resetBoard(int size) {
		if (!model.checkVictory()) {
		model.resetLightMatrix();
		initView();
		}
	}

	public void returnMainMenu() {
		view.getFrame().dispose();
		view = new Board();
		initMainMenu();
	}

	public void msgVictory() {
		if (model.checkVictory()) {
			view.victoryFx();
			view.enableButtons(false);
		}
	}

	public void setGameMode() {
		view.setGameLayout();

		if (view.getRadial4x4()) {
			model.setLightMatrix(4, setGameDificulty());
			view.createToggleButtonMatrix(4);
			view.setFrameSize(4);
		} else if (view.getRadial5x5()) {
			model.setLightMatrix(5, setGameDificulty());
			view.createToggleButtonMatrix(5);
			view.setFrameSize(5);
		} else if (view.getRadial6x6()) {
			model.setLightMatrix(6, setGameDificulty());
			view.createToggleButtonMatrix(6);
			view.setFrameSize(6);
		}
		initIngameMenuButtons(view.getMatrixButtons().length);
		initToggleButtonsListeners();
		view.setButtonsLayout();
		initView();
	}
}