package org.jetbrains.contest.keypromoter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.util.text.StringUtil;

/**
 * Date: 05.10.2006
 * Time: 15:01:47
 */
public class KeyPromoterUtils {
    public final static String SUGGESTION_MESSAGE = "Would you like to assign shortcut to '%s' action cause we " +
            "noticed it was used %s time(s) by mouse?";
    public final static String SUGGESTION_TITLE = "[KeyPromoter said]: Keyboard usage more productive!";
    /**
     * Get first field of class with target type to use during click source handling.
     *
     * @param aClass      class to inspect
     * @param targetClass target class to check field to plug
     * @return
     */
    public static Field getFieldOfType(final Class<?> aClass, final Class<?> targetClass) {
        Class<?> sourceClass = aClass;
        do {
            final Field[] declaredFields = sourceClass.getDeclaredFields();
            for (final Field declaredField : declaredFields) {
                if (declaredField.getType().equals(targetClass)) {
                    declaredField.setAccessible(true);
                    return declaredField;
                }
            }
        } while ((sourceClass = sourceClass.getSuperclass()) != null);
        return null;
    }

    /**
     * Creates popup message body from template.
     *
     * @param description  action description
     * @param shortcutText key combination
     * @param count        number of counted invocations
     * @return
     */
    public static String renderMessage(final String description, final String shortcutText, final Integer count) {
        final KeyPromoterConfiguration keyPromoterConfiguration =
                ApplicationManager.getApplication().getComponent(KeyPromoterConfiguration.class);
        final KeyPromoterSettings settings = keyPromoterConfiguration.getSettings();
        return MessageFormat.format(settings.getPopupTemplate(),
                (StringUtil.isEmpty(description) ? shortcutText : shortcutText + "<br>(" + description + ")"),
                count);
    }

    public static String getKeyboardShortcutsText(final AnAction anAction) {
        final Shortcut[] shortcuts = anAction.getShortcutSet().getShortcuts();
        if (shortcuts.length == 0) {
            return "";
        }
        final StringBuilder buffer = new StringBuilder();
        for (int index = 0; index < shortcuts.length; index++) {
            final Shortcut shortcut = shortcuts[index];
            if (index > 0) {
                buffer.append(" or ");
            }
            buffer.append("'").append(KeymapUtil.getShortcutText(shortcut)).append("'");
        }
        return buffer.toString();
    }
}
