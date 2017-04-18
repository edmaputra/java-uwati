package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPembelian is a Querydsl query type for Pembelian
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPembelian extends EntityPathBase<Pembelian> {

    private static final long serialVersionUID = 650723492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPembelian pembelian = new QPembelian("pembelian");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> grandTotal = createNumber("grandTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final StringPath nomorFaktur = createString("nomorFaktur");

    public final ListPath<PembelianDetail, QPembelianDetail> pembelianDetail = this.<PembelianDetail, QPembelianDetail>createList("pembelianDetail", PembelianDetail.class, QPembelianDetail.class, PathInits.DIRECT2);

    public final id.edmaputra.uwati.entity.pengguna.QPengguna pengguna;

    public final StringPath supplier = createString("supplier");

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public final DatePath<java.util.Date> waktuTransaksi = createDate("waktuTransaksi", java.util.Date.class);

    public QPembelian(String variable) {
        this(Pembelian.class, forVariable(variable), INITS);
    }

    public QPembelian(Path<? extends Pembelian> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPembelian(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPembelian(PathMetadata<?> metadata, PathInits inits) {
        this(Pembelian.class, metadata, inits);
    }

    public QPembelian(Class<? extends Pembelian> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pengguna = inits.isInitialized("pengguna") ? new id.edmaputra.uwati.entity.pengguna.QPengguna(forProperty("pengguna")) : null;
    }

}

