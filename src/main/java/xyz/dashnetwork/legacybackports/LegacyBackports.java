package xyz.dashnetwork.legacybackports;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.dashnetwork.legacybackports.item.ItemWrapper;

@Mod(modid = LegacyBackports.ID, name = LegacyBackports.NAME, version = LegacyBackports.VERSION)
public final class LegacyBackports {

    public static final String ID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VERSION@";

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        GameRegistry.registerItem(
                new ItemWrapper(Blocks.command_block, CreativeTabs.tabRedstone),
                "fakeCommandBlock"
        );
        GameRegistry.registerItem(
                new ItemWrapper(Blocks.barrier, CreativeTabs.tabMisc),
                "fakeBarrier"
        );
        GameRegistry.registerItem(
                new ItemWrapper(Items.command_block_minecart, CreativeTabs.tabTransport),
                "fakeCommandBlockMinecart"
        );

        new BackportConfig();
    }

}
