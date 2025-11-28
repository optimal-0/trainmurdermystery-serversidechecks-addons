package com.trainmurdermysteryextras.item;

import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.index.TMMBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;

import java.util.HashSet;
import java.util.Set;

public class GlassHammerItem extends Item  {

    public GlassHammerItem(Settings settings) {
        super(settings);
    }

    public Set<BlockPos> connected = new HashSet<>();

    private boolean isGlass(World world, BlockPos pos) {
        return world.getBlockState(pos).isOf(TMMBlocks.GOLDEN_GLASS_PANEL);
    }

    private boolean matches(BlockState state, Block block, Direction facing) {
        return state.getBlock() == block &&
                state.contains(FacingBlock.FACING) &&
                state.get(FacingBlock.FACING) == facing;
    }

    public void replaceGlass(World world, BlockPos currentPos) {

        if (connected.contains(currentPos)) return;
        connected.add(currentPos);

        BlockState currentState = world.getBlockState(currentPos);
        Block currentBlock = currentState.getBlock();
        Direction currentFacing = currentState.get(FacingBlock.FACING);

        BlockPos leftPos  = currentPos.add( 0, 0, 0);
        BlockPos rightPos = currentPos.add(0, 0, 0);
        BlockPos upPos    = currentPos.add(0, 0, 0);
        BlockPos downPos  = currentPos.add(0, 0, 0);

        switch (currentFacing) {
            case SOUTH -> {
                leftPos  = currentPos.add( -1, 0, 0);
                rightPos = currentPos.add(1, 0, 0);
                upPos    = currentPos.add(0, 1, 0);
                downPos  = currentPos.add(0, -1, 0);
            } case NORTH -> {
                leftPos  = currentPos.add( 1, 0, 0);
                rightPos = currentPos.add(-1, 0, 0);
                upPos    = currentPos.add(0, 1, 0);
                downPos  = currentPos.add(0, -1, 0);
            } case EAST -> {
                leftPos  = currentPos.add( 0, 0, 1);
                rightPos = currentPos.add(0, 0, -1);
                upPos    = currentPos.add(0, 1, 0);
                downPos  = currentPos.add(0, -1, 0);
            } case WEST -> {
                leftPos  = currentPos.add( 0, 0, -1);
                rightPos = currentPos.add(0, 0, 1);
                upPos    = currentPos.add(0, 1, 0);
                downPos  = currentPos.add(0, -1, 0);

            } case UP -> {
                leftPos  = currentPos.add(-1, 0, 0); // left along X-
                rightPos = currentPos.add(1, 0, 0);  // right along X+
                upPos    = currentPos.add(0, 0, -1); // forward along Z-
                downPos  = currentPos.add(0, 0, 1);  // backward along Z+
            } case DOWN -> {
                leftPos  = currentPos.add(-1, 0, 0); // left along X-
                rightPos = currentPos.add(1, 0, 0);  // right along X+
                upPos    = currentPos.add(0, 0, 1);  // forward along Z+
                downPos  = currentPos.add(0, 0, -1); // backward along Z-
            }
        }

        BlockState leftState  = world.getBlockState(leftPos);
        BlockState rightState = world.getBlockState(rightPos);
        BlockState upState    = world.getBlockState(upPos);
        BlockState downState  = world.getBlockState(downPos);

        boolean matchLeft  = matches(leftState, currentBlock, currentFacing);
        boolean matchRight = matches(rightState, currentBlock, currentFacing);
        boolean matchUp    = matches(upState, currentBlock, currentFacing);
        boolean matchDown  = matches(downState, currentBlock, currentFacing);

        if (matchLeft && !connected.contains(leftPos)) replaceGlass(world, leftPos);
        if (matchRight && !connected.contains(rightPos)) replaceGlass(world, rightPos);
        if (matchUp && !connected.contains(upPos)) replaceGlass(world, upPos);
        if (matchDown && !connected.contains(downPos)) replaceGlass(world, downPos);

        BlockState newState = TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN.getDefaultState().with(FacingBlock.FACING, currentFacing);

        if (!matchUp && !matchDown && !matchLeft && !matchRight) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && matchDown && matchLeft && matchRight) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && !matchDown && !matchLeft && !matchRight) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (!matchUp && matchDown && !matchLeft && !matchRight) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && !matchDown && matchRight && !matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && !matchDown && !matchRight && matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (!matchUp && matchDown && matchRight && !matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (!matchUp && matchDown && !matchRight && matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && matchDown && matchRight && !matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && matchDown && !matchRight && matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (!matchUp && matchDown && matchRight && matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }
        else if (matchUp && !matchDown && matchRight && matchLeft) {
            world.setBlockState(currentPos, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM.getDefaultState().with(FacingBlock.FACING, currentState.get(FacingBlock.FACING)),  3);
        }

        world.syncWorldEvent(2001, currentPos, Block.getRawIdFromState(currentState));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos startPos = context.getBlockPos();

        connected.clear();

        if (isGlass(world, startPos)) {
            replaceGlass(world, startPos);
        }

        return ActionResult.PASS;
    }
}
