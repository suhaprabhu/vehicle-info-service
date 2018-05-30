package vehicle.info.service;

import io.micronaut.http.client.Client;
import io.reactivex.Single;
import vehicle.info.api.VehicleConfig;
import vehicle.info.api.VehicleConfigOperations;

import java.util.List;

@Client("/config")
public interface InfoControllerTestClient extends VehicleConfigOperations<VehicleConfig> {
    @Override
    Single<List<VehicleConfig>> filter(String type);
}
