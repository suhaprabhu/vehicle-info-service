package vehicle.info.service;

import eu.medsea.mimeutil.MimeUtil;
import io.micronaut.configurations.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.server.types.files.AttachedFile;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.apache.commons.io.FilenameUtils;
import vehicle.info.api.VehicleConfig;
import vehicle.info.api.VehicleConfigOperations;
import vehicle.info.utils.MimeTypeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suhasini Sethuraman
 * @since 1.0
 */
@Controller("/config")
@Validated
public class InfoController implements VehicleConfigOperations<VehicleConfig> {

    private final InfoConfiguration configuration;

    private final String configDirString;

    public InfoController(InfoConfiguration configuration) {
        this.configuration = configuration;
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        this.configDirString = configuration.getInfoConfig();
        if (this.configDirString == null) {
            System.err.println("Unable to read the configuration from application.yml!");
        }
    }

    @Override
    @HystrixCommand
    public Single<List<VehicleConfig>> list() {
        return Flowable.fromArray(getData(null)).toList();
    }

    @Override
    @HystrixCommand
    public Maybe<VehicleConfig> first() {
        return Flowable.fromArray(getData(null)).firstElement();
    }

    @Override
    @HystrixCommand
    public Single<List<VehicleConfig>> filter(String type) {
        return Flowable.fromArray(getData(type)).toList();
    }

    @Override
    @HystrixCommand
    public AttachedFile view(String fileName) {
        // FIXME: Sanitise the file name here. Or use id instead of filename.
        File requestedFile = new File(this.configDirString + File.separator + fileName);
        if (requestedFile != null) {
            return new AttachedFile(requestedFile, fileName);
        }
        System.err.println("Requested file " + fileName + " is not found!");
        return null;
    }

    /**
     * Method to get list of files from a directory
     *
     * @return Array of Vehicle config
     */
    private VehicleConfig[] getData(String typeFilter) {
        File configDir = new File(this.configDirString);
        File[] configFiles = configDir.listFiles();
        List<VehicleConfig> dataList = new ArrayList<VehicleConfig>();
        if (configFiles != null) {
            for (File configF : configFiles) {
                if (configF.isFile()) {
                    String extension = FilenameUtils.getExtension(configF.getName());
                    VehicleConfig config = new VehicleConfig(configF.getName(), getMimeType(configF.getName()), configF.length(), extension);
                    if (typeFilter != null && !extension.equalsIgnoreCase(typeFilter)) {
                        break;
                    }
                    dataList.add(config);
                }
            }
        } else {
            System.err.println("Create some files in the directory " + configDir.getAbsolutePath());
        }
        return dataList.stream().toArray(VehicleConfig[]::new);
    }

    private static String getMimeType(String fileName) {
        return MimeTypeUtils.getContentTypeByFileName(fileName);
    }
}
