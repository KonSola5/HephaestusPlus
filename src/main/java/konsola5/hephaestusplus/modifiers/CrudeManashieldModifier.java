package konsola5.hephaestusplus.modifiers;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class CrudeManashieldModifier extends ManashieldModifier {
    @Override
    public double getChance() {
        return 0.15;
    }
}
