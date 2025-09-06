package org.betamc.calculator;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator extends JavaPlugin {
    public void onEnable() { getServer().getLogger().info("[Calculator] Plugin loaded."); }
    public void onDisable() {}
    private static String trimTrailingZeros(BigDecimal number) { return number.toString().replaceAll("\\.?0*$", ""); }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("calc") || label.equalsIgnoreCase("calculator")) {
            if (args.length < 3) {
                sender.sendMessage("§cError: Not enough arguments");
                return false;
            }

            BigDecimal num1, num2, result;
            String operator = args[1];

            try {
                num1 = new BigDecimal(args[0]);
                num2 = new BigDecimal(args[2]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage("§cError: Arguments must be valid numbers");
                return false;
            }

            switch(operator) {
                case "*":
                    result = num1.multiply(num2);
                    break;
                case "/":
                    try {
                        result = num1.divide(num2, 10, RoundingMode.HALF_UP);
                    }
                    catch (ArithmeticException e) {
                        sender.sendMessage("§cError: Arithmetic exception (Division by 0?)");
                        return false;
                    }
                    break;
                case "+":
                    result = num1.add(num2);
                    break;
                case "-":
                    result = num1.subtract(num2);
                    break;
                default:
                    sender.sendMessage("§cError: Invalid operator (must be +,-,*,/)");
                    return false;
            }

            sender.sendMessage("Result of §a" + num1 + " §f" + operator + " §a" + num2 + "§f: §a" + trimTrailingZeros(result));
        }

        return true;
    }
}
