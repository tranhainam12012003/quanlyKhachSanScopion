package vn.teca.scopio.base.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@Component
@Slf4j
public class LanguageFilter extends OncePerRequestFilter {

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (Optional.ofNullable(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)).isPresent()) {
            try {
                String language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
                if (StringUtils.isBlank(language)) {
                    language = "vi";
                } else if (language.contains("en") || language.contains("EN")) {
                    language = "en";
                } else {
                    language = "vi";
                }
                Locale locale = new Locale(language);
//                Locale locale = new Locale("vi");
                localeResolver.setLocale(request, response, locale);
            } catch (Exception ex) {
                log.info("ERROR: " + ex.getMessage());
            }
        }

        chain.doFilter(request, response);
    }
}
