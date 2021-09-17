package com.example.configs

import javax.validation.constraints.NotNull

interface ApplicationConfiguration {
    @NotNull Integer getMax();
}
