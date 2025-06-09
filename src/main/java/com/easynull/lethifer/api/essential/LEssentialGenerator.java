package com.easynull.lethifer.api.essential;

public interface LEssentialGenerator {
    float getBaudRateEssential();
    int getAmountRateEssential();
    default int getMaxEssential() {
        return 0;
    }
}
