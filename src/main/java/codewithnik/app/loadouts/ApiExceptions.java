package codewithnik.app.loadouts;
import lombok.AllArgsConstructor;    
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ApiExceptions {
        private String message;
        private boolean success;
}
