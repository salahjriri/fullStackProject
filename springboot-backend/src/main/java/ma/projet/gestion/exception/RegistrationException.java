package ma.projet.gestion.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "Registration can not be completed because of invalid data!"
)
public class RegistrationException extends RuntimeException {

    private String message;

    private Throwable exception;

    public RegistrationException() {
        super();
    }

    public RegistrationException(String message, Throwable exception) {
        super(message, exception);
    }

    public RegistrationException(String message) {
        super(message);
    }

}
