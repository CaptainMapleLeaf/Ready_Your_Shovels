package rys.common.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import rys.common.block.ModBlocks;
import rys.common.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	
	public static final CaveFeature cave = create("cave", new CaveFeature(NoFeatureConfig::deserialize));
	public static final GradientFeature gradient = create("gradient", new GradientFeature(NoFeatureConfig::deserialize));
	public static final CaveDecorationFeature cave_decoration = create("cave_decoration", new CaveDecorationFeature(BushConfig::deserialize));
	public static final DepositsInCavesFeature deposits_in_caves = create("deposits_in_caves", new DepositsInCavesFeature(OreFeatureConfig::deserialize));
	public static final DepositsInRiversFeature deposits_in_rivers = create("deposits_in_rivers", new DepositsInRiversFeature(OreFeatureConfig::deserialize));
	
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		IForgeRegistry<Feature<?>> registry = event.getRegistry();
		
		registry.register(cave);
		registry.register(gradient);
		registry.register(cave_decoration);
		registry.register(deposits_in_caves);
		registry.register(deposits_in_rivers);
	}
	
	public static <T extends Feature<?>> T create(String name, T feature) {
		feature.setRegistryName(Reference.MOD_ID, name);
		return feature;
	}
	
	public static void registerFeatures() {
		Biome.BIOMES.forEach((Biome biome) -> {
			// CaveFeature
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(cave, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			
			// GradientFeature
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(gradient, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			
			// CaveDecorationFeature
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(cave_decoration, new BushConfig(Blocks.GRASS.getDefaultState()), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(cave_decoration, new BushConfig(Blocks.TALL_GRASS.getDefaultState()), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(cave_decoration, new BushConfig(Blocks.LILY_OF_THE_VALLEY.getDefaultState()), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			
			// DepositsInCavesFeature
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(deposits_in_caves, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.clay_deposit.getDefaultState(), 0), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(deposits_in_caves, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.peat_deposit.getDefaultState(), 0), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(deposits_in_caves, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.iron_deposit.getDefaultState(), 0), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			
			// DepositsInRiversFeature
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(deposits_in_rivers, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.gold_deposit.getDefaultState(), 0), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
			
		});
	}
	
}
