package com.example.configs

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.core.annotation.NonNull

@ConfigurationProperties("application")
class ApplicationConfigurationProperties implements ApplicationConfiguration{
    protected final Integer DEFAULT_MAX = 10;

    @NonNull
    Integer max = DEFAULT_MAX;

    @Override
    @NonNull
    Integer getMax() {
        return max;
    }

    void setMax(@NonNull Integer max) {
        if (max != null) {
            this.max = max;
        }
    }
}
