package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QObatExpired is a Querydsl query type for ObatExpired
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObatExpired extends EntityPathBase<ObatExpired> {

    private static final long serialVersionUID = 853963272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObatExpired obatExpired = new QObatExpired("obatExpired");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final QObat obat;

    public final DatePath<java.util.Date> tanggalExpired = createDate("tanggalExpired", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QObatExpired(String variable) {
        this(ObatExpired.class, forVariable(variable), INITS);
    }

    public QObatExpired(Path<? extends ObatExpired> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatExpired(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatExpired(PathMetadata<?> metadata, PathInits inits) {
        this(ObatExpired.class, metadata, inits);
    }

    public QObatExpired(Class<? extends ObatExpired> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.obat = inits.isInitialized("obat") ? new QObat(forProperty("obat"), inits.get("obat")) : null;
    }

}

