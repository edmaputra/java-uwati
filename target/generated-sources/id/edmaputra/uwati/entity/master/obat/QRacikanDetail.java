package id.edmaputra.uwati.entity.master.obat;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRacikanDetail is a Querydsl query type for RacikanDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRacikanDetail extends EntityPathBase<RacikanDetail> {

    private static final long serialVersionUID = -2066197027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRacikanDetail racikanDetail = new QRacikanDetail("racikanDetail");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<java.math.BigDecimal> hargaSatuan = createNumber("hargaSatuan", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final QObat komposisi;

    public final QRacikan racikan;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QRacikanDetail(String variable) {
        this(RacikanDetail.class, forVariable(variable), INITS);
    }

    public QRacikanDetail(Path<? extends RacikanDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRacikanDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRacikanDetail(PathMetadata<?> metadata, PathInits inits) {
        this(RacikanDetail.class, metadata, inits);
    }

    public QRacikanDetail(Class<? extends RacikanDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.komposisi = inits.isInitialized("komposisi") ? new QObat(forProperty("komposisi"), inits.get("komposisi")) : null;
        this.racikan = inits.isInitialized("racikan") ? new QRacikan(forProperty("racikan")) : null;
    }

}

