//Rivka Doskoch & Yuval Terry 
package YandR_Olympics.View;

import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import YandR_Olympics.Listener.ViewEventListener;
import YandR_Olympics.Model.Athlete;
import YandR_Olympics.Model.Judge;
import YandR_Olympics.Model.Stadium;
import YandR_Olympics.Model.Team;
import YandR_Olympics.Model.Athlete.Countries;
import YandR_Olympics.Model.Athlete.SportType;
import YandR_Olympics.Model.Judge.JudgeArea;
import YandR_Olympics.Model.Stadium.StadiumLocation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OlympicsView {
	private Vector<ViewEventListener> allListeners = new Vector<ViewEventListener>();
	private ListView<Athlete> AllAthletesInCountryAndST;
	private ListView<Judge> AllJudgesInSpecificSportType;
	private ListView<Athlete> AllASpecificSportType_PC;
	private ListView<Team> AllTSpecificSportType_TC;
	private ListView<Judge> AllJudgesInSpecificSportType_TC;
	private ListView<Athlete> AllExistingPC;
	private ListView<Team> AllExistingTC;
	private Athlete FirstPlace;
	private Athlete SecondPlace;
	private Athlete ThirdPlace;
	private Team FirstPlaceT;
	private Team SecondPlaceT;
	private Team ThirdPlaceT;

	public OlympicsView(Stage theStage) {
		theStage.setTitle("Olimpic games 2020 - corona version: ");

		Button btnEditRepository = new Button("Edit Repository");
		Button btnCreateCompetitions = new Button("Create Competitions");
		Button btnLoadRepositoryFromFiles = new Button("Load Repository From Files");
		Button btnShowFinalWinners = new Button("Show Final Winners !");
		
		
		Calendar today = Calendar.getInstance();

		today.add(Calendar.DATE, 0);

		Calendar c = Calendar.getInstance();

		c.setTime(new Date()); // Now use today date.

		c.add(Calendar.DATE, 17); // Adds 17 days

		Label lblStartAndEndDates = new Label(today.getTime() + " - " + c.getTime());

		// tabs
		VBox vbAthlete = new VBox();
		vbAthlete.setPadding(new Insets(30));
		vbAthlete.setSpacing(20);
		vbAthlete.setAlignment(Pos.TOP_LEFT);
		vbAthlete.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbTeam = new VBox();
		vbTeam.setPadding(new Insets(30));
		vbTeam.setSpacing(20);
		vbTeam.setAlignment(Pos.TOP_CENTER);
		vbTeam.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbJudge = new VBox();
		vbJudge.setPadding(new Insets(30));
		vbJudge.setSpacing(20);
		vbJudge.setAlignment(Pos.TOP_CENTER);
		vbJudge.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbStadium = new VBox();
		vbStadium.setPadding(new Insets(30));
		vbStadium.setSpacing(20);
		vbStadium.setAlignment(Pos.TOP_CENTER);
		vbStadium.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbSingleCompetition = new VBox();
		vbSingleCompetition.setPadding(new Insets(30));
		vbSingleCompetition.setSpacing(20);
		vbSingleCompetition.setAlignment(Pos.TOP_CENTER);
		vbSingleCompetition.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbTeamCompetition = new VBox();
		vbTeamCompetition.setPadding(new Insets(30));
		vbTeamCompetition.setSpacing(20);
		vbTeamCompetition.setAlignment(Pos.TOP_CENTER);
		vbTeamCompetition.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbPersonalCompetitionR = new VBox();
		vbPersonalCompetitionR.setPadding(new Insets(30));
		vbPersonalCompetitionR.setSpacing(20);
		vbPersonalCompetitionR.setAlignment(Pos.TOP_CENTER);
		vbPersonalCompetitionR.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		VBox vbTeamCompetitionR = new VBox();
		vbTeamCompetitionR.setPadding(new Insets(30));
		vbTeamCompetitionR.setSpacing(20);
		vbTeamCompetitionR.setAlignment(Pos.TOP_CENTER);
		vbTeamCompetitionR.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tabAthlete = new Tab("Athlete", vbAthlete);
		Tab tabTeam = new Tab("Team", vbTeam);
		Tab tabJudge = new Tab("Judge", vbJudge);
		Tab tabStadium = new Tab("Stadium", vbStadium);

		tabPane.getTabs().add(tabAthlete);
		tabPane.getTabs().add(tabTeam);
		tabPane.getTabs().add(tabJudge);
		tabPane.getTabs().add(tabStadium);

		TabPane tabPaneComp = new TabPane();
		tabPaneComp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tabSingleCompetition = new Tab("Create Single Competition", vbSingleCompetition);
		Tab tabTeamCompetition = new Tab("Create Team Competition", vbTeamCompetition);
		Tab tabPersonalCompetitionResults = new Tab("Single Competition Results", vbPersonalCompetitionR);
		Tab tabTeamCompetitionResults = new Tab("Team Competition Results", vbTeamCompetitionR);

		tabPaneComp.getTabs().add(tabSingleCompetition);
		tabPaneComp.getTabs().add(tabTeamCompetition);
		tabPaneComp.getTabs().add(tabPersonalCompetitionResults);
		tabPaneComp.getTabs().add(tabTeamCompetitionResults);

		// For Athlete tab
		GridPane gpAthlete = new GridPane();
		gpAthlete.setPadding(new Insets(10));
		gpAthlete.setHgap(5);
		gpAthlete.setVgap(5);

		// For Team tab
		GridPane gpTeam = new GridPane();
		gpTeam.setPadding(new Insets(10));
		gpTeam.setHgap(5);
		gpTeam.setVgap(5);

		// For Judge tab
		GridPane gpJudge = new GridPane();
		gpJudge.setPadding(new Insets(10));
		gpJudge.setHgap(5);
		gpJudge.setVgap(5);

		// For Stadium tab
		GridPane gpStadium = new GridPane();
		gpStadium.setPadding(new Insets(10));
		gpStadium.setHgap(5);
		gpStadium.setVgap(5);

		// For Personal Competition
		GridPane gpPersonalC = new GridPane();
		gpPersonalC.setPadding(new Insets(10));
		gpPersonalC.setHgap(5);
		gpPersonalC.setVgap(5);

		// For Team Competition
		GridPane gpTeamC = new GridPane();
		gpTeamC.setPadding(new Insets(10));
		gpTeamC.setHgap(5);
		gpTeamC.setVgap(5);

		// For personal competition results
		GridPane gpPersonalCR = new GridPane();
		gpPersonalCR.setPadding(new Insets(10));
		gpPersonalCR.setHgap(5);
		gpPersonalCR.setVgap(5);

		// For Team Competition results
		GridPane gpTeamCR = new GridPane();
		gpTeamCR.setPadding(new Insets(10));
		gpTeamCR.setHgap(5);
		gpTeamCR.setVgap(5);

		// our primary stage
		VBox vbRoot = new VBox();
		vbRoot.setPadding(new Insets(10));
		vbRoot.setSpacing(15);
		vbRoot.getChildren().addAll(lblStartAndEndDates, btnEditRepository, btnLoadRepositoryFromFiles , btnCreateCompetitions, btnShowFinalWinners);
		vbRoot.setAlignment(Pos.CENTER);
		vbRoot.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, null, null)));

		HBox hbButten = new HBox();
		hbButten.setPadding(new Insets(30));
		hbButten.setSpacing(60);
		hbButten.setAlignment(Pos.BOTTOM_LEFT);
		
		HBox hbButten2 = new HBox();
		hbButten2.setPadding(new Insets(30));
		hbButten2.setSpacing(60);
		hbButten2.setAlignment(Pos.BOTTOM_LEFT);
		
		HBox hbRoot = new HBox();
		hbRoot.setPadding(new Insets(30));
		hbRoot.setSpacing(60);

		// Editing
		btnEditRepository.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				Stage editingTheRepository = new Stage();

				editingTheRepository.setTitle("Olimpiad Rpository Aditing");

				editingTheRepository.initOwner(theStage);
				editingTheRepository.initModality(Modality.WINDOW_MODAL);

				BorderPane bpRoot = new BorderPane();
				bpRoot.setTop(hbRoot);
				

				Label lblComboBoxes = new Label("Pay Attnion to choose all the comboBoxes, textFields and buttons.");
				hbButten.getChildren().add(lblComboBoxes);
				

				VBox windowWithTabs = new VBox(tabPane, hbButten);
				windowWithTabs.setPadding(new Insets(10));
				windowWithTabs.setSpacing(15);
				windowWithTabs.setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));

				editingTheRepository.setScene(new Scene(windowWithTabs, 1400, 450));
				editingTheRepository.show();

				// Add athlete
				Label lblAddAthlete = new Label("Add Athlete: ");
				lblAddAthlete.setFont(new Font(16));

				Button btnAddFromFile = new Button("Load From File");

				Label lblEnterName = new Label("Enter Athlete's name: ");
				TextField tfAthleteName = new TextField();

				Label lblAthletesCountry = new Label("Choose county: ");
				ComboBox<Athlete.Countries> cmbCountries = new ComboBox<>();
				cmbCountries.getItems().addAll(Countries.values());

				Label lblAthletesSportType = new Label("Choose sport type: ");
				ComboBox<Athlete.SportType> cmbSportType = new ComboBox<>();
				cmbSportType.getItems().addAll(SportType.values());

				Label lblAtleteMedals = new Label("Athlete's medals:  ");
				lblAtleteMedals.setFont(new Font(16));
				
				Label lblCAtleteMedals = new Label("Choose number: ");
				Spinner<Integer> spnNumOfAthleteMedals = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfAthleteMedals = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 100);
				spnNumOfAthleteMedals.setValueFactory(valueFactoryNumOfAthleteMedals);

				Button btnAddAthlete = new Button("Add The Athlete");

				Button btnSaveAToFile = new Button("Save");

				// Adding the athlete action
				btnAddAthlete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						if (tfAthleteName.getText().isEmpty()) {
							nameNotEntered();
						} else {
							for (ViewEventListener l : allListeners) {
								if (cmbSportType.getValue() == SportType.Jumper) {
									l.addedJumperToUI(tfAthleteName.getText(), cmbCountries.getValue(),
											cmbSportType.getValue(), spnNumOfAthleteMedals.getValue());
								}

								if (cmbSportType.getValue() == SportType.Runner) {
									l.addedRunnerToUI(tfAthleteName.getText(), cmbCountries.getValue(),
											cmbSportType.getValue(), spnNumOfAthleteMedals.getValue());
								}

								if (cmbSportType.getValue() == SportType.RunnerAndJumper) {
									l.addedRAndJToUI(tfAthleteName.getText(), cmbCountries.getValue(),
											cmbSportType.getValue(), spnNumOfAthleteMedals.getValue());
								}
							}

						}
						tfAthleteName.setText("");
						cmbCountries.getSelectionModel().clearSelection();
						cmbSportType.getSelectionModel().clearSelection();
					}
				});

				// saving athletes into file
				btnSaveAToFile.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							try {
								l.saveAthletesToFileInUI();
							} catch (FileNotFoundException e) {
								cantSaveF();
							}
						}
					}
				});

				// Read/Load Athletes from file
				btnAddFromFile.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						for (ViewEventListener l : allListeners) {
							try {
								l.readAthletesFromFileInUI();

							} catch (FileNotFoundException e) {
								// cantReadF();
							}
						}
					}
				});

				gpAthlete.add(lblAddAthlete, 0, 0);
				gpAthlete.add(btnAddFromFile, 2, 0);
				gpAthlete.add(lblEnterName, 0, 1);
				gpAthlete.add(tfAthleteName, 1, 1);
				gpAthlete.add(lblAthletesCountry, 0, 2);
				gpAthlete.add(cmbCountries, 1, 2);
				gpAthlete.add(lblAthletesSportType, 0, 3);
				gpAthlete.add(cmbSportType, 1, 3);
				gpAthlete.add(lblAtleteMedals, 0, 4);
				gpAthlete.add(lblCAtleteMedals, 0, 5);
				gpAthlete.add(spnNumOfAthleteMedals, 1, 5);
				gpAthlete.add(btnAddAthlete, 1, 7);
				gpAthlete.add(btnSaveAToFile, 1, 8);

				// Update athlete
				Label lblUpdateAthlete = new Label("Update Athlete's info: ");
				lblUpdateAthlete.setFont(new Font(16));
				
				Button btnShowAthlete = new Button("Show The Athlete");

				Label lblChooseAthlete = new Label("Choose athlete's number to update: ");
				Spinner<Integer> spnNumOfAthletesUp = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfAthletesUp = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfAthletesUp.setValueFactory(valueFactoryNumOfAthletesUp);

				Label lblUpdateAthleteST = new Label("Update sport type: ");
				ComboBox<Athlete.SportType> cmbUpdateAST = new ComboBox<>();
				cmbUpdateAST.getItems().addAll(SportType.values());

				Button btnUpdAST = new Button("Update Sport Type");

				Label lblUpdateAthleteM = new Label("Update number of athlete's medals: ");
				Spinner<Integer> spnNumOfAthleteUpM = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfAthleteUpM = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfAthleteUpM.setValueFactory(valueFactoryNumOfAthleteUpM);

				Button btnUpdAM = new Button("Update Athlete's Medals Number ");

				// Show all the athletes
				btnShowAthlete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							String allInfo = l.showedAllAInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
							textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}

					}
				});

				// Update sport type
				btnUpdAST.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.updatedAthleteSpotTypeInUI(spnNumOfAthletesUp.getValue(), cmbUpdateAST.getValue());
						}
					}
				});

				// Update number of medals
				btnUpdAM.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.updatedAthleteNumberOfMInUI(spnNumOfAthletesUp.getValue(), spnNumOfAthleteUpM.getValue());
						}
					}
				});

				gpAthlete.add(lblUpdateAthlete, 60, 0);
				gpAthlete.add(btnShowAthlete, 61, 0);
				gpAthlete.add(lblChooseAthlete, 60, 1);
				gpAthlete.add(spnNumOfAthletesUp, 61, 1);
				gpAthlete.add(lblUpdateAthleteST, 60, 2);
				gpAthlete.add(cmbUpdateAST, 61, 2);
				gpAthlete.add(btnUpdAST, 62, 2);
				gpAthlete.add(lblUpdateAthleteM, 60, 3);
				gpAthlete.add(spnNumOfAthleteUpM, 61, 3);
				gpAthlete.add(btnUpdAM, 62, 3);

				// Delete Athlete
				Label lblDelAthlete = new Label("Remove athlete from Olimpiad : ");
				lblDelAthlete.setFont(new Font(16));

				Label lblChooseA = new Label("Choose Athlete: ");
				Spinner<Integer> spnNumOfAthletesDel = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfAthletesDel = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfAthletesDel.setValueFactory(valueFactoryNumOfAthletesDel);

				Button btnDelA = new Button("Remove Athlete");

				btnDelA.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.removedAthleteFromUI(spnNumOfAthletesDel.getValue());
						}
					}
				});

				gpAthlete.add(lblDelAthlete, 60, 11);
				gpAthlete.add(lblChooseA, 60, 12);
				gpAthlete.add(spnNumOfAthletesDel, 61, 12);
				gpAthlete.add(btnDelA, 61, 13);

				vbAthlete.getChildren().addAll(gpAthlete);

				// Add Team
				Label lblAddTeam = new Label("Add Team : ");
				lblAddTeam.setFont(new Font(16));
				
				Button btnLoadT = new Button("Load Teams From File");

				Label lblTeamCountry = new Label("Choose county: ");
				ComboBox<Athlete.Countries> cmbTCountries = new ComboBox<>();
				cmbTCountries.getItems().addAll(Countries.values());

				Label lblTeamSportType = new Label("Choose sport type: ");
				ComboBox<Athlete.SportType> cmbSportTypeTeam = new ComboBox<>();
				cmbSportTypeTeam.getItems().addAll(SportType.values());

				Button btnShowAllAthletesInCountry = new Button("Add team's athletes");

				Label lblNumOfAthletesInTeam = new Label("Choose athletes to add to the team: ");

				Button btnChooseTheSelectedA = new Button("Add Choosed");

				Label lblTeamMedals = new Label("Team's medals: ");
				Label lblCTeamMedals = new Label("Choose number of medals: ");

				Spinner<Integer> spnNumOfTeamMedals = new Spinner<Integer>();
				SpinnerValueFactory<Integer> valueFactoryNumOfTeamMedals = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 100);
				spnNumOfTeamMedals.setValueFactory(valueFactoryNumOfTeamMedals);

				Button btnSaveTeamIntoFile = new Button("Save The Team To File");

				// Creating Team
				// Adding athletes to team
				cmbTCountries.setOnAction(new EventHandler<ActionEvent>() { // country

					@Override
					public void handle(ActionEvent event) {

						cmbSportTypeTeam.setOnAction(new EventHandler<ActionEvent>() {// sport type

							@Override
							public void handle(ActionEvent event) {// creating repository for choosing

								AllAthletesInCountryAndST = new ListView<Athlete>();
								for (ViewEventListener l : allListeners) {
									AllAthletesInCountryAndST.getItems().addAll(l.showedAllAByCountryAndSTInUI(
												cmbTCountries.getValue(), cmbSportTypeTeam.getValue()));
								
									AllAthletesInCountryAndST.getSelectionModel()
											.setSelectionMode(SelectionMode.MULTIPLE);
								}

								// repository for choosing
								btnShowAllAthletesInCountry.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
									
											Stage showAllSuitableA = new Stage();

											showAllSuitableA.initOwner(theStage);
											showAllSuitableA.initModality(Modality.WINDOW_MODAL);

											showAllSuitableA.setTitle(
													"Click on athlete to choose one, hold \"Ctrl\" button and click for multiple choice");

											// What window
											VBox vbShowAInCandST = new VBox(AllAthletesInCountryAndST,
													btnChooseTheSelectedA);
											vbShowAInCandST.setPadding(new Insets(10));
											vbShowAInCandST.setSpacing(15);
											vbShowAInCandST.setBackground(new Background(
													new BackgroundFill(Color.DARKSLATEBLUE, null, null)));

											showAllSuitableA.setScene(new Scene(vbShowAInCandST, 700, 400));
											showAllSuitableA.show();

											// add the selected
											btnChooseTheSelectedA.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent event) {
													ObservableList<Athlete> theNewA = AllAthletesInCountryAndST
															.getSelectionModel().getSelectedItems();
													ArrayList<Athlete> AthletesN = new ArrayList<Athlete>(theNewA);

													for (ViewEventListener l : allListeners) {
														l.addedTeamTOUI(cmbTCountries.getValue(),
																cmbSportTypeTeam.getValue(), AthletesN,
																spnNumOfTeamMedals.getValue());
														showAllSuitableA.close();
													}

													// Save the Team into file
													btnSaveTeamIntoFile.setOnAction(new EventHandler<ActionEvent>() {

														@Override
														public void handle(ActionEvent event) {
															for (ViewEventListener l : allListeners) {
																l.savedTeamInUI(cmbTCountries.getValue(),
																		cmbSportTypeTeam.getValue(), AthletesN,
																		spnNumOfTeamMedals.getValue());
															}

														}
													});
												}

											});
										
									}
								});

							}

						});
						
					}
					
				});

				btnLoadT.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							try {
								l.readTeamsFromFileInUI();
							} catch (FileNotFoundException e) {
								cantReadF();
							}
						}
					}
				});

				gpTeam.add(lblAddTeam, 0, 0);
				gpTeam.add(btnLoadT, 2, 0);
				gpTeam.add(lblTeamCountry, 0, 1);
				gpTeam.add(cmbTCountries, 1, 1);
				gpTeam.add(lblTeamSportType, 0, 2);
				gpTeam.add(cmbSportTypeTeam, 1, 2);
				gpTeam.add(lblTeamMedals, 0, 3);
				gpTeam.add(lblCTeamMedals, 0, 4);
				gpTeam.add(spnNumOfTeamMedals, 1, 4);
				gpTeam.add(lblNumOfAthletesInTeam, 0, 5);
				gpTeam.add(btnShowAllAthletesInCountry, 1, 5);
				gpTeam.add(btnSaveTeamIntoFile, 1, 7);

				// Update Team
				Label lblUpdateTeam = new Label("Update Team's info: ");
				lblUpdateTeam.setFont(new Font(16));

				Button btnShowTeams = new Button("Show Teams");

				Label lblChooseTeam = new Label("Choose team number: ");
				Spinner<Integer> spnNumOfTeamUp = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfTeamUp = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfTeamUp.setValueFactory(valueFactoryNumOfTeamUp);

				Label lblUpdateTeamM = new Label("Update number of team's medals: ");
				Spinner<Integer> spnNumOfTeamUpM = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfTeamUpM = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfTeamUpM.setValueFactory(valueFactoryNumOfTeamUpM);

				Button btnUpdTM = new Button("Update Team's Medals Number ");

				// Show all existing teams
				btnShowTeams.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							String allInfoT = l.showedAllTeamsInUI();
							JTextArea textAreaT = new JTextArea(15, 80);
							textAreaT.setEditable(false);
							textAreaT.setText(allInfoT);
							textAreaT.setLineWrap(true);
							textAreaT.setFont(textAreaT.getFont().deriveFont(14f));
							JScrollPane scrollPaneT = new JScrollPane(textAreaT);
							JOptionPane.showMessageDialog(null, scrollPaneT);
						}
					}
				});

				btnUpdTM.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.updatedTeamInUI(spnNumOfTeamUp.getValue(), spnNumOfTeamUpM.getValue());
						}
					}
				});

				gpTeam.add(lblUpdateTeam, 60, 0);
				gpTeam.add(btnShowTeams, 61, 0);
				gpTeam.add(lblChooseTeam, 60, 1);
				gpTeam.add(spnNumOfTeamUp, 61, 1);
				gpTeam.add(lblUpdateTeamM, 60, 2);
				gpTeam.add(spnNumOfTeamUpM, 61, 2);
				gpTeam.add(btnUpdTM, 62, 2);

				// Delete Team
				Label lblDelTeam = new Label("Delete the team: ");
				lblDelTeam.setFont(new Font(16));

				Label lblChooseTDel = new Label("Choose team: ");
				Spinner<Integer> spnNumOfTeamsDel = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfTeamsDel = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfTeamsDel.setValueFactory(valueFactoryNumOfTeamsDel);

				Button btnDelT = new Button("Delete Team");

				btnDelT.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.removedTeamFromUI(spnNumOfTeamsDel.getValue());
						}
						
					}					
				});

				gpTeam.add(lblDelTeam, 60, 7);
				gpTeam.add(lblChooseTDel, 60, 8);
				gpTeam.add(spnNumOfTeamsDel, 61, 8);
				gpTeam.add(btnDelT, 61, 9);

				vbTeam.getChildren().addAll(gpTeam);

				// Add Judges
				Label lblAddJudges = new Label("Add Judges: ");
				lblAddJudges.setFont(new Font(16));

				Button btnAddFromFileJ = new Button("Load From File");

				Label lblEnterJudgeName = new Label("Enter Judge's name: ");
				TextField tfJudgeName = new TextField();

				Label lblJudgeCountry = new Label("Choose county: ");
				ComboBox<Judge.Countries> cmbJudgeCountries = new ComboBox<>();
				cmbJudgeCountries.getItems().addAll(YandR_Olympics.Model.Judge.Countries.values());

				Label lblJudgeSportType = new Label("Choose sport type: ");
				ComboBox<Judge.JudgeArea> cmbJudgeSportType = new ComboBox<>();
				cmbJudgeSportType.getItems().addAll(JudgeArea.values());

				Button btnAddJudge = new Button("Add The Judge");

				btnAddJudge.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.addJudgeInUI(tfJudgeName.getText(), cmbJudgeCountries.getValue(),
									cmbJudgeSportType.getValue());
						}
						tfJudgeName.setText("");
						cmbJudgeCountries.getSelectionModel().clearSelection();
						cmbJudgeSportType.getSelectionModel().clearSelection();
					}
				});

				// Read/Load Judges from file
				btnAddFromFileJ.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						for (ViewEventListener l : allListeners) {
							try {
								l.readJudgesFromFileInUI();

							} catch (FileNotFoundException e) {
								// cantReadF();
							}
						}
					
					}
				});

				gpJudge.add(lblAddJudges, 0, 0);
				gpJudge.add(btnAddFromFileJ, 2, 0);
				gpJudge.add(lblEnterJudgeName, 0, 1);
				gpJudge.add(tfJudgeName, 1, 1);
				gpJudge.add(lblJudgeCountry, 0, 2);
				gpJudge.add(cmbJudgeCountries, 1, 2);
				gpJudge.add(lblJudgeSportType, 0, 3);
				gpJudge.add(cmbJudgeSportType, 1, 3);
				gpJudge.add(btnAddJudge, 1, 4);

				// Update Judges
				Label lblUpdateJudge = new Label("Update judge's info: ");
				lblUpdateJudge.setFont(new Font(16));

				Button btnShowJudge = new Button("Show The Judge");

				Label lblChooseJudge = new Label("Choose judge's number: ");
				Spinner<Integer> spnNumOfJudgeUp = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfJudgeUp = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfJudgeUp.setValueFactory(valueFactoryNumOfJudgeUp);

				Label lblUpdateJudgeST = new Label("Update sport type: ");
				ComboBox<Judge.JudgeArea> cmbUpdateJST = new ComboBox<>();
				cmbUpdateJST.getItems().addAll(JudgeArea.values());

				Button btnUpdJST = new Button("Update Sport Type");

				btnUpdJST.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.updatedJudgeSpotTypeInUI(spnNumOfJudgeUp.getValue(), cmbUpdateJST.getValue());
						}
						cmbUpdateJST.getSelectionModel().clearSelection();
					}
				});

				btnShowJudge.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							String allInfoT = l.showAllJudgesInUI();
							JTextArea textAreaT = new JTextArea(15, 40);
							textAreaT.setEditable(false);
							textAreaT.setText(allInfoT);
							textAreaT.setLineWrap(true);
							textAreaT.setFont(textAreaT.getFont().deriveFont(14f));
							JScrollPane scrollPaneT = new JScrollPane(textAreaT);
							JOptionPane.showMessageDialog(null, scrollPaneT);
						}
					}
				});

				gpJudge.add(lblUpdateJudge, 60, 0);
				gpJudge.add(btnShowJudge, 61, 0);
				gpJudge.add(lblChooseJudge, 60, 1);
				gpJudge.add(spnNumOfJudgeUp, 61, 1);
				gpJudge.add(lblUpdateJudgeST, 60, 2);
				gpJudge.add(cmbUpdateJST, 61, 2);
				gpJudge.add(btnUpdJST, 61, 3);

				// Delete Judges
				Label lblDelJudge = new Label("Delete the judge: ");
				lblDelJudge.setFont(new Font(16));

				Label lblChooseJ = new Label("Choose judge: ");
				Spinner<Integer> spnNumOfJudgeDel = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfJudgeDel = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfJudgeDel.setValueFactory(valueFactoryNumOfJudgeDel);

				Button btnDelJ = new Button("Delete Judge");

				Button btnSaveJToFile = new Button("Save Judges To File");

				btnDelJ.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.removedJudgeFromUI(spnNumOfJudgeDel.getValue());
						}
					}
				});

				btnSaveJToFile.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							try {
								l.saveJudgesToFileInUI();
							} catch (FileNotFoundException e) {
								cantSaveF();
							}
						}
					}
				});

				gpJudge.add(lblDelJudge, 60, 11);
				gpJudge.add(lblChooseJ, 60, 12);
				gpJudge.add(spnNumOfJudgeDel, 61, 12);
				gpJudge.add(btnDelJ, 61, 13);
				gpJudge.add(btnSaveJToFile, 75, 14);

				vbJudge.getChildren().addAll(gpJudge);

				// Add Stadium
				Label lblAddStadium = new Label("Add Stadium: ");
				lblAddStadium.setFont(new Font(16));

				Button btnAddFromFileS = new Button("Load From File");

				Label lblEnterStadiumName = new Label("Enter Stadium's name: ");
				TextField tfStadiumName = new TextField();

				Label lblStadiumLocation = new Label("Choose location: ");
				ComboBox<Stadium.StadiumLocation> cmbStadiumLocation = new ComboBox<>();
				cmbStadiumLocation.getItems().addAll(StadiumLocation.values());

				Label lblChooseNumOfSeats = new Label("Choose number of seats: ");
				Spinner<Integer> spnNumOfSeats = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfSeats = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfSeats.setValueFactory(valueFactoryNumOfSeats);

				Button btnAddStadium = new Button("Add The Stadium");

				btnAddStadium.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.addStadiumInUI(tfStadiumName.getText(), cmbStadiumLocation.getValue(),
									spnNumOfSeats.getValue());
						}
						tfStadiumName.setText("");
						cmbStadiumLocation.getSelectionModel().clearSelection();
					}
				});

				// Read/Load Stadiums from file
				btnAddFromFileS.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						for (ViewEventListener l : allListeners) {
							try {
								l.readStadiumsFromFileInUI();

							} catch (FileNotFoundException e) {
								// cantReadF();
							}
						}
					}
				});

				gpStadium.add(lblAddStadium, 0, 0);
				gpStadium.add(btnAddFromFileS, 2, 0);
				gpStadium.add(lblEnterStadiumName, 0, 1);
				gpStadium.add(tfStadiumName, 1, 1);
				gpStadium.add(lblStadiumLocation, 0, 2);
				gpStadium.add(cmbStadiumLocation, 1, 2);
				gpStadium.add(lblChooseNumOfSeats, 0, 3);
				gpStadium.add(spnNumOfSeats, 1, 3);
				gpStadium.add(btnAddStadium, 1, 4);

				// Update Stadium
				Label lblUpdateStadium = new Label("Update Stadium's info: ");
				lblUpdateStadium.setFont(new Font(16));

				Button btnShowStadium = new Button("Show The Stadium");

				Label lblChooseStadium = new Label("Choose Stadium's number: ");
				Spinner<Integer> spnNumOfStadiumUp = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfStadiumUp = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 400);
				spnNumOfStadiumUp.setValueFactory(valueFactoryNumOfStadiumUp);

				Label lblChooseStadiumSeats = new Label("Choose number of seats: ");
				Spinner<Integer> spnNumOfStadiumSeatsUp = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfStadiumSeatsUp = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 50000);
				spnNumOfStadiumSeatsUp.setValueFactory(valueFactoryNumOfStadiumSeatsUp);

				Button btnUpdStadium = new Button("Update Stadium");

				btnShowStadium.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							String allInfo = l.showedAllSInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
							textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}

					}
				});

				btnUpdStadium.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.updatedStadiumSeatsInUI(spnNumOfStadiumUp.getValue(), spnNumOfStadiumSeatsUp.getValue());
						}
					}
				});

				gpStadium.add(lblUpdateStadium, 60, 0);
				gpStadium.add(btnShowStadium, 61, 0);
				gpStadium.add(lblChooseStadium, 60, 1);
				gpStadium.add(spnNumOfStadiumUp, 61, 1);
				gpStadium.add(lblChooseStadiumSeats, 60, 2);
				gpStadium.add(spnNumOfStadiumSeatsUp, 61, 2);
				gpStadium.add(btnUpdStadium, 61, 3);

				// Delete Stadium
				Label lblDelStadium = new Label("Delete the stadium: ");
				lblDelStadium.setFont(new Font(16));

				Label lblChooseS = new Label("Choose stadium: ");
				Spinner<Integer> spnNumOfStadiumDel = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfStadiumDel = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfStadiumDel.setValueFactory(valueFactoryNumOfStadiumDel);

				Button btnDelS = new Button("Delete Stadium");

				Button btnSaveSToFile = new Button("Sava Stadium To FileS");

				btnDelS.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							l.removedStadiumFromUI(spnNumOfStadiumDel.getValue());
						}
					}
				});

				btnSaveSToFile.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							try {
								l.saveStadiumsToFileInUI();
							} catch (FileNotFoundException e) {
								cantSaveF();
							}
						}
					}
				});

				gpStadium.add(lblDelStadium, 60, 11);
				gpStadium.add(lblChooseS, 60, 12);
				gpStadium.add(spnNumOfStadiumDel, 61, 12);
				gpStadium.add(btnDelS, 61, 13);
				gpStadium.add(btnSaveSToFile, 75, 14);

				vbStadium.getChildren().addAll(gpStadium);
				
			}
		});
		

		btnLoadRepositoryFromFiles.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ViewEventListener l : allListeners) {
					try {
						l.readAthletesFromFileInUI();
						l.readJudgesFromFileInUI();
						l.readStadiumsFromFileInUI();
						l.readTeamsFromFileInUI();
					} catch (FileNotFoundException e) {
						cantReadF();
					}

				}
			}
		});
		
		// Using repository
		btnCreateCompetitions.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ViewEventListener l : allListeners) {
					l.loadAllReposytory();
				}

				Stage usingTheRepository = new Stage();

				usingTheRepository.initOwner(theStage);
				usingTheRepository.initModality(Modality.WINDOW_MODAL);

				BorderPane bpRoot = new BorderPane();
				bpRoot.setTop(hbRoot);

				Label lblComboBoxes2 = new Label("Pay Attnion to choose all the comboBoxes, textFields and buttons.");
				hbButten2.getChildren().add(lblComboBoxes2);
				
				VBox windowWithTabs2 = new VBox(tabPaneComp , hbButten2);
				windowWithTabs2.setPadding(new Insets(10));
				windowWithTabs2.setSpacing(15);
				windowWithTabs2.setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));

				usingTheRepository.setScene(new Scene(windowWithTabs2, 900, 400));
				usingTheRepository.show();

				// Create personal competition
				Label lblPersonalCompetitionS = new Label("Choose stadium: ");
				lblPersonalCompetitionS.setFont(new Font(16));

				Spinner<Integer> spnNumOfStadiumForPersC = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfStadiumForPersC = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfStadiumForPersC.setValueFactory(valueFactoryNumOfStadiumForPersC);

				Button btnshowAllExistingS_PC = new Button("Show All Existing Stadiums");

				Label lblChooseST_PC = new Label("Choose sport type: ");

				ComboBox<Judge.JudgeArea> cmbsportTypeForPersC = new ComboBox<Judge.JudgeArea>();
				cmbsportTypeForPersC.getItems().addAll(Judge.JudgeArea.values());

				Label lblPessonalCompetitionJ = new Label("Sign Judge for the competition: ");

				Button btnshowAllExisringJ_PC = new Button("Choose Judges");

				Button btnChooseJFor_PC = new Button("Choose Selected");

				Label lblLimitThePerticipantsIn_PC = new Label("Choose the limit for perticipants number: ");

				Spinner<Integer> spnLimitNum_PC = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryLimitNum_PC = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						3, 90);
				spnLimitNum_PC.setValueFactory(valueFactoryLimitNum_PC);

				Label lblChooseAthletes_PC = new Label("Sign athletes to competition: ");

				Button btnshowAllExistingA_PC = new Button("Add Athletes");

				Button btnCreateCompetition_PC = new Button("Create Competition");

				// Show all existing Stadiums
				btnshowAllExistingS_PC.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						/*
						 * for (ViewEventListener l : allListeners) { l.showAllExistingStadiumsInUI(); }
						 */ for (ViewEventListener l : allListeners) {
							String allInfo = l.showedAllSInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
							textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}
					}
				});

				// Creating list view for judges and athletes
				cmbsportTypeForPersC.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// list view for judges
						AllJudgesInSpecificSportType = new ListView<Judge>();
						for (ViewEventListener l : allListeners) {

							AllJudgesInSpecificSportType.getItems()
									.addAll(l.showedAllJBySTInUI(cmbsportTypeForPersC.getValue()));

							AllJudgesInSpecificSportType.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

						}

						// list view for athletes
						AllASpecificSportType_PC = new ListView<Athlete>();
						String AthleteST = "change";

						if (cmbsportTypeForPersC.getValue().equals(JudgeArea.Jumpers)) {
							AthleteST = "Jumper";
						}
						if (cmbsportTypeForPersC.getValue().equals(JudgeArea.Runners)) {
							AthleteST = "Runner";
						}
						if (cmbsportTypeForPersC.getValue().equals(JudgeArea.RunnersAndJumpers)) {
							AthleteST = "RunnerAndJumper";
						}

						for (ViewEventListener l : allListeners) {
							
							AllASpecificSportType_PC.getItems()
									.addAll(l.showedAllABySTInUI(SportType.valueOf(AthleteST)));
						}
						
						AllASpecificSportType_PC.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


						// repository for choosing a judge
						btnshowAllExisringJ_PC.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
									
									Stage showAllSuitableJudges = new Stage();

									showAllSuitableJudges.initOwner(theStage);
									showAllSuitableJudges.initModality(Modality.WINDOW_MODAL);

									showAllSuitableJudges.setTitle("Click on Judge to choose");

									// What window
									VBox vbShowJFor_PC = new VBox(AllJudgesInSpecificSportType, btnChooseJFor_PC);
									vbShowJFor_PC.setPadding(new Insets(10));
									vbShowJFor_PC.setSpacing(15);
									vbShowJFor_PC.setBackground(
											new Background(new BackgroundFill(Color.DARKSALMON, null, null)));

									showAllSuitableJudges.setScene(new Scene(vbShowJFor_PC, 700, 400));
									showAllSuitableJudges.show();

									// add the selected judge
									btnChooseJFor_PC.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent event) {

											Judge theNewJ = AllJudgesInSpecificSportType.getSelectionModel()
													.getSelectedItem();

											showAllSuitableJudges.close();

											// repository for choosing athletes
											btnshowAllExistingA_PC.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent event) {
																										
														Stage showAllSuitableAthletes = new Stage();

														showAllSuitableAthletes.initOwner(theStage);
														showAllSuitableAthletes.initModality(Modality.WINDOW_MODAL);

														showAllSuitableAthletes.setTitle(
																"Click on Athlete to choose one or press \"Ctrl\" and click to add many");

														// What window
														VBox vbShowAFor_PC = new VBox(AllASpecificSportType_PC,
																btnCreateCompetition_PC);
														vbShowAFor_PC.setPadding(new Insets(10));
														vbShowAFor_PC.setSpacing(15);
														vbShowAFor_PC.setBackground(new Background(
																new BackgroundFill(Color.BLANCHEDALMOND, null, null)));

														showAllSuitableAthletes
																.setScene(new Scene(vbShowAFor_PC, 700, 400));
														showAllSuitableAthletes.show();

														// add the selected athletes
														btnCreateCompetition_PC
																.setOnAction(new EventHandler<ActionEvent>() {

																	@Override
																	public void handle(ActionEvent event) {

																		
																		ObservableList<Athlete> theSuitableA = AllASpecificSportType_PC
																				.getSelectionModel().getSelectedItems();
																		ArrayList<Athlete> Athletes_PC = new ArrayList<Athlete>(
																				theSuitableA);

																		for (ViewEventListener l : allListeners) {
																			
																				l.createdPersonalCompetitionInUI(
																						spnLimitNum_PC.getValue(),
																						spnNumOfStadiumForPersC
																								.getValue(),
																						cmbsportTypeForPersC.getValue(),
																						theNewJ, Athletes_PC);
																			
																		}
																		showAllSuitableAthletes.close();

																	}

																});
													}
												
											});
										}

									});
								
							}
						
							
						});
//						cmbsportTypeForPersC.getSelectionModel().clearSelection();;
					}
				});

				gpPersonalC.add(lblPersonalCompetitionS, 0, 0);
				gpPersonalC.add(spnNumOfStadiumForPersC, 1, 0);
				gpPersonalC.add(btnshowAllExistingS_PC, 2, 0);
				gpPersonalC.add(lblChooseST_PC, 0, 1);
				gpPersonalC.add(cmbsportTypeForPersC, 1, 1);
				gpPersonalC.add(lblPessonalCompetitionJ, 0, 2);
				gpPersonalC.add(btnshowAllExisringJ_PC, 1, 2);
				gpPersonalC.add(lblLimitThePerticipantsIn_PC, 0, 3);
				gpPersonalC.add(spnLimitNum_PC, 1, 3);
				gpPersonalC.add(lblChooseAthletes_PC, 0, 4);
				gpPersonalC.add(btnshowAllExistingA_PC, 1, 4);

				vbSingleCompetition.getChildren().add(gpPersonalC);

				// Results tab of personal competition							
				Label lblChooseNOf_PCR = new Label("Choose competition number: ");

				ComboBox<Integer> cmbPC_R = new ComboBox<Integer>();

				for (int i = 1; i <= 100; i++) {
					cmbPC_R.getItems().add(i);

				}

				Button btnShowAll_PCR = new Button("Show all Personal Competition");
				
				Label lblFirsPlaceP = new Label("Choose athlete for the first place: ");

				Button btnFirstPlace = new Button("Set First Place");

				Button btnSetFirstPlace = new Button("Insert");
				
				Label lblSecondPlaceP = new Label("Choose athlete for the second place: ");


				Button btnSecondPlace = new Button("Set Second Place");

				Button btnSetSecondPlace = new Button("Insert");
				
				Label lblThirdPlaceP = new Label("Choose athlete for the third place: ");


				Button btnThirdPlace = new Button("Set Third Place");

				Button bntSave_PCR = new Button("Save Results");

				// personal competition results
				gpPersonalCR.add(lblChooseNOf_PCR, 0, 0);
				gpPersonalCR.add(btnShowAll_PCR, 1, 0);
				gpPersonalCR.add(cmbPC_R, 2, 0);
				gpPersonalCR.add(lblFirsPlaceP, 0, 1);
				gpPersonalCR.add(btnFirstPlace, 1, 1);
				gpPersonalCR.add(lblSecondPlaceP, 0, 2);
				gpPersonalCR.add(btnSecondPlace, 1, 2);
				gpPersonalCR.add(lblThirdPlaceP, 0, 3);
				gpPersonalCR.add(btnThirdPlace, 1, 3);

				vbPersonalCompetitionR.getChildren().add(gpPersonalCR);
				
				// Show all personal competitions
				btnShowAll_PCR.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						for (ViewEventListener l : allListeners) {
							String allInfo = l.repositoryAllPCInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
							textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}
					}
				});

				// Set the competition
				cmbPC_R.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						AllExistingPC = new ListView<Athlete>();
						for (ViewEventListener l : allListeners) {

							try {
								AllExistingPC.getItems().addAll(l.showAllPCInUI(cmbPC_R.getValue()));
							} catch (Exception e) {
								e.getMessage();
							}

						}
						// creating results
						// First place
						btnFirstPlace.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing 1

								Stage showAllPCA1 = new Stage();

								showAllPCA1.initOwner(theStage);
								showAllPCA1.initModality(Modality.WINDOW_MODAL);

								showAllPCA1.setTitle("Click on Athlete to set first place");

								// new window
								VBox vbShowAFor_PCR1 = new VBox(AllExistingPC, btnSetFirstPlace);
								vbShowAFor_PCR1.setPadding(new Insets(10));
								vbShowAFor_PCR1.setSpacing(15);
								vbShowAFor_PCR1.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllPCA1.setScene(new Scene(vbShowAFor_PCR1, 700, 400));
								showAllPCA1.show();

								// add the selected first place
								btnSetFirstPlace.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingPC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										FirstPlace = AllExistingPC.getSelectionModel().getSelectedItem();

										showAllPCA1.close();
									}
								});
							}

						});
					

						// Second place
						btnSecondPlace.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing 2

								Stage showAllPCA2 = new Stage();

								showAllPCA2.initOwner(theStage);
								showAllPCA2.initModality(Modality.WINDOW_MODAL);

								showAllPCA2.setTitle("Click on Athlete to set second place");

								// What window
								VBox vbShowAFor_PCR2 = new VBox(AllExistingPC, btnSetSecondPlace);
								vbShowAFor_PCR2.setPadding(new Insets(10));
								vbShowAFor_PCR2.setSpacing(15);
								vbShowAFor_PCR2.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllPCA2.setScene(new Scene(vbShowAFor_PCR2, 700, 400));
								showAllPCA2.show();

								// add the selected second place
								btnSetSecondPlace.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingPC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										SecondPlace = AllExistingPC.getSelectionModel().getSelectedItem();

										showAllPCA2.close();
									}

								});
							}
						});


						// Third Place
						btnThirdPlace.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing third place

								Stage showAllPCA3 = new Stage();

								showAllPCA3.initOwner(theStage);
								showAllPCA3.initModality(Modality.WINDOW_MODAL);

								showAllPCA3.setTitle("Click on Athlete to set third place");

								// What window
								VBox vbShowAFor_PCR3 = new VBox(AllExistingPC, bntSave_PCR);
								vbShowAFor_PCR3.setPadding(new Insets(10));
								vbShowAFor_PCR3.setSpacing(15);
								vbShowAFor_PCR3.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllPCA3.setScene(new Scene(vbShowAFor_PCR3, 700, 400));
								showAllPCA3.show();

								bntSave_PCR.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingPC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										ThirdPlace = AllExistingPC.getSelectionModel().getSelectedItem();
										for (ViewEventListener l : allListeners) {
											
												l.setPersonalCompetitionsResultsInUI(FirstPlace, SecondPlace, ThirdPlace);
											
										}
										showAllPCA3.close();
									}
								
								});
							}

						});
					
					}
				});

				


				// Create Team competition
				Label lblTeamCompetitionS = new Label("Choose stadium: ");

				Spinner<Integer> spnNumOfStadiumForTeamC = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfStadiumForTeamC = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfStadiumForTeamC.setValueFactory(valueFactoryNumOfStadiumForTeamC);

				Button btnShowAllExistingS_TC = new Button("Show All Existing Stadiums");

				Label lblChooseST_TC = new Label("Choose sport type: ");

				ComboBox<Judge.JudgeArea> cmbsportTypeForTeamC = new ComboBox<Judge.JudgeArea>();
				cmbsportTypeForTeamC.getItems().addAll(Judge.JudgeArea.values());

				Label lblTeamCompetitionJ = new Label("Choose Judge: ");

				Spinner<Integer> spnNumOfJudgeForTeamC = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryNumOfJudgeForTeamC = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						1, 90);
				spnNumOfJudgeForTeamC.setValueFactory(valueFactoryNumOfJudgeForTeamC);

				Button btnshowAllExisringJ_TC = new Button("Show All Existing Judges");

				Button btnChooseJFor_TC = new Button("Choose Judge");

				Label lblLimitTheParticipantsIn_TC = new Label("Choose the limit for perticipants number: ");

				Spinner<Integer> spnLimitNum_TC = new Spinner<Integer>();

				SpinnerValueFactory<Integer> valueFactoryLimitNum_TC = new SpinnerValueFactory.IntegerSpinnerValueFactory(
						3, 90);
				spnLimitNum_TC.setValueFactory(valueFactoryLimitNum_TC);

				Label lblChooseTeams_TC = new Label("Sign teams to competition: ");

				Button btnshowAllExistingT_TC = new Button("Add Team");

				Button btnCreateCompetition_TC = new Button("Create Competition");

				// Show all existing Stadiums
				btnShowAllExistingS_TC.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						for (ViewEventListener l : allListeners) {
							String allInfo = l.showedAllSInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
							textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}
					}
				});

				// Creating list view for judges and athletes
				cmbsportTypeForTeamC.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// list view for judges
						AllJudgesInSpecificSportType_TC = new ListView<Judge>();
						for (ViewEventListener l : allListeners) {

							AllJudgesInSpecificSportType_TC.getItems()
									.addAll(l.showedAllJBySTInUI(cmbsportTypeForTeamC.getValue()));

							AllJudgesInSpecificSportType_TC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
						}

						// list view for team
						AllTSpecificSportType_TC = new ListView<Team>();
						for (ViewEventListener l : allListeners) {
							String TeamST = "change";

							if (cmbsportTypeForTeamC.getValue().equals(JudgeArea.Jumpers)) {
								TeamST = "Jumper";
							}
							if (cmbsportTypeForTeamC.getValue().equals(JudgeArea.Runners)) {
								TeamST = "Runner";
							}
							if (cmbsportTypeForTeamC.getValue().equals(JudgeArea.RunnersAndJumpers)) {
								TeamST = "RunnerAndJumper";
							}

							AllTSpecificSportType_TC.getItems().addAll(l.showedAllTBySTInUI(SportType.valueOf(TeamST)));

							AllTSpecificSportType_TC.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
						}

						// repository for choosing a judge
						btnshowAllExisringJ_TC.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								
									Stage showAllSuitableJudges_TC = new Stage();

									showAllSuitableJudges_TC.initOwner(theStage);
									showAllSuitableJudges_TC.initModality(Modality.WINDOW_MODAL);

									showAllSuitableJudges_TC.setTitle("Click on Judge to choose");

									// What window
									VBox vbShowJFor_TC = new VBox(AllJudgesInSpecificSportType_TC, btnChooseJFor_TC);
									vbShowJFor_TC.setPadding(new Insets(10));
									vbShowJFor_TC.setSpacing(15);
									vbShowJFor_TC.setBackground(
											new Background(new BackgroundFill(Color.DARKSALMON, null, null)));

									showAllSuitableJudges_TC.setScene(new Scene(vbShowJFor_TC, 700, 400));
									showAllSuitableJudges_TC.show();

									// add the selected judge
									btnChooseJFor_TC.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent event) {
											Judge theNewJ = AllJudgesInSpecificSportType_TC.getSelectionModel()
													.getSelectedItem();

											showAllSuitableJudges_TC.close();

											// repository for choosing Team
											btnshowAllExistingT_TC.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent event) {
													
														Stage showAllSuitableTeams_TC = new Stage();

														showAllSuitableTeams_TC.initOwner(theStage);
														showAllSuitableTeams_TC.initModality(Modality.WINDOW_MODAL);

														showAllSuitableTeams_TC.setTitle(
																"Click on Team to choose one or press \"Ctrl\" and click to add many");

														// What window
														VBox vbShowTFor_TC = new VBox(AllTSpecificSportType_TC,
																btnCreateCompetition_TC);
														vbShowTFor_TC.setPadding(new Insets(10));
														vbShowTFor_TC.setSpacing(15);
														vbShowTFor_TC.setBackground(new Background(
																new BackgroundFill(Color.BLANCHEDALMOND, null, null)));

														showAllSuitableTeams_TC
																.setScene(new Scene(vbShowTFor_TC, 700, 400));
														showAllSuitableTeams_TC.show();

														// add the selected athletes
														btnCreateCompetition_TC
																.setOnAction(new EventHandler<ActionEvent>() {

																	@Override
																	public void handle(ActionEvent event) {
																		ObservableList<Team> theSuitableT = AllTSpecificSportType_TC
																				.getSelectionModel().getSelectedItems();
																		ArrayList<Team> Teams_TC = new ArrayList<Team>(
																				theSuitableT);

																		for (ViewEventListener l : allListeners) {

																				
																					l.createdTCompetitionInUI(
																							spnLimitNum_TC.getValue(),
																							spnNumOfStadiumForTeamC
																									.getValue(),
																							cmbsportTypeForTeamC.getValue(),
																							theNewJ, Teams_TC);
																				
																			

																			showAllSuitableTeams_TC.close();

																		}
																	}
																});
													
												}
											});
										}

									});
								
							}
						});
					}
				});

				gpTeamC.add(lblTeamCompetitionS, 0, 0);
				gpTeamC.add(spnNumOfStadiumForTeamC, 1, 0);
				gpTeamC.add(btnShowAllExistingS_TC, 2, 0);
				gpTeamC.add(lblChooseST_TC, 0, 1);
				gpTeamC.add(cmbsportTypeForTeamC, 1, 1);
				gpTeamC.add(lblTeamCompetitionJ, 0, 2);
				gpTeamC.add(btnshowAllExisringJ_TC, 1, 2);
				gpTeamC.add(lblLimitTheParticipantsIn_TC, 0, 3);
				gpTeamC.add(spnLimitNum_TC, 1, 3);
				gpTeamC.add(lblChooseTeams_TC, 0, 4);
				gpTeamC.add(btnshowAllExistingT_TC, 1, 4);

				vbTeamCompetition.getChildren().add(gpTeamC);

				// Results tab of teams competition
				Label lblChooseNOf_TC = new Label("Choose competition number: ");

				ComboBox<Integer> cmbTC_R = new ComboBox<Integer>();
				
				for (int i = 1; i <= 100; i++) {
					cmbTC_R.getItems().add(i);
				}
				
				Button btnShowAllTteam = new Button("Show All Teams");

				Label lblFirsPlaceT = new Label("Choose team for the first place: ");
				
				Button btnSetFirstPlace_TC = new Button("Set First Place");

				Button btnChooseFirstPlace_TC = new Button("Insert");

				Label lblSecondPlaceT = new Label("Choose team for the second place: ");

				Button btnSetSecondPlace_TC = new Button("Set Second Place");
				
				Button btnChooseSecondPlace_TC = new Button("Insert");

				Button btnSetThirdPlace_TC = new Button("Set Third Place");

				Label lblThirdPlaceT = new Label("Choose team for the third place: ");

				Button bntSave_TCR = new Button("Save Results");

				gpTeamCR.add(lblChooseNOf_TC, 0, 0);
				gpTeamCR.add(btnShowAllTteam, 1, 0);
				gpTeamCR.add(cmbTC_R, 2, 0);
				gpTeamCR.add(lblFirsPlaceT, 0, 1);
				gpTeamCR.add(btnSetFirstPlace_TC, 1, 1);
				gpTeamCR.add(lblSecondPlaceT, 0, 2);
				gpTeamCR.add(btnSetSecondPlace_TC, 1, 2);
				gpTeamCR.add(lblThirdPlaceT, 0, 3);
				gpTeamCR.add(btnSetThirdPlace_TC, 1, 3);

				vbTeamCompetitionR.getChildren().add(gpTeamCR);
				
				// Show all team competitions
				btnShowAllTteam.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
 
						for (ViewEventListener l : allListeners) {
						//	String allInfo = l.repositoryAllTCInUI();
							JTextArea textArea = new JTextArea(15, 50);
							textArea.setEditable(false);
					//		textArea.setText(allInfo);
							textArea.setLineWrap(true);
							textArea.setFont(textArea.getFont().deriveFont(14f));
							JScrollPane scrollPane = new JScrollPane(textArea);
							JOptionPane.showMessageDialog(null, scrollPane);
						}
					}
				});

				// Set the competition
				cmbTC_R.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						AllExistingTC = new ListView<Team>();
						for (ViewEventListener l : allListeners) {

							try {
								AllExistingTC.getItems().addAll(l.showAllTCInUI(cmbTC_R.getValue()));
							} catch (Exception e) {
								e.getMessage();
							}
						}
						// creating results
						// First place
						btnSetFirstPlace_TC.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing 1

								Stage showAllTCA1 = new Stage();

								showAllTCA1.initOwner(theStage);
								showAllTCA1.initModality(Modality.WINDOW_MODAL);

								showAllTCA1.setTitle("Click on Team to set first place");

								// new window
								VBox vbShowTFor_TCR1 = new VBox(AllExistingTC, btnChooseFirstPlace_TC);
								vbShowTFor_TCR1.setPadding(new Insets(10));
								vbShowTFor_TCR1.setSpacing(15);
								vbShowTFor_TCR1.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllTCA1.setScene(new Scene(vbShowTFor_TCR1, 700, 400));
								showAllTCA1.show();

								// add the selected first place
								btnChooseFirstPlace_TC.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingTC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										FirstPlaceT = AllExistingTC.getSelectionModel().getSelectedItem();

										showAllTCA1.close();
									}
								});
							}

						});
					

						// Second place
						btnSetSecondPlace_TC.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing 2

								Stage showAllTCT2 = new Stage();

								showAllTCT2.initOwner(theStage);
								showAllTCT2.initModality(Modality.WINDOW_MODAL);

								showAllTCT2.setTitle("Click on Team to set second place");

								// What window
								VBox vbShowTFor_TCR2 = new VBox(AllExistingTC, btnChooseSecondPlace_TC);
								vbShowTFor_TCR2.setPadding(new Insets(10));
								vbShowTFor_TCR2.setSpacing(15);
								vbShowTFor_TCR2.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllTCT2.setScene(new Scene(vbShowTFor_TCR2, 700, 400));
								showAllTCT2.show();

								// add the selected second place
								btnChooseSecondPlace_TC.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingTC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										SecondPlaceT = AllExistingTC.getSelectionModel().getSelectedItem();

										showAllTCT2.close();
									}

								});
							}
						});


						// Third Place
						btnSetThirdPlace_TC.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// repository for choosing third place

								Stage showAllTCT3 = new Stage();

								showAllTCT3.initOwner(theStage);
								showAllTCT3.initModality(Modality.WINDOW_MODAL);

								showAllTCT3.setTitle("Click on Team to set third place");

								// What window
								VBox vbShowAFor_PCR3 = new VBox(AllExistingTC, bntSave_TCR);
								vbShowAFor_PCR3.setPadding(new Insets(10));
								vbShowAFor_PCR3.setSpacing(15);
								vbShowAFor_PCR3.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));

								showAllTCT3.setScene(new Scene(vbShowAFor_PCR3, 700, 400));
								showAllTCT3.show();

								bntSave_TCR.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										AllExistingTC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

										ThirdPlaceT = AllExistingTC.getSelectionModel().getSelectedItem();
										for (ViewEventListener l : allListeners) {
										
												l.setTeamCompetitionsResultsInUI(FirstPlaceT, SecondPlaceT, ThirdPlaceT);
											
										}
										showAllTCT3.close();
									}
								
								});
							}

						});
					
					}
				});

				

			}
		});
		
		// Showing the final winners
		JFrame jf = new JFrame();
		jf.setSize(600, 150);
		jf.setLayout(new GridLayout(20, 50));
		
		btnShowFinalWinners.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			try {
			for (ViewEventListener l : allListeners) {
				String allInfo = l.finalWinnersShowedInUI();
				JTextArea textArea = new JTextArea(15, 50);
				textArea.setEditable(false);
				textArea.setText(allInfo);
				textArea.setLineWrap(true);
				textArea.setFont(textArea.getFont().deriveFont(14f));
				JScrollPane scrollPane = new JScrollPane(textArea);
				JOptionPane.showMessageDialog(null, scrollPane);
				}
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			}
		});

		theStage.setScene(new Scene(vbRoot, 700, 500));
		theStage.show();
	}

	public void registerListener(ViewEventListener newListener) {
		allListeners.add(newListener);
	}

	public void nameNotEntered() {
		JOptionPane.showMessageDialog(null, "Name not entered . . .");
	}

	public void successfullyMessage() {
		JOptionPane.showMessageDialog(null, "Success !");
	}

	public void fileSaved() {
		JOptionPane.showMessageDialog(null, "File saved successfully !");
	}

	public void cantAddMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	public void teamIsAddedMsg() {
		JOptionPane.showMessageDialog(null, "Team Added Successfully");
	}

	public void newAthleteAddedToTeam(String name) {
		JOptionPane.showMessageDialog(null, name + " added successfully");
	}

	public void addedJumperMsg(String name) {
		JOptionPane.showMessageDialog(null, name + " added as Jumper");
	}

	public void addedRunnerMsg(String name) {
		JOptionPane.showMessageDialog(null, name + " added as Runner");
	}

	public void addedJandRMsg(String name) {
		JOptionPane.showMessageDialog(null, name + " added as Jumper and Runner");

	}

	public void athleteaAddedToTeamMsg() {
		JOptionPane.showMessageDialog(null, "Athletes added successfully");

	}

	public void cantSaveF() {
		JOptionPane.showMessageDialog(null, "Problem with saveing file");
	}

	public void cantReadF() {
		JOptionPane.showMessageDialog(null, "Problem with reading file");
	}

	public void readFile() {
		JOptionPane.showMessageDialog(null, "Reading the file . . .");
	}

	public void endedReading() {
		JOptionPane.showMessageDialog(null, "Done !\nFile readed\nAll items added successfuly.");
	}

	public void loadFromFileMsg() {
		JOptionPane.showMessageDialog(null, "Make sure you load the files if needed");
	}

	public void STUpdated() {
		JOptionPane.showMessageDialog(null, "Sport Type updated .");

	}

	public void athleteNMUpdated() {
		JOptionPane.showMessageDialog(null, "Number of medals updated .");
	}

	public void numberOfTMUpdated() {
		JOptionPane.showMessageDialog(null, "Number of medals updated");
	}

	public void showAllItems() {
		JOptionPane.showMessageDialog(null,
				"Open from repository . . .\n (make sure you load the file before pressing \"Show Items\")");
	}

	public void judgeAdded() {
		JOptionPane.showMessageDialog(null, "Judge added successfully .");
	}

	public void stadiumAdded() {
		JOptionPane.showMessageDialog(null, "Stadium added successfully .");
	}
	

	public void athleteDeleted(int athleteNumber) {
		JOptionPane.showMessageDialog(null, "Athlete number " + athleteNumber + " removed from games.");
		
	}

	public void judgeDeleted(int judgeNumber) {
		JOptionPane.showMessageDialog(null, "Judge number " + judgeNumber + " removed from games .");
	}

	public void StadiumNSUpdated() {
		JOptionPane.showMessageDialog(null, "Number of seats updated .");
	}

	public void stadiumDeleted(int stadiumNumber) {
		JOptionPane.showMessageDialog(null, "Stadium number " + stadiumNumber + " removed from games .");
	}

	public void teamDeleted(int teamNumber) {
		JOptionPane.showMessageDialog(null, "Team number " + teamNumber + " removed from games .");
	}

	public void competitionCreated() {
		JOptionPane.showMessageDialog(null, "Competition Successfully created !");
	}

	public void limitPassed() {
		JOptionPane.showMessageDialog(null,
				"	Can't create !\nYou passed limit of athletes\nChange data and try again.");
	}

	public void resultsSaved() {
		JOptionPane.showMessageDialog(null, "	Competition results saved successfully");
	}

	public void noExist_PC(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void cantcreateTC(String message) {
		JOptionPane.showMessageDialog(null, message);		
	}

	public void cantCreatePC(String message) {
		JOptionPane.showMessageDialog(null, message);
		
	}

	public void cantWinPC(String message) {
		JOptionPane.showMessageDialog(null, message);
		
	}

	public void cantWinTC(String message) {
		JOptionPane.showMessageDialog(null, message);		
	}

	public void alredyExist(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void notEnoughInfo(String message) {
		JOptionPane.showMessageDialog(null, message);		
	}

	public void blaBla() {
		// TODO Auto-generated method stub

	}

	public String blaBla2() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Athlete> blaBla3() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Team> blaBla4() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> blaBla5() {
		// TODO Auto-generated method stub
		return null;
	}

}
