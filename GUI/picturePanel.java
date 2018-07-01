package GUI;

import game.racers.Racer;
import utilities.EnumContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//@author : Tzvi Puchinsky 
/**
 * PicturePanel class uses JPanel - this class is set to draw the images needed.
 * Also stores all the racers icons that created each time new Racer is added.
 */
class picturePanel extends JPanel {

    private int checkLength;

    private final static String ICON_PATH = "src\\icons";
    private Image backGround;

    private Image racerIcon;
    private String RACER_PATH = "\\AirplaneBlack.png";

    private ArrayList<racerIconObj> icons;

    private ArrayList<Racer> racers = new ArrayList<>();

    private int iconWidthDefault = 75;
    private int iconHeightDefault = 75;
    private int heightCount = 0;

    /**
     * Default Constructor
     */
    picturePanel(){
        icons = new ArrayList<>();
        //Empty -  No back ground, All parameters set to null
    }

    /**
     * This functions gets the needed image for background per arena chosen.
     * @param path
     * The path to the icon needed.
     */
    public void setBackGround(String path){
        try {
            backGround = ImageIO.read(new File(ICON_PATH + path)); //Sets Picture Back ground
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the racer icon needed depends on racer type
     * And adds to array of icon and racers to store the locations and the changes for further drawing.
     * @param racer
     * Racer object
     */
    public void setRacerIcon(Racer racer){
        String racerType = racer.className();
        EnumContainer.Color racerColor = racer.getColor();
        switch (racerColor){
            case BLUE:
                switch (racerType){
                    case "Airplane": RACER_PATH = "\\AirplaneBlue.png";
                    break;
                    case "Helicopter": RACER_PATH = "\\HelicopterBlue.png";
                        break;
                    case "Car": RACER_PATH = "\\CarBlue.png";
                        break;
                    case "Bicycle": RACER_PATH = "\\BicycleBlue.png";
                        break;
                    case "Horse": RACER_PATH = "\\HorseBlue.png";
                        break;
                    case "RowBoat": RACER_PATH = "\\RowboatBlue.png";
                        break;
                    case "SpeedBoat": RACER_PATH = "\\SpeedboatBlue.png";
                        break;

                }
                break;
            case RED:
                switch (racerType){
                    case "Airplane": RACER_PATH = "\\AirplaneRed.png";
                        break;
                    case "Helicopter": RACER_PATH = "\\HelicopterRed.png";
                        break;
                    case "Car": RACER_PATH = "\\CarRed.png";
                        break;
                    case "Bicycle": RACER_PATH = "\\BicycleRed.png";
                        break;
                    case "Horse": RACER_PATH = "\\HorseRed.png";
                        break;
                    case "RowBoat": RACER_PATH = "\\RowboatRed.png";
                        break;
                    case "SpeedBoat": RACER_PATH = "\\SpeedboatRed.png";
                        break;

                }
                break;
            case GREEN:
                switch (racerType){
                    case "Airplane": RACER_PATH = "\\AirplaneGreen.png";
                        break;
                    case "Helicopter": RACER_PATH = "\\HelicopterGreen.png";
                        break;
                    case "Car": RACER_PATH = "\\CarGreen.png";
                        break;
                    case "Bicycle": RACER_PATH = "\\BicycleGreen.png";
                        break;
                    case "Horse": RACER_PATH = "\\HorseGreen.png";
                        break;
                    case "RowBoat": RACER_PATH = "\\RowboatGreen.png";
                        break;
                    case "SpeedBoat": RACER_PATH = "\\SpeedboatGreen.png";
                        break;

                }
                break;
            case BLACK:
                switch (racerType){
                    case "Airplane": RACER_PATH = "\\AirplaneBlack.png";
                        break;
                    case "Helicopter": RACER_PATH = "\\HelicopterBlack.png";
                        break;
                    case "Car": RACER_PATH = "\\CarBlack.png";
                        break;
                    case "Bicycle": RACER_PATH = "\\BicycleBlack.png";
                        break;
                    case "Horse": RACER_PATH = "\\HorseBlack.png";
                        break;
                    case "RowBoat": RACER_PATH = "\\RowboatBlack.png";
                        break;
                    case "SpeedBoat": RACER_PATH = "\\SpeedboatBlack.png";
                        break;

                }
                break;
            case YELLOW:
                switch (racerType){
                    case "Airplane": RACER_PATH = "\\AirplaneYellow.png";
                        break;
                    case "Helicopter": RACER_PATH = "\\HelicopterYellow.png";
                        break;
                    case "Car": RACER_PATH = "\\CarYellow.png";
                        break;
                    case "Bicycle": RACER_PATH = "\\BicycleYellow.png";
                        break;
                    case "Horse": RACER_PATH = "\\HorseYellow.png";
                        break;
                    case "RowBoat": RACER_PATH = "\\RowboatYellow.png";
                        break;
                    case "SpeedBoat": RACER_PATH = "\\SpeedboatYellow.png";
                        break;

                }
                break;


        }
        try {
            racerIcon = ImageIO.read(new File(ICON_PATH + RACER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        icons.add(new racerIconObj(iconWidthDefault,iconHeightDefault, (int) racer.getCurrentLocation().getX(),heightCount,this.racerIcon));
        racers.add(racer);
        heightCount += 75;
    }

    /**
     * Sets all the variables back to zero and new empty arrays to arrange empty arena.
     */
    public void removeFigs(){
        heightCount = 0;
        racers = new ArrayList<>();
        icons = new ArrayList<>();
    }

    /**
     * This function draws all the component on the screen using built-in drawImage function.
     * @param g
     * Graphic object
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Dimension dm = getSize();

        if (backGround != null)
            g.drawImage(backGround, 0, 0, dm.width, dm.height, this);

        for (int i = 0 ; i < racers.size(); i++) {
            //Checks if icon is to be set at the end of the arena pic => 75 -  width of the icon
            if ((int) racers.get(i).getCurrentLocation().getX() < racers.get(i).getArena().getLength() - 100) {
                g.drawImage(icons.get(i).getIcon(), (int) racers.get(i).getCurrentLocation().getX(), icons.get(i).getPositionY(), icons.get(i).getIconWidth(), icons.get(i).getIconHeight(), this);
            } else {
                checkLength = (int) racers.get(i).getArena().getLength() - 75;
//                g.drawImage(icons.get(i).getIcon(), (int) racers.get(i).getArena().getLength() - 75, icons.get(i).getPositionY(), icons.get(i).getIconWidth(), icons.get(i).getIconHeight(), this);
                g.drawImage(icons.get(i).getIcon(), dm.width - 75, icons.get(i).getPositionY(), icons.get(i).getIconWidth(), icons.get(i).getIconHeight(), this);
            }
        }

    }
}
