package de.canitzp.spaceprobes.blocks.probebuilder;

import de.canitzp.ctpcore.base.BlockContainerBase;
import de.canitzp.spaceprobes.SpaceProbes;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class BlockProbeBuilder extends BlockContainerBase{

    public BlockProbeBuilder(){
        super(SpaceProbes.INSTANCE, Material.IRON, new ResourceLocation(SpaceProbes.MODID, "blockProbeBuilder"), TileEntityProbeBuilder.class);
        addGuiContainer(GuiProbeBuilder.class, ContainerProbeBuilder.class);
    }

}
