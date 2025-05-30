module Core {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    opens dk.sdu.cbse.main to javafx.graphics, spring.core, spring.beans, spring.context;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}


