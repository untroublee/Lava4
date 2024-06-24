package net.lua.lava4.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class OreProspector extends Item {
    public OreProspector(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            boolean foundBlock = false;

            for (int i = 0; i <= posClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(posClicked.below(i));

                if (isValuable(state)) {
                    printValCoords(posClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No valuables found!"));
            }
            player.sendSystemMessage(Component.literal("Finished"));
        }

        return InteractionResult.SUCCESS;
    }

    private boolean isValuable(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.ANCIENT_DEBRIS);
    }
    private void printValCoords(BlockPos blockPos, Player plr, Block block) {
        plr.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at [" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + "]"));
    }
}
