package vehicle.info.service;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("vehicle")
public class InfoConfiguration {

    private String infoConfig;

    public String getInfoConfig() {
        return infoConfig;
    }

    public void setInfoConfig(String infoConfig) {
        this.infoConfig = infoConfig;
    }
}
