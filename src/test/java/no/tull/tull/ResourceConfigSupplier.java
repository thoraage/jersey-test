package no.tull.tull;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class ResourceConfigSupplier {
    public static ResourceConfig get() {
//        DefaultResourceConfig resourceConfig = new DefaultResourceConfig(MyResource.class);
//        resourceConfig.getSingletons().add(MyExceptionMapper.class);
//        //noinspection unchecked
//        resourceConfig.getContainerResponseFilters().add(new GZIPContentEncodingFilter());
        return new ResourceConfig(MyResource.class)
                .register(MyExceptionMapper.class)
                .register(JacksonJaxbJsonProvider.class)
                .register(JacksonFeature.class);
    }
}
