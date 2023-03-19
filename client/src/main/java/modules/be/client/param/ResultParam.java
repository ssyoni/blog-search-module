package modules.be.client.param;

import java.util.List;

public record ResultParam(
        Page meta,
        List<BlogInfo> documents
) {}
