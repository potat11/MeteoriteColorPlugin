package com.github.potat11.colorplugin;

import com.github.potat11.colorplugin.Commands.CommandManager;
import com.meteoritepvp.api.MeteoritePlugin;
import org.bukkit.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class ColorPlugin extends MeteoritePlugin {
    @Override
    protected void onInit() {
        super.onInit();
        CommandManager command = new CommandManager(this);
        registerCommandObject(command);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        List<String> dyeColors = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            dyeColors.add(color.name().toLowerCase());
        }

        registerPlaceholderParameter("dyeColors", (sender -> dyeColors));
    }
}
