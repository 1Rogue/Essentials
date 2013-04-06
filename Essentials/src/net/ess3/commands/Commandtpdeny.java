package net.ess3.commands;

import static net.ess3.I18n._;
import net.ess3.api.IUser;


public class Commandtpdeny extends EssentialsCommand
{
	@Override
	public void run(final IUser user, final String commandLabel, final String[] args) throws Exception
	{
		final IUser player = user.getTeleportRequester();
		if (player == null)
		{
			throw new Exception(_("You do not have a pending request."));
		}

		user.sendMessage(_("Teleport request denied."));
		player.sendMessage(_("{0} denied your teleport request.", user.getPlayer().getDisplayName()));
		user.requestTeleport(null, false);
	}
}
