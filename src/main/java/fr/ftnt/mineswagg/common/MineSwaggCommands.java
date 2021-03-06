package fr.ftnt.mineswagg.common;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class MineSwaggCommands extends CommandBase implements ICommand
{
    protected String fullEntityName;
    protected Entity conjuredEntity;

    public MineSwaggCommands()
    {

    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "swagg";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return getCommandUsage();
    }

    public String getCommandUsage()
    {
        return "MineSwagg.command.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] arguments)
    {
        if(arguments.length <= 0)
        {
            throw new WrongUsageException("commands.swagg.usage", new Object[0]);
        }
        else
        {
            String s = arguments[0];
            boolean flag = s.endsWith("l") || s.endsWith("L");

            if(flag && s.length() > 1)
            {
                s = s.substring(0, s.length() - 1);
            }

            int i = parseInt(sender, s);
            boolean negative = i < 0;

            if(negative)
                i *= -1;

            EntityPlayerMP player;

            if(arguments.length > 1)
            {
                player = getPlayer(sender, arguments[1]);
            }
            else
            {
                player = getCommandSenderAsPlayer(sender);
            }

            MineSwaggExtendedEntityPlayer props = MineSwaggExtendedEntityPlayer.get(player);

            if(flag)
            {
                if(negative)
                {
                    func_152373_a(sender, this, "commands.swagg.success.negative.levels" + (i <= 1 ? ".singular" : ""), new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    if(props.consumeSwaggLevel(i, true, true))
                    {
                        func_152373_a(sender, this, "commands.swagg.nowNegative", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    }
                }
                else
                {
                    func_152373_a(sender, this, "commands.swagg.success.levels" + (i <= 1 ? ".singular" : ""), new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    if(props.addSwaggLevel(i, true, true))
                    {
                        func_152373_a(sender, this, "commands.swagg.nowPositive", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    }
                }
            }
            else
            {
                if(negative)
                {
                    func_152373_a(sender, this, "commands.swagg.success.negative", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    if(props.consumeSwaggAmount(i, true, true))
                    {
                        func_152373_a(sender, this, "commands.swagg.nowNegative", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    }
                }
                else
                {
                    func_152373_a(sender, this, "commands.swagg.success", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    if(props.addSwaggAmount(i, true, true))
                    {
                        func_152373_a(sender, this, "commands.swagg.nowPositive", new Object[] {Integer.valueOf(i), player.getCommandSenderName()});
                    }
                }
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender var1)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender var1, String[] var2)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2)
    {
        return false;
    }
}