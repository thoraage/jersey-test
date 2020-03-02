package no.tull.tull;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static void main(String[] args) {
        try {
            ResourceConfig resourceConfig = ResourceConfigSupplier.get();
            Server server = JettyHttpContainerFactory.createServer(URI.create("http://0.0.0.0:5555"), resourceConfig);
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
