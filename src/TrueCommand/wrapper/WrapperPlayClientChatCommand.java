package TrueCommand.wrapper;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class WrapperPlayClientChatCommand extends AbstractPacket {

	protected WrapperPlayClientChatCommand(PacketContainer handle, PacketType type) {
		super(handle, type);
	}

	public static final PacketType TYPE = PacketType.Play.Client.CHAT_COMMAND;

	public WrapperPlayClientChatCommand() {
		super(new PacketContainer(TYPE), TYPE);
		this.handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientChatCommand(PacketContainer packet) {
		super(packet, TYPE);
	}

	public String getMessage() {
		return (String) this.handle.getStrings().read(0);
	}

	public void setMessage(String value) {
		this.handle.getStrings().write(0, (String) value);
	}
}