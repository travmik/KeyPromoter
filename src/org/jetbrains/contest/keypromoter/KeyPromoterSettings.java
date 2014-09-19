package org.jetbrains.contest.keypromoter;

import java.awt.Color;

import com.google.common.base.Objects;
import com.intellij.ui.JBColor;

/**
 * Settings for KeyPromoter plugin.
 * @author Dmitry Kashin
 */
public class KeyPromoterSettings {

    /** Color of text in popup */
    public Color textColor = JBColor.BLACK;
    /** Color of border of popup */
    public Color borderColor = JBColor.RED;
    /** Background Color of popup */
    public Color backgroundColor = new JBColor(0x202040, 0x202040);

    /** Whether popup enabled or disabled on menus clicks. */
    public boolean menusEnabled = true;
    /** Whether popup enabled or disabled on toolbar buttons clicks. */
    public boolean toolbarButtonsEnabled = true;
    /** Whether popup enabled or disabled on toolwindow buttons clicks. */
    public boolean toolWindowButtonsEnabled = true;
    /** Whether popup enabled or disabled on all buttons with mnemonics clicks. */
    public boolean allButtonsEnabled = false;

    /** Time of popup display. */
    public long displayTime = 3000;
    /** Animation delay time. */
    public long flashAnimationDelay = 150;
    /** Count of invocations after which ask for creation of shortcut for actions without them. */
    public int proposeToCreateShortcutCount = 3;
    /** Popup position fixed or folow the mouse clicks. */
    public boolean fixedTipPosistion = false;
    /** Popup template. */
    public String popupTemplate = "<html>\n" +
            " <body>\n" +
            "  <table>\n" +
            "   <tr>\n" +
            "    <td align=\"center\"><font size=8>{0}</font></td>\n" +
            "   </tr>\n" +
            "   <tr>\n" +
            "    <td align=\"center\"><font size=6>{1} time(s)</font></td>\n" +
            "   </tr>\n" +
            "  </table>\n" +
            " </body>\n" +
            "</html>";

    public long getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(final long displayTime) {
        this.displayTime = displayTime;
    }

    public long getFlashAnimationDelay() {
        return flashAnimationDelay;
    }

    public void setFlashAnimationDelay(final long flashAnimationDelay) {
        this.flashAnimationDelay = flashAnimationDelay;
    }

    public boolean isMenusEnabled() {
        return menusEnabled;
    }

    public void setMenusEnabled(final boolean menusEnabled) {
        this.menusEnabled = menusEnabled;
    }

    public boolean isToolbarButtonsEnabled() {
        return toolbarButtonsEnabled;
    }

    public void setToolbarButtonsEnabled(final boolean toolbarButtonsEnabled) {
        this.toolbarButtonsEnabled = toolbarButtonsEnabled;
    }

    public boolean isToolWindowButtonsEnabled() {
        return toolWindowButtonsEnabled;
    }

    public void setToolWindowButtonsEnabled(final boolean toolWindowButtonsEnabled) {
        this.toolWindowButtonsEnabled = toolWindowButtonsEnabled;
    }

    public boolean isAllButtonsEnabled() {
        return allButtonsEnabled;
    }

    public void setAllButtonsEnabled(final boolean allButtonsEnabled) {
        this.allButtonsEnabled = allButtonsEnabled;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(final Color textColor) {
        this.textColor = textColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isFixedTipPosition() {
        return this.fixedTipPosistion;
    }

    public void setFixedTipPosistion(final boolean fixedTipPosistion) {
        this.fixedTipPosistion = fixedTipPosistion;
    }

    public int getProposeToCreateShortcutCount() {
        return proposeToCreateShortcutCount;
    }

    public void setProposeToCreateShortcutCount(final int proposeToCreateShortcutCount) {
        this.proposeToCreateShortcutCount = proposeToCreateShortcutCount;
    }

    public String getPopupTemplate() {
        return popupTemplate;
    }

    public void setPopupTemplate(final String popupTemplate) {
        this.popupTemplate = popupTemplate;
    }


    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final KeyPromoterSettings that = (KeyPromoterSettings) obj;
        return Objects.equal(allButtonsEnabled, that.allButtonsEnabled)
                && Objects.equal(displayTime, that.displayTime)
                && Objects.equal(fixedTipPosistion, that.fixedTipPosistion)
                && Objects.equal(flashAnimationDelay, that.flashAnimationDelay)
                && Objects.equal(menusEnabled, that.menusEnabled)
                && Objects.equal(proposeToCreateShortcutCount, that.proposeToCreateShortcutCount)
                && Objects.equal(toolWindowButtonsEnabled, that.toolWindowButtonsEnabled)
                && Objects.equal(toolbarButtonsEnabled, that.toolbarButtonsEnabled)
                && Objects.equal(backgroundColor, that.backgroundColor)
                && Objects.equal(borderColor, that.borderColor)
                && Objects.equal(popupTemplate, that.popupTemplate)
                && Objects.equal(textColor, that.textColor);
    }

    public int hashCode() {
        return Objects.hashCode(textColor, borderColor, backgroundColor, menusEnabled, toolbarButtonsEnabled,
                toolWindowButtonsEnabled, allButtonsEnabled, displayTime, flashAnimationDelay,
                proposeToCreateShortcutCount, fixedTipPosistion, popupTemplate);
    }
}
