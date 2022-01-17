package net.azisaba.library.spigot.modern.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Locale;

public class ComponentUtil {
    @NotNull
    public static BaseComponent createComponent(@NotNull Component component) {
        return net.azisaba.library.spigot.legacy.util.ComponentUtil.createComponent(component);
    }

    @Contract("!null -> !null; null -> null")
    public static BaseComponent toBaseComponent(@Nullable Component component) {
        if (component == null) return null;
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
                Text text = new Text(new BaseComponent[]{toBaseComponent((Component) hoverEvent.value())});
                bHoverEvent = new net.md_5.bungee.api.chat.HoverEvent(action, text);
            }
            if (hoverEvent.value() instanceof HoverEvent.ShowItem) {
                HoverEvent.ShowItem showItem = (HoverEvent.ShowItem) hoverEvent.value();
                ItemTag nbt = null;
                if (showItem.nbt() != null) nbt = ItemTag.ofNbt(showItem.nbt().string());
                Item item = new Item(showItem.item().value(), showItem.count(), nbt);
                bHoverEvent = new net.md_5.bungee.api.chat.HoverEvent(action, item);
            }
            if (hoverEvent.value() instanceof HoverEvent.ShowEntity) {
                HoverEvent.ShowEntity showEntity = (HoverEvent.ShowEntity) hoverEvent.value();
                Entity entity = new Entity(showEntity.type().value(), showEntity.id().toString(), toBaseComponent(showEntity.name()));
                bHoverEvent = new net.md_5.bungee.api.chat.HoverEvent(action, entity);
            }
            if (bHoverEvent != null) {
                baseComponent.setHoverEvent(bHoverEvent);
            }
        }
        Style style = component.style();
        TextColor color = style.color();
        if (color != null) {
            baseComponent.setColor(ChatColor.of(new Color(color.value())));
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
