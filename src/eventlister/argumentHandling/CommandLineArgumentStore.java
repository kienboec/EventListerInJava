package eventlister.argumentHandling;

import org.springframework.stereotype.Component;

/**
 * By adding the @Component, Spring is able to detect the class. We register this class manually in Main and provide the
 * commandline args for instantiation.
 */
@Component
public class CommandLineArgumentStore {
    private String[] args;

    public String[] getArgs() {
        return this.args;
    }

    public CommandLineArgumentStore(String[] args) {
        this.args = args;
    }
}
