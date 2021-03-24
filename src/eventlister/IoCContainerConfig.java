package eventlister;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Here, spring automagically builds a new context configuration by scanning the listed packages for classes that have
 * the @Component annotation. These classes will be added to the configuration.
 */
@Configuration
@ComponentScan("eventlister")
@ComponentScan("eventlister.argumentHandling")
@ComponentScan("eventlister.communicationHandling")
@ComponentScan("eventlister.filterHandling")
public class IoCContainerConfig {
}
