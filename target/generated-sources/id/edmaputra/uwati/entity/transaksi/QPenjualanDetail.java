package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPenjualanDetail is a Querydsl query type for PenjualanDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenjualanDetail extends EntityPathBase<PenjualanDetail> {

    private static final long serialVersionUID = -802748340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPenjualanDetail penjualanDetail = new QPenjualanDetail("penjualanDetail");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> diskon = createNumber("diskon", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hargaTotal = createNumber("hargaTotal", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final BooleanPath isRacikan = createBoolean("isRacikan");

    public final NumberPath<Integer> jumlah = createNumber("jumlah", Integer.class);

    public final id.edmaputra.uwati.entity.master.obat.QObatDetail obatDetail;

    public final NumberPath<java.math.BigDecimal> pajak = createNumber("pajak", java.math.BigDecimal.class);

    public final QPenjualan penjualan;

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPenjualanDetail(String variable) {
        this(PenjualanDetail.class, forVariable(variable), INITS);
    }

    public QPenjualanDetail(Path<? extends PenjualanDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanDetail(PathMetadata<?> metadata, PathInits inits) {
        this(PenjualanDetail.class, metadata, inits);
    }

    public QPenjualanDetail(Class<? extends PenjualanDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.obatDetail = inits.isInitialized("obatDetail") ? new id.edmaputra.uwati.entity.master.obat.QObatDetail(forProperty("obatDetail"), inits.get("obatDetail")) : null;
        this.penjualan = inits.isInitialized("penjualan") ? new QPenjualan(forProperty("penjualan"), inits.get("penjualan")) : null;
    }

}

