package com.github.potat11.colorplugin.Commands;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.command.DefaultCommand;
import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import java.util.HashSet;

@DefaultCommand
public class CommandManager implements CommandClass {
    private final MeteoriteInventory colorInventory;
    private final HashSet<DyeColor> colors = new HashSet<>();

    public CommandManager(MeteoritePlugin plugin) {
        this.colorInventory = new MeteoriteInventory(plugin, "Colors", 9, 2, false);
    }

    @Command(name = "color",
        description = "Removes a color from the gui.",
        aliases = {"c"},
        args = {"add"},
        params = "@dyeColors"
    )
    public void addColors(CommandSender sender, String[] params) {
        sender.sendMessage("added " + params[0] + " to the color gui");
        colors.add(DyeColor.valueOf((params[0])));
    }

    @Command(
        name = "color",
        description = "Removes a color from the gui.",
        aliases = {"c"},
        args = {"remove"},
        params = "@dyeColors"
    )
    public void removeColors(CommandSender sender, String[] params, String[] args) {
        sender.sendMessage("removed " + params[0] + " from the color gui");
        colors.remove(DyeColor.valueOf(params[0]));
    }

    @Command(
        name = "colors",
        aliases = {"c"},
        description = "Shows the color gui"
    )
    public void showColors(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Colors can only be shown to players ");
            return;
        }
        BasicInventory page = new BasicInventory();
        int i = 0;
        for (DyeColor color : colors) {
            ItemStack itemStack = new ItemStack(Material.WOOL);
            itemStack.setData(new Wool(color));
            page.setItem(i++, itemStack);
        }
        colorInventory.setPage(page);
        colorInventory.show((Player) sender);
    }
}
