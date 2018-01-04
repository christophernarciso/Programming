package org.chris;

import org.chris.util.CheckLevels;
import org.chris.util.Targets;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Jan 04 04:02:22 EST 2018
 */



/**
 * @author Christopher Narcisowddwdwd
 */
public class HiScoreGUI extends JFrame {

	private java.util.List<String> accsToInput = new ArrayList<>();
	private HashMap<String, Integer> accsToOutput = new HashMap<>();
	private int search, targetLevel;
	private String nameOfSkill;
	private File path;
	private JFileChooser fc;


	public HiScoreGUI() {
		this.fc = new JFileChooser();
		initComponents();
	}

	private void buttonFilterAccountsActionPerformed(ActionEvent e) {
		// TODO add your code here
		textOutputArea.setText("");
		long start = System.currentTimeMillis();
		printToOutput("Grabbing hiscore search value...\n");
		for (Targets s : Targets.values()) {
			if (s.getName().equals(comboSkillTarget.getSelectedItem().toString())) {
				search = s.getSearchValue();
				targetLevel = Integer.parseInt(textTargetLevel.getText());
				nameOfSkill = s.getName();
				printToOutput("Searching: " + nameOfSkill + " >= " + targetLevel + "\n");
			}
		}

		printToOutput("Saving each player's highscore value to our map.....\n");
		for (String playerName : accsToInput){
			int target = 0;
			int checks = 0;
			while (target == 0){
				if (checks > 4){
					break;
				}
				printToOutput("Checking the highscores...please wait while I load data...\n");
				target = new CheckLevels(playerName, search).getTargetLevel();
				checks++;
				/*try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}*/
			}
			if (target >= targetLevel){
				printToOutput("Found: " + playerName + "  Level: " + target + "\n");
				accsToOutput.put(playerName, target);
			}
		}
		printToOutput("Finished loading data for all accounts..sorting....\n");
		printToOutput("Clearing our output to display the sort.....\n");
		textOutputArea.setText("");
		printToOutput("Filtered Accounts for " + nameOfSkill + " Level:" + targetLevel + " or greater.\n");
		for (String name : accsToOutput.keySet()){
			printToOutput("Account: " + name + " | " + nameOfSkill + " Level: " + accsToOutput.get(name) + " \n");
		}
		printToOutput("Output time " + (System.currentTimeMillis() - start) + "ms.\n");
	}

	private void buttonLoadAccountsActionPerformed(ActionEvent e) {
		fc.setDialogTitle("Load Accounts File");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(buttonLoadAccounts) == JFileChooser.APPROVE_OPTION){
			//
		}
		printToOutput("Selected: " + fc.getSelectedFile().getName() + "\n");
		path = new File(fc.getSelectedFile().getAbsolutePath());
		printToOutput("Path(load): " + path.getAbsolutePath() + "\n");
		label5.setText("File: " + path.getAbsoluteFile().getName());
		printToOutput("Grabbing accounts from file................\n");
		try {
			accsToInput = Files.readAllLines(Paths.get(path.getAbsolutePath())
					, StandardCharsets.UTF_8);
		} catch (IOException f) {
			printToOutput(f + "\n");
		}
		printToOutput("Loaded accounts: " + (accsToInput.size()) + "\n");
	}

	private void printToOutput(String outputMessage){
		textOutputArea.append(outputMessage);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christopher Narcisowddwdwd
		label1 = new JLabel();
		scrollPane1 = new JScrollPane();
		textOutputArea = new JTextArea();
		label2 = new JLabel();
		comboSkillTarget = new JComboBox<>();
		label3 = new JLabel();
		textTargetLevel = new JTextField();
		buttonFilterAccounts = new JButton();
		label4 = new JLabel();
		label5 = new JLabel();
		buttonLoadAccounts = new JButton();

		//======== this ========
		setTitle("HiScore Filter Tool");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("Output");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
		contentPane.add(label1);
		label1.setBounds(15, 10, 60, 30);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(textOutputArea);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(15, 45, 295, 540);

		//---- label2 ----
		label2.setText("Target:");
		label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
		contentPane.add(label2);
		label2.setBounds(new Rectangle(new Point(325, 290), label2.getPreferredSize()));

		//---- comboSkillTarget ----
		comboSkillTarget.setFont(comboSkillTarget.getFont().deriveFont(comboSkillTarget.getFont().getSize() + 5f));
		comboSkillTarget.setModel(new DefaultComboBoxModel<>(new String[] {
			"Attack",
			"Defence",
			"Strength",
			"Hitpoints",
			"Range",
			"Prayer",
			"Magic",
			"Cooking",
			"Woodcutting",
			"Fletching",
			"Fishing",
			"Firemaking",
			"Crafting",
			"Smithing",
			"Mining",
			"Herblore",
			"Agility",
			"Theiving",
			"Slayer",
			"Farming",
			"Runecrafting",
			"Hunter",
			"Construction"
		}));
		contentPane.add(comboSkillTarget);
		comboSkillTarget.setBounds(395, 290, 115, comboSkillTarget.getPreferredSize().height);

		//---- label3 ----
		label3.setText("Target Level:");
		label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
		contentPane.add(label3);
		label3.setBounds(new Rectangle(new Point(325, 350), label3.getPreferredSize()));

		//---- textTargetLevel ----
		textTargetLevel.setText("1");
		textTargetLevel.setFont(textTargetLevel.getFont().deriveFont(textTargetLevel.getFont().getSize() + 4f));
		contentPane.add(textTargetLevel);
		textTargetLevel.setBounds(440, 345, 35, 30);

		//---- buttonFilterAccounts ----
		buttonFilterAccounts.setText("Start Filter");
		buttonFilterAccounts.setFont(buttonFilterAccounts.getFont().deriveFont(buttonFilterAccounts.getFont().getStyle() | Font.BOLD, buttonFilterAccounts.getFont().getSize() + 4f));
		buttonFilterAccounts.addActionListener(e -> buttonFilterAccountsActionPerformed(e));
		contentPane.add(buttonFilterAccounts);
		buttonFilterAccounts.setBounds(330, 420, 190, 60);

		//---- label4 ----
		label4.setText("Load Accounts");
		label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 4f));
		contentPane.add(label4);
		label4.setBounds(new Rectangle(new Point(325, 50), label4.getPreferredSize()));

		//---- label5 ----
		label5.setText("Path:");
		label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
		//contentPane.add(label5);
		label5.setBounds(new Rectangle(new Point(330, 80), label5.getPreferredSize()));

		//---- buttonLoadAccounts ----
		buttonLoadAccounts.setText("Load");
		buttonLoadAccounts.setFont(buttonLoadAccounts.getFont().deriveFont(buttonLoadAccounts.getFont().getStyle() | Font.BOLD, buttonLoadAccounts.getFont().getSize() + 4f));
		buttonLoadAccounts.addActionListener(e -> buttonLoadAccountsActionPerformed(e));
		contentPane.add(buttonLoadAccounts);
		buttonLoadAccounts.setBounds(330, 105, 120, buttonLoadAccounts.getPreferredSize().height);

		contentPane.setPreferredSize(new Dimension(550, 640));
		setSize(550, 640);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Christopher Narcisowddwdwd
	private JLabel label1;
	private JScrollPane scrollPane1;
	private JTextArea textOutputArea;
	private JLabel label2;
	private JComboBox<String> comboSkillTarget;
	private JLabel label3;
	private JTextField textTargetLevel;
	private JButton buttonFilterAccounts;
	private JLabel label4;
	private JLabel label5;
	private JButton buttonLoadAccounts;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
