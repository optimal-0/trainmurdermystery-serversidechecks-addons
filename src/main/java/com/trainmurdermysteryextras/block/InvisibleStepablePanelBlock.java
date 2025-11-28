package com.trainmurdermysteryextras.block;

import com.google.common.collect.ImmutableMap;
import dev.doctor4t.trainmurdermystery.block.PanelBlock;
import dev.doctor4t.trainmurdermystery.util.BarrierViewer;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class InvisibleStepablePanelBlock extends PanelBlock {

    private static final VoxelShape UP_SHAPE = createCuboidShape(0.0, 15.9, 0.0, 16.0, 16, 16.0);
    private static final VoxelShape DOWN_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.1, 16.0);
    private static final VoxelShape WEST_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 0.1, 8, 16.0);
    private static final VoxelShape EAST_SHAPE = createCuboidShape(15.9, 0.0, 0.0, 16.0, 8, 16.0);
    private static final VoxelShape SOUTH_SHAPE = createCuboidShape(0.0, 0.0, 15.9, 16.0, 8, 16.0);
    private static final VoxelShape NORTH_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 8, 0.1);

    private final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public InvisibleStepablePanelBlock(Settings settings) {
        super(settings);
        this.SHAPES = this.getShapesForStates(InvisibleStepablePanelBlock::shapeForState);
    }

    private static VoxelShape shapeForState(BlockState state) {
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

    // VISUAL OUTLINE
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state);
    }

    // COLLISION SHAPE (entities)
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        if (BarrierViewer.isBarrierVisible()) return BlockRenderType.MODEL;
        return BlockRenderType.INVISIBLE;
    }
}
