package ru.livingtnt;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(LivingTNT.MOD_ID)
public class LivingTNT {
 public static final String MOD_ID = "livingtnt";
 public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
 public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
 public static final RegistryObject<Block> HOUSE_TNT = register("house_tnt", StructureType.HOUSE);
 public static final RegistryObject<Block> CABIN_TNT = register("cabin_tnt", StructureType.CABIN);
 public static final RegistryObject<Block> CASTLE_TNT = register("castle_tnt", StructureType.CASTLE);
 public static final RegistryObject<Block> MANSION_TNT = register("mansion_tnt", StructureType.MANSION);
 public static final RegistryObject<Block> BUNKER_TNT = register("bunker_tnt", StructureType.BUNKER);
 public static final RegistryObject<Block> PAGODA_TNT = register("pagoda_tnt", StructureType.PAGODA);
 public static final RegistryObject<Block> COTTAGE_TNT = register("cottage_tnt", StructureType.COTTAGE);
 public static final RegistryObject<Block> STONE_HOUSE_TNT = register("stone_house_tnt", StructureType.STONE_HOUSE);
 public static final RegistryObject<Block> SPRUCE_HOUSE_TNT = register("spruce_house_tnt", StructureType.SPRUCE_HOUSE);
 public static final RegistryObject<Block> BIRCH_HOUSE_TNT = register("birch_house_tnt", StructureType.BIRCH_HOUSE);
 public static final RegistryObject<Block> JUNGLE_HOUSE_TNT = register("jungle_house_tnt", StructureType.JUNGLE_HOUSE);
 public static final RegistryObject<Block> DESERT_HOUSE_TNT = register("desert_house_tnt", StructureType.DESERT_HOUSE);
 public static final RegistryObject<Block> SNOW_HOUSE_TNT = register("snow_house_tnt", StructureType.SNOW_HOUSE);
 public static final RegistryObject<Block> SWAMP_HOUSE_TNT = register("swamp_house_tnt", StructureType.SWAMP_HOUSE);
 public static final RegistryObject<Block> FARM_HOUSE_TNT = register("farm_house_tnt", StructureType.FARM_HOUSE);
 public static final RegistryObject<Block> TOWER_HOUSE_TNT = register("tower_house_tnt", StructureType.TOWER_HOUSE);
 public static final RegistryObject<Block> WIZARD_TOWER_TNT = register("wizard_tower_tnt", StructureType.WIZARD_TOWER);
 public static final RegistryObject<Block> LIGHTHOUSE_TNT = register("lighthouse_tnt", StructureType.LIGHTHOUSE);
 public static final RegistryObject<Block> MODERN_HOUSE_TNT = register("modern_house_tnt", StructureType.MODERN_HOUSE);
 public static final RegistryObject<Block> TREEHOUSE_TNT = register("treehouse_tnt", StructureType.TREEHOUSE);
 private static RegistryObject<Block> register(String name, StructureType type) {
  RegistryObject<Block> block = BLOCKS.register(name, () -> new BuildingTntBlock(type));
  ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(net.minecraft.item.ItemGroup.REDSTONE)));
  return block;
 }
 public LivingTNT() {
  IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
  BLOCKS.register(bus); ITEMS.register(bus);
 }
}
