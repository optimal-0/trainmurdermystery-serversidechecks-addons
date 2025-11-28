package com.trainmurdermysteryextras.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.HashMap;
import java.util.Map;

public class StepablePanelBlock extends MultifaceGrowthBlock {

    // Predefined shapes for each face
    private static final VoxelShape UP_SHAPE = createCuboidShape(0.0, 15.9, 0.0, 16.0, 16, 16.0);
    private static final VoxelShape DOWN_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.1, 16.0);
    private static final VoxelShape WEST_SHAPE = createCuboidShape(-16.1, 0.0, 0.0, 0.1, 8, 16);
    private static final VoxelShape EAST_SHAPE = createCuboidShape(15.9, 0.0, 0.0, 32.1, 8, 16.0);
    private static final VoxelShape SOUTH_SHAPE = createCuboidShape(0.0, 0.0, 15.9, 16.0, 8, 32.1);
    private static final VoxelShape NORTH_SHAPE = createCuboidShape(0.0, 0.0, -16.1, 16.0, 8, 0.1);

    private final Map<BlockState, VoxelShape> SHAPES;

    public StepablePanelBlock(AbstractBlock.Settings settings) {
        super(settings);

        // Precompute all shapes for each block state
        this.SHAPES = new HashMap<>();
        for (BlockState state : this.getStateManager().getStates()) {
            SHAPES.put(state, computeShape(state));
        }
    }

    @Override
    protected MapCodec<? extends MultifaceGrowthBlock> getCodec() {
        return null;
    }

    // Compute union of all occupied faces
    private static VoxelShape computeShape(BlockState state) {
        VoxelShape shape = VoxelShapes.empty();
        for (Direction dir : DIRECTIONS) {
            if (hasDirection(state, dir)) {
                shape = VoxelShapes.union(shape, shapeForDir(dir));
            }
        }
        return shape.isEmpty() ? VoxelShapes.fullCube() : shape;
    }

    private static VoxelShape shapeForDir(Direction dir) {
        return switch (dir) {
            case UP -> UP_SHAPE;
            case DOWN -> DOWN_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case NORTH -> NORTH_SHAPE;
        };
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return SHAPES.get(state);
    }

    @Override
    public LichenGrower getGrower() {
        return null;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        return SHAPES.get(state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
