package vn.teca.scopio.base.config.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.teca.scopio.base.model.login.UserDTO;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Objects::nonNull)
                .filter(authentication -> authentication.getPrincipal() instanceof UserDTO)
                .map(authentication -> (UserDTO) authentication.getPrincipal())
                .map(UserDTO::getLogin);
    }
}
