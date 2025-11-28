package com.trainmurdermysteryextras.datagen;

import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.block.OrnamentBlock;
import dev.doctor4t.trainmurdermystery.block.PanelBlock;
import dev.doctor4t.trainmurdermystery.block.property.OrnamentShape;
import dev.doctor4t.trainmurdermystery.index.TMMBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BedPart;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.Direction;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class TMMEBlockLootTableGen extends FabricBlockLootTableProvider {

    public TMMEBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_EMPTY);


    }

    private void addFamily(BlockFamily family) {
        this.addFamily(family, this::addSelfDrop);
    }

    private void addFamily(BlockFamily family, Consumer<Block> consumer) {
        family.getVariants().values().forEach(consumer);
        consumer.accept(family.getBaseBlock());
    }

    private void addSelfDrop(Block block) {
        this.addSelfDrop(block, this::drops);
    }

    private void addSelfDrop(Block block, Function<Block, LootTable.Builder> function) {
        if (block.getHardness() == -1.0f) {
            // Register drops as nothing if block is unbreakable
            this.addDrop(block, dropsNothing());
        } else {
            this.addDrop(block, function);
        }
    }

    private void addNothingDrop(Block block) {
        this.addDrop(block, dropsNothing());
    }

    private ConstantLootNumberProvider count(float value) {
        return ConstantLootNumberProvider.create(value);
    }

    private LootTable.Builder panelDrops(Block block) {
        return LootTable.builder().pool(LootPool.builder().with(
                this.addSurvivesExplosionCondition(block, ItemEntry.builder(block))
                        .apply(
                                Direction.values(),
                                direction -> SetCountLootFunction.builder(this.count(1), true)
                                        .conditionally(BlockStatePropertyLootCondition.builder(block).properties(
                                                StatePredicate.Builder.create().exactMatch(PanelBlock.getProperty(direction), true)
                                        ))
                        ).apply(SetCountLootFunction.builder(this.count(-1f), true))
        ));
    }

    private LootTable.Builder ornamentDrops(Block block) {
        return LootTable.builder().pool(LootPool.builder().with(
                this.addSurvivesExplosionCondition(block, ItemEntry.builder(block))
                        .apply(
                                OrnamentShape.values(),
                                shape -> SetCountLootFunction.builder(this.count(shape.getCount()), false)
                                        .conditionally(BlockStatePropertyLootCondition.builder(block).properties(
                                                StatePredicate.Builder.create().exactMatch(OrnamentBlock.SHAPE, shape)
                                        ))
                        )
        ));
    }
}
