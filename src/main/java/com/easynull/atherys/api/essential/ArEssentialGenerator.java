package com.easynull.atherys.api.essential;

public interface ArEssentialGenerator {
    float getBaudRateEssential();
    int getAmountRateEssential();
    default int getMaxEssential() {
        return 0;
    }
}
