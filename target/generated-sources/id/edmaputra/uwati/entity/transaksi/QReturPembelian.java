package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReturPembelian is a Querydsl query type for ReturPembelian
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReturPembelian extends EntityPathBase<ReturPembelian> {

    private static final long serialVersionUID = 3562984L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReturPembelian returPembelian = new QReturPembelian("returPembelian");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final DatePath<java.util.Date> deadline = createDate("deadline", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> grandTotal = createNumber("grandTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final id.edmaputra.uwati.entity.pengguna.QPengguna pengguna;

    public final ListPath<ReturPembelianDetail, QReturPembelianDetail> returPembelianDetail = this.<ReturPembelianDetail, QReturPembelianDetail>createList("returPembelianDetail", ReturPembelianDetail.class, QReturPembelianDetail.class, PathInits.DIRECT2);

    public final StringPath supplier = createString("supplier");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public final DatePath<java.util.Date> waktuTransaksi = createDate("waktuTransaksi", java.util.Date.class);

    public QReturPembelian(String variable) {
        this(ReturPembelian.class, forVariable(variable), INITS);
    }

    public QReturPembelian(Path<? extends ReturPembelian> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReturPembelian(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReturPembelian(PathMetadata<?> metadata, PathInits inits) {
        this(ReturPembelian.class, metadata, inits);
    }

    public QReturPembelian(Class<? extends ReturPembelian> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pengguna = inits.isInitialized("pengguna") ? new id.edmaputra.uwati.entity.pengguna.QPengguna(forProperty("pengguna")) : null;
    }

}

