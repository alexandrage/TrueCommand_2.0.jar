package TrueCommand.utils;

import org.bukkit.command.Command;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.bukkit.Bukkit;
import java.util.Map;
import org.bukkit.command.CommandMap;

public class CommandUtils {
	private static CommandMap map;
	private static Map<String, String> repl;

	static {
		try {
			Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			field.setAccessible(true);
			map = (CommandMap) field.get(Bukkit.getServer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		(repl = new HashMap<String, String>()).put("й", "q");
		repl.put("ц", "w");
		repl.put("у", "e");
		repl.put("к", "r");
		repl.put("е", "t");
		repl.put("н", "y");
		repl.put("г", "u");
		repl.put("ш", "i");
		repl.put("щ", "o");
		repl.put("з", "p");
		repl.put("х", "[");
		repl.put("ъ", "]");
		repl.put("ф", "a");
		repl.put("ы", "s");
		repl.put("в", "d");
		repl.put("а", "f");
		repl.put("п", "g");
		repl.put("р", "h");
		repl.put("о", "j");
		repl.put("л", "k");
		repl.put("д", "l");
		repl.put("ж", ";");
		repl.put("э", "'");
		repl.put("я", "z");
		repl.put("ч", "x");
		repl.put("с", "c");
		repl.put("м", "v");
		repl.put("и", "b");
		repl.put("т", "n");
		repl.put("ь", "m");
		repl.put("б", ",");
		repl.put("ю", ".");
		repl.put("ё", "`");
		repl.put(".", "/");
	}

	public String get(String name) {
		Command cmd = map.getCommand(name);
		if (cmd != null) {
			return cmd.getName();
		}
		return null;
	}

	public boolean has(String name) {
		return repl.containsKey(name);
	}

	public String repl(String message) {
		for (Map.Entry<String, String> entry : repl.entrySet()) {
			message = message.replace(entry.getKey(), entry.getValue());
		}
		return message;
	}
}