package eventlister.contentInterpretation;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * By adding the @Component, Spring is able to detect the class and register it.
 */
@Component
public class HTTPOutputInterpreter implements ContentInterpreter {

    private List<String> cachedOutput = null;

    @Override
    public List<String> Interpret(String content) {

        // maybe this should check whether the content has changed before returning cached results
        if (cachedOutput != null) {
            return cachedOutput;
        }

        List<String> entries = new ArrayList<String>();

        Pattern pattern = Pattern.compile("<div\\ class=\"views-row.*?<div.*?class=\"title\".*?<a\\ href.*?\">\\W*(.*?)\\W*<\\/a>.*?<a class=\"more-link\".*?<\\/div>",
                Pattern.DOTALL); // singleline
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            var entry = matcher.group(1);
            entries.add(entry);
        }

        return cachedOutput = entries;
    }
}
