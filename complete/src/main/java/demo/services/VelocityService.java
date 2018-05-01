package demo.services;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

@Singleton
public class VelocityService {

    private VelocityEngine velocityEngine;

    @PostConstruct
    void initialize() {
        final Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine = new VelocityEngine(p);
    }

    public String render(final String name, Map<String, Object> data) {
        final StringWriter writer = new StringWriter();
        final VelocityContext velocityContext = new VelocityContext(data);
        velocityEngine.mergeTemplate(templateName(name), StandardCharsets.UTF_8.name(), velocityContext, writer);
        return writer.toString();
    }

    String templateName(final String name) {
        final StringBuilder sb = new StringBuilder();
        sb.append("templates/");
        sb.append(name);
        return sb.toString();
    }
}
