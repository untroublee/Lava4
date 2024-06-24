package net.lua.lava4.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player plr, InteractionHand hand) {
        ItemStack itemStack = plr.getItemInHand(hand);
        if (!level.isClientSide()) {
            ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
            AbstractArrow abstractArrow = arrowItem.createArrow(level, itemStack, plr);
            abstractArrow = this.customArrow(abstractArrow);
            abstractArrow.shootFromRotation(plr, plr.getXRot(), plr.getYRot(), 0.0F, VELOCITY, 0.0F);
            abstractArrow.setCritArrow(true);

            level.addFreshEntity(abstractArrow);
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
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//import java.util.List;
//import java.util.function.Predicate;
//import javax.annotation.Nullable;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResultHolder;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.Projectile;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.event.ForgeEventFactory;
//
//public class BowItem extends ProjectileWeaponItem {
//    public static final int MAX_DRAW_DURATION = 20;
//    public static final int DEFAULT_RANGE = 15;
//
//    public BowItem(Item.Properties pProperties) {
//        super(pProperties);
//    }
//
//
//
//    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
//        if (pEntityLiving instanceof Player player) {
//            ItemStack itemstack = player.getProjectile(pStack);
//            if (!itemstack.isEmpty()) {
//                int i = this.getUseDuration(pStack) - pTimeLeft;
//                i = ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
//                if (i < 0) {
//                    return;
//                }
//
//                float f = getPowerForTime(i);
//                if (!((double)f < 0.1)) {
//                    List<ItemStack> list = draw(pStack, itemstack, player);
//                    if (!pLevel.isClientSide() && !list.isEmpty()) {
//                        this.shoot(pLevel, player, player.getUsedItemHand(), pStack, list, f * 3.0F, 1.0F, f == 1.0F, (LivingEntity)null);
//                    }
//
//                    pLevel.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
//                    player.awardStat(Stats.ITEM_USED.get(this));
//                }
//            }
//        }
//
//    }
//
//    protected void shootProjectile(LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget) {
//        pProjectile.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot() + pAngle, 0.0F, pVelocity, pInaccuracy);
//    }
//
//    public static float getPowerForTime(int pCharge) {
//        float f = (float)pCharge / 20.0F;
//        f = (f * f + f * 2.0F) / 3.0F;
//        if (f > 1.0F) {
//            f = 1.0F;
//        }
//
//        return f;
//    }
//
//    public int getUseDuration(ItemStack pStack) {
//        return 72000;
//    }
//
//    public UseAnim getUseAnimation(ItemStack pStack) {
//        return UseAnim.BOW;
//    }
//
//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
//        ItemStack itemstack = pPlayer.getItemInHand(pHand);
//        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();
//        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
//        if (ret != null) {
//            return ret;
//        } else if (!pPlayer.hasInfiniteMaterials() && !flag) {
//            return InteractionResultHolder.fail(itemstack);
//        } else {
//            pPlayer.startUsingItem(pHand);
//            return InteractionResultHolder.consume(itemstack);
//        }
//    }
//
//    public Predicate<ItemStack> getAllSupportedProjectiles() {
//        return ARROW_ONLY;
//    }
//
//    public int getDefaultProjectileRange() {
//        return 15;
//    }
//}
