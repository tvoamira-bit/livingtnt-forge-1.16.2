package ru.livingtnt;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
final class StructureBuilder {
 private StructureBuilder() {}
 private static void put(World w, BlockPos p, BlockState s) { w.setBlockState(p,s,3); }
 private static void fill(World w, BlockPos o,int x1,int y1,int z1,int x2,int y2,int z2,BlockState s) { for(int x=x1;x<=x2;x++) for(int y=y1;y<=y2;y++) for(int z=z1;z<=z2;z++) put(w,o.add(x,y,z),s); }
 private static void room(World w,BlockPos o,int width,int depth,int height,BlockState wall,BlockState floor,BlockState roof,BlockState bed) {
  int hx=width/2,hz=depth/2; fill(w,o,-hx,0,-hz,hx,0,hz,floor); fill(w,o,-hx,height,-hz,hx,height,hz,roof);
  for(int y=1;y<height;y++){for(int x=-hx;x<=hx;x++){put(w,o.add(x,y,-hz),wall);put(w,o.add(x,y,hz),wall);}for(int z=-hz;z<=hz;z++){put(w,o.add(-hx,y,z),wall);put(w,o.add(hx,y,z),wall);}}
  put(w,o.add(0,1,hz),Blocks.AIR.getDefaultState());put(w,o.add(0,2,hz),Blocks.AIR.getDefaultState());
  put(w,o.add(0,1,hz),Blocks.OAK_DOOR.getDefaultState().with(DoorBlock.HALF,DoorBlock.DoubleBlockHalf.LOWER));put(w,o.add(0,2,hz),Blocks.OAK_DOOR.getDefaultState().with(DoorBlock.HALF,DoorBlock.DoubleBlockHalf.UPPER));
  if(width>=7){put(w,o.add(-hx,2,0),Blocks.GLASS_PANE.getDefaultState());put(w,o.add(hx,2,0),Blocks.GLASS_PANE.getDefaultState());}
  put(w,o.add(-1,1,0),Blocks.CRAFTING_TABLE.getDefaultState());put(w,o.add(1,1,0),Blocks.FURNACE.getDefaultState());put(w,o.add(0,1,-1),bed);
  put(w,o.add(-hx+1,1,-hz+1),Blocks.TORCH.getDefaultState());put(w,o.add(hx-1,1,-hz+1),Blocks.TORCH.getDefaultState());
 }
 private static void tower(World w,BlockPos o,BlockState wall,BlockState floor,BlockState roof,int h) {
  room(w,o,7,7,4,wall,floor,roof,Blocks.RED_BED.getDefaultState());
  for(int y=5;y<=h;y++){fill(w,o,-2,y,-2,2,y,2,roof);for(int x=-2;x<=2;x++){put(w,o.add(x,y,-2),wall);put(w,o.add(x,y,2),wall);}for(int z=-2;z<=2;z++){put(w,o.add(-2,y,z),wall);put(w,o.add(2,y,z),wall);}}
  put(w,o.add(0,h+1,0),Blocks.TORCH.getDefaultState());
 }
 static void build(World w,BlockPos o,StructureType t) { switch(t) {
 case HOUSE: room(w,o,7,7,4,Blocks.OAK_PLANKS.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.RED_BED.getDefaultState()); break;
 case CABIN: room(w,o,7,9,4,Blocks.SPRUCE_LOG.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.BROWN_BED.getDefaultState()); break;
 case CASTLE: room(w,o,13,13,6,Blocks.STONE_BRICKS.getDefaultState(),Blocks.STONE_BRICKS.getDefaultState(),Blocks.STONE_BRICKS.getDefaultState(),Blocks.RED_BED.getDefaultState()); for(int x=-6;x<=6;x+=12)for(int z=-6;z<=6;z+=12){fill(w,o,x,1,z,x,10,z,Blocks.STONE_BRICKS.getDefaultState());fill(w,o,x-1,10,z-1,x+1,10,z+1,Blocks.STONE_BRICK_SLAB.getDefaultState());} break;
 case MANSION: room(w,o,13,11,5,Blocks.QUARTZ_BLOCK.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.QUARTZ_BLOCK.getDefaultState(),Blocks.WHITE_BED.getDefaultState()); break;
 case BUNKER: fill(w,o,-5,-4,-5,5,-4,5,Blocks.IRON_BLOCK.getDefaultState());fill(w,o,-5,-3,-5,5,0,5,Blocks.IRON_BLOCK.getDefaultState());fill(w,o,-4,-3,-4,4,-1,4,Blocks.AIR.getDefaultState());fill(w,o,-4,0,-4,4,0,4,Blocks.IRON_BLOCK.getDefaultState());fill(w,o,0,-3,5,0,-2,5,Blocks.AIR.getDefaultState());put(w,o.add(0,-3,0),Blocks.CRAFTING_TABLE.getDefaultState());put(w,o.add(1,-3,0),Blocks.FURNACE.getDefaultState());put(w,o.add(-1,-3,0),Blocks.RED_BED.getDefaultState());break;
 case PAGODA: room(w,o,9,9,4,Blocks.DARK_OAK_PLANKS.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.DARK_OAK_PLANKS.getDefaultState(),Blocks.RED_BED.getDefaultState());fill(w,o,-5,5,-5,5,5,5,Blocks.DARK_OAK_STAIRS.getDefaultState());fill(w,o,-4,6,-4,4,6,4,Blocks.DARK_OAK_PLANKS.getDefaultState());fill(w,o,-3,7,-3,3,7,3,Blocks.DARK_OAK_PLANKS.getDefaultState());break;
 case COTTAGE: room(w,o,9,7,4,Blocks.OAK_LOG.getDefaultState(),Blocks.BIRCH_PLANKS.getDefaultState(),Blocks.OAK_STAIRS.getDefaultState(),Blocks.YELLOW_BED.getDefaultState());break;
 case STONE_HOUSE: room(w,o,9,9,4,Blocks.COBBLESTONE.getDefaultState(),Blocks.STONE_BRICKS.getDefaultState(),Blocks.STONE_BRICK_STAIRS.getDefaultState(),Blocks.GRAY_BED.getDefaultState());break;
 case SPRUCE_HOUSE: room(w,o,9,9,5,Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.SPRUCE_STAIRS.getDefaultState(),Blocks.BROWN_BED.getDefaultState());break;
 case BIRCH_HOUSE: room(w,o,9,7,4,Blocks.BIRCH_PLANKS.getDefaultState(),Blocks.BIRCH_PLANKS.getDefaultState(),Blocks.BIRCH_STAIRS.getDefaultState(),Blocks.WHITE_BED.getDefaultState());break;
 case JUNGLE_HOUSE: room(w,o,9,9,4,Blocks.JUNGLE_PLANKS.getDefaultState(),Blocks.JUNGLE_PLANKS.getDefaultState(),Blocks.JUNGLE_STAIRS.getDefaultState(),Blocks.GREEN_BED.getDefaultState());break;
 case DESERT_HOUSE: room(w,o,9,9,4,Blocks.SANDSTONE.getDefaultState(),Blocks.SMOOTH_SANDSTONE.getDefaultState(),Blocks.SANDSTONE_STAIRS.getDefaultState(),Blocks.YELLOW_BED.getDefaultState());break;
 case SNOW_HOUSE: room(w,o,9,9,4,Blocks.SNOW_BLOCK.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.SNOW_BLOCK.getDefaultState(),Blocks.LIGHT_BLUE_BED.getDefaultState());break;
 case SWAMP_HOUSE: room(w,o,9,9,4,Blocks.MOSSY_COBBLESTONE.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.LIME_BED.getDefaultState());fill(w,o,-5,-1,-5,5,-1,5,Blocks.OAK_FENCE.getDefaultState());break;
 case FARM_HOUSE: room(w,o,11,9,4,Blocks.OAK_LOG.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.HAY_BLOCK.getDefaultState(),Blocks.YELLOW_BED.getDefaultState());put(w,o.add(3,1,2),Blocks.BARREL.getDefaultState());put(w,o.add(-3,1,2),Blocks.COMPOSTER.getDefaultState());break;
 case TOWER_HOUSE: tower(w,o,Blocks.STONE_BRICKS.getDefaultState(),Blocks.STONE_BRICKS.getDefaultState(),Blocks.STONE_BRICK_SLAB.getDefaultState(),9);break;
 case WIZARD_TOWER: tower(w,o,Blocks.PURPUR_BLOCK.getDefaultState(),Blocks.PURPUR_BLOCK.getDefaultState(),Blocks.PURPUR_PILLAR.getDefaultState(),12);put(w,o.add(2,1,1),Blocks.ENCHANTING_TABLE.getDefaultState());put(w,o.add(-2,1,1),Blocks.BREWING_STAND.getDefaultState());break;
 case LIGHTHOUSE: tower(w,o,Blocks.WHITE_CONCRETE.getDefaultState(),Blocks.SPRUCE_PLANKS.getDefaultState(),Blocks.RED_CONCRETE.getDefaultState(),14);fill(w,o,-3,15,-3,3,15,3,Blocks.GLASS.getDefaultState());put(w,o.add(0,16,0),Blocks.SEA_LANTERN.getDefaultState());break;
 case MODERN_HOUSE: room(w,o,13,11,5,Blocks.WHITE_CONCRETE.getDefaultState(),Blocks.QUARTZ_BLOCK.getDefaultState(),Blocks.QUARTZ_BLOCK.getDefaultState(),Blocks.CYAN_BED.getDefaultState());fill(w,o,-5,2,5,5,3,5,Blocks.GLASS.getDefaultState());put(w,o.add(4,1,3),Blocks.SEA_LANTERN.getDefaultState());break;
 case TREEHOUSE: fill(w,o,-1,-1,-1,1,5,1,Blocks.OAK_LOG.getDefaultState());room(w,o,9,9,4,Blocks.OAK_PLANKS.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.OAK_PLANKS.getDefaultState(),Blocks.GREEN_BED.getDefaultState());fill(w,o,-1,1,5,1,1,8,Blocks.OAK_PLANKS.getDefaultState());for(int x=-2;x<=2;x+=4)for(int z=-2;z<=2;z+=4)fill(w,o,x,1,z,x,4,z,Blocks.OAK_LOG.getDefaultState());break;
 }} }
