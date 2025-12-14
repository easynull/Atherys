package com.easynull.atherys.core.essential;

public interface AreGenerator {
    float getBaudRateEssential();
    int getAmountRateEssential();
    default int getMaxEssential() {
        return 0;
    }
}
