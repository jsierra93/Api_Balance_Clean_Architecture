package co.com.jsierra.model.transaction;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RqPagination {
    private int size;
    private int key;
}
