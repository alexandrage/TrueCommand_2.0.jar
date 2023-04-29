package TrueCommand.wrapper;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;

public class WrapperPlayClientChat extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.CHAT;

	public WrapperPlayClientChat() {
		super(new PacketContainer(TYPE), TYPE);
		this.handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientChat(PacketContainer packet) {
		super(packet, TYPE);
	}

	public String getMessage() {
		return (String) this.handle.getStrings().read(0);
	}

	public void setMessage(String value) {
		this.handle.getStrings().write(0, (String) value);
	}
}