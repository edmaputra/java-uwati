package id.edmaputra.uwati.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDasarEntity is a Querydsl query type for DasarEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QDasarEntity extends EntityPathBase<DasarEntity<?>> {

    private static final long serialVersionUID = -1848441297L;

    public static final QDasarEntity dasarEntity = new QDasarEntity("dasarEntity");

    public final StringPath info = createString("info");

    public final DateTimePath<java.util.Date> terakhirDirubah = createDateTime("terakhirDirubah", java.util.Date.class);

    public final StringPath userEditor = createString("userEditor");

    public final StringPath userInput = createString("userInput");

    public final DateTimePath<java.util.Date> waktuDibuat = createDateTime("waktuDibuat", java.util.Date.class);

    @SuppressWarnings("all")
    public QDasarEntity(String variable) {
        super((Class)DasarEntity.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QDasarEntity(Path<? extends DasarEntity> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    @SuppressWarnings("all")
    public QDasarEntity(PathMetadata<?> metadata) {
        super((Class)DasarEntity.class, metadata);
    }

}

