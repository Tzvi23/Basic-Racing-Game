package GUI;

import java.awt.*;
//@author : Tzvi Puchinsky 
/**
 * Race Icon class - created to store positions and images for each racer
 */
class racerIconObj {
    private int iconWidth;
    private int iconHeight;
    private int positionX;
    private int positionY;
    private Image icon;

    /**
     * Constructor for Racer Icon Object to set all the needed parameters.
     * @param width
     * Width of the icon
     * @param height
     * Height of the icon
     * @param pos_x
     * Position X
     * @param pos_y
     * Position Y
     * @param img
     * Icon Image
     */
    racerIconObj(int width, int height, int pos_x, int pos_y, Image img) {
        iconHeight = height;
        iconWidth = width;
        positionX = pos_x;
        positionY = pos_y;
        icon = img;
    }

    /**
     * Getter - returns Icon Width (int)
     * @return int
     * Return int value of the icon Width
     */
    public int getIconWidth() {
        return iconWidth;
    }

    /**
     * Getter - returns Icon Height (int)
     * @return int
     * Return int value of the icon Height
     */
    public int getIconHeight() {
        return iconHeight;
    }

    /**
     * Getter - return Position X (int)
     * @return int
     * Return int value of the Position X
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Getter - return Position Y (int)
     * @return int
     * Return int value of the Position Y
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Getter - return Image type of icon
     * @return Image
     * Returns image of the icon
     */
    public Image getIcon() {
        return icon;
    }
}
