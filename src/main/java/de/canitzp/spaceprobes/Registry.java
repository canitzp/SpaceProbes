package de.canitzp.spaceprobes;

import de.canitzp.ctpcore.registry.MCRegistry;
import de.canitzp.spaceprobes.blocks.probebuilder.BlockProbeBuilder;
import de.canitzp.spaceprobes.items.ItemSpaceProbe;

/**
 * @author canitzp
 */
public class Registry{

    public static void preInit(){
        MCRegistry.register(new ItemSpaceProbe(ItemSpaceProbe.ProbeTypes.SPUTNIK));
        MCRegistry.register(new BlockProbeBuilder());
    }

}
