package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QObatStok is a Querydsl query type for ObatStok
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObatStok extends EntityPathBase<ObatStok> {

    private static final long serialVersionUID = 1091952634L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObatStok obatStok = new QObatStok("obatStok");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final QObat obat;

    public final NumberPath<Integer> stok = createNumber("stok", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QObatStok(String variable) {
        this(ObatStok.class, forVariable(variable), INITS);
    }

    public QObatStok(Path<? extends ObatStok> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatStok(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatStok(PathMetadata<?> metadata, PathInits inits) {
        this(ObatStok.class, metadata, inits);
    }

    public QObatStok(Class<? extends ObatStok> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.obat = inits.isInitialized("obat") ? new QObat(forProperty("obat"), inits.get("obat")) : null;
    }

}

