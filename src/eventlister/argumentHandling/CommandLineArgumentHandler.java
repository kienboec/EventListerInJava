package eventlister.argumentHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // default
public class CommandLineArgumentHandler extends DefaultArgumentHandler {

    private final CommandLineArgumentStore _store; // from ctor injected and stored here

    @Autowired
    public CommandLineArgumentHandler(CommandLineArgumentStore store) {
        this._store = store;
        for (String item : _store.getArgs()) {
            this._filterCriterias.add(item);
        }
    }
}
