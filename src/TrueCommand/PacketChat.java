package TrueCommand;

import TrueCommand.wrapper.WrapperPlayClientChat;
import TrueCommand.wrapper.WrapperPlayClientChatCommand;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;

import org.bukkit.plugin.Plugin;
import TrueCommand.utils.CommandUtils;
import com.comphenix.protocol.events.PacketAdapter;

public class PacketChat extends PacketAdapter {
	private CommandUtils utils;

	public PacketChat(Plugin plugin, PacketType[] type, CommandUtils commandUtils) {
		super(plugin, type);
		this.utils = commandUtils;
	}
	
	@Override
    public void onPacketReceiving(PacketEvent e) {
        WrapperPlayClientChat packet = new WrapperPlayClientChat(e.getPacket());
        WrapperPlayClientChatCommand packet2 = new WrapperPlayClientChatCommand();
        String msg = packet.getMessage();
        if (msg.length() > 1 && msg.startsWith(".")) {
            String cmd = msg.split(" ")[0].substring(1);
            if (this.utils.get(cmd) != null) {
            	packet2.setMessage(cmd);
            	packet2.getHandle().getModifier().write(1, packet.getHandle().getModifier().read(1));
            	ProtocolLibrary.getProtocolManager().receiveClientPacket(e.getPlayer(), packet2.getHandle(), true);
            	e.setCancelled(true);
                return;
            }
            if (this.utils.has(String.valueOf(cmd.toCharArray()[0]))) {
                String rcmd = this.utils.repl(cmd);
                if (this.utils.get(rcmd) != null) {
                	packet2.setMessage(rcmd);
                	packet2.getHandle().getModifier().write(1, packet.getHandle().getModifier().read(1));
                	ProtocolLibrary.getProtocolManager().receiveClientPacket(e.getPlayer(), packet2.getHandle(), true);
                	e.setCancelled(true);
                }
            }
        }
    }
}