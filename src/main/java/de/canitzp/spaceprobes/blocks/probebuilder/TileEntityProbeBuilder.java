package de.canitzp.spaceprobes.blocks.probebuilder;

import de.canitzp.ctpcore.base.TileEntityBase;
import de.canitzp.ctpcore.base.TileEntityInventory;
import de.canitzp.spaceprobes.SpaceProbes;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class TileEntityProbeBuilder extends TileEntityInventory{

    public TileEntityProbeBuilder(){
        super(new ResourceLocation(SpaceProbes.MODID, "ProbeBuilder"), 1);
    }

}
