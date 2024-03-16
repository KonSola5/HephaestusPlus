package konsola5.hephaestusplus.modifiers.dynamic;

import com.google.gson.JsonObject;
import konsola5.hephaestusplus.modifiers.BatteryModifier;
import lombok.Setter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import slimeknights.mantle.data.GenericLoaderRegistry.IGenericLoader;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class DynamicBatteryModifier extends BatteryModifier {

    public DynamicBatteryModifier(long capacity, long transferRate) {
        super(capacity, transferRate);
    }

    public static final IGenericLoader<DynamicBatteryModifier> LOADER = new IGenericLoader<>() {
        @Override
        public DynamicBatteryModifier deserialize(JsonObject json) {
            long capacity = GsonHelper.getAsLong(json, "capacity", 0);
            long transferRate = GsonHelper.getAsLong(json, "transfer_rate", 0);
            return new DynamicBatteryModifier(capacity, transferRate);
        }

        @Override
        public void serialize(DynamicBatteryModifier object, JsonObject json) {
            json.addProperty("capacity", object.getCapacity());
            json.addProperty("transfer_rate", object.getTransferRate());
        }

        @Override
        public DynamicBatteryModifier fromNetwork(FriendlyByteBuf buffer) {
            long capacity = buffer.readVarLong();
            long transferRate = buffer.readVarLong();
            return new DynamicBatteryModifier(capacity, transferRate);
        }

        @Override
        public void toNetwork(DynamicBatteryModifier object, FriendlyByteBuf buffer) {
            buffer.writeVarLong(object.getCapacity());
            buffer.writeVarLong(object.getTransferRate());
        }
    };

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public IGenericLoader<? extends Modifier> getLoader() {
        return LOADER;
    }

    public static class Builder {
        @Setter
        private long capacity = 0;
        @Setter
        private long transferRate = 0;

        public DynamicBatteryModifier build() {
            return new DynamicBatteryModifier(capacity, transferRate);
        }

        public Builder capacity(long capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder transferRate(long transferRate) {
            this.transferRate = transferRate;
            return this;
        }
    }
}
