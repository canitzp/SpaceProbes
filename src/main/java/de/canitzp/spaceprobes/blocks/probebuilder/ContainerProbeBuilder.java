package de.canitzp.spaceprobes.blocks.probebuilder;

import de.canitzp.ctpcore.inventory.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author canitzp
 */
public class ContainerProbeBuilder extends ContainerBase{

    @Override
    public boolean canInteractWith(EntityPlayer player){
        return true;
    }

}
