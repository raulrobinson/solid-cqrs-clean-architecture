package com.example.ws.infrastructure.shared.util;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Component;

@Component
public class Shield {

    public static String blindStr(String value) {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        return policy.sanitize(value)
                .replace("&#34;", "'")
                .replace("&#43;", "+")
                .replace("&#39;", "'")
                .replace("&#61;", "=")
                .replace("&amp;", " & ")
                .replace("&#64;", "@");
    }

}
