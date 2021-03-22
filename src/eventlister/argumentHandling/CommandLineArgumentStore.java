package eventlister.argumentHandling;

import org.springframework.stereotype.Component;

@Component
public class CommandLineArgumentStore {
    private String[] args;

    public String[] getArgs() {
        return this.args;
    }

    public CommandLineArgumentStore(String[] args){
        this.args = args;
    }
}
