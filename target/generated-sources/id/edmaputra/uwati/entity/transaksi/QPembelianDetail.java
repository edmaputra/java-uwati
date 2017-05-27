package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPembelianDetail is a Querydsl query type for PembelianDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPembelianDetail extends EntityPathBase<PembelianDetail> {

    private static final long serialVersionUID = 405158421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPembelianDetail pembelianDetail = new QPembelianDetail("pembelianDetail");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaBeli = createNumber("hargaBeli", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJualResep = createNumber("hargaJualResep", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final BooleanPath isReturned = createBoolean("isReturned");

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final StringPath obat = createString("obat");

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final QPembelian pembelian;

    public final NumberPath<java.math.BigDecimal> subTotal = createNumber("subTotal", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> tanggalKadaluarsa = createDateTime("tanggalKadaluarsa", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPembelianDetail(String variable) {
        this(PembelianDetail.class, forVariable(variable), INITS);
    }

    public QPembelianDetail(Path<? extends PembelianDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPembelianDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPembelianDetail(PathMetadata<?> metadata, PathInits inits) {
        this(PembelianDetail.class, metadata, inits);
    }

    public QPembelianDetail(Class<? extends PembelianDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pembelian = inits.isInitialized("pembelian") ? new QPembelian(forProperty("pembelian"), inits.get("pembelian")) : null;
    }

}

