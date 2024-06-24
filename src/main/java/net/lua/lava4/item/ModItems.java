package net.lua.lava4.item;

import net.lua.lava4.item.custom.Gun;
import net.lua.lava4.item.custom.OreProspector;
import net.lua.lava4.Lava4;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Lava4.MOD_ID);

    // Items
    public static final RegistryObject<Item> PLASTIC = ITEMS.register("plastic", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLASTIC = ITEMS.register("raw_plastic",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BULLET = ITEMS.register("bullet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_PROSPECTOR = ITEMS.register("ore_prospector", () -> new OreProspector(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GUN = ITEMS.register("gun", () -> new Gun(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}