package GUI;

import factory.CarRaceBuilder;
import factory.RaceBuilder;
import factory.RacingClassesFinder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.decorator.ColoredRacer;
import game.racers.decorator.WheeledRacer;
import game.racers.prototype.Prototype;
import game.racers.prototype.PrototypeFactory;
import utilities.EnumContainer;
import game.racers.Racer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * GUI class - Includes all the Variables and functions for the graphical interface
 * @author : Tzvi Puchinsky 
 */
public class GUI extends JFrame {

    private final static String TITLE = "Race";
    private boolean startRaceFlag = false;
    private boolean newArenaFlag = false;

    /**
     * Default Constructor for GUI sets the title and default size of the Jframe
     * and adds the Panel needed to display
     */
    public GUI() {
        super(TITLE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 815);
        this.setResizable(false);

        this.add(new firstPanel(this));
        this.setVisible(true);
    }

    /**
     * First Panel extends JPanel
     * Includes all the comboBox's,textFields,Buttons to build the Gui needed.
     * Also includes all the functionality of the Panel via actionListener functions.
     */
    class firstPanel extends JPanel implements ActionListener, Observer {

        private RacingClassesFinder finder = RacingClassesFinder.getInstance();

        private JButton buttonBuildArena;

        private JComboBox<String> arenaTypes;
        private JComboBox<String> racerTypes;
        private String[] racerColor = new String[]{"Red", "Green", "Blue", "Black", "Yellow"};

        private ArrayList<String> racerList;
        private ArrayList<String> arenaList;

        private JTextField textArenaLength;
        private JTextField textMaxRacerNumber;

        private JComboBox dropColor;
        private JTextField textRacerName;
        private JTextField textMaxSpeed;
        private JTextField textAcceleration;
        private JButton buttonAddRacer;

        private JButton buttonStartRace;
        private JButton buttonShowInfo;


        //HW4
        //State
        private JButton buttonConsole;
        private JTextArea textArea;

        //Prototype
        private JComboBox dropExistingRacer;
        private JComboBox dropPrototypeColors;
        private JButton buttonPrototypeBuild;
        private JTextField textSerialNumber;

        //Builder - Car Race
        private JTextField textCarRaceMaxNumber;
        private JButton buttonCreateCarRace;

        //Decorator
        private JComboBox dropDecoretorColors;
        private JTextField textNumberOfWheels;
        private JButton buttonCustom;

        //=>HW4

        private int arenaLength;
        private static final int ArenaLengthDefault = 1000;
        private int maxRacers;
        private static final int MaxRacerDefault = 8;
        private String racerName;
        private double maxSpeed;
        private static final int maxSpeedDefault = 20;
        private double acceleration;

        private ImageIcon racerIcon;

        //~~~~~~~~~~
        private static final String arenaConstructorPath = "game.arenas.";
        private String arenaTypeConstructor = arenaConstructorPath + "air.AerialArena";
        private String surface;

        private static final String racerConstructorPath = "game.racers.";

        private Arena arena;
        private RaceBuilder builder = RaceBuilder.getInstance();

        private Racer racer;
        private picturePanel picPanel;

        private JFrame base;
        private int arenaHeight = 815;


        //~~~~~~~~~~
        ////////Table/////
        private JDialog tableInfo;
        private String[] columnNames = {"Racer Name", "Current Speed", "Max Speed", "Current X location", "Finished"};
        private String[][] data;
        ////////////////////

        Box first = Box.createVerticalBox();
        Box picture = Box.createVerticalBox();

        /**
         * Panel constructor - initializing the gui interface with all the buttons
         * uses 2 box components first is the right panel with all the functions and picture includes all the graphoics
         * with background and icons of the racers.
         * @param baseFrame
         * To this constructor we send the baseFrame "GUI extends JFrame" so we could change the size of the frame
         * while in firstPanel after the user enters the arena length and number of racers.
         */
        public firstPanel(JFrame baseFrame) {

            racerList = new ArrayList<>();
            for (String path : finder.getRacersList()){
                racerList.add(path);
            }

            arenaList = new ArrayList<>();
            for (String path: finder.getArenasList()){
                arenaList.add(path);
            }

            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            base = baseFrame; //Copy reference from the base layer of the gui

            Dimension yGap = new Dimension(0, 10);
            Dimension maxSize = new Dimension(100, 24);

            //Labels and DropDowns and text
            JLabel labelChooseArena = new JLabel("Choose Arena:");
            labelChooseArena.setAlignmentX(Component.LEFT_ALIGNMENT);
            // create a combo box with the fixed array:
            arenaTypes = new JComboBox<>();
            for (String name : finder.getArenasNamesList()){
                arenaTypes.addItem(name);
            }
            arenaTypes.setSelectedIndex(0); // Sets default value to AerialArena if not changed
            arenaTypes.addActionListener(this); //ActionListener
            arenaTypes.setMaximumSize(maxSize);
            arenaTypes.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel labelArenaLength = new JLabel("Arena Length:");
            labelArenaLength.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.textArenaLength = new JTextField("1000");
            arenaLength = ArenaLengthDefault;
            textArenaLength.addActionListener(this);
            textArenaLength.setMaximumSize(maxSize);
            textArenaLength.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel labelMaxRacersNumber = new JLabel("Max Racers Number:");
            labelMaxRacersNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.textMaxRacerNumber = new JTextField("8");
            maxRacers = MaxRacerDefault;
            textMaxRacerNumber.addActionListener(this);
            textMaxRacerNumber.setMaximumSize(maxSize);
            textMaxRacerNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.buttonBuildArena = new JButton("Build Arena");
            buttonBuildArena.addActionListener(this); //ActionListener
            buttonBuildArena.setAlignmentX(Component.LEFT_ALIGNMENT);

            JSeparator bottom = new JSeparator(SwingConstants.HORIZONTAL);
            JSeparator bottom2 = new JSeparator(SwingConstants.HORIZONTAL);
            JSeparator bottom3 = new JSeparator(SwingConstants.HORIZONTAL);
            // ~~~~~~~~~~~~ End first Box ~~~~~~~~~~~~~~~~~~
            JLabel labelChooseRacer = new JLabel("Choose racer: ");
            labelChooseRacer.setAlignmentX(Component.LEFT_ALIGNMENT);
            // create a combo box with the fixed array:
            racerTypes = new JComboBox<>();
            for (String name : finder.getRacersNamesList()){
                racerTypes.addItem(name);
            }
            racerTypes.addActionListener(this);
            racerTypes.setMaximumSize(maxSize);
            racerTypes.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel labelChooseColor = new JLabel("Choose Color: ");
            labelChooseColor.setAlignmentX(Component.LEFT_ALIGNMENT);
            // create a combo box with the fixed array:
            this.dropColor = new JComboBox<String>(racerColor);
            dropColor.addActionListener(this);
            dropColor.setMaximumSize(maxSize);
            dropColor.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Racer Name
            JLabel labelRacerName = new JLabel("Racer Name: ");
            labelRacerName.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.textRacerName = new JTextField("");
            textRacerName.addActionListener(this);
            textRacerName.setMaximumSize(maxSize);
            textRacerName.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Max Speed
            JLabel labelMaxSpeed = new JLabel("Max Speed: ");
            labelMaxSpeed.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.textMaxSpeed = new JTextField("");
            textMaxSpeed.addActionListener(this);
            textMaxSpeed.setMaximumSize(maxSize);
            textMaxSpeed.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Acceleration
            JLabel labelAcceleration = new JLabel("Acceleration: ");
            labelAcceleration.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.textAcceleration = new JTextField("");
            textAcceleration.addActionListener(this);
            textAcceleration.setMaximumSize(maxSize);
            textAcceleration.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.buttonAddRacer = new JButton("Add Racer");
            buttonAddRacer.addActionListener(this);
            buttonAddRacer.setMaximumSize(maxSize);
            buttonAddRacer.setAlignmentX(Component.LEFT_ALIGNMENT);
            // ~~~~~~~~~~~~ End Second Box ~~~~~~~~~~~~~~~~~~
            this.buttonStartRace = new JButton("Start Race");
            buttonStartRace.addActionListener(this);
            buttonStartRace.setMaximumSize(maxSize);
            buttonStartRace.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.buttonShowInfo = new JButton("Show Info");
            buttonShowInfo.addActionListener(this);
            buttonShowInfo.setMaximumSize(maxSize);
            buttonShowInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
            //State
            this.buttonConsole = new JButton("Console");
            buttonConsole.addActionListener(this);
            buttonConsole.setMaximumSize(maxSize);
            buttonConsole.setAlignmentX(Component.LEFT_ALIGNMENT);
            textArea = new JTextArea(20, 40);
            //Decorator
            JLabel labelDecorateColor = new JLabel("Decorate Color");
            this.dropDecoretorColors = new JComboBox<>(racerColor);
            dropDecoretorColors.setMaximumSize(maxSize);
            dropDecoretorColors.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel labelAddWheels = new JLabel("Add Wheels");
            this.textNumberOfWheels = new JTextField();
            textNumberOfWheels.setMaximumSize(maxSize);
            textNumberOfWheels.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.buttonCustom = new JButton("Custom");
            buttonCustom.setMaximumSize(maxSize);
            buttonCustom.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonCustom.addActionListener(this);

            //Prototype
            JLabel labelExistingRacer = new JLabel("Existing Racer:");
            labelExistingRacer.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.dropExistingRacer = new JComboBox();
            dropExistingRacer.setMaximumSize(maxSize);
            dropExistingRacer.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel labelPrototypeColor = new JLabel("Color:");
            labelPrototypeColor.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.dropPrototypeColors = new JComboBox<>(racerColor);
            dropPrototypeColors.setMaximumSize(maxSize);
            dropPrototypeColors.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel labelSerialNumber = new JLabel("Change Serial Number:");
            labelSerialNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.textSerialNumber = new JTextField("");
            textSerialNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
            textSerialNumber.setMaximumSize(maxSize);
            this.buttonPrototypeBuild = new JButton("Prototype");
            buttonPrototypeBuild.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonPrototypeBuild.addActionListener(this);
            buttonPrototypeBuild.setMaximumSize(maxSize);

            //Builder - Car Race
            JLabel labelCarRace = new JLabel("Car Race Builder");
            labelCarRace.setAlignmentX(Component.LEFT_ALIGNMENT);
            labelCarRace.setMaximumSize(maxSize);
            JLabel labelCarRaceMaxRacers = new JLabel("Enter Max Racers:");
            labelCarRaceMaxRacers.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.textCarRaceMaxNumber = new JTextField("");
            textCarRaceMaxNumber.setMaximumSize(maxSize);
            textCarRaceMaxNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.buttonCreateCarRace = new JButton("Car Race");
            buttonCreateCarRace.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonCreateCarRace.setMaximumSize(maxSize);
            buttonCreateCarRace.addActionListener(this);





            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            //First Box
            first.add(labelChooseArena);
            first.add(arenaTypes);
            first.add(labelArenaLength);
            first.add(textArenaLength);
            first.add(labelMaxRacersNumber);
            first.add(textMaxRacerNumber);
            first.add(buttonBuildArena);
            first.add(Box.createRigidArea(yGap));
            //Second Box
            first.add(labelChooseRacer);
            first.add(racerTypes);
            first.add(labelChooseColor);
            first.add(dropColor);
            first.add(labelRacerName);
            first.add(textRacerName);
            first.add(labelMaxSpeed);
            first.add(textMaxSpeed);
            first.add(labelAcceleration);
            first.add(textAcceleration);
            first.add(buttonAddRacer);
            first.add(labelDecorateColor);
            first.add(dropDecoretorColors);
            first.add(labelAddWheels);
            first.add(textNumberOfWheels);
            first.add(buttonCustom);
            first.add(Box.createRigidArea(yGap));
            //Prototype
            first.add(labelExistingRacer);
            first.add(dropExistingRacer);
            first.add(labelPrototypeColor);
            first.add(dropPrototypeColors);
            first.add(buttonPrototypeBuild);
            first.add(Box.createRigidArea(yGap));
            //Car Race
            first.add(labelCarRace);
            first.add(labelCarRaceMaxRacers);
            first.add(textCarRaceMaxNumber);
            first.add(buttonCreateCarRace);
            //Third Box
            first.add(Box.createRigidArea(yGap));
            first.add(buttonStartRace);
            first.add(buttonShowInfo);
            first.add(Box.createRigidArea(new Dimension(0,5)));
            first.add(buttonConsole);

            picture.setPreferredSize(new Dimension(900, 700)); //Sets the size of the Picture
            first.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            picPanel = new picturePanel();
            picture.add(picPanel);
            this.add(picture);
            this.add(new JSeparator(SwingConstants.VERTICAL));
            this.add(first);

        }

        /**
         * Build Arena Panel
         * This function reacts to every component in the first panel.
         * Arena Length Text Box - User enters valid data to text box and presses "enter" the data is stored in variable called arenaLength
         * if data not valid shows Message Box and puts default value in arenaLength
         * valid data is set to be between 100 and 3000
         *
         * Max Racers Number Text Box - User enters valid data to text box and presses "enter" than the data is stored in variable called
         * maxRacers.
         * If Data not valid shows Message Box and puts default value in maxRacers.
         * Valid data is set to be between 0 and 20.
         *
         * Build Arena Button - When user presses this button the program checks for the Drop box to get the arena type needed to be build
         * and the 2 text box fields to get information for the Arena Constructor, if data is valid builds Arena object.
         * Else show's message box with "Invalid Data"
         * Also after arena was built and race started checks if race is ended using flags to determinate state.
         *
         * Racer Panel
         * Racer Name Text Box - User enters name's for racers.
         * Max Speed Text Box - User enters Max speed of the racer.
         * Acceleration Text Box - User enters Acceleration of the racer.
         * Add Racer Button - After user presses add racer button all the relevant fields checks for valid data and stored in variables to be
         * sent to the Racer Builder for him to create an Racer object to be added to arena that was built before.
         * If racer was added successfully activates setIcon function to print icon of the racer on the Board.
         * This part has try\catch to deal with exception might be thrown using the builder and other invalid data.
         *
         * Start Race\Show Info panel
         * Start Race Button - After user presses Start Button there is first check if arena != null than check if
         * arena has activeRacers and only than uses arena functions to initialize every racer and START RACE function.
         * START RACE function uses mulithreading to run all the racers.
         * Show Info Button - If user presses the button it shows the current data of all the racers in table, each time
         * the user presses the button the table is updated.
         *
         * Added:
         * Decorator Design Pattern - Now the user can add another color and wheels to all racer types. If added wheels can add any type to Land Arena.
         * Builder Design Pattern = The user can build Car Land Race using only the number of racers the user inputs.
         * @param e
         * Panel Event
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            //~~~~~~~~~~ Arena Panel ~~~~~~~~~~
            if (e.getSource() == textArenaLength) {
                try {
                    this.arenaLength = Integer.parseInt(textArenaLength.getText());
                    if ((arenaLength <= 100) || (arenaLength >= 3000)) {
                        arenaLength = ArenaLengthDefault;
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                    }
                    System.out.println(arenaLength);
                } catch (Exception exp) {
                    System.out.println("Invalid input values! Please try again");
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                }

            } else if (e.getSource() == textMaxRacerNumber) {
                try {
                    this.maxRacers = Integer.parseInt(textMaxRacerNumber.getText());
                    if ((maxRacers <= 0) || (maxRacers >= 20)) {
                        maxRacers = MaxRacerDefault;
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                    }
                    System.out.println(maxRacers);
                } catch (Exception exp) {
                    System.out.println("Invalid input values! Please try again");
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                }
            } else if (e.getSource() == buttonBuildArena) {
                //Get user Input
                //Arena Length
                if ( arena != null && arena.getActiveRacers().size() == 0){
                    startRaceFlag = false;
                    newArenaFlag = true;
                }
                if (!startRaceFlag) {
                try {
                    this.arenaLength = Integer.parseInt(textArenaLength.getText());
                    if ((arenaLength < 100) || (arenaLength > 3000)) {
                        arenaLength = ArenaLengthDefault;
                        throw new IllegalArgumentException();

                    }
                    System.out.println(arenaLength);
                    ///////////////////
                    //Max Racers
                    this.maxRacers = Integer.parseInt(textMaxRacerNumber.getText());
                    if ((maxRacers <= 0) || (maxRacers >= 20)) {
                        maxRacers = MaxRacerDefault;
                        throw new IllegalArgumentException();

                    }
                    System.out.println(maxRacers);
                    ////////////////////
                    System.out.println("Iam Build Arena Button");
                    if (newArenaFlag)picPanel.removeFigs();
                    textArea = new JTextArea(20, 40);
                    newArenaFlag = false;

                        arena = builder.buildArena(arenaList.get(arenaTypes.getSelectedIndex()), arenaLength, maxRacers);
                        setArenaImage(arenaTypes.getSelectedIndex());
                        if ((maxRacers * 75) >= 700) arenaHeight = (maxRacers * 75);

                        base.setSize(arenaLength + 150, arenaHeight + 50); // changes the size of the window
                        picture.setPreferredSize(new Dimension(arenaLength, arenaHeight)); //Sets the size of the Picture

                        JOptionPane.showMessageDialog(null, "Build Arena Successful!");
                        dropExistingRacer.removeAllItems();
                    } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                            | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e1){
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                        System.out.println("Unable to build arena!");
                    }
                }
                else if (arena.getActiveRacers().size() != 0)
                        JOptionPane.showMessageDialog(null, "Wait for Race to Finish");

            }

            //~~~~~~~~~~ Racer Panel ~~~~~~~~~~
             else if (e.getSource() == textRacerName) {
                try {
                    this.racerName = textRacerName.getText();
                    if (this.racerName.equals("")) {
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                    }
                    System.out.println(racerName);
                } catch (Exception exp) {
                    System.out.println("Invalid input values! Please try again");
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                }
            } else if (e.getSource() == textMaxSpeed) {
                try {
                    this.maxSpeed = Double.parseDouble(textMaxSpeed.getText());
                    if (maxSpeed <= 0) {
                        maxSpeed = maxSpeedDefault;
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                    }
                    System.out.println(maxSpeed);
                } catch (Exception exp) {
                    System.out.println("Invalid input values! Please try again");
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                }
            } else if (e.getSource() == textAcceleration) {
                try {
                    this.acceleration = Double.parseDouble(textAcceleration.getText());
                    if (acceleration <= 0) {
                        acceleration = 0;
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                    }
                    System.out.println(acceleration);
                } catch (Exception exp) {
                    System.out.println("Invalid input values! Please try again");
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                }
            }
             else if (e.getSource() == buttonAddRacer) {
                if (!startRaceFlag) {
                    try {
                        this.racerName = textRacerName.getText();
                        if (this.racerName.equals("")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println(racerName);
                        this.maxSpeed = Double.parseDouble(textMaxSpeed.getText());
                        if (maxSpeed <= 0) {
                            maxSpeed = maxSpeedDefault;
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                        }
                        System.out.println(maxSpeed);
                        this.acceleration = Double.parseDouble(textAcceleration.getText());
                        if (acceleration <= 0) {
                            acceleration = 0;
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                        }
                        if (!(arena.getActiveRacers().size() < arena.getMAX_RACERS()))throw new RacerLimitException("");
                            if (racerTypes.getSelectedIndex() == 0 | racerTypes.getSelectedIndex() == 2 | racerTypes.getSelectedIndex() == 3) {
                                racer = builder.buildWheeledRacer(racerList.get(racerTypes.getSelectedIndex()), racerName, maxSpeed, acceleration, EnumContainer.Color.values()[dropColor.getSelectedIndex()], 3);
                            } else
                                racer = builder.buildRacer(racerList.get(racerTypes.getSelectedIndex()), racerName, maxSpeed, acceleration, EnumContainer.Color.values()[dropColor.getSelectedIndex()]);
                        arena.addRacer(racer);
                        PrototypeFactory.setPrototypes(racer.className(), (Prototype) racer);
                        dropExistingRacer.addItem(racer.getSerialNumber() + " " + racer.getName());
                        setRacerIcon(racer);
                        racer.addObserver(this);

                        } catch(RacerLimitException el){
                            JOptionPane.showMessageDialog(null, "Reached to Max racers for this arena! ");
                        } catch(RacerTypeException el){
                            JOptionPane.showMessageDialog(null, "Racer type dont match arena type! ");
                        } catch
                        (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1){
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                        }
                        catch(Exception exp){
                            System.out.println("Invalid input values! Please try again");
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                        }


                }
                else{
                    JOptionPane.showMessageDialog(null, "Race In Progress ");
                }
                if (arena != null) {
                    System.out.println("Iam Add Racer Button");
                } else
                    JOptionPane.showMessageDialog(null, "Please build arena before adding racers! ");
            }


            //~~~~~~~~~~ Start/Info Panel ~~~~~~~~~~
            else if (e.getSource() == buttonStartRace) {
                if (!startRaceFlag) {
                    if (arena != null) {
                        if (arena.hasActiveRacers()) {
                            arena.initRace();
                            startRace();
                        } else
                            JOptionPane.showMessageDialog(null, "Please add racer to Start Race! ");

                    } else
                        JOptionPane.showMessageDialog(null, "Please build arena and add racer to Start Race! ");
                    System.out.println("Iam Start Race Button");
                }
                else
                    JOptionPane.showMessageDialog(null, "Wait for Race to Finish (or Build new Arena) ");
            } else if (e.getSource() == buttonShowInfo) {
                if (arena != null) {
                    data = new String[arena.getMAX_RACERS()][5];
                    JTable table = new JTable(data, columnNames);
                    JScrollPane s = new JScrollPane(table);
                    updateTable();
                    JOptionPane.showConfirmDialog(null,s , "Racers Info", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null);
                }
                else
                    JOptionPane.showMessageDialog(null, "Please build arena and add racer to Start Race! ");
                System.out.println("Iam Show info Button");
            }
            // Console Button [HW4]
            else if (e.getSource() == buttonConsole){
                textArea.append(arena.getEvents());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setEditable(false);
                JOptionPane.showConfirmDialog(null,scrollPane , "Console", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null);

            }
            // Prototype [HW4]
            else if (e.getSource() == buttonPrototypeBuild){
                if (arena != null && dropExistingRacer.getItemCount() > 0 && arena.getActiveRacers().size() > 0) {
                    try {
                        if (!(arena.getActiveRacers().size() < arena.getMAX_RACERS()))
                            throw new RacerLimitException("");
                        Racer tempRacer = arena.getActiveRacers().get(dropExistingRacer.getSelectedIndex());
                        Racer prototypeRacer = PrototypeFactory.getPrototype(tempRacer.className());
                        prototypeRacer.setColor(EnumContainer.Color.values()[dropPrototypeColors.getSelectedIndex()]);
                        arena.addRacer(prototypeRacer);
                        dropExistingRacer.addItem(prototypeRacer.getSerialNumber() + " " + prototypeRacer.getName());
                        setRacerIcon(prototypeRacer);
                    } catch (RacerLimitException e1) {
                        JOptionPane.showMessageDialog(null, "Reached to Max racers for this arena! ");
                    } catch (RacerTypeException e1) {
                        e1.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Missing Values ");

            }

            //Builder - Car Race [HW4]
            else if (e.getSource() == buttonCreateCarRace){
                dropExistingRacer.removeAllItems();
                picPanel.removeFigs();
                try {
                    int MaxRacers = Integer.parseInt(textCarRaceMaxNumber.getText());
                    if (MaxRacers <= 0)throw new NumberFormatException();
                    CarRaceBuilder carRace = new CarRaceBuilder.Builder().setArena("Land", MaxRacers).setRacerUsingPrototype().build();
                    arena = carRace.getCarArena();
                    maxRacers = arena.getMAX_RACERS();
                    arenaLength = (int) arena.getLength();
                    setArenaImage(1);// 1 - Land Image
                    if ((maxRacers * 75) >= 700) arenaHeight = (maxRacers * 75);

                    base.setSize(arenaLength + 150, arenaHeight + 50); // changes the size of the window
                    picture.setPreferredSize(new Dimension(arenaLength, arenaHeight)); //Sets the size of the Picture

                    for (Racer racer : arena.getActiveRacers()) {
                        racer.setArena(arena);
                        setRacerIcon(racer);
                        racer.addObserver(this);
                    }

                    startRaceFlag = false;

                    JOptionPane.showMessageDialog(null, "Build Car Race Successful!");
                }
                catch (NumberFormatException t){
                    JOptionPane.showMessageDialog(null, "Invalid Values");
                }

            }
            //Decorator [HW 4]
            else if (e.getSource() == buttonCustom){
                if (!startRaceFlag) {
                    try {
                        this.racerName = textRacerName.getText();
                        if (this.racerName.equals("")) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println(racerName);
                        this.maxSpeed = Double.parseDouble(textMaxSpeed.getText());
                        if (maxSpeed <= 0) {
                            maxSpeed = maxSpeedDefault;
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                        }
                        System.out.println(maxSpeed);
                        this.acceleration = Double.parseDouble(textAcceleration.getText());
                        if (acceleration <= 0) {
                            acceleration = 0;
                            JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");

                        }
                        if (!(arena.getActiveRacers().size() < arena.getMAX_RACERS()))throw new RacerLimitException("");
                        if (racerTypes.getSelectedIndex() == 0 | racerTypes.getSelectedIndex() == 2 | racerTypes.getSelectedIndex() == 3) {
                            racer = builder.buildWheeledRacer(racerList.get(racerTypes.getSelectedIndex()), racerName, maxSpeed, acceleration, EnumContainer.Color.values()[dropColor.getSelectedIndex()], 3);
                        } else
                            racer = builder.buildRacer(racerList.get(racerTypes.getSelectedIndex()), racerName, maxSpeed, acceleration, EnumContainer.Color.values()[dropColor.getSelectedIndex()]);
                        //Decorator
                        new ColoredRacer(racer,EnumContainer.Color.values()[dropDecoretorColors.getSelectedIndex()]);
                        racer.setColor(EnumContainer.Color.values()[dropDecoretorColors.getSelectedIndex()]);
                        int numWheels = Integer.parseInt(textNumberOfWheels.getText());
                        if (numWheels > 0)new WheeledRacer(racer,numWheels);

                        ///////////////////////////////
                        arena.addRacer(racer);
                        PrototypeFactory.setPrototypes(racer.className(), (Prototype) racer);
                        dropExistingRacer.addItem(racer.getSerialNumber() + " " + racer.getName());
                        setRacerIcon(racer);

                    } catch(RacerLimitException el){
                        JOptionPane.showMessageDialog(null, "Reached to Max racers for this arena! ");
                    } catch(RacerTypeException el){
                        JOptionPane.showMessageDialog(null, "Racer type dont match arena type! ");
                    } catch
                            (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1){
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                    }
                    catch(Exception exp){
                        System.out.println("Invalid input values! Please try again");
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again");
                    }


                }
                else{
                    JOptionPane.showMessageDialog(null, "Race In Progress ");
                }
                if (arena != null) {
                    System.out.println("Iam Add Racer Button");
                } else
                    JOptionPane.showMessageDialog(null, "Please build arena before adding racers! ");
            }


        }

        /**
         * Activates arena START RACE function and repaints the picture panel afterwards.
         * Sets START RACE FLAG to true after race is finished.
         */
        private void startRace() {
            System.out.println("Introduction: ");
            for (Racer racer : arena.getActiveRacers())
                racer.introduce();
            System.out.println("Start Race!");
                arena.startRace(this);
                picPanel.repaint();
            System.out.println("Race Completed!");
            arena.showResults();
            startRaceFlag = true;
        }

        /**
         * Function for creating Icons object for each racer and set it up to display
         * @param racer
         * Racer object that user inputted before
         */
        public void setRacerIcon(Racer racer) {
            picPanel.setRacerIcon(racer);
            picPanel.repaint();

        }

        /**
         * Function for drawing the background image of the arena that selected.
         * @param index
         * Index of the Arena Drop Box selected
         */
        public void setArenaImage(int index) {
            String PATH = null;
            switch (index) {
                case 0:
                    PATH = "\\AerialArena.jpg";
                    break;
                case 1:
                    PATH = "\\LandArena.jpg";
                    break;
                case 2:
                    PATH = "\\NavalArena.jpg";
                    break;
            }
            picPanel.setBackGround(PATH);
            picPanel.repaint();

        }

        /**
         * Creating JTable each time the function activates. Takes all the data from arrays to display.
         */
        public synchronized void updateTable() {
            int rowCounter = 0;
            //Completed Racers Array
            if (arena.getCompletedRacers() != null && arena.getCompletedRacers().size() != 0) {
                for (int i = 0; i < arena.getCompletedRacers().size(); i++) {
                    if (arena.getCompletedRacers().get(i) != null) {
                        data[rowCounter][0] = arena.getCompletedRacers().get(i).getName();
                        data[rowCounter][1] = String.valueOf(arena.getCompletedRacers().get(i).getCurrentSpeed());
                        data[rowCounter][2] = String.valueOf(arena.getCompletedRacers().get(i).getMaxSpeed());
                        data[rowCounter][3] = String.valueOf(arena.getCompletedRacers().get(i).getCurrentLocation().getX());
                        if (arena.getCompletedRacers().get(i).getCurrentLocation().getX() >= arena.getLength())
                            data[rowCounter][4] = "Yes";
                        else
                            data[rowCounter][4] = "No";
                        rowCounter++;
                    }
                }

            }
            //Active Racers Array
            if (arena.getActiveRacers() != null && arena.getActiveRacers().size() != 0) {
                for (int i = 0; i < arena.getActiveRacers().size(); i++) {
                    if (arena.getActiveRacers().get(i) != null) {
                        data[rowCounter][0] = arena.getActiveRacers().get(i).getName();
                        data[rowCounter][1] = String.valueOf(arena.getActiveRacers().get(i).getCurrentSpeed());
                        data[rowCounter][2] = String.valueOf(arena.getActiveRacers().get(i).getMaxSpeed());
                        data[rowCounter][3] = String.valueOf(arena.getActiveRacers().get(i).getCurrentLocation().getX());
                        if (arena.getActiveRacers().get(i).getCurrentLocation().getX() >= arena.getLength())
                            data[rowCounter][4] = "Yes";
                        else
                            data[rowCounter][4] = "No";
                        rowCounter++;
                    }

                }
            }

            //Disabled Racers Array
            if (arena.getDisabledRacers() != null && arena.getDisabledRacers().size() != 0) {
                for (int i = 0; i < arena.getDisabledRacers().size(); i++) {
                    if (arena.getDisabledRacers().get(i) != null) {
                        data[rowCounter][0] = arena.getDisabledRacers().get(i).getName();
                        data[rowCounter][1] = String.valueOf(arena.getDisabledRacers().get(i).getCurrentSpeed());
                        data[rowCounter][2] = String.valueOf(arena.getDisabledRacers().get(i).getMaxSpeed());
                        data[rowCounter][3] = String.valueOf(arena.getDisabledRacers().get(i).getCurrentLocation().getX());
                        data[rowCounter][4] = "Failed";

                        rowCounter++;
                    }
                }
            }
        }

        /**
         * Update Function for Observable functions Update the GUI every time racer moves
         * @param o
         * Object that did the notify
         * @param arg
         * The argument the object sent
         */
        @Override
        public void update(Observable o, Object arg) {
            if (arg == "Moved"){
                    try {
                        picPanel.repaint();
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

    }

}




