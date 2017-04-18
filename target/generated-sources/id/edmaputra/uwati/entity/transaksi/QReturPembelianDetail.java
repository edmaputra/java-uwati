package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReturPembelianDetail is a Querydsl query type for ReturPembelianDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReturPembelianDetail extends EntityPathBase<ReturPembelianDetail> {

    private static final long serialVersionUID = -1917758375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReturPembelianDetail returPembelianDetail = new QReturPembelianDetail("returPembelianDetail");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaBeli = createNumber("hargaBeli", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaTotal = createNumber("hargaTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final id.edmaputra.uwati.entity.master.obat.QObatDetail obatDetail;

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final QReturPembelian returPembelian;

    public final DateTimePath<java.util.Date> tanggalKadaluarsa = createDateTime("tanggalKadaluarsa", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QReturPembelianDetail(String variable) {
        this(ReturPembelianDetail.class, forVariable(variable), INITS);
    }

    public QReturPembelianDetail(Path<? extends ReturPembelianDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReturPembelianDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReturPembelianDetail(PathMetadata<?> metadata, PathInits inits) {
        this(ReturPembelianDetail.class, metadata, inits);
    }

    public QReturPembelianDetail(Class<? extends ReturPembelianDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.obatDetail = inits.isInitialized("obatDetail") ? new id.edmaputra.uwati.entity.master.obat.QObatDetail(forProperty("obatDetail"), inits.get("obatDetail")) : null;
        this.returPembelian = inits.isInitialized("returPembelian") ? new QReturPembelian(forProperty("returPembelian"), inits.get("returPembelian")) : null;
    }

}

