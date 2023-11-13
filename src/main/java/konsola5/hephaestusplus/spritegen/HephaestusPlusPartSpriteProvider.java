package konsola5.hephaestusplus.spritegen;

import konsola5.hephaestusplus.HephaestusPlus;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;


public class HephaestusPlusPartSpriteProvider extends AbstractPartSpriteProvider {

    public HephaestusPlusPartSpriteProvider() {
        super(HephaestusPlus.MOD_ID);
    }

    @Override
    public String getName() {
        return "HephaestusPlus Parts";
    }

    @Override
    protected void addAllSpites() { // sic
        buildTool("hand_hammer").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("crook").addBreakableHead("head").addHandle("handle").addBinding("binding");
    }
}
