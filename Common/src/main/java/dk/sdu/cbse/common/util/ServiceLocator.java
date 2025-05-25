package dk.sdu.cbse.common.util;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * A singleton service locator that dynamically loads service implementations
 * from JAR files located in a predefined directory.
 * This uses the Java Platform Module System (JPMS) to find, configure, and load
 * modules from the plugins directory, and then locates implementations
 * of specified service interfaces using {@link ServiceLoader}.
 *
 * This is implemented as an enum singleton to ensure a single instance and
 * thread-safe lazy initialization of the plugin module layer.
 * Example usage:
 * {@code
 * List<IMyPlugin> plugins = ServiceLocator.INSTANCE.locateAll(IMyPlugin.class);
 * }
 */
public enum ServiceLocator {

    /**
     * The singleton instance of the ServiceLocator.
     */
    INSTANCE;
    private static final Map<Class, ServiceLoader> LOADERMAP = new HashMap<>();
    private final ModuleLayer layer;

    /**
     * Constructs the ServiceLocator and initializes the JPMS module layer
     * for all plugins found in the {@code plugins} directory.
     *
     * This constructor performs the following steps:
     * Searches the {@code plugins} directory for JARs that define modules
     * Creates a {@link ModuleFinder} for those plugins
     * Resolves a configuration for the plugins using the system module layer
     * Defines a new {@link ModuleLayer} with a common system class loader
     *
     * @throws RuntimeException if any error occurs during
     * module resolution or layer definition
     */
    ServiceLocator() {
        try {
            // Directory with plugins JARs
            Path pluginsDir = Paths.get("plugins");

            // Search for plugins in the plugins directory
            ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

            // Find names of all found plugin modules
            List<String> plugins = pluginsFinder
                    .findAll()
                    .stream()
                    .map(ModuleReference::descriptor)
                    .map(ModuleDescriptor::name)
                    .collect(Collectors.toList());

            // Create configuration that will resolve plugin modules
            Configuration pluginsConfiguration = ModuleLayer
                    .boot()
                    .configuration()
                    .resolve(pluginsFinder, ModuleFinder.of(), plugins);

            // Create a module layer for plugins
            layer = ModuleLayer
                    .boot()
                    .defineModulesWithOneLoader(
                            pluginsConfiguration,
                            ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to initialize plugin module layer", e);
        }
    }

    /**
     * Locates and returns all service implementations of the given interface
     * type from the loaded plugin modules.
     * This method uses Java's {@link ServiceLoader} to load implementations
     * of the specified service interface from the plugin module layer.
     * Loaded services are cached to avoid redundant lookups.
     *
     * @param <T>     The type of the service interface
     * @param service The class object representing the service interface
     * @return A list of all discovered service implementations,
     * or an empty list if none are found
     * @throws NullPointerException if {@code service} is null
     */
    public <T> List<T> locateAll(final Class<T> service) {
        ServiceLoader<T> loader = LOADERMAP.get(service);

        if (loader == null) {
            loader = ServiceLoader.load(layer, service);
            LOADERMAP.put(service, loader);
        }

        List<T> list = new ArrayList<>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

        return list;
    }
}
