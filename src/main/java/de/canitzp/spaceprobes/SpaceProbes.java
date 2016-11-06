package de.canitzp.spaceprobes;

import de.canitzp.ctpcore.NotCorrectEnvironmentException;
import de.canitzp.ctpcore.inventory.CTPGuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * @author canitzp
 */
@Mod(modid = SpaceProbes.MODID, name = SpaceProbes.NAME, version = SpaceProbes.VERSION)
public class SpaceProbes{

    public static final String MODID = "spaceprobes";
    public static final String NAME = "Space Probes";
    public static final String VERSION = "@VERSION@";

    public static final boolean dev = VERSION.equals("@VERSION@");
    public static Logger logger;

    @Mod.Instance(MODID)
    public static SpaceProbes INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws NotCorrectEnvironmentException{
        logger = event.getModLog();
        if(dev) logger.info("You're starting '" + NAME + "' in a development environment.");
        //CTPCore.checkEnvironment(event);
        CTPGuiHandler.registerMod(INSTANCE);
        Registry.preInit();
    }

}
