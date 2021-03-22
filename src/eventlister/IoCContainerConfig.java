package eventlister;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("eventlister")
@ComponentScan("eventlister.argumentHandling")
@ComponentScan("eventlister.communicationHandling")
@ComponentScan("eventlister.filterHandling")
public class IoCContainerConfig {
}
