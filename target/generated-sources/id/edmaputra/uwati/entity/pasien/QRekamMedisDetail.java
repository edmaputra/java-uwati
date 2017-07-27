package id.edmaputra.uwati.entity.pasien;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRekamMedisDetail is a Querydsl query type for RekamMedisDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRekamMedisDetail extends EntityPathBase<RekamMedisDetail> {

    private static final long serialVersionUID = -1338809434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRekamMedisDetail rekamMedisDetail = new QRekamMedisDetail("rekamMedisDetail");

    public final id.edmaputra.uwati.entity.QDasarEntity _super = new id.edmaputra.uwati.entity.QDasarEntity(this);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaTotal = createNumber("hargaTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final QRekamMedis rekamMedis;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    public final StringPath terapi = createString("terapi");

    public final NumberPath<Integer> tipe = createNumber("tipe", Integer.class);

    //inherited
    public final StringPath userEditor = _super.userEditor;

    //inherited
    public final StringPath userInput = _super.userInput;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QRekamMedisDetail(String variable) {
        this(RekamMedisDetail.class, forVariable(variable), INITS);
    }

    public QRekamMedisDetail(Path<? extends RekamMedisDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRekamMedisDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRekamMedisDetail(PathMetadata<?> metadata, PathInits inits) {
        this(RekamMedisDetail.class, metadata, inits);
    }

    public QRekamMedisDetail(Class<? extends RekamMedisDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rekamMedis = inits.isInitialized("rekamMedis") ? new QRekamMedis(forProperty("rekamMedis"), inits.get("rekamMedis")) : null;
    }

}

