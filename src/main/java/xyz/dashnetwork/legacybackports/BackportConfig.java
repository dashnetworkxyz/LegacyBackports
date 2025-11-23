package xyz.dashnetwork.legacybackports;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.KeyBind;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.annotations.Text;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import cc.polyfrost.oneconfig.libs.universal.UKeyboard;
import xyz.dashnetwork.legacybackports.runnable.GamemodeSwitch;

public final class BackportConfig extends Config {

    @Switch(name = "256 chat limit", subcategory = "Chat", size = OptionSize.DUAL)
    public static boolean tfsChatLimit = true;

    @Text(
            name = "256 chat allowed servers",
            description = "Separate server ips with \";\"",
            placeholder = "hypixel.net;dashnetwork.xyz;oc.tc",
            size = OptionSize.DUAL,
            subcategory = "Chat"
    )
    public static String tfsChatServers = "hypixel.net;dashnetwork.xyz;oc.tc";

    @KeyBind(name = "Creative / Spectator switch", subcategory = "Keybind", size = OptionSize.DUAL)
    public static OneKeyBind ftnKeybind = new OneKeyBind(UKeyboard.KEY_F3, UKeyboard.KEY_N);

    @Switch(name = "Disable item breaks", subcategory = "Fixes")
    public static boolean disableItemBreaks = true;

    public BackportConfig() {
        super(new Mod("LegacyBackports", ModType.UTIL_QOL), "legacybackports.json");

        registerKeyBind(ftnKeybind, new GamemodeSwitch());
        initialize();
    }

}
