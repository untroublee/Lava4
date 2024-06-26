package net.lua.lava4.item.custom;

import net.lua.lava4.Lava4;
import net.lua.lava4.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Gun extends ProjectileWeaponItem {
    public static final int DEFAULT_RANGE = 50;
    public static final float VELOCITY = 30;

    public Gun(Properties properties) {
        super(properties);
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player plr, @NotNull InteractionHand hand) {
        ItemStack itemStack = plr.getItemInHand(hand);
        if (!level.isClientSide()) {
            boolean hasBullet = plr.getAbilities().instabuild; //Overrides hasBullet with true if in creative mode so to remove the need for bullets in creative
            boolean consumeBullets = false;

            if (!hasBullet) {
                Inventory inventory = plr.getInventory();
                for (int i = 0; i < inventory.getContainerSize(); i++) {
                    ItemStack checkItemStack = inventory.getItem(i);

                    if (checkItemStack.getItem().equals(ModItems.BULLET.get())) {
                        hasBullet = true;
                        checkItemStack.shrink(1);
                    }
                }
            }

            if (hasBullet) {
                shootBullet(itemStack,level,plr);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack,level.isClientSide());
    }

    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    public AbstractArrow customArrow(AbstractArrow arrow) {
        return arrow;
    }

    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }

    private void shootBullet(ItemStack itemStack, Level level, Player plr) {
        ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
        AbstractArrow abstractArrow = arrowItem.createArrow(level, itemStack, plr);
        abstractArrow = this.customArrow(abstractArrow);
        abstractArrow.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0.0F, VELOCITY, 0.0F);
        abstractArrow.setCritArrow(true);

        level.addFreshEntity(abstractArrow);
    }
}