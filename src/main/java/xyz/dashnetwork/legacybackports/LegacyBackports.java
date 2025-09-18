package xyz.dashnetwork.legacybackports;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = LegacyBackports.ID, name = LegacyBackports.NAME, version = LegacyBackports.VERSION)
public final class LegacyBackports {

    public static final String ID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VERSION@";

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        new BackportConfig();
    }

}
