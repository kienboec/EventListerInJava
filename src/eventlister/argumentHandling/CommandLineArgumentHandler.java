package eventlister.argumentHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * By adding the @Component, Spring is able to detect the class. @Scope defines the lifetime of an object and the
 * instantiation behavior. In this case, Spring will instantiate only a single CommandLineArgumentHandler and will
 * always provide this single instance during the whole program lifetime.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // default
public class CommandLineArgumentHandler extends DefaultArgumentHandler {

    private final CommandLineArgumentStore _store; // from ctor injected and stored here

    /**
     * In our case, the CommandLineArgumentHandler is instantiated via Spring, which also provides
     * the @Autowired argument handler parameter.
     * @param store The container holding the commandline arguments
     */
    @Autowired
    public CommandLineArgumentHandler(CommandLineArgumentStore store) {
        this._store = store;
        for (String item : _store.getArgs()) {
            this._filterCriterias.add(item);
        }
    }
}
