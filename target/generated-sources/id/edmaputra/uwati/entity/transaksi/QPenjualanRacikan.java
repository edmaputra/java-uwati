package id.edmaputra.uwati.entity.transaksi;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPenjualanRacikan is a Querydsl query type for PenjualanRacikan
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenjualanRacikan extends EntityPathBase<PenjualanRacikan> {

    private static final long serialVersionUID = 294778440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPenjualanRacikan penjualanRacikan = new QPenjualanRacikan("penjualanRacikan");

    public final id.edmaputra.uwati.entity.QDasarTransaksiEntity _super = new id.edmaputra.uwati.entity.QDasarTransaksiEntity(this);

    public final NumberPath<java.math.BigDecimal> hargaJual = createNumber("hargaJual", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath info = _super.info;

    public final QPenjualan penjualan;

    public final id.edmaputra.uwati.entity.master.obat.QRacikan racikan;

    public final ListPath<PenjualanDetailRacikan, QPenjualanDetailRacikan> racikanDetail = this.<PenjualanDetailRacikan, QPenjualanDetailRacikan>createList("racikanDetail", PenjualanDetailRacikan.class, QPenjualanDetailRacikan.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> terakhirDirubah = _super.terakhirDirubah;

    //inherited
    public final DateTimePath<java.util.Date> waktuDibuat = _super.waktuDibuat;

    public QPenjualanRacikan(String variable) {
        this(PenjualanRacikan.class, forVariable(variable), INITS);
    }

    public QPenjualanRacikan(Path<? extends PenjualanRacikan> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanRacikan(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPenjualanRacikan(PathMetadata<?> metadata, PathInits inits) {
        this(PenjualanRacikan.class, metadata, inits);
    }

    public QPenjualanRacikan(Class<? extends PenjualanRacikan> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.penjualan = inits.isInitialized("penjualan") ? new QPenjualan(forProperty("penjualan"), inits.get("penjualan")) : null;
        this.racikan = inits.isInitialized("racikan") ? new id.edmaputra.uwati.entity.master.obat.QRacikan(forProperty("racikan")) : null;
    }

}

