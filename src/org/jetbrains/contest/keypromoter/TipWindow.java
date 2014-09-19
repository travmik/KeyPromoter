package org.jetbrains.contest.keypromoter;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.intellij.openapi.application.ApplicationManager;

/**
 * Popup window with information about missed shortcut. Contains shortcut keys and number of invocations by mouse.
 * @author Dmitry Kashin
 */
public class TipWindow extends JWindow {

    public TipWindow(final Frame owner, final String text, final Component sourceComponent) {
        super(owner);
        final KeyPromoterConfiguration component =
                ApplicationManager.getApplication().getComponent(KeyPromoterConfiguration.class);
        final KeyPromoterSettings mySettings = component.getSettings();
        setAlwaysOnTop(true);
        final JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false);
        setContentPane(contentPane);
        final TipLabel myTip = new TipLabel(text, mySettings);
        add(myTip);
        pack();
        // If fixed position to show then place it at the bottom of screen, otherwise show it at the mouse click position
        if (mySettings.isFixedTipPosition()) {
            setLocation(owner.getX() + (int) (owner.getWidth() - myTip.getSize().getWidth()) / 2,
                    owner.getY() + (int) (owner.getHeight() - myTip.getSize().getHeight() - 100));
        } else {
            // Trying to fit the screen
            final Point locationPoint = SwingUtilities.convertPoint(sourceComponent,
                    new Point(sourceComponent.getWidth() + 2, sourceComponent.getHeight() + 2), owner);
            locationPoint.x = owner.getX() + (int) Math.min(locationPoint.getX(), owner.getWidth() - myTip.getSize().getWidth());
            locationPoint.y = owner.getY() + (int) Math.min(locationPoint.getY(), owner.getHeight() - myTip.getSize().getHeight());
            setLocation(locationPoint);
        }

    }

    /**
     * Component for displaying tip with some simple animation.
     */
    class TipLabel extends JLabel {
        private float myAlphaValue;
        private static final float ALPHA_STEP = 0.1f;
        private static final float START_ALPHA = 0f;
        private final KeyPromoterSettings mySettings;

        public TipLabel(final String text, final KeyPromoterSettings mySettings) {
            super();
            this.mySettings = mySettings;
            myAlphaValue = 0.5f;
            setOpaque(false);
            setText(text);
            setForeground(mySettings.getTextColor());
        }

        // some painting fun
        @Override
        public void paintComponent(final Graphics graphics) {
            // Cast to Graphics2D so we can set composite information.
            final Graphics2D g2d = (Graphics2D)graphics;

            // Save the original composite.
            final Composite oldComp = g2d.getComposite();

            // Create an AlphaComposite
            final Composite alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, myAlphaValue);
            // Increase or rotate
            if (myAlphaValue < 1 - ALPHA_STEP) {
                myAlphaValue += ALPHA_STEP;
            } else {
                myAlphaValue = START_ALPHA;
            }

            // Set the composite on the Graphics2D object.
            g2d.setColor(mySettings.getBorderColor());
            g2d.setComposite(alphaComp);

            final Area border = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
            border.subtract(new Area(new Rectangle2D.Double(2, 2, getWidth() - 4, getHeight() - 4)));
            g2d.fill(border);

            // Restore the old composite.
            g2d.setComposite(oldComp);
            final Color backgroundColor = mySettings.getBackgroundColor();
            g2d.setColor(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), 64));
            g2d.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
            super.paintComponent(graphics);
        }

    }

}
