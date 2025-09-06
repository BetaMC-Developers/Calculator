package org.betamc.calculator;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class Calculator extends JavaPlugin {
    public void onEnable() {
        getServer().getLogger().info("[Calculator] Plugin loaded.");
    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("calc") || label.equalsIgnoreCase("calculator")) {
            float num1, num2, result;
            String operator = args[1];

            if (args.length < 3) {
                sender.sendMessage("§cError: Not enough arguments");
                return false;
            }

            try {
                num1 = Float.parseFloat(args[0]);
                num2 = Float.parseFloat(args[2]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage("§cError: Arguments must be valid numbers");
                return false;
            }

            switch(operator) {
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                default:
                    sender.sendMessage("§cError: Invalid operator (must be +,-,*,/)");
                    return false;
            }

            sender.sendMessage("Result of §a" + num1 + operator + num2 + "§f: §a" + result);
        }

        return true;
    }
}
