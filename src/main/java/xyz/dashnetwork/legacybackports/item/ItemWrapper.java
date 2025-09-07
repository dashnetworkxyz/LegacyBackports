package xyz.dashnetwork.legacybackports.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.dashnetwork.legacybackports.BackportConfig;

import java.util.List;

public class ItemWrapper extends Item {

    private final Item item;
    private final CreativeTabs tab;

    public ItemWrapper(Item item, CreativeTabs tab) {
        this.item = item;
        this.tab = tab;
    }

    public ItemWrapper(Block block, CreativeTabs tab) { this(Item.getItemFromBlock(block), tab); }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        if (BackportConfig.operatorItems)
            subItems.add(new ItemStack(item));
    }

    @Override
    public CreativeTabs getCreativeTab() { return tab; }

}
