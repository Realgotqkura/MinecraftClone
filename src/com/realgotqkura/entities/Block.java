package com.realgotqkura.entities;
import com.realgotqkura.utilities.BlockLocation;
import java.util.HashMap;
import java.util.Map;


public class Block extends GameModel {

    private BlockLocation loc;
    public static final Map<BlockLocation, Block> blocks = new HashMap<>();

    public Block(GameModel model, BlockLocation location) {
        super(model.getMesh(), location.translateToVec3());
        GameModel.blocks.add(this);
        this.loc = location;
        blocks.put(loc, this);
    }

    public static BlockLocation[] getBlockNeighbours(Block block) {
        BlockLocation start = block.getLocation();
        return new BlockLocation[]{new BlockLocation(start.getX() + 1, start.getY(), start.getZ()), new BlockLocation(start.getX() - 1, start.getY(), start.getZ())
                , new BlockLocation(start.getX(), start.getY() + 1, start.getZ()), new BlockLocation(start.getX(), start.getY() - 1, start.getZ()),
                new BlockLocation(start.getX(), start.getY(), start.getZ() + 1), new BlockLocation(start.getX(), start.getY(), start.getZ() - 1)};
    }

    public BlockLocation getLocation() {
        return loc;
    }

    public void setLocation(BlockLocation loc) {
        this.loc = loc;
        super.setPosition((float)loc.getX(),(float)loc.getY(),(float)loc.getZ());
    }

    public boolean neighboursAir() {
        for(BlockLocation loc : getBlockNeighbours(this)){
            if(getBlockAt(loc) == null){
                //System.out.println("EEE");
                return true;
            }
        }
        //System.out.println("HEHEHEHAW");
        return false;
    }

    public static Block getBlockAt(BlockLocation loc) {
        for(Block block : GameModel.blocks){
            BlockLocation nloc = block.getLocation();
            if(nloc.getX() == loc.getX() && nloc.getY() == loc.getY() && nloc.getZ() == loc.getZ()){
                return block;
            }
        }
        //System.out.println(loc.toString());
        return null;
    }

    public static boolean isBlockNull(BlockLocation loc) {
        for(Block block : GameModel.blocks){
            if(block.getLocation().equals(loc)){
                System.out.println("false");
                return false;
            }
        }
        System.out.println("true");
        return true;
    }
}
