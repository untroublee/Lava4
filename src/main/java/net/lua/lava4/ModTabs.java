package net.lua.lava4;

import net.lua.lava4.block.ModBlocks;
import net.lua.lava4.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lava4.MOD_ID);

    public static final RegistryObject<CreativeModeTab> L3J_TAB = CREATIVE_TABS.register("l3j_tab",
            () -> {
                return CreativeModeTab.builder().icon(
                                () -> new ItemStack(ModItems.PLASTIC.get()))
                        .title(Component.translatable("creativetab.l3j_tab"))
                        .displayItems((parameters, output) -> {
                            // Add Items to the tab here
                            // pOutput.accept(ITEM.get());
                            output.accept(ModItems.PLASTIC.get());
                            output.accept(ModItems.RAW_PLASTIC.get());
                            output.accept(ModItems.ORE_PROSPECTOR.get());
                            output.accept(ModItems.GUN.get());
                            output.accept(ModItems.BULLET.get());

                            output.accept(ModBlocks.PLASTIC_BLOCK.get());
                            output.accept(ModBlocks.SOUND_BLOCK.get());
                            output.accept(ModBlocks.DRILL_BLOCK.get());

                            output.accept(Items.ACACIA_BOAT);
                        }).build();
            });
    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
