package net.azisaba.library.spigot.legacy.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.KeybindComponent;
import net.kyori.adventure.text.ScoreComponent;
import net.kyori.adventure.text.SelectorComponent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ComponentUtil {
    @NotNull
    public static BaseComponent createComponent(@NotNull Component component) {
        if (component instanceof TextComponent) {
            TextComponent textComponent = (TextComponent) component;
            return new net.md_5.bungee.api.chat.TextComponent(textComponent.content());
        }
        if (component instanceof TranslatableComponent) {
            TranslatableComponent translatableComponent = (TranslatableComponent) component;
            String key = translatableComponent.key();
            Object[] args = translatableComponent.args().stream().map(ComponentUtil::toBaseComponent).toArray();
            return new net.md_5.bungee.api.chat.TranslatableComponent(key, args);
        }
        if (component instanceof ScoreComponent) {
            ScoreComponent scoreComponent = (ScoreComponent) component;
            String name = scoreComponent.name();
            String objective = scoreComponent.objective();
            return new net.md_5.bungee.api.chat.ScoreComponent(name, objective);
        }
        if (component instanceof SelectorComponent) {
            SelectorComponent selectorComponent = (SelectorComponent) component;
            // BaseComponent separator = toBaseComponent(selectorComponent.separator());
            String pattern = selectorComponent.pattern();
            return new net.md_5.bungee.api.chat.SelectorComponent(pattern);
        }
        if (component instanceof KeybindComponent) {
            KeybindComponent keybindComponent = (KeybindComponent) component;
            String keybind = keybindComponent.keybind();
            return new net.md_5.bungee.api.chat.KeybindComponent(keybind);
        }
        throw new RuntimeException("Unmappable component " + component.getClass().getTypeName());
    }

    @NotNull
    public static BaseComponent toBaseComponent(@NotNull Component component) {
        BaseComponent baseComponent = createComponent(component);
        ClickEvent clickEvent = component.clickEvent();
        if (clickEvent != null) {
            baseComponent.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.valueOf(clickEvent.action().name()), clickEvent.value()));
        }
        HoverEvent<?> hoverEvent = component.hoverEvent();
        if (hoverEvent != null) {
            net.md_5.bungee.api.chat.HoverEvent.Action action = net.md_5.bungee.api.chat.HoverEvent.Action.valueOf(hoverEvent.action().toString().toUpperCase(Locale.ROOT));
            net.md_5.bungee.api.chat.HoverEvent bHoverEvent = null;
            if (hoverEvent.value() instanceof Component) {
                bHoverEvent = new net.md_5.bungee.api.chat.HoverEvent(action, new BaseComponent[]{toBaseComponent((Component) hoverEvent.value())});
            }
            if (bHoverEvent != null) {
                baseComponent.setHoverEvent(bHoverEvent);
            }
        }
        Style style = component.style();
        TextColor color = style.color();
        if (color != null) {
            ChatColor c = ChatColor.valueOf(NamedTextColor.nearestTo(color).toString().toUpperCase(Locale.ROOT));
            baseComponent.setColor(c);
        }
        baseComponent.setBold(style.hasDecoration(TextDecoration.BOLD));
        baseComponent.setItalic(style.hasDecoration(TextDecoration.ITALIC));
        baseComponent.setObfuscated(style.hasDecoration(TextDecoration.OBFUSCATED));
        baseComponent.setStrikethrough(style.hasDecoration(TextDecoration.STRIKETHROUGH));
        baseComponent.setUnderlined(style.hasDecoration(TextDecoration.UNDERLINED));
        baseComponent.setInsertion(style.insertion());
        for (Component child : component.children()) {
            baseComponent.addExtra(toBaseComponent(child));
        }
        return baseComponent;
    }
}
