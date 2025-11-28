package com.trainmurdermysteryextras.datagen;

import com.mojang.datafixers.util.Pair;
import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.index.TMMBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class TMMEModelGen extends FabricModelProvider {

    public TMMEModelGen(FabricDataOutput output) {
        super(output);
    }

    private static final Model GLASS_PANEL = template(
            "block/template_glass_panel", TextureKey.PANE, TextureKey.EDGE
    );

    private static final Model PANEL = template(
            "block/template_panel", TextureKey.ALL
    );

    private static Model template(Identifier parent, @Nullable String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(parent), Optional.ofNullable(variant), requiredTextureKeys);
    }

    private static Model template(Identifier parent, TextureKey... requiredTextureKeys) {
        return template(parent, null, requiredTextureKeys);
    }

    private static Model template(String parentName, TextureKey... requiredTextureKeys) {
        return template(TMM.id(parentName), requiredTextureKeys);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // Stepable panels
        this.registerPanel(generator, TMMEBlocks.STEPABLE_NAVY_STEEL_TILES_PANEL, TMMBlocks.NAVY_STEEL_TILES);
        this.registerPanel(generator, TMMEBlocks.STEPABLE_SMOOTH_NAVY_STEEL_PANEL, TMMBlocks.SMOOTH_NAVY_STEEL);
        this.registerPanel(generator, TMMEBlocks.STEPABLE_NAVY_STEEL_PANEL, TMMBlocks.NAVY_STEEL);
        this.registerPanel(generator, TMMEBlocks.STEPABLE_PANEL, TMMBlocks.GOLDEN_GLASS_PANEL);

        // Glass panels
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN);
        this.registerGlassPanel(generator, TMMEBlocks.GOLDEN_GLASS_PANEL_EMPTY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        // Leave empty if you don't have custom item models
    }

    private BlockStateVariant model(Identifier model) {
        return this.variant(VariantSettings.MODEL, model);
    }
    private BlockStateVariant variant() {
        return BlockStateVariant.create();
    }
    private <T> BlockStateVariant variant(VariantSetting<T> variantSetting, T value) {
        return this.variant().put(variantSetting, value);
    }

    // Copy implementations from base mod or provide your own
    private void registerPanel(BlockStateModelGenerator generator, Block block, Identifier texture) {
        Models.GENERATED.upload(ModelIds.getItemModelId(block.asItem()), TextureMap.layer0(texture), generator.modelCollector);
        Identifier model = PANEL.upload(block, TextureMap.all(texture), generator.modelCollector);
        MultipartBlockStateSupplier blockStateSupplier = MultipartBlockStateSupplier.create(block);
        When.PropertyCondition propertyCondition = When.create();
        BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS.stream().map(Pair::getFirst)
                .forEach(property -> propertyCondition.set(property, false));

        for (Pair<BooleanProperty, Function<Identifier, BlockStateVariant>> pair : BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS) {
            BooleanProperty facingProperty = pair.getFirst();
            BlockStateVariant variant = pair.getSecond().apply(model);
            blockStateSupplier.with(When.create().set(facingProperty, true), variant);
            blockStateSupplier.with(propertyCondition, variant);
        }

        generator.blockStateCollector.accept(blockStateSupplier);
    }

    private void registerPanel(BlockStateModelGenerator generator, Block block, Block textureBlock) {
        registerPanel(generator, block, TextureMap.getId(textureBlock));
    }

    private void registerGlassPanel(BlockStateModelGenerator generator, Block block) {
        generator.registerItemModel(block, "_trim");
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.PANE, TextureMap.getId(block))
                .put(TextureKey.EDGE, TextureMap.getSubId(block, "_trim"));
        Identifier model = GLASS_PANEL.upload(block, textureMap, generator.modelCollector);
        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block, this.model(model))
                        .coordinate(BlockStateVariantMap.create(Properties.FACING)
                                .register(Direction.UP, this.variant(VariantSettings.X, VariantSettings.Rotation.R90))
                                .register(Direction.DOWN, this.variant(VariantSettings.X, VariantSettings.Rotation.R270))
                                .register(Direction.SOUTH, this.variant())
                                .register(Direction.NORTH, this.variant(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(Direction.EAST, this.variant(VariantSettings.Y, VariantSettings.Rotation.R270))
                                .register(Direction.WEST, this.variant(VariantSettings.Y, VariantSettings.Rotation.R90))));
    }
}
