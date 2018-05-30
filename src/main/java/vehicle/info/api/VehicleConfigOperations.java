package vehicle.info.api;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.types.files.AttachedFile;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface VehicleConfigOperations<T extends VehicleConfig> {

    @Get("/")
    Single<List<T>> list();

    @Get("/first")
    Maybe<T> first();

    @Get("/filter/{type}")
    Single<List<T>> filter(String type);

    @Get("/view/{fileName}")
    AttachedFile view(String fileName);
}
