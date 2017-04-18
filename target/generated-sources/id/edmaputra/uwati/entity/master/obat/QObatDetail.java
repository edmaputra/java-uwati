package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QObatDetail is a Querydsl query type for ObatDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObatDetail extends EntityPathBase<ObatDetail> {

    private static final long serialVersionUID = 951313678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObatDetail obatDetail = new QObatDetail("obatDetail");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<java.math.BigDecimal> hargaBeli = createNumber("hargaBeli", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaDiskon = createNumber("hargaDiskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJualResep = createNumber("hargaJualResep", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QObat obat;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QObatDetail(String variable) {
        this(ObatDetail.class, forVariable(variable), INITS);
    }

    public QObatDetail(Path<? extends ObatDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObatDetail(PathMetadata<?> metadata, PathInits inits) {
        this(ObatDetail.class, metadata, inits);
    }

    public QObatDetail(Class<? extends ObatDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.obat = inits.isInitialized("obat") ? new QObat(forProperty("obat"), inits.get("obat")) : null;
    }

}

