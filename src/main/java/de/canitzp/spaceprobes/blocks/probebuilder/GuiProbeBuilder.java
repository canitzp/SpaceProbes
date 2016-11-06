package de.canitzp.spaceprobes.blocks.probebuilder;

import de.canitzp.ctpcore.inventory.GuiContainerBase;
import de.canitzp.spaceprobes.SpaceProbes;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class GuiProbeBuilder extends GuiContainerBase{

    public static final ResourceLocation guiLoc = new ResourceLocation(SpaceProbes.MODID, "textures/gui/guiProbeBuilder.png");

    public GuiProbeBuilder(){
        super(new ContainerProbeBuilder());
        this.xSize = 200;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        clearColor();
        drawBackgroundLocation(guiLoc);
    }

}
