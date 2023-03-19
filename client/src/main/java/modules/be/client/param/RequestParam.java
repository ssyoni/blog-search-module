package modules.be.client.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestParam implements Serializable {
    public String query;
    public String sort;
    public String page;
    public String size;
}
