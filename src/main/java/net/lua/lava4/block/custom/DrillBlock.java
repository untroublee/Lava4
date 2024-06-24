package net.lua.lava4.block.custom;

import net.lua.lava4.Lava4;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DrillBlock extends Block {

    public DrillBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.destroyBlock(pPos,true,pPlayer);
        for (int i = 0; i <= pPos.getY() + 64; i++) {
            Lava4.LOGGER.info(String.valueOf(i));
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
