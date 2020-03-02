package no.tull.tull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JsonMapperConfigurator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestTest extends JerseyTest {

    @Override
    protected Application configure() {
        return ResourceConfigSupplier.get();
//        return new ResourceConfig(MyResource.class)
//                .register(new ContextResolver<ObjectMapper>() {
//                    public ObjectMapper getContext(Class<?> type) {
//                        return createObjectMapper();
//                    }
//                })
//                .register(new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bind(createObjectMapper()).to(ObjectMapper.class);
//                    }
//                });
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(createJsonMapperConfigurator());
    }

    private ObjectMapper createObjectMapper() {
        return createJsonMapperConfigurator().getConfiguredMapper();
    }

    private JsonMapperConfigurator createJsonMapperConfigurator() {
        return new JsonMapperConfigurator(new ObjectMapper(), new Annotations[] {Annotations.JACKSON, Annotations.JAXB});
    }

    @Test
    public void test() {
        Response response = target("path/here").request().post(Entity.json(new Struct("Hopp", 9)));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testBadRequestForNull() {
        Response response = target("path/here").request().post(Entity.json(new Struct(null, 9)));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        assertNotNull(errorMessage.getMessage());
        assertNotNull(errorMessage.getType());
    }

    @Test
    public void testBadRequestForMissing() {
        Response response = target("path/here").request().post(Entity.json("{\"i\": 7}"));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        assertNotNull(errorMessage.getMessage());
        assertNotNull(errorMessage.getType());
    }

}
