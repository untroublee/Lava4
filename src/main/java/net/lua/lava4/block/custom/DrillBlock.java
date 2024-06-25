package net.lua.lava4.block.custom;

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
        drillAtOffset(0,0,pPos,pLevel,pPlayer);
        drillAtOffset(1,0,pPos,pLevel,pPlayer);
        drillAtOffset(-1,0,pPos,pLevel,pPlayer);
        drillAtOffset(0,1,pPos,pLevel,pPlayer);
        drillAtOffset(0,-1,pPos,pLevel,pPlayer);

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    private static void drillAtOffset(int x, int z, BlockPos pPos, Level pLevel, Player pPlayer) {
        for (int i = pPos.getY() - 1; i != -64; i--) {
            pLevel.destroyBlock(new BlockPos(pPos.getX() + x,i,pPos.getZ() + z),true,pPlayer);
        }
    }
}
