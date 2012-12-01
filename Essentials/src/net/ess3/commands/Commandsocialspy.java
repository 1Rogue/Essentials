package net.ess3.commands;

import static net.ess3.I18n._;
import net.ess3.api.IUser;
import net.ess3.permissions.Permissions;
import org.bukkit.command.CommandSender;


public class Commandsocialspy extends EssentialsSettingsCommand
{

	protected void setValue(final IUser player, final boolean value)
	{
		player.getData().setSocialspy(value);
		player.queueSave();
	}

	protected boolean getValue(final IUser player)
	{
		return player.getData().isSocialspy();
	}

	protected void informSender(final CommandSender sender, final boolean value, final IUser player)
	{
		if (value) {
			sender.sendMessage( _("socialSpyMode", _(getValue(player) ? "enabled" : "disabled"), player.getPlayer().getDisplayName()));
		}
		else {
			//TODO: TL this
			sender.sendMessage("Can't change socialspy for player " + player.getName());
		}
	}

	protected void informPlayer(final IUser player)
	{
		final String message = _("socialSpyMode", _(getValue(player) ? "enabled" : "disabled"), player.getPlayer().getDisplayName());
		player.sendMessage(message);
	}

	protected boolean canToggleOthers(final IUser user)
	{
		return Permissions.SOCIALSPY_OTHERS.isAuthorized(user);
	}

	protected boolean isExempt(final CommandSender sender, final IUser player)
	{
		return (player.isOnline() ? Permissions.SOCIALSPY_EXEMPT.isAuthorized(player) : !Permissions.SOCIALSPY_OFFLINE.isAuthorized(sender));
	}
}
